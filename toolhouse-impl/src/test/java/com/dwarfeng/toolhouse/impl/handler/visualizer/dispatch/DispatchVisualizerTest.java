package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.dutil.basic.io.StringInputStream;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean.TestRequestDto;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean.TestResponseDto;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.controller.*;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.groups.DefaultGroup;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.groups.TestGroup01;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.groups.TestGroup02;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.groups.TestGroup03;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization.FastJsonTextCodec;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class DispatchVisualizerTest {

    @Autowired
    private ApplicationContext ctx;

    @Autowired
    private TestController01 testController01;
    @Autowired
    private TestController02 testController02;
    @Autowired
    private TestController03 testController03;
    @Autowired
    private TestController04 testController04;
    @Autowired
    private TestController05 testController05;

    private FastJsonTextCodec codec;
    private Visualizer.Context visualizerContext;
    private Visualizer.CallerContext visualizerCallerContext;

    @Before
    public void setUp() {
        codec = new FastJsonTextCodec();
        visualizerContext = new InnerVisualizerContext();
        visualizerCallerContext = new InnerVisualizerCallerContext();
        testController01.reset();
        testController02.reset();
        testController03.reset();
        testController04.reset();
        testController05.reset();
    }

    @Test
    public void testHandleCall01() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup01.class), codec, codec
        );
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        VisualizerSystemCallInfo visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                null, null, TestController01.CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto)
        );
        VisualizerCallResult visualizerCallResult = visualizer.handleCall(
                visualizerCallerContext, visualizerSystemCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController01.CONTROLLER_NAME, testResponseDto.getControllerName());
        assertEquals(1, testController01.getHandleCallCount());
        assertEquals(0, testController02.getHandleCallCount());
        assertEquals(0, testController03.getHandleCallCount());
        assertEquals(0, testController04.getHandleCallCount());
        assertEquals(0, testController05.getHandleCallCount());
    }

    @Test
    public void testHandleCall02() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup02.class), codec, codec
        );
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        VisualizerSystemCallInfo visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                null, null, TestController01.CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto)
        );
        VisualizerCallResult visualizerCallResult = visualizer.handleCall(
                visualizerCallerContext, visualizerSystemCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController01.CONTROLLER_NAME, testResponseDto.getControllerName());
        testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                null, null, TestController02.CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto)
        );
        visualizerCallResult = visualizer.handleCall(
                visualizerCallerContext, visualizerSystemCallInfo
        );
        testResponseDto = JSON.parseObject(
                visualizerCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController02.CONTROLLER_NAME, testResponseDto.getControllerName());
        assertEquals(1, testController01.getHandleCallCount());
        assertEquals(1, testController02.getHandleCallCount());
        assertEquals(0, testController03.getHandleCallCount());
        assertEquals(0, testController04.getHandleCallCount());
        assertEquals(0, testController05.getHandleCallCount());
    }

    @Test
    public void testHandleCall03() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup03.class), codec, codec
        );
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        VisualizerSystemCallInfo visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                null, null, TestController03.CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto)
        );
        VisualizerCallResult visualizerCallResult = visualizer.handleCall(
                visualizerCallerContext, visualizerSystemCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController03.CONTROLLER_NAME, testResponseDto.getControllerName());
        assertEquals(0, testController01.getHandleCallCount());
        assertEquals(0, testController02.getHandleCallCount());
        assertEquals(1, testController03.getHandleCallCount());
        assertEquals(0, testController04.getHandleCallCount());
        assertEquals(0, testController05.getHandleCallCount());
    }

    @Test
    public void testHandleCall04() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(ctx, Collections.emptyList(), codec, codec);
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        VisualizerSystemCallInfo visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                null, null, TestController04.CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto)
        );
        VisualizerCallResult visualizerCallResult = visualizer.handleCall(
                visualizerCallerContext, visualizerSystemCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController04.CONTROLLER_NAME, testResponseDto.getControllerName());
        testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                null, null, TestController05.CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto)
        );
        visualizerCallResult = visualizer.handleCall(
                visualizerCallerContext, visualizerSystemCallInfo
        );
        testResponseDto = JSON.parseObject(
                visualizerCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController05.CONTROLLER_NAME, testResponseDto.getControllerName());
        assertEquals(0, testController01.getHandleCallCount());
        assertEquals(0, testController02.getHandleCallCount());
        assertEquals(0, testController03.getHandleCallCount());
        assertEquals(1, testController04.getHandleCallCount());
        assertEquals(1, testController05.getHandleCallCount());
    }

    @Test
    public void testHandleStreamCall01() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup01.class), codec, codec
        );
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        InputStream requestStream = new StringInputStream(UUID.randomUUID().toString());
        VisualizerSystemStreamCallInfo visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                null, null, TestController01.STREAM_CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto), requestStream
        );
        requestStream.close();
        VisualizerStreamCallResult visualizerStreamCallResult = visualizer.handleStreamCall(
                visualizerCallerContext, visualizerSystemStreamCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerStreamCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController01.CONTROLLER_NAME, testResponseDto.getControllerName());
        InputStream responseStream = visualizerStreamCallResult.getResponseStream();
        assertEquals(TestController01.CONTROLLER_NAME, readResponseStream(responseStream));
        responseStream.close();
        assertEquals(1, testController01.getHandleStreamCallCount());
        assertEquals(0, testController02.getHandleStreamCallCount());
        assertEquals(0, testController03.getHandleStreamCallCount());
        assertEquals(0, testController04.getHandleStreamCallCount());
        assertEquals(0, testController05.getHandleStreamCallCount());
    }

    @Test
    public void testHandleStreamCall02() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup02.class), codec, codec
        );
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        InputStream requestStream = new StringInputStream(UUID.randomUUID().toString());
        VisualizerSystemStreamCallInfo visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                null, null, TestController01.STREAM_CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto), requestStream
        );
        requestStream.close();
        VisualizerStreamCallResult visualizerStreamCallResult = visualizer.handleStreamCall(
                visualizerCallerContext, visualizerSystemStreamCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerStreamCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController01.CONTROLLER_NAME, testResponseDto.getControllerName());
        InputStream responseStream = visualizerStreamCallResult.getResponseStream();
        assertEquals(TestController01.CONTROLLER_NAME, readResponseStream(responseStream));
        responseStream.close();
        testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        requestStream = new StringInputStream(UUID.randomUUID().toString());
        visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                null, null, TestController02.STREAM_CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto), requestStream
        );
        requestStream.close();
        visualizerStreamCallResult = visualizer.handleStreamCall(
                visualizerCallerContext, visualizerSystemStreamCallInfo
        );
        testResponseDto = JSON.parseObject(
                visualizerStreamCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController02.CONTROLLER_NAME, testResponseDto.getControllerName());
        responseStream = visualizerStreamCallResult.getResponseStream();
        assertEquals(TestController02.CONTROLLER_NAME, readResponseStream(responseStream));
        responseStream.close();
        assertEquals(1, testController01.getHandleStreamCallCount());
        assertEquals(1, testController02.getHandleStreamCallCount());
        assertEquals(0, testController03.getHandleStreamCallCount());
        assertEquals(0, testController04.getHandleStreamCallCount());
        assertEquals(0, testController05.getHandleStreamCallCount());
    }

    @Test
    public void testHandleStreamCall03() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup03.class), codec, codec
        );
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        InputStream requestStream = new StringInputStream(UUID.randomUUID().toString());
        VisualizerSystemStreamCallInfo visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                null, null, TestController03.STREAM_CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto), requestStream
        );
        requestStream.close();
        VisualizerStreamCallResult visualizerStreamCallResult = visualizer.handleStreamCall(
                visualizerCallerContext, visualizerSystemStreamCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerStreamCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController03.CONTROLLER_NAME, testResponseDto.getControllerName());
        InputStream responseStream = visualizerStreamCallResult.getResponseStream();
        assertEquals(TestController03.CONTROLLER_NAME, readResponseStream(responseStream));
        responseStream.close();
        assertEquals(0, testController01.getHandleStreamCallCount());
        assertEquals(0, testController02.getHandleStreamCallCount());
        assertEquals(1, testController03.getHandleStreamCallCount());
        assertEquals(0, testController04.getHandleStreamCallCount());
        assertEquals(0, testController05.getHandleStreamCallCount());
    }

    @Test
    public void testHandleStreamCall04() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(ctx, Collections.emptyList(), codec, codec);
        visualizer.init(visualizerContext);
        TestRequestDto testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        InputStream requestStream = new StringInputStream(UUID.randomUUID().toString());
        VisualizerSystemStreamCallInfo visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                null, null, TestController04.STREAM_CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto), requestStream
        );
        requestStream.close();
        VisualizerStreamCallResult visualizerStreamCallResult = visualizer.handleStreamCall(
                visualizerCallerContext, visualizerSystemStreamCallInfo
        );
        TestResponseDto testResponseDto = JSON.parseObject(
                visualizerStreamCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController04.CONTROLLER_NAME, testResponseDto.getControllerName());
        InputStream responseStream = visualizerStreamCallResult.getResponseStream();
        assertEquals(TestController04.CONTROLLER_NAME, readResponseStream(responseStream));
        responseStream.close();
        testRequestDto = new TestRequestDto(UUID.randomUUID().toString());
        requestStream = new StringInputStream(UUID.randomUUID().toString());
        visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                null, null, TestController05.STREAM_CALL_FUNCTION_NAME, JSON.toJSONString(testRequestDto), requestStream
        );
        visualizerStreamCallResult = visualizer.handleStreamCall(
                visualizerCallerContext, visualizerSystemStreamCallInfo
        );
        testResponseDto = JSON.parseObject(
                visualizerStreamCallResult.getResponseText(), TestResponseDto.class
        );
        assertEquals(TestController05.CONTROLLER_NAME, testResponseDto.getControllerName());
        responseStream = visualizerStreamCallResult.getResponseStream();
        assertEquals(TestController05.CONTROLLER_NAME, readResponseStream(responseStream));
        responseStream.close();
        assertEquals(0, testController01.getHandleStreamCallCount());
        assertEquals(0, testController02.getHandleStreamCallCount());
        assertEquals(0, testController03.getHandleStreamCallCount());
        assertEquals(1, testController04.getHandleStreamCallCount());
        assertEquals(1, testController05.getHandleStreamCallCount());
    }

    private String readResponseStream(InputStream responseStream) throws Exception {
        // 由于字节数量较少，因此一次性读取全部内容。
        byte[] bytes = new byte[responseStream.available()];
        int read = responseStream.read(bytes);
        assert read == bytes.length;
        return new String(bytes);
    }

    @Test
    public void testClearCache01() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup01.class), codec, codec
        );
        visualizer.init(visualizerContext);
        visualizer.clearCache();
        assertEquals(2, testController01.getClearCacheCount());
        assertEquals(0, testController02.getClearCacheCount());
        assertEquals(0, testController03.getClearCacheCount());
        assertEquals(0, testController04.getClearCacheCount());
        assertEquals(0, testController05.getClearCacheCount());
    }

    @Test
    public void testClearCache02() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(
                ctx, Collections.singletonList(TestGroup02.class), codec, codec
        );
        visualizer.init(visualizerContext);
        visualizer.clearCache();
        assertEquals(2, testController01.getClearCacheCount());
        assertEquals(2, testController02.getClearCacheCount());
        assertEquals(0, testController03.getClearCacheCount());
        assertEquals(0, testController04.getClearCacheCount());
        assertEquals(0, testController05.getClearCacheCount());
    }

    @Test
    public void testClearCache03() throws Exception {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(TestGroup01.class);
        groups.add(TestGroup02.class);
        groups.add(TestGroup03.class);
        DispatchVisualizer visualizer = new DispatchVisualizer(ctx, groups, codec, codec);
        visualizer.init(visualizerContext);
        visualizer.clearCache();
        assertEquals(2, testController01.getClearCacheCount());
        assertEquals(2, testController02.getClearCacheCount());
        assertEquals(2, testController03.getClearCacheCount());
        assertEquals(0, testController04.getClearCacheCount());
        assertEquals(0, testController05.getClearCacheCount());
    }

    @Test
    public void testClearCache04() throws Exception {
        DispatchVisualizer visualizer = new DispatchVisualizer(ctx, Collections.emptyList(), codec, codec);
        visualizer.init(visualizerContext);
        visualizer.clearCache();
        assertEquals(0, testController01.getClearCacheCount());
        assertEquals(0, testController02.getClearCacheCount());
        assertEquals(0, testController03.getClearCacheCount());
        assertEquals(2, testController04.getClearCacheCount());
        assertEquals(2, testController05.getClearCacheCount());
    }

    @Test
    public void testClearCache05() throws Exception {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(TestGroup01.class);
        groups.add(TestGroup02.class);
        groups.add(TestGroup03.class);
        groups.add(DefaultGroup.class);
        DispatchVisualizer visualizer = new DispatchVisualizer(ctx, groups, codec, codec);
        visualizer.init(visualizerContext);
        visualizer.clearCache();
        assertEquals(2, testController01.getClearCacheCount());
        assertEquals(2, testController02.getClearCacheCount());
        assertEquals(2, testController03.getClearCacheCount());
        assertEquals(2, testController04.getClearCacheCount());
        assertEquals(2, testController05.getClearCacheCount());
    }

    private static class InnerVisualizerContext implements Visualizer.Context {

        @Override
        public File downloadFile(FileSystemDownloadInfo info) {
            return null;
        }

        @Override
        public FileStream downloadFileStream(FileStreamSystemDownloadInfo info) {
            return null;
        }

        @Override
        public FileUploadResult uploadFile(FileSystemUploadInfo info) {
            return null;
        }

        @Override
        public FileStreamUploadResult uploadFileStream(FileStreamSystemUploadInfo info) {
            return null;
        }

        @Override
        public void updateFile(FileSystemUpdateInfo info) {
        }

        @Override
        public void updateFileStream(FileStreamSystemUpdateInfo info) {
        }

        @Override
        public void removeFile(FileSystemRemoveInfo info) {
        }

        @Override
        public VariableInspectResult inspectVariable(VariableSystemInspectInfo info) {
            return null;
        }

        @Override
        public void upsertVariable(VariableSystemUpsertInfo info) {
        }

        @Override
        public void removeVariable(VariableSystemRemoveInfo info) {
        }

        @Override
        public TaskCreateResult createTask(TaskSystemCreateInfo info) {
            return null;
        }

        @Override
        public void executeTask(TaskSystemExecuteInfo info) {
        }

        @Override
        public CompletableFuture<Void> executeTaskAsync(TaskSystemExecuteInfo info) {
            return null;
        }

        @Nullable
        @Override
        public InputItemInspectResult inspectInputItem(InputItemSystemInspectInfo info) {
            return null;
        }

        @Override
        public void upsertInputItem(InputItemSystemUpsertInfo info) {
        }

        @Override
        public void removeInputItem(InputItemSystemRemoveInfo info) {
        }

        @Nullable
        @Override
        public OutputItemInspectResult inspectOutputItem(OutputItemSystemInspectInfo info) {
            return null;
        }

        @Override
        public void upsertOutputItem(OutputItemSystemUpsertInfo info) {
        }

        @Override
        public void removeOutputItem(OutputItemSystemRemoveInfo info) {
        }
    }

    private static class InnerVisualizerCallerContext implements Visualizer.CallerContext {

        @Override
        public VisualizerInfo getVisualizerInfo() {
            return null;
        }

        @Override
        public LongIdKey getToolKey() {
            return null;
        }

        @Override
        public LongIdKey getSessionKey() {
            return null;
        }

        @Nullable
        @Override
        public Task latestTask() {
            return null;
        }

        @Nullable
        @Override
        public Task latestCreatedTask() {
            return null;
        }

        @Override
        public List<Task> latestTasks() {
            return null;
        }

        @Override
        public List<Task> latestTasks(PagingInfo pagingInfo) {
            return null;
        }
    }
}
