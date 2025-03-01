package com.nali.gui.box.text;

import com.nali.gui.box.BoxV;
import com.nali.gui.box.BoxVT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxTextAll
{
	public final static byte B_FA_X = 12;
	public final static byte B_FA_Y = 13;
	public final static byte B_FA_SIZE = 14;
	public final static byte B_FA_SPACE = 15;

	public final static byte B_FA_X0 = 16;
	public final static byte B_FA_X1 = 17;
	public final static byte B_FAL = 6;
//	public char[] char_array;
//	public int
//		array_buffer = -1,
//		length;

//	public BoxTextAll(char[] char_array)
//	{
//		this.char_array = char_array;
//	}

//	public void gen(float x, float y, float size, float space, float width, float height)
	public static void set(float[] float_array, float[] w_float_array, char[] char_array, int index)
	{
//		this.clear();

		float x = float_array[B_FA_X];
//		float y = float_array[B_FA_Y];
		float size = float_array[B_FA_SIZE];
		float space = float_array[B_FA_SPACE];

//		this.length = this.char_array.length;
//		float[] float_array = new float[this.length * 24];

//		int l = 0;

		float w = (int)(size * 0.2F);
		float_array[B_FA_X0] = 0;
		float_array[B_FA_X1] = space + size;
		if (x + size > w)
		{
			float_array[B_FA_X0] = -w;
		}
		if (float_array[B_FA_X1] > w)
		{
			float_array[B_FA_X1] -= w;
		}

//		this.v_width = width;
//		this.v_height = height;

//		for (int i = 0; i < this.char_array.length; ++i)

		for (char c : char_array)
		{
			set(float_array, w_float_array, c, index, true);
			index += 24;
		}
//		return MemoA.genBuffer(MemoA.createFloatByteBuffer(w_float_array));
//		this.length *= 6;
	}

	public static void set(float[] float_array, float[] w_float_array, char c, int index, boolean jump)
	{
		float x = float_array[B_FA_X];
		float y = float_array[B_FA_Y];
		float size = float_array[B_FA_SIZE];
		float space = float_array[B_FA_SPACE];

//			char c = this.char_array[i];
		if (c == ' ')
		{
//				--this.length;
			if (jump)
			{
				float_array[B_FA_X] += space + size;
			}
		}
		else if (c == '-')
		{
			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y + size / 2.5F;
			float_array[BoxV.B_FA_X1] = x + size;
			float_array[BoxV.B_FA_Y1] = y + size / 1.625F;

			float_array[BoxVT.B_FA_U0] = 0;
			float_array[BoxVT.B_FA_V0] = 1;
			float_array[BoxVT.B_FA_U1] = 1;
			float_array[BoxVT.B_FA_V1] = 2;

//			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			if (jump)
			{
				float_array[B_FA_X] += space + size;
			}
		}
		else if (c == '.')
		{
			float_array[BoxV.B_FA_X0] = x + size / 2.5F;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = x + size / 1.625F;
			float_array[BoxV.B_FA_Y1] = y + size / 5.0F;

			float_array[BoxVT.B_FA_U0] = 0;
			float_array[BoxVT.B_FA_V0] = 1;
			float_array[BoxVT.B_FA_U1] = 1;
			float_array[BoxVT.B_FA_V1] = 2;

//			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			if (jump)
			{
				float_array[B_FA_X] += space + size;
			}
		}
		else if (/*c > 47 && */c < 58)
		{
			short s = (short)((c - 48) * 4 + 26 * 5);

			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = jump ? x + float_array[B_FA_X0] + size : x + size;
			float_array[BoxV.B_FA_Y1] = y + size;

			float_array[BoxVT.B_FA_U0] = s;
			float_array[BoxVT.B_FA_V0] = 0;
			float_array[BoxVT.B_FA_U1] = s + 4;
			float_array[BoxVT.B_FA_V1] = 5;

//			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			if (jump)
			{
				float_array[B_FA_X] += float_array[B_FA_X1];
			}
		}
		else if (/*c > 64 && */c < 91)
		{
			short s = (short)((c - 65) * 5);

			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = x + size;
			float_array[BoxV.B_FA_Y1] = y + size;

			float_array[BoxVT.B_FA_U0] = s;
			float_array[BoxVT.B_FA_V0] = 0;
			float_array[BoxVT.B_FA_U1] = s + 5;
			float_array[BoxVT.B_FA_V1] = 5;

//			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			if (jump)
			{
				float_array[B_FA_X] += space + size;
			}
		}
		else if (c == '_')
		{
			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = x + size;
			float_array[BoxV.B_FA_Y1] = y + size / 5.0F;

			float_array[BoxVT.B_FA_U0] = 0;
			float_array[BoxVT.B_FA_V0] = 1;
			float_array[BoxVT.B_FA_U1] = 1;
			float_array[BoxVT.B_FA_V1] = 2;

//			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			if (jump)
			{
				float_array[B_FA_X] += space + size;
			}
		}
		else if (c > 96/* && c < 123*/)
		{
			float new_size = size / 1.75F;
			short s = (short)((c - 97) * 5);

			float_array[BoxV.B_FA_X0] = x;
			float_array[BoxV.B_FA_Y0] = y;
			float_array[BoxV.B_FA_X1] = x + new_size;
			float_array[BoxV.B_FA_Y1] = y + new_size;

			float_array[BoxVT.B_FA_U0] = s;
			float_array[BoxVT.B_FA_V0] = 0;
			float_array[BoxVT.B_FA_U1] = s + 5;
			float_array[BoxVT.B_FA_V1] = 5;

//			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			BoxVT.set(float_array, w_float_array, index);
			if (jump)
			{
				float new_space = size / 1.75F;
				float_array[B_FA_X] += new_space + new_size;
			}
		}
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
////		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.length);
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
