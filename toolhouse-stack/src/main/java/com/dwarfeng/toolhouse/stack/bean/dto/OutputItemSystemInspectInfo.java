package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 输出项系统查询信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class OutputItemSystemInspectInfo implements Dto {

    private static final long serialVersionUID = -8471869213013762995L;
    
    private LongIdKey taskKey;
    private String itemStringId;

    public OutputItemSystemInspectInfo() {
    }

    public OutputItemSystemInspectInfo(LongIdKey taskKey, String itemStringId) {
        this.taskKey = taskKey;
        this.itemStringId = itemStringId;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public String toString() {
        return "OutputItemSystemInspectInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
