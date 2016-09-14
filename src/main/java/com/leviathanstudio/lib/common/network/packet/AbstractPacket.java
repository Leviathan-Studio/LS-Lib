package com.leviathanstudio.lib.common.network.packet;

import net.minecraft.entity.player.EntityPlayer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * Abstract Packet use in the LSL Network system. Please add an empty
 * constructor for the system.
 */
public abstract class AbstractPacket
{
    /**
     * Encode the message
     * @param ctx The channel context
     * @param buffer The buffer which contain the message
     */
    public abstract void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    /**
     * Decode the message
     * @param ctx The channel context
     * @param buffer The buffer which contain the message
     */
    public abstract void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer);

    /**
     * Handle the packet on the client side
     * @param player The entity player
     * @return an abstract packet to send back to the server or null
     */
    public abstract AbstractPacket handleClientSide(EntityPlayer player);

    /**
     * Handle the packet on the server side
     * @param player The entity player
     * @return an abstract packet to send back to the player or null
     */
    public abstract AbstractPacket handleServerSide(EntityPlayer player);
}
