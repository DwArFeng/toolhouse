package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_cabinet")
public class HibernateCabinet implements Bean {

    private static final long serialVersionUID = 5925595007338381066L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "tool_count")
    private int toolCount;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFolder.class, mappedBy = "cabinet")
    private Set<HibernateFolder> folders = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateTool.class, mappedBy = "cabinet")
    private Set<HibernateTool> tools = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePoca.class, mappedBy = "cabinet")
    private Set<HibernatePoca> pocas = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFavorite.class, mappedBy = "cabinet")
    private Set<HibernateFavorite> favorites = new HashSet<>();

    public HibernateCabinet() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
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

    public int getToolCount() {
        return toolCount;
    }

    public void setToolCount(int toolCount) {
        this.toolCount = toolCount;
    }

    public Set<HibernateFolder> getFolders() {
        return folders;
    }

    public void setFolders(Set<HibernateFolder> folders) {
        this.folders = folders;
    }

    public Set<HibernateTool> getTools() {
        return tools;
    }

    public void setTools(Set<HibernateTool> tools) {
        this.tools = tools;
    }

    public Set<HibernatePoca> getPocas() {
        return pocas;
    }

    public void setPocas(Set<HibernatePoca> pocas) {
        this.pocas = pocas;
    }

    public Set<HibernateFavorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<HibernateFavorite> favorites) {
        this.favorites = favorites;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "name = " + name + ", " +
                "remark = " + remark + ", " +
                "createdDate = " + createdDate + ", " +
                "toolCount = " + toolCount + ")";
    }
}
