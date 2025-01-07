package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.FileInfo;

/**
 * 文件信息维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FileInfoMaintainService extends BatchCrudService<LongIdKey, FileInfo>,
        EntireLookupService<FileInfo>, PresetLookupService<FileInfo> {

    String CHILD_FOR_SESSION = "child_for_session";

    String TOOL_KEY_EQ = "tool_key_eq";
    String USER_KEY_EQ = "user_key_eq";

    String CHILD_FOR_SESSION_ORIGIN_NAME_ASC = "child_for_session_origin_name_asc";
    String CHILD_FOR_SESSION_ORIGIN_NAME_DESC = "child_for_session_origin_name_desc";
    String CHILD_FOR_SESSION_CREATED_DATE_ASC = "child_for_session_created_date_asc";
    String CHILD_FOR_SESSION_CREATED_DATE_DESC = "child_for_session_created_date_desc";
    String CHILD_FOR_SESSION_MODIFIED_DATE_ASC = "child_for_session_modified_date_asc";
    String CHILD_FOR_SESSION_MODIFIED_DATE_DESC = "child_for_session_modified_date_desc";
    String CHILD_FOR_SESSION_INSPECTED_DATE_ASC = "child_for_session_inspected_date_asc";
    String CHILD_FOR_SESSION_INSPECTED_DATE_DESC = "child_for_session_inspected_date_desc";
}
