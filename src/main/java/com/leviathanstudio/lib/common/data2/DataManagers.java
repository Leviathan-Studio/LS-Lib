package com.leviathanstudio.lib.common.data2;

import java.util.Map;

import com.google.common.collect.Maps;
import com.leviathanstudio.lib.common.core.LSLib;
import com.leviathanstudio.lib.common.network.packet.DataManagerPacket;

public class DataManagers
{
    private static final Map<String, DataManager> managers = Maps.newHashMap();
    private static boolean                        isInit   = false;

    public static void init()
    {
        if (isInit)
        {
            LSLib.NETWORK.registerPacket(DataManagerPacket.class);
            isInit = true;
        }
    }

    public static DataManager createManager(String name)
    {
        if (!hasEntry(name))
        {
            DataManager manager = DataManager.create();
            managers.put(name, manager);
            return manager;
        }
        return null;
    }

    // *********************************************************************************************

    public static boolean hasEntry(String key)
    {
        isValidKey(key);
        return managers.containsKey(key);
    }

    public static DataManager getEntry(String key)
    {
        // Check key
        isValidKey(key);

        // Get the potential data type
        DataManager manager = managers.get(key);

        // Check if the key exist into the system
        if (manager == null)
            noValue(key);

        return manager;
    }

    // *********************************************************************************************

    /**
     * Test if a key is valid
     * 
     * @param key
     *            The key name
     * @throws IllegalArgumentException
     *             If the key is not valid
     */
    private static void isValidKey(String key)
    {
        if (key == null || key.isEmpty())
            throwArgumentException("Key null or empty");
    }

    private static void noValue(String key)
    {
        throwArgumentException(key + " has no entry in the table!");
    }

    private static void throwArgumentException(String text)
    {
        throw new IllegalArgumentException(text);
    }
}
