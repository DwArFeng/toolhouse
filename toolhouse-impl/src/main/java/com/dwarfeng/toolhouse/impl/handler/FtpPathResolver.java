package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * FTP 路径解析器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class FtpPathResolver {

    public static final char ROOT_PATH_STRING_SEPARATOR = '/';
    public static final String ROOT_PATH_STRING_SEPARATOR_STRING = String.valueOf(ROOT_PATH_STRING_SEPARATOR);

    public static final String[] RELATIVE_PATH_FILE = new String[]{"file"};

    public static String[] parseRootPath(@Nullable String rootPathString) {
        // 去除前后的空格以及分隔符。
        rootPathString = StringUtils.trim(rootPathString);
        // 值校验：如果以分隔符开头或结尾，则抛出异常。
        if (invalidRootPath(rootPathString)) {
            throw new IllegalArgumentException("非法的根路径: " + rootPathString);
        }
        // 特殊值处理。
        if (StringUtils.isEmpty(rootPathString)) {
            return new String[0];
        }
        // 分割字符串并返回。
        return StringUtils.split(rootPathString, ROOT_PATH_STRING_SEPARATOR);
    }

    // 为了代码的可读性，此处不做简化。
    @SuppressWarnings("RedundantIfStatement")
    private static boolean invalidRootPath(@Nullable String rootPathString) {
        if (Strings.CS.startsWith(rootPathString, ROOT_PATH_STRING_SEPARATOR_STRING)) {
            return true;
        }
        if (Strings.CS.endsWith(rootPathString, ROOT_PATH_STRING_SEPARATOR_STRING)) {
            return true;
        }
        return false;
    }

    @Value("#{T(com.dwarfeng.toolhouse.impl.handler.FtpPathResolver).parseRootPath('${ftp.root_path}')}")
    private String[] rootPath;

    /**
     * 解析路径。
     *
     * <p>
     * 该方法会在传入的相对路径前方拼接根路径，然后返回拼接后的路径。
     *
     * @param relativePath 相对路径。
     * @return 解析后的路径。
     */
    public String[] resolvePath(@Nonnull String[] relativePath) {
        String[] path = new String[rootPath.length + relativePath.length];
        System.arraycopy(rootPath, 0, path, 0, rootPath.length);
        System.arraycopy(relativePath, 0, path, rootPath.length, relativePath.length);
        return path;
    }

    /**
     * 解析文件名。
     *
     * @param fileKey 文件的键。
     * @return 解析后的文件名。
     */
    public String resolveFileName(@Nonnull LongIdKey fileKey) {
        return Long.toString(fileKey.getLongId());
    }
}
