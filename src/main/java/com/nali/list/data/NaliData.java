package com.nali.list.data;

public class NaliData
{
	public static int
		TEXTURE_STEP,
		SHADER_STEP;
//		MODEL_STEP
//		ANIMATION_STEP
//		FRAME_STEP
//		SOUND_STEP
//		MAX_BONE
	public static byte ORDINAL = 0;

//	public static int QUAD_ARRAY_BUFFER;
//
//	public static List<Integer>
//		TEXTURE_INTEGER_LIST = new ArrayList(),
//		ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
////		FONT_ARRAY_BUFFER_INTEGER_LIST = new ArrayList();
//	public static List<Byte> INIT_BYTE_LIST = new ArrayList();//1bit as init
//	public static byte INIT_BYTE_BIT;

//	public static void run()
//	{
//		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
//		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
//
//		QUAD_ARRAY_BUFFER = MemoA1.genBuffer(MemoA1.createFloatByteBuffer(new float[]
//		{
//			-1, 1, 0.0F, 1.0F,
//			-1, -1, 0.0F, 0.0F,
//			1, -1, 1.0F, 0.0F,
//
//			-1, 1, 0.0F, 1.0F,
//			1, -1, 1.0F, 0.0F,
//			1, 1, 1.0F, 1.0F
//		}));
//
////		initFont();
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//	}
//
////	public static void initFont()
////	{
////		Minecraft minecraft = Minecraft.getMinecraft();
//////		DisplayMode displaymode = Display.getDisplayMode();
////		int width = minecraft.displayWidth;
////		int height = minecraft.displayHeight;
////		//uv is wrong need 5x5 4x5 1 char
//////		FONT_ARRAY_BUFFER_INTEGER_LIST.add(MemoA1.genBuffer(MemoA1.createFloatByteBuffer(createQuad(0, 0, 5 + !, 5 + !, width, height, 0, 0, 5, 5, FONT_WIDTH, FONT_HEIGHT))));
//////		FONT_ARRAY_BUFFER_INTEGER_LIST.add(MemoA1.genBuffer(MemoA1.createFloatByteBuffer(createQuad(0, 0, 4 + !, 5 + !, width, height, 0, 0, 4, 5, FONT_WIDTH, FONT_HEIGHT))));
////	}
//
//	public static void setByte()
//	{
//		//1 bit
//		INIT_BYTE_BIT %= 8;
//		if (INIT_BYTE_BIT == 0)
//		{
//			INIT_BYTE_LIST.add((byte)0);
//		}
//		++INIT_BYTE_BIT;
//	}
//
//	public void clearTA()
//	{
////		for (int font_array_buffer : FONT_ARRAY_BUFFER_INTEGER_LIST)
////		{
////			OpenGlHelper.glDeleteBuffers(font_array_buffer);
////		}
//		for (int array_buffer : ARRAY_BUFFER_INTEGER_LIST)
//		{
//			OpenGlHelper.glDeleteBuffers(array_buffer);
//		}
//		for (int texture : TEXTURE_INTEGER_LIST)
//		{
//			GL11.glDeleteTextures(texture);
//		}
//
////		FONT_ARRAY_BUFFER_INTEGER_LIST.clear();
//		ARRAY_BUFFER_INTEGER_LIST.clear();
//		TEXTURE_INTEGER_LIST.clear();
//	}
//
////	public static void setFramebuffer(int x, int y, int width)
////	{
////		Minecraft minecraft = Minecraft.getMinecraft();
////		ARRAY_BUFFER_INTEGER_LIST.add(MemoA1.genBuffer(MemoA1.createFloatByteBuffer(createQuad(x, y, width + x, 5 + y, minecraft.displayWidth, minecraft.displayHeight))));
////
////		int texture = GL11.glGenTextures();
////		TEXTURE_INTEGER_LIST.add(texture);
////
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
//////		int gl_texture_binding_2d = INTBUFFER.get(0);
//////
//////		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, INTBUFFER);
//////		int gl_active_texture = INTBUFFER.get(0);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
//////		int gl_texture_binding_2d_0 = INTBUFFER.get(0);
////
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
////		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, 5, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (IntBuffer)null);
////
////		GL11.glViewport(0, 0, width, 5);
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0);
////
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
//////
//////		OpenGlHelper.setActiveTexture(gl_active_texture);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d);
////	}
//
//	public static void drawQuad(MemoS rs, float[] vec2_float_array, float[] color_float_array, int array_buffer, int texture)
//	{
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//
//		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(vec2_float_array);
//		RenderO.FLOATBUFFER.flip();
//		OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], RenderO.FLOATBUFFER);
//
//		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(color_float_array);
//		RenderO.FLOATBUFFER.flip();
//		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], RenderO.FLOATBUFFER);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//	}
//
//	public static void drawQuad(MemoS rs, int array_buffer, int texture)
//	{
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
////		R_GL_TEXTURE_BINDING_2D = INTBUFFER.get(0);
////
////		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, INTBUFFER);
////		R_GL_ACTIVE_TEXTURE = INTBUFFER.get(0);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
////		R_GL_TEXTURE_BINDING_2D_0 = INTBUFFER.get(0);
////		R_GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		R_GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, array_buffer);
//		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_0);
////
////		OpenGlHelper.setActiveTexture(R_GL_ACTIVE_TEXTURE);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D);
//	}
}
