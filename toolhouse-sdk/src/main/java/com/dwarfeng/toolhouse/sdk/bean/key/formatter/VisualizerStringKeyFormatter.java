package com.dwarfeng.toolhouse.sdk.bean.key.formatter;

import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

import java.util.Objects;

/**
 * VisualizerKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerStringKeyFormatter implements StringKeyFormatter<VisualizerKey> {

    private String prefix;

    public VisualizerStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(VisualizerKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getToolLongId() + "_" + key.getVisualizerStringId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "VisualizerStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
