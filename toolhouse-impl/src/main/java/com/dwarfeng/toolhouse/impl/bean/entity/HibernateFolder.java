package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_folder")
public class HibernateFolder implements Bean {

    private static final long serialVersionUID = 8728482102980275953L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // endregion

    // region 外键

    @Column(name = "parent_id")
    private Long parentLongId;

    @Column(name = "cabinet_id")
    private Long cabinetLongId;

    // endregion

    // region 主属性字段

    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateFolder.class)
    @JoinColumns({ //
            @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateFolder parent;

    @ManyToOne(targetEntity = HibernateCabinet.class)
    @JoinColumns({ //
            @JoinColumn(name = "cabinet_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateCabinet cabinet;

    // endregion

    // region 一对多

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateTool.class, mappedBy = "folder")
    private Set<HibernateTool> tools = new HashSet<>();

    // endregion

    public HibernateFolder() {
    }

    // region 映射用属性区

    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getParentKey() {
        return Optional.ofNullable(parentLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setParentKey(HibernateLongIdKey idKey) {
        this.parentLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getCabinetKey() {
        return Optional.ofNullable(cabinetLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setCabinetKey(HibernateLongIdKey idKey) {
        this.cabinetLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // endregion

    // region 常规属性区

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getParentLongId() {
        return parentLongId;
    }

    public void setParentLongId(Long parentLongId) {
        this.parentLongId = parentLongId;
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

    public HibernateFolder getParent() {
        return parent;
    }

    public void setParent(HibernateFolder parent) {
        this.parent = parent;
    }

    public HibernateCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(HibernateCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public Set<HibernateTool> getTools() {
        return tools;
    }

    public void setTools(Set<HibernateTool> tools) {
        this.tools = tools;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "parentLongId = " + parentLongId + ", " +
                "cabinetLongId = " + cabinetLongId + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "parent = " + parent + ", " +
                "cabinet = " + cabinet + ")";
    }
}
