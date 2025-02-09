package com.dwarfeng.toolhouse.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_executor_support}")
    private boolean resetExecutorSupport;
    @Value("${launcher.reset_visualizer_support}")
    private boolean resetVisualizerSupport;

    public boolean isResetExecutorSupport() {
        return resetExecutorSupport;
    }

    public boolean isResetVisualizerSupport() {
        return resetVisualizerSupport;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetExecutorSupport=" + resetExecutorSupport +
                ", resetVisualizerSupport=" + resetVisualizerSupport +
                '}';
    }
}
