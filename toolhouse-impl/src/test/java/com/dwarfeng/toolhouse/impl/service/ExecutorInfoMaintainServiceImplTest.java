package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import com.dwarfeng.toolhouse.stack.service.ExecutorInfoMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ExecutorInfoMaintainServiceImplTest {

    private static final long TOOL_LONG_ID = 12450;

    @Autowired
    private ToolMaintainService toolMaintainService;
    @Autowired
    private ExecutorInfoMaintainService executorInfoMaintainService;

    private Tool tool;
    private List<ExecutorInfo> executorInfos;

    @Before
    public void setUp() {
        tool = new Tool(new LongIdKey(TOOL_LONG_ID), null, null, "name", "remark", new Date());
        executorInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ExecutorInfo executorInfo = new ExecutorInfo(
                    new ExecutorKey(TOOL_LONG_ID, Integer.toString(i)), true, "type", "param", "remark"
            );
            executorInfos.add(executorInfo);
        }
    }

    @After
    public void tearDown() {
        tool = null;
        executorInfos.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            toolMaintainService.insertOrUpdate(tool);
            for (ExecutorInfo executorInfo : executorInfos) {
                executorInfoMaintainService.insertOrUpdate(executorInfo);
                ExecutorInfo testExecutorInfo = executorInfoMaintainService.get(executorInfo.getKey());
                assertEquals(BeanUtils.describe(executorInfo), BeanUtils.describe(testExecutorInfo));
                testExecutorInfo = executorInfoMaintainService.get(executorInfo.getKey());
                assertEquals(BeanUtils.describe(executorInfo), BeanUtils.describe(testExecutorInfo));
            }
        } finally {
            for (ExecutorInfo executorInfo : executorInfos) {
                executorInfoMaintainService.deleteIfExists(executorInfo.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
        }
    }

    @Test
    public void testForToolCascade() throws Exception {
        try {
            toolMaintainService.insertOrUpdate(tool);
            for (ExecutorInfo executorInfo : executorInfos) {
                executorInfoMaintainService.insertOrUpdate(executorInfo);
            }

            assertEquals(executorInfos.size(), executorInfoMaintainService.lookup(
                    ExecutorInfoMaintainService.CHILD_FOR_TOOL, new Object[]{tool.getKey()}
            ).getCount());

            toolMaintainService.deleteIfExists(tool.getKey());

            assertEquals(0, executorInfoMaintainService.lookup(
                    ExecutorInfoMaintainService.CHILD_FOR_TOOL, new Object[]{tool.getKey()}
            ).getCount());
        } finally {
            for (ExecutorInfo executorInfo : executorInfos) {
                executorInfoMaintainService.deleteIfExists(executorInfo.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
        }
    }
}
