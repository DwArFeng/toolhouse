package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.entity.User;
import com.dwarfeng.toolhouse.stack.bean.entity.Variable;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import com.dwarfeng.toolhouse.stack.service.UserMaintainService;
import com.dwarfeng.toolhouse.stack.service.VariableMaintainService;
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
public class VariableMaintainServiceImplTest {

    private static final Long SESSION_ID = 12451L;
    private static final Long TOOL_ID = 12452L;
    private static final String USER_ID = "test_user";

    @Autowired
    private SessionMaintainService sessionMaintainService;
    @Autowired
    private ToolMaintainService toolMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private VariableMaintainService variableMaintainService;

    private Session session;
    private Tool tool;
    private User user;
    private List<Variable> variables;

    @Before
    public void setUp() {
        session = new Session(
                new LongIdKey(SESSION_ID), new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), new Date(), new Date(),
                "remark"
        );
        tool = new Tool(new LongIdKey(TOOL_ID), null, null, "name", "remark", new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        variables = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Variable variable = new Variable(
                    new VariableKey(SESSION_ID, "test_variable." + i),
                    new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), 12450, "stringValue", 12450L, 12.450, true,
                    new Date(), 12450L, "remark"
            );
            variables.add(variable);
        }
    }

    @After
    public void tearDown() {
        session = null;
        tool = null;
        user = null;
        variables.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            for (Variable variable : variables) {
                variable.setKey(variableMaintainService.insertOrUpdate(variable));
                Variable testVariable = variableMaintainService.get(variable.getKey());
                assertEquals(BeanUtils.describe(variable), BeanUtils.describe(testVariable));
                testVariable = variableMaintainService.get(variable.getKey());
                assertEquals(BeanUtils.describe(variable), BeanUtils.describe(testVariable));
            }
        } finally {
            for (Variable variable : variables) {
                variableMaintainService.deleteIfExists(variable.getKey());
            }
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
            for (Variable variable : variables) {
                variable.setKey(variableMaintainService.insertOrUpdate(variable));
            }

            assertEquals(
                    variables.size(),
                    variableMaintainService.lookupAsList(
                            VariableMaintainService.CHILD_FOR_SESSION, new Object[]{session.getKey()}
                    ).size()
            );

            sessionMaintainService.deleteIfExists(session.getKey());

            assertEquals(
                    0,
                    variableMaintainService.lookupAsList(
                            VariableMaintainService.CHILD_FOR_SESSION, new Object[]{session.getKey()}
                    ).size()
            );

            for (Variable variable : variables) {
                assertFalse(variableMaintainService.exists(variable.getKey()));
            }
        } finally {
            for (Variable variable : variables) {
                variableMaintainService.deleteIfExists(variable.getKey());
            }
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
            for (Variable variable : variables) {
                variable.setKey(variableMaintainService.insertOrUpdate(variable));
            }

            assertEquals(
                    variables.size(),
                    variableMaintainService.lookupAsList(
                            VariableMaintainService.TOOL_KEY_EQ, new Object[]{tool.getKey()}
                    ).size()
            );

            toolMaintainService.deleteIfExists(tool.getKey());

            assertEquals(
                    0,
                    variableMaintainService.lookupAsList(
                            VariableMaintainService.TOOL_KEY_EQ, new Object[]{tool.getKey()}
                    ).size()
            );

            for (Variable variable : variables) {
                assertFalse(variableMaintainService.exists(variable.getKey()));
            }
        } finally {
            for (Variable variable : variables) {
                variableMaintainService.deleteIfExists(variable.getKey());
            }
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
            for (Variable variable : variables) {
                variable.setKey(variableMaintainService.insertOrUpdate(variable));
            }

            assertEquals(
                    variables.size(),
                    variableMaintainService.lookupAsList(
                            VariableMaintainService.USER_KEY_EQ, new Object[]{user.getKey()}
                    ).size()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(
                    0,
                    variableMaintainService.lookupAsList(
                            VariableMaintainService.USER_KEY_EQ, new Object[]{user.getKey()}
                    ).size()
            );

            for (Variable variable : variables) {
                assertFalse(variableMaintainService.exists(variable.getKey()));
            }
        } finally {
            for (Variable variable : variables) {
                variableMaintainService.deleteIfExists(variable.getKey());
            }
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }
}
