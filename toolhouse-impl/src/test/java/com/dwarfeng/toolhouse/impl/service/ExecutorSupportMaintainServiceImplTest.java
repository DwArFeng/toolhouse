package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorSupport;
import com.dwarfeng.toolhouse.stack.service.ExecutorSupportMaintainService;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ExecutorSupportMaintainServiceImplTest {

    @Autowired
    private ExecutorSupportMaintainService service;

    private final List<ExecutorSupport> executorSupports = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            ExecutorSupport executorSupport = new ExecutorSupport(
                    new StringIdKey("executor-support-" + (i + 1)), "label", "description", "exampleParam"
            );
            executorSupports.add(executorSupport);
        }
    }

    @After
    public void tearDown() {
        executorSupports.clear();
    }

    @Test
    public void test() throws Exception {
        try {
            for (ExecutorSupport executorSupport : executorSupports) {
                executorSupport.setKey(service.insert(executorSupport));
                service.update(executorSupport);
                ExecutorSupport testExecutorSupport = service.get(executorSupport.getKey());
                assertEquals(BeanUtils.describe(executorSupport), BeanUtils.describe(testExecutorSupport));
            }
        } finally {
            for (ExecutorSupport executorSupport : executorSupports) {
                service.deleteIfExists(executorSupport.getKey());
            }
        }
    }
}
