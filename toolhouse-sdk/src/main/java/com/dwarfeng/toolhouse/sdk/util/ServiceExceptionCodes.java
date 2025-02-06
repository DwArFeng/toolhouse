package com.dwarfeng.toolhouse.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 14000;

    public static final ServiceException.Code USER_NOT_EXISTS =
            new ServiceException.Code(offset(0), "user not exists");
    public static final ServiceException.Code USER_NOT_PERMITTED_FOR_CABINET =
            new ServiceException.Code(offset(10), "user not permitted for cabinet");
    public static final ServiceException.Code CABINET_NOT_EXISTS =
            new ServiceException.Code(offset(20), "cabinet not exists");
    public static final ServiceException.Code INVALID_PERMISSION_LEVEL =
            new ServiceException.Code(offset(30), "invalid permission level");
    public static final ServiceException.Code FOLDER_NOT_EXISTS =
            new ServiceException.Code(offset(40), "note node not exists");
    public static final ServiceException.Code ILLEGAL_FOLDER_STATE =
            new ServiceException.Code(offset(50), "illegal note node state");
    public static final ServiceException.Code CABINET_NOT_IDENTICAL =
            new ServiceException.Code(offset(60), "cabinet not identical");
    public static final ServiceException.Code TOOL_NOT_EXISTS =
            new ServiceException.Code(offset(70), "note item not exists");
    public static final ServiceException.Code ILLEGAL_TOOL_STATE =
            new ServiceException.Code(offset(80), "illegal note item state");
    public static final ServiceException.Code VISUALIZER_INFO_NOT_EXISTS =
            new ServiceException.Code(offset(90), "visualizer info not exists");
    public static final ServiceException.Code EXECUTOR_INFO_NOT_EXISTS =
            new ServiceException.Code(offset(100), "executor info not exists");
    public static final ServiceException.Code SESSION_NOT_EXISTS =
            new ServiceException.Code(offset(110), "session not exists");
    public static final ServiceException.Code USER_NOT_CONSISTS =
            new ServiceException.Code(offset(120), "user not consists");
    public static final ServiceException.Code FILE_INFO_NOT_EXISTS =
            new ServiceException.Code(offset(130), "file info not exists");
    public static final ServiceException.Code INVALID_VARIABLE_TYPE =
            new ServiceException.Code(offset(140), "invalid variable type");
    public static final ServiceException.Code TASK_NOT_EXISTS =
            new ServiceException.Code(offset(150), "task not exists");
    public static final ServiceException.Code TASK_STATUS_MISMATCH =
            new ServiceException.Code(offset(160), "task status mismatch");
    public static final ServiceException.Code INVALID_TASK_ITEM_TYPE =
            new ServiceException.Code(offset(170), "invalid task item type");
    public static final ServiceException.Code EXECUTOR_FAILED =
            new ServiceException.Code(offset(180), "executor failed");
    public static final ServiceException.Code EXECUTOR_MAKE_FAILED =
            new ServiceException.Code(offset(181), "executor make failed");
    public static final ServiceException.Code EXECUTOR_EXECUTION_FAILED =
            new ServiceException.Code(offset(182), "executor execution failed");
    public static final ServiceException.Code EXECUTOR_TYPE_UNSUPPORTED =
            new ServiceException.Code(offset(183), "executor type unsupported");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        USER_NOT_EXISTS.setCode(offset(0));
        USER_NOT_PERMITTED_FOR_CABINET.setCode(offset(10));
        CABINET_NOT_EXISTS.setCode(offset(20));
        INVALID_PERMISSION_LEVEL.setCode(offset(30));
        FOLDER_NOT_EXISTS.setCode(offset(40));
        ILLEGAL_FOLDER_STATE.setCode(offset(50));
        CABINET_NOT_IDENTICAL.setCode(offset(60));
        TOOL_NOT_EXISTS.setCode(offset(70));
        ILLEGAL_TOOL_STATE.setCode(offset(80));
        VISUALIZER_INFO_NOT_EXISTS.setCode(offset(90));
        EXECUTOR_INFO_NOT_EXISTS.setCode(offset(100));
        SESSION_NOT_EXISTS.setCode(offset(110));
        USER_NOT_CONSISTS.setCode(offset(120));
        FILE_INFO_NOT_EXISTS.setCode(offset(130));
        INVALID_VARIABLE_TYPE.setCode(offset(140));
        TASK_NOT_EXISTS.setCode(offset(150));
        TASK_STATUS_MISMATCH.setCode(offset(160));
        INVALID_TASK_ITEM_TYPE.setCode(offset(170));
        EXECUTOR_FAILED.setCode(offset(180));
        EXECUTOR_MAKE_FAILED.setCode(offset(181));
        EXECUTOR_EXECUTION_FAILED.setCode(offset(182));
        EXECUTOR_TYPE_UNSUPPORTED.setCode(offset(183));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
