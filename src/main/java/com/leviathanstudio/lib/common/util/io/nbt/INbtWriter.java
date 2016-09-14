package com.leviathanstudio.lib.common.util.io.nbt;

import net.minecraft.nbt.NBTTagCompound;

public interface INbtWriter<T>
{
    public void writeToNBT(T o, NBTTagCompound tag, String name);
}
