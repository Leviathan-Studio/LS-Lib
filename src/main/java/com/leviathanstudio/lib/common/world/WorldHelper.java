package com.leviathanstudio.lib.common.world;

import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class WorldHelper
{
    /**
     * Return the block at the coordinates
     * 
     * @param world
     *            the world instance
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param z
     *            the z coordinate
     * @return block at the given position
     */
    public static Block getBlock(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    /**
     * Drop all the ItemStack in the inventory at the position
     * 
     * @param world
     *            The World Instance
     * @param pos
     *            The Position
     * @param inventory
     *            The inventory
     */
    public static void dropInventoryItems(World world, BlockPos pos, IInventory inventory)
    {
        dropInventoryItems(world, pos.getX(), pos.getY(), pos.getZ(), inventory);
    }

    /**
     * Drop all the ItemStack in the inventory at the entity position
     * 
     * @param world
     *            The World Instance
     * @param entityAt
     *            The entity
     * @param inventory
     *            The inventory
     */
    public static void dropInventoryItems(World world, Entity entityAt, IInventory inventory)
    {
        dropInventoryItems(world, entityAt.posX, entityAt.posY, entityAt.posZ, inventory);
    }

    /**
     * Drop all the ItemStack in the inventory
     * 
     * @param world
     *            The World Instance
     * @param x
     *            The x Position
     * @param y
     *            The z Position
     * @param z
     *            The y Position
     * @param inventory
     *            The inventory
     */
    public static void dropInventoryItems(World world, double x, double y, double z, IInventory inventory)
    {
        for (int i = 0; i < inventory.getSizeInventory() + 1; ++i)
        {
            ItemStack stack = inventory.getStackInSlot(i);

            if (stack != null)
            {
                dropItem(world, x, y, z, stack);
            }
        }
    }

    /**
     * Drop an ItemStack
     * 
     * @param world
     *            The World Instance
     * @param x
     *            The x Position
     * @param y
     *            The z Position
     * @param z
     *            The y Position
     * @param stack
     *            The ItemStack
     */
    public static void dropItem(World world, double x, double y, double z, ItemStack stack)
    {
        InventoryHelper.spawnItemStack(world, x, y, z, stack);
    }

    /**
     * Register a dimension
     * 
     * @param id
     *            The id of the dimension
     * @param type
     *            The type of the dimension
     */
    public static void registerDimension(int id, DimensionType type)
    {
        DimensionManager.registerDimension(id, type);
    }

    /**
     * Register a DimensionType
     * 
     * @param name
     *            The enum name
     * @param suffix
     *            The folder suffix
     * @param id
     *            The dimension id
     * @param provider
     *            The world provider
     * @param keepLoaded
     *            Should keep the dimension loaded
     * @return The DimensionType
     */
    public static DimensionType registerDimensionType(String name, String suffix, int id,
            Class<? extends WorldProvider> provider, boolean keepLoaded)
    {
        return DimensionType.register(name, suffix, id, provider, keepLoaded);
    }

    /**
     * Get the world corresponding to the side (client or server)
     * 
     * @return The world instance
     */
    public static World getWorld()
    {
        switch (MinecraftUtil.getSide())
        {
            case CLIENT:
                return MinecraftUtil.getClient().theWorld;
            case SERVER:
                return MinecraftUtil.getServer().getEntityWorld();
            default:
                return null;
        }
    }

    /**
     * Get the world link with the id
     * 
     * @param id
     *            The world id
     * @return The world instance
     */
    public static WorldServer getWorld(int id)
    {
        return DimensionManager.getWorld(id);
    }

    /**
     * Get the world id with the world instance, if not found return 0
     * 
     * @param world
     *            The world instance
     * @return The world id
     */
    public static int getWorldId(World world)
    {
        if (world instanceof WorldServer)
            return getWorldId((WorldServer) world);
        return 0;
    }

    /**
     * Get the world id with the world instance
     * 
     * @param world
     *            The world instance
     * @return The world id
     */
    public static int getWorldId(WorldServer world)
    {
        int res = 0;
        boolean find = false;
        Integer[] worlds = DimensionManager.getIDs();
        for (int i = 0; i < worlds.length && !find; i++)
        {
            if (getWorld(worlds[i]).equals(world))
            {
                res = worlds[i];
                find = true;
            }
        }
        return res;
    }

    /**
     * Get an array with all the world instance
     * 
     * @return The world array
     */
    public static WorldServer[] getWorlds()
    {
        return DimensionManager.getWorlds();
    }

    /**
     * Returns a list of dimensions associated with this DimensionType.
     * 
     * @param type
     *            The DimensionType
     * @return The ids of the dimension
     */
    public static int[] getDimensions(DimensionType type)
    {
        return DimensionManager.getDimensions(type);
    }
}
