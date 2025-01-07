package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.FileInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.entity.User;
import com.dwarfeng.toolhouse.stack.service.FileInfoMaintainService;
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
public class FileInfoMaintainServiceImplTest {

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
    private FileInfoMaintainService fileInfoMaintainService;

    private Session session;
    private Tool tool;
    private User user;
    private List<FileInfo> fileInfos;

    @Before
    public void setUp() {
        session = new Session(
                new LongIdKey(SESSION_ID), new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), new Date(), new Date(),
                "remark"
        );
        tool = new Tool(new LongIdKey(TOOL_ID), null, null, "name", "remark", new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        fileInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FileInfo fileInfo = new FileInfo(
                    null, new LongIdKey(SESSION_ID), new LongIdKey(TOOL_ID), new StringIdKey(USER_ID), "originName",
                    12450L, new Date(), new Date(), new Date(), "remark"
            );
            fileInfos.add(fileInfo);
        }
    }

    @After
    public void tearDown() {
        session = null;
        tool = null;
        user = null;
        fileInfos.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            userMaintainService.insertOrUpdate(user);
            toolMaintainService.insertOrUpdate(tool);
            sessionMaintainService.insertOrUpdate(session);
            for (FileInfo fileInfo : fileInfos) {
                fileInfo.setKey(fileInfoMaintainService.insertOrUpdate(fileInfo));
                FileInfo testFileInfo = fileInfoMaintainService.get(fileInfo.getKey());
                assertEquals(BeanUtils.describe(fileInfo), BeanUtils.describe(testFileInfo));
                testFileInfo = fileInfoMaintainService.get(fileInfo.getKey());
                assertEquals(BeanUtils.describe(fileInfo), BeanUtils.describe(testFileInfo));
            }
        } finally {
            for (FileInfo fileInfo : fileInfos) {
                fileInfoMaintainService.deleteIfExists(fileInfo.getKey());
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
            for (FileInfo fileInfo : fileInfos) {
                fileInfo.setKey(fileInfoMaintainService.insertOrUpdate(fileInfo));
            }

            assertEquals(
                    fileInfos.size(),
                    fileInfoMaintainService.lookupAsList(
                            FileInfoMaintainService.CHILD_FOR_SESSION, new Object[]{session.getKey()}
                    ).size()
            );

            sessionMaintainService.deleteIfExists(session.getKey());

            assertEquals(
                    0,
                    fileInfoMaintainService.lookupAsList(
                            FileInfoMaintainService.CHILD_FOR_SESSION, new Object[]{session.getKey()}
                    ).size()
            );

            for (FileInfo fileInfo : fileInfos) {
                assertFalse(fileInfoMaintainService.exists(fileInfo.getKey()));
            }
        } finally {
            for (FileInfo fileInfo : fileInfos) {
                fileInfoMaintainService.deleteIfExists(fileInfo.getKey());
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
            for (FileInfo fileInfo : fileInfos) {
                fileInfo.setKey(fileInfoMaintainService.insertOrUpdate(fileInfo));
            }

            assertEquals(
                    fileInfos.size(),
                    fileInfoMaintainService.lookupAsList(
                            FileInfoMaintainService.TOOL_KEY_EQ, new Object[]{tool.getKey()}
                    ).size()
            );

            toolMaintainService.deleteIfExists(tool.getKey());

            assertEquals(
                    0,
                    fileInfoMaintainService.lookupAsList(
                            FileInfoMaintainService.TOOL_KEY_EQ, new Object[]{tool.getKey()}
                    ).size()
            );

            for (FileInfo fileInfo : fileInfos) {
                assertFalse(fileInfoMaintainService.exists(fileInfo.getKey()));
            }
        } finally {
            for (FileInfo fileInfo : fileInfos) {
                fileInfoMaintainService.deleteIfExists(fileInfo.getKey());
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
            for (FileInfo fileInfo : fileInfos) {
                fileInfo.setKey(fileInfoMaintainService.insertOrUpdate(fileInfo));
            }

            assertEquals(
                    fileInfos.size(),
                    fileInfoMaintainService.lookupAsList(
                            FileInfoMaintainService.USER_KEY_EQ, new Object[]{user.getKey()}
                    ).size()
            );

            userMaintainService.deleteIfExists(user.getKey());

            assertEquals(
                    0,
                    fileInfoMaintainService.lookupAsList(
                            FileInfoMaintainService.USER_KEY_EQ, new Object[]{user.getKey()}
                    ).size()
            );

            for (FileInfo fileInfo : fileInfos) {
                assertFalse(fileInfoMaintainService.exists(fileInfo.getKey()));
            }
        } finally {
            for (FileInfo fileInfo : fileInfos) {
                fileInfoMaintainService.deleteIfExists(fileInfo.getKey());
            }
            sessionMaintainService.deleteIfExists(session.getKey());
            toolMaintainService.deleteIfExists(tool.getKey());
            userMaintainService.deleteIfExists(user.getKey());
        }
    }
}
