package com.leviathanstudio.lib.common.other;

import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;

public class EnchantHelper
{
    /**
     * Get the enchantment cost for a given item stack
     * 
     * @param stack
     *            the give stack with enchant
     * @return level amount
     */

    public static int getEnchantmentsCost(ItemStack stack)
    {
        return getEnchantmentsCost(EnchantmentHelper.getEnchantments(stack));

    }

    /**
     * Get the enchantment cost for a given Enchantment list
     * 
     * @param map
     *            the map of the enchants
     * @return level amount
     */
    public static int getEnchantmentsCost(Map<Enchantment, Integer> map)
    {
        int i = 0;
        for (Enchantment enchantment1 : map.keySet())
        {
            if (enchantment1 != null)
            {
                int i3 = map.containsKey(enchantment1) ? map.get(enchantment1).intValue() : 0;
                int j3 = map.get(enchantment1).intValue();
                j3 = i3 == j3 ? j3 + 1 : Math.max(j3, i3);

                if (j3 > enchantment1.getMaxLevel())
                {
                    j3 = enchantment1.getMaxLevel();
                }

                map.put(enchantment1, Integer.valueOf(j3));
                int k3 = 0;

                switch (enchantment1.getRarity())
                {
                    case COMMON:
                        k3 = 1;
                        break;
                    case UNCOMMON:
                        k3 = 2;
                        break;
                    case RARE:
                        k3 = 4;
                        break;
                    case VERY_RARE:
                        k3 = 8;
                }

                k3 = Math.max(1, k3 / 2);

                i += k3 * j3;

            }
        }
        return i;
    }

    /**
     * Copy enchantment from an stack to an other
     * 
     * @param from
     *            The origin stack (where the enchantment are)
     * @param to
     *            The destination stack (where the enchantment need to be copy)
     */
    public static void copyEnchant(ItemStack from, ItemStack to)
    {
        copyEnchant(from.getEnchantmentTagList(), to);
    }

    /**
     * Copy the enchantment from the list to a new ItemStack
     * 
     * @param enchants
     *            The enchantment list to copy
     * @param to
     *            The destination stack (where the enchantment need to be copy)
     */
    public static void copyEnchant(NBTTagList enchants, ItemStack to)
    {
        for (int i = 0; i < enchants.tagCount(); i++)
        {
            short id = enchants.getCompoundTagAt(i).getShort("id");
            short lvl = enchants.getCompoundTagAt(i).getShort("lvl");
            EnchantmentData enchantmentdata = new EnchantmentData(Enchantment.getEnchantmentByID(id), lvl);
            Items.ENCHANTED_BOOK.addEnchantment(to, enchantmentdata);
        }
    }
}
