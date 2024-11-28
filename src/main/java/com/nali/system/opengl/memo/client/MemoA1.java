package com.nali.system.opengl.memo.client;

import com.nali.NaliConfig;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class MemoA1
{
	public Object o;
//	public int buffer;
	public byte /*stride, */size;
	public int type;
	public byte offset;

	public MemoA1(float[] float_array, byte size, int type, byte offset)
	{
//		if (o instanceof float[])
//		{
		this.o = float_array;
//		this.buffer = genBuffer(createFloatByteBuffer(float_array));
//		}
//		else if (o instanceof int[])
//		{
//			this.buffer = genBuffer(createIntByteBuffer((int[])o/*, true*/));
//		}
//		else
//		{
//			error("MEMOA1");
//		}
		this.size = size;
//		this.stride = (byte)(this.size * 4);
		this.type = type;
		this.offset = offset;
	}

	public MemoA1(byte[] byte_array, byte size, int type, byte offset)
	{
//		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(byte_array.length * 2).order(ByteOrder.nativeOrder());
////		bytebuffer.put(byte_array);
//		for (byte b : byte_array)
//		{
////			bytebuffer.put((byte)(b & 0xFF));
//			bytebuffer.putShort((short)(b & 0xFF));
////			bytebuffer.putInt(b & 0xFF);
//		}
//		bytebuffer.flip();
		this.o = byte_array;
//		this.buffer = genBuffer(bytebuffer);
		this.size = size;
		this.type = type;
		this.offset = offset;
	}
//	public MemoA1(float[] float_array, byte stride, byte size)
//	{
//		this.buffer = genBuffer(createFloatByteBuffer(float_array));
//		this.stride = stride;
//		this.size = size;
//	}

//	public MemoAttrib(/*Object object, */int buffer, byte size)
//	{
////		this.object = object;
//		this.buffer = buffer;
//		this.size = size;
//	}

	public static MemoA1[] gen(MemoA0[] memoa0_array, String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, String[] model_string_array/*, String folder_path*//*, String[][] shader_string_2d_array*/, int shader_id)
	{
//		String model_folder_string = folder_path + "/Model/" + model_string_array[0] + '/';
//		String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + model_string_array[2] + "/ShaderList"));
//		String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + model_string_array[0] + "/ShaderList"));
//		String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(folder_path + "/ShaderList"));
////		this.state |= Byte.parseByte(model_string_array[1]);//texture_state
//		//default_texture
//		this.texture_id = Integer.parseInt(model_string_array[1]);
//		this.shader_id = Integer.parseInt(model_string_array[2]);
////		this.shader_id = Integer.parseInt(model_string_array[3]);
//
//		this.state = (byte)(Byte.parseByte(model_string_array[3]) | 2 * Byte.parseByte(model_string_array[4]) | 4 * Byte.parseByte(model_string_array[5]) | 8 * Byte.parseByte(model_string_array[6]));//texture_state culling transparent glow
////		this.state = (byte)(Byte.parseByte(model_string_array[4]) | 2 * Byte.parseByte(model_string_array[5]) | 4 * Byte.parseByte(model_string_array[6]) | 8 * Byte.parseByte(model_string_array[7]));//texture_state culling transparent glow
//
////		int shader_id = Integer.parseInt(model_string_array[2]);
////		this.shader_id = Integer.parseInt(model_string_array[2]);
////		this.state |= (byte)(2 * Integer.parseInt(model_string_array[3]));//culling
//
////		this.shader = shader_id;
//
//		this.element_array_buffer = OpenGlHelper.glGenBuffers();
//		this.index_int_array = FileDataReader.getIntArray(model_folder_string + "/Index");
//		this.index_length = this.index_int_array.length;
//		this.index = OpenGLBuffer.createIntByteBuffer(this.index_int_array, true);
//		OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.element_array_buffer);
//		OpenGlHelper.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (ByteBuffer)this.index, OpenGlHelper.GL_STATIC_DRAW);

//		String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "Shaders/" + shader_string_2d_array[(int)this.shader][0] + "/Attrib");
//		String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + shader_string_2d_array[shader_id][0] + "/Shader/" + shader_string_2d_array[shader_id][1] + "/Attrib"));
		String[] shader_string_array = shader_string_2d_array[shader_id];
		switch (shader_string_array.length)
		{
			case 6:
				return genO(memoa0_array/*, model_string_array*//*, model_folder_string*//*folder_path*//*, shader_string_2d_array*//*, attriblocation_string_2d_array*/, attriblocation_string_2d_array.length);
			case 9:
				return genS(memoa0_array, model_string_array, shader_string_array/*, model_folder_string*//*folder_path*//*, shader_string_2d_array*//*, attriblocation_string_2d_array*/, attriblocation_string_2d_array.length);
			default:
				error("MEMOA1");
		}

		return null;
	}

	public static MemoA1[] genO(MemoA0[] memoa0_array/*, String[] model_string_array*//*, String model_folder_string*//*, String folder_path*//*, String[][] shader_string_2d_array*//*, String[][] attriblocation_string_2d_array*/, int length)
	{
//		String model_folder_string = folder_path + "/Model/" + model_string_array[0] + '/';

//		this.vertices_float_array = FileDataReader.getFloatArray(model_folder_string + "/Vertices");

		MemoA1[] memoa1_array = new MemoA1[memoa0_array.length];
		byte offset = 0;

		byte stride;
		if (NaliConfig.VAO)
		{
			for (int i = 0; i < length; ++i)
			{
				stride += memoa0_array[i].size * 4;
			}
		}

		for (int i = 0; i < length; ++i)
		{
//			String[] attriblocation_string_array = attriblocation_string_2d_array[i];
//			String attriblocation_name_string = attriblocation_string_array[0];
			MemoA0 memoa0 = memoa0_array[i];

			memoa1_array[i] = new MemoA1((float[])memoa0.o, memoa0.size, GL11.GL_FLOAT, offset);

			if (NaliConfig.VAO)
			{
				GL20.glVertexAttribPointer(i, memoa0.size, GL11.GL_FLOAT, false, stride, offset);
				GL20.glEnableVertexAttribArray(i);
			}

			offset += memoa0.size * 4;

//			memoa1_array[i] = new MemoA1(FileDataReader.getFloatArray(model_folder_string + Character.toUpperCase(attriblocation_name_string.charAt(0)) + attriblocation_name_string.substring(1)), Byte.parseByte(attriblocation_string_array[1]));
		}

		return memoa1_array;
	}

	public static MemoA1[] genS(MemoA0[] memoa0_array, String[] model_string_array, String[] shader_string_array/*, String model_folder_string*//*, String folder_path*//*, String[][] shader_string_2d_array*//*, String[][] attriblocation_string_2d_array*/, int length)
	{
		MemoA1[] memoa1_array = genO(memoa0_array/*, model_string_array*//*, model_folder_string*//*, folder_path*//*, shader_string_2d_array*//*, attriblocation_string_2d_array*/, length - 2);
//		String model_folder_string = folder_path + "Models/" + model_string_array[0] + '/';
//		byte max_joint = Byte.parseByte(model_string_array[4]);
//		this.max_joint = Byte.parseByte(model_string_array[4]);
//		this.animation_id = Integer.parseInt(model_string_array[7]);
//		byte max_joint = Byte.parseByte(model_string_array[7]);
		byte max_joint = Byte.parseByte(model_string_array[8]);
//		this.max_joint = Byte.parseByte(model_string_array[8]);

//		float[] joint_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
//		float[] joint_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
		int j_index = length - 2;
		int w_index = length - 1;
////		float[] joint_float_array = (float[])memoa0_array[j_index].t;
		MemoA0 j_memoa0 = memoa0_array[j_index];
		MemoA0 w_memoa0 = memoa0_array[w_index];
		byte[] joint_byte_array = (byte[])j_memoa0.o;
//////		float[] weight_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");
//////		float[] weight_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");
		float[] weight_float_array = (float[])w_memoa0.o;
//////		float[] temp_joint_float_array = joint_float_array;
		int size = joint_byte_array.length;
//		float[] temp_joint_float_array = new float[size];
//		for (int i = 0; i < size; ++i)
//		{
////			if (joint_int_array[i] == -1)
////			{
////				joint_int_array[i] = 0;
////			}
//			temp_joint_float_array[i] = joint_int_array[i];
//		}
//		int[] temp_joint_int_array = joint_int_array;
		byte[] temp_joint_byte_array = joint_byte_array;
		float[] temp_weight_float_array = weight_float_array;

		byte limit_max_joint = Byte.parseByte(shader_string_array[8]);

		if (max_joint != limit_max_joint)
		{
			int step = limit_max_joint - max_joint;
//			int joint_float_array_length = joint_float_array.length;
			int joint_float_array_length = size;
//			int joint_int_array_length = joint_int_array.length;
			int new_size = joint_float_array_length + (joint_float_array_length / max_joint) * step;
//			int new_size = joint_int_array_length + (joint_int_array_length / max_joint) * step;
//			temp_joint_float_array = new float[new_size];
			temp_joint_byte_array = new byte[new_size];
//			temp_joint_int_array = new int[new_size];
			temp_weight_float_array = new float[new_size];
			int index = 0;
			int temp_index = 0;

//			for (int x = 0; x < temp_joint_float_array.length; x += limit_max_joint)
			for (int x = 0; x < temp_joint_byte_array.length; x += limit_max_joint)
//			for (int x = 0; x < temp_joint_int_array.length; x += limit_max_joint)
			{
				for (int y = 0; y < max_joint; ++y)
				{
//					temp_joint_float_array[temp_index] = joint_float_array[index];
//					temp_joint_float_array[temp_index] = joint_int_array[index];
					temp_joint_byte_array[temp_index] = joint_byte_array[index];
//					temp_joint_int_array[temp_index] = joint_int_array[index];
					temp_weight_float_array[temp_index++] = weight_float_array[index++];
				}
				temp_index += step;

//				for (int y = 0; y < step; ++y)
//				{
////					temp_joint_float_array[temp_index] = -1;
////					temp_joint_float_array[temp_index] = 0;
//					temp_joint_byte_array[temp_index] = 0;
////					temp_joint_int_array[temp_index] = -1;
//					temp_weight_float_array[temp_index++] = 0.0F;
//				}
			}

//			this.joint_float_array = temp_joint_float_array;
//			this.weight_float_array = temp_weight_float_array;
		}

//		FloatBuffer joint_floatbuffer = OpenGLBuffer.createFloatBuffer(temp_joint_float_array, true);
//		ByteBuffer joint_bytebuffer = OpenGLBuffer.createFloatByteBuffer(temp_joint_float_array, true);

		MemoA1 last_memoa1 = memoa1_array[memoa1_array.length - 2-1];
		byte offset = (byte)(last_memoa1.offset + last_memoa1.size * 4);
//		memoa1_array[j_index] = new MemoA1(temp_joint_byte_array, limit_max_joint, GL11.GL_SHORT, offset);
		memoa1_array[j_index] = new MemoA1(temp_joint_byte_array, limit_max_joint, GL11.GL_UNSIGNED_BYTE, offset);
//		memoa1_array[j_index] = new MemoA1(temp_joint_byte_array, limit_max_joint, GL11.GL_FLOAT, offset);

		byte stride;
		if (NaliConfig.VAO)
		{
			stride = (byte)(offset + limit_max_joint + limit_max_joint * 4);
//			stride = (byte)(offset + limit_max_joint * 2 + limit_max_joint * 4);
//			GL20.glVertexAttribPointer(j_index, limit_max_joint, GL11.GL_FLOAT, false, 0, 0);
//			GL20.glVertexAttribPointer(j_index, limit_max_joint, GL11.GL_SHORT, false, stride, offset);
			GL20.glVertexAttribPointer(j_index, limit_max_joint, GL11.GL_UNSIGNED_BYTE, false, stride, offset);
			GL20.glEnableVertexAttribArray(j_index);
		}

//		offset += limit_max_joint * 2;
		offset += limit_max_joint;
//		offset += limit_max_joint * 4;

//		memoa1_array[j_index] = new MemoA1(temp_joint_int_array, limit_max_joint, j_memoa0.type);
//		FloatBuffer weight_floatbuffer = OpenGLBuffer.createFloatBuffer(temp_weight_float_array, true);
//		ByteBuffer weight_bytebuffer = OpenGLBuffer.createFloatByteBuffer(temp_weight_float_array, true);

		memoa1_array[w_index] = new MemoA1(temp_weight_float_array, limit_max_joint, GL11.GL_FLOAT, offset);

		if (NaliConfig.VAO)
		{
			GL20.glVertexAttribPointer(w_index, limit_max_joint, GL11.GL_FLOAT, false, stride, offset);
			GL20.glEnableVertexAttribArray(w_index);
		}

//		offset += limit_max_joint * 4;

//		memoa1_array[j_index] = new MemoA1(temp_joint_float_array, max_joint);
////		memoa1_array[j_index] = new MemoA1(temp_joint_float_array, Byte.parseByte(shader_string_array[9]), max_joint);
////		memoa1_array[j_index] = new MemoA1((float[])memoa0_array[j_index].o, Byte.parseByte(shader_string_array[9]), max_joint);
//		memoa1_array[w_index] = new MemoA1((float[])memoa0_array[w_indexecfd].o, max_joint);

		return memoa1_array;
	}

	public static int genBuffer(ByteBuffer bytebuffer)
	{
//		GL11.glGetInteger(GL15.R_GL_ELEMENT_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//		R_GL_ELEMENT_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
		int buffer = OpenGlHelper.glGenBuffers();
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
		OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, bytebuffer, OpenGlHelper.GL_STATIC_DRAW);
//		OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, R_GL_ELEMENT_ARRAY_BUFFER_BINDING);
		return buffer;
	}

