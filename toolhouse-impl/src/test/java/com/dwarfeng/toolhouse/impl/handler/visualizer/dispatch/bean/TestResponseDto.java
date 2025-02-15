package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 测试响应 DTO。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TestResponseDto implements Dto {

    private static final long serialVersionUID = -8746080403710910223L;
    
    @JSONField(name = "controller_name")
    private String controllerName;

    public TestResponseDto() {
    }

    public TestResponseDto(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    @Override
    public String toString() {
        return "TestResponseDto{" +
                "controllerName='" + controllerName + '\'' +
                '}';
    }
}
