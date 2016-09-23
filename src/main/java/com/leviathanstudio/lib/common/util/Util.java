package com.leviathanstudio.lib.common.util;

import net.minecraft.client.resources.I18n;

public class Util
{
    public static final boolean DEBUG = isDebug();

    private static boolean isDebug()
    {
        if (MinecraftUtil.getSide().isServer())
            return false;
        else if (MinecraftUtil.getClientDir().getPath().equals("."))
            return true;
        return false;
    }

    public static String translate(String translation)
    {
        return I18n.format(translation);
    }

    public static String translate(String translation, Object... params)
    {
        return I18n.format(translation, params);
    }

    public static String dotName(String a, String b)
    {
        return a + "." + b;
    }

    public static String underscoreName(String a, String b)
    {
        return a + "_" + b;
    }
}
