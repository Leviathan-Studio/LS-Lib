package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;

public class DataStringEntry extends DataEntry<String>
{

    public DataStringEntry(String name, String value)
    {
        super(name, value);
    }

    public DataStringEntry(String name, String defaultValue, String value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setString(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getString(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        ByteBufUtils.writeUTF8String(buffer, this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeUTF(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readUTF();
    }

}
