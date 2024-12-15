package com.dwarfeng.toolhouse.impl.bean;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.impl.bean.entity.*;
import com.dwarfeng.toolhouse.impl.bean.key.HibernateFavoriteKey;
import com.dwarfeng.toolhouse.impl.bean.key.HibernatePocaKey;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernatePocaKey pocaKeyToHibernate(PocaKey pocaKey);

    @InheritInverseConfiguration
    PocaKey pocaKeyFromHibernate(HibernatePocaKey hibernatePocaKey);

    HibernateFavoriteKey favoriteKeyToHibernate(FavoriteKey favoriteKey);

    @InheritInverseConfiguration
    FavoriteKey favoriteKeyFromHibernate(HibernateFavoriteKey hibernateFavoriteKey);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernatePoca pocaToHibernate(Poca poca);

    @InheritInverseConfiguration
    Poca pocaFromHibernate(HibernatePoca hibernatePoca);

    @Mapping(target = "tools", ignore = true)
    @Mapping(target = "pocas", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "folders", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    HibernateCabinet cabinetToHibernate(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet cabinetFromHibernate(HibernateCabinet hibernateCabinet);

    @Mapping(target = "tools", ignore = true)
    @Mapping(target = "parentLongId", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernateFolder folderToHibernate(Folder folder);

    @InheritInverseConfiguration
    Folder folderFromHibernate(HibernateFolder hibernateFolder);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "folderLongId", ignore = true)
    @Mapping(target = "folder", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernateTool toolToHibernate(Tool tool);

    @InheritInverseConfiguration
    Tool toolFromHibernate(HibernateTool hibernateTool);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "pocas", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernateFavorite favoriteToHibernate(Favorite favorite);

    @InheritInverseConfiguration
    Favorite favoriteFromHibernate(HibernateFavorite hibernateFavorite);
}
