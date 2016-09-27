package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.leviathanstudio.lib.common.data2.util.DataStreamUtil;
import com.leviathanstudio.lib.common.data2.util.NBTUtil;
import com.leviathanstudio.lib.common.network.ByteBufUtil;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import io.netty.buffer.ByteBuf;

public class DataBlockPosEntry extends DataEntry<BlockPos>
{

    public DataBlockPosEntry(String name, BlockPos value)
    {
        super(name, value);
    }

    public DataBlockPosEntry(String name, BlockPos defaultValue, BlockPos value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        NBTUtil.writeBlockPos(nbt, this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = NBTUtil.readBlockPos(nbt, this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        ByteBufUtil.writeBlockPos(buffer, this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        ByteBufUtil.readBlockPos(buffer);
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        DataStreamUtil.writeBlockPos(data, this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = DataStreamUtil.readBlockPos(data);
    }

}
