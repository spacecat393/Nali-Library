package com.nali;

import com.nali.network.NetworkRegistry;
import com.nali.particle.ParticleRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//"required-after:mixinbooter", "required-before:nali@[1.2.0]", "required-before:small@[1.2.0]"
@Mod(modid = Nali.ID)
public class Nali
{
	public final static String ID = "nali";

//	@Instance
//	public static Nali I;

	public final static Logger LOGGER = LogManager.getLogger(ID);
//	public Random random = new Random();

//	@EventHandler
//	public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
//	{
//	}

	@EventHandler
	public void onFMLInitializationEvent(FMLInitializationEvent event)
	{
		NetworkRegistry.register();

		if (event.getSide().isClient())
		{
			ParticleRegistry.register();
		}
//		if (FMLLaunchHandler.side() == Side.SERVER)
//		{
//		}
	}

//	@EventHandler
//	public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
//	{
//	}

//	@EventHandler
//	public void onFMLLoadCompleteEvent(FMLLoadCompleteEvent event)
//	{
//	}

//	@EventHandler
//	public void onFMLServerStartedEvent(FMLServerStartedEvent event)
//	{
////		if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
//		if (event.getSide() == Side.SERVER)
//		{
//			ServerLoader.init();
//		}
//	}
//
//	@EventHandler
//	public void onFMLServerStoppingEvent(FMLServerStoppingEvent event)
//	{
//		if ()
//		{
//
//		}
//	}

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
