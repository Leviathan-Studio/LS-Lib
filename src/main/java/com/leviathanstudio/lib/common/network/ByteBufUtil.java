package com.leviathanstudio.lib.common.network;

import java.util.UUID;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;

public class ByteBufUtil extends ByteBufUtils
{
    /**
     * Read a UUID from a ByteBuf
     * 
     * @param buffer
     *            The byteBuf
     * @return The UUID
     */
    public static UUID readUUID(ByteBuf buffer)
    {
        String stringUUID = ByteBufUtils.readUTF8String(buffer);
        return UUID.fromString(stringUUID);
    }

    /**
     * Write a UUID to a ByteBuf
     * 
     * @param buffer
     *            The byteBuf
     * @param uuid
     *            The UUID to write
     */
    public static void writeUUID(ByteBuf buffer, UUID uuid)
    {
        String stringUUID = uuid.toString();
        ByteBufUtils.writeUTF8String(buffer, stringUUID);
    }

    /**
     * Read a BlockPos from a ByteBuf
     * 
     * @param buffer
     *            The byteBuf
     * @return The BlockPos
     */
    public static BlockPos readBlockPos(ByteBuf buffer)
    {
        int x = buffer.readInt();
        int y = buffer.readInt();
        int z = buffer.readInt();

        return new BlockPos(x, y, z);
    }

    /**
     * Write a BlockPos to a ByteBuf
     * 
     * @param buffer
     *            The byteBuf
     * @param pos
     *            The BlockPos to write
     */
    public static void writeBlockPos(ByteBuf buffer, BlockPos pos)
    {
        buffer.writeInt(pos.getX());
        buffer.writeInt(pos.getY());
        buffer.writeInt(pos.getZ());
    }
}
