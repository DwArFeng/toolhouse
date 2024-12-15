package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;

/**
 * 工具柜维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CabinetMaintainService extends BatchCrudService<LongIdKey, Cabinet>,
        EntireLookupService<Cabinet>, PresetLookupService<Cabinet> {

    String NAME_LIKE = "name_like";

    /**
     * 获取工具柜。
     *
     * <p>
     * 返回指定用户拥有的工具柜。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>StringIdKey 用户的键。</li>
     * </ol>
     *
     * @since beta-1.0.0
     */
    String USER_OWNED = "user_owned";

    /**
     * 获取展示用的工具柜。
     *
     * <p>
     * 返回指定用户有权限（任何权限均可）的工具柜，且满足查询条件。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>StringIdKey 用户的键。</li>
     *     <li>String 工具柜名称的模糊匹配字符串，如果值为空（blank)，则不参与查询。</li>
     *     <li>boolean 是否只查询收藏的工具柜。</li>
     * </ol>
     * 返回的数据按照名称升序排列。
     *
     * @since beta-1.0.0
     */
    String USER_PERMITTED_WITH_CONDITION_DISPLAY = "user_permitted_with_condition_display";

    /**
     * 获取展示用的工具柜。
     *
     * <p>
     * 返回指定用户拥有的工具柜，且满足查询条件。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>StringIdKey 用户的键。</li>
     *     <li>String 工具柜名称的模糊匹配字符串，如果值为空（blank)，则不参与查询。</li>
     *     <li>boolean 是否只查询收藏的工具柜。</li>
     * </ol>
     * 返回的数据按照名称升序排列。
     *
     * @since beta-1.0.0
     */
    String USER_OWNED_WITH_CONDITION_DISPLAY = "user_owned_name_like_display";
}
