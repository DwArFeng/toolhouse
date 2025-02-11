package com.dwarfeng.toolhouse.impl.service.telqos;

import com.dwarfeng.springtelqos.node.config.TelqosCommand;
import com.dwarfeng.springtelqos.sdk.command.CliCommand;
import com.dwarfeng.springtelqos.stack.command.Context;
import com.dwarfeng.springtelqos.stack.exception.TelqosException;
import com.dwarfeng.toolhouse.stack.service.VisualizeCacheQosService;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

@TelqosCommand
public class VisualizerCacheCommand extends CliCommand {

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String COMMAND_OPTION_CLEAR_CACHE = "cc";
    private static final String COMMAND_OPTION_CLEAR_CACHE_LONG_OPT = "clear-cache";

    private static final String[] COMMAND_OPTION_ARRAY = new String[]{
            COMMAND_OPTION_CLEAR_CACHE,
    };

    @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
    private static final String IDENTITY = "vcache";
    private static final String DESCRIPTION = "可视化缓存操作";

    private static final String CMD_LINE_SYNTAX_CLEAR_CACHE = IDENTITY + " " +
            CommandUtil.concatOptionPrefix(COMMAND_OPTION_CLEAR_CACHE);

    private static final String[] CMD_LINE_ARRAY = new String[]{
            CMD_LINE_SYNTAX_CLEAR_CACHE,
    };

    private static final String CMD_LINE_SYNTAX = CommandUtil.syntax(CMD_LINE_ARRAY);

    private final VisualizeCacheQosService visualizeCacheQosService;

    public VisualizerCacheCommand(VisualizeCacheQosService visualizeCacheQosService) {
        super(IDENTITY, DESCRIPTION, CMD_LINE_SYNTAX);
        this.visualizeCacheQosService = visualizeCacheQosService;
    }

    @Override
    protected List<Option> buildOptions() {
        List<Option> list = new ArrayList<>();
        list.add(
                Option.builder(COMMAND_OPTION_CLEAR_CACHE).longOpt(COMMAND_OPTION_CLEAR_CACHE_LONG_OPT)
                        .desc("清除缓存").build()
        );
        return list;
    }

    // 为了方法的可扩展性，此处代码不做简化。
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    protected void executeWithCmd(Context context, CommandLine cmd) throws TelqosException {
        try {
            Pair<String, Integer> pair = CommandUtil.analyseCommand(cmd, COMMAND_OPTION_ARRAY);
            if (pair.getRight() != 1) {
                context.sendMessage(CommandUtil.optionMismatchMessage(COMMAND_OPTION_ARRAY));
                context.sendMessage(CMD_LINE_SYNTAX);
                return;
            }
            switch (pair.getLeft()) {
                case COMMAND_OPTION_CLEAR_CACHE:
                    visualizeCacheQosService.clearCache();
                    context.sendMessage("本地缓存已清除");
                    break;
            }
        } catch (Exception e) {
            throw new TelqosException(e);
        }
    }
}