//	public static int genIntBuffer(ByteBuffer bytebuffer)
//	{
////		GL11.glGetInteger(GL15.R_GL_ELEMENT_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_ELEMENT_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//		int buffer = OpenGlHelper.glGenBuffers();
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
//		OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, bytebuffer, OpenGlHelper.GL_STATIC_DRAW);
////		OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, R_GL_ELEMENT_ARRAY_BUFFER_BINDING);
//		return buffer;
//	}

	public static ByteBuffer createIntByteBuffer(int[] int_array)
	{
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(int_array.length << 2).order(ByteOrder.nativeOrder());

		for (int i : int_array)
		{
			bytebuffer.putInt(i);
		}

		bytebuffer.flip();

		return bytebuffer;
	}

	public static ByteBuffer createFloatByteBuffer(float[] float_array)
	{
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(float_array.length << 2).order(ByteOrder.nativeOrder());

		for (float f : float_array)
		{
			bytebuffer.putFloat(f);
		}

		bytebuffer.flip();

		return bytebuffer;
	}

	public static ByteBuffer createFloatByteBuffer(float[] float_array, int size)
	{
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(size << 2).order(ByteOrder.nativeOrder());

		for (int i = 0; i < size; ++i)
		{
			bytebuffer.putFloat(float_array[i]);
		}

		bytebuffer.flip();

		return bytebuffer;
	}

	public static void put(FloatBuffer floatbuffer, float[] float_array, int max)
	{
		floatbuffer.clear();
		for (int i = 0; i < max; ++i)
		{
			floatbuffer.put(float_array[i]);
		}
		floatbuffer.flip();
	}
}
