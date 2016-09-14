package com.leviathanstudio.lib.common.recipe.potion;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;

public class RemovedBrewingManager
{
    private static final List<ItemStack> removedBrewing = Lists.newArrayList();

    /**
     * Add a new ban potion
     * 
     * @param result
     *            The potion
     * @return true if added, false else
     */
    public static boolean add(ItemStack result)
    {
        if (result != null && !removedBrewing.contains(result) && result.stackSize != 0)
        {
            return removedBrewing.add(result);
        }
        return false;
    }

    /**
     * Test if the potion can be brew
     * 
     * @param result
     *            The brewing result
     * @return true if the potion can be brew, false else
     */
    public static boolean canBrew(ItemStack result)
    {
        boolean res = true;
        for (int i = 0; i < removedBrewing.size() && res; i++)
        {
            ItemStack stack = removedBrewing.get(i);
            res = !ItemStack.areItemStacksEqual(result, stack);
        }
        return res;
    }
}
