package com.leviathanstudio.lib.common.data2;

import java.util.Map;

import com.google.common.collect.Maps;

public class DataManager
{
    private final Map<String, DataType<?>> dataType;
    private final Map<String, Object>      dataValue;

    // *********************************************************************************************

    private DataManager()
    {
        this.dataType = Maps.newHashMap();
        this.dataValue = Maps.newHashMap();
    }

    private DataManager(DataManager manager)
    {
        this.dataType = Maps.newHashMap(manager.dataType);
        this.dataValue = Maps.newHashMap(manager.dataValue);
    }

    // *********************************************************************************************

    public static DataManager create()
    {
        return new DataManager();
    }

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
     * Get the type of an entry by giving its name
     * 
     * @param key
     *            The name of the entry
     * @return The type of the entry
     */
    public DataType<?> getType(String key)
    {
        return this.dataType.getOrDefault(key, null);
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
        if (!this.getType(key).equals(DataType.BOOLEAN))
            invalidType(key, "boolean");
        return ((Boolean) this.dataValue.get(key));
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
        if (!this.getType(key).equals(DataType.BYTE))
            invalidType(key, "byte");
        return ((Number) this.dataValue.get(key)).byteValue();
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
        if (!this.getType(key).equals(DataType.SHORT))
            invalidType(key, "short");
        return ((Number) this.dataValue.get(key)).shortValue();
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
        if (!this.getType(key).equals(DataType.INTEGER))
            invalidType(key, "integer");
        return ((Number) this.dataValue.get(key)).intValue();
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
        if (!this.getType(key).equals(DataType.LONG))
            invalidType(key, "long");
        return ((Number) this.dataValue.get(key)).longValue();
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
        return ((Number) this.dataValue.get(key)).floatValue();
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
        return ((Number) this.dataValue.get(key)).doubleValue();
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
        return ((char) this.dataValue.get(key));
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
        return ((String) this.dataValue.get(key));
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

    private void invalidType(String key, String type)
    {
        throw new IllegalArgumentException(
                "Type " + type + " is not valid for '" + key + " (" + this.getType(key) + ")");
    }
}
