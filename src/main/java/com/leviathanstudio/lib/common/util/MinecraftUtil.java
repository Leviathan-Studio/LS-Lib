package com.leviathanstudio.lib.common.util;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.server.FMLServerHandler;

public class MinecraftUtil
{

    public static Side getSide()
    {
        return FMLCommonHandler.instance().getSide();
    }

    public static Side getEffectiveSide()
    {
        return FMLCommonHandler.instance().getEffectiveSide();
    }

    @SideOnly(Side.CLIENT)
    public static Minecraft getClient()
    {
        return FMLClientHandler.instance().getClient();// Minecraft.getMinecraft();
    }

    public static MinecraftServer getServer()
    {
        return FMLCommonHandler.instance().getMinecraftServerInstance();
    }

    public static File getMcDir()
    {
        switch (getSide())
        {
            case CLIENT:
                return getClientDir();
            case SERVER:
                return getServerDir();
        }
        return null;
    }

    @SideOnly(Side.CLIENT)
    public static File getClientDir()
    {
        return FMLClientHandler.instance().getClient().mcDataDir;
    }

    @SideOnly(Side.SERVER)
    public static File getServerDir()
    {
        return FMLServerHandler.instance().getServer().getDataDirectory();
    }

    public static File getConfigDir()
    {
        return new File(getMcDir(), "config");
    }
}
