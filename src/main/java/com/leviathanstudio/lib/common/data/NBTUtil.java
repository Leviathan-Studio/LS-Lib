package com.leviathanstudio.lib.common.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class NBTUtil
{
    public static void writeBlockPos(NBTTagCompound nbt, String name, BlockPos pos)
    {
        nbt.setInteger(name + "_x", pos.getX());
        nbt.setInteger(name + "_y", pos.getY());
        nbt.setInteger(name + "_z", pos.getZ());
    }

    public static BlockPos readBlockPos(NBTTagCompound nbt, String name)
    {
        int x = nbt.getInteger(name + "_x");
        int y = nbt.getInteger(name + "_y");
        int z = nbt.getInteger(name + "_z");

        return new BlockPos(x, y, z);
    }
}
