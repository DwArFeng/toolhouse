package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.handler.ExecuteLocalCacheHandler;
import com.dwarfeng.toolhouse.stack.handler.PushHandler;
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

    private final PushHandler pushHandler;

    private final Lock lock = new ReentrantLock();

    public ResetProcessor(
            ExecuteLocalCacheHandler executeLocalCacheHandler,
            VisualizerHandler visualizerHandler,
            PushHandler pushHandler
    ) {
        this.executeLocalCacheHandler = executeLocalCacheHandler;
        this.visualizerHandler = visualizerHandler;
        this.pushHandler = pushHandler;
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

        // 消息推送。
        try {
            pushHandler.executeReset();
        } catch (Exception e) {
            LOGGER.warn("推送执行功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
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

        // 消息推送。
        try {
            pushHandler.visualizeReset();
        } catch (Exception e) {
            LOGGER.warn("推送可视化功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
