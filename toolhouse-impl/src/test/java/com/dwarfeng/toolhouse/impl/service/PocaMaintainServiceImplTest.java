package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;
import com.dwarfeng.toolhouse.stack.bean.entity.User;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.PocaMaintainService;
import com.dwarfeng.toolhouse.stack.service.UserMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PocaMaintainServiceImplTest {

    private static final long CABINET_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private CabinetMaintainService cabinetMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private PocaMaintainService pocaMaintainService;

    private Cabinet cabinet;
    private User user;
    private Poca poca;

    @Before
    public void setUp() {
        cabinet = new Cabinet(new LongIdKey(CABINET_ID), "name", "remark", new Date(), 0);
        user = new User(new StringIdKey(USER_ID), "remark");
        poca = new Poca(new PocaKey(CABINET_ID, USER_ID), 233, "remark");
    }

    @After
    public void tearDown() {
        cabinet = null;
        user = null;
        poca = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            cabinetMaintainService.insertOrUpdate(cabinet);
            userMaintainService.insertOrUpdate(user);
            pocaMaintainService.insert(poca);
            pocaMaintainService.update(poca);

            Poca testPoca = pocaMaintainService.get(poca.getKey());
            assertEquals(BeanUtils.describe(poca), BeanUtils.describe(testPoca));
            testPoca = pocaMaintainService.get(poca.getKey());
            assertEquals(BeanUtils.describe(poca), BeanUtils.describe(testPoca));
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            pocaMaintainService.deleteIfExists(poca.getKey());
        }
    }

    @Test
    public void testForCabinetCascade() throws Exception {
        try {
            cabinetMaintainService.insertOrUpdate(cabinet);
            userMaintainService.insertOrUpdate(user);
            pocaMaintainService.insert(poca);

            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            assertFalse(pocaMaintainService.exists(poca.getKey()));
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            pocaMaintainService.deleteIfExists(poca.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            cabinetMaintainService.insertOrUpdate(cabinet);
            userMaintainService.insertOrUpdate(user);
            pocaMaintainService.insert(poca);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(pocaMaintainService.exists(poca.getKey()));
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            pocaMaintainService.deleteIfExists(poca.getKey());
        }
    }
}
