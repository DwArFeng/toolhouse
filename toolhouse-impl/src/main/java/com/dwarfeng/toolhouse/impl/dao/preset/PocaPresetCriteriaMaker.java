package com.dwarfeng.toolhouse.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.service.PocaMaintainService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PocaPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case PocaMaintainService.CHILD_FOR_CABINET:
                childForCabinet(detachedCriteria, objects);
                break;
            case PocaMaintainService.CHILD_FOR_USER:
                childForUser(detachedCriteria, objects);
                break;
            case PocaMaintainService.CHILD_FOR_CABINET_PERMISSION_LEVEL_EQUALS:
                childForCabinetPermissionLevelEquals(detachedCriteria, objects);
                break;
            case PocaMaintainService.CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS:
                childForUserPermissionLevelEquals(detachedCriteria, objects);
                break;
            case PocaMaintainService.CHILD_FOR_CABINET_SET:
                childForCabinetSet(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForCabinet(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForUser(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForCabinetPermissionLevelEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            int permissionLevel = (int) objects[1];
            detachedCriteria.add(Restrictions.eq("permissionLevel", permissionLevel));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForUserPermissionLevelEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("userStringId"));
            } else {
                StringIdKey stringIdKey = (StringIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("userStringId", stringIdKey.getStringId())
                );
            }
            int permissionLevel = (int) objects[1];
            detachedCriteria.add(Restrictions.eq("permissionLevel", permissionLevel));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForCabinetSet(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            Collection<LongIdKey> cabinetKeys;
            if (Objects.isNull(objects[0])) {
                cabinetKeys = Collections.emptySet();
            } else {
                @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked"})
                Collection<LongIdKey> dejaVu = (Collection<LongIdKey>) objects[0];
                cabinetKeys = dejaVu;
            }
            Collection<Long> cabinetLongIds = cabinetKeys.stream().map(LongIdKey::getLongId)
                    .collect(Collectors.toSet());
            detachedCriteria.add(Restrictions.in("cabinetLongId", cabinetLongIds));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
