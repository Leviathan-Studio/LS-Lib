package com.leviathanstudio.lib.common.network;

import com.leviathanstudio.lib.common.network.packet.AbstractPacket;
import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Sharable
public class LSLChannelHandler extends SimpleChannelInboundHandler<AbstractPacket>
{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractPacket pkt) throws Exception
    {
        AbstractPacket callback = null;
        EntityPlayer player;

        switch (MinecraftUtil.getEffectiveSide())
        {
            case CLIENT:
                // Get needed information
                player = this.getClientPlayer();
                // Handle packet
                callback = pkt.handleClientSide(player);
                break;
            case SERVER:
                // Get needed information
                final INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
                player = ((NetHandlerPlayServer) netHandler).playerEntity;
                // Handle packet
                callback = pkt.handleServerSide(player);
                break;
            default:
                // Nothing
                break;
        }

        // Send callback
        if (callback != null)
        {
            ctx.channel().attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.REPLY);
            ctx.writeAndFlush(callback).addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        }

    }

    @SideOnly(Side.CLIENT)
    private EntityPlayer getClientPlayer()
    {
        return MinecraftUtil.getClient().thePlayer;
    }

}
