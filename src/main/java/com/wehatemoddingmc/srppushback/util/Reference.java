package com.wehatemoddingmc.srppushback.util;

import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;

import java.util.Random;

public class Reference {

    public static final String MOD_ID = "srppushback";
    public static final String NAME = "SRP: Pushback";
    public static final String VERSION = "1.0";
    public static final String ACCEPTED_VERSION = "[1.12.2]";
    public static final String CLIENT = "com.wehatemoddingmc.srppushback.proxy.ClientProxy";
    public static final String COMMON = "com.wehatemoddingmc.srppushback.proxy.CommonProxy";
    public static final Random RANDOM = new Random();

    public static MinecraftServer server;

    public static Item setItemName(Item parItem, String parItemName)
    {
        parItem.setRegistryName(parItemName);
        parItem.setTranslationKey(parItemName);
        return parItem;
    }
}
