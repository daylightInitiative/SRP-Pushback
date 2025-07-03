package com.wehatemoddingmc.srppushback.util.Handlers;

//import com.wehatemoddingmc.srpPushback.events.*;

import com.wehatemoddingmc.srppushback.events.OnRespawnEvent;
import net.minecraftforge.common.MinecraftForge;

public class EventHandler {


    static public void registerEvents() {

        // register random spawn event
        MinecraftForge.EVENT_BUS.register(new OnRespawnEvent());
    }
}
