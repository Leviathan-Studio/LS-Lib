package com.leviathanstudio.lib.common.recipe.anvil;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.apache.commons.lang3.StringUtils;

/**
 * Event use for the anvil recipe system
 */
public class AnvilEvent
{
    @SubscribeEvent
    public void anvilEvent(AnvilUpdateEvent event)
    {
        // Get information
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();
        ItemStack out = event.getOutput();
        String name = event.getName();

        // find recipe
        IAnvilRecipe recipe = AnvilRecipeManager.getInstance().findMatchingRecipe(left, right);
        if (recipe != null)
        {
            int cost = 0;
            int materialCost = 0;

            out = recipe.getRecipeOutput();
            cost = recipe.getCost();
            materialCost = recipe.getMaterialCost();

            // prevent materialCost negative
            if (materialCost <= 0)
                materialCost = 1;

            // Custom name
            if (out != null && name != null)
            {
                if (StringUtils.isBlank(name))
                {
                    if (out.hasDisplayName())
                    {
                        cost++;
                        out.clearCustomName();
                    }
                }
                else if (!name.equals(out.getDisplayName()))
                {
                    cost++;
                    out.setStackDisplayName(name);
                }
            }

            // Set information
            event.setCost(cost);
            event.setMaterialCost(materialCost);
            event.setOutput(out);
        }
    }
}
