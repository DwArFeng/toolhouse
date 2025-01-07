package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.entity.User;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import com.dwarfeng.toolhouse.stack.service.UserMaintainService;
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
public class SessionMaintainServiceImplTest {

    private static final Long TOOL_ID = 12452L;
    private static final String USER_ID = "test_user";

    @Autowired
    private ToolMaintainService toolMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private SessionMaintainService sessionMaintainService;

    private Tool tool;
    private User user;
    private List<Session> sessions;

    @Before
    public void setUp() {
        tool = new Tool(new LongIdKey(TOOL_ID), null, null, "name", "remark", new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        sessions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Session session = new Session(
                    null, new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), new Date(), new Date(), "remark"
            );
            sessions.add(session);
        }
    }

    @After
    public void tearDown() {
        tool = null;
        user = null;
        sessions.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            for (Session session : sessions) {
                session.setKey(sessionMaintainService.insertOrUpdate(session));
                Session testSession = sessionMaintainService.get(session.getKey());
                assertEquals(BeanUtils.describe(session), BeanUtils.describe(testSession));
                testSession = sessionMaintainService.get(session.getKey());
                assertEquals(BeanUtils.describe(session), BeanUtils.describe(testSession));
            }
        } finally {
            for (Session session : sessions) {
                sessionMaintainService.deleteIfExists(session.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForToolCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            for (Session session : sessions) {
                sessionMaintainService.insertOrUpdate(session);
            }

            assertEquals(
                    sessions.size(),
                    sessionMaintainService.lookupAsList(
                            SessionMaintainService.CHILD_FOR_TOOL, new Object[]{tool.getKey()}
                    ).size()
            );

            toolMaintainService.deleteIfExists(tool.getKey());

            assertEquals(
                    0,
                    sessionMaintainService.lookupAsList(
                            SessionMaintainService.CHILD_FOR_TOOL, new Object[]{tool.getKey()}
                    ).size()
            );

            for (Session session : sessions) {
                assertFalse(sessionMaintainService.exists(session.getKey()));
            }
        } finally {
            for (Session session : sessions) {
                sessionMaintainService.deleteIfExists(session.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            for (Session session : sessions) {
                sessionMaintainService.insertOrUpdate(session);
            }

            assertEquals(
                    sessions.size(),
                    sessionMaintainService.lookupAsList(
                            SessionMaintainService.CHILD_FOR_USER, new Object[]{user.getKey()}
                    ).size()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(
                    0,
                    sessionMaintainService.lookupAsList(
                            SessionMaintainService.CHILD_FOR_USER, new Object[]{user.getKey()}
                    ).size()
            );

            for (Session session : sessions) {
                assertFalse(sessionMaintainService.exists(session.getKey()));
            }
        } finally {
            for (Session session : sessions) {
                sessionMaintainService.deleteIfExists(session.getKey());
            }
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }
}
