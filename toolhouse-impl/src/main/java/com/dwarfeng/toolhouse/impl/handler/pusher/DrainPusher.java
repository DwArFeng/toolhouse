package com.dwarfeng.toolhouse.impl.handler.pusher;

import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import org.springframework.stereotype.Component;

/**
 * 简单的丢弃掉所有信息的推送器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class DrainPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "drain";

    public DrainPusher() {
        super(PUSHER_TYPE);
    }

    @Override
    public void executeReset() {
    }

    @Override
    public void visualizeReset() {
    }

    @Override
    public void taskFinished(Task task) {
    }

    @Override
    public void taskFailed(Task task) {
    }

    @Override
    public void taskExpired(Task task) {
    }

    @Override
    public void taskDied(Task task) {
    }

    @Override
    public String toString() {
        return "DrainPusher{" +
                "pusherType='" + pusherType + '\'' +
                '}';
    }
}
