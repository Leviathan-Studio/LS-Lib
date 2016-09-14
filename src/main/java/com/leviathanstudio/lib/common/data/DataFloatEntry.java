package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataFloatEntry extends DataEntry<Float>
{

    public DataFloatEntry(String name, Float value)
    {
        super(name, value);
    }

    public DataFloatEntry(String name, Float defaultValue, Float value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setFloat(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getFloat(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeFloat(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readFloat();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeFloat(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readFloat();
    }

}
