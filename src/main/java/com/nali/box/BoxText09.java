package com.nali.box;

import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoA1;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class BoxText09
{
	public char[] char_array;
	public int array_buffer;
	public int size;

	public BoxText09(char[] char_array, int size, int width, int height)
	{
		for (int i = 0; i < char_array.length; ++i)
		{
			char_array[i] -= 48;
			char_array[i] *= 4;
//			char_array[i] += 26 * 5;
		}

		this.char_array = char_array;
		this.size = size;
		this.gen(width, height);
	}

	public void gen(int width, int height)
	{
		this.array_buffer = MemoA1.genBuffer(MemoA1.createFloatByteBuffer(NaliData.createQuad(0, 0, 4 + this.size, 5 + this.size, width, height, 0, 0, 4, 5, NaliData.FONT_WIDTH, NaliData.FONT_HEIGHT)));
	}

	public void draw(MemoS rs, float x, float y, int width, int space)
	{
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		for (char c : this.char_array)
		{
			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(new float[]
			{
				x, y,
				(c + 26 * 5) / NaliData.FONT_WIDTH, 0.0F
			});
			RenderO.FLOATBUFFER.flip();
			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[0], RenderO.FLOATBUFFER);

			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(new float[]
			{
				1.0F, 1.0F, 1.0F, 1.0F
			});
			RenderO.FLOATBUFFER.flip();
			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], RenderO.FLOATBUFFER);

			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
			x += (space + 4 + this.size) * 2.0F / width;
		}
	}
}
