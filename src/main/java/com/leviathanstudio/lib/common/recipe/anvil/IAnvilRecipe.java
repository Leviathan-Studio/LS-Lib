package com.leviathanstudio.lib.common.recipe.anvil;

import net.minecraft.item.ItemStack;

public interface IAnvilRecipe
{
    /**
     * Test if the recipe corresponding to the setup
     * @param left The left stack
     * @param right The right stack
     * @return return true if setup match with the recipe
     */
    boolean matches(ItemStack left, ItemStack right);

    /**
     * Method call just after match if success, use to update output stack in
     * function of setup stacks. By default nothing.
     * 
     * @param left
     *            The left stack
     * @param right
     *            The right stack
     */
    default void update(ItemStack left, ItemStack right)
    {
    }

    /**
     * Get the result of the recipe. call after update method
     * @return The recipe result
     */
    ItemStack getRecipeOutput();

    /**
     * Get the experience cost for the recipe
     * @return The experience cost
     */
    int getCost();

    /**
     * Get the material cost, that mean how many item should be use to consume
     * the left stack. Can't no be negative. By default 1.
     * 
     * @return The material cost
     */
    default int getMaterialCost()
    {
        return 1;
    }
}
