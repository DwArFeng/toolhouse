package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_user")
public class HibernateUser implements Bean {

    private static final long serialVersionUID = -3162510786322649799L;
    
    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true, length = Constraints.LENGTH_USER)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePoca.class, mappedBy = "user")
    private Set<HibernatePoca> pocas = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFavorite.class, mappedBy = "user")
    private Set<HibernateFavorite> favorites = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSession.class, mappedBy = "user")
    private Set<HibernateSession> sessions = new HashSet<>();

    public HibernateUser() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey idKey) {
        this.stringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Set<HibernateSession> getSessions() {
        return sessions;
    }

    public void setSessions(Set<HibernateSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "remark = " + remark + ")";
    }
}
