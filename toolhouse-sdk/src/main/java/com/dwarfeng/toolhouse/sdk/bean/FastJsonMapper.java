package com.dwarfeng.toolhouse.sdk.bean;

import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonFavoriteKey;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonPocaKey;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonPocaKey pocaKeyToFastJson(PocaKey pocaKey);

    @InheritInverseConfiguration
    PocaKey pocaKeyFromFastJson(FastJsonPocaKey fastJsonPocaKey);

    FastJsonFavoriteKey favoriteKeyToFastJson(FavoriteKey favoriteKey);

    @InheritInverseConfiguration
    FavoriteKey favoriteKeyFromFastJson(FastJsonFavoriteKey fastJsonFavoriteKey);

    FastJsonPoca pocaToFastJson(Poca poca);

    @InheritInverseConfiguration
    Poca pocaFromFastJson(FastJsonPoca fastJsonPoca);

    FastJsonCabinet cabinetToFastJson(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet cabinetFromFastJson(FastJsonCabinet fastJsonCabinet);

    FastJsonFolder folderToFastJson(Folder folder);

    @InheritInverseConfiguration
    Folder folderFromFastJson(FastJsonFolder fastJsonFolder);

    FastJsonTool toolToFastJson(Tool tool);

    @InheritInverseConfiguration
    Tool toolFromFastJson(FastJsonTool fastJsonTool);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);

    FastJsonFavorite favoriteToFastJson(Favorite favorite);

    @InheritInverseConfiguration
    Favorite favoriteFromFastJson(FastJsonFavorite fastJsonFavorite);
}
