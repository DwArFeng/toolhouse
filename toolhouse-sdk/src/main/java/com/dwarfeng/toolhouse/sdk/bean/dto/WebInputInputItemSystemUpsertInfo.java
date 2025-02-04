package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.sdk.util.ValidTaskItemType;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemSystemUpsertInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 输入项系统创建/更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputInputItemSystemUpsertInfo implements Dto {

    public static InputItemSystemUpsertInfo toStackBean(WebInputInputItemSystemUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new InputItemSystemUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey()),
                    webInput.getItemStringId(),
                    webInput.getType(),
                    webInput.getStringValue(),
                    webInput.getLongValue(),
                    webInput.getDoubleValue(),
                    webInput.getBooleanValue(),
                    webInput.getDateValue(),
                    webInput.getFileRef(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "item_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String itemStringId;

    @JSONField(name = "type")
    @ValidTaskItemType
    private int type;

    @JSONField(name = "string_value")
    private String stringValue;

    @JSONField(name = "long_value")
    private Long longValue;

    @JSONField(name = "doubleValue")
    private Double doubleValue;

    @JSONField(name = "boolean_value")
    private Boolean booleanValue;

    @JSONField(name = "date_value")
    private Date dateValue;

    @JSONField(name = "file_ref")
    private Long fileRef;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputInputItemSystemUpsertInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Long getFileRef() {
        return fileRef;
    }

    public void setFileRef(Long fileRef) {
        this.fileRef = fileRef;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputInputItemSystemUpsertInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                ", type=" + type +
                ", stringValue='" + stringValue + '\'' +
                ", longValue=" + longValue +
                ", doubleValue=" + doubleValue +
                ", booleanValue=" + booleanValue +
                ", dateValue=" + dateValue +
                ", fileRef=" + fileRef +
                ", remark='" + remark + '\'' +
                '}';
    }
}
