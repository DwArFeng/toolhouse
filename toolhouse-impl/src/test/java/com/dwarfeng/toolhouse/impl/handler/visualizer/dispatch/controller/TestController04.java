package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.controller;

import com.dwarfeng.dutil.basic.io.StringInputStream;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.annotations.*;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean.TestRequestDto;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean.TestResponseDto;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.model.ResultContext;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;

import java.io.InputStream;

/**
 * 测试控制器 04。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Controller
public class TestController04 extends TestBaseController {

    public static final String CALL_FUNCTION_NAME = "test_call_04";
    public static final String STREAM_CALL_FUNCTION_NAME = "test_stream_call_04";
    public static final String CONTROLLER_NAME = "test_controller_04";

    @FunctionMapping(functionName = CALL_FUNCTION_NAME)
    @Override
    public void handleCall(
            @VisualizerCallerContextParam Visualizer.CallerContext callerContext,
            @VisualizerContextParam Visualizer.Context context,
            @RequestTextParam TestRequestDto testRequestDto,
            @RequestStreamParam InputStream inputStream,
            @ResultContextParam ResultContext resultContext
    ) throws Exception {
        super.handleCall(callerContext, context, testRequestDto, inputStream, resultContext);
        resultContext.setResponseText(new TestResponseDto(CONTROLLER_NAME));
        resultContext.setResponseStream(new StringInputStream(CONTROLLER_NAME));
    }

    @FunctionMapping(functionName = STREAM_CALL_FUNCTION_NAME)
    @Override
    public void handleStreamCall(
            @VisualizerCallerContextParam Visualizer.CallerContext callerContext,
            @VisualizerContextParam Visualizer.Context context,
            @RequestTextParam TestRequestDto testRequestDto,
            @RequestStreamParam InputStream inputStream,
            @ResultContextParam ResultContext resultContext
    ) throws Exception {
        super.handleStreamCall(callerContext, context, testRequestDto, inputStream, resultContext);
        resultContext.setResponseText(new TestResponseDto(CONTROLLER_NAME));
        resultContext.setResponseStream(new StringInputStream(CONTROLLER_NAME));
    }
}
