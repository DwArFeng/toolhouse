package com.dwarfeng.toolhouse.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class SessionPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case SessionMaintainService.CHILD_FOR_TOOL:
                childForTool(criteria, objs);
                break;
            case SessionMaintainService.CHILD_FOR_USER:
                childForUser(criteria, objs);
                break;
            case SessionMaintainService.CREATED_DATE_DESC:
                createdDateDesc(criteria, objs);
                break;
            case SessionMaintainService.CHILD_FOR_TOOL_CREATED_DATE_DESC:
                childForToolCreatedDateDesc(criteria, objs);
                break;
            case SessionMaintainService.CHILD_FOR_USER_CREATED_DATE_DESC:
                childForUserCreatedDateDesc(criteria, objs);
                break;
            case SessionMaintainService.CHILD_FOR_TOOL_CHILD_FOR_USER_CREATED_DATE_DESC:
                childForToolChildForUserCreatedDateDesc(criteria, objs);
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForTool(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("toolLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("toolLongId", longIdKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUser(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void createdDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            criteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForToolCreatedDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("toolLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("toolLongId", longIdKey.getLongId()));
            }
            criteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForUserCreatedDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId()));
            }
            criteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForToolChildForUserCreatedDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("toolLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("toolLongId", longIdKey.getLongId()));
            }
            if (Objects.isNull(objs[1])) {
                criteria.add(Restrictions.isNull("userLongId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objs[1];
                criteria.add(Restrictions.eqOrIsNull("userLongId", stringIdKey.getStringId()));
            }
            criteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}
