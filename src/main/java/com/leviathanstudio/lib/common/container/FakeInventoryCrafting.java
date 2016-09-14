package com.leviathanstudio.lib.common.container;

import net.minecraft.inventory.InventoryCrafting;

/**
 * A fake Inventory crafting, use the process recipe without have to setup a
 * container
 */
public class FakeInventoryCrafting extends InventoryCrafting
{

    public FakeInventoryCrafting(int width, int height)
    {
        super(new FakeContainer(), width, height);
    }

}
