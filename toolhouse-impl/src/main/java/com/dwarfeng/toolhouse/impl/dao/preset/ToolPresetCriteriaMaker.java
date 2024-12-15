package com.dwarfeng.toolhouse.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ToolPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case ToolMaintainService.CHILD_FOR_FOLDER:
                childForFolder(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET:
                childForCabinet(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET_ROOT:
                childForCabinetRoot(detachedCriteria, objects);
                break;
            case ToolMaintainService.NAME_LIKE:
                nameLike(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_FOLDER_NAME_ASC:
                childForFolderNameAsc(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_FOLDER_NAME_DESC:
                childForFolderNameDesc(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET_NAME_LIKE:
                childForCabinetNameLike(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET_NAME_ASC:
                childForCabinetNameAsc(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET_NAME_DESC:
                childForCabinetNameDesc(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET_ROOT_NAME_ASC:
                childForCabinetRootNameAsc(detachedCriteria, objects);
                break;
            case ToolMaintainService.CHILD_FOR_CABINET_ROOT_NAME_DESC:
                childForCabinetRootNameDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForFolder(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("folderLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("folderLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
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

    private void childForCabinetRoot(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.add(Restrictions.isNull("folderLongId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
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

    @SuppressWarnings("DuplicatedCode")
    private void childForCabinetNameLike(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            String pattern = (String) objects[1];
            detachedCriteria.add(Restrictions.like("name", pattern, MatchMode.ANYWHERE));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForFolderNameAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("folderLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("folderLongId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.asc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForFolderNameDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("folderLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("folderLongId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.desc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForCabinetNameAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForCabinetNameDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForCabinetRootNameAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.add(Restrictions.isNull("folderLongId"));
            detachedCriteria.addOrder(Order.asc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForCabinetRootNameDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("cabinetLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("cabinetLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.add(Restrictions.isNull("folderLongId"));
            detachedCriteria.addOrder(Order.desc("name"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
