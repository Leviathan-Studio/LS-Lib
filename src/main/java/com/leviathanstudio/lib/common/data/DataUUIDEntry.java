package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;

import com.leviathanstudio.lib.common.network.ByteBufUtil;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public class DataUUIDEntry extends DataEntry<UUID>
{

    public DataUUIDEntry(String name, UUID value)
    {
        super(name, value);
    }

    public DataUUIDEntry(String name, UUID defaultValue, UUID value)
    {
        super(name, defaultValue, value);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt)
    {
        nbt.setUniqueId(this.name, this.value);
    }

    @Override
    public void readNBT(NBTTagCompound nbt)
    {
        this.value = nbt.getUniqueId(this.name);
    }

    @Override
    public void writeBuffer(ByteBuf buffer)
    {
        ByteBufUtil.writeUUID(buffer, this.value);
    }

    @Override
    public void readBuffer(ByteBuf buffer)
    {
        this.value = ByteBufUtil.readUUID(buffer);
    }

    @Override
    public void writeData(DataOutput data) throws IOException
    {
        DataStreamUtil.writeUUID(data, this.value);
    }

    @Override
    public void readData(DataInput data) throws IOException
    {
        this.value = DataStreamUtil.readUUID(data);
    }

}
