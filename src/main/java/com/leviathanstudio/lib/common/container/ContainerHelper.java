package com.leviathanstudio.lib.common.container;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerHelper
{
    /**
     * Consume an Item with a specific damage
     * 
     * @param player
     *            The player instance
     * @param item
     *            The item to consume
     * @param damage
     *            The block damage
     * @return true if item is consume, false else
     */
    public static boolean consumeItemWithMetadataInInventory(EntityPlayer player, Item item, int damage)
    {
        for (int j = 0; j < player.inventory.mainInventory.length; ++j)
            if (player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].getItem().equals(item)
                    && player.inventory.mainInventory[j].getItemDamage() == damage)
            {
                if (--player.inventory.mainInventory[j].stackSize <= 0)
                    player.inventory.mainInventory[j] = null;
                return true;
            }
        return false;
    }

    /**
     * Consume an Item with a specific damage
     * 
     * @param player
     *            The player instance
     * @param block
     *            The block to consume
     * @param damage
     *            The block damage
     * @return true if item is consume, false else
     */
    public static boolean consumeItemWithMetadataInInventory(EntityPlayer player, Block block, int damage)
    {
        return consumeItemWithMetadataInInventory(player, Item.getItemFromBlock(block), damage);
    }

    /**
     * Test if ItemStack have the same item, the same damage, and the same NBT
     * tags
     * 
     * @param a
     *            The first stack
     * @param b
     *            The second stack
     * @return The result of the test
     */
    public static boolean isSame(ItemStack a, ItemStack b)
    {
        return ItemStack.areItemsEqual(a, b) && ItemStack.areItemStackTagsEqual(a, b)
                && a.getItemDamage() == b.getItemDamage();
    }
}
