package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataBooleanEntry extends DataEntry<Boolean>
{

    public DataBooleanEntry(String name, Boolean value)
    {
        super(name, value);
    }

    public DataBooleanEntry(String name, Boolean defaultValue, Boolean value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getBoolean(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeBoolean(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readBoolean();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeBoolean(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readBoolean();
    }

}
