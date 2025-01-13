package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileSystemDownloadInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 文件系统下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputFileSystemDownloadInfo implements Dto {

    private static final long serialVersionUID = 2995960937965568871L;

    public static FileSystemDownloadInfo toStackBean(WebInputFileSystemDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new FileSystemDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.fileInfoKey)
            );
        }
    }

    @JSONField(name = "file_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey fileInfoKey;

    public WebInputFileSystemDownloadInfo() {
    }

    public WebInputLongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(WebInputLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputFileSystemDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
