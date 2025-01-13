package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileUploadResult;

import java.util.Objects;

/**
 * FastJson 文件上传结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonFileUploadResult implements Dto {

    private static final long serialVersionUID = 6101259945678815712L;

    public static FastJsonFileUploadResult of(FileUploadResult fileUploadResult) {
        if (Objects.isNull(fileUploadResult)) {
            return null;
        } else {
            return new FastJsonFileUploadResult(
                    FastJsonLongIdKey.of(fileUploadResult.getFileInfoKey())
            );
        }
    }

    private FastJsonLongIdKey fileInfoKey;

    public FastJsonFileUploadResult() {
    }

    public FastJsonFileUploadResult(FastJsonLongIdKey fileInfoKey) {
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
        return "FastJsonFileUploadResult{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
