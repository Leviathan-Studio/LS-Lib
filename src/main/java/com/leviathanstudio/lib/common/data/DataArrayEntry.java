package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public abstract class DataArrayEntry<T> extends DataEntry<T[]>
{

    public DataArrayEntry(String name, T[] value)
    {
        super(name, value);
    }

    public DataArrayEntry(String name, T[] defaultValue, T[] value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger(this.name + "_length", value.length);
        for (int i = 0; i < value.length; i++)
        {
            writeOneNBT(nbt, i);
        }
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        int length = nbt.getInteger(this.name + "_length");
        for (int i = 0; i < length; i++)
        {
            this.value[i] = readOneNBT(nbt, i);
        }
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeInt(value.length);
        for (int i = 0; i < value.length; i++)
        {
            writeOneBuffer(buffer, i);
        }
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        int length = buffer.readInt();
        for (int i = 0; i < length; i++)
        {
            this.value[i] = readOneBuffer(buffer, i);
        }
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeInt(value.length);
        for (int i = 0; i < value.length; i++)
        {
            writeOneData(data, i);
        }
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        int length = data.readInt();
        for (int i = 0; i < length; i++)
        {
            this.value[i] = readOneData(data, i);
        }
    }

    public abstract void writeOneNBT(NBTTagCompound nbt, int i);

    public abstract T readOneNBT(NBTTagCompound nbt, int i);

    public abstract void writeOneBuffer(ByteBuf buffer, int i);

    public abstract T readOneBuffer(ByteBuf buffer, int i);

    public abstract void writeOneData(DataOutput data, int i);

    public abstract T readOneData(DataInput data, int i);

}
