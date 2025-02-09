package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.handler.VisualizeCacheHandler;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;
import com.dwarfeng.toolhouse.stack.handler.VisualizerHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VisualizeCacheHandlerImpl implements VisualizeCacheHandler {

    private final VisualizerHandler visualizerHandler;

    public VisualizeCacheHandlerImpl(VisualizerHandler visualizerHandler) {
        this.visualizerHandler = visualizerHandler;
    }

    @Override
    public void clearCache() throws HandlerException {
        try {
            List<Visualizer> visualizers = visualizerHandler.all();
            for (Visualizer visualizer : visualizers) {
                visualizer.clearCache();
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
