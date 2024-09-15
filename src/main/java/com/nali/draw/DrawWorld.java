package com.nali.draw;

import com.nali.Nali;
import com.nali.NaliGL;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.render.RenderO;
import com.nali.system.bytes.ByteReader;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.render.RenderO.setLightMapBuffer;
import static com.nali.render.RenderO.setTextureBuffer;
import static com.nali.system.ClientLoader.G_LIST;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Nali.ID, value = Side.CLIENT)
public class DrawWorld
{
	public static List<DrawWorldData> DRAWWORLDDATA_LIST = new ArrayList();
	public static int
	DATA_SIZE;

	public static Map<String, byte[]> KEY_MAP = new HashMap();
	public static Map<byte[], List<Integer>> E_MODEL_MAP = new HashMap();
	public static Map<byte[], List<Integer>> E_TRANSLUCENT_MAP = new HashMap();

	public static Map<byte[], List<Integer>> E_GLOW_MAP = new HashMap();

	public static Map<byte[], List<Integer>> T_MODEL_MAP = new HashMap();
	public static Map<byte[], List<Integer>> T_TRANSLUCENT_MAP = new HashMap();

//	public static byte INDEX;

	public static void add(byte[] byte_array)
	{
		List<Integer> index_integer_list;
		Map<byte[], List<Integer>> data_map;

		if ((byte_array[4 + 4 + 4] & 16) == 16)
		{
			if ((byte_array[4 + 4 + 4] & 1) == 1)
			{
				index_integer_list = T_TRANSLUCENT_MAP.get(byte_array);
				data_map = T_TRANSLUCENT_MAP;
			}
			else
			{
				index_integer_list = T_MODEL_MAP.get(byte_array);
				data_map = T_MODEL_MAP;
			}
		}
		else
		{
			if ((byte_array[4 + 4 + 4] & 8) == 8)
			{
				index_integer_list = E_GLOW_MAP.get(byte_array);
				data_map = E_GLOW_MAP;
//			switch (INDEX)
//			{
////				case 1:
////					break;
//				case 1:
//					index_integer_list = GLOW_MAP.get(byte_array);
//					break;
//				default:
//					index_integer_list = MODEL_MAP.get(byte_array);
//			}
			}
			else if ((byte_array[4 + 4 + 4] & 1) == 1)
			{
				index_integer_list = E_TRANSLUCENT_MAP.get(byte_array);
				data_map = E_TRANSLUCENT_MAP;
			}
			else
			{
				index_integer_list = E_MODEL_MAP.get(byte_array);
				data_map = E_MODEL_MAP;
			}
		}

		if (index_integer_list == null)
		{
			List list = new ArrayList();
			list.add(DATA_SIZE);
			data_map.put(byte_array, list);
		}
		else
		{
			index_integer_list.add(DATA_SIZE);
		}
	}

	public static void add(float[] float_array, FloatBuffer floatbuffer)
	{
		for (int i = 0; i < floatbuffer.limit(); ++i)
		{
			float_array[i] = floatbuffer.get(i);
		}
	}

	public static void runE()
	{
//		Minecraft minecraft = Minecraft.getMinecraft();
//		int display_width = minecraft.displayWidth,
//		display_height = minecraft.displayHeight;
//		if (display_width != DISPLAY_WIDTH || display_height != DISPLAY_HEIGHT)
//		{
//			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//			GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//			R_GL_RENDERBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//			DISPLAY_WIDTH = display_width;
//			DISPLAY_HEIGHT = display_height;
//
//			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, MC_FRAMEBUFFER);
//			OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, MC_RENDERBUFFER);
//
//			OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, DISPLAY_WIDTH, DISPLAY_HEIGHT);
//			OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, MC_RENDERBUFFER);
//
//			OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_GL_RENDERBUFFER_BINDING);
//			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//		}

		RenderO.takeDefault();

		if (!E_MODEL_MAP.isEmpty())
		{
			draw(E_MODEL_MAP);
		}
//		FIRST_MODEL_MAP.clear();

		if (!E_TRANSLUCENT_MAP.isEmpty())
		{
			GL11.glDepthMask(false);
//			GL11.glEnable(GL11.GL_CULL_FACE);
//			GL11.glDisable(GL11.GL_DEPTH_TEST);

//			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//			R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
//			R_GL_DEPTH_ATTACHMENT = OPENGL_INTBUFFER.get(0);
//
//			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, MC_FRAMEBUFFER);
//
//			GL30.glBlitFramebuffer
//			(
//				0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//				0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//				GL11.GL_DEPTH_BUFFER_BIT,
//				GL11.GL_NEAREST
//			);
//
//			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, MC_RENDERBUFFER, 0);
////			GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

			draw(E_TRANSLUCENT_MAP);
//			SECOND_MODEL_MAP.clear();
//
//			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//
//			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_GL_DEPTH_ATTACHMENT, 0);
		}

//		DRAWWORLDDATA_LIST.clear();
//		DATA_SIZE = 0;

		RenderO.setDefault();
	}

