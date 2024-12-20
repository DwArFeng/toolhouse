package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.impl.bean.key.HibernateVisualizerKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;

@Entity
@IdClass(HibernateVisualizerKey.class)
@Table(name = "tbl_visualizer_info")
public class HibernateVisualizerInfo implements Bean {

    private static final long serialVersionUID = 270163041650571153L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "tool_id", nullable = false)
    private Long toolLongId;

    @Id
    @Column(name = "visualizer_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String visualizerStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "type", length = Constraints.LENGTH_TYPE)
    private String type;

    @Column(name = "param", columnDefinition = "TEXT")
    private String param;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTool.class)
    @JoinColumns({ //
            @JoinColumn(name = "tool_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTool tool;

    public HibernateVisualizerInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateVisualizerKey getKey() {
        return new HibernateVisualizerKey(toolLongId, visualizerStringId);
    }

    public void setKey(HibernateVisualizerKey key) {
        if (null == key) {
            this.toolLongId = null;
            this.visualizerStringId = null;
        } else {
            this.toolLongId = key.getToolLongId();
            this.visualizerStringId = key.getVisualizerStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getToolLongId() {
        return toolLongId;
    }

    public void setToolLongId(Long toolLongId) {
        this.toolLongId = toolLongId;
    }

    public String getVisualizerStringId() {
        return visualizerStringId;
    }

    public void setVisualizerStringId(String visualizerStringId) {
        this.visualizerStringId = visualizerStringId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateTool getTool() {
        return tool;
    }

    public void setTool(HibernateTool tool) {
        this.tool = tool;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "toolLongId = " + toolLongId + ", " +
                "visualizerStringId = " + visualizerStringId + ", " +
                "enabled = " + enabled + ", " +
                "type = " + type + ", " +
                "param = " + param + ", " +
                "remark = " + remark + ", " +
                "tool = " + tool + ")";
    }
}
