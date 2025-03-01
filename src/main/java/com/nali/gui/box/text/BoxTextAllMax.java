package com.nali.gui.box.text;

import com.nali.gui.box.Box;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxTextAllMax
{
	public final static byte B_FA_NEW_LINE = 16;
//	public final static byte B_FAL = 1;

//	public char[] char_array;
//	public int
//		array_buffer = -1,
//		length;

//	public BoxTextAllMax(char[] char_array)
//	{
//		this.char_array = char_array;
//	}

//	public void gen(float x, float y, float size, float space, float new_line, float width, float height)
	public static void set(float[] float_array, float[] w_float_array, char[] char_array, int index)
	{
//		float x = float_array[BoxTextAll.B_FA_X];
//		float y = float_array[BoxTextAll.B_FA_Y];
		float size = float_array[BoxTextAll.B_FA_SIZE];
		float space = float_array[BoxTextAll.B_FA_SPACE];
//		this.clear();

//		this.length = this.char_array.length;
//		float[] float_array = new float[this.length * 24];

//		int l = 0;

		float ss = space + size;

//		int w = (int)(size * 0.2F);
//		int x0 = 0;
//		int x1 = ss;
//		if (x + size > w)
//		{
//			x0 = -w;
//		}
//		if (x1 > w)
//		{
//			x1 -= w;
//		}

//		this.v_width = width;
//		this.v_height = height;

//		for (int i = 0; i < this.char_array.length; ++i)
		for (char c : char_array)
		{
			//s0-newline
			if (float_array[BoxTextAll.B_FA_X] > Box.WIDTH - ss)
			{
				float_array[BoxTextAll.B_FA_X] = float_array[B_FA_NEW_LINE];
				float_array[BoxTextAll.B_FA_Y] -= ss;
			}
			//e0-newline

			BoxTextAll.set(float_array, w_float_array, c, index, false);
			index += 24;
////			char c = this.char_array[i];
//			if (c == ' ')
//			{
////				--this.length;
//			}
//			else if (c == '-')
//			{
//				float_array[BoxV.B_FA_X0] = x;
//				this.y0 = y + size / 2.5F;
//				this.x1 = x + size;
//				this.y1 = y + size / 1.625F;
//
//				this.u0 = 0;
//				this.v0 = 1;
//				this.u1 = 1;
//				this.v1 = 2;
//
//				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
//			}
//			else if (c == '.')
//			{
//				float_array[BoxV.B_FA_X0] = x + size / 2.5F;
//				this.y0 = y;
//				this.x1 = x + size / 1.625F;
//				this.y1 = y + size / 5.0F;
//
//				this.u0 = 0;
//				this.v0 = 1;
//				this.u1 = 1;
//				this.v1 = 2;
//
//				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
//			}
//			else if (c < 58)
//			{
//				short s = (short)((c - 48) * 4 + 26 * 5);
//
//				float_array[BoxV.B_FA_X0] = x;
//				this.y0 = y;
//				this.x1 = x/* + x0*/ + size;
//				this.y1 = y + size;
//
//				this.u0 = s;
//				this.v0 = 0;
//				this.u1 = s + 4;
//				this.v1 = 5;
//
//				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
//			}
//			else if (c < 91)
//			{
//				short s = (short)((c - 65) * 5);
//
//				float_array[BoxV.B_FA_X0] = x;
//				this.y0 = y;
//				this.x1 = x + size;
//				this.y1 = y + size;
//
//				this.u0 = s;
//				this.v0 = 0;
//				this.u1 = s + 5;
//				this.v1 = 5;
//
//				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
//			}
//			else if (c == '_')
//			{
//				float_array[BoxV.B_FA_X0] = x;
//				this.y0 = y;
//				this.x1 = x + size;
//				this.y1 = y + size / 5.0F;
//
//				this.u0 = 0;
//				this.v0 = 1;
//				this.u1 = 1;
//				this.v1 = 2;
//
//				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
//			}
//			else if (c > 96)
//			{
//				float new_size = size / 1.75F;
////				float new_space = size / 1.75F;
//				short s = (short)((c - 97) * 5);
//
//				float_array[BoxV.B_FA_X0] = x;
//				this.y0 = y;
//				this.x1 = x + new_size;
//				this.y1 = y + new_size;
//
//				this.u0 = s;
//				this.v0 = 0;
//				this.u1 = s + 5;
//				this.v1 = 5;
//
//				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
//			}
			//same space
			float_array[BoxTextAll.B_FA_X] += ss;
		}
//		return MemoA.genBuffer(MemoA.createFloatByteBuffer(float_array));
//		this.length *= 6;
	}

//	public void draw(MemoS rs, float[] v_float_array, float[] c_float_array)
//	{
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);
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
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.length);
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
