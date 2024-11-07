package com.nali.system;

import com.nali.Nali;
import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.gui.page.PageLoad;
import com.nali.render.RenderO;
import com.nali.render.RenderS;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memo.client.MemoN;
import com.nali.system.opengl.memo.client.*;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.*;

@SideOnly(Side.CLIENT)
public class ClientLoader
{
	public static int BGM_BUFFER = -1;
	public static int BGM_SOURCE = -1;
	public static List<Integer> TEXTURE_INTEGER_LIST = new ArrayList();
	public static List<Integer> SOUND_INTEGER_LIST = new ArrayList();
	public static List<MemoG> G_LIST = new ArrayList();//graphic
	public static List<MemoS> S_LIST = new ArrayList();//shader
	public static List<MemoF> F_LIST = new ArrayList();//frame
	public static Map<Integer, MemoA2> A2_MAP = new HashMap();//3d skinning cpu

	public static void loadPreInit()
	{
		List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
//		for (Class data_class : data_class_list)
//		{
//			try
//			{
//				data_class.getMethod("run").invoke(null);
//			}
//			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//			{
//			}
//		}

		File[] file_array = new File(ID).listFiles();
		if (file_array != null)
		{
			if (NaliConfig.VAO)
			{
				NaliGL.init();
			}

			//s0-data
			int max_ordinal = 0;
			for (Class data_class : data_class_list)
			{
				try
				{
					Field field = data_class.getField("ORDINAL");
					max_ordinal = Math.max(max_ordinal, (byte)field.get(null));
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}
			}
			++max_ordinal;

			Map<String, Class>[] data_class_map_array = new Map[max_ordinal];
			int[] ordinal_int_array = new int[max_ordinal];
			for (int i = 0; i < max_ordinal; ++i)
			{
				data_class_map_array[i] = new HashMap();
			}
			for (Class data_class : data_class_list)
			{
				try
				{
					Field field = data_class.getField("ORDINAL");
					byte ordinal = (byte)field.get(null);
					data_class_map_array[ordinal].put(StringReader.get(data_class)[1], data_class);
					++ordinal_int_array[ordinal];
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}
			}

			File[][] file_2d_array = new File[max_ordinal][];
			for (int i = 0; i < max_ordinal; ++i)
			{
				file_2d_array[i] = new File[ordinal_int_array[i]];
			}

			int[] add_int_array = new int[max_ordinal];
			for (File file : file_array)
			{
				String name = file.getName();
				for (int i = 0; i < max_ordinal; ++i)
				{
					if (data_class_map_array[i].get(name) != null)
					{
						file_2d_array[i][add_int_array[i]++] = file;
						break;
					}
				}
			}

			Map<String, MemoHVo[]> memohvo_array_map = new HashMap();
			Map<String, MemoHFo[]> memohfo_array_map = new HashMap();
			//e0-data

			load(file_2d_array[0], data_class_map_array[0], memohvo_array_map, memohfo_array_map);

			//s0-config
			File tmp_file = new File("nali/nali/tmp");
			tmp_file.mkdirs();
			Path config_path = new File("nali/nali/tmp/config.bin").toPath();

			try
			{
				NaliConfig.set(Files.readAllBytes(config_path));
			}
			catch (Exception e)
			{
				NaliConfig.render();

				try
				{
					Files.write(config_path, NaliConfig.getByteArray());
				}
				catch (IOException ex)
				{
					error(ex);
				}
			}
			//e0-config

			//s0-sound
			if ((NaliConfig.STATE & 8) == 8)
			{
				NaliAL.load();
				NaliAL.init();
			}
			String bgm = "nali/nali/tmp/" + NaliConfig.BGM_ID;
			if ((NaliConfig.STATE & 4) == 4 && !new File(bgm).isFile())
			{
				try
				{
					Process process = Command.get("yt-dlp", "-f", "bestaudio", "-o", bgm, "https://www.youtube.com/watch?v=" + NaliConfig.BGM_ID).redirectErrorStream(true).start();
					process.waitFor();
					process.destroy();
				}
				catch (IOException | InterruptedException e)
				{
					error(e);
				}
			}
			if ((NaliConfig.STATE & 8+4) == 8+4)
			{
				BGM_BUFFER = MemoN.gen(bgm);
				BGM_SOURCE = NaliAL.alGenSources();
				NaliAL.alSourcei(BGM_SOURCE, AL10.AL_BUFFER, BGM_BUFFER);
				NaliAL.alSourcei(BGM_SOURCE, AL10.AL_LOOPING, AL10.AL_TRUE);
				NaliAL.alSourcef(BGM_SOURCE, AL10.AL_GAIN, NaliConfig.AL_GAIN);
				NaliAL.alSourcef(BGM_SOURCE, AL10.AL_PITCH, NaliConfig.AL_PITCH);
				NaliAL.alSourcePlay(BGM_SOURCE);
			}
			//e0-sound

			render();

			for (Class data_class : data_class_list)
			{
				try
				{
					Field field = data_class.getField("MAX_BONE");
					if (field != null)
					{
						int max_bone = (int)field.get(null);
						if (RenderS.MAX_BONE < max_bone)
						{
							RenderS.MAX_BONE = max_bone;
						}
					}
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
				}
			}
			RenderS.BONE_FLOATBUFFER = ByteBuffer.allocateDirect(RenderS.MAX_BONE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

			loadSound(file_2d_array[0], data_class_map_array[0]);
			for (int i = 1; i < max_ordinal; ++i)
			{
				load(file_2d_array[i], data_class_map_array[i], memohvo_array_map, memohfo_array_map);
				loadSound(file_2d_array[i], data_class_map_array[i]);
			}

			List<MemoHVo[]> memohvo_array_list = new ArrayList(memohvo_array_map.values());
			List<MemoHFo[]> memohfo_array_list = new ArrayList(memohfo_array_map.values());
			for (MemoHVo[] memohvo_array : memohvo_array_list)
			{
				for (MemoHVo memohvo : memohvo_array)
				{
					OpenGlHelper.glDeleteShader(memohvo.shader);
				}
			}
			for (MemoHFo[] memohfo_array : memohfo_array_list)
			{
				for (MemoHFo memohfo : memohfo_array)
				{
					OpenGlHelper.glDeleteShader(memohfo.shader);
				}
			}
		}
		else
		{
			warn("SKIP_DATA");
		}
	}

	public static void load(File[] file_array, Map<String, Class> data_class_map, Map<String, MemoHVo[]> memohvo_array_map, Map<String, MemoHFo[]> memohfo_array_map)
	{
		for (File file : file_array)
		{
			File[] texture_file_array = new File(file, "texture").listFiles();
			if (texture_file_array != null)
			{
				try
				{
					int step = TEXTURE_INTEGER_LIST.size();
					data_class_map.get(file.getName()).getField("TEXTURE_STEP").set(null, step);

					for (File texture_file : texture_file_array)
					{
						TEXTURE_INTEGER_LIST.add(null);
					}

					GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
					String[][] texture_string_2d_array = FileDataReader.getMixXStringArray(new File(file, "texture.dat").toPath());
					for (File texture_file : texture_file_array)
					{
						String name = texture_file.getName();
						int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
						TEXTURE_INTEGER_LIST.set(index + step, MemoT.gen(texture_file, Byte.parseByte(texture_string_2d_array[index][0]) == 1));
					}
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, RenderO.INTBUFFER.get(0));
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}
			}
		}

		for (File file : file_array)
		{
			File[] vert_file_array = new File(file, "shader/vert/o").listFiles();
			if (vert_file_array != null)
			{
				MemoHVo[] memohvo_array = new MemoHVo[vert_file_array.length];

				for (File vert_file : vert_file_array)
				{
					String name = vert_file.getName();
					int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
					memohvo_array[index] = new MemoHVo(vert_file.toString());
				}

				memohvo_array_map.put(file.getName(), memohvo_array);
			}
		}

		for (File file : file_array)
		{
			File[] frag_file_array = new File(file, "shader/frag").listFiles();
			if (frag_file_array != null)
			{
				MemoHFo[] memohfo_array = new MemoHFo[frag_file_array.length];

				for (File frag_file : frag_file_array)
				{
					String name = frag_file.getName();
					int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
					memohfo_array[index] = new MemoHFo(frag_file.toString());
				}

				memohfo_array_map.put(file.getName(), memohfo_array);
			}
		}

		List<MemoHVs> memohvs_list = new ArrayList();
		for (File file : file_array)
		{
			File shader_file = new File(file, "shader.dat");
			if (shader_file.isFile())
			{
				String name = file.getName();
				String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());

				try
				{
					data_class_map.get(name).getField("SHADER_STEP").set(null, S_LIST.size());
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}

				for (String[] shader_string_array : shader_string_2d_array)
				{
					String shader_string = shader_string_array[3];
					byte[] byte_array = shader_string.getBytes();
					MemoHFo memohfo = memohfo_array_map.get(shader_string_array[4])[Integer.parseInt(shader_string_array[5])];
					if (shader_string.charAt(0) == 's')
					{
						MemoHVs memohvs = new MemoHVs(shader_string_array);
						S_LIST.add(new MemoS(memohvs.shader, memohfo.shader, shader_string_array));
						OpenGlHelper.glDeleteShader(memohvs.shader);
						memohvs_list.add(memohvs);
					}
					else
					{
						S_LIST.add(new MemoS
						(
							memohvo_array_map.get(shader_string_array[2])[Integer.parseInt(new String(byte_array, 1, byte_array.length - 1))].shader,
							memohfo.shader, shader_string_array
						));
					}
				}
			}
		}

