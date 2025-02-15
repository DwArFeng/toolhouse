package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 测试请求 DTO。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TestRequestDto implements Dto {

    private static final long serialVersionUID = -719436888884152220L;
    
    @JSONField(name = "fuzzy_text")
    private String fuzzyText;

    public TestRequestDto() {
    }

    public TestRequestDto(String fuzzyText) {
        this.fuzzyText = fuzzyText;
    }

    public String getFuzzyText() {
        return fuzzyText;
    }

    public void setFuzzyText(String fuzzyText) {
        this.fuzzyText = fuzzyText;
    }

    @Override
    public String toString() {
        return "TestRequestDto{" +
                "fuzzyText='" + fuzzyText + '\'' +
                '}';
    }
}
