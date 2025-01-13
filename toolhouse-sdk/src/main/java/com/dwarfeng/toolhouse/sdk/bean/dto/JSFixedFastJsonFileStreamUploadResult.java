package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileStreamUploadResult;

import java.util.Objects;

/**
 * JSFixed FastJson 文件流上传结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonFileStreamUploadResult implements Dto {

    private static final long serialVersionUID = 3504662605357895397L;

    public static JSFixedFastJsonFileStreamUploadResult of(FileStreamUploadResult fileStreamUploadResult) {
        if (Objects.isNull(fileStreamUploadResult)) {
            return null;
        } else {
            return new JSFixedFastJsonFileStreamUploadResult(
                    JSFixedFastJsonLongIdKey.of(fileStreamUploadResult.getFileInfoKey())
            );
        }
    }

    @JSONField(name = "file_info_key", ordinal = 1)
    private JSFixedFastJsonLongIdKey fileInfoKey;

    public JSFixedFastJsonFileStreamUploadResult() {
    }

    public JSFixedFastJsonFileStreamUploadResult(JSFixedFastJsonLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    public JSFixedFastJsonLongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(JSFixedFastJsonLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonFileStreamUploadResult{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
