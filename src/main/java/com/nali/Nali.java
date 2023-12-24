package com.nali;

import com.nali.config.MyConfig;
import com.nali.draw.ObjectWorldDraw;
import com.nali.key.KeyRegistryHelper;
import com.nali.system.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
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
    public static Nali I;
    public static Configuration CONFIGURATION;

    public static Logger LOGGER;

    public static Random RANDOM = new Random();

    @EventHandler
    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
        CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());

        if (event.getSide().isClient())
        {
            MyConfig.registerConfig();
            ObjectWorldDraw.loadWithConfig();
            KeyRegistryHelper.set();
        }
    }

    public static void error(Throwable t)
    {
        Nali.LOGGER.error(t);
        FMLCommonHandler.instance().exitJava(-1, true);
    }

    public static void error(String s)
    {
        Nali.LOGGER.error(s);
        FMLCommonHandler.instance().exitJava(-1, true);
    }
}
