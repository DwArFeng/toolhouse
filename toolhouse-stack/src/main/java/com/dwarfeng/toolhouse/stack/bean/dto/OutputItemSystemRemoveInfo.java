package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 输出项系统删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class OutputItemSystemRemoveInfo implements Dto {

    private static final long serialVersionUID = -5536037012981603508L;

    private LongIdKey taskKey;
    private String itemStringId;

    public OutputItemSystemRemoveInfo() {
    }

    public OutputItemSystemRemoveInfo(LongIdKey taskKey, String itemStringId) {
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
        return "OutputItemSystemRemoveInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
