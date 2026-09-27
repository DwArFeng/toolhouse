package com.dwarfeng.toolhouse.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputVisualizerManualCallInfo;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputVisualizerOverrideCallInfo;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputVisualizerSystemCallInfo;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.service.VisualizeClientQosService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@TelqosCommand
public class VisualClientCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_VISUALIZER_CALL = "manvc";
    private static final String COMMAND_OPTION_MANUAL_VISUALIZER_CALL_LONG_OPT = "manual-visualizer-call";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL = "manvsc";
    private static final String COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL_LONG_OPT = "manual-visualizer-stream-call";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL = "ovrvc";
    private static final String COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL_LONG_OPT = "override-visualizer-call";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL = "ovrvsc";
    private static final String COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL_LONG_OPT
            = "override-visualizer-stream-call";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_VISUALIZER_CALL = "sysvc";
    private static final String COMMAND_OPTION_SYSTEM_VISUALIZER_CALL_LONG_OPT = "system-visualizer-call";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL = "sysvsc";
    private static final String COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL_LONG_OPT = "system-visualizer-stream-call";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_MANUAL_VISUALIZER_CALL,
            COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL,
            COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL,
            COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL,
            COMMAND_OPTION_SYSTEM_VISUALIZER_CALL,
            COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL,
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String IDENTITY = "vcli";
    private static final String DESCRIPTION = "可视化客户端操作";

    private static final String CMD_LINE_SYNTAX_MANUAL_VISUALIZER_CALL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_VISUALIZER_CALL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_MANUAL_VISUALIZER_STREAM_CALL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_VISUALIZER_CALL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_VISUALIZER_STREAM_CALL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_VISUALIZER_CALL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_VISUALIZER_CALL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_VISUALIZER_STREAM_CALL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_MANUAL_VISUALIZER_CALL,
            CMD_LINE_SYNTAX_MANUAL_VISUALIZER_STREAM_CALL,
            CMD_LINE_SYNTAX_OVERRIDE_VISUALIZER_CALL,
            CMD_LINE_SYNTAX_OVERRIDE_VISUALIZER_STREAM_CALL,
            CMD_LINE_SYNTAX_SYSTEM_VISUALIZER_CALL,
            CMD_LINE_SYNTAX_SYSTEM_VISUALIZER_STREAM_CALL,
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final VisualizeClientQosService visualizeClientQosService;

    public VisualClientCommand(VisualizeClientQosService visualizeClientQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.visualizeClientQosService = visualizeClientQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_VISUALIZER_CALL)
                        .longOpt(COMMAND_OPTION_MANUAL_VISUALIZER_CALL_LONG_OPT)
                        .desc("手动调用可视化器").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL)
                        .longOpt(COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL_LONG_OPT)
                        .desc("手动流式调用可视化器").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL)
                        .longOpt(COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL_LONG_OPT)
                        .desc("超控调用可视化器").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL)
                        .longOpt(COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL_LONG_OPT)
                        .desc("超控流式调用可视化器").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_VISUALIZER_CALL)
                        .longOpt(COMMAND_OPTION_SYSTEM_VISUALIZER_CALL_LONG_OPT)
                        .desc("系统调用可视化器").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL).longOpt(COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL_LONG_OPT)
                        .desc("系统流式调用可视化器").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON).desc("JSON 字符串").hasArg().type(String.class).build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_JSON_FILE).longOpt(COMMAND_OPTION_JSON_FILE_LONG_OPT).desc("JSON 文件")
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
                case COMMAND_OPTION_MANUAL_VISUALIZER_CALL:
                    handleManualVisualizerCall(context, cmd);
                    break;
                case COMMAND_OPTION_MANUAL_VISUALIZER_STREAM_CALL:
                    handleManualVisualizerStreamCall(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_VISUALIZER_CALL:
                    handleOverrideVisualizerCall(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_VISUALIZER_STREAM_CALL:
                    handleOverrideVisualizerStreamCall(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_VISUALIZER_CALL:
                    handleSystemVisualizerCall(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_VISUALIZER_STREAM_CALL:
                    handleSystemVisualizerStreamCall(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleManualVisualizerCall(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        VisualizerManualCallInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VisualizerManualCallInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVisualizerManualCallInfo wrapped = JSON.parseObject(json, WrappedVisualizerManualCallInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputVisualizerManualCallInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VisualizerManualCallInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVisualizerManualCallInfo wrapped = JSON.parseObject(in, WrappedVisualizerManualCallInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputVisualizerManualCallInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 可视化器调用。
        VisualizerCallResult result = visualizeClientQosService.manualVisualizerCall(operateUserKey, info);

        // 输出结果。
        context.sendMessage("可视化器调用成功: ");
        context.sendMessage("  响应文本: " + result.getResponseText());
        context.sendMessage("  响应文本描述: " + result.getResponseTextDescription());
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleManualVisualizerStreamCall(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        StreamCallInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 StreamCallInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVisualizerManualStreamCallInfo wrapped = JSON.parseObject(
                    json, WrappedVisualizerManualStreamCallInfo.class
            );
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVisualizerManualStreamCallInfo wrapped = JSON.parseObject(
                        in, WrappedVisualizerManualStreamCallInfo.class
                );
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 定义结果。
        VisualizerStreamCallResult result;

        // StreamCallInfo.requestStreamFilePath 转化为 InputStream。
        InputStream in = null;
        try {
            LongIdKey sessionKey = WebInputLongIdKey.toStackBean(info.getSessionKey());
            String visualizerStringId = info.getVisualizerStringId();
            String functionName = info.getFunctionName();
            String functionDescription = info.getFunctionDescription();
            String requestText = info.getRequestText();
            String requestTextDescription = info.getRequestTextDescription();
            if (Objects.nonNull(info.getRequestStreamFilePath())) {
                File file = new File(info.getRequestStreamFilePath());
                in = Files.newInputStream(file.toPath());
            }
            String requestStreamDescription = info.getRequestStreamDescription();
            result = visualizeClientQosService.manualVisualizerStreamCall(
                    operateUserKey,
                    new VisualizerManualStreamCallInfo(
                            sessionKey, visualizerStringId, functionName, functionDescription, requestText,
                            requestTextDescription, in, requestStreamDescription
                    )
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("可视化器流式调用成功: ");
        context.sendMessage("  响应文本: " + result.getResponseText());
        context.sendMessage("  响应文本描述: " + result.getResponseTextDescription());
        context.sendMessage("  响应流: " + result.getResponseStream());
        context.sendMessage("  响应流描述: " + result.getResponseStreamDescription());

        // 如果响应流不为 null，则关闭响应流。
        if (Objects.nonNull(result.getResponseStream())) {
            result.getResponseStream().close();
        }
    }

    private void handleOverrideVisualizerCall(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        VisualizerOverrideCallInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VisualizerOverrideCallInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVisualizerOverrideCallInfo wrapped = JSON.parseObject(json, WrappedVisualizerOverrideCallInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputVisualizerOverrideCallInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VisualizerOverrideCallInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVisualizerOverrideCallInfo wrapped = JSON.parseObject(in, WrappedVisualizerOverrideCallInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputVisualizerOverrideCallInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 可视化器调用。
        VisualizerCallResult result = visualizeClientQosService.overrideVisualizerCall(operateUserKey, info);

        // 输出结果。
        context.sendMessage("可视化器调用成功: ");
        context.sendMessage("  响应文本: " + result.getResponseText());
        context.sendMessage("  响应文本描述: " + result.getResponseTextDescription());
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleOverrideVisualizerStreamCall(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        StreamCallInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 StreamCallInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVisualizerOverrideStreamCallInfo wrapped = JSON.parseObject(
                    json, WrappedVisualizerOverrideStreamCallInfo.class
            );
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = wrapped.getInfo();
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVisualizerOverrideStreamCallInfo wrapped = JSON.parseObject(
                        in, WrappedVisualizerOverrideStreamCallInfo.class
                );
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = wrapped.getInfo();
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 定义结果。
        VisualizerStreamCallResult result;

        // StreamCallInfo.requestStreamFilePath 转化为 InputStream。
        InputStream in = null;
        try {
            LongIdKey sessionKey = WebInputLongIdKey.toStackBean(info.getSessionKey());
            String visualizerStringId = info.getVisualizerStringId();
            String functionName = info.getFunctionName();
            String functionDescription = info.getFunctionDescription();
            String requestText = info.getRequestText();
            String requestTextDescription = info.getRequestTextDescription();
            if (Objects.nonNull(info.getRequestStreamFilePath())) {
                File file = new File(info.getRequestStreamFilePath());
                in = Files.newInputStream(file.toPath());
            }
            String requestStreamDescription = info.getRequestStreamDescription();
            result = visualizeClientQosService.overrideVisualizerStreamCall(
                    operateUserKey, new VisualizerOverrideStreamCallInfo(
                            sessionKey, visualizerStringId, functionName, functionDescription, requestText,
                            requestTextDescription, in, requestStreamDescription
                    )
            );
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("可视化器流式调用成功: ");
        context.sendMessage("  响应文本: " + result.getResponseText());
        context.sendMessage("  响应文本描述: " + result.getResponseTextDescription());
        context.sendMessage("  响应流: " + result.getResponseStream());
        context.sendMessage("  响应流描述: " + result.getResponseStreamDescription());

        // 如果响应流不为 null，则关闭响应流。
        if (Objects.nonNull(result.getResponseStream())) {
            result.getResponseStream().close();
        }
    }

    private void handleSystemVisualizerCall(Context context, CommandLine cmd) throws Exception {
        VisualizerSystemCallInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VisualizerSystemCallInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputVisualizerSystemCallInfo.toStackBean(
                    JSON.parseObject(json, WebInputVisualizerSystemCallInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VisualizerSystemCallInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputVisualizerSystemCallInfo.toStackBean(
                        JSON.parseObject(in, WebInputVisualizerSystemCallInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 可视化器调用。
        VisualizerCallResult result = visualizeClientQosService.systemVisualizerCall(info);

        // 输出结果。
        context.sendMessage("可视化器调用成功: ");
        context.sendMessage("  响应文本: " + result.getResponseText());
        context.sendMessage("  响应文本描述: " + result.getResponseTextDescription());
    }

    @SuppressWarnings("DuplicatedCode")
    private void handleSystemVisualizerStreamCall(Context context, CommandLine cmd) throws Exception {
        StreamCallInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 StreamCallInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = JSON.parseObject(json, StreamCallInfo.class);
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 DownloadInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = JSON.parseObject(in, StreamCallInfo.class);
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 定义结果。
        VisualizerStreamCallResult result;

        // StreamCallInfo.requestStreamFilePath 转化为 InputStream。
        InputStream in = null;
        try {
            LongIdKey sessionKey = WebInputLongIdKey.toStackBean(info.getSessionKey());
            String visualizerStringId = info.getVisualizerStringId();
            String functionName = info.getFunctionName();
            String functionDescription = info.getFunctionDescription();
            String requestText = info.getRequestText();
            String requestTextDescription = info.getRequestTextDescription();
            if (Objects.nonNull(info.getRequestStreamFilePath())) {
                File file = new File(info.getRequestStreamFilePath());
                in = Files.newInputStream(file.toPath());
            }
            String requestStreamDescription = info.getRequestStreamDescription();
            result = visualizeClientQosService.systemVisualizerStreamCall(new VisualizerSystemStreamCallInfo(
                    sessionKey, visualizerStringId, functionName, functionDescription, requestText,
                    requestTextDescription, in, requestStreamDescription
            ));
        } finally {
            if (Objects.nonNull(in)) {
                in.close();
            }
        }

        // 输出结果。
        context.sendMessage("可视化器流式调用成功: ");
        context.sendMessage("  响应文本: " + result.getResponseText());
        context.sendMessage("  响应文本描述: " + result.getResponseTextDescription());
        context.sendMessage("  响应流: " + result.getResponseStream());
        context.sendMessage("  响应流描述: " + result.getResponseStreamDescription());

        // 如果响应流不为 null，则关闭响应流。
        if (Objects.nonNull(result.getResponseStream())) {
            result.getResponseStream().close();
        }
    }

    public static class WrappedVisualizerManualCallInfo implements Dto {

        private static final long serialVersionUID = -6185505889349710665L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputVisualizerManualCallInfo info;

        public WrappedVisualizerManualCallInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputVisualizerManualCallInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputVisualizerManualCallInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVisualizerManualCallInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedVisualizerManualStreamCallInfo implements Dto {

        private static final long serialVersionUID = 9209319501678514728L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private StreamCallInfo info;

        public WrappedVisualizerManualStreamCallInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public StreamCallInfo getInfo() {
            return info;
        }

        public void setInfo(StreamCallInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVisualizerManualStreamCallInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedVisualizerOverrideCallInfo implements Dto {

        private static final long serialVersionUID = 3501377653822860871L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputVisualizerOverrideCallInfo info;

        public WrappedVisualizerOverrideCallInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputVisualizerOverrideCallInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputVisualizerOverrideCallInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVisualizerOverrideCallInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedVisualizerOverrideStreamCallInfo implements Dto {

        private static final long serialVersionUID = -6736319747218199345L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private StreamCallInfo info;

        public WrappedVisualizerOverrideStreamCallInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public StreamCallInfo getInfo() {
            return info;
        }

        public void setInfo(StreamCallInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVisualizerOverrideStreamCallInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class StreamCallInfo implements Dto {

        private static final long serialVersionUID = 9128711976088466347L;

        @JSONField(name = "session_key")
        @NotNull
        @Valid
        private WebInputLongIdKey sessionKey;

        @JSONField(name = "visualizer_string_id")
        @NotNull
        @Length(max = Constraints.LENGTH_STRING_ID)
        private String visualizerStringId;

        @JSONField(name = "function_name")
        @NotNull
        private String functionName;

        @JSONField(name = "function_description")
        private String functionDescription;

        @JSONField(name = "request_text")
        private String requestText;

        @JSONField(name = "request_text_description")
        private String requestTextDescription;

        @JSONField(name = "request_stream_file_path")
        private String requestStreamFilePath;

        @JSONField(name = "request_stream_description")
        private String requestStreamDescription;

        public StreamCallInfo() {
        }

        public WebInputLongIdKey getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(WebInputLongIdKey sessionKey) {
            this.sessionKey = sessionKey;
        }

        public String getVisualizerStringId() {
            return visualizerStringId;
        }

        public void setVisualizerStringId(String visualizerStringId) {
            this.visualizerStringId = visualizerStringId;
        }

        public String getFunctionName() {
            return functionName;
        }

        public void setFunctionName(String functionName) {
            this.functionName = functionName;
        }

        public String getFunctionDescription() {
            return functionDescription;
        }

        public void setFunctionDescription(String functionDescription) {
            this.functionDescription = functionDescription;
        }

        public String getRequestText() {
            return requestText;
        }

        public void setRequestText(String requestText) {
            this.requestText = requestText;
        }

        public String getRequestTextDescription() {
            return requestTextDescription;
        }

        public void setRequestTextDescription(String requestTextDescription) {
            this.requestTextDescription = requestTextDescription;
        }

        public String getRequestStreamFilePath() {
            return requestStreamFilePath;
        }

        public void setRequestStreamFilePath(String requestStreamFilePath) {
            this.requestStreamFilePath = requestStreamFilePath;
        }

        public String getRequestStreamDescription() {
            return requestStreamDescription;
        }

        public void setRequestStreamDescription(String requestStreamDescription) {
            this.requestStreamDescription = requestStreamDescription;
        }

        @Override
        public String toString() {
            return "StreamCallInfo{" +
                    "sessionKey=" + sessionKey +
                    ", visualizerStringId='" + visualizerStringId + '\'' +
                    ", functionName='" + functionName + '\'' +
                    ", functionDescription='" + functionDescription + '\'' +
                    ", requestText='" + requestText + '\'' +
                    ", requestTextDescription='" + requestTextDescription + '\'' +
                    ", requestStreamFilePath='" + requestStreamFilePath + '\'' +
                    ", requestStreamDescription='" + requestStreamDescription + '\'' +
                    '}';
        }
    }
}

