package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataByteEntry extends DataEntry<Byte>
{

    public DataByteEntry(String name, Byte value)
    {
        super(name, value);
    }

    public DataByteEntry(String name, Byte defaultValue, Byte value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setByte(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getByte(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeByte(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readByte();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeByte(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readByte();
    }

}
