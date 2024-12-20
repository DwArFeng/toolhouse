package com.dwarfeng.toolhouse.sdk.bean.key.formatter;

import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

import java.util.Objects;

/**
 * ExecutorKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorStringKeyFormatter implements StringKeyFormatter<ExecutorKey> {

    private String prefix;

    public ExecutorStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(ExecutorKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getToolLongId() + "_" + key.getExecutorStringId();
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
        return "ExecutorStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
