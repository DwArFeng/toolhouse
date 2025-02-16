package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.handler.PushHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class PushHandlerImpl implements PushHandler {

    private final List<Pusher> pushers;

    @Value("${pusher.type}")
    private String pusherType;

    private Pusher pusher;

    public PushHandlerImpl(List<Pusher> pushers) {
        this.pushers = Optional.ofNullable(pushers).orElse(Collections.emptyList());
    }

    @PostConstruct
    public void init() throws HandlerException {
        this.pusher = pushers.stream().filter(p -> p.supportType(pusherType)).findAny().orElseThrow(
                () -> new HandlerException("未知的 pusher 类型: " + pusherType)
        );
    }

    @Override
    public void executeReset() throws HandlerException {
        pusher.executeReset();
    }

    @Override
    public void visualizeReset() throws HandlerException {
        pusher.visualizeReset();
    }

    @Override
    public void taskFinished(Task task) throws HandlerException {
        pusher.taskFinished(task);
    }

    @Override
    public void taskFailed(Task task) throws HandlerException {
        pusher.taskFailed(task);
    }

    @Override
    public void taskExpired(Task task) throws HandlerException {
        pusher.taskExpired(task);
    }

    @Override
    public void taskDied(Task task) throws HandlerException {
        pusher.taskDied(task);
    }

    @Override
    public String toString() {
        return "PushHandlerImpl{" +
                "pushers=" + pushers +
                ", pusherType='" + pusherType + '\'' +
                ", pusher=" + pusher +
                '}';
    }
}
