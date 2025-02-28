package com.nali.gui.box.text;

import com.nali.gui.box.BoxV;
import com.nali.gui.box.BoxVT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxTextAZ
{
	public final static byte B_IA_X = 0;
	public final static byte B_IA_Y = 1;
	public final static byte B_IA_SIZE = 2;
	public final static byte B_IA_SPACE = 3;
//	public final static byte B_IA_WIDTH = 4;
//	public final static byte B_IA_HEIGHT = 5;
//	public char[] char_array;
//	public int array_buffer = -1;

	public static void set(char[] char_array)
	{
		for (int i = 0; i < char_array.length; ++i)
		{
			char_array[i] -= 65;
			char_array[i] *= 5;
		}
//
//		this.char_array = char_array;
	}

//	public static int gen(int x, int y, int size, int space, int width, int height)
	public static void set(int[] int_array, float[] float_array, float[] w_float_array, char[] char_array, int index)
	{
//		this.clear();

		int
			x = int_array[B_IA_X],
			y = int_array[B_IA_Y],
			size = int_array[B_IA_SIZE],
			space = int_array[B_IA_SPACE];
//			width = int_array[B_IA_WIDTH],
//			height = int_array[B_IA_HEIGHT];

//		int length = char_array.length;
//		float[] new_float_array = new float[length * 24];
//		int l = 0;

//		float_array[BoxV.B_FA_V_WIDTH] = width;
//		float_array[BoxV.B_FA_V_HEIGHT] = height;

		for (char c : char_array)
		{
			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = x + size;
			float_array[BoxV.B_FA_Y1] = y + size;

			float_array[BoxVT.B_FA_U0] = c;
//			this.v0 = 0;
			float_array[BoxVT.B_FA_U1] = c + 5;
//			this.v1 = 5;

			BoxVT.set(float_array, w_float_array, index);
			index += 24;
//			System.arraycopy(, 0, new_float_array, l++ * 24, 24);
			x += space + size;
		}
//		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(new_float_array));
//		return MemoA.genBuffer(MemoA.createFloatByteBuffer(w_float_array));
	}

//	public static void draw(MemoS rs, float[] v_float_array, float[] c_float_array, int array_buffer, int count)
//	{
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);
//
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(v_float_array);
//		RenderO.FLOATBUFFER.flip();
//		OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], RenderO.FLOATBUFFER);
//
//		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(c_float_array);
//		RenderO.FLOATBUFFER.flip();
//		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], RenderO.FLOATBUFFER);
//
////		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, count);
//	}

//	public void draw()
//	{
//		//take
//		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		NaliData.drawQuad
//		(
//			rs,
//			new float[2],
//			new float[]{1.0F, 1.0F, 1.0F, 1.0F},
//			NaliData.QUAD2D_ARRAY_BUFFER,
//			ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP)
//		);
//
//		GL20.glDisableVertexAttribArray(v);
//		//set
//	}

//	public void clear()
//	{
//		if (this.array_buffer != -1)
//		{
//			OpenGlHelper.glDeleteBuffers(this.array_buffer);
//			this.array_buffer = -1;
//		}
//	}
}
