package com.leviathanstudio.lib.common.event;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Event.HasResult;

/**
 * Event call each time the craft matrix is update (workbench or player). If
 * event result is Deny then the output will be null
 */
@HasResult
public class CraftMatrixUpdateEvent extends PlayerEvent
{
    private final World             world;       // The world
    private final InventoryCrafting craftMatrix; // The matrix
    private ItemStack               result;      // The result

    /**
     * 
     * @param player
     *            The player instance
     * @param world
     *            The world instance
     * @param result
     *            The default crafting result
     * @param craftMatrix
     *            The crafting matrix
     */
    public CraftMatrixUpdateEvent(EntityPlayer player, World world, @Nullable ItemStack result,
            InventoryCrafting craftMatrix)
    {
        super(player);
        this.world = world;
        this.craftMatrix = craftMatrix;
        this.result = result;
    }

    /**
     * Get the world
     * 
     * @return The world
     */
    public World getWorld()
    {
        return this.world;
    }

    /**
     * Get the craft matrix
     * 
     * @return The craftMatrix
     */
    public InventoryCrafting getCraftMatrix()
    {
        return this.craftMatrix;
    }

    /**
     * Get the craft result
     * 
     * @return The craft result
     */
    public @Nullable ItemStack getRecipeResult()
    {
        return this.result;
    }

    /**
     * Set the new craft result
     * 
     * @param stack
     *            The new result
     */
    public void setRecipeResult(@Nullable ItemStack stack)
    {
        this.result = stack;
    }
}
