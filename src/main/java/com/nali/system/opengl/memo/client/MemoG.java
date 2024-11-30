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
import org.lwjgl.opengl.GL20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.nali.system.ClientLoader.A2_MAP;
import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class MemoG
{
	public MemoA[] memoa_array;//vbo
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

	public MemoG(String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, int shader_id, String[] model_string_array, String folder_path)
	{
		String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';
//		Nali.warn(model_folder_string);

		this.texture_id = Integer.parseInt(model_string_array[1]);
		this.shader_id = shader_id;
		this.state = (byte)(Byte.parseByte(model_string_array[4]) | 2 * Byte.parseByte(model_string_array[5]) | 4 * Byte.parseByte(model_string_array[6]) | 8 * Byte.parseByte(model_string_array[7]));//texture_state culling transparent glow

		this.memoa_array = MemoA.gen(this, shader_string_2d_array, model_string_array, attriblocation_string_2d_array, folder_path, shader_id);
		int buffer_size = 0;
		for (MemoA memoa : this.memoa_array)
		{
			if (memoa.type == GL11.GL_FLOAT)
			{
				buffer_size += ((float[])memoa.o).length * 4;
			}
			else if (memoa.type == GL11.GL_UNSIGNED_BYTE)
			{
				buffer_size += ((byte[])memoa.o).length;
			}
			else
			{
				Nali.error("BUFFER_SIZE");
			}
		}
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(buffer_size).order(ByteOrder.nativeOrder());
		for (int index = 0; index < ((float[])this.memoa_array[0].o).length / 3; ++index)
		{
			for (MemoA memoa : this.memoa_array)
			{
				if (memoa.type == GL11.GL_FLOAT)
				{
					int size = index * memoa.size;
					float[] float_array = (float[])memoa.o;
					for (int l = size; l < size + memoa.size; ++l)
					{
						bytebuffer.putFloat(float_array[l]);
					}
				}
				else if (memoa.type == GL11.GL_UNSIGNED_BYTE)
				{
					int size = index * memoa.size;
					byte[] byte_array = (byte[])memoa.o;
					for (int l = size; l < size + memoa.size; ++l)
					{
						bytebuffer.put(byte_array[l]);
					}
				}
				else
				{
					Nali.error("BYTEBUFFER");
				}
			}
		}
		bytebuffer.flip();
		if (NaliConfig.VAO)
		{
			this.vao = NaliGL.glGenVertexArrays();
			NaliGL.glBindVertexArray(this.vao);
			this.buffer = MemoA.genBuffer(bytebuffer);
			for (int i = 0; i < this.memoa_array.length; ++i)
			{
				MemoA memoa = this.memoa_array[i];
//				OpenGlHelper.glGetAttribLocation(this.program, attriblocation_string_2d_array[i][0])
				//need check or use gl 3 to set location
				GL20.glVertexAttribPointer(i, memoa.size, GL11.GL_FLOAT, false, this.stride, memoa.offset);
				GL20.glEnableVertexAttribArray(i);
			}
		}
		else
		{
			this.buffer = MemoA.genBuffer(bytebuffer);
		}

		int[] index_int_array = FileDataReader.getIntArray(model_folder_string + "index.bin");
		this.index_length = index_int_array.length;

		this.ebo = OpenGlHelper.glGenBuffers();
		OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.ebo);
		OpenGlHelper.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, MemoA.createIntByteBuffer(index_int_array), OpenGlHelper.GL_STATIC_DRAW);

		if (model_string_array.length == 9)
		{
			int size = this.memoa_array.length;

			MemoA2 memoa2 = new MemoA2();

			memoa2.index_int_array = index_int_array;

			memoa2.vertex_float_array = (float[])this.memoa_array[0].o;
			byte[] joint_byte_array = (byte[])this.memoa_array[size - 2].o;
			int joint_byte_array_length = joint_byte_array.length;
			memoa2.joint_short_array = new short[joint_byte_array_length];
			for (int i = 0; i < joint_byte_array_length; ++i)
			{
				memoa2.joint_short_array[i] = (short)(joint_byte_array[i] & 0xFF);
			}

			memoa2.weight_float_array = (float[])this.memoa_array[size - 1].o;
			memoa2.max_joint = Byte.parseByte(model_string_array[8]);

			A2_MAP.put(G_LIST.size(), memoa2);
		}
	}
}
