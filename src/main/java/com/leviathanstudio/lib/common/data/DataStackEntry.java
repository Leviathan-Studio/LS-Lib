package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;

public class DataStackEntry extends DataEntry<ItemStack>
{

    public DataStackEntry(String name, ItemStack value)
    {
        super(name, value);
    }

    public DataStackEntry(String name, ItemStack defaultValue, ItemStack value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        NBTTagCompound tag = value.writeToNBT(new NBTTagCompound());
        nbt.setTag(this.name, tag);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        NBTTagCompound tag = (NBTTagCompound) nbt.getTag(this.name);
        this.value = ItemStack.loadItemStackFromNBT(tag);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        ByteBufUtils.writeItemStack(buffer, this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = ByteBufUtils.readItemStack(buffer);
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        DataStreamUtil.writeItemStack(data, this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = DataStreamUtil.readItemStack(data);
    }

}
