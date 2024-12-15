package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.impl.bean.key.HibernateFavoriteKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateFavoriteKey.class)
@Table(name = "tbl_favorite")
public class HibernateFavorite implements Bean {

    private static final long serialVersionUID = 7639159556782653240L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "cabinet_id", nullable = false)
    private Long cabinetLongId;

    @Id
    @Column(name = "user_id", length = Constraints.LENGTH_USER, nullable = false)
    private String userStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateCabinet.class)
    @JoinColumns({ //
            @JoinColumn(name = "cabinet_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateCabinet cabinet;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    public HibernateFavorite() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateFavoriteKey getKey() {
        return new HibernateFavoriteKey(cabinetLongId, userStringId);
    }

    public void setKey(HibernateFavoriteKey key) {
        if (Objects.isNull(key)) {
            this.cabinetLongId = null;
            this.userStringId = null;
        } else {
            this.cabinetLongId = key.getCabinetLongId();
            this.userStringId = key.getUserStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getCabinetLongId() {
        return cabinetLongId;
    }

    public void setCabinetLongId(Long cabinetLongId) {
        this.cabinetLongId = cabinetLongId;
    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateCabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(HibernateCabinet cabinet) {
        this.cabinet = cabinet;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "cabinetLongId = " + cabinetLongId + ", " +
                "userStringId = " + userStringId + ", " +
                "remark = " + remark + ", " +
                "cabinet = " + cabinet + ", " +
                "user = " + user + ")";
    }
}
