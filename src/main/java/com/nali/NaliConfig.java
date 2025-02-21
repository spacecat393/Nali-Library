package com.nali;

import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
//@Config(modid = ID)
public class NaliConfig
{
	public final static boolean GL_DEBUG = true;
	public final static boolean VAO = false;
	public final static String ATTRIBUTE = "attribute";

	public final static boolean NEED_EXTRA = true;

	//0 STATE
	//1-4 AL_GAIN
	//5-8 AL_PITCH
	//9-12 BGM_ID_COUNT
	//13-? BGM_ID

//	public static String GLSL = "100";
//	public static String ATTRIBUTE = "attribute";
	public static byte STATE = 1+4+8+16;//PRE_SHADER USE_SWITCH USE_YT-DLP USE_FFMPEG FREE_MEMORY DISABLE_MC_LOAD

	public static float[] FLOAT_ARRAY =
	{
		1.0F,//0 ALL
		0.25F,//1 BGM
		1.0F,//2 ENTITY
		1.0F,//3 BLOCK
		1.0F,//4 EFFECT

		1.0F,//5 ALL
		1.0F,//6 BGM
		1.0F,//7 ENTITY
		1.0F,//8 BLOCK
		1.0F//9 EFFECT
	};

	public static String BGM_ID = "";
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
	public static byte[] getByteArray()
	{
		byte[] bgm_id_byte_array = BGM_ID.getBytes();
		int bgm_id_length = bgm_id_byte_array.length;
		byte[] byte_array = new byte[1 + 4 * 10 + 4 + bgm_id_length];
		int index = 0;
		byte_array[index++] = STATE;
		for (float v : FLOAT_ARRAY)
		{
			ByteWriter.set(byte_array, v, index);
			index += 4;
		}

		ByteWriter.set(byte_array, bgm_id_length, index);
		index += 4;
		System.arraycopy(bgm_id_byte_array, 0, byte_array, index, bgm_id_length);
		return byte_array;
	}

	public static void set(byte[] byte_array)
	{
		int index = 0;
		STATE = byte_array[index++];
		for (int i = 0; i < FLOAT_ARRAY.length; ++i)
		{
			FLOAT_ARRAY[i] = ByteReader.getFloat(byte_array, index);
			index += 4;
		}

		int bgm_id_length = ByteReader.getInt(byte_array, index);
		index += 4;
		BGM_ID = new String(byte_array, index, bgm_id_length);
	}
}
