package com.leviathanstudio.lib.common.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

import io.netty.buffer.ByteBuf;

public class DataManager
{
    Map<String, Object> entries;

    private DataManager()
    {
        this.entries = Maps.newHashMap();
    }

    // *********************************************************************************************

    public static DataManager createDataManager()
    {
        return new DataManager();
    }

    public static DataManager createDataManager(Map<String, Object> entries)
    {
        DataManager dm = createDataManager();
        dm.entries = entries;
        return dm;
    }

    public static DataManager createDataManager(DataManager dataManager)
    {
        return createDataManager(dataManager.getEntries());
    }

    // *********************************************************************************************

    /**
     * Get a copy of all entries
     * 
     * @return the entries
     */
    public Map<String, Object> getEntries()
    {
        return Maps.newHashMap(this.entries);
    }

    /**
     * Create a copy of data manager
     * 
     * @return a copy of data manager
     */
    public DataManager copy()
    {
        return createDataManager(this);
    }

    /**
     * Remove all entry
     */
    public void clear()
    {
        this.entries.clear();
    }

    /**
     * Remove a specific entry
     * 
     * @param name
     *            The name of the entry
     * @return true if an element has been remove else false
     */
    public boolean remove(String name)
    {
        if (hasEntry(name))
        {
            this.entries.remove(name);
            return true;
        }
        return false;
    }

    // *********************************************************************************************

    /**
     * Test if an entry is present
     * 
     * @param name
     *            The name of the entry
     * @return true if contains the entry else false
     */
    public boolean hasEntry(String name)
    {
        if (name == null || name.isEmpty())
            throwArgumentException("Key null or empty");
        return this.entries.containsKey(name);
    }

    // *********************************************************************************************

    /**
     * Add a new entry
     * 
     * @param name
     *            The name of the entry
     * @param entry
     *            The entry object
     * @return true if add entry else false
     */
    private boolean addEntry(String name, @Nonnull Object entry)
    {
        if (!hasEntry(name))
        {
            this.entries.put(name, entry);
            return true;
        }
        return false;
    }

    /**
     * Add a new entry
     * 
     * @param entry
     *            The entry object
     * @return true if add entry else false
     */
    public boolean addEntry(DataEntry<?> entry)
    {
        if (!hasEntry(entry.getName()))
        {
            this.entries.put(entry.getName(), entry);
            return true;
        }
        return false;
    }

    /**
     * Add a new boolean entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addBoolean(String name, boolean value, boolean defaultValue)
    {
        return addEntry(name, new DataBooleanEntry(name, defaultValue, value));
    }

    /**
     * Add a new boolean entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addBoolean(String name, boolean value)
    {
        return addBoolean(name, value, value);
    }

    /**
     * Add a new byte entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addByte(String name, int value, int defaultValue)
    {
        return addEntry(name, new DataByteEntry(name, (byte) defaultValue, (byte) value));
    }

    /**
     * Add a new byte entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addByte(String name, int value)
    {
        return addByte(name, value, value);
    }

    /**
     * Add a new short entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addShort(String name, int value, int defaultValue)
    {
        return addEntry(name, new DataShortEntry(name, (short) defaultValue, (short) value));
    }

    /**
     * Add a new short entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addShort(String name, int value)
    {
        return addShort(name, value, value);
    }

    /**
     * Add a new integer entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addInteger(String name, int value, int defaultValue)
    {
        return addEntry(name, new DataIntegerEntry(name, defaultValue, value));
    }

    /**
     * Add a new integer entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addInteger(String name, int value)
    {
        return addInteger(name, value, value);
    }

    /**
     * Add a new long entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addLong(String name, long value, long defaultValue)
    {
        return addEntry(name, new DataLongEntry(name, defaultValue, value));
    }

    /**
     * Add a new long entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addLong(String name, long value)
    {
        return addLong(name, value, value);
    }

    /**
     * Add a new float entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addFloat(String name, float value, float defaultValue)
    {
        return addEntry(name, new DataFloatEntry(name, defaultValue, value));
    }

    /**
     * Add a new float entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addFloat(String name, float value)
    {
        return addFloat(name, value, value);
    }

    /**
     * Add a new double entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addDouble(String name, double value, double defaultValue)
    {
        return addEntry(name, new DataDoubleEntry(name, defaultValue, value));
    }

    /**
     * Add a new double entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addDouble(String name, double value)
    {
        return addDouble(name, value, value);
    }

    /**
     * Add a new character entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addCharacter(String name, char value, char defaultValue)
    {
        return addEntry(name, new DataCharacterEntry(name, defaultValue, value));
    }

    /**
     * Add a new character entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addCharacter(String name, char value)
    {
        return addCharacter(name, value, value);
    }

    /**
     * Add a new string entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addString(String name, @Nonnull String value, @Nonnull String defaultValue)
    {
        return addEntry(name, new DataStringEntry(name, defaultValue, value));
    }

    /**
     * Add a new string entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addString(String name, @Nonnull String value)
    {
        return addString(name, value, value);
    }

    /**
     * Add a new item stack entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addItemStack(String name, @Nonnull ItemStack value, @Nonnull ItemStack defaultValue)
    {
        return addEntry(name, new DataStackEntry(name, defaultValue, value));
    }

    /**
     * Add a new item stack entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addItemStack(String name, @Nonnull ItemStack value)
    {
        return addItemStack(name, value, value);
    }

    /**
     * Add a new tag compound entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addTag(String name, @Nonnull NBTTagCompound value, @Nonnull NBTTagCompound defaultValue)
    {
        return addEntry(name, new DataTagEntry(name, defaultValue, value));
    }

    /**
     * Add a new tag compound entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addTag(String name, @Nonnull NBTTagCompound value)
    {
        return addTag(name, value, value);
    }

    /**
     * Add a new UUID entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addUUID(String name, @Nonnull UUID value, @Nonnull UUID defaultValue)
    {
        return addEntry(name, new DataUUIDEntry(name, defaultValue, value));
    }

    /**
     * Add a new UUID entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addUUID(String name, @Nonnull UUID value)
    {
        return addUUID(name, value, value);
    }

    /**
     * Add a new BlockPos entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry
     * @param defaultValue
     *            The default value for the entry (use for reset)
     * @return true if add entry else false
     */
    public boolean addBlockPos(String name, @Nonnull BlockPos value, @Nonnull BlockPos defaultValue)
    {
        return addEntry(name, new DataBlockPosEntry(name, defaultValue, value));
    }

