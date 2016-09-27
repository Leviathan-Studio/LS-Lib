package com.leviathanstudio.lib.common.data2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import io.netty.buffer.ByteBuf;
import jline.internal.Nullable;

public class DataManager
{
    private final Map<String, DataType<?>> dataType;
    private final Map<String, Object>      dataValue;

    // *********************************************************************************************

    /**
     * Private constructor use by default
     */
    private DataManager()
    {
        this.dataType = Maps.newHashMap();
        this.dataValue = Maps.newHashMap();
    }

    /**
     * Private constructor use to copy an instance
     * 
     * @param manager
     *            the manager to copy
     */
    private DataManager(DataManager manager)
    {
        this.dataType = Maps.newHashMap(manager.dataType);
        this.dataValue = Maps.newHashMap(manager.dataValue);
    }

    // *********************************************************************************************

    /**
     * Create a new instance of DataManager
     * 
     * @return A new instance of DataManager
     */
    public static DataManager create()
    {
        return new DataManager();
    }

    /**
     * Create a copy of a DataManager
     * 
     * @param manager
     *            The manager to copy
     * @return A new instance of DataManager
     */
    public static DataManager create(DataManager manager)
    {
        if (manager != null)
            return new DataManager(manager);
        else
            return create();
    }

    // *********************************************************************************************

    /**
     * Create a copy of data manager
     * 
     * @return A copy of data manager
     */
    public DataManager copy()
    {
        return create(this);
    }

    /**
     * @return The number of entries
     */
    public int size()
    {
        return this.dataValue.size();
    }

    /**
     * Return true if, and only if, size = 0
     * 
     * @return True if size = 0
     */
    public boolean isEmpty()
    {
        return this.size() == 0;
    }

    /**
     * Remove all entry
     */
    public void clear()
    {
        this.dataValue.clear();
        this.dataType.clear();
    }

    /**
     * Remove a specific entry
     * 
     * @param name
     *            The name of the entry
     * @return True if an element has been remove else false
     */
    public boolean remove(String name)
    {
        if (hasEntry(name))
        {
            this.dataValue.remove(name);
            this.dataType.remove(name);
            return true;
        }
        return false;
    }

    // *********************************************************************************************

    /**
     * Test if an entry is present
     * 
     * @param key
     *            The name of the entry
     * @return true if contains the entry else false
     * @throws IllegalArgumentException
     *             If the key isn't valid
     */
    public boolean hasEntry(String key)
    {
        isValidKey(key);
        return this.dataValue.containsKey(key);
    }

    /**
     * Get the type of an entry by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The type of the entry or null
     * @throws IllegalArgumentException
     *             If the key isn't valid
     */
    public @Nullable DataType<?> getType(String key)
    {
        isValidKey(key);
        return this.dataType.getOrDefault(key, null);
    }

    /**
     * Get the value of an entry by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry or null
     * @throws IllegalArgumentException
     *             If the key isn't valid
     */
    public @Nullable Object getValue(String key)
    {
        isValidKey(key);
        return this.dataValue.getOrDefault(key, null);
    }

    /**
     * Replace a value
     * 
     * @param key
     *            The name of the entry
     * @param oldValue
     *            The old value
     * @param newValue
     *            The new value
     * @return True if the value has changed
     */
    public boolean replaceValue(String key, Object oldValue, Object newValue)
    {
        // same value
        if (oldValue.equals(newValue))
            return false;
        this.dataValue.replace(key, newValue);
        return true;
    }

    /**
     * Replace a value
     * 
     * @param key
     *            The name of the entry
     * @param newValue
     *            The new value
     * @return True if the value has changed
     */
    public boolean replaceValue(String key, Object newValue)
    {
        return this.replaceValue(key, this.getValue(key), newValue);
    }

    // *********************************************************************************************

