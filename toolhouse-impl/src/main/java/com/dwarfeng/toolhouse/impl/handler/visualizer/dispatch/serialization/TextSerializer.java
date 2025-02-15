package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization;

/**
 * 文本序列化器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface TextSerializer {

    /**
     * 将指定的对象序列化为文本。
     *
     * @param object 指定的对象。
     * @return 序列化后的文本。
     * @throws Exception 序列化过程中发生的任何异常。
     */
    String serialize(Object object) throws Exception;
}
