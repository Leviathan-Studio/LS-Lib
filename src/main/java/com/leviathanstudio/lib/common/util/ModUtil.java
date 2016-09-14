package com.leviathanstudio.lib.common.util;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public class ModUtil
{
    public static String getCurrentModid()
    {
        return getCurrentModContainer().getModId();
    }

    public static ModContainer getCurrentModContainer()
    {
        return Loader.instance().activeModContainer();
    }

    public static ModContainer getModContainer(String modid)
    {
        return Loader.instance().getIndexedModList().get(modid);
    }
}
