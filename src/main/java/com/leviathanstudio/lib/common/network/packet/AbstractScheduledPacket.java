package com.leviathanstudio.lib.common.network.packet;

import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Abstract Scheduled Packet use in the LSL Network system. This packet process
 * data in the same way on client side and server side with Scheduled Task.
 * Please add an empty. constructor for the system. This packet can't send back
 * packet.
 */
public abstract class AbstractScheduledPacket extends AbstractPacket implements Runnable
{
    private EntityPlayer player = null;

    @Override
    public AbstractPacket handleClientSide(EntityPlayer player)
    {
        this.player = player;
        MinecraftUtil.getClient().addScheduledTask(this);
        return null;
    }

    @Override
    public AbstractPacket handleServerSide(EntityPlayer player)
    {
        this.player = player;
        MinecraftUtil.getServer().addScheduledTask(this);
        return null;
    }

    @Override
    public void run()
    {
        this.handle(this.player);
    }

    /**
     * Handle the packet on both side when task is called
     * @param player The entity player
     */
    public abstract void handle(EntityPlayer player);
}
