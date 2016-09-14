package com.leviathanstudio.lib.common.config.simple;

import java.util.List;

import com.google.common.collect.Lists;
import com.leviathanstudio.lib.common.core.LSLib;

public class ConfigFileManager
{
    private static List<ConfigFileMeta> configFiles = Lists.newArrayList();

    public static void add(ConfigFileMeta meta)
    {
        if (!configFiles.contains(meta))
            configFiles.add(meta);
    }

    public static ConfigFileMeta get(int i)
    {
        return configFiles.get(i);
    }

    public static int size()
    {
        return configFiles.size();
    }

    public static ConfigFileMeta getLast()
    {
        return get(size() - 1);
    }

    public static boolean hasMany(String configFile)
    {
        int number = 0;

        for (ConfigFileMeta meta : configFiles)
        {
            if (meta.getConfigFile().equals(configFile))
                number++;
        }

        return number > 1;
    }

    public static void processConfig()
    {
        LSLib.LOGGER.info("Configuration to process: " + configFiles.size());
        for (ConfigFileMeta config : configFiles)
        {
            LSLib.LOGGER.info("Configure: " + config.getConfigurationFile().getName());
            ConfigProcessing.processAnnotations(config.getModId(), config.getConfiguration(), config.getConfigClass(),
                    config.getBaseCategory());
        }
    }
}
