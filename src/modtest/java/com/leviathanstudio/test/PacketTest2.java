package com.leviathanstudio.test;

import com.leviathanstudio.lib.common.network.packet.AbstractPacket;

import net.minecraft.entity.player.EntityPlayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketTest2 extends AbstractPacket
{
    int test;

    public PacketTest2()
    {
    }

    public PacketTest2(int test)
    {
        this.test = test;
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        buffer.writeInt(test);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
    {
        this.test = buffer.readInt();
    }

    @Override
    public AbstractPacket handleClientSide(EntityPlayer player)
    {
        System.out.println("client " + (this.test) + " -> " + ++this.test);
        return new PacketTest2(this.test);
    }

    @Override
    public AbstractPacket handleServerSide(EntityPlayer player)
    {
        System.out.println("server " + this.test);
        return null;
    }

}
