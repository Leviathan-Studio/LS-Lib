package com.leviathanstudio.lib.common.data2.interfaces.nbt;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTWriter<T>
{
    public void writeNBT(NBTTagCompound nbt, String name, T value);
}
