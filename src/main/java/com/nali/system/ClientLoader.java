package com.nali.system;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.box.BoxTextAZ;
import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.render.RenderS;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memo.client.MemoN;
import com.nali.system.opengl.memo.client.*;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
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

	public static List<Integer> TEXTURE_INTEGER_LIST = new ArrayList();
	public static List<Integer> SOUND_INTEGER_LIST = new ArrayList();
	public static List<MemoG> G_LIST = new ArrayList();//graphic
	public static List<MemoS> S_LIST = new ArrayList();//shader
	public static List<MemoF> F_LIST = new ArrayList();//frame
	public static Map<Integer, MemoA2> A2_MAP = new HashMap();//3d skinning cpu

	public static void loadPreInit()
	{
		List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");

		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		for (Class data_class : data_class_list)
		{
			try
			{
				Method method = data_class.getMethod("run");
				if (method != null)
				{
					method.invoke(null);
				}
			}
			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
				error(e);
			}
		}

//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

		File[] file_array = new File(ID).listFiles();

		if (file_array != null)
		{
			if (Nali.VAO)
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
				File shader_file = new File(file + "/shader.dat");
				if (shader_file.isFile())
				{
					String name = file.getName();
					shader_name_string_list.add(name);
					String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());
					shader_string_2d_array_list.add(shader_string_2d_array);
					shader_string_2d_array_map.put(name, shader_string_2d_array);
				}

				File model_file = new File(file + "/model.dat");
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
			File config_file = new File("nali/nali/tmp/config.bin");

			try
			{
				byte[] byte_array = Files.readAllBytes(config_file.toPath());
			}
			catch (Exception e)
			{
				//s0-draw
				GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
				int gl_current_program = RenderO.INTBUFFER.get(0);
				GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
				int gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
				int gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
				int gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

				MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 3);
				OpenGlHelper.glUseProgram(rs.program);
				int v = rs.attriblocation_int_array[0];
				GL20.glEnableVertexAttribArray(v);

				BoxTextAZ boxtextaz0 = new BoxTextAZ("NALICRAFT".toCharArray());
				BoxTextAZ boxtextaz1 = new BoxTextAZ("IS".toCharArray());
				BoxTextAZ boxtextaz2 = new BoxTextAZ("LOADING".toCharArray());

				byte[] config_byte_array = null;
				boolean loop = true;
				int tmp_width = -1, tmp_height = -1;
				while (loop)
				{
					int width = Display.getWidth();
					int height = Display.getHeight();

					if (tmp_width != width || tmp_height != height)
					{
						//854 x 480
						//edit shader and 09
						int w20 = (int)(0.0234192037470726F * width);
						int w10 = (int)(0.011709602F * width);
						int w5 = (int)(0.005854801F * width);
						int h20 = (int)(0.041666668F * height);
						int h10 = (int)(0.020833334F * height);
						int h5 = (int)(0.010416667F * height);
						int wh20 = Math.min(w20, h20);
						int wh10 = Math.min(w10, h10);
						int wh5 = Math.min(w5, h5);
						boxtextaz0.gen(wh20, wh20, wh20, wh10, width, height);
						boxtextaz1.gen(wh20 + (/*5 + */wh20 + wh10) * 9, wh20, wh10, wh5, width, height);
						boxtextaz2.gen(wh20 + (/*5 + */wh20 + wh10) * 9 + (/*5 + */wh10 + wh5) * 2 + wh20, wh20, wh10, wh5, width, height);
						GL11.glViewport(0, 0, width, height);
						tmp_width = width;
						tmp_height = height;
					}

					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
					boxtextaz0.draw(rs);
					boxtextaz1.draw(rs);
					boxtextaz2.draw(rs);
					Display.update();

					while (Keyboard.next())
					{
						int key = Keyboard.getEventKey();
//						int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
//						warn("KEY: " + key);
//						warn("I: " + i);
						//SHIFT 42
						//ESC 1
						//left 203
						//up 200
						//down 208
						//right 205
						//enter 28
						//space 57
						if (key == Keyboard.KEY_Q)
						{
//							config_byte_array = new byte[];
							loop = false;
						}
					}
				}

				GL20.glDisableVertexAttribArray(v);

				OpenGlHelper.glUseProgram(gl_current_program);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_0);
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_0);

				OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

				OpenGlHelper.glDeleteBuffers(boxtextaz0.array_buffer);
				OpenGlHelper.glDeleteBuffers(boxtextaz1.array_buffer);
				OpenGlHelper.glDeleteBuffers(boxtextaz2.array_buffer);
				//e0-draw

				try
				{
					Files.write(config_file.toPath(), config_byte_array);
				}
				catch (IOException ex)
				{
					error(ex);
				}
			}
			//e0-config

