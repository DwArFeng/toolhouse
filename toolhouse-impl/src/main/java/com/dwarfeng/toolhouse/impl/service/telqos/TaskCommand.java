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
import com.dwarfeng.toolhouse.stack.service.TaskQosService;
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
public class TaskCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_CREATE = "mancre";
    private static final String COMMAND_OPTION_MANUAL_CREATE_LONG_OPT = "manual-create";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_CREATE = "ovrcre";
    private static final String COMMAND_OPTION_OVERRIDE_CREATE_LONG_OPT = "override-create";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_CREATE = "syscre";
    private static final String COMMAND_OPTION_SYSTEM_CREATE_LONG_OPT = "system-create";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_START = "sysstart";
    private static final String COMMAND_OPTION_SYSTEM_START_LONG_OPT = "system-start";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_FINISH = "sysfin";
    private static final String COMMAND_OPTION_SYSTEM_FINISH_LONG_OPT = "system-finish";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_FAIL = "sysfail";
    private static final String COMMAND_OPTION_SYSTEM_FAIL_LONG_OPT = "system-fail";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_EXPIRE = "sysexp";
    private static final String COMMAND_OPTION_SYSTEM_EXPIRE_LONG_OPT = "system-expire";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_UPDATE_MODAL = "sysupmod";
    private static final String COMMAND_OPTION_SYSTEM_UPDATE_MODAL_LONG_OPT = "system-update-modal";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_DIE = "sysdie";
    private static final String COMMAND_OPTION_SYSTEM_DIE_LONG_OPT = "system-die";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_BEAT = "sysbeat";
    private static final String COMMAND_OPTION_SYSTEM_BEAT_LONG_OPT = "system-beat";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_MANUAL_CREATE,
            COMMAND_OPTION_OVERRIDE_CREATE,
            COMMAND_OPTION_SYSTEM_CREATE,
            COMMAND_OPTION_SYSTEM_START,
            COMMAND_OPTION_SYSTEM_FINISH,
            COMMAND_OPTION_SYSTEM_FAIL,
            COMMAND_OPTION_SYSTEM_EXPIRE,
            COMMAND_OPTION_SYSTEM_UPDATE_MODAL,
            COMMAND_OPTION_SYSTEM_DIE,
            COMMAND_OPTION_SYSTEM_BEAT
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private static final String IDENTITY = "task";
    private static final String DESCRIPTION = "任务操作";

    private static final String CMD_LINE_SYNTAX_MANUAL_CREATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_CREATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_CREATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_CREATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_CREATE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_CREATE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_START = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_START) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_FINISH = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_FINISH) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_FAIL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_FAIL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_EXPIRE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_EXPIRE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_UPDATE_MODAL = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_UPDATE_MODAL) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_DIE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_DIE) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_BEAT = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_BEAT) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_MANUAL_CREATE,
            CMD_LINE_SYNTAX_OVERRIDE_CREATE,
            CMD_LINE_SYNTAX_SYSTEM_CREATE,
            CMD_LINE_SYNTAX_SYSTEM_START,
            CMD_LINE_SYNTAX_SYSTEM_FINISH,
            CMD_LINE_SYNTAX_SYSTEM_FAIL,
            CMD_LINE_SYNTAX_SYSTEM_EXPIRE,
            CMD_LINE_SYNTAX_SYSTEM_UPDATE_MODAL,
            CMD_LINE_SYNTAX_SYSTEM_DIE,
            CMD_LINE_SYNTAX_SYSTEM_BEAT
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final TaskQosService taskQosService;

    public TaskCommand(TaskQosService taskQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.taskQosService = taskQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_CREATE).longOpt(COMMAND_OPTION_MANUAL_CREATE_LONG_OPT)
                        .desc("手动创建任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_CREATE).longOpt(COMMAND_OPTION_OVERRIDE_CREATE_LONG_OPT)
                        .desc("超控创建任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_CREATE).longOpt(COMMAND_OPTION_SYSTEM_CREATE_LONG_OPT)
                        .desc("系统创建任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_START).longOpt(COMMAND_OPTION_SYSTEM_START_LONG_OPT)
                        .desc("系统开始任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_FINISH).longOpt(COMMAND_OPTION_SYSTEM_FINISH_LONG_OPT)
                        .desc("系统完成任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_FAIL).longOpt(COMMAND_OPTION_SYSTEM_FAIL_LONG_OPT)
                        .desc("系统失败任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_EXPIRE).longOpt(COMMAND_OPTION_SYSTEM_EXPIRE_LONG_OPT)
                        .desc("系统过期任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_UPDATE_MODAL).longOpt(COMMAND_OPTION_SYSTEM_UPDATE_MODAL_LONG_OPT)
                        .desc("系统更新任务模态").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_DIE).longOpt(COMMAND_OPTION_SYSTEM_DIE_LONG_OPT)
                        .desc("系统死亡任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_BEAT).longOpt(COMMAND_OPTION_SYSTEM_BEAT_LONG_OPT)
                        .desc("系统心跳任务").build()
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
                case COMMAND_OPTION_OVERRIDE_CREATE:
                    handleOverrideCreate(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_CREATE:
                    handleSystemCreate(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_START:
                    handleSystemStart(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_FINISH:
                    handleSystemFinish(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_FAIL:
                    handleSystemFail(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_EXPIRE:
                    handleSystemExpire(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_UPDATE_MODAL:
                    handleSystemUpdateModal(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_DIE:
                    handleSystemDie(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_BEAT:
                    handleSystemBeat(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleManualCreate(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        TaskManualCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskManualCreateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedTaskManualCreateInfo wrapped = JSON.parseObject(json, WrappedTaskManualCreateInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputTaskManualCreateInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskManualCreateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedTaskManualCreateInfo wrapped = JSON.parseObject(in, WrappedTaskManualCreateInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputTaskManualCreateInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        TaskCreateResult result = taskQosService.manualCreate(operateUserKey, info);

        // 输出结果。
        context.sendMessage("任务创建成功, 创建结果: " + result);
    }

    private void handleOverrideCreate(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        TaskOverrideCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskOverrideCreateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedTaskOverrideCreateInfo wrapped = JSON.parseObject(json, WrappedTaskOverrideCreateInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputTaskOverrideCreateInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskOverrideCreateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedTaskOverrideCreateInfo wrapped = JSON.parseObject(in, WrappedTaskOverrideCreateInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputTaskOverrideCreateInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        TaskCreateResult result = taskQosService.overrideCreate(operateUserKey, info);

        // 输出结果。
        context.sendMessage("任务创建成功, 创建结果: " + result);
    }

    private void handleSystemCreate(Context context, CommandLine cmd) throws Exception {
        TaskSystemCreateInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemCreateInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemCreateInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemCreateInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemCreateInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemCreateInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemCreateInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        TaskCreateResult result = taskQosService.systemCreate(info);

        // 输出结果。
        context.sendMessage("任务创建成功, 创建结果: " + result);
    }

    private void handleSystemStart(Context context, CommandLine cmd) throws Exception {
        TaskSystemStartInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemStartInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemStartInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemStartInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemStartInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemStartInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemStartInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemStart(info);

        // 输出结果。
        context.sendMessage("任务开始成功");
    }

    private void handleSystemFinish(Context context, CommandLine cmd) throws Exception {
        TaskSystemFinishInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemFinishInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemFinishInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemFinishInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemFinishInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemFinishInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemFinishInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemFinish(info);

        // 输出结果。
        context.sendMessage("任务结束成功");
    }

    private void handleSystemFail(Context context, CommandLine cmd) throws Exception {
        TaskSystemFailInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemFailInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemFailInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemFailInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemFailInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemFailInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemFailInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemFail(info);

        // 输出结果。
        context.sendMessage("任务失败成功");
    }

    private void handleSystemExpire(Context context, CommandLine cmd) throws Exception {
        TaskSystemExpireInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemExpireInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemExpireInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemExpireInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemExpireInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemExpireInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemExpireInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemExpire(info);

        // 输出结果。
        context.sendMessage("任务过期成功");
    }

    private void handleSystemUpdateModal(Context context, CommandLine cmd) throws Exception {
        TaskSystemUpdateModalInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemUpdateModalInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemUpdateModalInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemUpdateModalInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemUpdateModalInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemUpdateModalInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemUpdateModalInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemUpdateModal(info);

        // 输出结果。
        context.sendMessage("更新任务模态成功");
    }

    private void handleSystemDie(Context context, CommandLine cmd) throws Exception {
        TaskSystemDieInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemDieInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemDieInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemDieInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemDieInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemDieInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemDieInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemDie(info);

        // 输出结果。
        context.sendMessage("任务死亡成功");
    }

    private void handleSystemBeat(Context context, CommandLine cmd) throws Exception {
        TaskSystemBeatInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemBeatInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemBeatInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemBeatInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemBeatInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemBeatInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemBeatInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 调用 QOS 服务相关方法。
        taskQosService.systemBeat(info);

        // 输出结果。
        context.sendMessage("任务心跳成功");
    }

    public static class WrappedTaskManualCreateInfo implements Dto {

        private static final long serialVersionUID = 3112562743293851266L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputTaskManualCreateInfo info;

        public WrappedTaskManualCreateInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputTaskManualCreateInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputTaskManualCreateInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedTaskManualCreateInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedTaskOverrideCreateInfo implements Dto {

        private static final long serialVersionUID = -3069296744710804228L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputTaskOverrideCreateInfo info;

        public WrappedTaskOverrideCreateInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputTaskOverrideCreateInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputTaskOverrideCreateInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedTaskOverrideCreateInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
