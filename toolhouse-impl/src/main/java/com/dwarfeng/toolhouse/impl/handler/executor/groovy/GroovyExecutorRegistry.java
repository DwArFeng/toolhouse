package com.dwarfeng.toolhouse.impl.handler.executor.groovy;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.toolhouse.impl.handler.executor.AbstractExecutorRegistry;
import com.dwarfeng.toolhouse.stack.exception.ExecutorException;
import com.dwarfeng.toolhouse.stack.exception.ExecutorMakeException;
import com.dwarfeng.toolhouse.stack.handler.Executor;
import groovy.lang.GroovyClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Groovy 执行器注册。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component("groovyExecutorRegistry")
public class GroovyExecutorRegistry extends AbstractExecutorRegistry {

    public static final String EXECUTOR_TYPE = "groovy_executor";

    private static final Logger LOGGER = LoggerFactory.getLogger(GroovyExecutorRegistry.class);

    private final ApplicationContext ctx;

    public GroovyExecutorRegistry(ApplicationContext ctx) {
        super(EXECUTOR_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "Groovy 执行器";
    }

    @Override
    public String provideDescription() {
        return "通过自定义的 groovy 脚本，进行数据执行。";
    }

    @Override
    public String provideExampleParam() {
        try {
            Resource resource = ctx.getResource("classpath:groovy/ExampleExecutorProcessor.groovy");
            String example;
            try (InputStream sin = resource.getInputStream();
                 StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)) {
                IOUtil.trans(sin, sout, 4096);
                sout.flush();
                example = sout.toString();
            }
            return example;
        } catch (Exception e) {
            LOGGER.warn("读取文件 classpath:groovy/ExampleExecutorProcessor.groovy 时出现异常", e);
            return "";
        }
    }

    @Override
    public Executor makeExecutor(String type, String param) throws ExecutorException {
        try (GroovyClassLoader classLoader = new GroovyClassLoader()) {
            // 通过 Groovy 脚本生成处理器。
            Class<?> aClass = classLoader.parseClass(param);
            Processor processor = (Processor) aClass.newInstance();
            // 生成并返回执行器。
            return ctx.getBean(GroovyExecutor.class, ctx, processor);
        } catch (Exception e) {
            throw new ExecutorMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "GroovyExecutorRegistry{" +
                "ctx=" + ctx +
                ", executorType='" + executorType + '\'' +
                '}';
    }
}
