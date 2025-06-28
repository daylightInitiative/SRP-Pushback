package com.wehatemoddingmc.srppushback.util.Handlers;

//import com.wehatemoddingmc.srpPushback.world.gen.WorldGenCustomStructures;
import com.wehatemoddingmc.srppushback.util.Handlers.SoundsHandler;

import com.wehatemoddingmc.srppushback.events.KeybindEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    public static void preInitRegistries() {

        //GameRegistry.registerWorldGenerator(new WorldGenCustomStructures(), 0);

        EntityInit.registerEntities();
        //RenderHandler.registerEntityRenders();
        EventHandler.registerEvents();

    }

    public static void initRegistries(FMLInitializationEvent event) {

        SoundsHandler.registerSounds();
        //NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        KeybindEvents.registerKeybinds(event);
        PacketHandler.registerMessages();
    }
}
