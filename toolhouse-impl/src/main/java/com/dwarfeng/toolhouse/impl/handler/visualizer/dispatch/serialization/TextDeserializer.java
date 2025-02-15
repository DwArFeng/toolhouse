package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization;

/**
 * 文本反序列化器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface TextDeserializer {

    /**
     * 反序列化指定的文本为指定的类型。
     *
     * @param text 指定的文本。
     * @param type 指定的类型。
     * @param <T>  指定类型的泛型。
     * @return 反序列化的结果。
     * @throws Exception 反序列化过程中发生的任何异常。
     */
    <T> T deserialize(String text, Class<T> type) throws Exception;
}
