package com.leviathanstudio.lib.common.server.command;

import com.leviathanstudio.lib.common.util.MinecraftUtil;

public class CommandsHelper
{
    /**
     * Get all the player on a server
     * 
     * @return The player list
     */
    public static String[] getPlayers()
    {
        return MinecraftUtil.getServer().getAllUsernames();
    }
}
