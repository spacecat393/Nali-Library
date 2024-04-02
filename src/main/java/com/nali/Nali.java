package com.nali;

import com.nali.config.MyConfig;
import com.nali.draw.ObjectWorldDraw;
import com.nali.key.KeyRegistryHelper;
import com.nali.networks.NetworksRegistry;
import com.nali.particle.ParticleRegistry;
import com.nali.system.DataLoader;
import com.nali.system.Reference;
import com.nali.system.opengl.memory.OpenGLCurrentMemory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
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
            CONFIGURATION.load();
//            MyConfig.registerConfig();
            ObjectWorldDraw.loadWithConfig();
            KeyRegistryHelper.set();
            OpenGLCurrentMemory.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(MyConfig.SHADER.max_bones << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

//            OpenGLCurrentMemory.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//            Nali.LOGGER.info("SIZE " + OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE);
        }
    }

    @EventHandler
    public void onFMLInitializationEvent(FMLInitializationEvent event)
    {
        NetworksRegistry.register();

        if (event.getSide().isClient())
        {
            ParticleRegistry.register();
            DataLoader.setMemory();
        }
    }

    public static void error(Throwable t)
    {
        LOGGER.error(t, t);
        FMLCommonHandler.instance().exitJava(-1, true);
    }

    public static void error(String s)
    {
        LOGGER.error(s);
        FMLCommonHandler.instance().exitJava(-1, true);
    }

//    public static void debug(String s)
//    {
//        LOGGER.info(s);
//    }
}
