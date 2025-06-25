package com.wehatemoddingmc.srpPushback.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.Random;

public class Reference {

    public static final String MOD_ID = "srpPushback";
    public static final String NAME = "SRP: Pushback";
    public static final String VERSION = "1.0";
    public static final String ACCEPTED_VERSION = "[1.12.2]";
    public static final String CLIENT = "com.wehatemoddingmc.srpPushback.proxy.ClientProxy";
    public static final String COMMON = "com.wehatemoddingmc.srpPushback.proxy.CommonProxy";
    public static final Random RANDOM = new Random();

    public static MinecraftServer server;


}
