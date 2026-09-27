package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.impl.bean.key.HibernatePocaKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernatePocaKey.class)
@Table(name = "tbl_poca")
public class HibernatePoca implements Bean {

    private static final long serialVersionUID = -5121871210298719604L;

    // region 主键

    @Id
    @Column(name = "cabinet_id", nullable = false)
    private Long cabinetLongId;

    @Id
    @Column(name = "user_id", length = Constraints.LENGTH_USER, nullable = false)
    private String userStringId;

    // endregion

    // region 主属性字段

    @Column(name = "permission_level")
    private int permissionLevel;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // endregion

    // region 多对一

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

    // endregion

    public HibernatePoca() {
    }

    // region 映射用属性区

    public HibernatePocaKey getKey() {
        return new HibernatePocaKey(cabinetLongId, userStringId);
    }

    public void setKey(HibernatePocaKey key) {
        if (Objects.isNull(key)) {
            this.cabinetLongId = null;
            this.userStringId = null;
        } else {
            this.cabinetLongId = key.getCabinetLongId();
            this.userStringId = key.getUserStringId();
        }
    }

    // endregion

    // region 常规属性区

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

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
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

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "cabinetLongId = " + cabinetLongId + ", " +
                "userStringId = " + userStringId + ", " +
                "permissionLevel = " + permissionLevel + ", " +
                "remark = " + remark + ", " +
                "cabinet = " + cabinet + ", " +
                "user = " + user + ")";
    }
}
