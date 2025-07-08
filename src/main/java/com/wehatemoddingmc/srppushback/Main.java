package com.wehatemoddingmc.srppushback;

import com.wehatemoddingmc.srppushback.util.Handlers.PacketHandler;
import com.wehatemoddingmc.srppushback.util.Handlers.RegistryHandler;
import com.wehatemoddingmc.srppushback.proxy.CommonProxy;
import com.wehatemoddingmc.srppushback.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid= Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {

	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);

	@Mod.Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	public Main() {

	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		RegistryHandler.preInitRegistries();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		RegistryHandler.initRegistries(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
}

