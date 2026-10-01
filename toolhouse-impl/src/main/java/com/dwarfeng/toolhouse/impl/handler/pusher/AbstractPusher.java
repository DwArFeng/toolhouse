package com.dwarfeng.toolhouse.impl.handler.pusher;

/**
 * 抽象推送器。
 *
 * @author DwArFeng
 * @see com.dwarfeng.toolhouse.sdk.handler.pusher.AbstractPusher
 * @since beta-1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractPusher extends com.dwarfeng.toolhouse.sdk.handler.pusher.AbstractPusher {

    public AbstractPusher() {
    }

    public AbstractPusher(String pusherType) {
        super(pusherType);
    }
}
