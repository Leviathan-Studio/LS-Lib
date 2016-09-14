package com.leviathanstudio.lib.common.network.packet;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Sync Abstract Packet use in the LSL Network system. This packet process data
 * in the same way on client side and server side. Please add an empty
 * constructor for the system.
 */
public abstract class SyncAbstractPacket extends AbstractPacket
{

    @Override
    public AbstractPacket handleClientSide(EntityPlayer player)
    {
        return handle(player);
    }

    @Override
    public AbstractPacket handleServerSide(EntityPlayer player)
    {
        return handle(player);
    }

    /**
     * Handle the packet on both side
     * @param player The entity player
     * @return an abstract packet to send back to the player or null
     */
    public abstract AbstractPacket handle(EntityPlayer player);

}
