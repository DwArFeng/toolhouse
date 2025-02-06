package groovy

import com.dwarfeng.toolhouse.impl.handler.executor.groovy.Processor
import com.dwarfeng.toolhouse.sdk.util.Constants
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemSystemUpsertInfo
import com.dwarfeng.toolhouse.stack.handler.Executor

/**
 * 在上下文中增加一个 id 为 foobar 的输出项。
 *
 * @author Dwarfeng
 * @since beta-1.0.0
 */
@SuppressWarnings('GrPackage')
class ExampleExecutorProcessor implements Processor {

    @Override
    void execute(Executor.Context context) throws Exception {
        context.upsertOutputItem(new OutputItemSystemUpsertInfo(
                context.getTaskKey(), 'foobar', Constants.TASK_ITEM_TYPE_STRING,
                'foobar', null, null, null, null, null, 'Groovy 脚本测试'
        ))
    }
}