    /**
     * Add a new BlockPos entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The current value of the entry, set as default value too
     * @return true if add entry else false
     */
    public boolean addBlockPos(String name, @Nonnull BlockPos value)
    {
        return addBlockPos(name, value, value);
    }

    // *********************************************************************************************

    /**
     * Set a new boolean value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setBoolean(String name, boolean value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Boolean.class))
            invalidType(name, "boolean");

        return entry.setValue(value);
    }

    /**
     * Set a new byte value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setByte(String name, int value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Byte.class))
            invalidType(name, "byte");

        return entry.setValue((byte) value);
    }

    /**
     * Set a new short value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setShort(String name, int value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Short.class))
            invalidType(name, "short");

        return entry.setValue((short) value);
    }

    /**
     * Set a new integer value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setInteger(String name, int value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Integer.class))
            invalidType(name, "integer");

        return entry.setValue(value);
    }

    /**
     * Set a new long value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setLong(String name, long value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Long.class))
            invalidType(name, "long");

        return entry.setValue(value);
    }

    /**
     * Set a new float value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setFloat(String name, float value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Float.class))
            invalidType(name, "float");

        return entry.setValue(value);
    }

    /**
     * Set a new double value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setDouble(String name, double value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Double.class))
            invalidType(name, "double");

        return entry.setValue(value);
    }

    /**
     * Set a new character value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setCharacter(String name, char value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Character.class))
            invalidType(name, "character");

        return entry.setValue(value);
    }

    /**
     * Set a new string value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setString(String name, String value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, String.class))
            invalidType(name, "String");

        return entry.setValue(value);
    }

    /**
     * Set a new item stack value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setItemStack(String name, ItemStack value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, ItemStack.class))
            invalidType(name, "ItemStack");

        return entry.setValue(value);
    }

    /**
     * Set a new tag compound value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setTag(String name, NBTTagCompound value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, NBTTagCompound.class))
            invalidType(name, "NBTTagCompound");

        return entry.setValue(value);
    }

    /**
     * Set a new UUID value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setUUID(String name, UUID value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, UUID.class))
            invalidType(name, "UUID");

        return entry.setValue(value);
    }

    /**
     * Set a new BlockPos value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     */
    public boolean setBlockPos(String name, BlockPos value)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, BlockPos.class))
            invalidType(name, "BlockPos");

        return entry.setValue(value);
    }

    // *********************************************************************************************

    /**
     * Reset all the entry with their default value
     */
    public void resetAll()
    {
        for (int i = 0; i < this.entries.size(); i++)
        {
            ((DataEntry<?>) this.entries.get(i)).reset();
        }
    }

    /**
     * Reset a specific entry
     * 
     * @param name
     *            The name of the entry
     * @return true if value has changed else false
     */
    public boolean reset(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (entry != null)
        {
            return entry.reset();
        }

        return false;
    }

    // *********************************************************************************************

    /**
     * Get a specific entry
     * 
     * @param name
     *            The name of the entry
     * @return the entry
     */
    @SuppressWarnings("unchecked")
    public DataEntry<Object> getEntry(String name)
    {
        if (name == null || name.isEmpty())
            throwArgumentException("Key null or empty");
        DataEntry<Object> entry = (DataEntry<Object>) this.entries.get(name);
        if (entry == null)
            invalidKey(name);
        return entry;
    }

    /**
     * Get the boolean value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public boolean getBoolean(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Boolean.class))
            invalidType(name, "boolean");

        return (boolean) entry.getValue();
    }

    /**
     * Get the byte value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public byte getByte(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Byte.class))
            invalidType(name, "byte");

        return (byte) entry.getValue();
    }

    /**
     * Get the short value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public short getShort(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Short.class))
            invalidType(name, "short");

        return (short) entry.getValue();
    }

    /**
     * Get the integer value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public int getInteger(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Integer.class))
            invalidType(name, "integer");

        return (int) entry.getValue();
    }

    /**
     * Get the long value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public long getLong(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Long.class))
            invalidType(name, "long");

        return (long) entry.getValue();
    }

    /**
     * Get the float value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public float getFloat(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Float.class))
            invalidType(name, "float");

        return (float) entry.getValue();
    }

    /**
     * Get the double value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public double getDouble(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Double.class))
            invalidType(name, "double");
        return (double) entry.getValue();
    }

    /**
     * Get the string value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public String getString(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, String.class))
            invalidType(name, "string");

        return (String) entry.getValue();
    }

    /**
     * Get the character value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public char getCharacter(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, Character.class))
            invalidType(name, "character");

        return (char) entry.getValue();
    }

    /**
     * Get the item stack value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public ItemStack getItemStack(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, ItemStack.class))
            invalidType(name, "ItemStack");

        return (ItemStack) entry.getValue();
    }

    /**
     * Get the tag compound value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public NBTTagCompound getTag(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, NBTTagCompound.class))
            invalidType(name, "NBTTagCompound");

        return (NBTTagCompound) entry.getValue();
    }

    /**
     * Get the UUID value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public UUID getUUID(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, UUID.class))
            invalidType(name, "UUID");

        return (UUID) entry.getValue();
    }

    /**
     * Get the BlockPos value for the given entry
     * 
     * @param name
     *            The name of the entry
     * @return the value
     */
    public BlockPos getBlockPos(String name)
    {
        DataEntry<Object> entry = getEntry(name);
        if (!isValidefor(entry, BlockPos.class))
            invalidType(name, "BlockPos");

        return (BlockPos) entry.getValue();
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
            this.getEntry(key).writeBuffer(buffer);
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
            this.getEntry(key).readBuffer(buffer);
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

        for (Object entry : this.entries.values())
        {
            ((DataEntry<?>) entry).writeNBT(nbt);
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
        for (Object entry : this.entries.values())
        {
            ((DataEntry<?>) entry).readNBT(nbt);
        }
    }

    // *********************************************************************************************

    private boolean isValidefor(DataEntry<Object> entry, Class<?> type)
    {
        return (entry != null && entry.getType().equals(type));
    }

    private void invalidType(String key, String type)
    {
        throwArgumentException("Type " + type + " is not valid for '" + key + "' (data type "
                + (getEntry(key) != null ? getEntry(key).getType() : null) + ") '");
    }

    private void invalidKey(String name)
    {
        throwArgumentException("Invalid key: " + name);
    }

    private void throwArgumentException(String text)
    {
        throw new IllegalArgumentException(text);
    }

    // *********************************************************************************************

    private List<String> sortKey()
    {
        List<String> list = Lists.newArrayList(this.entries.keySet());
        Collections.sort(list, sorter);
        return list;
    }

    @SuppressWarnings("unchecked")
    private static Comparator<Object> sorter = (ob1, ob2) ->
    {
        DataEntry<Object> entry1 = (DataEntry<Object>) ob1;
        DataEntry<Object> entry2 = (DataEntry<Object>) ob2;
        return entry1.getName().compareToIgnoreCase(entry2.getName());
    };

}
