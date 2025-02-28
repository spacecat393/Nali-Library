//package com.nali.gui.box;
//
//import com.nali.render.RenderO;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL20;
//
//@SideOnly(Side.CLIENT)
//public class BoxColor
//{
////	public int array_buffer = -1;
//
////	public void gen(/*int sx, int sy, int ex, int ey, int width, int height*/)
////	{
////		this.clear();
//////		this.x0 = sx;
//////		this.y0 = sy;
//////		this.x1 = ex;
//////		this.y1 = ey;
//////		this.v_width = width;
//////		this.v_height = height;
////		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(this.createQuad()));
////	}
//
//	public static void draw(MemoS rs, float[] v_float_array, float[] c_float_array, int array_buffer, int count)
//	{
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 2, GL11.GL_FLOAT, false, 0, 0);
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
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, count);
//	}
//
////	public void clear()
////	{
////		if (this.array_buffer != -1)
////		{
////			OpenGlHelper.glDeleteBuffers(this.array_buffer);
////			this.array_buffer = -1;
////		}
////	}
//}
