package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.controller;

import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.annotations.*;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean.TestRequestDto;
import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.model.ResultContext;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;

import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试基础控制器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TestBaseController {

    protected final AtomicInteger handleCallCounter = new AtomicInteger(0);
    protected final AtomicInteger handleStreamCallCounter = new AtomicInteger(0);
    protected final AtomicInteger clearCacheCounter = new AtomicInteger(0);

    protected void handleCall(
            @VisualizerCallerContextParam Visualizer.CallerContext callerContext,
            @VisualizerContextParam Visualizer.Context context,
            @RequestTextParam TestRequestDto testRequestDto,
            @RequestStreamParam InputStream inputStream,
            @ResultContextParam ResultContext resultContext
    ) throws Exception {
        handleCallCounter.incrementAndGet();
    }

    protected void handleStreamCall(
            @VisualizerCallerContextParam Visualizer.CallerContext callerContext,
            @VisualizerContextParam Visualizer.Context context,
            @RequestTextParam TestRequestDto testRequestDto,
            @RequestStreamParam InputStream inputStream,
            @ResultContextParam ResultContext resultContext
    ) throws Exception {
        handleStreamCallCounter.incrementAndGet();
    }

    @ClearCacheMapping
    public void clearCache0() {
        clearCacheCounter.incrementAndGet();
    }

    @ClearCacheMapping
    public void clearCache1() {
        clearCacheCounter.incrementAndGet();
    }

    public int getHandleCallCount() {
        return handleCallCounter.get();
    }

    public int getHandleStreamCallCount() {
        return handleStreamCallCounter.get();
    }

    public int getClearCacheCount() {
        return clearCacheCounter.get();
    }

    public void reset() {
        handleCallCounter.set(0);
        handleStreamCallCounter.set(0);
        clearCacheCounter.set(0);
    }
}
