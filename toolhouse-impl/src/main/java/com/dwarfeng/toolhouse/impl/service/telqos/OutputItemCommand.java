package com.dwarfeng.toolhouse.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.service.OutputItemQosService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class OutputItemCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_UPSERT = "manups";
    private static final String COMMAND_OPTION_MANUAL_UPSERT_LONG_OPT = "manual-upsert";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_REMOVE = "manrm";
    private static final String COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT = "manual-remove";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_UPSERT = "ovrups";
    private static final String COMMAND_OPTION_OVERRIDE_UPSERT_LONG_OPT = "override-upsert";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_REMOVE = "ovrrm";
    private static final String COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT = "override-remove";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_INSPECT = "sysins";
    private static final String COMMAND_OPTION_SYSTEM_INSPECT_LONG_OPT = "system-inspect";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_UPSERT = "sysups";
    private static final String COMMAND_OPTION_SYSTEM_UPSERT_LONG_OPT = "system-upsert";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_REMOVE = "sysrm";
    private static final String COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT = "system-remove";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_MANUAL_UPSERT,
            COMMAND_OPTION_MANUAL_REMOVE,
            COMMAND_OPTION_OVERRIDE_UPSERT,
            COMMAND_OPTION_OVERRIDE_REMOVE,
            COMMAND_OPTION_SYSTEM_INSPECT,
            COMMAND_OPTION_SYSTEM_UPSERT,
            COMMAND_OPTION_SYSTEM_REMOVE
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String IDENTITY = "outputitem";
    private static final String DESCRIPTION = "输出项操作";

    private static final String CMD_LINE_SYNTAX_MANUAL_UPSERT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_UPSERT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_MANUAL_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_UPSERT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_UPSERT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_INSPECT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_INSPECT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_UPSERT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_UPSERT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_MANUAL_UPSERT,
            CMD_LINE_SYNTAX_MANUAL_REMOVE,
            CMD_LINE_SYNTAX_OVERRIDE_UPSERT,
            CMD_LINE_SYNTAX_OVERRIDE_REMOVE,
            CMD_LINE_SYNTAX_SYSTEM_INSPECT,
            CMD_LINE_SYNTAX_SYSTEM_UPSERT,
            CMD_LINE_SYNTAX_SYSTEM_REMOVE
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final OutputItemQosService outputItemQosService;

    public OutputItemCommand(OutputItemQosService outputItemQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.outputItemQosService = outputItemQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_UPSERT).longOpt(COMMAND_OPTION_MANUAL_UPSERT_LONG_OPT)
                        .desc("手动创建/更新输出项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_REMOVE).longOpt(COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT)
                        .desc("手动删除输出项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_UPSERT).longOpt(COMMAND_OPTION_OVERRIDE_UPSERT_LONG_OPT)
                        .desc("超控创建/更新输出项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_REMOVE).longOpt(COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT)
                        .desc("超控删除输出项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_INSPECT).longOpt(COMMAND_OPTION_SYSTEM_INSPECT_LONG_OPT)
                        .desc("系统查询输出项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_UPSERT).longOpt(COMMAND_OPTION_SYSTEM_UPSERT_LONG_OPT)
                        .desc("系统创建/更新输出项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_REMOVE).longOpt(COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT)
                        .desc("系统删除输出项").build()
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

    @SuppressWarnings("DuplicatedCode")
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
                case COMMAND_OPTION_MANUAL_UPSERT:
                    handleManualUpsert(context, cmd);
                    break;
                case COMMAND_OPTION_MANUAL_REMOVE:
                    handleManualRemove(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_UPSERT:
                    handleOverrideUpsert(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_REMOVE:
                    handleOverrideRemove(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_INSPECT:
                    handleSystemInspect(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_UPSERT:
                    handleSystemUpsert(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_REMOVE:
                    handleSystemRemove(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleManualUpsert(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        OutputItemManualUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemManualUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedOutputItemManualUpsertInfo wrapped = JSON.parseObject(json, WrappedOutputItemManualUpsertInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputOutputItemManualUpsertInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemManualUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedOutputItemManualUpsertInfo wrapped = JSON.parseObject(in, WrappedOutputItemManualUpsertInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputOutputItemManualUpsertInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        outputItemQosService.manualUpsert(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输出项创建/更新成功");
    }

    private void handleManualRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        OutputItemManualRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemManualRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedOutputItemManualRemoveInfo wrapped = JSON.parseObject(json, WrappedOutputItemManualRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputOutputItemManualRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemManualRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedOutputItemManualRemoveInfo wrapped = JSON.parseObject(in, WrappedOutputItemManualRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputOutputItemManualRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        outputItemQosService.manualRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输出项删除成功");
    }

    private void handleOverrideUpsert(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        OutputItemOverrideUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemOverrideUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedOutputItemOverrideUpsertInfo wrapped = JSON.parseObject(json, WrappedOutputItemOverrideUpsertInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputOutputItemOverrideUpsertInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemOverrideUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedOutputItemOverrideUpsertInfo wrapped = JSON.parseObject(in, WrappedOutputItemOverrideUpsertInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputOutputItemOverrideUpsertInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        outputItemQosService.overrideUpsert(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输出项创建/更新成功");
    }

    private void handleOverrideRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        OutputItemOverrideRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemOverrideRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedOutputItemOverrideRemoveInfo wrapped = JSON.parseObject(json, WrappedOutputItemOverrideRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputOutputItemOverrideRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemOverrideRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedOutputItemOverrideRemoveInfo wrapped = JSON.parseObject(in, WrappedOutputItemOverrideRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputOutputItemOverrideRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        outputItemQosService.overrideRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输出项删除成功");
    }

    private void handleSystemInspect(Context context, CommandLine cmd) throws Exception {
        OutputItemSystemInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemSystemInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputOutputItemSystemInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputOutputItemSystemInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemSystemInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputOutputItemSystemInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputOutputItemSystemInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        OutputItemInspectResult result = outputItemQosService.systemInspect(info);

        // 输出结果。
        context.sendMessage("输出项查询成功");
        context.sendMessage("result: " + result);
    }

    private void handleSystemUpsert(Context context, CommandLine cmd) throws Exception {
        OutputItemSystemUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemSystemUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputOutputItemSystemUpsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputOutputItemSystemUpsertInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemSystemUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputOutputItemSystemUpsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputOutputItemSystemUpsertInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        outputItemQosService.systemUpsert(info);

        // 输出结果。
        context.sendMessage("输出项创建/更新成功");
    }

    private void handleSystemRemove(Context context, CommandLine cmd) throws Exception {
        OutputItemSystemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 OutputItemSystemRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputOutputItemSystemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputOutputItemSystemRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 OutputItemSystemRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputOutputItemSystemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputOutputItemSystemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        outputItemQosService.systemRemove(info);

        // 输出结果。
        context.sendMessage("输出项删除成功");
    }

    public static class WrappedOutputItemManualUpsertInfo implements Dto {

        private static final long serialVersionUID = 4822379410080797738L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputOutputItemManualUpsertInfo info;

        public WrappedOutputItemManualUpsertInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputOutputItemManualUpsertInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputOutputItemManualUpsertInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedOutputItemManualUpsertInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedOutputItemManualRemoveInfo implements Dto {

        private static final long serialVersionUID = 6895525834484834006L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputOutputItemManualRemoveInfo info;

        public WrappedOutputItemManualRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputOutputItemManualRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputOutputItemManualRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedOutputItemManualRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedOutputItemOverrideUpsertInfo implements Dto {

        private static final long serialVersionUID = 273104157592531774L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputOutputItemOverrideUpsertInfo info;

        public WrappedOutputItemOverrideUpsertInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputOutputItemOverrideUpsertInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputOutputItemOverrideUpsertInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedOutputItemOverrideUpsertInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedOutputItemOverrideRemoveInfo implements Dto {

        private static final long serialVersionUID = -2924146786077434438L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputOutputItemOverrideRemoveInfo info;

        public WrappedOutputItemOverrideRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputOutputItemOverrideRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputOutputItemOverrideRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedOutputItemOverrideRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
