package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;

/**
 * 任务维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface TaskMaintainService extends BatchCrudService<LongIdKey, Task>,
        EntireLookupService<Task>, PresetLookupService<Task> {

    String CHILD_FOR_SESSION = "child_for_session";

    String CREATE_DATE_DESC = "create_date_desc";
    String CHILD_FOR_SESSION_CREATE_DATE_DESC = "child_for_session_create_date_desc";
    String CHILD_FOR_SESSION_STATUS_IN_CREATE_DATE_DESC = "child_for_session_status_in_create_date_desc";

    String TOOL_KEY_EQ = "tool_key_eq";
    String USER_KEY_EQ = "user_key_eq";

    String SHOULD_EXPIRE = "should_expire";
    String SHOULD_DIE = "should_die";
}
