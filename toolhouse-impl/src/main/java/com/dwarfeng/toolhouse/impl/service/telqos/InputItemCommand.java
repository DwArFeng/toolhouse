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
import com.dwarfeng.toolhouse.stack.service.InputItemQosService;
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
public class InputItemCommand extends CliCommand {

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
    private static final String IDENTITY = "inputitem";
    private static final String DESCRIPTION = "输入项操作";

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

    private final InputItemQosService inputItemQosService;

    public InputItemCommand(InputItemQosService inputItemQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.inputItemQosService = inputItemQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_UPSERT).longOpt(COMMAND_OPTION_MANUAL_UPSERT_LONG_OPT)
                        .desc("手动创建/更新输入项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_REMOVE).longOpt(COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT)
                        .desc("手动删除输入项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_UPSERT).longOpt(COMMAND_OPTION_OVERRIDE_UPSERT_LONG_OPT)
                        .desc("超控创建/更新输入项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_REMOVE).longOpt(COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT)
                        .desc("超控删除输入项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_INSPECT).longOpt(COMMAND_OPTION_SYSTEM_INSPECT_LONG_OPT)
                        .desc("系统查询输入项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_UPSERT).longOpt(COMMAND_OPTION_SYSTEM_UPSERT_LONG_OPT)
                        .desc("系统创建/更新输入项").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_REMOVE).longOpt(COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT)
                        .desc("系统删除输入项").build()
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
        InputItemManualUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemManualUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedInputItemManualUpsertInfo wrapped = JSON.parseObject(json, WrappedInputItemManualUpsertInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputInputItemManualUpsertInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemManualUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedInputItemManualUpsertInfo wrapped = JSON.parseObject(in, WrappedInputItemManualUpsertInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputInputItemManualUpsertInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        inputItemQosService.manualUpsert(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输入项创建/更新成功");
    }

    private void handleManualRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        InputItemManualRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemManualRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedInputItemManualRemoveInfo wrapped = JSON.parseObject(json, WrappedInputItemManualRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputInputItemManualRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemManualRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedInputItemManualRemoveInfo wrapped = JSON.parseObject(in, WrappedInputItemManualRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputInputItemManualRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        inputItemQosService.manualRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输入项删除成功");
    }

    private void handleOverrideUpsert(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        InputItemOverrideUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemOverrideUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedInputItemOverrideUpsertInfo wrapped = JSON.parseObject(json, WrappedInputItemOverrideUpsertInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputInputItemOverrideUpsertInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemOverrideUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedInputItemOverrideUpsertInfo wrapped = JSON.parseObject(in, WrappedInputItemOverrideUpsertInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputInputItemOverrideUpsertInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        inputItemQosService.overrideUpsert(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输入项创建/更新成功");
    }

    private void handleOverrideRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        InputItemOverrideRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemOverrideRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedInputItemOverrideRemoveInfo wrapped = JSON.parseObject(json, WrappedInputItemOverrideRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputInputItemOverrideRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemOverrideRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedInputItemOverrideRemoveInfo wrapped = JSON.parseObject(in, WrappedInputItemOverrideRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputInputItemOverrideRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        inputItemQosService.overrideRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("输入项删除成功");
    }

    private void handleSystemInspect(Context context, CommandLine cmd) throws Exception {
        InputItemSystemInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemSystemInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputInputItemSystemInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputInputItemSystemInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemSystemInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputInputItemSystemInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputInputItemSystemInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        InputItemInspectResult result = inputItemQosService.systemInspect(info);

        // 输出结果。
        context.sendMessage("输入项查询成功");
        context.sendMessage("result: " + result);
    }

    private void handleSystemUpsert(Context context, CommandLine cmd) throws Exception {
        InputItemSystemUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemSystemUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputInputItemSystemUpsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputInputItemSystemUpsertInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemSystemUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputInputItemSystemUpsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputInputItemSystemUpsertInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        inputItemQosService.systemUpsert(info);

        // 输出结果。
        context.sendMessage("输入项创建/更新成功");
    }

    private void handleSystemRemove(Context context, CommandLine cmd) throws Exception {
        InputItemSystemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 InputItemSystemRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputInputItemSystemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputInputItemSystemRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 InputItemSystemRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputInputItemSystemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputInputItemSystemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        inputItemQosService.systemRemove(info);

        // 输出结果。
        context.sendMessage("输入项删除成功");
    }

    public static class WrappedInputItemManualUpsertInfo implements Dto {

        private static final long serialVersionUID = 1128175222313987297L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputInputItemManualUpsertInfo info;

        public WrappedInputItemManualUpsertInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputInputItemManualUpsertInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputInputItemManualUpsertInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedInputItemManualUpsertInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedInputItemManualRemoveInfo implements Dto {

        private static final long serialVersionUID = -7560348815571825257L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputInputItemManualRemoveInfo info;

        public WrappedInputItemManualRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputInputItemManualRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputInputItemManualRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedInputItemManualRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedInputItemOverrideUpsertInfo implements Dto {

        private static final long serialVersionUID = 6476928003353667190L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputInputItemOverrideUpsertInfo info;

        public WrappedInputItemOverrideUpsertInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputInputItemOverrideUpsertInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputInputItemOverrideUpsertInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedInputItemOverrideUpsertInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedInputItemOverrideRemoveInfo implements Dto {

        private static final long serialVersionUID = -4433529074268627463L;
        
        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputInputItemOverrideRemoveInfo info;

        public WrappedInputItemOverrideRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputInputItemOverrideRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputInputItemOverrideRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedInputItemOverrideRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
