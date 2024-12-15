package com.dwarfeng.toolhouse.sdk.bean.key.formatter;

import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;

import java.util.Objects;

/**
 * PocaKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class PocaStringKeyFormatter implements StringKeyFormatter<PocaKey> {

    private String prefix;

    public PocaStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(PocaKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getCabinetLongId() + "_" + key.getUserStringId();
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
        return "PocaStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
