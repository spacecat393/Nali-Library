package com.nali.gui.box.text;

import com.nali.gui.box.BoxVT;
import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class BoxTextAll extends BoxVT
{
	public char[] char_array;
	public int
		array_buffer = -1,
		length;

	public BoxTextAll(char[] char_array)
	{
		this.char_array = char_array;
	}

	public void gen(float x, float y, float size, float space, float width, float height)
	{
		if (this.array_buffer != -1)
		{
			OpenGlHelper.glDeleteBuffers(this.array_buffer);
		}

		this.length = this.char_array.length;
		float[] float_array = new float[this.length * 24];

		int l = 0;

		float w = (int)(size * 0.2F);
		float x0 = 0;
		float x1 = space + size;
		if (x + size > w)
		{
			x0 = -w;
		}
		if (x1 > w)
		{
			x1 -= w;
		}

		this.v_width = width;
		this.v_height = height;

		for (int i = 0; i < this.char_array.length; ++i)
		{
			char c = this.char_array[i];
			if (c == ' ')
			{
				--this.length;
				x += space + size;
			}
			else if (c == '-')
			{
				this.x0 = x;
				this.y0 = y + size / 2.5F;
				this.x1 = x + size;
				this.y1 = y + size / 1.625F;

				this.u0 = 0;
				this.v0 = 1;
				this.u1 = 1;
				this.v1 = 2;

				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
				x += space + size;
			}
			else if (c == '.')
			{
				this.x0 = x + size / 2.5F;
				this.y0 = y;
				this.x1 = x + size / 1.625F;
				this.y1 = y + size / 5.0F;

				this.u0 = 0;
				this.v0 = 1;
				this.u1 = 1;
				this.v1 = 2;

				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
				x += space + size;
			}
			else if (/*c > 47 && */c < 58)
			{
				short s = (short)((c - 48) * 4 + 26 * 5);

				this.x0 = x;
				this.y0 = y;
				this.x1 = x + x0 + size;
				this.y1 = y + size;

				this.u0 = s;
				this.v0 = 0;
				this.u1 = s + 4;
				this.v1 = 5;

				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
				x += x1;
			}
			else if (/*c > 64 && */c < 91)
			{
				short s = (short)((c - 65) * 5);

				this.x0 = x;
				this.y0 = y;
				this.x1 = x + size;
				this.y1 = y + size;

				this.u0 = s;
				this.v0 = 0;
				this.u1 = s + 5;
				this.v1 = 5;

				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
				x += space + size;
			}
			else if (c == '_')
			{
				this.x0 = x;
				this.y0 = y;
				this.x1 = x + size;
				this.y1 = y + size / 5.0F;

				this.u0 = 0;
				this.v0 = 1;
				this.u1 = 1;
				this.v1 = 2;

				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
				x += space + size;
			}
			else if (c > 96/* && c < 123*/)
			{
				float new_size = size / 1.75F;
				float new_space = size / 1.75F;
				short s = (short)((c - 97) * 5);

				this.x0 = x;
				this.y0 = y;
				this.x1 = x + new_size;
				this.y1 = y + new_size;

				this.u0 = s;
				this.v0 = 0;
				this.u1 = s + 5;
				this.v1 = 5;

				System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
				x += new_space + new_size;
			}
		}
		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(float_array, this.length * 24));
		this.length *= 6;
	}

	public void draw(MemoS rs, float[] v_float_array, float[] c_float_array)
	{
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);

//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		RenderO.FLOATBUFFER.clear();
		RenderO.FLOATBUFFER.put(v_float_array);
		RenderO.FLOATBUFFER.flip();
		OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], RenderO.FLOATBUFFER);

		RenderO.FLOATBUFFER.clear();
		RenderO.FLOATBUFFER.put(c_float_array);
		RenderO.FLOATBUFFER.flip();
		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], RenderO.FLOATBUFFER);

//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.length);
	}

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
}
