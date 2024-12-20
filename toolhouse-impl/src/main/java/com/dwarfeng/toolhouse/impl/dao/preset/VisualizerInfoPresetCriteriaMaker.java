package com.dwarfeng.toolhouse.impl.dao.preset;

import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.toolhouse.stack.service.VisualizerInfoMaintainService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class VisualizerInfoPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case VisualizerInfoMaintainService.CHILD_FOR_TOOL:
                childForTool(detachedCriteria, objects);
                break;
            case VisualizerInfoMaintainService.CHILD_FOR_TOOL_VISUALIZER_ID_ASC:
                childForToolVisualizerIdAsc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForTool(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("toolLongId"));
            } else {
                LongIdKey toolKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("toolLongId", toolKey.getLongId()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForToolVisualizerIdAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("toolLongId"));
            } else {
                LongIdKey toolKey = (LongIdKey) objects[0];
                detachedCriteria.add(Restrictions.eqOrIsNull("toolLongId", toolKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.asc("visualizerStringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
