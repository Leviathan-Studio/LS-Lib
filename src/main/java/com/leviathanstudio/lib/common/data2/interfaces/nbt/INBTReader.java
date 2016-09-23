package com.leviathanstudio.lib.common.data2.interfaces.nbt;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTReader<T>
{
    public T readNBT(NBTTagCompound nbt, String name);
}
