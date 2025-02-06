package com.dwarfeng.toolhouse.node.launcher;

import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.node.handler.LauncherSettingHandler;
import com.dwarfeng.toolhouse.stack.service.ExecutorSupportMaintainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Launcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    // 为了程序的可扩展性，此处不进行代码简化。
    @SuppressWarnings("Convert2MethodRef")
    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性重置执行器。
            mayResetExecutor(ctx);
        });
    }

    private static void mayResetExecutor(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 判断是否重置执行器支持，并按条件执行重置操作。
        if (launcherSettingHandler.isResetExecutorSupport()) {
            LOGGER.info("重置执行器支持...");
            ExecutorSupportMaintainService maintainService = ctx.getBean(ExecutorSupportMaintainService.class);
            try {
                maintainService.reset();
            } catch (ServiceException e) {
                LOGGER.warn("执行器支持重置失败，异常信息如下", e);
            }
        }
    }
}
