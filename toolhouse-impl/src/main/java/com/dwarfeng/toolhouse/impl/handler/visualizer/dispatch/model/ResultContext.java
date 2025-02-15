package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.model;

import com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization.TextSerializer;

import java.io.InputStream;

/**
 * 结果上下文。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ResultContext {

    /**
     * 设置结果文本。
     *
     * <p>
     * 该方法允许传入任意对象，传入的对象将会被可视化器中指定的序列化器序列化为文本。
     *
     * @param responseObject 结果对象。
     * @throws Exception 方法执行过程中发生的任何异常。
     * @see TextSerializer
     */
    void setResponseText(Object responseObject) throws Exception;

    /**
     * 设置结果文本。
     *
     * @param responseText 结果文本。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void setResponseText(String responseText) throws Exception;

    /**
     * 设置结果文本描述。
     *
     * @param responseTextDescription 结果文本描述。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void setResponseTextDescription(String responseTextDescription) throws Exception;

    /**
     * 设置结果流。
     *
     * @param responseStream 结果流。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void setResponseStream(InputStream responseStream) throws Exception;

    /**
     * 设置结果流描述。
     *
     * @param responseStreamDescription 结果流描述。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void setResponseStreamDescription(String responseStreamDescription) throws Exception;
}
