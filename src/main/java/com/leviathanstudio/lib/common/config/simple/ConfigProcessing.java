package com.leviathanstudio.lib.common.config.simple;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;

import net.minecraftforge.common.config.Configuration;

public class ConfigProcessing
{
    public static class ModConfig
    {
        private final Configuration                       config;
        public final Class<?>                             configClass;
        public final String                               modId;
        public final String                               baseCategory;

        private Table<String, String, ConfigPropertyMeta> properties = HashBasedTable.create();

        private ModConfig(String modId, Configuration config, Class<?> configClass, String baseCategory)
        {
            this.modId = modId;
            this.config = config;
            this.configClass = configClass;
            this.baseCategory = baseCategory;
        }

        void tryProcessConfig(Field field)
        {
            ConfigPropertyMeta meta = ConfigPropertyMeta.createMetaForField(config, field, this.baseCategory);
            if (meta != null)
            {
                meta.updateValueFromConfig(false);
                properties.put(meta.category.toLowerCase(Locale.ENGLISH), meta.name.toLowerCase(Locale.ENGLISH), meta);
            }
        }

        public File getConfigFile()
        {
            return config.getConfigFile();
        }

        public void save()
        {
            if (config.hasChanged())
                config.save();
        }

        public Collection<String> getCategories()
        {
            return Collections.unmodifiableCollection(properties.rowKeySet());
        }

        public Collection<String> getValues(String category)
        {
            return Collections.unmodifiableCollection(properties.row(category.toLowerCase(Locale.ENGLISH)).keySet());
        }

        public ConfigPropertyMeta getValue(String category, String name)
        {
            return properties.get(category.toLowerCase(Locale.ENGLISH), name.toLowerCase(Locale.ENGLISH));
        }
    }

    private static final Map<String, ModConfig> configs = Maps.newHashMap();

    public static Collection<String> getConfigsIds()
    {
        return Collections.unmodifiableCollection(configs.keySet());
    }

    public static ModConfig getConfig(String modId)
    {
        return configs.get(modId.toLowerCase(Locale.ENGLISH));
    }

    public static void config(Class<?> clazz)
    {
        ConfigFile anno[] = clazz.getAnnotationsByType(ConfigFile.class);
        if (anno.length > 0)
        {
            ConfigFileMeta meta = new ConfigFileMeta(clazz.getName(), anno[0].modid(), anno[0].configFile(),
                    anno[0].configFolder(), anno[0].baseCategory());
            meta.config();
        }
    }

    public static void processAnnotations(String modId, Configuration config, Class<?> clazz, String baseCategory)
    {
        Preconditions.checkState(
                !(ConfigFileManager.hasMany(config.getConfigFile().getName()) && baseCategory.isEmpty()),
                "Trying to configure mod '%s' twice", modId);

        ModConfig configMeta = new ModConfig(modId, config, clazz, baseCategory);
        configs.put(modId.toLowerCase(Locale.ENGLISH), configMeta);

        for (Field f : clazz.getFields())
            configMeta.tryProcessConfig(f);

        configMeta.save();
    }
}
