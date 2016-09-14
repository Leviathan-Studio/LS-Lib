package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import io.netty.handler.codec.EncoderException;

public class DataStreamUtil
{
    /**
     * Write a BlockPos into a DataOutput
     * 
     * @param data
     *            The DataOutput
     * @param pos
     *            The BlockPos
     * @throws IOException
     *             if error
     */
    public static void writeBlockPos(DataOutput data, BlockPos pos) throws IOException
    {
        data.writeInt(pos.getX());
        data.writeInt(pos.getY());
        data.writeInt(pos.getZ());
    }

    /**
     * Read a BlockPos from a DataInput
     * 
     * @param data
     *            The DataInput
     * @return The BlockPos
     * @throws IOException
     *             if error
     */
    public static BlockPos readBlockPos(DataInput data) throws IOException
    {
        int x = data.readInt();
        int y = data.readInt();
        int z = data.readInt();

        return new BlockPos(x, y, z);
    }

    /**
     * Write a UUID into a DataOutput
     * 
     * @param data
     *            The DataOutput
     * @param uuid
     *            The UUID to write
     * @throws IOException
     *             if error
     * 
     */
    public static void writeUUID(DataOutput data, UUID uuid) throws IOException
    {
        String stringUUID = uuid.toString();
        data.writeUTF(stringUUID);
    }

    /**
     * Read a UUID from a DataInput
     * 
     * @param data
     *            The DataInput
     * @return The UUID
     * @throws IOException
     *             if error
     */
    public static UUID readUUID(DataInput data) throws IOException
    {
        String stringUUID = data.readUTF();
        return UUID.fromString(stringUUID);
    }

    /**
     * Write a NBTTagCompound into a DataOutput
     * 
     * @param data
     *            The DataOutput
     * @param nbt
     *            The NBTTagCompound to write
     * @throws IOException
     *             if error
     * 
     */
    public static void writeTag(DataOutput data, @Nullable NBTTagCompound nbt) throws IOException
    {
        if (nbt == null)
        {
            data.writeByte(0);
        }
        else
        {
            data.writeByte(1);
            try
            {
                CompressedStreamTools.write(nbt, data);
            } catch (IOException ioexception)
            {
                throw new EncoderException(ioexception);
            }
        }
    }

    /**
     * Read a NBTTagCompound from a DataInput
     * 
     * @param data
     *            The DataInput
     * @return The NBTTagCompound
     * @throws IOException
     *             if error
     */
    public static @Nullable NBTTagCompound readTag(DataInput data) throws IOException
    {
        byte b0 = data.readByte();

        if (b0 == 0)
        {
            return null;
        }
        else
        {
            try
            {
                return CompressedStreamTools.read(data, new NBTSizeTracker(2097152L));
            } catch (IOException ioexception)
            {
                throw new EncoderException(ioexception);
            }
        }
    }

    /**
     * Write a ItemStack into a DataOutput
     * 
     * @param data
     *            The DataOutput
     * @param stack
     *            The ItemStack to write
     * @throws IOException
     *             if error
     * 
     */
    public static void writeItemStack(DataOutput data, @Nullable ItemStack stack) throws IOException
    {
        if (stack == null)
        {
            data.writeShort(-1);
        }
        else
        {
            data.writeShort(Item.getIdFromItem(stack.getItem()));
            data.writeByte(stack.stackSize);
            data.writeShort(stack.getMetadata());
            NBTTagCompound nbttagcompound = null;

            if (stack.getItem().isDamageable() || stack.getItem().getShareTag())
            {
                nbttagcompound = stack.getTagCompound();
            }

            writeTag(data, nbttagcompound);
        }

    }

    /**
     * Read a ItemStack from a DataInput
     * 
     * @param data
     *            The DataInput
     * @return The ItemStack
     * @throws IOException
     *             if error
     */
    public static @Nullable ItemStack readItemStack(DataInput data) throws IOException
    {
        ItemStack itemstack = null;
        int i = data.readShort();

        if (i >= 0)
        {
            int j = data.readByte();
            int k = data.readShort();
            itemstack = new ItemStack(Item.getItemById(i), j, k);
            itemstack.setTagCompound(readTag(data));
        }

        return itemstack;
    }

}
