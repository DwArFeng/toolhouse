package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoUpdateInfo;

/**
 * 可视化器信息操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerInfoOperateHandler extends Handler {

    /**
     * 创建可视化器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器信息创建信息。
     * @throws HandlerException 处理器异常。
     */
    void create(StringIdKey operateUserKey, VisualizerInfoCreateInfo info) throws HandlerException;

    /**
     * 更新可视化器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器信息更新信息。
     * @throws HandlerException 处理器异常。
     */
    void update(StringIdKey operateUserKey, VisualizerInfoUpdateInfo info) throws HandlerException;

    /**
     * 删除可视化器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器信息删除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(StringIdKey operateUserKey, VisualizerInfoRemoveInfo info) throws HandlerException;
}
