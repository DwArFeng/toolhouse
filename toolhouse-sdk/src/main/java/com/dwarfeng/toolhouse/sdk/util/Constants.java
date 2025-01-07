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

    public static final int IO_TRANS_BUFFER_SIZE = 4096;

    @TaskStatusItem
    public static final int TASK_STATUS_CREATED = 0;
    @TaskStatusItem
    public static final int TASK_STATUS_PROCESSING = 1;
    @TaskStatusItem
    public static final int TASK_STATUS_FINISHED = 2;
    @TaskStatusItem
    public static final int TASK_STATUS_FAILED = 3;
    @TaskStatusItem
    public static final int TASK_STATUS_EXPIRED = 4;
    @TaskStatusItem
    public static final int TASK_STATUS_DIED = 5;

    @VariableTypeItem
    public static final int VARIABLE_TYPE_STRING = 0;
    @VariableTypeItem
    public static final int VARIABLE_TYPE_LONG = 1;
    @VariableTypeItem
    public static final int VARIABLE_TYPE_DOUBLE = 2;
    @VariableTypeItem
    public static final int VARIABLE_TYPE_BOOLEAN = 3;
    @VariableTypeItem
    public static final int VARIABLE_TYPE_DATE = 4;
    @VariableTypeItem
    public static final int VARIABLE_TYPE_FILE = 5;

    @TaskItemTypeItem
    public static final int TASK_ITEM_TYPE_STRING = 0;
    @TaskItemTypeItem
    public static final int TASK_ITEM_TYPE_LONG = 1;
    @TaskItemTypeItem
    public static final int TASK_ITEM_TYPE_DOUBLE = 2;
    @TaskItemTypeItem
    public static final int TASK_ITEM_TYPE_BOOLEAN = 3;
    @TaskItemTypeItem
    public static final int TASK_ITEM_TYPE_DATE = 4;
    @TaskItemTypeItem
    public static final int TASK_ITEM_TYPE_FILE = 5;

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);
    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> permissionLevelSpace = null;
    private static List<Integer> taskStatusSpace = null;
    private static List<Integer> variableTypeSpace = null;
    private static List<Integer> taskItemTypeSpace = null;

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

    /**
     * 任务状态空间。
     *
     * @return 任务状态空间。
     */
    public static List<Integer> taskStatusSpace() {
        if (Objects.nonNull(taskStatusSpace)) {
            return taskStatusSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(taskStatusSpace)) {
                return taskStatusSpace;
            }
            initTaskStatusSpace();
            return taskStatusSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initTaskStatusSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(TaskStatusItem.class)) {
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

        taskStatusSpace = Collections.unmodifiableList(result);
    }

    /**
     * 变量类型空间。
     *
     * @return 变量类型空间。
     */
    public static List<Integer> variableTypeSpace() {
        if (Objects.nonNull(variableTypeSpace)) {
            return variableTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(variableTypeSpace)) {
                return variableTypeSpace;
            }
            initVariableTypeSpace();
            return variableTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initVariableTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(VariableTypeItem.class)) {
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

        variableTypeSpace = Collections.unmodifiableList(result);
    }

    /**
     * 任务项类型空间。
     *
     * @return 任务项类型空间。
     */
    public static List<Integer> taskItemTypeSpace() {
        if (Objects.nonNull(taskItemTypeSpace)) {
            return taskItemTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(taskItemTypeSpace)) {
                return taskItemTypeSpace;
            }
            initTaskItemTypeSpace();
            return taskItemTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initTaskItemTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(TaskItemTypeItem.class)) {
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

        taskItemTypeSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
