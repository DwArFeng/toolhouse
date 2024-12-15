package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolUpdateInfo;

/**
 * 工具操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ToolOperateService extends Service {

    /**
     * 创建工具。
     *
     * @param userKey        工具的所有者的主键。
     * @param toolCreateInfo 工具的创建信息。
     * @return 生成的工具的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createTool(StringIdKey userKey, ToolCreateInfo toolCreateInfo) throws ServiceException;

    /**
     * 更新工具。
     *
     * @param userKey        工具的所有者的主键。
     * @param toolUpdateInfo 工具的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateTool(StringIdKey userKey, ToolUpdateInfo toolUpdateInfo) throws ServiceException;

    /**
     * 删除工具。
     *
     * @param userKey 工具的所有者的主键。
     * @param toolKey 工具的主键。
     * @throws ServiceException 服务异常。
     */
    void removeTool(StringIdKey userKey, LongIdKey toolKey) throws ServiceException;
}
