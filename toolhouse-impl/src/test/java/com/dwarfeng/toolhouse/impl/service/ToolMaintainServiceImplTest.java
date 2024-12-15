package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.FolderMaintainService;
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
public class ToolMaintainServiceImplTest {

    @Autowired
    private ToolMaintainService toolMaintainService;
    @Autowired
    private FolderMaintainService folderMaintainService;
    @Autowired
    private CabinetMaintainService cabinetMaintainService;

    private List<Tool> tools;
    private Folder folder;
    private Cabinet cabinet;

    @Before
    public void setUp() {
        tools = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tool tool = new Tool(null, null, null, "name", "remark", new Date());
            tools.add(tool);
        }
        folder = new Folder(null, null, null, "name", "remark");
        cabinet = new Cabinet(null, "name", "remark", new Date(), 12450);
    }

    @After
    public void tearDown() {
        tools.clear();
        folder = null;
        cabinet = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Tool tool : tools) {
                tool.setKey(toolMaintainService.insert(tool));

                Tool testTool = toolMaintainService.get(tool.getKey());
                assertEquals(BeanUtils.describe(tool), BeanUtils.describe(testTool));
                toolMaintainService.update(tool);
                testTool = toolMaintainService.get(tool.getKey());
                assertEquals(BeanUtils.describe(tool), BeanUtils.describe(testTool));
            }
        } finally {
            for (Tool tool : tools) {
                toolMaintainService.deleteIfExists(tool.getKey());
            }
        }
    }

    @Test
    public void testForFolderCascade() throws Exception {
        try {
            folder.setKey(folderMaintainService.insertOrUpdate(folder));
            for (Tool tool : tools) {
                tool.setFolderKey(folder.getKey());
                tool.setKey(toolMaintainService.insert(tool));
            }

            assertEquals(tools.size(), toolMaintainService.lookup(
                    ToolMaintainService.CHILD_FOR_FOLDER, new Object[]{folder.getKey()}
            ).getCount());

            folderMaintainService.deleteIfExists(folder.getKey());

            assertEquals(0, toolMaintainService.lookup(
                    ToolMaintainService.CHILD_FOR_FOLDER, new Object[]{folder.getKey()}
            ).getCount());
        } finally {
            folderMaintainService.deleteIfExists(folder.getKey());
            for (Tool tool : tools) {
                toolMaintainService.deleteIfExists(tool.getKey());
            }
        }
    }

    @Test
    public void testForCabinetCascade() throws Exception {
        try {
            cabinet.setKey(cabinetMaintainService.insertOrUpdate(cabinet));
            for (Tool tool : tools) {
                tool.setCabinetKey(cabinet.getKey());
                tool.setKey(toolMaintainService.insert(tool));
            }

            assertEquals(tools.size(), toolMaintainService.lookup(
                    ToolMaintainService.CHILD_FOR_CABINET, new Object[]{cabinet.getKey()}
            ).getCount());

            cabinetMaintainService.deleteIfExists(cabinet.getKey());

            assertEquals(0, toolMaintainService.lookup(
                    ToolMaintainService.CHILD_FOR_CABINET, new Object[]{cabinet.getKey()}
            ).getCount());
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            for (Tool tool : tools) {
                toolMaintainService.deleteIfExists(tool.getKey());
            }
        }
    }
}
