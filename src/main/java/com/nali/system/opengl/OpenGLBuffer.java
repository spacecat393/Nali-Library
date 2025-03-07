//package com.nali.system.opengl;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//
//@SideOnly(Side.CLIENT)
//public class OpenGLBuffer
//{
////	public static ByteBuffer createIntByteBuffer(int[] int_array/*, boolean native_memory*/)
////	{
////		ByteBuffer bytebuffer;
////
//////		if (native_memory)
//////		{
////		bytebuffer = ByteBuffer.allocateDirect(int_array.length << 2).order(ByteOrder.nativeOrder());
//////		}
//////		else
//////		{
//////			bytebuffer = ByteBuffer.allocate(int_array.length);
//////		}
////
////		for (int i : int_array)
////		{
////			bytebuffer.putInt(i);
////		}
////
////		bytebuffer.flip();
////
////		return bytebuffer;
////	}
////
////	public static ByteBuffer createFloatByteBuffer(float[] float_array/*, boolean native_memory*/)
////	{
////		ByteBuffer bytebuffer;
////
//////		if (native_memory)
//////		{
////		bytebuffer = ByteBuffer.allocateDirect(float_array.length << 2).order(ByteOrder.nativeOrder());
//////		}
//////		else
//////		{
//////			bytebuffer = ByteBuffer.allocate(float_array.length);
//////		}
////
////		for (float f : float_array)
////		{
////			bytebuffer.putFloat(f);
////		}
////
////		bytebuffer.flip();
////
////		return bytebuffer;
////	}
//
////	public static IntBuffer createIntBuffer(int[] int_array, boolean native_memory)
////	{
////		IntBuffer intbuffer;
////
////		if (native_memory)
////		{
////			intbuffer = ByteBuffer.allocateDirect(int_array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
////		}
////		else
////		{
////			intbuffer = IntBuffer.allocate(int_array.length);
////		}
////
////		intbuffer.put(int_array);
////		intbuffer.flip();
////
////		return intbuffer;
////	}
////
////	public static FloatBuffer createFloatBuffer(float[] float_array, boolean native_memory)
////	{
////		FloatBuffer floatbuffer = null;
////
////		if (native_memory)
////		{
////			floatbuffer = ByteBuffer.allocateDirect(float_array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
////		}
////		else
////		{
////			floatbuffer = FloatBuffer.allocate(float_array.length);
////		}
////
////		floatbuffer.put(float_array);
////		floatbuffer.flip();
////
////		return floatbuffer;
////	}
//
//	// public static void setM4x4FloatBuffer(Object[] object_array, int id, boolean native_memory)
//	// {
//	//	 List<M4x4> m4x4_arraylist = (List<M4x4>)object_array[id];
//	//	 float[] float_array = new float[m4x4_arraylist.size() * 16];
//
//	//	 for (int i = 0; i < m4x4_arraylist.size(); ++i)
//	//	 {
//	//		 // for (int x = 0; x < m4x4_arraylist.get(i).m4x4_float_array.length; ++x)
//	//		 // {
//	//		 //	 float_array[i * 16 + x] = m4x4_arraylist.get(i).m4x4_float_array[x];
//	//		 // }
//	//		 System.arraycopy(m4x4_arraylist.get(i).m4x4_float_array, 0, float_array, i * 16, 16);
//	//	 }
//
//	//	 FloatBuffer floatbuffer = null;
//
//	//	 if (native_memory)
//	//	 {
//	//		 floatbuffer = ByteBuffer.allocateDirect(float_array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//	//	 }
//	//	 else
//	//	 {
//	//		 floatbuffer = FloatBuffer.allocate(float_array.length);
//	//	 }
//
//	//	 floatbuffer.put(float_array);
//	//	 floatbuffer.flip();
//
//	//	 object_array[id] = floatbuffer;
//	// }
//
//	// public static void setIntBuffer(Object[] object_array, int id, boolean native_memory)
//	// {
//	//	 List<Integer> int_arraylist = (List<Integer>)object_array[id];
//	//	 int[] int_array = new int[int_arraylist.size()];
//
//	//	 for (int m0 = 0; m0 < int_arraylist.size(); ++m0)
//	//	 {
//	//		 int_array[m0] = int_arraylist.get(m0);
//	//	 }
//
//	//	 IntBuffer intbuffer = null;
//
//	//	 if (native_memory)
//	//	 {
//	//		 intbuffer = ByteBuffer.allocateDirect(int_array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
//	//	 }
//	//	 else
//	//	 {
//	//		 intbuffer = IntBuffer.allocate(int_array.length);
//	//	 }
//
//	//	 intbuffer.put(int_array);
//	//	 intbuffer.flip();
//
//	//	 object_array[id] = intbuffer;
//	// }
//
//	// public static void setIntArrayBuffer(Object[] object_array, int id, boolean native_memory)
//	// {
//	//	 List<int[]> int_arraylist = (List<int[]>)object_array[id];
//
//	//	 int size = 0;
//
//	//	 for (int[] int_array : int_arraylist)
//	//	 {
//	//		 size += int_array.length;
//	//	 }
//
//	//	 int[] int_array = new int[size];
//	//	 int index = 0;
//
//	//	 for (int m0 = 0; m0 < int_arraylist.size(); ++m0)
//	//	 {
//	//		 int[] temp_int_array = int_arraylist.get(m0);
//
//	//		 for (int m1 = 0; m1 < temp_int_array.length; ++m1)
//	//		 {
//	//			 int_array[index] = temp_int_array[m1];
//	//			 ++index;
//	//		 }
//	//	 }
//
//	//	 IntBuffer intbuffer = null;
//
//	//	 if (native_memory)
//	//	 {
//	//		 intbuffer = ByteBuffer.allocateDirect(int_array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
//	//	 }
//	//	 else
//	//	 {
//	//		 intbuffer = IntBuffer.allocate(int_array.length);
//	//	 }
//
//	//	 intbuffer.put(int_array);
//	//	 intbuffer.flip();
//
//	//	 object_array[id] = intbuffer;
//	// }
//
////	public static void setTextureByteBuffer(Object[] object_array, int id, byte[] byte_array, int width, int height)
////	{
////		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(width * height * 4);
////
////		bytebuffer.put(byte_array);
////		bytebuffer.flip();
////
////		object_array[id] = bytebuffer;
////	}
//
//	// public static void setByteBuffer(Object[] object_array, int id, BufferedImage bufferedimage)
//	// {
//	//	 ByteBuffer bytebuffer = ByteBuffer.allocateDirect(bufferedimage.getWidth() * bufferedimage.getHeight() * 4);
//
//	//	 int[] pixels = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
//	//	 bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), pixels, 0, bufferedimage.getWidth());
//
//	//	 for (int h = 0; h < bufferedimage.getHeight(); h++)
//	//	 {
//	//		 for (int w = 0; w < bufferedimage.getWidth(); w++)
//	//		 {
//	//			 int pixel = pixels[h * bufferedimage.getWidth() + w];
//
//	//			 bytebuffer.put((byte)((pixel >> 16) & 0xFF));
//	//			 bytebuffer.put((byte)((pixel >> 8) & 0xFF));
//	//			 bytebuffer.put((byte)(pixel & 0xFF));
//	//			 bytebuffer.put((byte)((pixel >> 24) & 0xFF));
//	//		 }
//	//	 }
//
//	//	 // bytebuffer.flip();
//
//	//	 // java.awt.image.Raster raster = bufferedimage.getRaster();
//	//	 // byte data[] = (byte[])raster.getDataElements(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null);
//	//	 // WritableRaster writableraster = bufferedimage.getRaster();
//	//	 // DataBufferByte databufferbyte = (DataBufferByte)writableraster.getDataBuffer();
//	//	 // bytebuffer.put(databufferbyte.getData());
//	//	 bytebuffer.flip();
//
//	//	 object_array[id] = bytebuffer;
//	// }
//
////	public static int loadFloatBuffer(FloatBuffer floatbuffer)
////	public static int loadFloatBuffer(ByteBuffer bytebuffer)
////	{
//////		GL11.glGetInteger(GL15.R_GL_ELEMENT_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//////		R_GL_ELEMENT_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////		int buffer = OpenGlHelper.glGenBuffers();
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
////		OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, bytebuffer, OpenGlHelper.GL_STATIC_DRAW);
//////		OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, R_GL_ELEMENT_ARRAY_BUFFER_BINDING);
////		return buffer;
////	}
//
////	public static void setIntBuffer(int id, int buffer, int size)
////	{
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
////		GL30.glVertexAttribIPointer(id, size, OpenGlHelper.GL_INT, 0, 0);
////	}
//
////	public static void setFloatBuffer(int id, int buffer, int size)
////	{
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
////		GL20.glVertexAttribPointer(id, size, GL11.GL_FLOAT, false, 0, 0);
////	}
//
////	public static int loadIntBuffer(IntBuffer intbuffer)
////	{
////		int buffer = OpenGlHelper.glGenBuffers();
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
////		OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, intbuffer, OpenGlHelper.GL_STATIC_DRAW);
////
////		return buffer;
////	}
//
////	public static int loadTextureBuffer(ByteBuffer bytebuffer, int width, int height)
////	{
////		int buffer = GL11.glGenTextures();
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, buffer);
////		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bytebuffer);
////		return buffer;
////	}
//
////	public static void setTextureBuffer(int buffer, byte texture_state)
////	{
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, buffer);
////
////		switch (texture_state)
////		{
////			case 0:
////			{
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////				break;
////			}
////			case 1:
////			{
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
////				break;
////			}
////			default:
////			{
////				error("TEXTURE_LEAK");
////			}
////		}
////	}
////
////	public static void setLightMapBuffer(int buffer)
////	{
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, buffer);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
////	}
//
////	public static void put(FloatBuffer floatbuffer, float[] float_array, int max)
////	{
////		floatbuffer.clear();
////		for (int i = 0; i < max; ++i)
////		{
////			floatbuffer.put(float_array[i]);
////		}
////		floatbuffer.flip();
////	}
//}
