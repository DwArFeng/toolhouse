package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 输出项超控删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class OutputItemOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = -6199831822149979580L;

    private LongIdKey taskKey;
    private String itemStringId;

    public OutputItemOverrideRemoveInfo() {
    }

    public OutputItemOverrideRemoveInfo(LongIdKey taskKey, String itemStringId) {
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
        return "OutputItemOverrideRemoveInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
