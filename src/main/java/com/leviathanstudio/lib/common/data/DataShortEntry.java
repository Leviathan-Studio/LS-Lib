package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataShortEntry extends DataEntry<Short>
{
    public DataShortEntry(String name, Short value)
    {
        super(name, value);
    }

    public DataShortEntry(String name, Short defaultValue, Short value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setShort(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getShort(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeShort(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readShort();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeShort(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readShort();
    }

}
