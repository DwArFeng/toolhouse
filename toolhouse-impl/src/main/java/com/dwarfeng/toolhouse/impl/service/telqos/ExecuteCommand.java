package com.dwarfeng.toolhouse.impl.service.telqos;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.dutil.basic.mea.TimeMeasurer;
import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputTaskManualExecuteInfo;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputTaskOverrideExecuteInfo;
import com.dwarfeng.toolhouse.sdk.bean.dto.WebInputTaskSystemExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemExecuteInfo;
import com.dwarfeng.toolhouse.stack.service.ExecuteQosService;
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
public class ExecuteCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_MANUAL_EXECUTE_TASK = "manet";
    private static final String COMMAND_OPTION_MANUAL_EXECUTE_TASK_LONG_OPT = "manual-execute-task";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_OVERRIDE_EXECUTE_TASK = "ovret";
    private static final String COMMAND_OPTION_OVERRIDE_EXECUTE_TASK_LONG_OPT = "override-execute-task";
    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_SYSTEM_EXECUTE_TASK = "syset";
    private static final String COMMAND_OPTION_SYSTEM_EXECUTE_TASK_LONG_OPT = "system-execute-task";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_MANUAL_EXECUTE_TASK,
            COMMAND_OPTION_OVERRIDE_EXECUTE_TASK,
            COMMAND_OPTION_SYSTEM_EXECUTE_TASK,
    };

    private static final String COMMAND_OPTION_JSON = "json";
    private static final String COMMAND_OPTION_JSON_FILE = "jf";
    private static final String COMMAND_OPTION_JSON_FILE_LONG_OPT = "json-file";

    private static final String IDENTITY = "execute";
    private static final String DESCRIPTION = "执行操作";

    private static final String CMD_LINE_SYNTAX_MANUAL_EXECUTE_TASK = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_MANUAL_EXECUTE_TASK) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_OVERRIDE_EXECUTE_TASK = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_OVERRIDE_EXECUTE_TASK) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";
    private static final String CMD_LINE_SYNTAX_SYSTEM_EXECUTE_TASK = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_SYSTEM_EXECUTE_TASK) + " [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON) + " json-string] [" +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_JSON_FILE) + " json-file]";

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_MANUAL_EXECUTE_TASK,
            CMD_LINE_SYNTAX_OVERRIDE_EXECUTE_TASK,
            CMD_LINE_SYNTAX_SYSTEM_EXECUTE_TASK,
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final ExecuteQosService executeQosService;

    public ExecuteCommand(ExecuteQosService executeQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.executeQosService = executeQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_MANUAL_EXECUTE_TASK).longOpt(COMMAND_OPTION_MANUAL_EXECUTE_TASK_LONG_OPT)
                        .desc("手动执行任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_OVERRIDE_EXECUTE_TASK)
                        .longOpt(COMMAND_OPTION_OVERRIDE_EXECUTE_TASK_LONG_OPT).desc("超控执行任务").build()
        );
        list.add(
                Option.builder(COMMAND_OPTION_SYSTEM_EXECUTE_TASK).longOpt(COMMAND_OPTION_SYSTEM_EXECUTE_TASK_LONG_OPT)
                        .desc("系统执行任务").build()
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
                case COMMAND_OPTION_MANUAL_EXECUTE_TASK:
                    handleManualExecuteTask(context, cmd);
                    break;
                case COMMAND_OPTION_OVERRIDE_EXECUTE_TASK:
                    handleOverrideExecuteTask(context, cmd);
                    break;
                case COMMAND_OPTION_SYSTEM_EXECUTE_TASK:
                    handleSystemExecuteTask(context, cmd);
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }

    private void handleManualExecuteTask(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        TaskManualExecuteInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskManualExecuteInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedTaskManualExecuteInfo wrapped = JSON.parseObject(json, WrappedTaskManualExecuteInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputTaskManualExecuteInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskManualExecuteInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedTaskManualExecuteInfo wrapped = JSON.parseObject(in, WrappedTaskManualExecuteInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputTaskManualExecuteInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 启动计时器。
        TimeMeasurer tm = new TimeMeasurer();
        tm.start();

        // 调用 QOS 服务相关方法。
        executeQosService.manualExecuteTask(operateUserKey, info);

        // 停止计时器。
        tm.stop();

        // 输出结果。
        context.sendMessage("任务执行成功，耗时：" + tm.getTimeMs() + "ms");
    }

    private void handleOverrideExecuteTask(Context context, CommandLine cmd) throws Exception {
        StringIdKey operateUserKey;
        TaskOverrideExecuteInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskOverrideExecuteInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            WrappedTaskOverrideExecuteInfo wrapped = JSON.parseObject(json, WrappedTaskOverrideExecuteInfo.class);
            operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
            info = WebInputTaskOverrideExecuteInfo.toStackBean(wrapped.getInfo());
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskOverrideExecuteInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                WrappedTaskOverrideExecuteInfo wrapped = JSON.parseObject(in, WrappedTaskOverrideExecuteInfo.class);
                operateUserKey = WebInputStringIdKey.toStackBean(wrapped.getOperateUserKey());
                info = WebInputTaskOverrideExecuteInfo.toStackBean(wrapped.getInfo());
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 启动计时器。
        TimeMeasurer tm = new TimeMeasurer();
        tm.start();

        // 调用 QOS 服务相关方法。
        executeQosService.overrideExecuteTask(operateUserKey, info);

        // 停止计时器。
        tm.stop();

        // 输出结果。
        context.sendMessage("任务执行成功，耗时：" + tm.getTimeMs() + "ms");
    }

    private void handleSystemExecuteTask(Context context, CommandLine cmd) throws Exception {
        TaskSystemExecuteInfo info;

        // 如果有 -json 选项，则从选项中获取 JSON，转化为 TaskSystemExecuteInfo。
        if (cmd.hasOption(COMMAND_OPTION_JSON)) {
            String json = (String) cmd.getParsedOptionValue(COMMAND_OPTION_JSON);
            info = WebInputTaskSystemExecuteInfo.toStackBean(
                    JSON.parseObject(json, WebInputTaskSystemExecuteInfo.class)
            );
        }
        // 如果有 --json-file 选项，则从选项中获取 JSON 文件，转化为 TaskSystemExecuteInfo。
        else if (cmd.hasOption(COMMAND_OPTION_JSON_FILE)) {
            File jsonFile = (File) cmd.getParsedOptionValue(COMMAND_OPTION_JSON_FILE);
            try (FileInputStream in = new FileInputStream(jsonFile)) {
                info = WebInputTaskSystemExecuteInfo.toStackBean(
                        JSON.parseObject(in, WebInputTaskSystemExecuteInfo.class)
                );
            }
        } else {
            // 暂时未实现。
            throw new UnsupportedOperationException("not supported yet");
        }

        // 启动计时器。
        TimeMeasurer tm = new TimeMeasurer();
        tm.start();

        // 调用 QOS 服务相关方法。
        executeQosService.systemExecuteTask(info);

        // 停止计时器。
        tm.stop();

        // 输出结果。
        context.sendMessage("任务执行成功，耗时：" + tm.getTimeMs() + "ms");
    }

    public static class WrappedTaskManualExecuteInfo implements Dto {

        private static final long serialVersionUID = 5719789668807670367L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputTaskManualExecuteInfo info;

        public WrappedTaskManualExecuteInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputTaskManualExecuteInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputTaskManualExecuteInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedTaskManualExecuteInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }

    public static class WrappedTaskOverrideExecuteInfo implements Dto {

        private static final long serialVersionUID = 5445435915324876813L;

        @JSONField(name = "operate_user_key")
        @NotNull
        @Valid
        private WebInputStringIdKey operateUserKey;

        @JSONField(name = "info")
        @NotNull
        @Valid
        private WebInputTaskOverrideExecuteInfo info;

        public WrappedTaskOverrideExecuteInfo() {
        }

        public WebInputStringIdKey getOperateUserKey() {
            return operateUserKey;
        }

        public void setOperateUserKey(WebInputStringIdKey operateUserKey) {
            this.operateUserKey = operateUserKey;
        }

        public WebInputTaskOverrideExecuteInfo getInfo() {
            return info;
        }

        public void setInfo(WebInputTaskOverrideExecuteInfo info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "WrappedTaskOverrideExecuteInfo{" +
                    "operateUserKey=" + operateUserKey +
                    ", info=" + info +
                    '}';
        }
    }
}
