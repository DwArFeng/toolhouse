package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import com.dwarfeng.toolhouse.stack.handler.ExecuteLocalCacheHandler;
import com.dwarfeng.toolhouse.stack.handler.Executor;
import com.dwarfeng.toolhouse.stack.handler.ExecutorHandler;
import com.dwarfeng.toolhouse.stack.service.ExecutorInfoMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import com.dwarfeng.toolhouse.stack.struct.ExecuteInfo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExecuteLocalCacheHandlerImpl implements ExecuteLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, ExecuteInfo> handler;

    public ExecuteLocalCacheHandlerImpl(ExecuteInfoFetcher ExecuteInfoFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(ExecuteInfoFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public ExecuteInfo get(LongIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(LongIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() {
        handler.clear();
    }

    @Component
    public static class ExecuteInfoFetcher implements Fetcher<LongIdKey, ExecuteInfo> {

        private final ToolMaintainService toolMaintainService;
        private final ExecutorInfoMaintainService executorInfoMaintainService;

        private final ExecutorHandler executorHandler;

        public ExecuteInfoFetcher(
                ToolMaintainService toolMaintainService,
                ExecutorInfoMaintainService executorInfoMaintainService,
                ExecutorHandler executorHandler
        ) {
            this.toolMaintainService = toolMaintainService;
            this.executorInfoMaintainService = executorInfoMaintainService;
            this.executorHandler = executorHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            return toolMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public ExecuteInfo fetch(LongIdKey key) throws Exception {
            Tool tool = toolMaintainService.get(key);
            Map<ExecutorKey, ExecutorInfo> executorInfoMap = new HashMap<>();
            Map<ExecutorKey, Executor> executorMap = new HashMap<>();
            List<ExecutorInfo> executorInfos = executorInfoMaintainService.lookupAsList(
                    ExecutorInfoMaintainService.CHILD_FOR_TOOL, new Object[]{key}
            );
            for (ExecutorInfo executorInfo : executorInfos) {
                executorInfoMap.put(executorInfo.getKey(), executorInfo);
                Executor executor = executorHandler.make(executorInfo.getType(), executorInfo.getParam());
                executorMap.put(executorInfo.getKey(), executor);
            }
            return new ExecuteInfo(tool, executorInfoMap, executorMap);
        }
    }
}
