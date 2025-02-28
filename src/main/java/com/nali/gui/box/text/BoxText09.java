package com.nali.gui.box.text;

import com.nali.gui.box.BoxV;
import com.nali.gui.box.BoxVT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxText09
{
	public final static byte B_IA_X = 0;
	public final static byte B_IA_Y = 1;
	public final static byte B_IA_SIZE = 2;
	public final static byte B_IA_SPACE = 3;
//	public char[] char_array;
//	public int array_buffer = -1;

	public static void set(char[] char_array)
	{
		for (int i = 0; i < char_array.length; ++i)
		{
			char_array[i] -= 48;
			char_array[i] *= 4;
		}
//		this.char_array = char_array;
	}

//	public void gen(int x, int y, int size, int space, int width, int height)
	public static void set(int[] int_array, float[] float_array, float[] w_float_array, char[] char_array, int index)
	{
//		this.clear();

		int
			x = int_array[B_IA_X],
			y = int_array[B_IA_Y],
			size = int_array[B_IA_SIZE],
			space = int_array[B_IA_SPACE];
//		int length = this.char_array.length;
//		float[] float_array = new float[length * 24];

		int w = (int)(size * 0.2F);
		int x0 = 0;
		int x1 = space + size;
		if (x + size > w)
		{
			x0 = -w;
		}
		if (x1 > w)
		{
			x1 -= w;
		}

//		this.v_width = width;
//		this.v_height = height;

//		for (int i = 0; i < length; ++i)
//		int l = 0;
		for (char c : char_array)
		{
//			char c = this.char_array[i];

			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = x + x0 + size;
			float_array[BoxV.B_FA_Y1] = y + size;

			float_array[BoxVT.B_FA_U0] = c + 26 * 5;
//			this.v0 = 0;
			float_array[BoxVT.B_FA_U1] = c + 26 * 5 + 4;
//			this.v1 = 5;

//			System.arraycopy(this.createQuad(), 0, float_array, i * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			index += 24;
			x += x1;
		}
//		return MemoA.genBuffer(MemoA.createFloatByteBuffer(w_float_array));
	}

//	public void draw(MemoS rs, float[] v_float_array, float[] c_float_array)
//	{
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
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
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
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
