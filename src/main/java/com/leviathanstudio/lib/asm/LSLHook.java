package com.leviathanstudio.lib.asm;

import com.leviathanstudio.lib.common.event.CraftMatrixUpdateEvent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

public class LSLHook
{
    public static ItemStack onCraftMatrixChanged(EntityPlayer player, World world, InventoryCrafting craftMatrix)
    {
        ItemStack result = CraftingManager.getInstance().findMatchingRecipe(craftMatrix, world);
        CraftMatrixUpdateEvent event = new CraftMatrixUpdateEvent(player, world, result, craftMatrix);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult().equals(Result.DENY) || (event.isCancelable() && event.isCanceled()))
            return null;
        return event.getRecipeResult();
    }

}
