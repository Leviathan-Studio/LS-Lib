package com.leviathanstudio.lib.common.data2;

import java.util.Map;

import com.google.common.collect.Maps;

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
     * @return a copy of data manager
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

    // *********************************************************************************************

    /**
     * Get the type of an entry by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The type of the entry or null
     */
    public @Nullable DataType<?> getType(String key)
    {
        return this.dataType.getOrDefault(key, null);
    }

    /**
     * Get the value of an entry by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry or null
     */
    public @Nullable Object getValue(String key)
    {
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
        return this.dataValue.replace(key, oldValue, newValue);
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
        return this.dataValue.replace(key, this.getValue(key), newValue);
    }

    // *********************************************************************************************

    /**
     * Add a new boolean value to the system
     * 
     * @param key
     *            The name of the entry
     * @param value
     *            The value of the entry
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
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
     * @return true if value has been add to the system else false
     */
    public boolean addString(String key, String value)
    {
        return addValue(key, DataType.STRING, value);
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
     * @return true if value has been add to the system else false
     */
    private boolean addValue(String key, DataType<?> type, Object value)
    {
        // Key already exists
        if (this.dataValue.containsKey(key))
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
     *             if the entry type doesn't match
     */
    public boolean getBoolean(String key)
    {
        isValid(key, DataType.BOOLEAN, "boolean");
        return ((Boolean) this.getValue(key));
    }

    /**
     * Get a byte value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public byte getByte(String key)
    {
        isValid(key, DataType.BYTE, "byte");
        return ((Number) this.getValue(key)).byteValue();
    }

    /**
     * Get a short value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public short getShort(String key)
    {
        isValid(key, DataType.SHORT, "short");
        return ((Number) this.getValue(key)).shortValue();
    }

    /**
     * Get an integer value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public int getInteger(String key)
    {
        isValid(key, DataType.INTEGER, "integer");
        return ((Number) this.getValue(key)).intValue();
    }

    /**
     * Get a long value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public long getLong(String key)
    {
        isValid(key, DataType.LONG, "long");
        return ((Number) this.getValue(key)).longValue();
    }

    /**
     * Get a float value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public float getFloat(String key)
    {
        isValid(key, DataType.FLOAT, "float");
        return ((Number) this.getValue(key)).floatValue();
    }

    /**
     * Get a double value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public double getDouble(String key)
    {
        isValid(key, DataType.DOUBLE, "double");
        return ((Number) this.getValue(key)).doubleValue();
    }

    /**
     * Get a character value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public char getCharacter(String key)
    {
        isValid(key, DataType.CHARACTER, "character");
        return ((char) this.getValue(key));
    }

    /**
     * Get a string value by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The value of the entry
     * @throws IllegalArgumentException
     *             if the entry type doesn't match
     */
    public String getString(String key)
    {
        isValid(key, DataType.STRING, "string");
        return ((String) this.getValue(key));
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
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setBoolean(String key, boolean value)
    {
        isValid(key, DataType.BOOLEAN, "boolean");
        return this.replaceValue(key, value);
    }

    /**
     * Set a new byte value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setByte(String key, byte value)
    {
        isValid(key, DataType.BYTE, "byte");
        return this.replaceValue(key, value);
    }

    /**
     * Set a new byte value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
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
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setShort(String key, short value)
    {
        isValid(key, DataType.BYTE, "short");
        return this.replaceValue(key, value);
    }

    /**
     * Set a new short value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
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
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setInteger(String key, int value)
    {
        isValid(key, DataType.BYTE, "integer");
        return this.replaceValue(key, value);
    }

    /**
     * Set a new long value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setLong(String key, long value)
    {
        isValid(key, DataType.BYTE, "long");
        return this.replaceValue(key, value);
    }

    /**
     * Set a new long value for an entry
     * 
     * @param name
     *            The name of the entry
     * @param value
     *            The new value for the entry
     * @return true if value has changed else false
     * @throws IllegalArgumentException
     *             if the entry dones'nt exist or if the DataType does'nt match
     */
    public boolean setLong(String key, int value)
    {
        return setLong(key, (long) value);
    }

    // *********************************************************************************************

    private void isValid(String key, DataType<?> type, String typeName)
    {
        DataType<?> keyType = this.getType(key);

        // Check if the key exist into the system
        if (keyType == null)
            noValue(key);
        // Check if the key type is the same as require
        if (!keyType.equals(type))
            invalidType(key, typeName);
    }

    private void noValue(String key)
    {
        throw new IllegalArgumentException(key + " has no entry in the table!");
    }

    private void invalidValue(String key, Object value)
    {
        throw new IllegalArgumentException("Invalide value for '" + key + "'");
    }

    private void invalidType(String key, String type)
    {
        throw new IllegalArgumentException(
                "Type " + type + " is not valid for '" + key + " (" + this.getType(key) + ")");
    }
}
