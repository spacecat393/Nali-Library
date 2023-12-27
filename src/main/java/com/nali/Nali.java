package com.nali;

import com.nali.draw.ObjectWorldDraw;
import com.nali.key.KeyRegistryHelper;
import com.nali.system.Reference;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@Mod(modid = Reference.MOD_ID, name = Reference.NAME)
public class Nali
{
    @Instance
    public static Nali I;
    public static Configuration CONFIGURATION;

    public static Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

    public static Random RANDOM = new Random();

    @EventHandler
    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
    {
        if (event.getSide().isClient())
        {
            CONFIGURATION = new Configuration(event.getSuggestedConfigurationFile());
            Nali.CONFIGURATION.load();
//            MyConfig.registerConfig();
            ObjectWorldDraw.loadWithConfig();
            KeyRegistryHelper.set();
        }
    }

    public static void error(Throwable t)
    {
        LOGGER.error(t);
        FMLCommonHandler.instance().exitJava(-1, true);
    }

    public static void error(String s)
    {
        LOGGER.error(s);
        FMLCommonHandler.instance().exitJava(-1, true);
    }
}