//			//s0-draw
//			GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
//			int gl_current_program = RenderO.INTBUFFER.get(0);
//			GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
//			int gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
////			int gl_texture_wrap_s_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////			int gl_texture_wrap_t_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//			int gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//			int gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
//			MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 3);
//			OpenGlHelper.glUseProgram(rs.program);
//			int v = rs.attriblocation_int_array[0];
//			GL20.glEnableVertexAttribArray(v);
//
//			int width = Display.getWidth();
//			int height = Display.getHeight();
//			BoxTextAZ boxtextaz0 = new BoxTextAZ("NALICRAFT".toCharArray(), 20, width, height);
//			BoxTextAZ boxtextaz1 = new BoxTextAZ("IS".toCharArray(), 10, width, height);
//			BoxTextAZ boxtextaz2 = new BoxTextAZ("LOADING".toCharArray(), 10, width, height);
//			boxtextaz0.draw(rs, 20.0F / width, 20.0F / height, width, 10);
//			boxtextaz1.draw(rs, ((5.0F + 20.0F + 10.0F) * 9.0F * 2.0F + 20.0F) / width, 20.0F / height, width, 5);
//			boxtextaz2.draw(rs, ((5.0F + 20.0F + 10.0F) * (9.0F + 2.0F) * 2.0F + 20.0F) / width, 20.0F / height, width, 5);
//
////			BoxTextAZ boxtextaz0 = new BoxTextAZ("NALICRAFT".toCharArray(), (int)(width * 0.02F / 2.0F), width, height);
////			BoxTextAZ boxtextaz1 = new BoxTextAZ("IS".toCharArray(), (int)(width * 0.02F * 0.5F / 2.0F), width, height);
////			BoxTextAZ boxtextaz2 = new BoxTextAZ("LOADING".toCharArray(), (int)(width * 0.02F * 0.5F / 2.0F), width, height);
////			boxtextaz0.draw(rs, 0.01F, 0.01F, width, (int)(width * 0.02F * 0.5F / 2.0F));
////			boxtextaz1.draw(rs, ((0.02F + 0.02F / 2.0F) * 9.0F * 2.0F + 0.01F), 0.01F, width, (int)(width * 0.02F * 0.25F / 2.0F));// + 5.0F / width
////			boxtextaz2.draw(rs, ((0.02F + 0.02F / 2.0F) * (9.0F + 2.0F) * 2.0F + 0.01F), 0.01F, width, (int)(width * 0.02F * 0.25F / 2.0F));
//
////			BoxText09 boxtext09 = new BoxText09("0942745".toCharArray());
////			boxtext09.draw(rs);
//
//			GL20.glDisableVertexAttribArray(v);
//
//			OpenGlHelper.glUseProgram(gl_current_program);
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, gl_texture_wrap_s_0);
////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, gl_texture_wrap_t_0);
//			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_0);
//			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_0);
//
//			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//
//			OpenGlHelper.glDeleteBuffers(boxtextaz0.array_buffer);
//			OpenGlHelper.glDeleteBuffers(boxtextaz1.array_buffer);
//			OpenGlHelper.glDeleteBuffers(boxtextaz2.array_buffer);
//
//			Display.update();
//			//e0-draw

