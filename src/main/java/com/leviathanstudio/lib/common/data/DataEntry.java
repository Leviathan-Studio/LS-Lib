package com.leviathanstudio.lib.common.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;

import net.minecraft.nbt.NBTTagCompound;

import io.netty.buffer.ByteBuf;

public abstract class DataEntry<T>
{
    protected final String name;

    @Nonnull
    private final T        defaultValue;

    @Nonnull
    protected T            value;

    public DataEntry(String name, T value)
    {
        this(name, value, value);
    }

    public DataEntry(String name, T defaultValue, T value)
    {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = value;
    }

    /**
     * Get the name of the data
     * 
     * @return The name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Get the default value for the data
     * 
     * @return The default value
     */
    public Object getDefaultValue()
    {
        return this.defaultValue;
    }

    /**
     * Get the current value for the data
     * 
     * @return The value
     */
    public Object getValue()
    {
        return this.value;
    }

    /**
     * Get the class for the value
     * 
     * @return The class link with the value
     */
    public Class<?> getType()
    {
        return this.value.getClass();
    }

    /**
     * Set the value of the data
     * 
     * @param value
     *            The new value
     * @return true if the value has changed else false
     */
    public boolean setValue(T value)
    {
        if (this.value.equals(value))
            return false;

        this.value = value;
        return true;
    }

    /**
     * Reset value to the default one
     * 
     * @return true if the value has changed else false
     */
    public boolean reset()
    {
        if (this.value.equals(defaultValue))
            return false;
        this.value = defaultValue;
        return true;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        else if (this == obj)
            return true;
        else if (obj instanceof DataEntry)
        {
            DataEntry<?> entry = (DataEntry<?>) obj;

            if (this.name.equalsIgnoreCase(entry.name) && this.value.equals(entry.value)
                    && this.defaultValue.equals(entry.defaultValue))
                return true;
            else
                return false;
        }
        else
            return false;
    }

    /**
     * Use to convert data into NBT
     * @param nbt The NBTTagCompound
     */
    public abstract void writeNBT(NBTTagCompound nbt);

    /**
     * Use to convert data from NBT
     * @param nbt The NBTTagCompound
     */
    public abstract void readNBT(NBTTagCompound nbt);

    /**
     * Use to convert data into ByteBuf
     * @param buffer The Byte buffer
     */
    public abstract void writeBuffer(ByteBuf buffer);

    /**
     * Use to convert data from ByteBuf
     * @param buffer The Byte buffer
     */
    public abstract void readBuffer(ByteBuf buffer);

    /**
     * Use to convert data into ByteBuf
     * 
     * @param data
     *            The Data output stream
     *  @throws IOException if error

     */
    public abstract void writeData(DataOutput data) throws IOException;

    /**
     * Use to convert data from ByteBuf
     * 
     * @param data
     *            The Data output stream
     * @throws IOException if error
     */
    public abstract void readData(DataInput data) throws IOException;
}
