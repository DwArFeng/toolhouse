package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
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
public class CabinetMaintainServiceImplTest {

    @Autowired
    private CabinetMaintainService cabinetMaintainService;

    private List<Cabinet> cabinets;

    @Before
    public void setUp() {
        cabinets = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Cabinet cabinet = new Cabinet(null, "name", "remark", new Date(), 0);
            cabinets.add(cabinet);
        }
    }

    @After
    public void tearDown() {
        cabinets.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Cabinet cabinet : cabinets) {
                cabinet.setKey(cabinetMaintainService.insert(cabinet));

                Cabinet testCabinet = cabinetMaintainService.get(cabinet.getKey());
                assertEquals(BeanUtils.describe(cabinet), BeanUtils.describe(testCabinet));
                cabinetMaintainService.update(cabinet);
                testCabinet = cabinetMaintainService.get(cabinet.getKey());
                assertEquals(BeanUtils.describe(cabinet), BeanUtils.describe(testCabinet));
            }
        } finally {
            for (Cabinet cabinet : cabinets) {
                cabinetMaintainService.deleteIfExists(cabinet.getKey());
            }
        }
    }
}
