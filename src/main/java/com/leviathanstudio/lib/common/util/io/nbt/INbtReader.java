package com.leviathanstudio.lib.common.util.io.nbt;

import net.minecraft.nbt.NBTTagCompound;

public interface INbtReader<T>
{
    public T readFromNBT(NBTTagCompound tag, String name);
}
