package com.leviathanstudio.lib.client;

import com.leviathanstudio.lib.common.util.ModUtil;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class AssetsHelper
{
    public static void registerItemTexture(Item item, int metadata, String modid, String name)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata,
                new ModelResourceLocation(modid + ":" + name, "inventory"));
    }

    public static void registerItemTexture(Item item, String modid, String name)
    {
        registerItemTexture(item, 0, modid, name);
    }

    public static void registerBlockTexture(Block block, int metadata, String modid, String name)
    {
        registerItemTexture(Item.getItemFromBlock(block), metadata, modid, name);
    }

    public static void registerBlockTexture(Block block, String modid, String name)
    {
        registerBlockTexture(block, 0, modid, name);
    }

    public static void registerItemTexture(Item item, int metadata, String name)
    {
        ModelLoader.setCustomModelResourceLocation(item, metadata,
                new ModelResourceLocation(ModUtil.getCurrentModid() + ":" + name, "inventory"));
    }

    public static void registerItemTexture(Item item, String name)
    {
        registerItemTexture(item, 0, ModUtil.getCurrentModid(), name);
    }

    public static void registerBlockTexture(Block block, int metadata, String name)
    {
        registerItemTexture(Item.getItemFromBlock(block), metadata, ModUtil.getCurrentModid(), name);
    }

    public static void registerBlockTexture(Block block, String name)
    {
        registerBlockTexture(block, 0, ModUtil.getCurrentModid(), name);
    }
}
