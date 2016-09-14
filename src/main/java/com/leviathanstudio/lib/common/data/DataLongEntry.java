package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataLongEntry extends DataEntry<Long>
{
    public DataLongEntry(String name, Long value)
    {
        super(name, value);
    }

    public DataLongEntry(String name, Long defaultValue, Long value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setLong(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getLong(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeLong(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readLong();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeLong(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readLong();
    }

}
