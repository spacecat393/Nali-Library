package com.nali;

import com.nali.network.NetworkRegistry;
import com.nali.particle.ParticleRegistry;
import com.nali.system.ServerLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//"required-after:mixinbooter", "required-before:nali@[1.2.0]", "required-before:small@[1.2.0]"
@Mod(modid = Nali.ID)
public class Nali
{
	public final static String ID = "nali";

//	@Instance
//	public static Nali I;

//	public Configuration configuration;
	public final static Logger LOGGER = LogManager.getLogger(ID);
//	public Random random = new Random();

//	@SideOnly(Side.CLIENT)
//	public ClientLoader clientloader;
//	public BothLoader bothloader;

//	@EventHandler
//	public void onFMLPreInitializationEvent(FMLPreInitializationEvent event)
//	{
////		this.bothloader = new BothLoader();
////		this.bothloader.pairModel();
//		if (event.getSide().isClient())
//		{
//			FULL_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(new float[]
//			{
//				-1, 1, 0.0F, 1.0F,
//				-1, -1, 0.0F, 0.0F,
//				1, -1, 1.0F, 0.0F,
//
//				-1, 1, 0.0F, 1.0F,
//				1, -1, 1.0F, 0.0F,
//				1, 1, 1.0F, 1.0F
//			}));
////			DrawWorldExFb.gen();
////			if (true)
////			{
////			MC_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////				DEPTH_COLOR0_TEXTURE = GL11.glGenTextures();
////
////				R_MCTB_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////				R_MCTB_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
////
////				R_S_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////				R_S_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
////
////				R_T_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////				R_T_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
////
////				R_G_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////				R_G_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
////
////				R_TG_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////				R_TG_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
////
////				R_M_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////				R_M_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
////			}
////			MC_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			MC_RENDERBUFFER = OpenGlHelper.glGenRenderbuffers();
////			FULL_X5_ARRAY_BUFFER = genBuffer(createFloatByteBuffer(new float[]
////			{
//////				-0.75F, 0.75F, 0.0F, 1.0F,
//////				-0.75F, -0.75F, 0.0F, 0.0F,
//////				0.75F, -0.75F, 1.0F, 0.0F,
//////
//////				-0.75F, 0.75F, 0.0F, 1.0F,
//////				0.75F, -0.75F, 1.0F, 0.0F,
//////				0.75F, 0.75F, 1.0F, 1.0F
////
//////				-0.75F, 0.75F, 0.25F, 0.75F,
//////				-0.75F, -0.75F, 0.25F, 0.25F,
//////				0.75F, -0.75F, 0.75F, 0.25F,
//////
//////				-0.75F, 0.75F, 0.25F, 0.75F,
//////				0.75F, -0.75F, 0.75F, 0.25F,
//////				0.75F, 0.75F, 0.75F, 0.75F
////			}));
//
////			MC_DEPTH_TEXTURE = GL11.glGenTextures();
//
//////			R_MC_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_MC_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////			R_MC_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
//////			R_MC2_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_MC2_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////			R_MC2_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
////			R_MCTB_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
//
//////			R_MCH_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_MCH_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////			R_MCH_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
//////			R_M_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_M_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
//////			R_M_RENDERBUFFER = OpenGlHelper.glGenRenderbuffers();
////			R_M_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
////			R_S_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_S_RENDERBUFFER = OpenGlHelper.glGenRenderbuffers();
//
////			R_T_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_T_RENDERBUFFER = OpenGlHelper.glGenRenderbuffers();
//
////			R_G_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
//
////			R_GD_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_GD_DEPTH_TEXTURE = GL11.glGenTextures();
////
////			R_GDB_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_GDB_DEPTH_TEXTURE = GL11.glGenTextures();
//
////			R_TG_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////			R_TG_RENDERBUFFER = OpenGlHelper.glGenRenderbuffers();
//
////			this.configuration = new Configuration(event.getSuggestedConfigurationFile());
////			this.configuration.load();
////			MyConfig.registerConfig();
////			ObjectWorldDraw.loadWithConfig();
////			KeyRegistryHelper.set();
////			MemoCurrent.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(NaliConfig.SHADER.max_bones << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
////			MemoC.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(MemoC.MAX_BONE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//
////			OpenGLCurrentMemory.OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
////			Nali.LOGGER.info("SIZE " + OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE);
////			this.clientloader = new ClientLoader();
////			this.clientloader.loadPreInit();
////			ClientLoader.loadPreInit();
//		}
//	}

	@EventHandler
	public void onFMLInitializationEvent(FMLInitializationEvent event)
	{
		NetworkRegistry.register();

		if (event.getSide().isClient())
		{
			ParticleRegistry.register();
//			DataLoader.setMemory();
//			ClientLoader.loadInit();
		}
		if (FMLLaunchHandler.side() == Side.SERVER)
		{
			ServerLoader.pairSound();
		}
	}

//	@EventHandler
//	public void onFMLPostInitializationEvent(FMLPostInitializationEvent event)
//	{
//		if (event.getSide().isClient())
//		{
//			ClientLoader.loadPreInit();
//			ClientLoader.loadInit();
//		}
//////		this.bothloader.clear();
//	}

//	@EventHandler
//	public void onFMLLoadCompleteEvent(FMLLoadCompleteEvent event)
//	{
//		if (event.getSide().isClient())
//		{
//			NaliGL.init();
//			ClientLoader.loadPreInit();
//			ClientLoader.loadInit();
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

//	public static void check()
//	{
//		int error = GL11.glGetError();
//		if (error != GL11.GL_NO_ERROR)
//		{
//			error(GLU.gluErrorString(error));
//		}
//	}
}
