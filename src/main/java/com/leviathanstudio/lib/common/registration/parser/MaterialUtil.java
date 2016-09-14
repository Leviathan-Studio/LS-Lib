package com.leviathanstudio.lib.common.registration.parser;

import java.util.HashMap;

import com.google.common.collect.Maps;

import net.minecraft.block.material.Material;

public class MaterialUtil implements IParser
{
    public static final HashMap<String, Material> materials = Maps.newHashMap();

    public static boolean add(String name, Material material)
    {
        if (name != null && !name.isEmpty() && material != null && !materials.containsKey(name))
            return materials.put(name, material) == null;
        return false;
    }

    @Override
    public void init()
    {
        materials.put("grass", Material.GRASS);
        materials.put("ground", Material.GROUND);
        materials.put("wood", Material.WOOD);
        materials.put("rock", Material.ROCK);
        materials.put("iron", Material.IRON);
        materials.put("anvil", Material.ANVIL);
        materials.put("water", Material.WATER);
        materials.put("lava", Material.LAVA);
        materials.put("leaves", Material.LEAVES);
        materials.put("plants", Material.PLANTS);
        materials.put("vines", Material.VINE);
        materials.put("sponge", Material.SPONGE);
        materials.put("cloth", Material.CLOTH);
        materials.put("fire", Material.FIRE);
        materials.put("sand", Material.SAND);
        materials.put("circuits", Material.CIRCUITS);
        materials.put("carpet", Material.CARPET);
        materials.put("glass", Material.GLASS);
        materials.put("redstoneLight", Material.REDSTONE_LIGHT);
        materials.put("tnt", Material.TNT);
        materials.put("coral", Material.CORAL);
        materials.put("ice", Material.ICE);
        materials.put("packedIce", Material.PACKED_ICE);
        materials.put("snow", Material.SNOW);
        materials.put("craftedSnow", Material.CRAFTED_SNOW);
        materials.put("cactus", Material.CACTUS);
        materials.put("clay", Material.CLAY);
        materials.put("gourd", Material.GOURD);
        materials.put("dragonEgg", Material.DRAGON_EGG);
        materials.put("portal", Material.PORTAL);
        materials.put("cake", Material.CAKE);
        materials.put("web", Material.WEB);
        materials.put("piston", Material.PISTON);
        materials.put("barrier", Material.BARRIER);
        materials.put("structureVoid", Material.STRUCTURE_VOID);
        materials.put("air", Material.AIR);
    }

    @Override
    public Material parse(String value)
    {
        return materials.get(value);
    }

    @Override
    public boolean canParse(String value)
    {
        return parse(value) != null;
    }

}
