package com.leviathanstudio.lib.common.potion;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionHelper
{
    /**
     * Get the potion stack with the specific potion effect
     * 
     * @param potion
     *            The vanilla potion effect
     * @return The potion item
     */
    public static ItemStack getPotion(EnumVanilliaPotion potion)
    {
        return getPotion(EnumPotionType.NORMAL, potion);
    }

    /**
     * Get the potion stack with the specific potion effect
     * 
     * @param type
     *            The potion type (normal, splash or lingering)
     * @param potion
     *            The vanilla potion effect
     * @return The potion item
     */
    public static ItemStack getPotion(EnumPotionType type, EnumVanilliaPotion potion)
    {
        return getPotion(type, potion, EnumPotionEffectType.NORMAL);
    }

    /**
     * Get the potion stack with the specific potion effect
     * 
     * @param potion
     *            The vanilla potion effect
     * @param effectType
     *            The potion modifier (normal, strong or extended)
     * @return The potion item
     */
    public static ItemStack getPotion(EnumVanilliaPotion potion, EnumPotionEffectType effectType)
    {
        return getPotion(EnumPotionType.NORMAL, potion, effectType);
    }

    /**
     * Get the potion stack with the specific potion effect
     * 
     * @param type
     *            The potion type (normal, splash or lingering)
     * @param potion
     *            The vanilla potion effect
     * @param effectType
     *            The potion modifier (normal, strong or extended)
     * @return The potion item
     */
    public static ItemStack getPotion(EnumPotionType type, EnumVanilliaPotion potion, EnumPotionEffectType effectType)
    {
        ItemStack result;

        if (!isValid(potion, effectType))
            throw new IllegalArgumentException("Wrong association of the potion and the effect type");

        switch (type)
        {
            case SPLASH:
                result = new ItemStack(Items.SPLASH_POTION);
                break;
            case LINGERING:
                result = new ItemStack(Items.LINGERING_POTION);
                break;
            default:
                result = new ItemStack(Items.POTIONITEM);
                break;
        }

        result.setTagInfo("Potion", new NBTTagString(getPotionNBT(potion, effectType)));
        return result;
    }

    /**
     * Get the NBT string for the potion
     * 
     * @param potion
     *            The potion value
     * @param effectType
     *            The modifier value
     * @return The NBT string
     */
    private static String getPotionNBT(EnumVanilliaPotion potion, EnumPotionEffectType effectType)
    {
        return "minecraft:" + effectType.getText() + potion.getName();
    }

    /**
     * Test if a potion exists and has the request modifier
     * 
     * @param potion
     *            The potion value
     * @param type
     *            The modifier value
     * @return The result of the test
     */
    private static boolean isValid(EnumVanilliaPotion potion, EnumPotionEffectType type)
    {
        switch (type)
        {
            case STRONG:
                return potion.hasStrong();
            case EXTENDED:
                return potion.hasExtended();
            default:
                return true;
        }
    }

    /**
     * Register a new potion
     * 
     * @param id
     *            The potion id
     * @param name
     *            The potion name
     * @param type
     *            The potion type (effect)
     */
    public static void registerPotion(int id, ResourceLocation name, PotionType type)
    {
        PotionType.REGISTRY.register(id, name, type);
    }

    // Some PotionUtils methods

    public static void addCustomPotionEffectToList(@Nullable NBTTagCompound tag, List<PotionEffect> effectList)
    {
        PotionUtils.addCustomPotionEffectToList(tag, effectList);
    }

    public static ItemStack addPotionToItemStack(ItemStack item, PotionType potion)
    {
        return PotionUtils.addPotionToItemStack(item, potion);
    }

    @SideOnly(Side.CLIENT)
    public static void addPotionTooltip(ItemStack stack, List<String> lores, float durationFactor)
    {
        PotionUtils.addPotionTooltip(stack, lores, durationFactor);
    }

    public static ItemStack appendEffects(ItemStack stack, Collection<PotionEffect> effects)
    {
        return PotionUtils.appendEffects(stack, effects);
    }

    public static List<PotionEffect> mergeEffects(PotionType potion, Collection<PotionEffect> effects)
    {
        return PotionUtils.mergeEffects(potion, effects);
    }

    public static List<PotionEffect> getEffectsFromStack(ItemStack stack)
    {
        return PotionUtils.getEffectsFromStack(stack);
    }

    public static List<PotionEffect> getEffectsFromTag(@Nullable NBTTagCompound tag)
    {
        return PotionUtils.getEffectsFromTag(tag);
    }

    public static List<PotionEffect> getFullEffectsFromItem(ItemStack stack)
    {
        return PotionUtils.getFullEffectsFromItem(stack);
    }

    public static List<PotionEffect> getFullEffectsFromTag(@Nullable NBTTagCompound tag)
    {
        return PotionUtils.getFullEffectsFromTag(tag);
    }

    @SideOnly(Side.CLIENT)
    public static int getPotionColor(PotionType potion)
    {
        return PotionUtils.getPotionColor(potion);
    }

    public static int getPotionColorFromEffectList(Collection<PotionEffect> effects)
    {
        return PotionUtils.getPotionColorFromEffectList(effects);
    }

    public static PotionType getPotionFromItem(ItemStack item)
    {
        return PotionUtils.getPotionFromItem(item);
    }

    public static PotionType getPotionTypeFromNBT(@Nullable NBTTagCompound tag)
    {
        return PotionUtils.getPotionTypeFromNBT(tag);
    }

    public static boolean isReagent(ItemStack stack)
    {
        return PotionHelper.isReagent(stack);
    }

    public static boolean hasConversions(ItemStack input, ItemStack reagent)
    {
        return PotionHelper.hasConversions(input, reagent);
    }

    @Nullable
    public static ItemStack doReaction(ItemStack reagent, @Nullable ItemStack input)
    {
        return PotionHelper.doReaction(reagent, input);
    }
}
