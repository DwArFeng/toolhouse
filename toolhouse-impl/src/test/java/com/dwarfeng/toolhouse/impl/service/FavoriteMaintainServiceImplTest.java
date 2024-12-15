package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;
import com.dwarfeng.toolhouse.stack.bean.entity.User;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.FavoriteMaintainService;
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
public class FavoriteMaintainServiceImplTest {

    private static final long cabinet_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private CabinetMaintainService cabinetMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private FavoriteMaintainService favoriteMaintainService;

    private Cabinet cabinet;
    private User user;
    private Favorite favorite;

    @Before
    public void setUp() {
        cabinet = new Cabinet(new LongIdKey(cabinet_ID), "name", "remark", new Date(), 0);
        user = new User(new StringIdKey(USER_ID), "remark");
        favorite = new Favorite(new FavoriteKey(cabinet_ID, USER_ID), "remark");
    }

    @After
    public void tearDown() {
        cabinet = null;
        user = null;
        favorite = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            cabinetMaintainService.insertOrUpdate(cabinet);
            userMaintainService.insertOrUpdate(user);
            favoriteMaintainService.insert(favorite);
            favoriteMaintainService.update(favorite);

            Favorite testFavorite = favoriteMaintainService.get(favorite.getKey());
            assertEquals(BeanUtils.describe(favorite), BeanUtils.describe(testFavorite));
            testFavorite = favoriteMaintainService.get(favorite.getKey());
            assertEquals(BeanUtils.describe(favorite), BeanUtils.describe(testFavorite));
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            favoriteMaintainService.deleteIfExists(favorite.getKey());
        }
    }

    @Test
    public void testForCabinetCascade() throws Exception {
        try {
            cabinetMaintainService.insertOrUpdate(cabinet);
            userMaintainService.insertOrUpdate(user);
            favoriteMaintainService.insert(favorite);

            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            assertFalse(favoriteMaintainService.exists(favorite.getKey()));
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            favoriteMaintainService.deleteIfExists(favorite.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            cabinetMaintainService.insertOrUpdate(cabinet);
            userMaintainService.insertOrUpdate(user);
            favoriteMaintainService.insert(favorite);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(favoriteMaintainService.exists(favorite.getKey()));
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            favoriteMaintainService.deleteIfExists(favorite.getKey());
        }
    }
}
