package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.FolderMaintainService;
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
public class FolderMaintainServiceImplTest {

    @Autowired
    private FolderMaintainService folderMaintainService;
    @Autowired
    private CabinetMaintainService cabinetMaintainService;

    private List<Folder> folders;
    private Folder parent;
    private Cabinet cabinet;

    @Before
    public void setUp() {
        folders = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Folder folder = new Folder(null, null, null, "name", "remark");
            folders.add(folder);
        }
        parent = new Folder(null, null, null, "name", "remark");
        cabinet = new Cabinet(null, "name", "remark", new Date(), 0);
    }

    @After
    public void tearDown() {
        folders.clear();
        parent = null;
        cabinet = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Folder folder : folders) {
                folder.setKey(folderMaintainService.insert(folder));

                Folder testFolder = folderMaintainService.get(folder.getKey());
                assertEquals(BeanUtils.describe(folder), BeanUtils.describe(testFolder));
                folderMaintainService.update(folder);
                testFolder = folderMaintainService.get(folder.getKey());
                assertEquals(BeanUtils.describe(folder), BeanUtils.describe(testFolder));
            }
        } finally {
            for (Folder folder : folders) {
                folderMaintainService.deleteIfExists(folder.getKey());
            }
        }
    }

    @Test
    public void testForParentCascade() throws Exception {
        try {
            parent.setKey(folderMaintainService.insertOrUpdate(parent));
            for (Folder folder : folders) {
                folder.setParentKey(parent.getKey());
                folder.setKey(folderMaintainService.insert(folder));
            }

            assertEquals(folders.size(), folderMaintainService.lookup(
                    FolderMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
            ).getCount());

            folderMaintainService.deleteIfExists(parent.getKey());

            assertEquals(0, folderMaintainService.lookup(
                    FolderMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
            ).getCount());
        } finally {
            folderMaintainService.deleteIfExists(parent.getKey());
            for (Folder folder : folders) {
                folderMaintainService.deleteIfExists(folder.getKey());
            }
        }
    }

    @Test
    public void testForCabinetCascade() throws Exception {
        try {
            cabinet.setKey(cabinetMaintainService.insertOrUpdate(cabinet));
            for (Folder folder : folders) {
                folder.setCabinetKey(cabinet.getKey());
                folder.setKey(folderMaintainService.insert(folder));
            }

            assertEquals(folders.size(), folderMaintainService.lookup(
                    FolderMaintainService.CHILD_FOR_CABINET, new Object[]{cabinet.getKey()}
            ).getCount());

            cabinetMaintainService.deleteIfExists(cabinet.getKey());

            assertEquals(0, folderMaintainService.lookup(
                    FolderMaintainService.CHILD_FOR_CABINET, new Object[]{cabinet.getKey()}
            ).getCount());
        } finally {
            cabinetMaintainService.deleteIfExists(cabinet.getKey());
            for (Folder folder : folders) {
                folderMaintainService.deleteIfExists(folder.getKey());
            }
        }
    }
}