//			try
//			{
//				Thread.sleep(100000);
//			}
//			catch (InterruptedException e)
//			{
//				error(e);
//			}

			if (second_file_array.length != 0)
			{
				load(second_file_array, second_data_class_map, object_array);
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
			File[] texture_file_array = new File(file + "/texture").listFiles();
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
					String[][] texture_string_2d_array = FileDataReader.getMixXStringArray(new File(file + "/texture.dat").toPath());
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
			File[] vert_file_array = new File(file + "/shader/" + NaliConfig.GL_SHADING_LANGUAGE_VERSION_STRING + "/vert/o").listFiles();
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
			File[] frag_file_array = new File(file + "/shader/" + NaliConfig.GL_SHADING_LANGUAGE_VERSION_STRING + "/frag").listFiles();
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
			if (Nali.VAO)
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

			if (Nali.VAO)
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
			File model_file = new File(file + "/frame.dat");
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

	public static void loadInit()
	{
		if (isCommandLive("ffmpeg"))
		{
			File[] file_array = new File(ID).listFiles();

			if (file_array == null)
			{
				return;
			}

			Map<String, Class> data_class_map = new HashMap();
			List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
			for (Class data_class : data_class_list)
			{
				data_class_map.put(StringReader.get(data_class)[1], data_class);
			}

			for (File file : file_array)
			{
				File[] sound_file_array = new File(file + "/sound").listFiles();
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
							SOUND_INTEGER_LIST.set(index + step, MemoN.gen(sound_file));
						}
					}
					catch (IllegalAccessException | NoSuchFieldException e)
					{
						error(e);
					}
				}
			}
		}

		if ((NaliConfig.STATE & 1) == 1)
		{
			for (Class render_class : Reflect.getClasses("com.nali.list.render.s"))
			{
				try
				{
					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAS").get(null), render_class.getField("IBOTHDASN").get(null))).draw();
				}
				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
				{
					error(e);
				}
			}

			for (Class render_class : Reflect.getClasses("com.nali.list.render.o"))
			{
				try
				{
					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAO").get(null))).draw();
				}
				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
				{
					error(e);
				}
			}
		}

//		this.data_class_list = null;
//		this.data_string_list = null;
//		this.data_class_map = null;
	}

//	public static void setModels(DataLoader dataloader, String mod_id_string)
//	{
//		mod_id_string = ID + '/' + mod_id_string;
//		DataLoader.check(mod_id_string);
//
//		String folder_path = mod_id_string + '/';
//
//		dataloader.opengltexturememory = new OpenGLTextureMemory(folder_path);
//
//		String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "ShaderList");
//		int length = shader_string_2d_array.length;
//		dataloader.openglobjectshadermemory_array = new OpenGLObjectShaderMemory[length];
//
//		for (int i = 0; i < length; ++i)
//		{
//			String[] string_array = shader_string_2d_array[i];
//			switch (string_array.length)
//			{
//				case 2:
//					dataloader.openglobjectshadermemory_array[i] = new OpenGLObjectShaderMemory(string_array, folder_path);
//					break;
//				case 3:
//					dataloader.openglobjectshadermemory_array[i] = new OpenGLSkinningShaderMemory(string_array, folder_path);
//					break;
//				default:
//					error("SHADER_LIST " + i);
//			}
//		}
//
//		Object[] object_array = FileDataReader.getMixXStringArray(folder_path + "ModelList");
//		length = object_array.length;
//		dataloader.object_array = new Object[length];
//
//		for (int i = 0; i < length; ++i)
//		{
//			String[] string_array = (String[])object_array[i];
//			switch (string_array.length)
//			{
//				case 1:
////					dataloader.openglanimationmemory_list.add(new OpenGLAnimationMemory(string_array, folder_path));
//					dataloader.object_array[i] = new OpenGLAnimationMemory(string_array, folder_path);
//					break;
//				case 7:
//					dataloader.object_array[i] = new OpenGLObjectMemory(string_array, folder_path, shader_string_2d_array);
//					break;
//				case 8:
//					dataloader.object_array[i] = new OpenGLSkinningMemory(string_array, folder_path, shader_string_2d_array);
//					break;
//				default:
//					error("MODEL_LIST " + i);
//			}
//		}
//
//		if (MyConfig.SHADER.pre_shader)
//		{
//			for (int i = 0; i < length; ++i)
//			{
//				if (dataloader.object_array[i] instanceof OpenGLObjectMemory)
//				{
//					new ObjectRender(null, new FastClientData(i, i + 1), dataloader)
//					{
//						@Override
//						public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
//						{
//						}
//					}.draw();
//				}
//			}
//		}
//
////		for (int i = 0; i < model_length; ++i)
////		{
////			if (dataloader.memory_object_array[i] instanceof OpenGLObjectMemory)
////			{
////				OpenGLObjectMemory openglobjectmemorydata = (OpenGLObjectMemory)dataloader.memory_object_array[i];
////				int shader_id = (int)openglobjectmemorydata.shader;
////				openglobjectmemorydata.shader = dataloader.openglobjectshadermemory_array[shader_id];
////
////				if (MyConfig.SHADER.pre_shader)
////				{
////					new ObjectRender(new GuiObjectData(i, 1), dataloader, new Object[]{dataloader.memory_object_array[i]})
////					{
////						@Override
////						public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
////						{
////						}
////					}.objectscreendraw.renderScreen();
////				}
////			}
////		}
//
//		DATALOADER_LIST.add(dataloader);
//		dataloader.index = MAX++;
//	}

