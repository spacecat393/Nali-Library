package com.nali.system;

import com.nali.Nali;
import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import com.nali.gui.page.PageConfig;
import com.nali.gui.page.PageLoad;
import com.nali.gui.page.PageSelect;
import com.nali.render.RenderO;
import com.nali.render.RenderS;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memo.client.MemoN;
import com.nali.system.opengl.memo.MemoF2;
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

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class ClientLoader extends BothLoader
{
	public static int BGM_BUFFER = -1;
	public static int BGM_SOURCE = -1;
	public static List<Integer> TEXTURE_INTEGER_LIST = new ArrayList();
	public static List<Integer> SOUND_INTEGER_LIST = new ArrayList();
	public static List<MemoG> G_LIST = new ArrayList();//graphic
	public static List<MemoS> S_LIST = new ArrayList();//shader
//	public static List<MemoF> F_LIST = new ArrayList();//frame

//	//temp
//	public List<MemoHVs> memohvs_list = new ArrayList();

	public void init()
	{
		Display.update();

		if (NaliConfig.VAO)
		{
			NaliGL.init();
		}

		super.init();
	}

	public void load(int max_ordinal, List<Class> data_class_list, File[][] file_2d_array, Map<String, Class>[] data_class_map_array)
	{
		Map<String, MemoHVo[]> memohvo_array_map = new HashMap();
		Map<String, MemoHFo[]> memohfo_array_map = new HashMap();

		this.load(file_2d_array[0], data_class_map_array[0], memohvo_array_map, memohfo_array_map);

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
			Page.PAGE = new PageConfig();
			Page.PAGE.init();
			Key.KEY = new KeySelect();
			while ((((PageSelect)Page.PAGE).state & 2) == 0)
			{
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
				Page.PAGE.render();
				Display.update();
				Key.KEY.run();
			}
			Page.PAGE.clear();

			Page.PAGE_LIST.clear();
			Page.KEY_LIST.clear();
			Page.PAGE = null;
			Key.KEY = null;

//				try
//				{
//					Files.write(config_path, NaliConfig.getByteArray());
//				}
//				catch (IOException ex)
//				{
//					error(ex);
//				}
		}
		//e0-config

		//s0-sound
		if ((NaliConfig.STATE & 8) == 8)
		{
			NaliAL.load();
			NaliAL.init();
		}
		String bgm = "nali/nali/tmp/bgm";
		if (!NaliConfig.BGM_ID.isEmpty())
		{
			if ((NaliConfig.STATE & 4) == 4 && !new File(bgm).isFile())
			{
				try
				{
					Process process = Command.get("yt-dlp", "-f", "bestaudio", "-o", bgm, NaliConfig.BGM_ID).redirectErrorStream(true).start();
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
				NaliAL.alSourcef(BGM_SOURCE, AL10.AL_GAIN, NaliConfig.FLOAT_ARRAY[1] * NaliConfig.FLOAT_ARRAY[0]);
				NaliAL.alSourcef(BGM_SOURCE, AL10.AL_PITCH, NaliConfig.FLOAT_ARRAY[6] * NaliConfig.FLOAT_ARRAY[0]);
				NaliAL.alSourcePlay(BGM_SOURCE);
			}
		}
		//e0-sound

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		PageLoad pageload = new PageLoad();
		pageload.init();
		Page.WIDTH = -1;
		pageload.render();
		pageload.clear();
		Display.update();

		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
		Page.QUAD2D_ARRAY_BUFFER = MemoA.genBuffer(MemoA.createFloatByteBuffer(new float[]
		{
			-1, 1,
			-1, -1,
			1, -1,

			-1, 1,
			1, -1,
			1, 1
		}));

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

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

		this.loadSound(file_2d_array[0], data_class_map_array[0]);
		for (int i = 1; i < max_ordinal; ++i)
		{
			this.load(file_2d_array[i], data_class_map_array[i], memohvo_array_map, memohfo_array_map);
			this.loadSound(file_2d_array[i], data_class_map_array[i]);
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

	public void load(File[] file_array, Map<String, Class> data_class_map, Map<String, MemoHVo[]> memohvo_array_map, Map<String, MemoHFo[]> memohfo_array_map)
	{
		List<MemoHVs> memohvs_list = new ArrayList();

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
				String path_string = file.getPath();
				String[][] model_string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());

				try
				{
					data_class_map.get(name).getField("MODEL_STEP").set(null, G_LIST.size());
				}
				catch (NoSuchFieldException | IllegalAccessException e)
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

					G_LIST.add(new MemoG(shader_string_2d_array, attriblocation_string_2d_array, shader_id, model_string_array, path_string));
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
				String path = file.getPath();
				String[][] frame_string_2d_array = FileDataReader.getMixXStringArray(frame_file.toPath());

				try
				{
					data_class_map.get(file.getName()).getField("FRAME_STEP").set(null, F2_LIST.size());
				}
				catch (NoSuchFieldException | IllegalAccessException e)
				{
					error(e);
				}

				int i = 0;
				for (String[] frame_string_array : frame_string_2d_array)
				{
					MemoHVs memohvs = memohvs_list.get(i++);
//					MemoF memof = new MemoF(memohvs.bone, frame_string_array, path);
//					MemoF2 memof2 = new MemoF2(memohvs.bone, memohvs.bind_pose_float_array, memohvs.back_bone_2d_int_array, frame_string_array, path);
					MemoF2 memof2 = new MemoF2(memohvs.bone, memohvs.bind_pose_float_array, memohvs.bone_2d_short_array, frame_string_array, path);
//					MemoF memof = new MemoF(memohvs.bone, memof2.transforms_float_array);
//						memof.bind_pose_float_array = memohvs.bind_pose_float_array;
//						memof.back_bone_2d_int_array = memohvs.back_bone_2d_int_array;

//					F_LIST.add(memof);
					F2_LIST.add(memof2);
				}
			}
		}
	}

	public void loadSound(File[] file_array, Map<String, Class> data_class_map)
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

//	public static void setRender()
//	{
//		preTexture(Reflect.getClasses("com.nali.list.render"));
////		preTexture(Reflect.getClasses("com.nali.list.render.s"));
//	}

	public static void preTexture(List<Class> render_class_list)
	{
		for (Class render_class : render_class_list)
		{
			try
			{
				render_class.getMethod("setTextureMap").invoke(null);
			}
			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
			}
		}
	}
}
