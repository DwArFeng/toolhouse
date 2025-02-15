package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch;

import com.dwarfeng.toolhouse.impl.handler.visualizer.AbstractVisualizer;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.annotations.*;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.groups.DefaultGroup;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.model.ResultContext;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization.TextDeserializer;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization.TextSerializer;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct.ClearCacheMethodMeta;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct.RequestMethodMeta;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct.RequestParamMeta;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct.RequestParamType;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerCallResult;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerStreamCallResult;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerSystemCallInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerSystemStreamCallInfo;
import com.dwarfeng.toolhouse.stack.exception.VisualizerException;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;
import org.springframework.context.ApplicationContext;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 调度可视化器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class DispatchVisualizer extends AbstractVisualizer {

    private static final List<Class<?>> DEFAULT_GROUPS = Collections.singletonList(DefaultGroup.class);

    private final ApplicationContext ctx;

    private final List<Class<?>> groups;
    private final TextDeserializer textDeserializer;
    private final TextSerializer textSerializer;

    private final Map<String, RequestMethodMeta> requestMethodMetaMap = new HashMap<>();
    private final Set<ClearCacheMethodMeta> clearCacheMethodMetas = new HashSet<>();

    public DispatchVisualizer(
            ApplicationContext ctx,
            List<Class<?>> groups, TextDeserializer textDeserializer, TextSerializer textSerializer
    ) throws VisualizerException {
        // 判空。
        Objects.requireNonNull(ctx, "ctx cannot be null");
        Objects.requireNonNull(groups, "groups cannot be null");
        Objects.requireNonNull(textDeserializer, "textDeserializer cannot be null");
        Objects.requireNonNull(textSerializer, "textSerializer cannot be null");

        // 设置字段。
        this.ctx = ctx;
        this.groups = groups;
        this.textDeserializer = textDeserializer;
        this.textSerializer = textSerializer;

        // 初始化。
        init();
    }

    private void init() throws VisualizerException {
        try {
            doInit();
        } catch (Exception e) {
            throw new VisualizerException(e);
        }
    }

    private void doInit() {
        // 获取可视化器使用的 Controller 的所有分组组成的列表。
        List<Class<?>> groups = parseGroups();

        // 在 ctx 中获取所包含 Controller 注解的 bean。
        Map<String, Object> controllerMap = ctx.getBeansWithAnnotation(Controller.class);

        // 对每一个 Bean 进行处理。
        for (Object bean : controllerMap.values()) {
            processSingleBean(groups, bean);
        }
    }

    private List<Class<?>> parseGroups() {
        // 特殊值判断：如果 groupNames 为空，则返回 DefaultGroup.class。
        if (groups.isEmpty()) {
            return DEFAULT_GROUPS;
        }
        // 否则返回 groups。
        return groups;
    }

    private void processSingleBean(List<Class<?>> groups, Object bean) {
        // 获取 bean 的 Controller 注解。
        // 由于 bean 是通过 ctx.getBeansWithAnnotation(Controller.class) 获取的，
        // 所以 bean 一定有 Controller 注解。
        Controller controller = bean.getClass().getAnnotation(Controller.class);

        // 根据 controller 获取 bean 的分组。
        List<Class<?>> beanGroups = parseBeanGroups(controller);

        // 根据 groups 和 beanGroups 判断 bean 是否命中该可视化器。
        boolean beanHit = false;
        for (Class<?> beanGroup : beanGroups) {
            for (Class<?> group : groups) {
                if (beanGroup.isAssignableFrom(group)) {
                    beanHit = true;
                    break;
                }
            }
        }

        // 如果未能命中，则直接返回。
        if (!beanHit) {
            return;
        }

        // 处理 Bean 中的方法。
        processBeanMethods(bean);
    }

    private List<Class<?>> parseBeanGroups(Controller controller) {
        // 展开参数。
        Class<?>[] beanGroups = controller.groups();

        // 特殊值判断：如果 beanGroups 为空，则返回 DefaultGroup.class。
        if (beanGroups.length == 0) {
            return DEFAULT_GROUPS;
        }

        // 定义结果列表。
        List<Class<?>> results = new ArrayList<>(beanGroups.length);

        // 遍历 beanGroups，将其中的每一个 Class 对象添加到结果列表中。
        for (Class<?> beanGroup : beanGroups) {
            // beanGroup 必须是接口。
            if (!beanGroup.isInterface()) {
                throw new IllegalStateException("beanGroup 必须是接口");
            }

            // 将 beanGroup 添加到结果列表中。
            results.add(beanGroup);
        }

        // 返回结果列表。
        return results;
    }

    private void processBeanMethods(Object bean) {
        // 获取 bean 的所有方法。
        Method[] methods = bean.getClass().getMethods();

        // 遍历 bean 的所有方法。
        for (Method method : methods) {
            // 如果方法中含有 FunctionMapping 注解，则处理该方法。
            FunctionMapping functionMapping = method.getAnnotation(FunctionMapping.class);
            if (Objects.nonNull(functionMapping)) {
                processSingleMethodWithFunctionMapping(bean, method, functionMapping);
            }

            // 如果方法中含有 ClearCacheMapping 注解，则处理该方法。
            ClearCacheMapping clearCacheMapping = method.getAnnotation(ClearCacheMapping.class);
            if (Objects.nonNull(clearCacheMapping)) {
                processSingleMethodWithClearCacheMapping(bean, method, clearCacheMapping);
            }
        }
    }

    private void processSingleMethodWithFunctionMapping(
            Object bean, Method method, FunctionMapping functionMapping
    ) {
        // 通过 functionMapping 获取请求的方法名。
        String functionName = functionMapping.functionName();

        // 方法的返回值必须是 void。
        if (!method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalStateException("方法的返回值必须是 void");
        }

        // 遍历方法的参数列表。
        Parameter[] parameters = method.getParameters();

        // 定义 RequestParamMeta 列表。
        List<RequestParamMeta> requestParamMetas = new ArrayList<>(parameters.length);

        // 定义部分只能被解析一次的类型的标记。
        AtomicBoolean visualizerContextParsedFlag = new AtomicBoolean(false);
        AtomicBoolean visualizerCallerContextParsedFlag = new AtomicBoolean(false);
        AtomicBoolean requestTextParamParsedFlag = new AtomicBoolean(false);
        AtomicBoolean requestStreamParamParsedFlag = new AtomicBoolean(false);
        AtomicBoolean resultContextParsedFlag = new AtomicBoolean(false);

        // 遍历参数并解析。
        for (Parameter parameter : parameters) {
            requestParamMetas.add(parseSingleRequestParamMeta(
                    parameter, visualizerContextParsedFlag, visualizerCallerContextParsedFlag,
                    requestTextParamParsedFlag, requestStreamParamParsedFlag, resultContextParsedFlag
            ));
        }

        // 生成 RequestMethodMeta 对象。
        RequestMethodMeta requestMethodMeta = new RequestMethodMeta(functionName, bean, method, requestParamMetas);

        // 将 RequestMethodMeta 对象添加到 requestMethodMetaMap 中。
        requestMethodMetaMap.put(functionName, requestMethodMeta);
    }

    private RequestParamMeta parseSingleRequestParamMeta(
            Parameter parameter, AtomicBoolean visualizerContextParsedFlag,
            AtomicBoolean visualizerCallerContextParsedFlag, AtomicBoolean requestTextParamParsedFlag,
            AtomicBoolean requestStreamParamParsedFlag, AtomicBoolean resultContextParsedFlag
    ) {
        // 获取参数的类。
        Class<?> type = parameter.getType();

        // 获取参数的 VisualizerContextParam 注解。
        VisualizerContextParam visualizerContextParam = parameter.getAnnotation(VisualizerContextParam.class);
        // 如果 VisualizerContextParam 注解不为空。
        if (Objects.nonNull(visualizerContextParam)) {
            // 确保参数的类型是 Visualizer.Context 或是其父类。
            if (!type.isAssignableFrom(Visualizer.Context.class)) {
                throw new IllegalStateException("参数的类型必须是 Visualizer.Context 或是其父类");
            }
            // 如果 visualizerContextParsedFlag 为 true，则抛出异常。
            if (visualizerContextParsedFlag.get()) {
                throw new IllegalStateException("VisualizerContextParam 只能出现一次");
            }
            // 设置 visualizerContextParsedFlag 为 true。
            visualizerContextParsedFlag.set(true);
            // 返回 VisualizerContextParam 的 RequestParamMeta 对象。
            return new RequestParamMeta(RequestParamType.VISUALIZER_CONTEXT, type);
        }

        // 获取参数的 VisualizerCallerContextParam 注解。
        VisualizerCallerContextParam visualizerCallerContextParam = parameter.getAnnotation(
                VisualizerCallerContextParam.class
        );
        // 如果 VisualizerCallerContextParam 注解不为空。
        if (Objects.nonNull(visualizerCallerContextParam)) {
            // 确保参数的类型是 Visualizer.CallerContext 或是其父类。
            if (!type.isAssignableFrom(Visualizer.CallerContext.class)) {
                throw new IllegalStateException("参数的类型必须是 Visualizer.CallerContext 或是其父类");
            }
            // 如果 visualizerCallerContextParsedFlag 为 true，则抛出异常。
            if (visualizerCallerContextParsedFlag.get()) {
                throw new IllegalStateException("VisualizerCallerContextParam 只能出现一次");
            }
            // 设置 visualizerCallerContextParsedFlag 为 true。
            visualizerCallerContextParsedFlag.set(true);
            // 返回 VisualizerCallerContextParam 的 RequestParamMeta 对象。
            return new RequestParamMeta(RequestParamType.VISUALIZER_CALLER_CONTEXT, type);
        }

        // 获取参数的 RequestTextParam 注解。
        RequestTextParam requestTextParam = parameter.getAnnotation(RequestTextParam.class);
        // 如果 RequestTextParam 注解不为空。
        if (Objects.nonNull(requestTextParam)) {
            // 如果 requestTextParamParsedFlag 为 true，则抛出异常。
            if (requestTextParamParsedFlag.get()) {
                throw new IllegalStateException("RequestTextParam 只能出现一次");
            }
            // 设置 requestTextParamParsedFlag 为 true。
            requestTextParamParsedFlag.set(true);
            // 返回 RequestTextParam 的 RequestParamMeta 对象。
            return new RequestParamMeta(RequestParamType.REQUEST_TEXT, type);
        }

        // 获取参数的 RequestStreamParam 注解。
        RequestStreamParam requestStreamParam = parameter.getAnnotation(RequestStreamParam.class);
        // 如果 RequestStreamParam 注解不为空。
        if (Objects.nonNull(requestStreamParam)) {
            // 确保参数的类型是 InputStream 或是其父类。
            if (!type.isAssignableFrom(InputStream.class)) {
                throw new IllegalStateException("参数的类型必须是 InputStream 或是其父类");
            }
            // 如果 requestStreamParamParsedFlag 为 true，则抛出异常。
            if (requestStreamParamParsedFlag.get()) {
                throw new IllegalStateException("RequestStreamParam 只能出现一次");
            }
            // 设置 requestStreamParamParsedFlag 为 true。
            requestStreamParamParsedFlag.set(true);
            // 返回 RequestStreamParam 的 RequestParamMeta 对象。
            return new RequestParamMeta(RequestParamType.REQUEST_STREAM, type);
        }

        // 获取参数的 ResultContextParam 注解。
        ResultContextParam resultContextParam = parameter.getAnnotation(ResultContextParam.class);
        // 如果 ResultContextParam 注解不为空。
        if (Objects.nonNull(resultContextParam)) {
            // 确保参数的类型是 ResultContext 或是其父类。
            if (!type.isAssignableFrom(ResultContext.class)) {
                throw new IllegalStateException("参数的类型必须是 ResultContext 或是其父类");
            }
            // 如果 resultContextParsedFlag 为 true，则抛出异常。
            if (resultContextParsedFlag.get()) {
                throw new IllegalStateException("ResultContextParam 只能出现一次");
            }
            // 设置 resultContextParsedFlag 为 true。
            resultContextParsedFlag.set(true);
            // 返回 ResultContextParam 的 RequestParamMeta 对象。
            return new RequestParamMeta(RequestParamType.RESULT_CONTEXT, type);
        }

        // 如果参数没有命中以上任何一种注解，则抛出异常。
        throw new IllegalStateException("参数 " + parameter.getName() + " 没有有效的注解");
    }

    // 考虑到代码的对称性，抑制 unused 警告。
    @SuppressWarnings("unused")
    private void processSingleMethodWithClearCacheMapping(
            Object bean, Method method, ClearCacheMapping clearCacheMapping
    ) {
        // 确保方法的返回值是 void。
        if (!method.getReturnType().equals(Void.TYPE)) {
            throw new IllegalStateException("方法的返回值必须是 void");
        }

        // 确保方法的参数列表为空。
        if (method.getParameters().length != 0) {
            throw new IllegalStateException("方法的参数列表必须为空");
        }

        // 生成 ClearCacheMethodMeta 对象。
        clearCacheMethodMetas.add(new ClearCacheMethodMeta(bean, method));
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected VisualizerCallResult doHandleCall(CallerContext callerContext, VisualizerSystemCallInfo info)
            throws Exception {
        // 获取请求的方法名。
        String functionName = info.getFunctionName();

        // 从 requestMethodMetaMap 中获取 RequestMethodMeta 对象。
        RequestMethodMeta requestMethodMeta = requestMethodMetaMap.get(functionName);

        // 如果 requestMethodMeta 为空，则抛出异常。
        if (Objects.isNull(requestMethodMeta)) {
            throw new IllegalStateException("未找到请求的方法");
        }

        // 构造 resultContext 对象。
        InnerResultContext resultContext = new InnerResultContext();

        // 定义参数数组。
        List<RequestParamMeta> requestParamMetas = requestMethodMeta.getRequestParamMetas();
        Object[] args = new Object[requestParamMetas.size()];

        // 为参数数组装订数据。
        for (int i = 0; i < requestParamMetas.size(); i++) {
            RequestParamMeta requestParamMeta = requestParamMetas.get(i);
            switch (requestParamMeta.getRequestParamType()) {
                case VISUALIZER_CONTEXT:
                    args[i] = context;
                    break;
                case VISUALIZER_CALLER_CONTEXT:
                    args[i] = callerContext;
                    break;
                case REQUEST_TEXT:
                    args[i] = textDeserializer.deserialize(info.getRequestText(), requestParamMeta.getType());
                    break;
                case REQUEST_STREAM:
                    args[i] = null;
                    break;
                case RESULT_CONTEXT:
                    args[i] = resultContext;
                    break;
                default:
                    throw new IllegalStateException("未知的参数类型");
            }
        }

        // 调用方法。
        requestMethodMeta.getMethod().invoke(requestMethodMeta.getBean(), args);

        // 处理响应流。
        // 如果响应流不为空，则关闭响应流，因为响应流不会被传递给客户端，所以需要在此处关闭。
        if (Objects.nonNull(resultContext.getResponseStream())) {
            resultContext.getResponseStream().close();
        }

        // 构造 VisualizerCallResult 对象。
        return new VisualizerCallResult(resultContext.getResponseText(), resultContext.getResponseTextDescription());
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    protected VisualizerStreamCallResult doHandleStreamCall(
            CallerContext callerContext, VisualizerSystemStreamCallInfo info
    ) throws Exception {
        // 获取请求的方法名。
        String functionName = info.getFunctionName();

        // 从 requestMethodMetaMap 中获取 RequestMethodMeta 对象。
        RequestMethodMeta requestMethodMeta = requestMethodMetaMap.get(functionName);

        // 如果 requestMethodMeta 为空，则抛出异常。
        if (Objects.isNull(requestMethodMeta)) {
            throw new IllegalStateException("未找到请求的方法");
        }

        // 构造 resultContext 对象。
        InnerResultContext resultContext = new InnerResultContext();

        // 定义参数数组。
        List<RequestParamMeta> requestParamMetas = requestMethodMeta.getRequestParamMetas();
        Object[] args = new Object[requestParamMetas.size()];

        // 为参数数组装订数据。
        for (int i = 0; i < requestParamMetas.size(); i++) {
            RequestParamMeta requestParamMeta = requestParamMetas.get(i);
            switch (requestParamMeta.getRequestParamType()) {
                case VISUALIZER_CONTEXT:
                    args[i] = context;
                    break;
                case VISUALIZER_CALLER_CONTEXT:
                    args[i] = callerContext;
                    break;
                case REQUEST_TEXT:
                    args[i] = textDeserializer.deserialize(info.getRequestText(), requestParamMeta.getType());
                    break;
                case REQUEST_STREAM:
                    args[i] = null;
                    break;
                case RESULT_CONTEXT:
                    args[i] = resultContext;
                    break;
                default:
                    throw new IllegalStateException("未知的参数类型");
            }
        }

        // 调用方法。
        requestMethodMeta.getMethod().invoke(requestMethodMeta.getBean(), args);

        // 构造 VisualizerStreamCallResult 对象。
        return new VisualizerStreamCallResult(
                resultContext.getResponseText(), resultContext.getResponseTextDescription(),
                resultContext.getResponseStream(), resultContext.getResponseStreamDescription()
        );
    }

    @Override
    protected void doClearCache() throws Exception {
        for (ClearCacheMethodMeta clearCacheMethodMeta : clearCacheMethodMetas) {
            clearCacheMethodMeta.getMethod().invoke(clearCacheMethodMeta.getBean());
        }
    }

    @Override
    public String toString() {
        return "DispatchVisualizer{" +
                "ctx=" + ctx +
                ", groups=" + groups +
                ", textDeserializer=" + textDeserializer +
                ", textSerializer=" + textSerializer +
                ", requestMethodMetaMap=" + requestMethodMetaMap +
                ", clearCacheMethodMetas=" + clearCacheMethodMetas +
                ", context=" + context +
                '}';
    }

    private final class InnerResultContext implements ResultContext {

        private String responseText;
        private String responseTextDescription;
        private InputStream responseStream;
        private String responseStreamDescription;

        @Override
        public void setResponseText(Object responseObject) throws Exception {
            this.responseText = textSerializer.serialize(responseObject);
        }

        @Override
        public void setResponseText(String responseText) {
            this.responseText = responseText;
        }

        @Override
        public void setResponseTextDescription(String responseTextDescription) {
            this.responseTextDescription = responseTextDescription;
        }

        @Override
        public void setResponseStream(InputStream responseStream) {
            this.responseStream = responseStream;
        }

        @Override
        public void setResponseStreamDescription(String responseStreamDescription) {
            this.responseStreamDescription = responseStreamDescription;
        }

        public String getResponseText() {
            return responseText;
        }

        public String getResponseTextDescription() {
            return responseTextDescription;
        }

        public InputStream getResponseStream() {
            return responseStream;
        }

        public String getResponseStreamDescription() {
            return responseStreamDescription;
        }
    }
}
