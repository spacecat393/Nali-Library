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

	//0 STATE
	//1-4 AL_GAIN
	//5-8 AL_PITCH
	//9-12 BGM_ID_COUNT
	//13-? BGM_ID

//	public static String GLSL = "100";
//	public static String ATTRIBUTE = "attribute";
	public static byte STATE = 1+4+8;//PRE_SHADER USE_SWITCH USE_YT-DLP USE_FFMPEG

	public static float
		AL_GAIN_ALL = 1.0F,
		AL_GAIN_BGM = 0.25F,
		AL_GAIN_ENTITY = 1.0F,
		AL_GAIN_BLOCK = 1.0F,
		AL_GAIN_EFFECT = 1.0F,

		AL_PITCH_ALL = 1.0F,
		AL_PITCH_BGM = 1.0F,
		AL_PITCH_ENTITY = 1.0F,
		AL_PITCH_BLOCK = 1.0F,
		AL_PITCH_EFFECT = 1.0F;

	public static String BGM_ID = "eeXVnP0zuMo";
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
		byte[] byte_array = new byte[1+4+4+4+4+4+4+4+4+4+4+4 + bgm_id_length];
		byte_array[0] = STATE;
		ByteWriter.set(byte_array, AL_GAIN_ALL, 1);
		ByteWriter.set(byte_array, AL_GAIN_BGM, 1+4);
		ByteWriter.set(byte_array, AL_GAIN_ENTITY, 1+4+4);
		ByteWriter.set(byte_array, AL_GAIN_BLOCK, 1+4+4+4);
		ByteWriter.set(byte_array, AL_GAIN_EFFECT, 1+4+4+4+4);

		ByteWriter.set(byte_array, AL_PITCH_ALL, 1+4+4+4+4+4);
		ByteWriter.set(byte_array, AL_PITCH_BGM, 1+4+4+4+4+4+4);
		ByteWriter.set(byte_array, AL_PITCH_ENTITY, 1+4+4+4+4+4+4+4);
		ByteWriter.set(byte_array, AL_PITCH_BLOCK, 1+4+4+4+4+4+4+4+4);
		ByteWriter.set(byte_array, AL_PITCH_EFFECT, 1+4+4+4+4+4+4+4+4+4);

		ByteWriter.set(byte_array, bgm_id_length, 1+4+4+4+4+4+4+4+4+4+4);
		System.arraycopy(bgm_id_byte_array, 0, byte_array, 1+4+4+4+4+4+4+4+4+4+4+4, bgm_id_length);
		return byte_array;
	}

	public static void set(byte[] byte_array)
	{
		STATE = byte_array[0];
		AL_GAIN_ALL = ByteReader.getFloat(byte_array, 1);
		AL_GAIN_BGM = ByteReader.getFloat(byte_array, 1+4);
		AL_GAIN_ENTITY = ByteReader.getFloat(byte_array, 1+4+4);
		AL_GAIN_BLOCK = ByteReader.getFloat(byte_array, 1+4+4+4);
		AL_GAIN_EFFECT = ByteReader.getFloat(byte_array, 1+4+4+4+4);

		AL_PITCH_ALL = ByteReader.getFloat(byte_array, 1+4+4+4+4+4);
		AL_PITCH_BGM = ByteReader.getFloat(byte_array, 1+4+4+4+4+4+4);
		AL_PITCH_ENTITY = ByteReader.getFloat(byte_array, 1+4+4+4+4+4+4+4);
		AL_PITCH_BLOCK = ByteReader.getFloat(byte_array, 1+4+4+4+4+4+4+4+4);
		AL_PITCH_EFFECT = ByteReader.getFloat(byte_array, 1+4+4+4+4+4+4+4+4+4);

		int bgm_id_length = ByteReader.getInt(byte_array, 1+4+4+4+4+4+4+4+4+4+4);
		BGM_ID = new String(byte_array, 1+4+4+4+4+4+4+4+4+4+4+4, bgm_id_length);
	}
}
