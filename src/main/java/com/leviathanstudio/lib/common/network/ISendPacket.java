package com.leviathanstudio.lib.common.network;

import com.leviathanstudio.lib.common.network.packet.AbstractPacket;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public interface ISendPacket
{
    /**
     * Send this message to everyone.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     */
    public void sendToAll(final AbstractPacket message);

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
    public void sendTo(final AbstractPacket message, final EntityPlayerMP player);

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
    public void sendToAllAround(final AbstractPacket message, WorldServer dimension, double x, double y,
            double z, double range);

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
    public void sendToAllAround(final AbstractPacket message, int dimension, double x, double y, double z,
            double range);

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
    public void sendToAllAround(final AbstractPacket message, final NetworkRegistry.TargetPoint point);

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
    public void sendToDimension(final AbstractPacket message, final int dimensionId);

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
    public void sendToDimension(final AbstractPacket message, final WorldServer dimension);

    /**
     * Send this message to all player in the target chunk
     * 
     * @param message
     *            The message to send
     * @param chunk
     *            The target chunk
     */
    public void sendToAllIn(final AbstractPacket message, final Chunk chunk);

    /**
     * Send this message to the server.
     * 
     * Adapted from CPW's code in
     * cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
     *
     * @param message
     *            The message to send
     */
    public void sendToServer(final AbstractPacket message);

}
