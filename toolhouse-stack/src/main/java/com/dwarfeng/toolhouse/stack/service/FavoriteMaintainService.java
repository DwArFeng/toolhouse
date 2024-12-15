package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;

/**
 * 收藏维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FavoriteMaintainService extends BatchCrudService<FavoriteKey, Favorite>,
        EntireLookupService<Favorite>, PresetLookupService<Favorite> {

    String CHILD_FOR_CABINET = "child_for_cabinet";
    String CHILD_FOR_USER = "child_for_user";
}
