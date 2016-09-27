//@formatter:off
package com.leviathanstudio.lib.common.data2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.leviathanstudio.lib.common.data2.interfaces.bytebuf.IByteBufSerializer;
import com.leviathanstudio.lib.common.data2.interfaces.nbt.INBTSerializer;
import com.leviathanstudio.lib.common.data2.interfaces.stream.IStreamSerializer;
import com.leviathanstudio.lib.common.data2.interfaces.string.IStringSerializer;
import com.leviathanstudio.lib.common.data2.util.DataStreamUtil;
import com.leviathanstudio.lib.common.data2.util.NBTUtil;
import com.leviathanstudio.lib.common.network.ByteBufUtil;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import io.netty.buffer.ByteBuf;

public abstract class DataType<T> implements IStringSerializer<T>, INBTSerializer<T>, IStreamSerializer<T>, IByteBufSerializer<T>
{
    public static final DataType<Boolean>        BOOLEAN    = new DataType<Boolean>()
    {

        @Override
        public Boolean readString(String text)
        {
            return Boolean.parseBoolean(text);
        }

        @Override
        public String writeString(Boolean value)
        {
            return value ? "true" : "false";
        }

        @Override
        public boolean checkParseType(String text)
        {
            return text.equalsIgnoreCase("true") || text.equalsIgnoreCase("false");
        }

        @Override
        public Boolean readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getBoolean(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Boolean value)
        {
            nbt.setBoolean(name, value);
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagByte;
        }

        @Override
        public Boolean readStream(DataInput data) throws IOException
        {
            return data.readBoolean();
        }

        @Override
        public void writeStream(DataOutput data, Boolean value) throws IOException
        {
            data.writeBoolean(value);
        }

        @Override
        public Boolean readBuffer(ByteBuf buffer)
        {
            return buffer.readBoolean();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Boolean value)
        {
            buffer.writeBoolean(value);
        }

        @Override
        public String getType()
        {
            return "boolean";
        }
    };
    
    
    public static final DataType<Byte>           BYTE       = new DataType<Byte>()
    {

        @Override
        public Byte readString(String text)
        {
            return Byte.parseByte(text);
        }

        @Override
        public String writeString(Byte value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            try
            {
                readString(text);
                return true;
            } catch (Exception e)
            {
                return false;
            }        
        }

        @Override
        public Byte readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getByte(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Byte value)
        {
            nbt.setByte(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagByte;
        }

        @Override
        public Byte readStream(DataInput data) throws IOException
        {
            return data.readByte();
        }

        @Override
        public void writeStream(DataOutput data, Byte value) throws IOException
        {
            data.writeByte(value);      
        }

        @Override
        public Byte readBuffer(ByteBuf buffer)
        {
            return buffer.readByte();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Byte value)
        {
            buffer.writeByte(value);
        }

        @Override
        public String getType()
        {
            return "byte";
        }
    };
    
    
    public static final DataType<Short>          SHORT      = new DataType<Short>()
    {
        @Override
        public Short readString(String text)
        {
            return Short.parseShort(text);
        }

        @Override
        public String writeString(Short value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            try
            {
                readString(text);
                return true;
            } catch (Exception e)
            {
                return false;
            }        
        }

        @Override
        public Short readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getShort(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Short value)
        {
            nbt.setShort(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagShort;
        }

        @Override
        public Short readStream(DataInput data) throws IOException
        {
            return data.readShort();
        }

        @Override
        public void writeStream(DataOutput data, Short value) throws IOException
        {
            data.writeShort(value);      
        }

        @Override
        public Short readBuffer(ByteBuf buffer)
        {
            return buffer.readShort();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Short value)
        {
            buffer.writeShort(value);
        }
        
        @Override
        public String getType()
        {
            return "short";
        }
    };
    
    
    public static final DataType<Integer>        INTEGER    = new DataType<Integer>()
    {
        @Override
        public Integer readString(String text)
        {
            return Integer.parseInt(text);
        }

        @Override
        public String writeString(Integer value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            try
            {
                readString(text);
                return true;
            } catch (Exception e)
            {
                return false;
            }        
        }

        @Override
        public Integer readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getInteger(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Integer value)
        {
            nbt.setInteger(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagInt;
        }

        @Override
        public Integer readStream(DataInput data) throws IOException
        {
            return data.readInt();
        }

        @Override
        public void writeStream(DataOutput data, Integer value) throws IOException
        {
            data.writeInt(value);      
        }

        @Override
        public Integer readBuffer(ByteBuf buffer)
        {
            return buffer.readInt();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Integer value)
        {
            buffer.writeInt(value);
        }
        
        @Override
        public String getType()
        {
            return "integer";
        }
    };
    
    public static final DataType<Long>           LONG       = new DataType<Long>()
    {
        @Override
        public Long readString(String text)
        {
            return Long.parseLong(text);
        }

        @Override
        public String writeString(Long value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            try
            {
                readString(text);
                return true;
            } catch (Exception e)
            {
                return false;
            }        
        }

        @Override
        public Long readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getLong(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Long value)
        {
            nbt.setLong(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagLong;
        }

        @Override
        public Long readStream(DataInput data) throws IOException
        {
            return data.readLong();
        }

        @Override
        public void writeStream(DataOutput data, Long value) throws IOException
        {
            data.writeLong(value);      
        }

        @Override
        public Long readBuffer(ByteBuf buffer)
        {
            return buffer.readLong();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Long value)
        {
            buffer.writeLong(value);
        }
        
        @Override
        public String getType()
        {
            return "long";
        }
    };
    
    public static final DataType<Float>          FLOAT      = new DataType<Float>()
    {
        @Override
        public Float readString(String text)
        {
            return Float.parseFloat(text);
        }

        @Override
        public String writeString(Float value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            try
            {
                readString(text);
                return true;
            } catch (Exception e)
            {
                return false;
            }        
        }

        @Override
        public Float readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getFloat(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Float value)
        {
            nbt.setFloat(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagFloat;
        }

        @Override
        public Float readStream(DataInput data) throws IOException
        {
            return data.readFloat();
        }

        @Override
        public void writeStream(DataOutput data, Float value) throws IOException
        {
            data.writeFloat(value);      
        }

        @Override
        public Float readBuffer(ByteBuf buffer)
        {
            return buffer.readFloat();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Float value)
        {
            buffer.writeFloat(value);
        }
        
        @Override
        public String getType()
        {
            return "float";
        }
    };
    
    public static final DataType<Double>         DOUBLE     = new DataType<Double>()
    {
        @Override
        public Double readString(String text)
        {
            return Double.parseDouble(text);
        }

        @Override
        public String writeString(Double value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            try
            {
                readString(text);
                return true;
            } catch (Exception e)
            {
                return false;
            }        
        }

        @Override
        public Double readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getDouble(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Double value)
        {
            nbt.setDouble(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagDouble;
        }

        @Override
        public Double readStream(DataInput data) throws IOException
        {
            return data.readDouble();
        }

        @Override
        public void writeStream(DataOutput data, Double value) throws IOException
        {
            data.writeDouble(value);      
        }

        @Override
        public Double readBuffer(ByteBuf buffer)
        {
            return buffer.readDouble();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Double value)
        {
            buffer.writeDouble(value);
        }
        
        @Override
        public String getType()
        {
            return "double";
        }
    };

    public static final DataType<String>         STRING     = new DataType<String>()
    {
        @Override
        public String readString(String text)
        {
            return text;
        }

        @Override
        public String writeString(String value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
           return true;      
        }

        @Override
        public String readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getString(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, String value)
        {
            nbt.setString(name, value);            
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagString;
        }

        @Override
        public String readStream(DataInput data) throws IOException
        {
            return data.readUTF();
        }

        @Override
        public void writeStream(DataOutput data, String value) throws IOException
        {
            data.writeUTF(value);     
        }

        @Override
        public String readBuffer(ByteBuf buffer)
        {
            return ByteBufUtils.readUTF8String(buffer);
        }

        @Override
        public void writeBuffer(ByteBuf buffer, String value)
        {
            ByteBufUtils.writeUTF8String(buffer, value);
        }
        
        @Override
        public String getType()
        {
            return "string";
        }
    };

   
    public static final DataType<Character>      CHARACTER  = new DataType<Character>()
    {
        @Override
        public Character readString(String text)
        {
            return text.toCharArray()[0];
        }

        @Override
        public String writeString(Character value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            return text.length() == 1;       
        }

        @Override
        public Character readNBT(NBTTagCompound nbt, String name)
        {
            return (char) nbt.getInteger(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, Character value)
        {
            nbt.setInteger(name, Character.getNumericValue(value));
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagInt;
        }

        @Override
        public Character readStream(DataInput data) throws IOException
        {
            return data.readChar();
        }

        @Override
        public void writeStream(DataOutput data, Character value) throws IOException
        {
            data.writeChar(value);      
        }

        @Override
        public Character readBuffer(ByteBuf buffer)
        {
            return buffer.readChar();
        }

        @Override
        public void writeBuffer(ByteBuf buffer, Character value)
        {
            buffer.writeChar(value);
        }
        
        @Override
        public String getType()
        {
            return "character";
        }
    };
    
    
    public static final DataType<UUID>           UUID       = new DataType<UUID>()
    {
        @Override
        public UUID readString(String text)
        {
            return java.util.UUID.fromString(text);
        }

        @Override
        public String writeString(UUID value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            return text.length() == 36;       
        }

        @Override
        public UUID readNBT(NBTTagCompound nbt, String name)
        {
            return nbt.getUniqueId(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, UUID value)
        {
            nbt.setUniqueId(name, value);
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagLong;
        }

        @Override
        public UUID readStream(DataInput data) throws IOException
        {
            return DataStreamUtil.readUUID(data);
        }

        @Override
        public void writeStream(DataOutput data, UUID value) throws IOException
        {
            DataStreamUtil.writeUUID(data, value);
        }

        @Override
        public UUID readBuffer(ByteBuf buffer)
        {
            return ByteBufUtil.readUUID(buffer);
        }

        @Override
        public void writeBuffer(ByteBuf buffer, UUID value)
        {
            ByteBufUtil.writeUUID(buffer, value);
        }
        
        @Override
        public String getType()
        {
            return "UUID";
        }
    };
    
    public static final DataType<BlockPos>       BLOCK_POS  = new DataType<BlockPos>()
    {
        String regex = "^BlockPos{x=-?[0-9]+, y=[0-9]+, z=-?[0-9]+}$";
        
        @Override
        public BlockPos readString(String text)
        {
            Matcher m = Pattern.compile(regex).matcher(text);
            return new BlockPos(Double.parseDouble(m.group(1)), Double.parseDouble(m.group(2)), Double.parseDouble(m.group(3)));
        }

        @Override
        public String writeString(BlockPos value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            return text.matches(regex);
        }

        @Override
        public BlockPos readNBT(NBTTagCompound nbt, String name)
        {
            return NBTUtil.readBlockPos(nbt, name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, BlockPos value)
        {
            NBTUtil.writeBlockPos(nbt, name, value);
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagLong;
        }

        @Override
        public BlockPos readStream(DataInput data) throws IOException
        {
            return DataStreamUtil.readBlockPos(data);
        }

        @Override
        public void writeStream(DataOutput data, BlockPos value) throws IOException
        {
            DataStreamUtil.writeBlockPos(data, value);
        }

        @Override
        public BlockPos readBuffer(ByteBuf buffer)
        {
            return ByteBufUtil.readBlockPos(buffer);
        }

        @Override
        public void writeBuffer(ByteBuf buffer, BlockPos value)
        {
            ByteBufUtil.writeBlockPos(buffer, value);
        }
        
        @Override
        public String getType()
        {
            return "BlockPos";
        }
    };
    
    public static final DataType<ItemStack>      ITEM_STACK = new DataType<ItemStack>()
    {       
        @Override
        public ItemStack readString(String text)
        {
            unsupportedOperation();
            return null;
        }

        @Override
        public String writeString(ItemStack value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            return false;
        }

        @Override
        public ItemStack readNBT(NBTTagCompound nbt, String name)
        {
            return ItemStack.loadItemStackFromNBT((NBTTagCompound) nbt.getTag(name));
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, ItemStack value)
        {
            nbt.setTag(name, value.writeToNBT(new NBTTagCompound()));
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagCompound;
        }

        @Override
        public ItemStack readStream(DataInput data) throws IOException
        {
            return DataStreamUtil.readItemStack(data);
        }

        @Override
        public void writeStream(DataOutput data, ItemStack value) throws IOException
        {
            DataStreamUtil.writeItemStack(data, value);
        }

        @Override
        public ItemStack readBuffer(ByteBuf buffer)
        {
            return ByteBufUtils.readItemStack(buffer);
        }

        @Override
        public void writeBuffer(ByteBuf buffer, ItemStack value)
        {
            ByteBufUtils.writeItemStack(buffer, value);
        }
        
        @Override
        public String getType()
        {
            return "ItemStack";
        }
    };
    
    public static final DataType<NBTTagCompound> TAG        = new DataType<NBTTagCompound>()
    {       
        @Override
        public NBTTagCompound readString(String text)
        {
            unsupportedOperation();
            return null;
        }

        @Override
        public String writeString(NBTTagCompound value)
        {
            return value.toString();
        }

        @Override
        public boolean checkParseType(String text)
        {
            return false;
        }

        @Override
        public NBTTagCompound readNBT(NBTTagCompound nbt, String name)
        {
            return (NBTTagCompound) nbt.getTag(name);
        }

        @Override
        public void writeNBT(NBTTagCompound nbt, String name, NBTTagCompound value)
        {
            nbt.setTag(name, value);
        }

        @Override
        public boolean checkTagType(NBTBase tag)
        {
            return tag instanceof NBTTagCompound;
        }

        @Override
        public NBTTagCompound readStream(DataInput data) throws IOException
        {
            return DataStreamUtil.readTag(data);
        }

        @Override
        public void writeStream(DataOutput data, NBTTagCompound value) throws IOException
        {
            DataStreamUtil.writeTag(data, value);
        }

        @Override
        public NBTTagCompound readBuffer(ByteBuf buffer)
        {
            return ByteBufUtils.readTag(buffer);
        }

        @Override
        public void writeBuffer(ByteBuf buffer, NBTTagCompound value)
        {
            ByteBufUtils.writeTag(buffer, value);
        }
        
        @Override
        public String getType()
        {
            return "NBTTagCompound";
        }
    };
            
    public abstract String getType();
    
    @SuppressWarnings("unchecked")
    public void writeBufferIn(ByteBuf buffer, Object value)
    {
        this.writeBuffer(buffer, (T)value);
    }
    
    @SuppressWarnings("unchecked")
    public void writeNBTIn(NBTTagCompound buffer, String name, Object value)
    {
        this.writeNBT(buffer, name, (T)value);
    }
    
    @SuppressWarnings("unchecked")
    public void writeStreamIn(DataOutput stream, Object value) throws IOException
    {
        this.writeStream(stream, (T)value);
    }    

    @Override
    public String toString()
    {
        return "data type: " + getType();
    }
    
    private static void unsupportedOperation()
    {
        throw new UnsupportedOperationException();
    }
}
