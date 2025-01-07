package com.dwarfeng.toolhouse.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class Constraints {

    /**
     * 字符串 ID 的长度约束。
     */
    public static final int LENGTH_STRING_ID = 100;

    /**
     * 名称的长度约束。
     */
    public static final int LENGTH_NAME = 50;

    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;

    /**
     * 用户主键的长度约束。
     */
    public static final int LENGTH_USER = 50;

    /**
     * 可视化器、执行器类型的长度约束。
     */
    public static final int LENGTH_TYPE = 50;

    /**
     * 消息的长度约束。
     */
    public static final int LENGTH_MESSAGE = 200;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
