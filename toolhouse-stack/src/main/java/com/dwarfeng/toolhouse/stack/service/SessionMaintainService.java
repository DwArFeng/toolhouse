package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;

/**
 * 会话维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SessionMaintainService extends BatchCrudService<LongIdKey, Session>,
        EntireLookupService<Session>, PresetLookupService<Session> {

    String CHILD_FOR_TOOL = "child_for_tool";
    String CHILD_FOR_USER = "child_for_user";
    String CREATED_DATE_DESC = "created_date_desc";
    String CHILD_FOR_TOOL_CREATED_DATE_DESC = "child_for_tool_created_date_desc";
    String CHILD_FOR_USER_CREATED_DATE_DESC = "child_for_user_created_date_desc";
    String CHILD_FOR_TOOL_CHILD_FOR_USER_CREATED_DATE_DESC = "child_for_tool_child_for_user_created_date_desc";
}
