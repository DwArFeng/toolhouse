package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.toolhouse.stack.bean.entity.User;

/**
 * 用户数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface UserDao extends BatchBaseDao<StringIdKey, User> {
}
