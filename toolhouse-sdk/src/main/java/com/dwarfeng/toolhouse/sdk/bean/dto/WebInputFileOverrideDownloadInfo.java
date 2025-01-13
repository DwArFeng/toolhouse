package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileOverrideDownloadInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 文件超控下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputFileOverrideDownloadInfo implements Dto {

    private static final long serialVersionUID = 2019847689697261439L;

    public static FileOverrideDownloadInfo toStackBean(WebInputFileOverrideDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new FileOverrideDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getFileInfoKey())
            );
        }
    }

    @JSONField(name = "file_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey fileInfoKey;

    public WebInputFileOverrideDownloadInfo() {
    }

    public WebInputLongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(WebInputLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputFileOverrideDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
