package com.nali.box;

import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoA1;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class BoxTextAZ
{
	public char[] char_array;
	public int array_buffer = -1;

	public BoxTextAZ(char[] char_array)
	{
		for (int i = 0; i < char_array.length; ++i)
		{
			char_array[i] -= 65;
			char_array[i] *= 5;
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
		for (int i = 0; i < length; ++i)
		{
			char c = this.char_array[i];
			float[] quad_float_array = NaliData.createQuad(x, y, x/* + 5*/ + size, y/* + 5*/ + size, width, height, c, 0, c + 5, 5, NaliData.FONT_WIDTH, NaliData.FONT_HEIGHT);
			System.arraycopy(quad_float_array, 0, float_array, i * 24, 24);
			x += space/* + 5*/ + size;
		}

		this.array_buffer = MemoA1.genBuffer(MemoA1.createFloatByteBuffer(float_array));
	}

	public void draw(MemoS rs)
	{
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(new float[]
			{
				0.0F, 0.0F,
				0.0F, 0.0F
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
//			OpenGlHelper.(rs.uniformlocation_int_array[2], 0);

			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
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
