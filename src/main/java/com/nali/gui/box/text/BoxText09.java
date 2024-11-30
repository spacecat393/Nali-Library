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
public class BoxText09 extends BoxVT
{
	public char[] char_array;
	public int array_buffer = -1;

	public BoxText09(char[] char_array)
	{
		for (int i = 0; i < char_array.length; ++i)
		{
			char_array[i] -= 48;
			char_array[i] *= 4;
		}

		this.char_array = char_array;
	}

	public void gen(int x, int y, int size, int space, int width, int height)
	{
		if (this.array_buffer != -1)
		{
			OpenGlHelper.glDeleteBuffers(this.array_buffer);
		}

		int length = this.char_array.length;
		float[] float_array = new float[length * 24];

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

		this.v_width = width;
		this.v_height = height;

		for (int i = 0; i < length; ++i)
		{
			char c = this.char_array[i];

			this.x0 = x;
			this.y0 = y;
			this.x1 = x + x0 + size;
			this.y1 = y + size;

			this.u0 = c + 26 * 5;
//			this.v0 = 0;
			this.u1 = c + 26 * 5 + 4;
//			this.v1 = 5;

			System.arraycopy(this.createQuad(), 0, float_array, i * 24, 24);
			x += x1;
		}

		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(float_array));
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

		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
	}
}
