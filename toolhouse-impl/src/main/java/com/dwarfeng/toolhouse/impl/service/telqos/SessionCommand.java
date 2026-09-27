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
import com.dwarfeng.toolhouse.stack.service.SessionQosService;
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
public class SessionCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_CREATE = "mancre";
    private static final String COMMAND_OPTION_MANUAL_CREATE_LONG_OPT = "manual-create";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_REMOVE = "manrm";
    private static final String COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT = "manual-remove";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_CREATE = "ovrcre";
    private static final String COMMAND_OPTION_OVERRIDE_CREATE_LONG_OPT = "override-create";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_REMOVE = "ovrrm";
    private static final String COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT = "override-remove";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_CREATE = "syscre";
    private static final String COMMAND_OPTION_SYSTEM_CREATE_LONG_OPT = "system-create";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_REMOVE = "sysrm";
    private static final String COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT = "system-remove";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_MANUAL_CREATE,
            COMMAND_OPTION_MANUAL_REMOVE,
            COMMAND_OPTION_OVERRIDE_CREATE,
            COMMAND_OPTION_OVERRIDE_REMOVE,
            COMMAND_OPTION_SYSTEM_CREATE,
            COMMAND_OPTION_SYSTEM_REMOVE,
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private static final String IDENTITY = "session";
    private static final String DESCRIPTION = "会话操作";

    private static final String CMD_LINE_SYNTAX_MANUAL_CREATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_CREATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_MANUAL_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_CREATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_CREATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_CREATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_CREATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_REMOVE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_REMOVE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_MANUAL_CREATE,
            CMD_LINE_SYNTAX_MANUAL_REMOVE,
            CMD_LINE_SYNTAX_OVERRIDE_CREATE,
            CMD_LINE_SYNTAX_OVERRIDE_REMOVE,
            CMD_LINE_SYNTAX_SYSTEM_CREATE,
            CMD_LINE_SYNTAX_SYSTEM_REMOVE,
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final SessionQosService sessionQosService;

    public SessionCommand(SessionQosService sessionQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.sessionQosService = sessionQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_CREATE).longOpt(COMMAND_OPTION_MANUAL_CREATE_LONG_OPT)
                        .desc("手动创建会话").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_REMOVE).longOpt(COMMAND_OPTION_MANUAL_REMOVE_LONG_OPT)
                        .desc("手动删除会话").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_CREATE).longOpt(COMMAND_OPTION_OVERRIDE_CREATE_LONG_OPT)
                        .desc("超控创建会话").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_REMOVE).longOpt(COMMAND_OPTION_OVERRIDE_REMOVE_LONG_OPT)
                        .desc("超控删除会话").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_CREATE).longOpt(COMMAND_OPTION_SYSTEM_CREATE_LONG_OPT)
                        .desc("系统创建会话").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_REMOVE).longOpt(COMMAND_OPTION_SYSTEM_REMOVE_LONG_OPT)
                        .desc("系统删除会话").build()
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
                case COMMAND_OPTION_MANUAL_CREATE:
                    handleManualCreate(context, cmd);
                    break;
                case COMMAND_OPTION_MANUAL_REMOVE:
                    handleManualRemove(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_CREATE:
                    handleOverrideCreate(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_REMOVE:
                    handleOverrideRemove(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_CREATE:
                    handleSystemCreate(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_REMOVE:
                    handleSystemRemove(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleManualCreate(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        SessionManualCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SessionManualCreateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedSessionManualCreateInfo wrapped = JSON.parseObject(json, WrappedSessionManualCreateInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputSessionManualCreateInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SessionManualCreateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedSessionManualCreateInfo wrapped = JSON.parseObject(in, WrappedSessionManualCreateInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputSessionManualCreateInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        SessionCreateResult result = sessionQosService.manualCreate(operateUserKey, info);

        // 输出结果。
        context.sendMessage("会话创建成功, 创建结果: " + result);
    }

    private void handleManualRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        SessionManualRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SessionManualRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedSessionManualRemoveInfo wrapped = JSON.parseObject(json, WrappedSessionManualRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputSessionManualRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SessionManualRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedSessionManualRemoveInfo wrapped = JSON.parseObject(in, WrappedSessionManualRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputSessionManualRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        sessionQosService.manualRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("会话创建成功");
    }

    private void handleOverrideCreate(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        SessionOverrideCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SessionOverrideCreateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedSessionOverrideCreateInfo wrapped = JSON.parseObject(json, WrappedSessionOverrideCreateInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputSessionOverrideCreateInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SessionOverrideCreateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedSessionOverrideCreateInfo wrapped = JSON.parseObject(in, WrappedSessionOverrideCreateInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputSessionOverrideCreateInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        SessionCreateResult result = sessionQosService.overrideCreate(operateUserKey, info);

        // 输出结果。
        context.sendMessage("会话创建成功, 创建结果: " + result);
    }

    private void handleOverrideRemove(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        SessionOverrideRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SessionOverrideRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedSessionOverrideRemoveInfo wrapped = JSON.parseObject(json, WrappedSessionOverrideRemoveInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputSessionOverrideRemoveInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SessionOverrideRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedSessionOverrideRemoveInfo wrapped = JSON.parseObject(in, WrappedSessionOverrideRemoveInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputSessionOverrideRemoveInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        sessionQosService.overrideRemove(operateUserKey, info);

        // 输出结果。
        context.sendMessage("会话创建成功");
    }

    private void handleSystemCreate(Context context, CommandLine cmd) throws Exception {
        SessionSystemCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SessionSystemCreateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputSessionSystemCreateInfo.toStackBean(
                    JSON.parseObject(json, WebInputSessionSystemCreateInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SessionSystemCreateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSessionSystemCreateInfo.toStackBean(
                        JSON.parseObject(in, WebInputSessionSystemCreateInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        SessionCreateResult result = sessionQosService.systemCreate(info);

        // 输出结果。
        context.sendMessage("会话创建成功, 创建结果: " + result);
    }

    private void handleSystemRemove(Context context, CommandLine cmd) throws Exception {
        SessionSystemRemoveInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 SessionSystemRemoveInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputSessionSystemRemoveInfo.toStackBean(
                    JSON.parseObject(json, WebInputSessionSystemRemoveInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 SessionSystemRemoveInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputSessionSystemRemoveInfo.toStackBean(
                        JSON.parseObject(in, WebInputSessionSystemRemoveInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        sessionQosService.systemRemove(info);

        // 输出结果。
        context.sendMessage("会话创建成功");
    }

    public static class WrappedSessionManualCreateInfo implements Dto {

        private static final long serialVersionUID = -941736622137444590L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputSessionManualCreateInfo info;

        public WrappedSessionManualCreateInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputSessionManualCreateInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputSessionManualCreateInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedSessionManualCreateInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedSessionManualRemoveInfo implements Dto {

        private static final long serialVersionUID = 197714785885459562L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputSessionManualRemoveInfo info;

        public WrappedSessionManualRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputSessionManualRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputSessionManualRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedSessionManualRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedSessionOverrideCreateInfo implements Dto {

        private static final long serialVersionUID = 899095661985055630L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputSessionOverrideCreateInfo info;

        public WrappedSessionOverrideCreateInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputSessionOverrideCreateInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputSessionOverrideCreateInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedSessionOverrideCreateInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedSessionOverrideRemoveInfo implements Dto {

        private static final long serialVersionUID = 3311353660629780773L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputSessionOverrideRemoveInfo info;

        public WrappedSessionOverrideRemoveInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputSessionOverrideRemoveInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputSessionOverrideRemoveInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedSessionOverrideRemoveInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
