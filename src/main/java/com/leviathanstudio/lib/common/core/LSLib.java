package com.leviathanstudio.lib.common.core;

import com.leviathanstudio.lib.common.LSLRegister;
import com.leviathanstudio.lib.common.config.simple.ConfigFile;
import com.leviathanstudio.lib.common.config.simple.ConfigFileManager;
import com.leviathanstudio.lib.common.data2.DataManagers;
import com.leviathanstudio.lib.common.entity.spawn.PreventSpawnEvent;
import com.leviathanstudio.lib.common.network.LSLNetwork;
import com.leviathanstudio.lib.common.recipe.anvil.AnvilEvent;
import com.leviathanstudio.lib.common.recipe.potion.PreventBrewingEvent;
import com.leviathanstudio.lib.common.registration.parser.ParserManager;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = LSLib.MODID, version = LSLib.VERSION, name = LSLib.NAME)
public class LSLib
{
    public static final String     MODID       = "lslib";
    public static final String     NAME        = "Leviathan Studio Library";
    public static final String     VERSION     = "1.0";
    public static final String     BASE_DOMAIN = "com.leviathanstudio.lib";

    public static final Logger     LOGGER      = LogManager.getLogger(LSLib.NAME);

    public static final LSLNetwork NETWORK     = new LSLNetwork(MODID);

    @Instance(LSLib.MODID)
    public static LSLib            INSTANCE;

    @ConfigFile(configFile = "lslib.cfg", modid = LSLib.MODID, configFolder = "leviathanstudio")
    public static LSLibConfig      config;

    @SidedProxy(clientSide = "com.leviathanstudio.lib.client.ClientProxy", serverSide = "com.leviathanstudio.lib.server.ServerProxy")
    public static CommonProxy      proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        // Process all the configuration using the annotation system
        ConfigFileManager.processConfig();

        // Process some changes if needed after the configuration process
        LSLibConfig.postConfig();

        // Initialize Parser manager with some default parser
        ParserManager.init();

        // Register event for the systems
        LSLRegister.registerEvent(new AnvilEvent());
        LSLRegister.registerEvent(new PreventSpawnEvent());
        LSLRegister.registerEvent(new PreventBrewingEvent());

        // Initialize DataManager
        DataManagers.init();

        // Proxy
        proxy.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Proxy
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        // Proxy
        proxy.postInit();
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event)
    {
    }
}
