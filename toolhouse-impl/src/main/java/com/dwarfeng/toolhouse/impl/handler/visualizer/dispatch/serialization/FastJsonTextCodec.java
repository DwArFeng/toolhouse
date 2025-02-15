package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.serialization;

import com.alibaba.fastjson.JSON;

/**
 * FastJson 文本编解码器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonTextCodec implements TextDeserializer, TextSerializer {

    @Override
    public <T> T deserialize(String text, Class<T> type) {
        return JSON.parseObject(text, type);
    }

    @Override
    public String serialize(Object object) {
        return JSON.toJSONString(object);
    }
}
