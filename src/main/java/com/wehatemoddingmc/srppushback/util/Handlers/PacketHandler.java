package com.wehatemoddingmc.srppushback.util.Handlers;

//import com.wehatemoddingmc.srpPushback.Packages.*;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class PacketHandler {
    public static SimpleNetworkWrapper INSTANCE;
    private static int ID = 0;

    private static int nextID() {
        return ID++;
    }

    public static void init() {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("srpPushbackChannel");
//
//        INSTANCE.registerMessage(MyMessage.Handler.class, MyMessage.class, nextID(), Side.SERVER);
//        INSTANCE.registerMessage(MyMessage.Handler.class, MyMessage.class, nextID(), Side.CLIENT);
    }
}
