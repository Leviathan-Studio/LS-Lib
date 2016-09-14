package com.leviathanstudio.lib.common.server.command;

import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraft.command.CommandResultStats.Type;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

/**
 * A special command sender to allow player to execute command with
 * administrator right
 */
public class AdminCommandSender implements ICommandSender
{
    public final EntityPlayer player;

    public AdminCommandSender(EntityPlayer player)
    {
        this.player = player;
    }

    @Override
    public String getName()
    {
        return this.player.getName();
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return this.player.getDisplayName();
    }

    @Override
    public void addChatMessage(ITextComponent component)
    {

    }

    @Override
    public boolean canCommandSenderUseCommand(int permLevel, String commandName)
    {
        return true;
    }

    @Override
    public BlockPos getPosition()
    {
        return this.player.playerLocation;
    }

    @Override
    public Vec3d getPositionVector()
    {
        return this.player.getPositionVector();
    }

    @Override
    public World getEntityWorld()
    {
        return this.player.getEntityWorld();
    }

    @Override
    public Entity getCommandSenderEntity()
    {
        return this.player;
    }

    @Override
    public boolean sendCommandFeedback()
    {
        return false;
    }

    @Override
    public void setCommandStat(Type type, int amount)
    {
    }

    @Override
    public MinecraftServer getServer()
    {
        return MinecraftUtil.getServer();
    }

}