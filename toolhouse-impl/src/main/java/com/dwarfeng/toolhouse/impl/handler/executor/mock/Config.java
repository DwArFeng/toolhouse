package com.dwarfeng.toolhouse.impl.handler.executor.mock;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 配置。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Config implements Bean {

    private static final long serialVersionUID = 471212910014248921L;

    @JSONField(name = "#random_seed", ordinal = 1, deserialize = false)
    private String randomSeedRem = "随机种子, 用于生成随机数据, 可以为 null。";

    @JSONField(name = "random_seed", ordinal = 2)
    private Long randomSeed;

    @JSONField(name = "#duration", ordinal = 3, deserialize = false)
    private String durationRem = "模拟耗时，单位为毫秒，如果值小于等于 0，则不模拟耗时。";

    @JSONField(name = "duration", ordinal = 4)
    private Long duration;

    @JSONField(name = "#success_rate", ordinal = 5, deserialize = false)
    private String successRateRem = "模拟成功率，取值范围为 [0, 1]，如果值小于等于 0，则模拟失败，如果值大于等于 1，则模拟成功。";

    @JSONField(name = "success_rate", ordinal = 6)
    private Double successRate;

    public Config() {
    }

    public Config(Long randomSeed, Long duration, Double successRate) {
        this.randomSeed = randomSeed;
        this.duration = duration;
        this.successRate = successRate;
    }

    public String getRandomSeedRem() {
        return randomSeedRem;
    }

    public void setRandomSeedRem(String randomSeedRem) {
        this.randomSeedRem = randomSeedRem;
    }

    public Long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(Long randomSeed) {
        this.randomSeed = randomSeed;
    }

    public String getDurationRem() {
        return durationRem;
    }

    public void setDurationRem(String durationRem) {
        this.durationRem = durationRem;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getSuccessRateRem() {
        return successRateRem;
    }

    public void setSuccessRateRem(String successRateRem) {
        this.successRateRem = successRateRem;
    }

    public Double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(Double successRate) {
        this.successRate = successRate;
    }

    @Override
    public String toString() {
        return "Config{" +
                "randomSeedRem='" + randomSeedRem + '\'' +
                ", randomSeed=" + randomSeed +
                ", durationRem='" + durationRem + '\'' +
                ", duration=" + duration +
                ", successRateRem='" + successRateRem + '\'' +
                ", successRate=" + successRate +
                '}';
    }
}
