package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

/**
 * 可视化器信息不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 6693861195904509745L;
    
    private final VisualizerKey visualizerInfoKey;

    public VisualizerInfoNotExistsException(VisualizerKey visualizerInfoKey) {
        this.visualizerInfoKey = visualizerInfoKey;
    }

    public VisualizerInfoNotExistsException(Throwable cause, VisualizerKey visualizerInfoKey) {
        super(cause);
        this.visualizerInfoKey = visualizerInfoKey;
    }

    @Override
    public String getMessage() {
        return "可视化器信息 " + visualizerInfoKey + " 不存在";
    }
}
