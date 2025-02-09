package com.dwarfeng.toolhouse.impl.handler.visualizer;

import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerCallResult;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerStreamCallResult;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerSystemCallInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerSystemStreamCallInfo;
import com.dwarfeng.toolhouse.stack.exception.VisualizerException;
import com.dwarfeng.toolhouse.stack.exception.VisualizerExecutionException;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;

/**
 * 可视化器的抽象实现。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public abstract class AbstractVisualizer implements Visualizer {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public VisualizerCallResult handleCall(CallerContext callerContext, VisualizerSystemCallInfo info)
            throws VisualizerException {
        try {
            return doHandleCall(callerContext, info);
        } catch (VisualizerException e) {
            throw e;
        } catch (Exception e) {
            throw new VisualizerExecutionException(e);
        }
    }

    /**
     * 处理调用。
     *
     * <p>
     * 异常处理逻辑已经在抽象实现中完成，调用者无需处理异常。
     *
     * @param callerContext 调用者上下文。
     * @param info          系统调用信息。
     * @return 处理结果。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    protected abstract VisualizerCallResult doHandleCall(CallerContext callerContext, VisualizerSystemCallInfo info)
            throws Exception;

    @Override
    public VisualizerStreamCallResult handleStreamCall(CallerContext callerContext, VisualizerSystemStreamCallInfo info)
            throws VisualizerException {
        try {
            return doHandleStreamCall(callerContext, info);
        } catch (VisualizerException e) {
            throw e;
        } catch (Exception e) {
            throw new VisualizerExecutionException(e);
        }
    }

    /**
     * 处理流调用。
     *
     * <p>
     * 异常处理逻辑已经在抽象实现中完成，调用者无需处理异常。
     *
     * @param callerContext 调用者上下文。
     * @param info          系统调用信息。
     * @return 处理结果。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    protected abstract VisualizerStreamCallResult doHandleStreamCall(CallerContext callerContext, VisualizerSystemStreamCallInfo info)
            throws Exception;

    @Override
    public void clearCache() throws VisualizerException {
        try {
            doClearCache();
        } catch (VisualizerException e) {
            throw e;
        } catch (Exception e) {
            throw new VisualizerExecutionException(e);
        }
    }

    /**
     * 清除缓存。
     *
     * <p>
     * 异常处理逻辑已经在抽象实现中完成，调用者无需处理异常。
     *
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    protected abstract void doClearCache() throws Exception;

    @Override
    public String toString() {
        return "AbstractVisualizer{" +
                "context=" + context +
                '}';
    }
}
