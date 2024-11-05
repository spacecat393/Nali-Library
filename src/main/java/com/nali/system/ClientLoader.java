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
import java.lang.reflect.InvocationTargetException;
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
//	public static List<char[]> TEXT_CHAR_ARRAY_LIST = new ArrayList();

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

//		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
//		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		for (Class data_class : data_class_list)
		{
			try
			{
				data_class.getMethod("run").invoke(null);
			}
			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
//				error(e);
			}
		}

//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

		File[] file_array = new File(ID).listFiles();

		if (file_array != null)
		{
			if (NaliConfig.VAO)
			{
				NaliGL.init();
			}

			Map<String, Class> first_data_class_map = new HashMap();
			Map<String, Class> second_data_class_map = new HashMap();
			List<MemoHVo> memohvo_list = new ArrayList();
			List<MemoHVs> memohvs_list = new ArrayList();
			List<MemoHFo> memohfo_list = new ArrayList();

			for (Class data_class : data_class_list)
			{
				try
				{
					Field field = data_class.getField("STATE");
					if (field != null && ((byte)field.get(null) & 1) == 1)
					{
						first_data_class_map.put(StringReader.get(data_class)[1], data_class);
					}
					else
					{
						second_data_class_map.put(StringReader.get(data_class)[1], data_class);
					}
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}
			}

			File[] first_file_array = new File[first_data_class_map.size()];
			File[] second_file_array = new File[second_data_class_map.size()];

			int i0 = 0, i1 = 0;
			for (File file : file_array)
			{
				if (first_data_class_map.get(file.getName()) != null)
				{
					first_file_array[i0++] = file;
				}
				else
				{
					second_file_array[i1++] = file;
				}
			}

			List<String> shader_name_string_list = new ArrayList();
			List<String> model_name_string_list = new ArrayList();
			List<String[][]> shader_string_2d_array_list = new ArrayList();
			Map<String, String[][]> shader_string_2d_array_map = new HashMap();
			List<String[][]> model_string_2d_array_list = new ArrayList();
			List<MemoA0[]> memoa0_array_list = new ArrayList();
			List<String[][]> attriblocation_string_2d_array_list = new ArrayList();
			List<Integer> shader_id_integer_list = new ArrayList();

			Map<String, Integer> frag_integer_map = new HashMap();
			Map<String, Integer> vert_o_integer_map = new HashMap();

//			Map<String, String[]> texture_mipmap_map = new HashMap();

			for (File file : file_array)
			{
				File shader_file = new File(file, "shader.dat");
				if (shader_file.isFile())
				{
					String name = file.getName();
					shader_name_string_list.add(name);
					String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());
					shader_string_2d_array_list.add(shader_string_2d_array);
					shader_string_2d_array_map.put(name, shader_string_2d_array);
				}

				File model_file = new File(file, "model.dat");
				if (model_file.isFile())
				{
					String name_string = file.getName();
					model_name_string_list.add(name_string);
					String[][] model_string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
					model_string_2d_array_list.add(model_string_2d_array);

					for (String[] model_string_array : model_string_2d_array)
					{
						int shader_id = Integer.parseInt(model_string_array[3]);
						shader_id_integer_list.add(shader_id);

						String[][] shader_string_2d_array = shader_string_2d_array_map.get(model_string_array[2]);

						String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + shader_string_2d_array[shader_id][0] + "/shader/" + shader_string_2d_array[shader_id][1] + "/attrib.dat"));
						attriblocation_string_2d_array_list.add(attriblocation_string_2d_array);

						memoa0_array_list.add(MemoA0.gen(model_string_array, attriblocation_string_2d_array, Nali.ID + "/" + name_string/*, shader_id*/));
					}
				}
			}

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

			Object[] object_array = new Object[]
			{
				memohvo_list,
				memohvs_list,
				memohfo_list,
				frag_integer_map,
				vert_o_integer_map,
				shader_name_string_list,
				model_name_string_list,
				shader_string_2d_array_list,
				shader_string_2d_array_map,
				model_string_2d_array_list,
				memoa0_array_list,
				attriblocation_string_2d_array_list,
				shader_id_integer_list
			};
			load(first_file_array, first_data_class_map, object_array);

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

			loadSound(first_file_array, first_data_class_map);
			if (second_file_array.length != 0)
			{
				load(second_file_array, second_data_class_map, object_array);
				loadSound(second_file_array, second_data_class_map);
			}
		}
		else
		{
			warn("SKIP_DATA");
		}
	}

	public static void load(File[] file_array, Map<String, Class> data_class_map, Object[] object_array)
	{
		List<MemoHVo> memohvo_list = (List)object_array[0];
		List<MemoHVs> memohvs_list = (List)object_array[1];
		List<MemoHFo> memohfo_list = (List)object_array[2];
		Map<String, Integer> frag_integer_map = (Map)object_array[3];
		Map<String, Integer> vert_o_integer_map = (Map)object_array[4];
		List<String> shader_name_string_list = (List)object_array[5];
		List<String> model_name_string_list = (List)object_array[6];
		List<String[][]> shader_string_2d_array_list = (List)object_array[7];
		Map<String, String[][]> shader_string_2d_array_map = (Map)object_array[8];
		List<String[][]> model_string_2d_array_list = (List)object_array[9];
		List<MemoA0[]> memoa0_array_list = (List)object_array[10];
		List<String[][]> attriblocation_string_2d_array_list = (List)object_array[11];
		List<Integer> shader_id_integer_list = (List)object_array[12];

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
//			File[] vert_file_array = new File(file + "/shader/" + NaliConfig.GLSL + "/vert/o").listFiles();
			File[] vert_file_array = new File(file, "shader/vert/o").listFiles();
			if (vert_file_array != null)
			{
				int step = memohvo_list.size();
				vert_o_integer_map.put(file.getName(), step);

				for (File vert_file : vert_file_array)
				{
					memohvo_list.add(null);
				}

				for (File vert_file : vert_file_array)
				{
					String name = vert_file.getName();
					int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
					memohvo_list.set(index + step, new MemoHVo(vert_file.toString()));
				}
			}
		}

		for (File file : file_array)
		{
//			File[] frag_file_array = new File(file + "/shader/" + NaliConfig.GLSL + "/frag").listFiles();
			File[] frag_file_array = new File(file, "shader/frag").listFiles();
			if (frag_file_array != null)
			{
				int step = memohfo_list.size();
				frag_integer_map.put(file.getName(), step);

				for (File frag_file : frag_file_array)
				{
					memohfo_list.add(null);
				}

				for (File frag_file : frag_file_array)
				{
					String name = frag_file.getName();
					int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
					memohfo_list.set(index + step, new MemoHFo(frag_file.toString()));
				}
			}
		}

		int i = 0/*, l = 0*/;
		for (String[][] string_2d_array : shader_string_2d_array_list)
		{
			try
			{
				data_class_map.get(shader_name_string_list.get(i++)).getField("SHADER_STEP").set(null, S_LIST.size());
			}
			catch (IllegalAccessException | NoSuchFieldException e)
			{
				error(e);
			}

			for (String[] string_array : string_2d_array)
			{
				String shader_string = string_array[3];
				byte[] byte_array = shader_string.getBytes();
				int v = Integer.parseInt(new String(byte_array, 1, byte_array.length - 1));
				int f = Integer.parseInt(string_array[5]) + frag_integer_map.get(string_array[4]);
				if (shader_string.charAt(0) == 's')
				{
					MemoHVs memohvs = new MemoHVs(string_array);
					memohvs_list.add(memohvs);
					S_LIST.add(new MemoS(memohvs.shader, memohfo_list.get(f).shader, string_array/*, path_string*/));
				}
				else
				{
					S_LIST.add(new MemoS(memohvo_list.get(v + vert_o_integer_map.get(string_array[2])).shader, memohfo_list.get(f).shader, string_array/*, path_string*/));
				}
			}
		}

		i = 0;
		int l = 0;
		for (String[][] string_2d_array : model_string_2d_array_list)
		{
			String path_string = Nali.ID + "/" + model_name_string_list.get(i);

			try
			{
				data_class_map.get(model_name_string_list.get(i++)).getField("MODEL_STEP").set(null, G_LIST.size());
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

			for (String[] string_array : string_2d_array)
			{
				G_LIST.add(new MemoG(/*index_int_array_list, */memoa0_array_list.get(l), shader_string_2d_array_map.get(string_array[2]), attriblocation_string_2d_array_list.get(l), shader_id_integer_list.get(l++), string_array, path_string));
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

		i = 0;
		for (File file : file_array)
		{
			File model_file = new File(file, "frame.dat");
			if (model_file.isFile())
			{
				String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
				try
				{
					data_class_map.get(file.getName()).getField("FRAME_STEP").set(null, F_LIST.size());
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}

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
		//s0-load
		PageLoad pageload = new PageLoad();
		pageload.take();
		pageload.init();
//			int tmp_width = -1, tmp_height = -1;
//			while (true)
//			{
		int width = Display.getWidth();
		int height = Display.getHeight();
//				if (tmp_width != width || tmp_height != height)
//				{
		GL11.glViewport(0, 0, width, height);
//					tmp_width = width;
//					tmp_height = height;
		pageload.gen(width, height);
//				}
//				if (false)
//				{
//					break;
//				}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		pageload.draw();
		Display.update();
//			}
		pageload.free();
		pageload.clear();
		//e0-load
//			while (true)
//			{
//				if (false)
//				{
//					break;
//				}
//			}

//			try
//			{
//				Thread.sleep(5000);
//			}
//			catch (InterruptedException e)
//			{
//				error(e);
//			}

//			//s0-sound
//			if ((NaliConfig.STATE & 8) == 8)
//			{
//				NaliAL.alSourceStop(source);
//			}
//			//e0-sound
	}
}
