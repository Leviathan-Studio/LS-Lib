package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataIntegerEntry extends DataEntry<Integer>
{
    public DataIntegerEntry(String name, Integer value)
    {
        super(name, value);
    }

    public DataIntegerEntry(String name, Integer defaultValue, Integer value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger(name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getInteger(name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeInt(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readInt();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeInt(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readInt();
    }

}
