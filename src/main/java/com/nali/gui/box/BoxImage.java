package com.nali.gui.box;

import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class BoxImage extends BoxVT
{
	public int array_buffer = -1;

	public void gen(/*int v_sx, int v_sy, int v_ex, int v_ey, int v_width, int v_height, int t_sx, int t_sy, int t_ex, int t_ey, int t_width, int t_height*/)
	{
		this.clear();

		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(this.createQuad()));
	}

	public void draw(MemoS rs/*, int texture*/, float[] v_float_array, float[] c_float_array)
	{
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);

//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
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

		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
	}

	public void clear()
	{
		if (this.array_buffer != -1)
		{
			OpenGlHelper.glDeleteBuffers(this.array_buffer);
			this.array_buffer = -1;
		}
	}
}
