package com.nali;

import com.nali.gui.page.PageConfig;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

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
	public static float AL_GAIN = 1.0F;
	public static float AL_PITCH = 1.0F;
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
		byte[] byte_array = new byte[1+4+4+4 + bgm_id_length];
		byte_array[0] = STATE;
		ByteWriter.set(byte_array, AL_GAIN, 1);
		ByteWriter.set(byte_array, AL_PITCH, 1+4);
		ByteWriter.set(byte_array, bgm_id_length, 1+4+4);
		System.arraycopy(bgm_id_byte_array, 0, byte_array, 1+4+4+4, bgm_id_length);
		return byte_array;
	}

	public static void set(byte[] byte_array)
	{
		STATE = byte_array[0];
		AL_GAIN = ByteReader.getFloat(byte_array, 1);
		AL_PITCH = ByteReader.getFloat(byte_array, 1+4);
		int bgm_id_length = ByteReader.getInt(byte_array, 1+4+4);
		BGM_ID = new String(byte_array, 1+4+4+4, bgm_id_length);
	}

	public static void render()
	{
		PageConfig pageconfig = new PageConfig();
		pageconfig.take();
		pageconfig.init();

		boolean loop = true;
		int tmp_width = -1, tmp_height = -1;
		while (loop)
		{
			int width = Display.getWidth();
			int height = Display.getHeight();

			int h20 = (int)(0.041666668F * height);
			int wh20 = Math.min((int)(0.0234192037470726F * width), h20);

			if (tmp_width != width || tmp_height != height)
			{
				GL11.glViewport(0, 0, width, height);
				tmp_width = width;
				tmp_height = height;
				pageconfig.gen(width, height, wh20, h20);
				if (pageconfig.scroll != 0)
				{
					pageconfig.scroll = ((float)pageconfig.select * wh20 * 4 - wh20 * 4) / height;
				}
			}

			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			pageconfig.draw(width, height, wh20, h20);
			Display.update();

			while (Keyboard.next())
			{
				if (Keyboard.getEventKeyState())
				{
					int key = Keyboard.getEventKey();
					//						int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
					//						warn("KEY: " + key);
					//						warn("I: " + i);
					//SHIFT 42
					//space 57
					if ((pageconfig.state & 4) == 4)
					{
						char c = Keyboard.getEventCharacter();
						if (Character.isLetterOrDigit(c) || c == '.'/* || Character.isSpaceChar(c)*/)
						{
							pageconfig.input_stringbuilder.insert(pageconfig.select_box, c);
							++pageconfig.select_box;

							pageconfig.clear();
							pageconfig.init();
							pageconfig.gen(width, height, wh20, h20);
							setScrollEdit(pageconfig, wh20, width, height);
						}
						else if (key == Keyboard.KEY_BACK)
						{
							if (pageconfig.select_box > 0)
							{
								pageconfig.input_stringbuilder.deleteCharAt(pageconfig.select_box - 1);
								--pageconfig.select_box;
							}

							pageconfig.clear();
							pageconfig.init();
							pageconfig.gen(width, height, wh20, h20);
							setScrollEdit(pageconfig, wh20, width, height);
						}
						else if (key == Keyboard.KEY_LEFT)
						{
							if (pageconfig.select_box > 0)
							{
								--pageconfig.select_box;
							}

//							pageconfig.gen(width, height, wh20, h20);
							setScrollEdit(pageconfig, wh20, width, height);
						}
						else if (key == Keyboard.KEY_RIGHT)
						{
							if (pageconfig.select_box < pageconfig.input_stringbuilder.length())
							{
								++pageconfig.select_box;
							}

//							pageconfig.gen(width, height, wh20, h20);
							setScrollEdit(pageconfig, wh20, width, height);
						}
					}
					else
					{
						if (key == Keyboard.KEY_ESCAPE)
						{
							//							config_byte_array = new byte[];
							loop = false;
						}
						else if (key == Keyboard.KEY_LEFT)
						{
							pageconfig.next((byte)-1);
							pageconfig.gen(width, height, wh20, h20);
							pageconfig.scroll = ((float)pageconfig.select * wh20 * 4 - wh20 * 4) / height;
						}
						else if (key == Keyboard.KEY_RIGHT)
						{
							pageconfig.next((byte)1);
							pageconfig.gen(width, height, wh20, h20);
							pageconfig.scroll = ((float)pageconfig.select * wh20 * 4 - wh20 * 4) / height;
						}
					}
					if (key == Keyboard.KEY_RETURN)
					{
						pageconfig.enter();
						pageconfig.clear();
						pageconfig.init();
						pageconfig.gen(width, height, wh20, h20);
//								pageconfig.update(width, height, wh20, h20);
					}
					else if (key == Keyboard.KEY_UP)
					{
						pageconfig.scroll -= wh20 * 4.0F / height;
					}
					else if (key == Keyboard.KEY_DOWN)
					{
						pageconfig.scroll += wh20 * 4.0F / height;
					}
				}
			}
		}

		pageconfig.free();
		pageconfig.clear();
	}

	public static void setScrollEdit(PageConfig pageconfig, int wh20, int width, int height)
	{
		int wh10 = wh20 / 2;
		int nl_ss = wh20 + wh10;
		int nl_x = wh20, nl_y = 0;

		for (int i = 0; i < pageconfig.select_box; ++i)
		{
			if (nl_x > width - nl_ss)
			{
				nl_x = wh20;
				nl_y += nl_ss;
			}
			nl_x += nl_ss;
		}

		pageconfig.scroll = 2.0F * (float)nl_y / height;
	}
}
