package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataDoubleEntry extends DataEntry<Double>
{

    public DataDoubleEntry(String name, Double value)
    {
        super(name, value);
    }

    public DataDoubleEntry(String name, Double defaultValue, Double value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setDouble(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getDouble(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeDouble(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readDouble();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeDouble(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readDouble();
    }

}
