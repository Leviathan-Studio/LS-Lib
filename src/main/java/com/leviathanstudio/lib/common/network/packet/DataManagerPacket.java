package com.leviathanstudio.lib.common.network.packet;

import com.leviathanstudio.lib.common.data2.DataManagers;

import net.minecraft.entity.player.EntityPlayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Data Manager Packet use in the LSL Network system. This packet process data
 * contain in the data manager and handle serialization and deserialization.
 * Please add an empty. constructor for the system.
 */
public class DataManagerPacket extends SyncAbstractPacket
{
    private String name;

    public DataManagerPacket(String manager)
    {
        this.name = manager;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        DataManagers.getEntry(this.name).writeBuffer(buffer);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        DataManagers.getEntry(this.name).readBuffer(buffer);
    }

    @Override
    public AbstractPacket handle(EntityPlayer player)
    {
        return null;
    }

}
