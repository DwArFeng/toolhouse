package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.FileManualDownloadInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 文件手动下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputFileManualDownloadInfo implements Dto {

    private static final long serialVersionUID = -3169917406953077320L;

    public static FileManualDownloadInfo toStackBean(WebInputFileManualDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new FileManualDownloadInfo(
                    WebInputLongIdKey.toStackBean(webInput.getFileInfoKey())
            );
        }
    }

    @JSONField(name = "file_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey fileInfoKey;

    public WebInputFileManualDownloadInfo() {
    }

    public WebInputLongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(WebInputLongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputFileManualDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
