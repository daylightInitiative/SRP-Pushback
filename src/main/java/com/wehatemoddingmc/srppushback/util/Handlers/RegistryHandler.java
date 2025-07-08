package com.wehatemoddingmc.srppushback.util.Handlers;

//import com.wehatemoddingmc.srpPushback.world.gen.WorldGenCustomStructures;

import com.wehatemoddingmc.srppushback.Main;
import com.wehatemoddingmc.srppushback.events.OnKeybindEvents;
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

        Main.logger.info("Initializing registries...");

        LootTableHandler.init();
        SoundsHandler.registerSounds();
        //NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
        OnKeybindEvents.registerKeybinds(event);
        PacketHandler.init();
    }

}
