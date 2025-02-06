package com.dwarfeng.toolhouse.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_executor_support}")
    private boolean resetExecutorSupport;

    public boolean isResetExecutorSupport() {
        return resetExecutorSupport;
    }

    @Override
    public String toString() {
        return "LauncherSettingHandler{" +
                "resetExecutorSupport=" + resetExecutorSupport +
                '}';
    }
}
