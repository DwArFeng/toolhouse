package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.toolhouse.stack.bean.entity.User;

/**
 * 用户维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface UserMaintainService extends BatchCrudService<StringIdKey, User> {
}
