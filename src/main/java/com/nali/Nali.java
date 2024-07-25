package com.nali;

import com.nali.network.NetworkRegistry;
import com.nali.particle.ParticleRegistry;
import com.nali.system.ClientLoader;
import com.nali.system.ServerLoader;
import com.nali.system.opengl.memo.client.MemoC;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Mod(modid = Nali.ID)
public class Nali
{
    public final static String ID = "nali";

//    @Instance
//    public static Nali I;

//    public Configuration configuration;
    public static Logger LOGGER = LogManager.getLogger(ID);
//    public Random random = new Random();

//    @SideOnly(Side.CLIENT)
//    public ClientLoader clientloader;
//    public BothLoader bothloader;

    @EventHandler
    public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
    {
//        this.bothloader = new BothLoader();
//        this.bothloader.pairModel();
        if (event.getSide().isClient())
        {
//            this.configuration = new Configuration(event.getSuggestedConfigurationFile());
//            this.configuration.load();
//            MyConfig.registerConfig();
//            ObjectWorldDraw.loadWithConfig();
//            KeyRegistryHelper.set();
//            MemoCurrent.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(NaliConfig.SHADER.max_bones << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
            MemoC.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(MemoC.MAX_BONE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

//            OpenGLCurrentMemory.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//            Nali.LOGGER.info("SIZE " + OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE);
//            this.clientloader = new ClientLoader();
//            this.clientloader.loadPreInit();
            ClientLoader.loadPreInit();
        }
    }

    @EventHandler
    public void onFMLInitializationEvent(FMLInitializationEvent event)
    {
        NetworkRegistry.register();

        if (event.getSide().isClient())
        {
            ParticleRegistry.register();
//            DataLoader.setMemory();
//            ClientLoader.loadInit();
        }
        if (FMLLaunchHandler.side() == Side.SERVER)
        {
            ServerLoader.pairSound();
        }
    }

//    @EventHandler
//    public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
//    {
//        this.bothloader.clear();
//    }

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

    public static void warn(Throwable t)
    {
        LOGGER.warn(t, t);
    }

    public static void warn(String s)
    {
        LOGGER.warn(s);
    }
}