    /**
     * Add a new boolean value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addBoolean(String key, boolean value)
    {
        return addValue(key, DataType.BOOLEAN, value);
    }

    /**
     * Add a new byte value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addByte(String key, byte value)
    {
        return addValue(key, DataType.BYTE, value);
    }

    /**
     * Add a new byte value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addByte(String key, int value)
    {
        if (value > Byte.MAX_VALUE || value < Byte.MIN_VALUE)
            invalidValue(key, value);
        return addByte(key, (byte) value);
    }

    /**
     * Add a new short value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addShort(String key, short value)
    {
        return addValue(key, DataType.SHORT, value);
    }

    /**
     * Add a new short value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addShort(String key, int value)
    {
        if (value > Short.MAX_VALUE || value < Short.MIN_VALUE)
            invalidValue(key, value);
        return addShort(key, (short) value);
    }

    /**
     * Add a new integer value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addInteger(String key, int value)
    {
        return addValue(key, DataType.INTEGER, value);
    }

    /**
     * Add a new long value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addLong(String key, long value)
    {
        return addValue(key, DataType.LONG, value);
    }

    /**
     * Add a new float value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addFloat(String key, float value)
    {
        return addValue(key, DataType.FLOAT, value);
    }

    /**
     * Add a new double value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addDouble(String key, double value)
    {
        return addValue(key, DataType.DOUBLE, value);
    }

    /**
     * Add a new character value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addCharacter(String key, char value)
    {
        return addValue(key, DataType.CHARACTER, value);
    }

    /**
     * Add a new string value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addString(String key, String value)
    {
        return addValue(key, DataType.STRING, value);
    }

    /**
     * Add a new UUID value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addUUID(String key, UUID value)
    {
        return addValue(key, DataType.UUID, value);
    }

    /**
     * Add a new BlockPos value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addBlockPos(String key, BlockPos value)
    {
        return addValue(key, DataType.BLOCK_POS, value);
    }

    /**
     * Add a new ItemStack value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addItemStack(String key, ItemStack value)
    {
        return addValue(key, DataType.ITEM_STACK, value);
    }

    /**
     * Add a new NBTTagCompound value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    public boolean addTag(String key, NBTTagCompound value)
    {
        return addValue(key, DataType.TAG, value);
    }

    /**
     * Add a new value to the system
     * 
     * @param key
     *            The name of the entry
     * @param type
     *            The type of the entry
     * @param value
     *            The value of the entry
     * @return True if value has been add to the system else false
     */
    private boolean addValue(String key, DataType<?> type, Object value)
    {
        // Key already exists
        if (this.hasEntry(key))
            return false;

        // Add all the informations
        this.dataValue.put(key, value);
        this.dataType.put(key, type);
        return true;
    }

    // *********************************************************************************************

    /**
     * Get a boolean value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public boolean getBoolean(String key)
    {
        isValid(key, DataType.BOOLEAN);
        return ((Boolean) this.getValue(key));
    }

    /**
     * Get a byte value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public byte getByte(String key)
    {
        isValid(key, DataType.BYTE);
        return ((Number) this.getValue(key)).byteValue();
    }

    /**
     * Get a short value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public short getShort(String key)
    {
        isValid(key, DataType.SHORT);
        return ((Number) this.getValue(key)).shortValue();
    }

    /**
     * Get an integer value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public int getInteger(String key)
    {
        isValid(key, DataType.INTEGER);
        return ((Number) this.getValue(key)).intValue();
    }

    /**
     * Get a long value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public long getLong(String key)
    {
        isValid(key, DataType.LONG);
        return ((Number) this.getValue(key)).longValue();
    }

    /**
     * Get a float value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public float getFloat(String key)
    {
        isValid(key, DataType.FLOAT);
        return ((Number) this.getValue(key)).floatValue();
    }

    /**
     * Get a double value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public double getDouble(String key)
    {
        isValid(key, DataType.DOUBLE);
        return ((Number) this.getValue(key)).doubleValue();
    }

    /**
     * Get a character value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public char getCharacter(String key)
    {
        isValid(key, DataType.CHARACTER);
        return ((char) this.getValue(key));
    }

    /**
     * Get a string value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public String getString(String key)
    {
        isValid(key, DataType.STRING);
        return ((String) this.getValue(key));
    }

    /**
     * Get a UUID value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public UUID getUUID(String key)
    {
        isValid(key, DataType.UUID);
        return ((UUID) this.getValue(key));
    }

    /**
     * Get a BlockPos value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public BlockPos getBlockPos(String key)
    {
        isValid(key, DataType.BLOCK_POS);
        return ((BlockPos) this.getValue(key));
    }

    /**
     * Get a ItemStack value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public ItemStack getItemStack(String key)
    {
        isValid(key, DataType.ITEM_STACK);
        return ((ItemStack) this.getValue(key));
    }

    /**
     * Get a NBTTagCompound value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             If the entry type doesn't match
     */
    public NBTTagCompound getTag(String key)
    {
        isValid(key, DataType.TAG);
        return ((NBTTagCompound) this.getValue(key));
    }

    // *********************************************************************************************

