package com.leviathanstudio.lib.common.network.packet;

import com.leviathanstudio.lib.common.data2.DataManager;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Abstract Data Manager Packet use in the LSL Network system. This packet
 * process data contain in the data manager and handle serialization and
 * deserialization. Please add an empty. constructor for the system.
 */
public abstract class AbstractDataManagerPacket extends AbstractPacket
{
    private DataManager manager;

    public AbstractDataManagerPacket(DataManager manager)
    {
        this.manager = manager;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        this.manager.writeBuffer(buffer);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        this.manager.readBuffer(buffer);
    }

    public DataManager getManager()
    {
        return this.manager;
    }

}
