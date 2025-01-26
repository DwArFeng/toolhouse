package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskCreateResult;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideCreateInfo;

/**
 * 任务操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface TaskOperateService extends Service {

    /**
     * 手动创建任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务手动创建信息。
     * @return 任务创建结果。
     * @throws ServiceException 服务异常。
     */
    TaskCreateResult manualCreate(StringIdKey operateUserKey, TaskManualCreateInfo info) throws ServiceException;

    /**
     * 超控创建任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务超控创建信息。
     * @return 任务创建结果。
     * @throws ServiceException 服务异常。
     */
    TaskCreateResult overrideCreate(StringIdKey operateUserKey, TaskOverrideCreateInfo info) throws ServiceException;
}
