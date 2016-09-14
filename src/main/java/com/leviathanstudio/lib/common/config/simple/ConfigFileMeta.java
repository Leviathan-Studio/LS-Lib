package com.leviathanstudio.lib.common.config.simple;

import java.io.File;
import java.util.List;

import com.leviathanstudio.lib.common.util.MinecraftUtil;

import net.minecraftforge.common.config.Configuration;

public class ConfigFileMeta
{
    private String       modid;
    private String       configFile;
    private String       configFolder;
    private String       baseCategory;
    private final String className;

    private boolean      isConfig = false;

    public ConfigFileMeta(String className, List<Object> values)
    {
        this.className = className;
        // Default value
        this.configFolder = "";
        this.baseCategory = "";

        // Property by 2 (name and value)
        for (int i = 0; i < values.size(); i += 2)
        {
            String name = (String) values.get(i);
            Object value = values.get(i + 1);
            if (name.equals("modid"))
            {
                this.modid = (String) value;
            }
            else if (name.equals("configFile"))
            {
                if (((String) value).isEmpty())
                    this.configFile = this.modid + ".cfg";
                else
                    this.configFile = (String) value;
            }
            else if (name.equals("configFolder"))
            {
                this.configFolder = (String) value;
            }
            else if (name.equals("baseCategory"))
            {
                this.baseCategory = (String) value;
            }
        }
    }

    public ConfigFileMeta(String className, String modid, String configFile, String configFolder, String baseCategory)
    {
        this.className = className;
        this.modid = modid;
        this.configFolder = configFolder;
        this.configFile = configFile;
        this.baseCategory = baseCategory;
    }

    public String getModId()
    {
        return this.modid;
    }

    public String getBaseCategory()
    {
        return this.baseCategory;
    }

    public File getConfigurationFile()
    {
        File configFolder = null;
        if (!this.configFolder.isEmpty())
        {
            configFolder = new File(MinecraftUtil.getConfigDir(), this.configFolder);
        }
        else
        {
            configFolder = MinecraftUtil.getConfigDir();
        }
        return new File(configFolder, this.configFile);
    }

    public Configuration getConfiguration()
    {
        return new Configuration(getConfigurationFile());
    }

    public Class<?> getConfigClass()
    {
        try
        {
            return Class.forName(this.className);
        } catch (ClassNotFoundException e)
        {
            return null;
        }
    }

    public void config()
    {
        if (!isConfig)
            ConfigProcessing.processAnnotations(this.getModId(), this.getConfiguration(), this.getConfigClass(),
                    this.getBaseCategory());
        isConfig = true;
    }

    public String getConfigFile()
    {
        return this.configFile;
    }

    @Override
    public String toString()
    {
        return className + " : " + this.configFolder + "/" + this.configFile + " (" + this.modid + ")";
    }
}
