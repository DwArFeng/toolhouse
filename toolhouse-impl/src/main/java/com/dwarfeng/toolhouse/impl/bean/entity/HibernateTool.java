package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_tool")
public class HibernateTool implements Bean {

    private static final long serialVersionUID = -7989381235534807379L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "folder_id")
    private Long folderLongId;

    @Column(name = "cabinet_id")
    private Long cabinetLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateFolder.class)
    @JoinColumns({ //
            @JoinColumn(name = "folder_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateFolder folder;

    @ManyToOne(targetEntity = HibernateCabinet.class)
    @JoinColumns({ //
            @JoinColumn(name = "cabinet_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateCabinet cabinet;

    public HibernateTool() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getFolderKey() {
        return Optional.ofNullable(folderLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setFolderKey(HibernateLongIdKey idKey) {
        this.folderLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getCabinetKey() {
        return Optional.ofNullable(cabinetLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setCabinetKey(HibernateLongIdKey idKey) {
        this.cabinetLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getFolderLongId() {
        return folderLongId;
    }

    public void setFolderLongId(Long folderLongId) {
        this.folderLongId = folderLongId;
    }

    public Long getCabinetLongId() {
        return cabinetLongId;
    }

    public void setCabinetLongId(Long cabinetLongId) {
        this.cabinetLongId = cabinetLongId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public HibernateFolder getFolder() {
        return folder;
    }

    public void setFolder(HibernateFolder folder) {
        this.folder = folder;
    }

    public HibernateCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(HibernateCabinet cabinet) {
        this.cabinet = cabinet;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "folderLongId = " + folderLongId + ", " +
                "cabinetLongId = " + cabinetLongId + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "createdDate = " + createdDate + ", " +
                "folder = " + folder + ", " +
                "cabinet = " + cabinet + ")";
    }
}
