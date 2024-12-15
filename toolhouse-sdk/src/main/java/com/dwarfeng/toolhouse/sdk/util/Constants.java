package com.dwarfeng.toolhouse.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class Constants {

    @PermissionLevelItem
    public static final int PERMISSION_LEVEL_OWNER = 0;
    @PermissionLevelItem
    public static final int PERMISSION_LEVEL_GUEST = 1;

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);
    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> permissionLevelSpace = null;

    /**
     * 获取权限等级的空间。
     *
     * @return 权限等级的空间。
     */
    public static List<Integer> permissionLevelSpace() {
        if (Objects.nonNull(permissionLevelSpace)) {
            return permissionLevelSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(permissionLevelSpace)) {
                return permissionLevelSpace;
            }
            initPermissionLevelSpace();
            return permissionLevelSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initPermissionLevelSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(PermissionLevelItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        permissionLevelSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
