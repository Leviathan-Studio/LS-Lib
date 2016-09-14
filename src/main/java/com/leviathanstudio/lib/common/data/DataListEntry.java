package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public abstract class DataListEntry<T> extends DataEntry<List<T>>
{

    public DataListEntry(String name, List<T> value)
    {
        super(name, value);
    }

    public DataListEntry(String name, List<T> defaultValue, List<T> value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger(this.name + "_size", value.size());
        for (int i = 0; i < value.size(); i++)
        {
            writeOneNBT(nbt, i);
        }
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        int size = nbt.getInteger(this.name + "_size");
        for (int i = 0; i < size; i++)
        {
            this.value.set(i, readOneNBT(nbt, i));
        }
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeInt(value.size());
        for (int i = 0; i < value.size(); i++)
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
            this.value.set(i, readOneBuffer(buffer, i));
        }
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeInt(value.size());
        for (int i = 0; i < value.size(); i++)
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
            this.value.set(i, readOneData(data, i));
        }
    }

    public abstract void writeOneNBT(NBTTagCompound nbt, int i);

    public abstract T readOneNBT(NBTTagCompound nbt, int i);

    public abstract void writeOneBuffer(ByteBuf buffer, int i);

    public abstract T readOneBuffer(ByteBuf buffer, int i);

    public abstract void writeOneData(DataOutput data, int i);

    public abstract T readOneData(DataInput data, int i);

}
