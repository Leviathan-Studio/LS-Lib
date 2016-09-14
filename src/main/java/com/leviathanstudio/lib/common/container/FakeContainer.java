package com.leviathanstudio.lib.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

/**
 * A fake container for the fake Inventory crafting
 */
public class FakeContainer extends Container
{
    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return false;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        // do nothing
    }
}
