package com.nali;

import com.nali.config.MyConfig;
import com.nali.system.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME)
public class Nali
{
    @Instance
    public static Nali NALI;
    public static Configuration CONFIGURATION;

    public static Logger LOGGER;

    public static Random RANDOM = new Random();

    @EventHandler
    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
        CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());
        MyConfig.registerConfig();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
