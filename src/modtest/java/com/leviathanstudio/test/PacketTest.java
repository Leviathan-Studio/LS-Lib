package com.leviathanstudio.test;

import com.leviathanstudio.lib.common.network.packet.AbstractPacket;

import net.minecraft.entity.player.EntityPlayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketTest extends AbstractPacket
{
    int test;

    public PacketTest()
    {
    }

    public PacketTest(int test)
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
        System.out.println("client " + this.test);
        return null;
    }

    @Override
    public AbstractPacket handleServerSide(EntityPlayer player)
    {
        System.out.println("server " + (this.test) + " -> " + ++this.test);
        return new PacketTest(this.test);
    }

}
