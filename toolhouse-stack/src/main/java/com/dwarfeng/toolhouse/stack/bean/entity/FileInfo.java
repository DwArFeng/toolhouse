package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;

/**
 * 文件信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = 968146312709967223L;

    private LongIdKey key;
    private LongIdKey sessionKey;

    /**
     * 父节点或祖父节点所属的工具的主键。
     *
     * <p>
     * 该字段为冗余字段，便于删除工具时快速删除该实体。
     */
    private LongIdKey toolKey;

    /**
     * 父节点或祖父节点所属的用户的主键。
     *
     * <p>
     * 该字段为冗余字段，便于删除用户时快速删除该实体。
     */
    private StringIdKey userKey;

    private String originName;
    private long length;
    private Date createdDate;
    private Date modifiedDate;
    private Date inspectedDate;
    private String remark;

    public FileInfo() {
    }

    public FileInfo(
            LongIdKey key, LongIdKey sessionKey, LongIdKey toolKey, StringIdKey userKey, String originName,
            long length, Date createdDate, Date modifiedDate, Date inspectedDate, String remark
    ) {
        this.key = key;
        this.sessionKey = sessionKey;
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.originName = originName;
        this.length = length;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.inspectedDate = inspectedDate;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(LongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public LongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(LongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "key=" + key +
                ", sessionKey=" + sessionKey +
                ", toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", inspectedDate=" + inspectedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
