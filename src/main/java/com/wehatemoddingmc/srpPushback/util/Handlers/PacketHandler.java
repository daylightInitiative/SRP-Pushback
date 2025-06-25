package com.wehatemoddingmc.srpPushback.util.Handlers;

//import com.wehatemoddingmc.srpPushback.Packages.*;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

    public static SimpleNetworkWrapper INSTANCE;

    private static int ID = 0;
    private static int nextID() {
        return ID++;
    }


    public static void registerMessages() {

        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("srpPushbackChannel");

//        INSTANCE.registerMessage(ChangeSkin.Handler.class, ChangeSkin.class, nextID(), Side.SERVER);
//        INSTANCE.registerMessage(ChangeSkin.Handler.class, ChangeSkin.class, nextID(), Side.CLIENT);
    }

}
