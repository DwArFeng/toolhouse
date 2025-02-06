package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.exception.ExecutorException;

import javax.annotation.Nullable;

/**
 * 执行器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Executor {

    /**
     * 生成一个新的执行器代理人。
     *
     * @return 新的执行器代理人。
     * @throws ExecutorException 执行器异常。
     */
    Agent newAgent() throws ExecutorException;

    /**
     * 执行器代理人。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    interface Agent {

        /**
         * 初始化执行器。
         *
         * <p>
         * 该方法会在执行器初始化后调用。<br>
         * 该方法会传入一个 {@link Context}，此对象为执行器的上下文，其中包含了执行器需要使用的所有方法。<br>
         * 实现该方法时，应妥善保存上下文，以便在后续的方法调用中使用。
         *
         * @param context 执行器的上下文。
         */
        void init(Context context);

        /**
         * 执行处理过程。
         *
         * @throws ExecutorException 执行器异常。
         */
        void execute() throws ExecutorException;
    }

    /**
     * 执行器上下文。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    interface Context {

        /**
         * 获取执行器的字符串标识。
         *
         * @return 执行器的字符串标识。
         */
        String getExecutorStringId();

        /**
         * 获取会话键。
         *
         * @return 会话键。
         */
        LongIdKey getSessionKey();

        /**
         * 获取任务键。
         *
         * @return 任务键。
         */
        LongIdKey getTaskKey();

        /**
         * 更新任务模态。
         *
         * @param info 任务系统更新模态信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void updateTaskModal(TaskSystemUpdateModalInfo info) throws Exception;

        /**
         * 下载文件。
         *
         * @param info 文件系统下载信息。
         * @return 文件。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        File downloadFile(FileSystemDownloadInfo info) throws Exception;

        /**
         * 下载文件流。
         *
         * <p>
         * 如果返回的 <code>FileStream</code> 不为 <code>null</code>，则调用者有义务关闭
         * <code>FileStream</code> 中的输入流，即其中的 <code>FileStream.content</code>。
         *
         * @param info 文件流系统下载信息。
         * @return 文件流。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        FileStream downloadFileStream(FileStreamSystemDownloadInfo info) throws Exception;

        /**
         * 上传文件。
         *
         * @param info 文件系统上传信息。
         * @return 文件上传结果。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        FileUploadResult uploadFile(FileSystemUploadInfo info) throws Exception;

        /**
         * 上传文件流。
         *
         * <p>
         * 调用者有义务关闭 <code>FileStreamSystemUploadInfo</code> 中的输入流，
         * 即其中的 <code>FileStreamSystemUploadInfo.content</code>。
         *
         * @param info 文件流系统上传信息。
         * @return 文件流上传结果。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        FileStreamUploadResult uploadFileStream(FileStreamSystemUploadInfo info) throws Exception;

        /**
         * 更新文件。
         *
         * @param info 文件系统更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void updateFile(FileSystemUpdateInfo info) throws Exception;

        /**
         * 更新文件流。
         *
         * <p>
         * 调用者有义务关闭 <code>FileStreamSystemUpdateInfo</code> 中的输入流，
         * 即其中的 <code>FileStreamSystemUpdateInfo.content</code>。
         *
         * @param info 文件流系统更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void updateFileStream(FileStreamSystemUpdateInfo info) throws Exception;

        /**
         * 删除文件。
         *
         * @param info 文件系统删除信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeFile(FileSystemRemoveInfo info) throws Exception;

        /**
         * 查询变量。
         *
         * <p>
         * 如果 {@link VariableSystemInspectInfo#getSessionKey()} 对应的会话不存在，则抛出异常。<br>
         * 如果 {@link VariableSystemInspectInfo#getVariableStringId()}  对应的变量不存在，则返回 null。
         *
         * @param info 变量系统查询信息。
         * @return 变量系统查询结果。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        VariableInspectResult inspectVariable(VariableSystemInspectInfo info) throws Exception;

        /**
         * 创建/更新变量。
         *
         * @param info 变量系统创建/更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertVariable(VariableSystemUpsertInfo info) throws Exception;

        /**
         * 删除变量。
         *
         * @param info 变量系统删除信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeVariable(VariableSystemRemoveInfo info) throws Exception;

        /**
         * 查询输入项。
         *
         * <p>
         * 如果 {@link InputItemSystemInspectInfo#getTaskKey()} 对应的任务不存在，则抛出异常。<br>
         * 如果 {@link InputItemSystemInspectInfo#getItemStringId()}  对应的输入项不存在，则返回 null。
         *
         * @param info 输入项系统查询信息。
         * @return 输入项系统查询结果。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        @Nullable
        InputItemInspectResult inspectInputItem(InputItemSystemInspectInfo info) throws Exception;

        /**
         * 创建/更新输入项。
         *
         * @param info 输入项系统创建/更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertInputItem(InputItemSystemUpsertInfo info) throws Exception;

        /**
         * 删除输入项。
         *
         * @param info 输入项系统删除信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeInputItem(InputItemSystemRemoveInfo info) throws Exception;

        /**
         * 查询输出项。
         *
         * <p>
         * 如果 {@link OutputItemSystemInspectInfo#getTaskKey()} 对应的任务不存在，则抛出异常。<br>
         * 如果 {@link OutputItemSystemInspectInfo#getItemStringId()}  对应的输出项不存在，则返回 null。
         *
         * @param info 输出项系统查询信息。
         * @return 输出项系统查询结果。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        @Nullable
        OutputItemInspectResult inspectOutputItem(OutputItemSystemInspectInfo info) throws Exception;

        /**
         * 创建/更新输出项。
         *
         * @param info 输出项系统创建/更新信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void upsertOutputItem(OutputItemSystemUpsertInfo info) throws Exception;

        /**
         * 删除输出项。
         *
         * @param info 输出项系统删除信息。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void removeOutputItem(OutputItemSystemRemoveInfo info) throws Exception;
    }
}
