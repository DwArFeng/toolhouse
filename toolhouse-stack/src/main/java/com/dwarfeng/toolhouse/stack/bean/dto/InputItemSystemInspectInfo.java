package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 输入项系统查询信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class InputItemSystemInspectInfo implements Dto {

    private static final long serialVersionUID = 4736669449880491796L;
    
    private LongIdKey taskKey;
    private String itemStringId;

    public InputItemSystemInspectInfo() {
    }

    public InputItemSystemInspectInfo(LongIdKey taskKey, String itemStringId) {
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
        return "InputItemSystemInspectInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
