package com.leviathanstudio.lib.common.network;

import com.leviathanstudio.lib.common.network.packet.AbstractPacket;

import net.minecraftforge.fml.common.network.FMLIndexedMessageToMessageCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;

@Sharable
public class LSLMessageHandler extends FMLIndexedMessageToMessageCodec<AbstractPacket>
{
    @Override
    public void encodeInto(ChannelHandlerContext ctx, AbstractPacket msg, ByteBuf buffer) throws Exception
    {
        msg.encodeInto(ctx, buffer);
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer, AbstractPacket msg)
    {
        msg.decodeInto(ctx, buffer);
    }

}
