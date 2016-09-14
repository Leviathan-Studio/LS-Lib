package com.leviathanstudio.lib.common.recipe.anvil;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;

public class AnvilRecipeManager
{
    /** The static instance of this class */
    private static final AnvilRecipeManager INSTANCE = new AnvilRecipeManager();
    private final List<IAnvilRecipe>        recipes  = Lists.<IAnvilRecipe> newArrayList();

    /**
     * Returns the static instance of this class
     * 
     * @return the instance of the AnvilRecipeManager
     */
    public static AnvilRecipeManager getInstance()
    {
        /** The static instance of this class */
        return INSTANCE;
    }

    public List<IAnvilRecipe> getRecipeList()
    {
        return this.recipes;
    }

    /**
     * Add a new IAnvilRecipe recipe
     * 
     * @param recipe
     *            The anvil recipe
     * @return true if added, false else
     */
    public boolean addRecipe(IAnvilRecipe recipe)
    {
        if (recipe != null && !this.recipes.contains(recipe))
        {
            return this.recipes.add(recipe);
        }
        return false;
    }

    /**
     * Retrieves an ItemStack that has multiple recipes for it.
     * 
     * @param left
     *            the left item in the anvil
     * @param right
     *            the right item in the anvil
     * @return the IAnvilRecipe if it's match else null
     */
    @Nullable
    public IAnvilRecipe findMatchingRecipe(ItemStack left, ItemStack right)
    {
        for (int i = 0; i < recipes.size(); i++)
        {
            IAnvilRecipe recipe = recipes.get(i);
            if (recipe.matches(left, right))
            {
                // update recipe before get output
                recipe.update(left, right);
                return recipe;
            }
        }
        return null;
    }
}
