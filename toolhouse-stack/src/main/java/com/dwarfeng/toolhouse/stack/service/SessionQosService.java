package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 会话 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SessionQosService extends Service {

    /**
     * 手动创建会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动创建信息。
     * @return 会话创建结果。
     * @throws ServiceException 服务异常。
     */
    SessionCreateResult manualCreate(StringIdKey operateUserKey, SessionManualCreateInfo info) throws ServiceException;

    /**
     * 手动删除会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动删除信息。
     * @throws ServiceException 服务异常。
     */
    void manualRemove(StringIdKey operateUserKey, SessionManualRemoveInfo info) throws ServiceException;

    /**
     * 超控创建会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动超控创建信息。
     * @return 会话创建结果。
     * @throws ServiceException 服务异常。
     */
    SessionCreateResult overrideCreate(StringIdKey operateUserKey, SessionOverrideCreateInfo info)
            throws ServiceException;

    /**
     * 超控删除会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动超控删除信息。
     * @throws ServiceException 服务异常。
     */
    void overrideRemove(StringIdKey operateUserKey, SessionOverrideRemoveInfo info) throws ServiceException;

    /**
     * 系统创建会话。
     *
     * @param info 会话系统创建信息。
     * @return 会话创建结果。
     * @throws ServiceException 服务异常。
     */
    SessionCreateResult systemCreate(SessionSystemCreateInfo info) throws ServiceException;

    /**
     * 系统删除会话。
     *
     * @param info 会话系统删除信息。
     * @throws ServiceException 服务异常。
     */
    void systemRemove(SessionSystemRemoveInfo info) throws ServiceException;
}
