package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 输入项超控删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class InputItemOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = 4795359896066158533L;

    private LongIdKey taskKey;
    private String itemStringId;

    public InputItemOverrideRemoveInfo() {
    }

    public InputItemOverrideRemoveInfo(LongIdKey taskKey, String itemStringId) {
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
        return "InputItemOverrideRemoveInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
