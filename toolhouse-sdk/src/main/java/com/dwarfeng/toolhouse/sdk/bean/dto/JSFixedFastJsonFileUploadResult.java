package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileUploadResult;

import java.util.Objects;

/**
 * JSFixed FastJson 文件上传结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonFileUploadResult implements Dto {

    private static final long serialVersionUID = 787689652072418644L;

    public static JSFixedFastJsonFileUploadResult of(FileUploadResult fileUploadResult) {
        if (Objects.isNull(fileUploadResult)) {
            return null;
        } else {
            return new JSFixedFastJsonFileUploadResult(
                    JSFixedFastJsonLongIdKey.of(fileUploadResult.getFileInfoKey())
            );
        }
    }

    private JSFixedFastJsonLongIdKey fileInfoKey;

    public JSFixedFastJsonFileUploadResult() {
    }

    public JSFixedFastJsonFileUploadResult(JSFixedFastJsonLongIdKey fileInfoKey) {
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
        return "JSFixedFastJsonFileUploadResult{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
