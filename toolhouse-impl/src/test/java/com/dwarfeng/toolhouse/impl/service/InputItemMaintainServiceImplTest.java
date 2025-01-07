package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.service.*;
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
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class InputItemMaintainServiceImplTest {

    private static final Long TASK_ID = 12450L;
    private static final Long SESSION_ID = 12451L;
    private static final Long TOOL_ID = 12452L;
    private static final String USER_ID = "test_user";

    @Autowired
    private TaskMaintainService taskMaintainService;
    @Autowired
    private SessionMaintainService sessionMaintainService;
    @Autowired
    private ToolMaintainService toolMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private InputItemMaintainService inputItemMaintainService;

    private Task task;
    private Session session;
    private Tool tool;
    private User user;
    private List<InputItem> inputItems;

    @Before
    public void setUp() {
        task = new Task(
                new LongIdKey(TASK_ID), new LongIdKey(SESSION_ID), new LongIdKey(TOOL_ID), new StringIdKey(USER_ID),
                12450, new Date(), new Date(), new Date(), new Date(), new Date(), 12450L, new Date(), new Date(),
                "frontMessage", "remark"
        );
        session = new Session(
                new LongIdKey(SESSION_ID), new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), new Date(), new Date(),
                "remark"
        );
        tool = new Tool(new LongIdKey(TOOL_ID), null, null, "name", "remark", new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        inputItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            InputItem inputItem = new InputItem(
                    new TaskItemKey(TASK_ID, "test_input_item." + i),
                    new LongIdKey(SESSION_ID), new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), 12450, "stringValue",
                    12450L, 12.450, true, new Date(), 12450L, "remark"
            );
            inputItems.add(inputItem);
        }
    }

    @After
    public void tearDown() {
        task = null;
        session = null;
        tool = null;
        user = null;
        inputItems.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            taskMaintainService.insertOrUpdate(task);
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.insertOrUpdate(inputItem);
                InputItem testInputItem = inputItemMaintainService.get(inputItem.getKey());
                assertEquals(BeanUtils.describe(inputItem), BeanUtils.describe(testInputItem));
                testInputItem = inputItemMaintainService.get(inputItem.getKey());
                assertEquals(BeanUtils.describe(inputItem), BeanUtils.describe(testInputItem));
            }
        } finally {
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.deleteIfExists(inputItem.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForTaskCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            taskMaintainService.insertOrUpdate(task);
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.insertOrUpdate(inputItem);
            }

            assertEquals(
                    inputItems.size(),
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            taskMaintainService.deleteIfExists(task.getKey());

            assertEquals(
                    0,
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.CHILD_FOR_TASK, new Object[]{task.getKey()}
                    ).size()
            );

            for (InputItem inputItem : inputItems) {
                assertFalse(inputItemMaintainService.exists(inputItem.getKey()));
            }
        } finally {
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.deleteIfExists(inputItem.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForSessionCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            taskMaintainService.insertOrUpdate(task);
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.insertOrUpdate(inputItem);
            }

            assertEquals(
                    inputItems.size(),
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.SESSION_KEY_EQ, new Object[]{session.getKey()}
                    ).size()
            );

            sessionMaintainService.deleteIfExists(session.getKey());

            assertEquals(
                    0,
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.SESSION_KEY_EQ, new Object[]{session.getKey()}
                    ).size()
            );

            for (InputItem inputItem : inputItems) {
                assertFalse(inputItemMaintainService.exists(inputItem.getKey()));
            }
        } finally {
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.deleteIfExists(inputItem.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForToolCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            taskMaintainService.insertOrUpdate(task);
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.insertOrUpdate(inputItem);
            }

            assertEquals(
                    inputItems.size(),
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.TOOL_KEY_EQ, new Object[]{tool.getKey()}
                    ).size()
            );

            toolMaintainService.deleteIfExists(tool.getKey());

            assertEquals(
                    0,
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.TOOL_KEY_EQ, new Object[]{tool.getKey()}
                    ).size()
            );

            for (InputItem inputItem : inputItems) {
                assertFalse(inputItemMaintainService.exists(inputItem.getKey()));
            }
        } finally {
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.deleteIfExists(inputItem.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            taskMaintainService.insertOrUpdate(task);
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.insertOrUpdate(inputItem);
            }

            assertEquals(
                    inputItems.size(),
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.USER_KEY_EQ, new Object[]{user.getKey()}
                    ).size()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(
                    0,
                    inputItemMaintainService.lookupAsList(
                            InputItemMaintainService.USER_KEY_EQ, new Object[]{user.getKey()}
                    ).size()
            );

            for (InputItem inputItem : inputItems) {
                assertFalse(inputItemMaintainService.exists(inputItem.getKey()));
            }
        } finally {
            for (InputItem inputItem : inputItems) {
                inputItemMaintainService.deleteIfExists(inputItem.getKey());
            }
            taskMaintainService.deleteIfExists(task.getKey());
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }
}
