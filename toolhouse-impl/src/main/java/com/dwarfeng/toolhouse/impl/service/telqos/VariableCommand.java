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
import com.dwarfeng.toolhouse.stack.service.VariableQosService;
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
public class VariableCommand extends CliCommand {

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

    private static final String IDENTITY = "variable";
    private static final String DESCRIPTION = "变量操作";

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

    private final VariableQosService variableQosService;

    public VariableCommand(VariableQosService variableQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.variableQosService = variableQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_UPSERT).longOpt(COMMAND_OPTION_MANUAL_UPSERT_LONG_OPT)
                        .desc("手动创建/更新变量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_REMOVE).longOpt(COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT)
                        .desc("手动删除变量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_UPSERT).longOpt(COMMAND_OPTION_OVERRIDE_UPSERT_LONG_OPT)
                        .desc("超控创建/更新变量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_REMOVE).longOpt(COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT)
                        .desc("超控删除变量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_INSPECT).longOpt(COMMAND_OPTION_SYSTEM_INSPECT_LONG_OPT)
                        .desc("系统查询变量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_UPSERT).longOpt(COMMAND_OPTION_SYSTEM_UPSERT_LONG_OPT)
                        .desc("系统创建/更新变量").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_REMOVE).longOpt(COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT)
                        .desc("系统删除变量").build()
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
        VariableManualUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableManualUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVariableManualUpsertInfo wrapped = JSON.parseObject(json, WrappedVariableManualUpsertInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputVariableManualUpsertInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableManualUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVariableManualUpsertInfo wrapped = JSON.parseObject(in, WrappedVariableManualUpsertInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputVariableManualUpsertInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        variableQosService.manualUpsert(operateUserKey, info);

        // 输出结果。
        context.sendMessage("变量创建/更新成功");
    }

    private void handleManualRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        VariableManualRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableManualRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVariableManualRemoveInfo wrapped = JSON.parseObject(json, WrappedVariableManualRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputVariableManualRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableManualRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVariableManualRemoveInfo wrapped = JSON.parseObject(in, WrappedVariableManualRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputVariableManualRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        variableQosService.manualRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("变量删除成功");
    }

    private void handleOverrideUpsert(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        VariableOverrideUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableOverrideUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVariableOverrideUpsertInfo wrapped = JSON.parseObject(json, WrappedVariableOverrideUpsertInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputVariableOverrideUpsertInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableOverrideUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVariableOverrideUpsertInfo wrapped = JSON.parseObject(in, WrappedVariableOverrideUpsertInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputVariableOverrideUpsertInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        variableQosService.overrideUpsert(operateUserKey, info);

        // 输出结果。
        context.sendMessage("变量创建/更新成功");
    }

    private void handleOverrideRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        VariableOverrideRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableOverrideRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedVariableOverrideRemoveInfo wrapped = JSON.parseObject(json, WrappedVariableOverrideRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputVariableOverrideRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableOverrideRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedVariableOverrideRemoveInfo wrapped = JSON.parseObject(in, WrappedVariableOverrideRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputVariableOverrideRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        variableQosService.overrideRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("变量删除成功");
    }

    private void handleSystemInspect(Context context, CommandLine cmd) throws Exception {
        VariableSystemInspectInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableSystemInspectInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputVariableSystemInspectInfo.toStackBean(
                    JSON.parseObject(json, WebInputVariableSystemInspectInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableSystemInspectInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputVariableSystemInspectInfo.toStackBean(
                        JSON.parseObject(in, WebInputVariableSystemInspectInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        VariableInspectResult result = variableQosService.systemInspect(info);

        // 输出结果。
        context.sendMessage("变量查询成功");
        context.sendMessage("result: " + result);
    }

    private void handleSystemUpsert(Context context, CommandLine cmd) throws Exception {
        VariableSystemUpsertInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableSystemUpsertInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputVariableSystemUpsertInfo.toStackBean(
                    JSON.parseObject(json, WebInputVariableSystemUpsertInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableSystemUpsertInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputVariableSystemUpsertInfo.toStackBean(
                        JSON.parseObject(in, WebInputVariableSystemUpsertInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        variableQosService.systemUpsert(info);

        // 输出结果。
        context.sendMessage("变量创建/更新成功");
    }

    private void handleSystemRemove(Context context, CommandLine cmd) throws Exception {
        VariableSystemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 VariableSystemRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputVariableSystemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputVariableSystemRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 VariableSystemRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputVariableSystemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputVariableSystemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        variableQosService.systemRemove(info);

        // 输出结果。
        context.sendMessage("变量删除成功");
    }

    public static class WrappedVariableManualUpsertInfo implements Dto {

        private static final long serialVersionUID = -6796680869670972874L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputVariableManualUpsertInfo info;

        public WrappedVariableManualUpsertInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputVariableManualUpsertInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputVariableManualUpsertInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVariableManualUpsertInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedVariableManualRemoveInfo implements Dto {

        private static final long serialVersionUID = -2588648342010962864L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputVariableManualRemoveInfo info;

        public WrappedVariableManualRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputVariableManualRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputVariableManualRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVariableManualRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedVariableOverrideUpsertInfo implements Dto {

        private static final long serialVersionUID = 5880917899207783059L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputVariableOverrideUpsertInfo info;

        public WrappedVariableOverrideUpsertInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputVariableOverrideUpsertInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputVariableOverrideUpsertInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVariableOverrideUpsertInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedVariableOverrideRemoveInfo implements Dto {

        private static final long serialVersionUID = 1657298495360986687L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputVariableOverrideRemoveInfo info;

        public WrappedVariableOverrideRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputVariableOverrideRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputVariableOverrideRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedVariableOverrideRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
