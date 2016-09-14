package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;

public class DataTagEntry extends DataEntry<NBTTagCompound>
{

    public DataTagEntry(String name, NBTTagCompound value)
    {
        super(name, value);
    }

    public DataTagEntry(String name, NBTTagCompound defaultValue, NBTTagCompound value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setTag(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = (NBTTagCompound) nbt.getTag(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        ByteBufUtils.writeTag(buffer, this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = ByteBufUtils.readTag(buffer);
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        DataStreamUtil.writeTag(data, this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = DataStreamUtil.readTag(data);
    }

}
