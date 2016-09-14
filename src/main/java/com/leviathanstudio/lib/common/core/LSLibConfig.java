package com.leviathanstudio.lib.common.core;

import com.leviathanstudio.lib.common.config.simple.ConfigProperty;
import com.leviathanstudio.lib.common.recipe.RecipeHelper;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class LSLibConfig
{
    @ConfigProperty(category = "craft", name = "CraftStairsNumber", comment = "Number of stairs give for the craft (default:8)")
    public static int     craftStairsNumber   = 8;

    @ConfigProperty(category = "craft", name = "CraftSlabsNumber", comment = "Number of slabs give for the craft (default:6)")
    public static int     craftSlabsNumber    = 6;

    @ConfigProperty(category = "craft", name = "OverrideStairsCraft", comment = "Define if the library sould override the stairs recipe")
    public static boolean overrideStairsCraft = true;

    public static void postConfig()
    {
        if (overrideStairsCraft)
        {
            RecipeHelper.removeRecipe(Blocks.ACACIA_STAIRS);
            RecipeHelper.removeRecipe(Blocks.BIRCH_STAIRS);
            RecipeHelper.removeRecipe(Blocks.BRICK_STAIRS);
            RecipeHelper.removeRecipe(Blocks.DARK_OAK_STAIRS);
            RecipeHelper.removeRecipe(Blocks.JUNGLE_STAIRS);
            RecipeHelper.removeRecipe(Blocks.NETHER_BRICK_STAIRS);
            RecipeHelper.removeRecipe(Blocks.OAK_STAIRS);
            RecipeHelper.removeRecipe(Blocks.PURPUR_STAIRS);
            RecipeHelper.removeRecipe(Blocks.QUARTZ_STAIRS);
            RecipeHelper.removeRecipe(Blocks.RED_SANDSTONE_STAIRS);
            RecipeHelper.removeRecipe(Blocks.SANDSTONE_STAIRS);
            RecipeHelper.removeRecipe(Blocks.SPRUCE_STAIRS);
            RecipeHelper.removeRecipe(Blocks.STONE_BRICK_STAIRS);
            RecipeHelper.removeRecipe(Blocks.STONE_STAIRS);

            RecipeHelper.addStairs(Blocks.ACACIA_STAIRS, new ItemStack(Blocks.PLANKS, 1, 4));
            RecipeHelper.addStairs(Blocks.BIRCH_STAIRS, new ItemStack(Blocks.PLANKS, 1, 2));
            RecipeHelper.addStairs(Blocks.BRICK_STAIRS, Blocks.BRICK_BLOCK);
            RecipeHelper.addStairs(Blocks.DARK_OAK_STAIRS, new ItemStack(Blocks.PLANKS, 1, 5));
            RecipeHelper.addStairs(Blocks.JUNGLE_STAIRS, new ItemStack(Blocks.PLANKS, 1, 3));
            RecipeHelper.addStairs(Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK);
            RecipeHelper.addStairs(Blocks.OAK_STAIRS, new ItemStack(Blocks.PLANKS, 1, 0));
            RecipeHelper.addStairs(Blocks.PURPUR_STAIRS, Blocks.PURPUR_BLOCK);
            RecipeHelper.addStairs(Blocks.QUARTZ_STAIRS, Blocks.QUARTZ_BLOCK);
            RecipeHelper.addStairs(Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE);
            RecipeHelper.addStairs(Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE);
            RecipeHelper.addStairs(Blocks.SPRUCE_STAIRS, new ItemStack(Blocks.PLANKS, 1, 1));
            RecipeHelper.addStairs(Blocks.STONE_BRICK_STAIRS, Blocks.STONEBRICK);
            RecipeHelper.addStairs(Blocks.STONE_STAIRS, Blocks.COBBLESTONE);
        }
    }
}
