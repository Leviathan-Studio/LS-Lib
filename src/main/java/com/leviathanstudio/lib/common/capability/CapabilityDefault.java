package com.leviathanstudio.lib.common.capability;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityDefault implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

    @Override
    public NBTTagCompound serializeNBT()
    {
        return null;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {

    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return null;
    }

}
