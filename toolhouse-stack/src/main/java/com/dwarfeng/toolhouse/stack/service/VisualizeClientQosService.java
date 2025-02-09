package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 可视化 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizeClientQosService extends Service {

    /**
     * 手动调用可视化器。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器手动调用信息。
     * @return 可视化器调用结果。
     * @throws ServiceException 服务异常。
     */
    VisualizerCallResult manualVisualizerCall(StringIdKey operateUserKey, VisualizerManualCallInfo info)
            throws ServiceException;

    /**
     * 手动流式调用可视化器。
     *
     * <p>
     * 如果 <code>VisualizerManualStreamCallInfo.requestStream</code> 不为 <code>null</code>，则调用者有义务关闭该流。
     *
     * <p>
     * 如果返回的 <code>VisualizerStreamCallResult.responseStream</code> 不为 <code>null</code>，则调用者有义务关闭该流。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器手动流式调用信息。
     * @return 可视化器流式调用结果。
     * @throws ServiceException 服务异常。
     */
    VisualizerStreamCallResult manualVisualizerStreamCall(
            StringIdKey operateUserKey, VisualizerManualStreamCallInfo info
    ) throws ServiceException;

    /**
     * 超控调用可视化器。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器超控调用信息。
     * @return 可视化器调用结果。
     * @throws ServiceException 服务异常。
     */
    VisualizerCallResult overrideVisualizerCall(StringIdKey operateUserKey, VisualizerOverrideCallInfo info)
            throws ServiceException;

    /**
     * 超控流式调用可视化器。
     *
     * <p>
     * 如果 <code>VisualizerOverrideStreamCallInfo.requestStream</code> 不为 <code>null</code>，则调用者有义务关闭该流。
     *
     * <p>
     * 如果返回的 <code>VisualizerStreamCallResult.responseStream</code> 不为 <code>null</code>，则调用者有义务关闭该流。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器超控流式调用信息。
     * @return 可视化器流式调用结果。
     * @throws ServiceException 服务异常。
     */
    VisualizerStreamCallResult overrideVisualizerStreamCall(
            StringIdKey operateUserKey, VisualizerOverrideStreamCallInfo info
    ) throws ServiceException;

    /**
     * 系统调用可视化器。
     *
     * @param info 可视化器系统调用信息。
     * @return 可视化器调用结果。
     * @throws ServiceException 服务异常。
     */
    VisualizerCallResult systemVisualizerCall(VisualizerSystemCallInfo info) throws ServiceException;

    /**
     * 系统流式调用可视化器。
     *
     * <p>
     * 如果 <code>VisualizerSystemStreamCallInfo.requestStream</code> 不为 <code>null</code>，则调用者有义务关闭该流。
     *
     * <p>
     * 如果返回的 <code>VisualizerStreamCallResult.responseStream</code> 不为 <code>null</code>，则调用者有义务关闭该流。
     *
     * @param info 可视化器系统流式调用信息。
     * @return 可视化器流式调用结果。
     * @throws ServiceException 服务异常。
     */
    VisualizerStreamCallResult systemVisualizerStreamCall(VisualizerSystemStreamCallInfo info) throws ServiceException;
}
