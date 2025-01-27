package com.nali.system.opengl.memo.client;

import com.nali.Nali;
import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

@SideOnly(Side.CLIENT)
public class MemoA
{
	public Object o;
	public byte
		size,
		offset;
	public int type;

	public MemoA(Object o, byte size, int type, byte offset)
	{
		this.o = o;
		this.size = size;
		this.offset = offset;
		this.type = type;
	}

	public static MemoA[] gen(MemoG memog, String[][] shader_string_2d_array, String[] model_string_array, String[][] attriblocation_string_2d_array, String folder_path, int shader_id)
	{
		String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';

		int length = attriblocation_string_2d_array.length;
		byte offset = 0;
		MemoA[] memoa0_array = new MemoA[length];
		if (model_string_array.length == 8)
		{
			String[] attriblocation_string_array;
			for (int i = 0; i < length - 2; ++i)
			{
				attriblocation_string_array = attriblocation_string_2d_array[i];
				String attriblocation_name_string = attriblocation_string_array[0];
				byte size = Byte.parseByte(attriblocation_string_array[1]);
				memoa0_array[i] = new MemoA(FileDataReader.getFloatArray(model_folder_string + attriblocation_name_string + ".bin"), size, GL11.GL_FLOAT, offset);
				offset += size * 4;
			}

			length -= 2;
			String[] shader_string_array = shader_string_2d_array[shader_id];
			byte max_joint = Byte.parseByte(model_string_array[7]);
			byte limit_max_joint = Byte.parseByte(shader_string_array[8]);
			try
			{
				byte[] joint_byte_array = Files.readAllBytes(Paths.get(model_folder_string + attriblocation_string_2d_array[length][0] + ".bin"));
				float[] weight_float_array = FileDataReader.getFloatArray(model_folder_string + attriblocation_string_2d_array[length + 1][0] + ".bin");

				int size = joint_byte_array.length;
				byte[] temp_joint_byte_array = joint_byte_array;
				float[] temp_weight_float_array = weight_float_array;

				if (max_joint != limit_max_joint)
				{
					int step = limit_max_joint - max_joint;
					int joint_float_array_length = size;
					int new_size = joint_float_array_length + (joint_float_array_length / max_joint) * step;
					temp_joint_byte_array = new byte[new_size];
					temp_weight_float_array = new float[new_size];
					int index = 0;
					int temp_index = 0;

					for (int x = 0; x < temp_joint_byte_array.length; x += limit_max_joint)
					{
						for (int y = 0; y < max_joint; ++y)
						{
							temp_joint_byte_array[temp_index] = joint_byte_array[index];
							temp_weight_float_array[temp_index++] = weight_float_array[index++];
						}
						temp_index += step;
					}
				}

				memoa0_array[length] = new MemoA(temp_joint_byte_array, limit_max_joint, GL11.GL_UNSIGNED_BYTE, offset);
				offset += limit_max_joint;
				memoa0_array[length + 1] = new MemoA(temp_weight_float_array, limit_max_joint, GL11.GL_FLOAT, offset);
				offset += limit_max_joint * 4;
			}
			catch (IOException e)
			{
				Nali.error(e);
			}
		}
		else
		{
			for (int i = 0; i < length; ++i)
			{
				String[] attriblocation_string_array = attriblocation_string_2d_array[i];
				String attriblocation_name_string = attriblocation_string_array[0];
				byte size = Byte.parseByte(attriblocation_string_array[1]);
				memoa0_array[i] = new MemoA(FileDataReader.getFloatArray(model_folder_string + attriblocation_name_string + ".bin"), size, GL11.GL_FLOAT, offset);
				offset += size * 4;
			}
		}
		memog.stride = offset;

		return memoa0_array;
	}

	public static int genBuffer(ByteBuffer bytebuffer)
	{
		int buffer = OpenGlHelper.glGenBuffers();
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, buffer);
		OpenGlHelper.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, bytebuffer, OpenGlHelper.GL_STATIC_DRAW);
		return buffer;
	}

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