//	public static void setSounds(DataLoader dataloader, String mod_id_string)
//	{
//		if (MyConfig.SOUND.ffmpeg)
//		{
//			mod_id_string = ID + '/' + mod_id_string;
////		DataLoader.check(mod_id_string);
//
//			String folder_path = mod_id_string + '/';
////		if (new File(folder_path + "Sounds").isDirectory())
////		{
//			dataloader.openalmemory = new OpenALMemory(folder_path);
////		}
//		}
//	}

//	public static void check(String mod_id_string)
//	{
//		File file = new File(mod_id_string);
//		if (!file.isDirectory())
//		{
//			error('\"' + file.getPath() + "\" NOT_FOUND");
//		}
//	}

//	public static void setMemory()
//	{
//		List<Class> RENDER_CLASS_LIST = Reflect.getClasses("com.nali.list.render");
//		RENDER_CLASS_LIST.sort(Comparator.comparing(Class::getName));
//		int size = RENDER_CLASS_LIST.size();
//		MIX_MEMORY_OBJECT_ARRAY = new Object[size][];
//
//		int index = 0;
//		for (int i = 0; i < size; ++i)
//		{
//			try
//			{
//				Class clasz = RENDER_CLASS_LIST.get(i);
//				DataLoader dataloader = (DataLoader)clasz.getField("DATALOADER").get(null);
//				BothData bothdata = (BothData)clasz.getField("BOTHDATA").get(null);
//				clasz.getField("ID").set(null, index++);
//				int max_part = bothdata.MaxPart();
//				MIX_MEMORY_OBJECT_ARRAY[i] = new Object[max_part];
//				System.arraycopy(dataloader.memory_object_array, bothdata.StepModels(), MIX_MEMORY_OBJECT_ARRAY[i], 0, max_part);
//			}
//			catch (NoSuchFieldException | IllegalAccessException e)
//			{
//				Nali.error(e);
//			}
//		}
//	}

//	public int genTexture(File file)
//	{
//		try
//		{
//			BufferedImage bufferedimage = ImageIO.read(file);
//
//			int width = bufferedimage.getWidth();
//			int height = bufferedimage.getHeight();
//
//			ByteBuffer bytebuffer = ByteBuffer.allocateDirect(width * height * 4);
//			int[] pixels = new int[width * height];
//			bufferedimage.getRGB(0, 0, width, height, pixels, 0, width);
//			int[] new_pixels = new int[width * height];
//
//			for (int h = 0; h < height; ++h)
//			{
////				if (width >= 0)
////				{
//				System.arraycopy(pixels, h * width, new_pixels, (height - 1 - h) * width, width);
////				}
//			}
//
//			for (int pixel : new_pixels)
//			{
//				bytebuffer.put((byte)((pixel >> 16) & 0xFF));
//				bytebuffer.put((byte)((pixel >> 8) & 0xFF));
//				bytebuffer.put((byte)(pixel & 0xFF));
//				bytebuffer.put((byte)((pixel >> 24) & 0xFF));
//			}
//
//			bytebuffer.flip();
//
//			int texture_buffer = GL11.glGenTextures();
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture_buffer);
//			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bytebuffer);
//
//			return texture_buffer;
//		}
//		catch (IOException e)
//		{
//			I.error(e);
//		}
//
//		return -1;
//	}

	public static boolean isCommandLive(String command_string)
	{
		try
		{
			get(command_string).redirectErrorStream(true).start().destroy();
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
	}

	public static ProcessBuilder get(String... command_string)
	{
		ProcessBuilder processbuilder;
		String os_name = System.getProperty("os.name").toLowerCase();
		if (os_name.contains("win"))
		{
			String[] windows_command = new String[command_string.length + 2];
			windows_command[0] = "cmd";
			windows_command[1] = "/c";
			System.arraycopy(command_string, 0, windows_command, 2, command_string.length);

			processbuilder = new ProcessBuilder(windows_command);
		}
		else
		{
			processbuilder = new ProcessBuilder(command_string);
		}

		return processbuilder;
	}
}