		for (File file : file_array)
		{
			File model_file = new File(file, "model.dat");
			if (model_file.isFile())
			{
				String name = file.getName();
				String path_string = Nali.ID + "/" + name;
				String[][] model_string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());

				try
				{
					data_class_map.get(name).getField("MODEL_STEP").set(null, G_LIST.size());
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}

				int gl_array_buffer_binding;
				int gl_vertex_array_binding;

				int gl_element_array_buffer_binding;
				if (NaliConfig.VAO)
				{
					gl_vertex_array_binding = NaliGL.glVertexArrayBinding();
				}
				else
				{
					GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
					gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
					GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
					gl_element_array_buffer_binding = RenderO.INTBUFFER.get(0);
				}

				for (String[] model_string_array : model_string_2d_array)
				{
					int shader_id = Integer.parseInt(model_string_array[3]);
					String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + model_string_array[2] + "/shader.dat"));
					String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + shader_string_2d_array[shader_id][0] + "/shader/" + shader_string_2d_array[shader_id][1] + "/attrib.dat"));

					G_LIST.add(new MemoG
					(
						MemoA0.gen(model_string_array, attriblocation_string_2d_array, path_string),
						shader_string_2d_array, attriblocation_string_2d_array, shader_id, model_string_array, path_string
					));
				}

				if (NaliConfig.VAO)
				{
					NaliGL.glBindVertexArray(gl_vertex_array_binding);
				}
				else
				{
					OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
					OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, gl_element_array_buffer_binding);
				}
			}
		}

		for (File file : file_array)
		{
			File frame_file = new File(file, "frame.dat");
			if (frame_file.isFile())
			{
				String[][] string_2d_array = FileDataReader.getMixXStringArray(frame_file.toPath());
				try
				{
					data_class_map.get(file.getName()).getField("FRAME_STEP").set(null, F_LIST.size());
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}

				int i = 0;
				for (String[] string_array : string_2d_array)
				{
					MemoHVs memohvs = memohvs_list.get(i++);
					MemoF memof = new MemoF(memohvs.bone, string_array, file.getPath());
					memof.bind_pose_float_array = memohvs.bind_pose_float_array;
					memof.back_bone_2d_int_array = memohvs.back_bone_2d_int_array;

					F_LIST.add(memof);
				}
			}
		}
	}

	public static void loadSound(File[] file_array, Map<String, Class> data_class_map)
	{
		if ((NaliConfig.STATE & 8) == 8)
		{
			for (File file : file_array)
			{
				File[] sound_file_array = new File(file, "sound").listFiles();
				if (sound_file_array != null)
				{
					try
					{
						int step = SOUND_INTEGER_LIST.size();
						data_class_map.get(file.getName()).getField("SOUND_STEP").set(null, step);

						for (File sound_file : sound_file_array)
						{
							SOUND_INTEGER_LIST.add(null);
						}

						for (File sound_file : sound_file_array)
						{
							String name = sound_file.getName();
							int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
							SOUND_INTEGER_LIST.set(index + step, MemoN.gen(sound_file.getPath()));
						}
					}
					catch (IllegalAccessException | NoSuchFieldException e)
					{
						error(e);
					}
				}
			}
		}
	}

	public static void render()
	{
		PageLoad pageload = new PageLoad();
		pageload.take();
		pageload.init();
		int width = Display.getWidth();
		int height = Display.getHeight();
		GL11.glViewport(0, 0, width, height);
		pageload.gen(width, height);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		pageload.draw();
		Display.update();
		pageload.free();
		pageload.clear();
	}
}
