package com.leviathanstudio.lib.common.recipe.potion;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Event use for the prevent brewing recipe system
 */
public class PreventBrewingEvent
{
    @SubscribeEvent
    public void onPotionAttemptBrew(PotionBrewEvent.Pre event)
    {
        ItemStack ingredient = event.getItem(3);
        for (int i = 0; i < event.getLength() - 1; i++)
        {
            ItemStack slot = event.getItem(i);
            // Get the result of the brewing
            ItemStack result = BrewingRecipeRegistry.getOutput(slot, ingredient);

            // Test if the brewing is allow or not
            if (result != null && !RemovedBrewingManager.canBrew(result))
            {
                // if the brewing can't be done, cancel the event to prevent the
                // craft
                event.setCanceled(true);
            }
        }
    }
}
