package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataCharacterEntry extends DataEntry<Character>
{

    public DataCharacterEntry(String name, Character value)
    {
        super(name, value);
    }

    public DataCharacterEntry(String name, Character defaultValue, Character value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setInteger(this.name, Character.getNumericValue(this.value));
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = (char) nbt.getInteger(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        buffer.writeChar(this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = buffer.readChar();
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        data.writeChar(this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = data.readChar();
    }

}
