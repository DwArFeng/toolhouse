package com.dwarfeng.toolhouse.impl.handler.pusher;

/**
 * 推送器适配器。
 *
 * @author DwArFeng
 * @see com.dwarfeng.toolhouse.sdk.handler.pusher.PusherAdapter
 * @since beta-1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class PusherAdapter extends com.dwarfeng.toolhouse.sdk.handler.pusher.PusherAdapter {

    public PusherAdapter() {
        super();
    }

    public PusherAdapter(String pusherType) {
        super(pusherType);
    }
}
