package com.nali.system.opengl.memo.client;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.memo.MemoA2;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.nali.system.ClientLoader.A2_MAP;
import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class MemoG
{
	public MemoA1[] memoa1_array;//vbo
	public int
		index_length,
		ebo,
		buffer;

	public byte state;//texture_state culling transparent glow
	public int
		texture_id,
		shader_id,
		vao;
	public byte stride;

	public MemoG(MemoA0[] memoa0_array, String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, int shader_id, String[] model_string_array, String folder_path)
	{
		String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';
//		Nali.warn(model_folder_string);

		if (NaliConfig.VAO)
		{
			this.vao = NaliGL.glGenVertexArrays();
			NaliGL.glBindVertexArray(this.vao);
		}

		this.texture_id = Integer.parseInt(model_string_array[1]);
		this.shader_id = shader_id;
		this.state = (byte)(Byte.parseByte(model_string_array[4]) | 2 * Byte.parseByte(model_string_array[5]) | 4 * Byte.parseByte(model_string_array[6]) | 8 * Byte.parseByte(model_string_array[7]));//texture_state culling transparent glow

		this.memoa1_array = MemoA1.gen(memoa0_array, shader_string_2d_array, attriblocation_string_2d_array, model_string_array/*, folder_path*//*, shader_string_2d_array*/, shader_id);
		int _size = 0;
//		for (MemoA1 memoa1 : this.memoa1_array)
		for (int i = 0; i < memoa0_array.length; ++i)
		{
//			MemoA0 memoa0 = memoa0_array[i];
			MemoA1 memoa1 = memoa1_array[i];
			if (memoa1.type == GL11.GL_FLOAT)
			{
//				if (memoa1.o instanceof byte[])
//				{
//					_size += ((byte[])memoa1.o).length * 4;
//					this.stride += memoa1.size * 4;
//				}
//				else
//				{
					_size += ((float[])memoa1.o).length * 4;
					this.stride += memoa1.size * 4;
//				}
			}
			else if (memoa1.type == GL11.GL_UNSIGNED_BYTE)
//			else if (memoa1.type == GL11.GL_SHORT)
			{
				_size += ((byte[])memoa1.o).length;
				this.stride += memoa1.size;
//				_size += ((byte[])memoa1.o).length * 2;
//				this.stride += memoa1.size * 2;
			}
			else
			{
				Nali.error("MemoG");
			}
		}
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(_size).order(ByteOrder.nativeOrder());
//		for (MemoA0 memoa0 : memoa0_array)
		for (int index = 0; index < ((float[])memoa0_array[0].o).length / 3; ++index)
		{
			for (int i = 0; i < memoa0_array.length; ++i)
			{
//				MemoA0 memoa0 = memoa0_array[i];
				MemoA1 memoa1 = memoa1_array[i];
				if (memoa1.type == GL11.GL_FLOAT)
				{
//					if (memoa1.o instanceof byte[])
//					{
//						int size = index * memoa1.size;
//						byte[] byte_array = (byte[])memoa1.o;
//						for (int l = size; l < size + memoa1.size; ++l)
//						{
////						bytebuffer.putShort((short)(byte_array[l]));
//							bytebuffer.putFloat((float)(byte_array[l] & 0xFF));
////							bytebuffer.putFloat((float)(byte_array[l]));
//						}
//					}
//					else
//					{
						int size = index * memoa1.size;
						float[] float_array = (float[])memoa1.o;
						for (int l = size; l < size + memoa1.size; ++l)
						{
							bytebuffer.putFloat(float_array[l]);
						}
//					}
				}
				else if (memoa1.type == GL11.GL_UNSIGNED_BYTE)
//				else if (memoa1.type == GL11.GL_SHORT)
				{
					int size = index * memoa1.size;
					byte[] byte_array = (byte[])memoa1.o;
					for (int l = size; l < size + memoa1.size; ++l)
					{
//						bytebuffer.putShort((short)(byte_array[l]));
//						bytebuffer.putShort((short)(byte_array[l] & 0xFF));
						bytebuffer.put(byte_array[l]);
					}
				}
				else
				{
					Nali.error("MemoG");
				}
			}
		}
		bytebuffer.flip();
		this.buffer = MemoA1.genBuffer(bytebuffer);

		int[] index_int_array = FileDataReader.getIntArray(model_folder_string + "index.bin");
		this.index_length = index_int_array.length;

		this.ebo = OpenGlHelper.glGenBuffers();
		OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.ebo);
		OpenGlHelper.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, MemoA1.createIntByteBuffer(index_int_array), OpenGlHelper.GL_STATIC_DRAW);

		if (model_string_array.length == 9)
		{
			int size = memoa0_array.length;

			MemoA2 memoa2 = new MemoA2();

			memoa2.index_int_array = index_int_array;

			memoa2.vertex_float_array = (float[])memoa0_array[0].o;
//			memoa2.joint_int_array = (int[])memoa0_array[size - 2].o;
			byte[] joint_byte_array = (byte[])memoa0_array[size - 2].o;
			int joint_byte_array_length = joint_byte_array.length;
			memoa2.joint_short_array = new short[joint_byte_array_length];
			for (int i = 0; i < joint_byte_array_length; ++i)
			{
				memoa2.joint_short_array[i] = (short)(joint_byte_array[i] & 0xFF);
			}

			memoa2.weight_float_array = (float[])memoa0_array[size - 1].o;
			memoa2.max_joint = Byte.parseByte(model_string_array[8]);

//			Nali.A2_LIST;
			A2_MAP.put(G_LIST.size(), memoa2);
		}
	}
}
