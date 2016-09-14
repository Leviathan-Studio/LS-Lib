package com.leviathanstudio.lib.common.config.property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.leviathanstudio.lib.common.core.LSLib;

public class PropertyConfiguration
{
    /** The properties list **/
    private final Properties properties = new Properties();

    /** The properties file. */
    private final File       propertiesFile;

    public PropertyConfiguration(File propertiesFile)
    {
        this.propertiesFile = propertiesFile;

        if (propertiesFile.exists())
        {
            FileInputStream fileinputstream = null;

            try
            {
                fileinputstream = new FileInputStream(propertiesFile);
                this.properties.load(fileinputstream);
            } catch (Exception exception)
            {
                LSLib.LOGGER.warn("Failed to load " + propertiesFile + "!");
                this.generateNewProperties();
            } finally
            {
                if (fileinputstream != null)
                {
                    try
                    {
                        fileinputstream.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            LSLib.LOGGER.warn(propertiesFile + " does not exist!");
            this.generateNewProperties();
        }
    }

    // *********************************************************************************************

    /**
     * Generate a new property file
     */
    public void generateNewProperties()
    {
        LSLib.LOGGER.info("Generating new properties file!");
        this.saveProperties();
    }

    /**
     * Save all properties
     */
    public void saveProperties()
    {
        FileOutputStream fileoutputstream = null;

        try
        {
            fileoutputstream = new FileOutputStream(this.propertiesFile);
            this.properties.store(fileoutputstream, "Game properties");
        } catch (Exception exception)
        {
            LSLib.LOGGER.warn("Failed to save " + this.propertiesFile + "!", exception);
            this.generateNewProperties();
        } finally
        {
            if (fileoutputstream != null)
            {
                try
                {
                    fileoutputstream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 
     * @return The property file
     */
    public File getPropertiesFile()
    {
        return this.propertiesFile;
    }

    // *********************************************************************************************

    /**
     * Return the string value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The string value
     */
    public String getStringProperty(String key, String defaultValue)
    {
        if (!this.properties.containsKey(key))
        {
            this.properties.setProperty(key, defaultValue);
            this.saveProperties();
        }

        return this.properties.getProperty(key, defaultValue);
    }

    // *********************************************************************************************

    /**
     * Return the byte value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The byte value
     */
    public byte getByteProperty(String key, int defaultValue)
    {
        return getByteProperty(key, (byte) defaultValue);
    }

    /**
     * Return the byte value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The byte value
     */
    public byte getByteProperty(String key, byte defaultValue)
    {
        try
        {
            return Byte.parseByte(this.getStringProperty(key, "" + defaultValue));
        } catch (Exception exception)
        {
            this.properties.setProperty(key, "" + defaultValue);
            this.saveProperties();
            return defaultValue;
        }
    }

    /**
     * Return the short value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The short value
     */
    public short getShortProperty(String key, int defaultValue)
    {
        return getShortProperty(key, (short) defaultValue);
    }

    /**
     * Return the short value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The short value
     */
    public short getShortProperty(String key, short defaultValue)
    {
        try
        {
            return Short.parseShort(this.getStringProperty(key, "" + defaultValue));
        } catch (Exception exception)
        {
            this.properties.setProperty(key, "" + defaultValue);
            this.saveProperties();
            return defaultValue;
        }
    }

    /**
     * Return the integer value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The integer value
     */
    public int getIntProperty(String key, int defaultValue)
    {
        try
        {
            return Integer.parseInt(this.getStringProperty(key, "" + defaultValue));
        } catch (Exception exception)
        {
            this.properties.setProperty(key, "" + defaultValue);
            this.saveProperties();
            return defaultValue;
        }
    }

    /**
     * Return the long value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The long value
     */
    public long getLongProperty(String key, long defaultValue)
    {
        try
        {
            return Long.parseLong(this.getStringProperty(key, "" + defaultValue));
        } catch (Exception exception)
        {
            this.properties.setProperty(key, "" + defaultValue);
            this.saveProperties();
            return defaultValue;
        }
    }

    // *********************************************************************************************

    /**
     * Return the boolean value of the property define by the key
     * 
     * @param key
     *            The property key
     * @param defaultValue
     *            The default value
     * @return The boolean value
     */
    public boolean getBooleanProperty(String key, boolean defaultValue)
    {
        try
        {
            return Boolean.parseBoolean(this.getStringProperty(key, "" + defaultValue));
        } catch (Exception exception)
        {
            this.properties.setProperty(key, "" + defaultValue);
            this.saveProperties();
            return defaultValue;
        }
    }
}
