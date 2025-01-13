package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileStreamUploadResult;

import java.util.Objects;

/**
 * FastJson 文件流上传结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonFileStreamUploadResult implements Dto {

    private static final long serialVersionUID = -4459905877981068059L;

    public static FastJsonFileStreamUploadResult of(FileStreamUploadResult fileStreamUploadResult) {
        if (Objects.isNull(fileStreamUploadResult)) {
            return null;
        } else {
            return new FastJsonFileStreamUploadResult(
                    FastJsonLongIdKey.of(fileStreamUploadResult.getFileInfoKey())
            );
        }
    }

    @JSONField(name = "file_info_key", ordinal = 1)
    private FastJsonLongIdKey fileInfoKey;

    public FastJsonFileStreamUploadResult() {
    }

    public FastJsonFileStreamUploadResult(FastJsonLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    public FastJsonLongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(FastJsonLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String toString() {
        return "FastJsonFileStreamUploadResult{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
