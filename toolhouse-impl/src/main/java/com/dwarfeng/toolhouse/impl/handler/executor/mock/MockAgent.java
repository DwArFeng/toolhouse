package com.dwarfeng.toolhouse.impl.handler.executor.mock;

import com.dwarfeng.toolhouse.sdk.handler.executor.AbstractAgent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * 模拟代理人。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component("mockExecutorRegistry.mockAgent")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MockAgent extends AbstractAgent {

    private final Config config;

    public MockAgent(Config config) {
        this.config = config;
    }

    @Override
    protected void doExecute() throws Exception {
        // 基于 config 的配置生成随机数生成器。
        Random random = generateRandom();
        // 延时指定的时间。
        delay();
        // 随机失败。
        randomFail(random);
    }

    private Random generateRandom() {
        Long randomSeed = config.getRandomSeed();
        if (Objects.isNull(randomSeed)) {
            return new Random();
        } else {
            return new Random(randomSeed);
        }
    }

    private void delay() throws InterruptedException {
        Long delay = config.getDuration();
        if (Objects.isNull(delay)) {
            return;
        }
        Thread.sleep(delay);
    }

    private void randomFail(Random random) throws Exception {
        Double successRate = config.getSuccessRate();
        if (Objects.isNull(successRate)) {
            return;
        }
        if (random.nextDouble() > successRate) {
            throw new Exception("Random fail.");
        }
    }

    @Override
    public String toString() {
        return "MockAgent{" +
                "config=" + config +
                ", context=" + context +
                '}';
    }
}
