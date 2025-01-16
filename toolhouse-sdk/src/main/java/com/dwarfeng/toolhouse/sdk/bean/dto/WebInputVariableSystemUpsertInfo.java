package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.sdk.util.ValidVariableType;
import com.dwarfeng.toolhouse.stack.bean.dto.VariableSystemUpsertInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * WebInput 变量系统创建/更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVariableSystemUpsertInfo implements Dto {

    private static final long serialVersionUID = -5511788102339230552L;

    public static VariableSystemUpsertInfo toStackBean(WebInputVariableSystemUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VariableSystemUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey()),
                    webInput.getVariableStringId(),
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

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    @JSONField(name = "variable_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String variableStringId;

    @JSONField(name = "type")
    @ValidVariableType
    private int type;

    @JSONField(name = "string_value")
    private String stringValue;

    @JSONField(name = "long_value")
    private Long longValue;

    @JSONField(name = "double_value")
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

    public WebInputVariableSystemUpsertInfo() {
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getVariableStringId() {
        return variableStringId;
    }

    public void setVariableStringId(String variableStringId) {
        this.variableStringId = variableStringId;
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
        return "WebInputVariableSystemUpsertInfo{" +
                "sessionKey=" + sessionKey +
                ", variableStringId='" + variableStringId + '\'' +
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
