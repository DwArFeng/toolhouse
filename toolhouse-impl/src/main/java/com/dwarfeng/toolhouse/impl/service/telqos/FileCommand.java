package com.dwarfeng.toolhouse.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputFileManualRemoveInfo;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputFileOverrideRemoveInfo;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputFileSystemRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.service.FileQosService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class FileCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_DOWNLOAD = "mandl";
    private static final String COMMAND_OPTION_MANUAL_DOWNLOAD_LONG_OPT = "manual-download";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_UPLOAD = "manul";
    private static final String COMMAND_OPTION_MANUAL_UPLOAD_LONG_OPT = "manual-upload";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_UPDATE = "manup";
    private static final String COMMAND_OPTION_MANUAL_UPDATE_LONG_OPT = "manual-update";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_REMOVE = "manrm";
    private static final String COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT = "manual-remove";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_DOWNLOAD = "ovrdl";
    private static final String COMMAND_OPTION_OVERRIDE_DOWNLOAD_LONG_OPT = "override-download";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_UPLOAD = "ovrul";
    private static final String COMMAND_OPTION_OVERRIDE_UPLOAD_LONG_OPT = "override-upload";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_UPDATE = "ovrup";
    private static final String COMMAND_OPTION_OVERRIDE_UPDATE_LONG_OPT = "override-update";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_REMOVE = "ovrrm";
    private static final String COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT = "override-remove";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_DOWNLOAD = "sysdl";
    private static final String COMMAND_OPTION_SYSTEM_DOWNLOAD_LONG_OPT = "system-download";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_UPLOAD = "sysul";
    private static final String COMMAND_OPTION_SYSTEM_UPLOAD_LONG_OPT = "system-upload";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_UPDATE = "sysup";
    private static final String COMMAND_OPTION_SYSTEM_UPDATE_LONG_OPT = "system-update";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_REMOVE = "sysrm";
    private static final String COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT = "system-remove";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_MANUAL_DOWNLOAD,
            COMMAND_OPTION_MANUAL_UPLOAD,
            COMMAND_OPTION_MANUAL_UPDATE,
            COMMAND_OPTION_MANUAL_REMOVE,
            COMMAND_OPTION_OVERRIDE_DOWNLOAD,
            COMMAND_OPTION_OVERRIDE_UPLOAD,
            COMMAND_OPTION_OVERRIDE_UPDATE,
            COMMAND_OPTION_OVERRIDE_REMOVE,
            COMMAND_OPTION_SYSTEM_DOWNLOAD,
            COMMAND_OPTION_SYSTEM_UPLOAD,
            COMMAND_OPTION_SYSTEM_UPDATE,
            COMMAND_OPTION_SYSTEM_REMOVE
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private static final String IDENTITY = "file";
    private static final String DESCRIPTION = "文件操作";

    private static final String CMD_LINE_SYNTAX_MANUAL_DOWNLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_DOWNLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_MANUAL_UPLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_UPLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_MANUAL_UPDATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_UPDATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_MANUAL_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_DOWNLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_DOWNLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_UPLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_UPLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_UPDATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_UPDATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_DOWNLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_DOWNLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_UPLOAD = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_UPLOAD) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_UPDATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_UPDATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_MANUAL_DOWNLOAD,
            CMD_LINE_SYNTAX_MANUAL_UPLOAD,
            CMD_LINE_SYNTAX_MANUAL_UPDATE,
            CMD_LINE_SYNTAX_MANUAL_REMOVE,
            CMD_LINE_SYNTAX_OVERRIDE_DOWNLOAD,
            CMD_LINE_SYNTAX_OVERRIDE_UPLOAD,
            CMD_LINE_SYNTAX_OVERRIDE_UPDATE,
            CMD_LINE_SYNTAX_OVERRIDE_REMOVE,
            CMD_LINE_SYNTAX_SYSTEM_DOWNLOAD,
            CMD_LINE_SYNTAX_SYSTEM_UPLOAD,
            CMD_LINE_SYNTAX_SYSTEM_UPDATE,
            CMD_LINE_SYNTAX_SYSTEM_REMOVE
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final FileQosService fileQosService;

    public FileCommand(FileQosService fileQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.fileQosService = fileQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_DOWNLOAD).longOpt(COMMAND_OPTION_MANUAL_DOWNLOAD_LONG_OPT)
                        .desc("手动下载文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_UPLOAD).longOpt(COMMAND_OPTION_MANUAL_UPLOAD_LONG_OPT)
                        .desc("手动上传文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_UPDATE).longOpt(COMMAND_OPTION_MANUAL_UPDATE_LONG_OPT)
                        .desc("手动更新文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_REMOVE).longOpt(COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT)
                        .desc("手动删除文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_DOWNLOAD).longOpt(COMMAND_OPTION_OVERRIDE_DOWNLOAD_LONG_OPT)
                        .desc("超控下载文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_UPLOAD).longOpt(COMMAND_OPTION_OVERRIDE_UPLOAD_LONG_OPT)
                        .desc("超控上传文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_UPDATE).longOpt(COMMAND_OPTION_OVERRIDE_UPDATE_LONG_OPT)
                        .desc("超控更新文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_REMOVE).longOpt(COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT)
                        .desc("超控删除文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_DOWNLOAD).longOpt(COMMAND_OPTION_SYSTEM_DOWNLOAD_LONG_OPT)
                        .desc("系统下载文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_UPLOAD).longOpt(COMMAND_OPTION_SYSTEM_UPLOAD_LONG_OPT)
                        .desc("系统上传文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_UPDATE).longOpt(COMMAND_OPTION_SYSTEM_UPDATE_LONG_OPT)
                        .desc("系统更新文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_REMOVE).longOpt(COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT)
                        .desc("系统删除文件").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON).desc("JSON字符串").hasArg().type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON_FILE).longOpt(COMMAND_OPTION_JSON_FILE_LONG_OPT).desc("JSON文件")
                        .hasArg().type(File.class).build()
        );
        return list;
    }

    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(super.cmdLineSyntax);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_MANUAL_DOWNLOAD:
                    handleManualDownload(context, cmd);
                    break;
                case COMMAND_OPTION_MANUAL_UPLOAD:
                    handleManualUpload(context, cmd);
                    break;
                case COMMAND_OPTION_MANUAL_UPDATE:
                    handleManualUpdate(context, cmd);
                    break;
                case COMMAND_OPTION_MANUAL_REMOVE:
                    handleManualRemove(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_DOWNLOAD:
                    handleOverrideDownload(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_UPLOAD:
                    handleOverrideUpload(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_UPDATE:
                    handleOverrideUpdate(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_REMOVE:
                    handleOverrideRemove(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_DOWNLOAD:
                    handleSystemDownload(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_UPLOAD:
                    handleSystemUpload(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_UPDATE:
                    handleSystemUpdate(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_REMOVE:
                    handleSystemRemove(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleManualDownload(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        DownloadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 DownloadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedDownloadInfo wrapped = JSON.parseObject(json, WrappedDownloadInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedDownloadInfo wrapped = JSON.parseObject(in, WrappedDownloadInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 下载数据。
        InputStream in = null;
        OutputStream out = null;
        try {
            LongIdKey fileInfoKey = WebInputLongIdKey.toStackBean(info.getFileInfoKey());
            FileStreamManualDownloadInfo downloadInfo = new FileStreamManualDownloadInfo(fileInfoKey);
            in = fileQosService.manualDownloadFileStream(operateUserKey, downloadInfo).getContent();
            out = Files.newOutputStream(
                    new File(info.getFilePath()).toPath(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
            );
            IOUtil.trans(in, out, 4096);
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
            if (Objects.nonNull(out)) {
                out.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件下载成功");
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleManualUpload(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        UploadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UploadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedUploadInfo wrapped = JSON.parseObject(json, WrappedUploadInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UploadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedUploadInfo wrapped = JSON.parseObject(in, WrappedUploadInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 上传文件。
        FileStreamUploadResult result;
        InputStream in = null;
        try {
            LongIdKey sessionKey = WebInputLongIdKey.toStackBean(info.getSessionKey());
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            result = fileQosService.manualUploadFileStream(
                    operateUserKey, new FileStreamManualUploadInfo(sessionKey, originName, length, in)
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件上传成功, 上传结果: " + result);
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleManualUpdate(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        UpdateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UpdateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedUpdateInfo wrapped = JSON.parseObject(json, WrappedUpdateInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UpdateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedUpdateInfo wrapped = JSON.parseObject(in, WrappedUpdateInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 更新文件。
        InputStream in = null;
        try {
            LongIdKey fileInfoKey = WebInputLongIdKey.toStackBean(info.getFileInfoKey());
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            fileQosService.manualUpdateFileStream(
                    operateUserKey, new FileStreamManualUpdateInfo(fileInfoKey, originName, length, in)
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件更新成功");
    }

    private void handleManualRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        FileManualRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 RemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedFileManualRemoveInfo wrapped = JSON.parseObject(json, WrappedFileManualRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputFileManualRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 RemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedFileManualRemoveInfo wrapped = JSON.parseObject(in, WrappedFileManualRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputFileManualRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 删除文件。
        fileQosService.manualRemoveFile(operateUserKey, info);

        // 输出结果。
        context.sendMessage("文件删除成功");
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleOverrideDownload(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        DownloadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 DownloadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedDownloadInfo wrapped = JSON.parseObject(json, WrappedDownloadInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedDownloadInfo wrapped = JSON.parseObject(in, WrappedDownloadInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 下载数据。
        InputStream in = null;
        OutputStream out = null;
        try {
            LongIdKey fileInfoKey = WebInputLongIdKey.toStackBean(info.getFileInfoKey());
            FileStreamOverrideDownloadInfo downloadInfo = new FileStreamOverrideDownloadInfo(fileInfoKey);
            in = fileQosService.overrideDownloadFileStream(operateUserKey, downloadInfo).getContent();
            out = Files.newOutputStream(
                    new File(info.getFilePath()).toPath(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
            );
            IOUtil.trans(in, out, 4096);
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
            if (Objects.nonNull(out)) {
                out.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件下载成功");
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleOverrideUpload(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        UploadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UploadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedUploadInfo wrapped = JSON.parseObject(json, WrappedUploadInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UploadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedUploadInfo wrapped = JSON.parseObject(in, WrappedUploadInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 上传文件。
        FileStreamUploadResult result;
        InputStream in = null;
        try {
            LongIdKey sessionKey = WebInputLongIdKey.toStackBean(info.getSessionKey());
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            result = fileQosService.overrideUploadFileStream(
                    operateUserKey, new FileStreamOverrideUploadInfo(sessionKey, originName, length, in)
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件上传成功, 上传结果: " + result);
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleOverrideUpdate(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        UpdateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UpdateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedUpdateInfo wrapped = JSON.parseObject(json, WrappedUpdateInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UpdateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedUpdateInfo wrapped = JSON.parseObject(in, WrappedUpdateInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 更新文件。
        InputStream in = null;
        try {
            LongIdKey fileInfoKey = WebInputLongIdKey.toStackBean(info.getFileInfoKey());
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            fileQosService.overrideUpdateFileStream(
                    operateUserKey, new FileStreamOverrideUpdateInfo(fileInfoKey, originName, length, in)
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件更新成功");
    }

    private void handleOverrideRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        FileOverrideRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 RemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedFileOverrideRemoveInfo wrapped = JSON.parseObject(json, WrappedFileOverrideRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputFileOverrideRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 RemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedFileOverrideRemoveInfo wrapped = JSON.parseObject(in, WrappedFileOverrideRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputFileOverrideRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 删除文件。
        fileQosService.overrideRemoveFile(operateUserKey, info);

        // 输出结果。
        context.sendMessage("文件删除成功");
    }

    private void handleSystemDownload(Context context, CommandLine cmd) throws Exception {
        DownloadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 DownloadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, DownloadInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, DownloadInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 下载数据。
        InputStream in = null;
        OutputStream out = null;
        try {
            LongIdKey fileInfoKey = WebInputLongIdKey.toStackBean(info.getFileInfoKey());
            FileStreamSystemDownloadInfo downloadInfo = new FileStreamSystemDownloadInfo(fileInfoKey);
            in = fileQosService.systemDownloadFileStream(downloadInfo).getContent();
            out = Files.newOutputStream(
                    new File(info.getFilePath()).toPath(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING
            );
            IOUtil.trans(in, out, 4096);
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
            if (Objects.nonNull(out)) {
                out.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件下载成功");
    }

    private void handleSystemUpload(Context context, CommandLine cmd) throws Exception {
        UploadInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UploadInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, UploadInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UploadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UploadInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 上传文件。
        FileStreamUploadResult result;
        InputStream in = null;
        try {
            LongIdKey sessionKey = WebInputLongIdKey.toStackBean(info.getSessionKey());
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            result = fileQosService.systemUploadFileStream(
                    new FileStreamSystemUploadInfo(sessionKey, originName, length, in)
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件上传成功, 上传结果: " + result);
    }

    private void handleSystemUpdate(Context context, CommandLine cmd) throws Exception {
        UpdateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 UpdateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, UpdateInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 UpdateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, UpdateInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 更新文件。
        InputStream in = null;
        try {
            LongIdKey fileInfoKey = WebInputLongIdKey.toStackBean(info.getFileInfoKey());
            File file = new File(info.getFilePath());
            String originName = file.getName();
            long length = file.length();
            in = Files.newInputStream(file.toPath());
            fileQosService.systemUpdateFileStream(new FileStreamSystemUpdateInfo(fileInfoKey, originName, length, in));
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("文件更新成功");
    }

    private void handleSystemRemove(Context context, CommandLine cmd) throws Exception {
        FileSystemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 RemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputFileSystemRemoveInfo.toStackBean(JSON.parseObject(json, WebInputFileSystemRemoveInfo.class));
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 RemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputFileSystemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputFileSystemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 删除文件。
        fileQosService.systemRemoveFile(info);

        // 输出结果。
        context.sendMessage("文件删除成功");
    }

    public static class WrappedDownloadInfo implements Dto {

        private static final long serialVersionUID = 6444517717921721996L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private DownloadInfo info;

        public WrappedDownloadInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public DownloadInfo getInfo() {
            return info;
        }

        public void setInfo(DownloadInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedDownloadInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedUploadInfo implements Dto {

        private static final long serialVersionUID = 3717829423094084646L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private UploadInfo info;

        public WrappedUploadInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public UploadInfo getInfo() {
            return info;
        }

        public void setInfo(UploadInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedUploadInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedUpdateInfo implements Dto {

        private static final long serialVersionUID = -5083749614453833828L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private UpdateInfo info;

        public WrappedUpdateInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public UpdateInfo getInfo() {
            return info;
        }

        public void setInfo(UpdateInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedUpdateInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class DownloadInfo implements Dto {

        private static final long serialVersionUID = -7153103241367981903L;

        @JSONField(name = "file_info_key")
        @NotNull
        @Valid
        private WebInputLongIdKey fileInfoKey;

        @JSONField(name = "file_path")
        @NotNull
        @NotEmpty
        private String filePath;

        public DownloadInfo() {
        }

        public WebInputLongIdKey getFileInfoKey() {
            return fileInfoKey;
        }

        public void setFileInfoKey(WebInputLongIdKey fileInfoKey) {
            this.fileInfoKey = fileInfoKey;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "DownloadInfo{" +
                    "fileInfoKey=" + fileInfoKey +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }

    public static class UploadInfo implements Dto {

        private static final long serialVersionUID = -14261594301475416L;

        @JSONField(name = "session_key")
        @NotNull
        @Valid
        private WebInputLongIdKey sessionKey;

        @JSONField(name = "file_path")
        @NotNull
        @NotEmpty
        private String filePath;

        public UploadInfo() {
        }

        public WebInputLongIdKey getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(WebInputLongIdKey sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "UploadInfo{" +
                    "sessionKey=" + sessionKey +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }

    public static class UpdateInfo implements Dto {

        private static final long serialVersionUID = -6924287828526698736L;

        @JSONField(name = "file_info_key")
        @NotNull
        @Valid
        private WebInputLongIdKey fileInfoKey;

        @JSONField(name = "file_path")
        @NotNull
        @NotEmpty
        private String filePath;

        public UpdateInfo() {
        }

        public WebInputLongIdKey getFileInfoKey() {
            return fileInfoKey;
        }

        public void setFileInfoKey(WebInputLongIdKey fileInfoKey) {
            this.fileInfoKey = fileInfoKey;
        }

        public String getFilePath() {
            return filePath;
        }

        public void setFilePath(String filePath) {
            this.filePath = filePath;
        }

        @Override
        public String toString() {
            return "UpdateInfo{" +
                    "fileInfoKey=" + fileInfoKey +
                    ", filePath='" + filePath + '\'' +
                    '}';
        }
    }

    public static class WrappedFileManualRemoveInfo implements Dto {

        private static final long serialVersionUID = -2623738651862788370L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputFileManualRemoveInfo info;

        public WrappedFileManualRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputFileManualRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputFileManualRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedFileManualRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedFileOverrideRemoveInfo implements Dto {

        private static final long serialVersionUID = 8460505546066005372L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputFileOverrideRemoveInfo info;

        public WrappedFileOverrideRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputFileOverrideRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputFileOverrideRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedFileOverrideRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
