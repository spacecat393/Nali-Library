package com.nali.gui.box;

import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoA1;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class BoxColor
{
	public int array_buffer = -1;

	public void gen(int sx, int sy, int ex, int ey, int width, int height)
	{
		if (this.array_buffer != -1)
		{
			OpenGlHelper.glDeleteBuffers(this.array_buffer);
		}

		this.array_buffer = MemoA1.genBuffer(MemoA1.createFloatByteBuffer(NaliData.createQuad(sx, sy, ex, ey, width, height)));
	}

	public void draw(MemoS rs, float[] v_float_array, float[] c_float_array)
	{
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.array_buffer);
		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 2, GL11.GL_FLOAT, false, 0, 0);

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
}
