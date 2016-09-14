package com.leviathanstudio.test;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.EnumDyeColor;

public class BlockTest extends Block
{
    private EnumDyeColor dye;

    public BlockTest(Material materialIn, EnumDyeColor dye)
    {
        super(materialIn);
        this.dye = dye;
    }

    @Override
    public String toString()
    {
        return super.toString() + " -> " + dye.name();
    }

}
