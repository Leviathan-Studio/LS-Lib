package com.leviathanstudio.lib.common.network;

import java.util.EnumMap;
import java.util.List;

import com.leviathanstudio.lib.common.network.exception.SideException;
import com.leviathanstudio.lib.common.network.exception.UnregisteredPacketException;
import com.leviathanstudio.lib.common.network.packet.AbstractPacket;
import com.leviathanstudio.lib.common.util.MinecraftUtil;
import com.leviathanstudio.lib.common.world.WorldHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.FMLOutboundHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;

import io.netty.channel.ChannelFutureListener;

/**
 * Implemented version of ISendPacket with FML
 */
public abstract class SendPacketImpl implements ISendPacket
{
    /**
     * Get the channel to send packet
     * @return The Channel
     */
    public abstract EnumMap<Side, FMLEmbeddedChannel> getChannels();

    /**
     * Get the register packet list
     * @return The packets list
     */
    public abstract List<Class<? extends AbstractPacket>> getPackets();

    /**
     * Send this message to everyone.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     */
    @Override
    public void sendToAll(final AbstractPacket message)
    {
        // Verify the packet
        verifyMessage(message, Side.CLIENT);

        // Send the packet
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALL);
        this.getChannels().get(Side.SERVER).writeAndFlush(message)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to the specified player.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     * @param player
     *            The player to send it to
     */
    @Override
    public void sendTo(final AbstractPacket message, final EntityPlayerMP player)
    {
        // Verify the packet
        verifyMessage(message, Side.CLIENT);

        // Send the packet
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.PLAYER);
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
        this.getChannels().get(Side.SERVER).writeAndFlush(message)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * 
     * @param message
     *            The message to send
     * @param dimension
     *            The dimension instance
     * @param x
     *            The x position
     * @param y
     *            The y position
     * @param z
     *            The z position
     * @param range
     *            The range
     */
    @Override
    public void sendToAllAround(final AbstractPacket message, WorldServer dimension, double x, double y, double z,
            double range)
    {
        sendToAllAround(message, new NetworkRegistry.TargetPoint(WorldHelper.getWorldId(dimension), x, y, z, range));
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * 
     * @param message
     *            The message to send
     * @param dimension
     *            The dimension id
     * @param x
     *            The x position
     * @param y
     *            The y position
     * @param z
     *            The z position
     * @param range
     *            The range
     */
    @Override
    public void sendToAllAround(final AbstractPacket message, int dimension, double x, double y, double z, double range)
    {
        sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
    }

    /**
     * Send this message to everyone within a certain range of a point.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     * @param point
     *            The cpw.mods.fml.common.network.NetworkRegistry.TargetPoint
     *            around which to send
     */
    @Override
    public void sendToAllAround(final AbstractPacket message, final NetworkRegistry.TargetPoint point)
    {
        // Verify the packet
        verifyMessage(message, Side.CLIENT);

        // Send the packet
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
        this.getChannels().get(Side.SERVER).writeAndFlush(message)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     * @param dimensionId
     *            The dimension id to target
     */
    @Override
    public void sendToDimension(final AbstractPacket message, final int dimensionId)
    {
        // Verify the packet
        verifyMessage(message, Side.CLIENT);

        // Send the packet
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.DIMENSION);
        this.getChannels().get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
        this.getChannels().get(Side.SERVER).writeAndFlush(message)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Send this message to everyone within the supplied dimension.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     * @param dimension
     *            The dimension to target
     */
    @Override
    public void sendToDimension(final AbstractPacket message, final WorldServer dimension)
    {
        sendToDimension(message, WorldHelper.getWorldId(dimension));
    }

    /**
     * Send this message to all player in the target chunk
     * 
     * @param message
     *            The message to send
     * @param chunk
     *            The target chunk
     */
    @Override
    public void sendToAllIn(final AbstractPacket message, final Chunk chunk)
    {
        // Verify the packet
        verifyMessage(message, Side.CLIENT);

        // Send the packet
        List<EntityPlayer> playerList = chunk.getWorld().playerEntities;
        for (EntityPlayer player : playerList)
        {
            if (player.chunkCoordX == chunk.xPosition && player.chunkCoordZ == chunk.zPosition
                    && player instanceof EntityPlayerMP)
                // Send each packet
                sendTo(message, (EntityPlayerMP) player);
        }
    }

    /**
     * Send this message to the server.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     */
    @Override
    public void sendToServer(final AbstractPacket message)
    {
        // Verify the packet
        verifyMessage(message, Side.SERVER);

        // Send the packet
        this.getChannels().get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET)
                .set(FMLOutboundHandler.OutboundTarget.TOSERVER);
        this.getChannels().get(Side.CLIENT).writeAndFlush(message)
                .addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    /**
     * Verify if the packet can be send
     * 
     * @param message
     *            The message to send
     * @param side
     *            The side to process
     */
    private void verifyMessage(final AbstractPacket message, Side side)
    {
        // Send an exception if the packet isn't registered
        if (!isRegistered(message))
            invalidePacket(message);

        // Check side to process
        switch (side)
        {
            case SERVER:
                // Send an exception if the method is call from the server, it's
                // stupid
                if (MinecraftUtil.getEffectiveSide().isServer())
                    invalideSide("You are on the server");
                break;
            case CLIENT:
                // Send an exception if the method is call from the client, it's
                // stupid
                if (MinecraftUtil.getEffectiveSide().isClient())
                    invalideSide("You are on the client");
                break;
        }
    }

    /**
     * Send an exception for the used Side
     * 
     * @param messgae
     *            The text message
     */
    private void invalideSide(String messgae)
    {
        throw new SideException(messgae);
    }

    /**
     * Send an exception for the unregistered packet
     * 
     * @param messgae
     *            The packet
     */
    private void invalidePacket(AbstractPacket messgae)
    {
        throw new UnregisteredPacketException(messgae.getClass());
    }

    /**
     * Check if a packet is registered
     * 
     * @param message
     *            The packet
     * @return true if the packet is registered, false else
     */
    public boolean isRegistered(final AbstractPacket message)
    {
        return this.getPackets().contains(message.getClass());
    }
}
