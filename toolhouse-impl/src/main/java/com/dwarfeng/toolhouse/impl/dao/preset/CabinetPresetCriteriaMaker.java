package com.dwarfeng.toolhouse.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CabinetPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case CabinetMaintainService.NAME_LIKE:
                nameLike(detachedCriteria, objects);
                break;
            case CabinetMaintainService.USER_OWNED:
                userOwned(detachedCriteria, objects);
                break;
            case CabinetMaintainService.USER_PERMITTED_WITH_CONDITION_DISPLAY:
                userPermittedWithConditionDisplay(detachedCriteria, objects);
                break;
            case CabinetMaintainService.USER_OWNED_WITH_CONDITION_DISPLAY:
                userOwnedWithConditionDisplay(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void nameLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            String pattern = (String) objects[0];
            detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void userOwned(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            StringIdKey userKey = (StringIdKey) objects[0];
            detachedCriteria.createAlias("pocas", "p");
            detachedCriteria.add(Restrictions.eq("p.userStringId", userKey.getStringId()));
            detachedCriteria.add(Restrictions.eq("p.permissionLevel", Constants.PERMISSION_LEVEL_OWNER));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void userPermittedWithConditionDisplay(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            StringIdKey userKey = (StringIdKey) objects[0];
            detachedCriteria.createAlias("pocas", "p");
            detachedCriteria.add(Restrictions.eq("p.userStringId", userKey.getStringId()));
            String pattern = (String) objects[1];
            if (StringUtils.isNotBlank(pattern)) {
                detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
            }
            boolean onlyFavorite = (boolean) objects[2];
            if (onlyFavorite) {
                detachedCriteria.createAlias("favorites", "f");
                detachedCriteria.add(Restrictions.eq("f.userStringId", userKey.getStringId()));
            }
            detachedCriteria.addOrder(Order.asc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void userOwnedWithConditionDisplay(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            StringIdKey userKey = (StringIdKey) objects[0];
            detachedCriteria.createAlias("pocas", "p");
            detachedCriteria.add(Restrictions.eq("p.userStringId", userKey.getStringId()));
            detachedCriteria.add(Restrictions.eq("p.permissionLevel", Constants.PERMISSION_LEVEL_OWNER));
            String pattern = (String) objects[1];
            if (StringUtils.isNotBlank(pattern)) {
                detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
            }
            boolean onlyFavorite = (boolean) objects[2];
            if (onlyFavorite) {
                detachedCriteria.createAlias("favorites", "f");
                detachedCriteria.add(Restrictions.eq("f.userStringId", userKey.getStringId()));
            }
            detachedCriteria.addOrder(Order.asc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
