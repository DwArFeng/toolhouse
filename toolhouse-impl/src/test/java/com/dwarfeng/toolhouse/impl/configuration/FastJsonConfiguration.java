package com.dwarfeng.toolhouse.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        // 实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonUser.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonPoca.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonCabinet.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFolder.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTool.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFavorite.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVisualizerInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVisualizerSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonExecutorInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonExecutorSupport.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonSession.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonVariable.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonTask.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonInputItem.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonOutputItem.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonFileInfo.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
