package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemExecuteInfo;
import com.dwarfeng.toolhouse.stack.struct.ExecuteInfo;

/**
 * 执行 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecuteQosService extends Service {

    /**
     * 手动执行任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务手动执行信息。
     * @throws ServiceException 服务异常。
     */
    void manualExecuteTask(StringIdKey operateUserKey, TaskManualExecuteInfo info) throws ServiceException;

    /**
     * 超控执行任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务超控执行信息。
     * @throws ServiceException 服务异常。
     */
    void overrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) throws ServiceException;

    /**
     * 系统执行任务。
     *
     * @param info 任务系统执行信息。
     * @throws ServiceException 服务异常。
     */
    void systemExecuteTask(TaskSystemExecuteInfo info) throws ServiceException;

    /**
     * 获取指定工具的执行信息。
     *
     * @param toolKey 指定工具的主键。
     * @return 指定工具的执行信息。
     * @throws ServiceException 服务异常。
     */
    ExecuteInfo getExecuteInfo(LongIdKey toolKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
