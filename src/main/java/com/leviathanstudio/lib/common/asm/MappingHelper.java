package com.leviathanstudio.lib.common.asm;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraftforge.classloading.FMLForgePlugin;

public class MappingHelper
{
    private static final Map<String, String> MAPPED     = Maps.newHashMap();
    private static final Map<String, String> MCP        = Maps.newHashMap();
    private static final Map<String, String> OBFUSCATED = Maps.newHashMap();

    /**
     * Add a new entry to the system
     * 
     * @param key
     *            The entry name
     * @param obfuscated
     *            The obfuscated name
     * @param mapped
     *            The mapped name
     */
    public static void addName(String key, String obfuscated, String mapped)
    {
        addName(key, obfuscated, "", mapped);
    }

    /**
     * Add a new entry to the system
     * 
     * @param key
     *            The entry name
     * @param obfuscated
     *            The obfuscated name
     * @param mcp
     *            The MCP name
     * @param mapped
     *            The mapped name
     */
    public static void addName(String key, String obfuscated, String mcp, String mapped)
    {
        if (!hasKey(key))
        {
            OBFUSCATED.put(key, obfuscated.trim());
            MCP.put(key, mcp.trim());
            MAPPED.put(key, mapped.trim().replace('.', '/'));
        }
    }

    /**
     * Get all the name for a given entry
     * 
     * @param key
     *            The entry name
     * @return The names for an entry or null
     */
    public static @Nullable String[] getNames(String key)
    {
        if (hasKey(key))
            return new String[] { OBFUSCATED.get(key), MCP.get(key), MAPPED.get(key) };
        return null;
    }

    /**
     * Get the obfuscated name for a given entry
     * 
     * @param key
     *            The entry name
     * @return The obfuscated name for an entry or null
     */
    public static @Nullable String getObfuscatedName(String key)
    {
        if (hasKey(key))
        {
            return OBFUSCATED.get(key);
        }
        return null;
    }

    /**
     * Get the MCP name for a given entry
     * 
     * @param key
     *            The entry name
     * @return The MCP name for an entry or null
     */
    public static @Nullable String getMCPName(String key)
    {
        if (hasKey(key))
        {
            return MCP.get(key);
        }
        return null;
    }

    /**
     * Get the mapped name for a given entry
     * 
     * @param key
     *            The entry name
     * @return The mapped name for an entry or null
     */
    public static @Nullable String getMappedName(String key)
    {
        if (hasKey(key))
        {
            return MAPPED.get(key);
        }
        return null;
    }

    /**
     * Get the name for a given entry, the obfuscated one of the code is
     * obfuscated or the mapped one
     * 
     * @param key
     *            The entry name
     * @return The name for an entry or null
     */
    public static @Nullable String getName(String key)
    {
        return isObfuscated() ? getObfuscatedName(key) : getMappedName(key);
    }

    /**
     * Test if an entry exists
     * 
     * @param key
     *            The entry name
     * @return True if entry exists, false else
     */
    public static boolean hasKey(String key)
    {
        return key != null && MAPPED.containsKey(key);
    }

    public static boolean isObfuscated()
    {
        return FMLForgePlugin.RUNTIME_DEOBF;
    }

    public static void init()
    {
        addName("net.minecraft.world.World", "aid", "net/minecraft/world/World");
        addName("net.minecraft.item.ItemStack", "adz", "net/minecraft/item/ItemStack");
        addName("net.minecraft.inventory.InventoryCrafting", "abl", "net/minecraft/inventory/InventoryCrafting");
        addName("net.minecraft.inventory.ContainerPlayer", "abu", "net/minecraft/inventory/ContainerPlayer");
        addName("net.minecraft.inventory.ContainerWorkbench", "abm", "net/minecraft/inventory/ContainerWorkbench");
        addName("net.minecraft.inventory.IInventory", "ql", "net/minecraft/inventory/IInventory");
        addName("net.minecraft.entity.player.EntityPlayer", "zs", "net/minecraft/entity/player/EntityPlayer");
        addName("net.minecraft.entity.player.InventoryPlayer", "zr", "net/minecraft/entity/player/InventoryPlayer");
        addName("net.minecraft.util.math.BlockPos", "cm", "net/minecraft/util/math/BlockPos");

        addName("net.minecraft.inventory.Container.onCraftMatrixChanged", "a", "func_75130_a", "onCraftMatrixChanged");

        addName("net.minecraft.inventory.ContainerPlayer.craftMatrix", "a", "field_75181_e", "craftMatrix");
        addName("net.minecraft.inventory.ContainerPlayer.thePlayer", "i", "field_82862_h", "thePlayer");
        addName("net.minecraft.inventory.ContainerPlayer.craftResult ", "f", "field_75179_f", "craftResult");
        addName("net.minecraft.inventory.ContainerWorkbench.worldObj", "g", "field_75161_g", "worldObj");
        addName("net.minecraft.inventory.ContainerWorkbench.craftMatrix", "a", "field_75162_e", "craftMatrix");
        addName("net.minecraft.inventory.ContainerWorkbench.craftResult ", "f", "field_75160_f", "craftResult");
        addName("net.minecraft.entity.player.InventoryPlayer.player", "e", "field_70458_d", "player");
        addName("net.minecraft.world.GameRules.theGameRules", "a", "field_82771_a", "theGameRules");
        addName("net.minecraft.entity.Entity.worldObj", "l", "field_70170_p", "worldObj");
        addName("net.minecraft.client.Minecraft.defaultResourcePacks", "aB", "field_110449_ao", "defaultResourcePacks");
    }

}
