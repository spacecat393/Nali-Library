package com.nali.draw;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.render.RenderO;
import com.nali.render.RenderS;
import com.nali.system.bytes.ByteReader;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;
import static com.nali.system.ClientLoader.S_LIST;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Nali.ID, value = Side.CLIENT)
public class DrawWorld
{
	public static List<DrawWorldData> DRAWWORLDDATA_LIST = new ArrayList();
	public static int DATA_SIZE;

	public static Map<String, byte[]> KEY_MAP = new HashMap();
	public static Map<byte[], List<Integer>> E_MODEL_MAP = new HashMap();
	public static Map<byte[], List<Integer>> E_TRANSLUCENT_MAP = new HashMap();

	public static Map<byte[], List<Integer>> E_GLOW_MAP = new HashMap();

	public static Map<byte[], List<Integer>> T_MODEL_MAP = new HashMap();
	public static Map<byte[], List<Integer>> T_TRANSLUCENT_MAP = new HashMap();

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
		RenderO.take();

		if (!E_MODEL_MAP.isEmpty())
		{
			draw(E_MODEL_MAP);
		}

		//model texture
		if (!E_GLOW_MAP.isEmpty())
		{
			draw(E_GLOW_MAP);
		}

		if (!E_TRANSLUCENT_MAP.isEmpty())
		{
			GL11.glDepthMask(false);
			draw(E_TRANSLUCENT_MAP);
		}

		RenderO.free();
	}

	public static void runEG()
	{
		//outline
		if (!E_GLOW_MAP.isEmpty())
		{
			RenderO.take();
			draw(E_GLOW_MAP);
			RenderO.free();
		}
	}

	public static void runT()
	{
		RenderO.take();

		if (!T_MODEL_MAP.isEmpty())
		{
			draw(T_MODEL_MAP);
		}

		if (!T_TRANSLUCENT_MAP.isEmpty())
		{
			GL11.glDepthMask(false);
			draw(T_TRANSLUCENT_MAP);
		}

		RenderO.free();
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
//			Nali.warn("rg " + ByteReader.getInt(byte_array, 0));
			MemoG rg = G_LIST.get(ByteReader.getInt(byte_array, 0));
			MemoS rs = S_LIST.get(ByteReader.getInt(byte_array, 4 + 4));

			RenderO.enableBuffer(rg, rs);

			OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 1);
			if ((byte_array[4 + 4 + 4] & 4) == 4)//color
			{
				int color = ByteReader.getInt(byte_array, 4);
				RenderO.FLOATBUFFER.clear();
				RenderO.FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
				RenderO.FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
				RenderO.FLOATBUFFER.put((color & 0xFF) / 255.0F);
				RenderO.FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
				RenderO.FLOATBUFFER.flip();
				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[5], RenderO.FLOATBUFFER);
			}
			else
			{
//				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//				Nali.warn("texture " + ByteReader.getInt(byte_array, 4));
//				Nali.warn("texture_state " + (rg.state & 1));
				RenderO.setTextureBuffer(ByteReader.getInt(byte_array, 4), (byte)(rg.flag & 1+2));
			}

			for (Integer integer : index_integer_list)
			{
				DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(integer);
//				Nali.warn("g" + g + " i" + integer);

				RenderO.FLOATBUFFER.limit(16);
				MemoA.put(RenderO.FLOATBUFFER, drawworlddata.projection_m4x4_float, 16);
				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, RenderO.FLOATBUFFER);
				MemoA.put(RenderO.FLOATBUFFER, drawworlddata.modelview_m4x4_float, 16);
				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, RenderO.FLOATBUFFER);
				RenderO.FLOATBUFFER.limit(4);
				MemoA.put(RenderO.FLOATBUFFER, drawworlddata.color_v4_float, 4);
				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], RenderO.FLOATBUFFER);
//				MemoA.put(RenderO.FLOATBUFFER, drawworlddata.light0position_v4_float, 4);
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], RenderO.FLOATBUFFER);

				RenderO.FLOATBUFFER.clear();
				if ((rg.flag & 16) == 16)
				{
					RenderO.FLOATBUFFER.put(-1.0F);
					RenderO.FLOATBUFFER.put(-1.0F);
				}
				else
				{
					RenderO.FLOATBUFFER.put(drawworlddata.light_b);
					RenderO.FLOATBUFFER.put(drawworlddata.light_s);
				}
				RenderO.FLOATBUFFER.flip();
				OpenGlHelper.glUniform2(rs.uniformlocation_int_array[3], RenderO.FLOATBUFFER);

				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, drawworlddata.light_b, drawworlddata.light_s);

				if ((byte_array[4 + 4 + 4] & 2) == 2)
				{
					RenderS.setFloatBuffer(drawworlddata.skinning_float_array);
					OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[6], false, RenderS.BONE_FLOATBUFFER);
				}

				if (NaliConfig.VAO)
				{
					NaliGL.glDrawElementsTUi0(rg.index_length);
				}
				else
				{
					GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
				}
			}

			if (!NaliConfig.VAO)
			{
				RenderO.disableBuffer(rs);
			}
		}
	}
}
