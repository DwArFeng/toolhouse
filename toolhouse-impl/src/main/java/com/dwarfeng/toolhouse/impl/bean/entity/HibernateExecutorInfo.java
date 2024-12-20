package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.impl.bean.key.HibernateExecutorKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;

@Entity
@IdClass(HibernateExecutorKey.class)
@Table(name = "tbl_executor_info")
public class HibernateExecutorInfo implements Bean {

    private static final long serialVersionUID = 1484673514871953485L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "tool_id", nullable = false)
    private Long toolLongId;

    @Id
    @Column(name = "executor_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String executorStringId;

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

    public HibernateExecutorInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateExecutorKey getKey() {
        return new HibernateExecutorKey(toolLongId, executorStringId);
    }

    public void setKey(HibernateExecutorKey key) {
        if (null == key) {
            this.toolLongId = null;
            this.executorStringId = null;
        } else {
            this.toolLongId = key.getToolLongId();
            this.executorStringId = key.getExecutorStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getToolLongId() {
        return toolLongId;
    }

    public void setToolLongId(Long toolLongId) {
        this.toolLongId = toolLongId;
    }

    public String getExecutorStringId() {
        return executorStringId;
    }

    public void setExecutorStringId(String executorStringId) {
        this.executorStringId = executorStringId;
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
                "executorStringId = " + executorStringId + ", " +
                "enabled = " + enabled + ", " +
                "type = " + type + ", " +
                "param = " + param + ", " +
                "remark = " + remark + ", " +
                "tool = " + tool + ")";
    }
}
