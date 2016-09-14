package com.leviathanstudio.lib.common.util.io.nbt;

import net.minecraft.nbt.NBTBase;

public interface INbtChecker
{
    public boolean checkTagType(NBTBase tag);
}
