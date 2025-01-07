package com.dwarfeng.toolhouse.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.service.TaskMaintainService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria criteria, String preset, Object[] objs) {
        switch (preset) {
            case TaskMaintainService.CHILD_FOR_SESSION:
                childForSession(criteria, objs);
                break;
            case TaskMaintainService.CREATE_DATE_DESC:
                createDesc(criteria, objs);
                break;
            case TaskMaintainService.CHILD_FOR_SESSION_CREATE_DATE_DESC:
                childForSessionCreateDateDesc(criteria, objs);
                break;
            case TaskMaintainService.CHILD_FOR_SESSION_STATUS_IN_CREATE_DATE_DESC:
                childForSessionStatusInCreateDateDesc(criteria, objs);
                break;
            case TaskMaintainService.TOOL_KEY_EQ:
                toolKeyEq(criteria, objs);
                break;
            case TaskMaintainService.USER_KEY_EQ:
                userKeyEq(criteria, objs);
                break;
            case TaskMaintainService.SHOULD_EXPIRE:
                shouldExpire(criteria, objs);
                break;
            case TaskMaintainService.SHOULD_DIE:
                shouldDie(criteria, objs);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + preset);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForSession(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sessionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sessionLongId", longIdKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void createDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            criteria.addOrder(Order.desc("createDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForSessionCreateDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sessionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sessionLongId", longIdKey.getLongId()));
            }
            criteria.addOrder(Order.desc("createDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void childForSessionStatusInCreateDateDesc(DetachedCriteria criteria, Object[] objs) {
        try {
            if (Objects.isNull(objs[0])) {
                criteria.add(Restrictions.isNull("sessionLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objs[0];
                criteria.add(Restrictions.eqOrIsNull("sessionLongId", longIdKey.getLongId()));
            }
            // 此处类型转换的安全由调用者保证。
            @SuppressWarnings("unchecked")
            Collection<Integer> statuses = (Collection<Integer>) objs[1];
            criteria.add(Restrictions.in("status", statuses));
            criteria.addOrder(Order.desc("createDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void toolKeyEq(DetachedCriteria criteria, Object[] objs) {
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
    private void userKeyEq(DetachedCriteria criteria, Object[] objs) {
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

    private void shouldExpire(DetachedCriteria criteria, Object[] objs) {
        try {
            Date currentDate = new Date();
            criteria.add(Restrictions.in("status", Collections.singletonList(Constants.TASK_STATUS_CREATED)));
            criteria.add(Restrictions.le("shouldExpireDate", currentDate));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }

    private void shouldDie(DetachedCriteria criteria, Object[] objs) {
        try {
            Date currentDate = new Date();
            criteria.add(Restrictions.in("status", Collections.singletonList(Constants.TASK_STATUS_PROCESSING)));
            criteria.add(Restrictions.le("shouldDieDate", currentDate));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objs));
        }
    }
}
