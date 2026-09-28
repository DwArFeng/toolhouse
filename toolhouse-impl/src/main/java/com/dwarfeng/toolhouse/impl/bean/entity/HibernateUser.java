package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.datamark.bean.jpa.DatamarkEntityListener;
import com.dwarfeng.datamark.bean.jpa.DatamarkField;
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
@EntityListeners(DatamarkEntityListener.class)
public class HibernateUser implements Bean {

    private static final long serialVersionUID = -4182188410380306593L;

    // region 主键

    @Id
    @Column(name = "id", nullable = false, unique = true, length = Constraints.LENGTH_USER)
    private String stringId;

    // endregion

    // region 主属性字段

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 一对多

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePoca.class, mappedBy = "user")
    private Set<HibernatePoca> pocas = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFavorite.class, mappedBy = "user")
    private Set<HibernateFavorite> favorites = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateSession.class, mappedBy = "user")
    private Set<HibernateSession> sessions = new HashSet<>();

    // endregion

    // region 审计

    @DatamarkField(handlerName = "userDatamarkHandler")
    @Column(
            name = "created_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE,
            updatable = false
    )
    private String createdDatamark;

    @DatamarkField(handlerName = "userDatamarkHandler")
    @Column(
            name = "modified_datamark",
            length = com.dwarfeng.datamark.util.Constraints.LENGTH_DATAMARK_VALUE
    )
    private String modifiedDatamark;

    // endregion

    public HibernateUser() {
    }

    // region 映射用属性区

    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey idKey) {
        this.stringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // endregion

    // region 常规属性区

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

    public String getCreatedDatamark() {
        return createdDatamark;
    }

    public void setCreatedDatamark(String createdDatamark) {
        this.createdDatamark = createdDatamark;
    }

    public String getModifiedDatamark() {
        return modifiedDatamark;
    }

    public void setModifiedDatamark(String modifiedDatamark) {
        this.modifiedDatamark = modifiedDatamark;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "stringId = " + stringId + ", " +
                "remark = " + remark + ", " +
                "createdDatamark = " + createdDatamark + ", " +
                "modifiedDatamark = " + modifiedDatamark + ")";
    }
}