	public static void runEG()
	{
		if (!E_GLOW_MAP.isEmpty())
		{
			RenderO.takeDefault();
			draw(E_GLOW_MAP);
			RenderO.setDefault();
		}
	}

	public static void runT()
	{
		RenderO.takeDefault();

		if (!T_MODEL_MAP.isEmpty())
		{
			draw(T_MODEL_MAP);
		}

		if (!T_TRANSLUCENT_MAP.isEmpty())
		{
			GL11.glDepthMask(false);
			draw(T_TRANSLUCENT_MAP);
		}

		RenderO.setDefault();
	}

	public static void clear()
	{
		DATA_SIZE = 0;
		E_MODEL_MAP.clear();
		E_TRANSLUCENT_MAP.clear();
		E_GLOW_MAP.clear();
		T_MODEL_MAP.clear();
		T_TRANSLUCENT_MAP.clear();
		DRAWWORLDDATA_LIST.clear();
	}

	public static void draw(Map<byte[], List<Integer>> model_map)
	{
		byte[][] keyset_byte_2d_array = model_map.keySet().toArray(new byte[0][]);
		List<Integer>[] values_integer_list = model_map.values().toArray(new ArrayList[0]);

		for (int g = 0; g < keyset_byte_2d_array.length; ++g)
		{
			byte[] byte_array = keyset_byte_2d_array[g];
			List<Integer> index_integer_list = values_integer_list[g];
			MemoG rg = G_LIST.get(ByteReader.getInt(byte_array, 0));
			MemoS rs = S_LIST.get(ByteReader.getInt(byte_array, 4 + 4));

			RenderO.enableBuffer(rg, rs);

//			OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 2);
//			OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//			setTextureBuffer(Minecraft.getMinecraft().getFramebuffer().framebufferTexture, (byte)0);

			if ((byte_array[4 + 4 + 4] & 4) == 4)//color
			{
				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
				setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

				int color = ByteReader.getInt(byte_array, 4);
				OPENGL_FIXED_PIPE_FLOATBUFFER.limit(3);
				OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
				OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
				OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
				OPENGL_FIXED_PIPE_FLOATBUFFER.put((color & 0xFF) / 255.0F);
				OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
				OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4/*+1*/], OPENGL_FIXED_PIPE_FLOATBUFFER);
			}
			else
			{
				OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5/*+1*/], 1);
				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
				setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

//				OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4/*+1*/], 0);
				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
				setTextureBuffer(ByteReader.getInt(byte_array, 4), (byte)(rg.state & 1));
			}

			for (Integer integer : index_integer_list)
			{
				DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(integer);

//				boolean transparent = (byte_array[4 + 4 + 4] & 1) == 1;
//				RenderO.setTransparentStart(transparent);

				OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.projection_m4x4_float, 16);
				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.modelview_m4x4_float, 16);
				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
				OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.color_v4_float, 4);
				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[3/*+1*/], OPENGL_FIXED_PIPE_FLOATBUFFER);
				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.light0position_v4_float, 4);
				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2/*+1*/], OPENGL_FIXED_PIPE_FLOATBUFFER);

				if ((rg.state & 8) == 8)
				{
					OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
					OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
					OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
					OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
					OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
					OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6/*+1*/], OPENGL_FIXED_PIPE_FLOATBUFFER);
				}
				else
				{
					OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
					OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
					OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_b);
					OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_s);
					OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
					OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6/*+1*/], OPENGL_FIXED_PIPE_FLOATBUFFER);
				}

//				if (drawworlddata.skinning_float_array != null)
				if ((byte_array[4 + 4 + 4] & 2) == 2)
				{
					setFloatBuffer(drawworlddata.skinning_float_array);
					OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7/*+1*/], false, OPENGL_FLOATBUFFER);
				}
//				OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, rg.ebo);
//				int buffer = OpenGlHelper.glGenBuffers();
//				GL30.glBindBufferBase(GL30.GL_TRANSFORM_FEEDBACK_BUFFER, 0, transformFeedbackBufferID);
//				GL30.glBeginTransformFeedback(GL11.GL_TRIANGLES);
//				GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
				if (Nali.VAO)
				{
					NaliGL.glDrawElementsTUi0(rg.index_length);
				}
				else
				{
					GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
				}
//				GL30.glEndTransformFeedback();

				//read
				//glBindBuffer(GL_TRANSFORM_FEEDBACK_BUFFER, transformFeedbackBufferID);
				//ByteBuffer buffer = glMapBuffer(GL_TRANSFORM_FEEDBACK_BUFFER, GL_READ_ONLY);

				//done
				//glUnmapBuffer(GL_TRANSFORM_FEEDBACK_BUFFER);
				//glBindBuffer(GL_TRANSFORM_FEEDBACK_BUFFER, 0);
			}

			if (!Nali.VAO)
			{
				RenderO.disableBuffer(rs);
			}
		}
	}
}
