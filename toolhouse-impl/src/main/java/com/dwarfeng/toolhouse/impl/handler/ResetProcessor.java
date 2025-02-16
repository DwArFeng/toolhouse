package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.handler.ExecuteLocalCacheHandler;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;
import com.dwarfeng.toolhouse.stack.handler.VisualizerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final ExecuteLocalCacheHandler executeLocalCacheHandler;
    private final VisualizerHandler visualizerHandler;

    private final Lock lock = new ReentrantLock();

    public ResetProcessor(
            ExecuteLocalCacheHandler executeLocalCacheHandler,
            VisualizerHandler visualizerHandler
    ) {
        this.executeLocalCacheHandler = executeLocalCacheHandler;
        this.visualizerHandler = visualizerHandler;
    }

    public void resetExecute() throws HandlerException {
        lock.lock();
        try {
            doResetExecute();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetExecute() throws Exception {
        // 清空本地缓存。
        executeLocalCacheHandler.clear();
    }

    public void resetVisualize() throws HandlerException {
        lock.lock();
        try {
            doResetVisualize();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetVisualize() throws Exception {
        // 对所有可视化器执行清除缓存操作。
        List<Visualizer> all = visualizerHandler.all();

        for (Visualizer visualizer : all) {
            try {
                visualizer.clearCache();
            } catch (Exception e) {
                String message = "清除可视化器缓存时发生异常, 可视化器: " + visualizer + ", 异常信息如下: ";
                LOGGER.error(message, e);
            }
        }
    }
}