    /**
     * Set a new boolean value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setBoolean(String key, boolean value)
    {
        isValid(key, DataType.BOOLEAN);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new byte value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setByte(String key, byte value)
    {
        isValid(key, DataType.BYTE);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new byte value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setByte(String key, int value)
    {
        if (value > Byte.MAX_VALUE || value < Byte.MIN_VALUE)
            invalidValue(key, value);
        return setByte(key, (byte) value);
    }

    /**
     * Set a new short value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setShort(String key, short value)
    {
        isValid(key, DataType.SHORT);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new short value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setShort(String key, int value)
    {
        if (value > Short.MAX_VALUE || value < Short.MIN_VALUE)
            invalidValue(key, value);
        return setShort(key, (short) value);
    }

    /**
     * Set a new int value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setInteger(String key, int value)
    {
        isValid(key, DataType.INTEGER);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new long value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setLong(String key, long value)
    {
        isValid(key, DataType.LONG);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new float value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setFloat(String key, float value)
    {
        isValid(key, DataType.FLOAT);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new double value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setDouble(String key, double value)
    {
        isValid(key, DataType.DOUBLE);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new character value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setCharacter(String key, char value)
    {
        isValid(key, DataType.CHARACTER);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new string value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setString(String key, String value)
    {
        isValid(key, DataType.STRING);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new UUID value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setUUID(String key, UUID value)
    {
        isValid(key, DataType.UUID);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new BlockPos value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setBlockPos(String key, BlockPos value)
    {
        isValid(key, DataType.BLOCK_POS);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new ItemStack value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setItemStack(String key, ItemStack value)
    {
        isValid(key, DataType.ITEM_STACK);
        return this.replaceValue(key, value);
    }

    /**
     * Set a new NBTTagCompound value for an entry
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return True if value has changed else false
     * @throws IllegalArgumentException
     *             If the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setTag(String key, NBTTagCompound value)
    {
        isValid(key, DataType.TAG);
        return this.replaceValue(key, value);
    }

    // *********************************************************************************************

    /**
     * Write all the data into the buffer
     * 
     * @param buffer
     *            The byte buffer
     */
    public void writeBuffer(ByteBuf buffer)
    {
        List<String> list = sortKey();

        for (String key : list)
        {
            this.getType(key).writeBufferIn(buffer, this.getValue(key));
        }
    }

    /**
     * Read all the data from the buffer
     * 
     * @param buffer
     *            The byte buffer
     */
    public void readBuffer(ByteBuf buffer)
    {
        List<String> list = sortKey();

        for (String key : list)
        {
            this.replaceValue(key, this.getType(key).readBuffer(buffer));
        }
    }

    /**
     * Write all the data into a NBT tag
     * 
     * @return the NBT tag compound
     */
    public NBTTagCompound writeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();

        for (String key : this.dataValue.keySet())
        {
            this.getType(key).writeNBTIn(nbt, key, this.getValue(key));

        }

        return nbt;
    }

    /**
     * Read all the data from a NBT tag
     * 
     * @param nbt
     *            the NBT tag compound
     */
    public void readNBT(NBTTagCompound nbt)
    {
        for (String key : this.dataValue.keySet())
        {
            this.replaceValue(key, this.getType(key).readNBT(nbt, key));
        }
    }

    /**
     * Write all the data into the stream
     * 
     * @param stream
     *            The output stream
     */
    public void writeStream(DataOutput stream) throws IOException
    {
        List<String> list = sortKey();

        for (String key : list)
        {
            this.getType(key).writeStreamIn(stream, this.getValue(key));
        }
    }

    /**
     * Read all the data from the stream
     * 
     * @param stream
     *            The input stream
     */
    public void readStream(DataInput stream) throws IOException
    {
        List<String> list = sortKey();

        for (String key : list)
        {
            this.replaceValue(key, this.getType(key).readStream(stream));
        }
    }

    // *********************************************************************************************

    /**
     * Test if the type of the entry match with the request one
     * 
     * @param key
     *            The name of the entry
     * @param type
     *            The type of the entry
     * @throws IllegalArgumentException
     *             If the key is not valid
     */
    private void isValid(String key, DataType<?> type)
    {
        // Test the key
        isValidKey(key);

        // Get the potential data type
        DataType<?> keyType = this.getType(key);

        // Check if the key exist into the system
        if (keyType == null)
            noValue(key);
        // Check if the key type is the same as require
        if (!keyType.equals(type))
            invalidType(key, type.getType());
    }

    /**
     * Test if a key is valid
     * 
     * @param key
     *            The key name
     * @throws IllegalArgumentException
     *             If the key is not valid
     */
    private void isValidKey(String key)
    {
        if (key == null || key.isEmpty())
            throwArgumentException("Key null or empty");
    }

    private void noValue(String key)
    {
        throwArgumentException(key + " has no entry in the table!");
    }

    private void invalidValue(String key, Object value)
    {
        throwArgumentException("Invalide value for '" + key + "'");
    }

    private void invalidType(String key, String type)
    {
        throwArgumentException("Type " + type + " is not valid for '" + key + "' (" + this.getType(key) + ")");
    }

    private void throwArgumentException(String text)
    {
        throw new IllegalArgumentException(text);
    }

    // *********************************************************************************************

    private List<String> sortKey()
    {
        List<String> list = Lists.newArrayList(this.dataValue.keySet());
        Collections.sort(list, sorter);
        return list;
    }

    private static Comparator<String> sorter = (ob1, ob2) ->
    {
        return ob1.compareToIgnoreCase(ob2);
    };
}
