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
public class BoxTextAZ extends BoxVT
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
		int l = 0;

		this.v_width = width;
		this.v_height = height;

		for (char c : this.char_array)
		{
			this.x0 = x;
			this.y0 = y;
			this.x1 = x + size;
			this.y1 = y + size;

			this.u0 = c;
//			this.v0 = 0;
			this.u1 = c + 5;
//			this.v1 = 5;

			System.arraycopy(this.createQuad(), 0, float_array, l++ * 24, 24);
			x += space + size;
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

//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, this.char_array.length * 6);
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
