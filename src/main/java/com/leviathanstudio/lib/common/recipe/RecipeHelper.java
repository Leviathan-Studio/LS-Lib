package com.leviathanstudio.lib.common.recipe;

import java.util.Iterator;
import java.util.List;

import com.leviathanstudio.lib.common.core.LSLibConfig;
import com.leviathanstudio.lib.common.recipe.potion.RemovedBrewingManager;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.brewing.AbstractBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeHelper
{
    // *********************************************************************************************

    /**
     * Remove a crafting recipe
     *
     * @param item
     *            The item to remove
     */
    public static void removeRecipe(Item item)
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        Iterator<IRecipe> iterator = recipes.iterator();
        while (iterator.hasNext())
        {
            ItemStack is = iterator.next().getRecipeOutput();
            if ((is != null) && (is.getItem() == item))
                iterator.remove();
        }
        ;
    }

    /**
     * Remove a crafting recipe
     *
     * @param block
     *            The block to remove
     */
    public static void removeRecipe(Block block)
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        Iterator<IRecipe> iterator = recipes.iterator();
        while (iterator.hasNext())
        {
            ItemStack is = iterator.next().getRecipeOutput();
            if ((is != null) && (is.getItem() == Item.getItemFromBlock(block)))
                iterator.remove();
        }
        ;
    }

    /**
     * Remove a crafting recipe
     *
     * @param stack
     *            The ItemStack to remove
     */
    public static void removeRecipe(ItemStack stack)
    {
        List<IRecipe> recipes = CraftingManager.getInstance().getRecipeList();
        Iterator<IRecipe> iterator = recipes.iterator();
        while (iterator.hasNext())
        {
            ItemStack is = iterator.next().getRecipeOutput();
            if ((is != null) && (is == stack))
                iterator.remove();
        }
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The ItemStack to remove
     */
    public static void removeSmeltingRecipe(ItemStack output)
    {
        FurnaceRecipes.instance().getSmeltingList().remove(output);
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The item to remove
     * @param damage
     *            The damage of the item
     */
    public static void removeSmeltingRecipe(Item output, int damage)
    {
        removeSmeltingRecipe(new ItemStack(output, 1, damage));
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The item to remove
     */
    public static void removeSmeltingRecipe(Item output)
    {
        removeSmeltingRecipe(new ItemStack(output, 1, OreDictionary.WILDCARD_VALUE));
    }

    /**
     * Remove a smelting recipe
     * 
     * @param output
     *            The block to remove
     */
    public static void removeSmeltingRecipe(Block output)
    {
        removeSmeltingRecipe(Item.getItemFromBlock(output));
    }

    /**
     * Remove a vanilla brewing recipe
     * 
     * @param stack
     *            The ItemStack to remove
     */
    public static void removeVanillaBrewingRecipe(ItemStack stack)
    {
        RemovedBrewingManager.add(stack);
    }

    /**
     * Remove a brewing recipe
     * 
     * @param stack
     *            The ItemStack to remove
     */
    @SuppressWarnings("rawtypes")
    public static void removeBrewingRecipe(ItemStack stack)
    {
        List<IBrewingRecipe> recipes = BrewingRecipeRegistry.getRecipes();
        Iterator<IBrewingRecipe> iterator = recipes.iterator();
        while (iterator.hasNext())
        {
            IBrewingRecipe recipe = iterator.next();
            if (recipe instanceof AbstractBrewingRecipe)
            {
                AbstractBrewingRecipe bRecipe = (AbstractBrewingRecipe) recipe;
                ItemStack is = bRecipe.getOutput();
                if ((is != null) && ItemStack.areItemStacksEqual(is, stack))
                    iterator.remove();
            }

        }
    }

    /**
     * Remove a brewing recipe
     * 
     * @param item
     *            The item to remove
     */
    @SuppressWarnings("rawtypes")
    public static void removeBrewingRecipe(Item item)
    {
        List<IBrewingRecipe> recipes = BrewingRecipeRegistry.getRecipes();
        Iterator<IBrewingRecipe> iterator = recipes.iterator();
        while (iterator.hasNext())
        {
            IBrewingRecipe recipe = iterator.next();
            if (recipe instanceof AbstractBrewingRecipe)
            {
                AbstractBrewingRecipe bRecipe = (AbstractBrewingRecipe) recipe;
                ItemStack is = bRecipe.getOutput();
                if ((is != null) && (is.getItem() == item))
                    iterator.remove();
            }

        }
    }

    // *********************************************************************************************

    /**
     * Add stair recipe
     * 
     * @param output
     *            The stair block
     * @param input
     *            The block use to craft the stair
     */
    public static void addStairs(Block output, Block input)
    {
        GameRegistry.addRecipe(new ItemStack(output, LSLibConfig.craftStairsNumber), "X  ", "XX ", "XXX", 'X', input);
        GameRegistry.addRecipe(new ItemStack(output, LSLibConfig.craftStairsNumber), "  X", " XX", "XXX", 'X', input);
    }

    /**
     * Add stair recipe
     * 
     * @param output
     *            The stair block
     * @param input
     *            The item stack use to craft the stair
     */
    public static void addStairs(Block output, ItemStack input)
    {
        GameRegistry.addRecipe(new ItemStack(output, LSLibConfig.craftStairsNumber), "X  ", "XX ", "XXX", 'X', input);
        GameRegistry.addRecipe(new ItemStack(output, LSLibConfig.craftStairsNumber), "  X", " XX", "XXX", 'X', input);
    }

    /**
     * Add slab recipe
     * 
     * @param output
     *            The stair block
     * @param input
     *            The block use to craft the slab
     */
    public static void addSlabs(Block output, Block input)
    {
        GameRegistry.addRecipe(new ItemStack(output, LSLibConfig.craftSlabsNumber), "XXX", "XXX", 'X', input);
    }

    /**
     * Add slab recipe
     * 
     * @param output
     *            The stair block
     * @param input
     *            The item stack use to craft the slab
     */
    public static void addSlabs(Block output, ItemStack input)
    {
        GameRegistry.addRecipe(new ItemStack(output, LSLibConfig.craftSlabsNumber), "XXX", "XXX", 'X', input);
    }
}
