package com.dwarfeng.toolhouse.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 任务项键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class HibernateTaskItemKey implements Key {

    private static final long serialVersionUID = 4175258926458119487L;

    private Long taskLongId;
    private String itemStringId;

    public HibernateTaskItemKey() {
    }

    public HibernateTaskItemKey(Long taskLongId, String itemStringId) {
        this.taskLongId = taskLongId;
        this.itemStringId = itemStringId;
    }

    public Long getTaskLongId() {
        return taskLongId;
    }

    public void setTaskLongId(Long taskLongId) {
        this.taskLongId = taskLongId;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateTaskItemKey that = (HibernateTaskItemKey) o;
        return Objects.equals(taskLongId, that.taskLongId) && Objects.equals(itemStringId, that.itemStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(taskLongId);
        result = 31 * result + Objects.hashCode(itemStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateTaskItemKey{" +
                "taskLongId=" + taskLongId +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
