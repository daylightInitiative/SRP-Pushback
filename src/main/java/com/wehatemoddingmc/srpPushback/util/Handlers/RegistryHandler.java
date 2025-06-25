package com.wehatemoddingmc.srpPushback.util.Handlers;

import com.wehatemoddingmc.srpPushback.Main;
//import com.wehatemoddingmc.srpPushback.world.gen.WorldGenCustomStructures;
import com.wehatemoddingmc.srpPushback.util.Handlers.PacketHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegistryHandler {

    public static void preInitRegistries() {

        //GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);

        EntityInit.registerEntities();
        ItemInit.registerItems();
        //RenderHandler.registerEntityRenders();
        EventHandler.registerEvents();

    }

    public static void initRegistries() {

        //SoundsHandler.registerSounds();
        //NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        PacketHandler.registerMessages();
    }
}
