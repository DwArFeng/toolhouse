package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import com.dwarfeng.toolhouse.stack.service.VisualizerInfoMaintainService;
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
public class VisualizerInfoMaintainServiceImplTest {

    private static final long TOOL_LONG_ID = 12450;

    @Autowired
    private ToolMaintainService toolMaintainService;
    @Autowired
    private VisualizerInfoMaintainService visualizerInfoMaintainService;

    private Tool tool;
    private List<VisualizerInfo> visualizerInfos;

    @Before
    public void setUp() {
        tool = new Tool(new LongIdKey(TOOL_LONG_ID), null, null, "name", "remark", new Date());
        visualizerInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VisualizerInfo visualizerInfo = new VisualizerInfo(
                    new VisualizerKey(TOOL_LONG_ID, Integer.toString(i)), true, "type", "param", "remark"
            );
            visualizerInfos.add(visualizerInfo);
        }
    }

    @After
    public void tearDown() {
        tool = null;
        visualizerInfos.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            toolMaintainService.insertOrUpdate(tool);
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfoMaintainService.insertOrUpdate(visualizerInfo);
                VisualizerInfo testVisualizerInfo = visualizerInfoMaintainService.get(visualizerInfo.getKey());
                assertEquals(BeanUtils.describe(visualizerInfo), BeanUtils.describe(testVisualizerInfo));
                testVisualizerInfo = visualizerInfoMaintainService.get(visualizerInfo.getKey());
                assertEquals(BeanUtils.describe(visualizerInfo), BeanUtils.describe(testVisualizerInfo));
            }
        } finally {
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfoMaintainService.deleteIfExists(visualizerInfo.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
        }
    }

    @Test
    public void testForToolCascade() throws Exception {
        try {
            toolMaintainService.insertOrUpdate(tool);
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfoMaintainService.insertOrUpdate(visualizerInfo);
            }

            assertEquals(visualizerInfos.size(), visualizerInfoMaintainService.lookup(
                    VisualizerInfoMaintainService.CHILD_FOR_TOOL, new Object[]{tool.getKey()}
            ).getCount());

            toolMaintainService.deleteIfExists(tool.getKey());

            assertEquals(0, visualizerInfoMaintainService.lookup(
                    VisualizerInfoMaintainService.CHILD_FOR_TOOL, new Object[]{tool.getKey()}
            ).getCount());
        } finally {
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfoMaintainService.deleteIfExists(visualizerInfo.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
        }
    }
}
