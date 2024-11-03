package com.nali;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
//@Config(modid = ID)
public class NaliConfig
{
	//0 STATE
	//1-4 AL_GAIN
	//5-8 AL_PITCH

	//9-12 GL_SHADING_LANGUAGE_VERSION_COUNT
	//13-? GL_SHADING_LANGUAGE_VERSION

	//?-?+4 ATTRIBUTE_COUNT
	//?-? ATTRIBUTE

	public static String GLSL = "100";
	public static String ATTRIBUTE = "attribute";
	public static byte STATE = 1+4+8;//PRE_SHADER USE_SWITCH USE_YT-DLP USE_FFMPEG
	public static float AL_GAIN = 1.0F;
	public static float AL_PITCH = 1.0F;
//	public static byte[] CONFIG_BYTE_ARRAY;

//	@Config.Name("Shader")
//	public static final Shader SHADER = new Shader();
//
//	@Config.Name("Sound")
//	public static final Sound SOUND = new Sound();
//
//	public static class Shader
//	{
//		@Config.Name("Pre-Shaders")
//		@Config.Comment("First compile")
//		@Config.RequiresMcRestart
//		public boolean pre_shader = true;
//
//		@Config.Name("Use-Switch")
//		@Config.Comment("130+")
//		@Config.RequiresMcRestart
//		public boolean use_switch = false;
//
//		@Config.Name("GL_SHADING_LANGUAGE_VERSION")
//		@Config.Comment("\"100\" \"120\" \"460\"")
//		@Config.RequiresMcRestart
//		public String gl_shading_language_version = "100";
//
//		@Config.Name("ATTRIBUTE")
//		@Config.Comment("\"attribute\" \"in\"")
//		@Config.RequiresMcRestart
//		public String attribute = "attribute";
//	}
//
//	public static class Sound
//	{
//		@Config.Name("ffmpeg")
//		@Config.RequiresMcRestart
//		public boolean ffmpeg = true;
//
//		@Config.Name("AL_GAIN")
//		public float al_gain = 1.0F;
//
//		@Config.Name("AL_PITCH")
//		public float al_pitch = 1.0F;
//	}
//
//	@Mod.EventBusSubscriber(modid = ID, value = Side.CLIENT)
//	public static class ConfigEvent
//	{
//		@SubscribeEvent
//		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
//		{
//			if (event.getModID().equals(ID))
//			{
//				ConfigManager.sync(ID, Config.Type.INSTANCE);
//			}
//		}
//	}
}
