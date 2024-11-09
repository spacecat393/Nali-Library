package com.nali.system.opengl.memo.client;

import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL15;

import static com.nali.system.ClientLoader.A2_MAP;
import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class MemoG
{
	public MemoA1[] memoa1_array;//vbo
	public int index_length;
	public int ebo;

	public byte state;//texture_state culling transparent glow
	public int
	texture_id,
	shader_id,
	vao;

	public MemoG(MemoA0[] memoa0_array, String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, int shader_id, String[] model_string_array, String folder_path)
	{
		String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';

		if (NaliConfig.VAO)
		{
			this.vao = NaliGL.glGenVertexArrays();
			NaliGL.glBindVertexArray(this.vao);
		}

		this.texture_id = Integer.parseInt(model_string_array[1]);
		this.shader_id = shader_id;
		this.state = (byte)(Byte.parseByte(model_string_array[4]) | 2 * Byte.parseByte(model_string_array[5]) | 4 * Byte.parseByte(model_string_array[6]) | 8 * Byte.parseByte(model_string_array[7]));//texture_state culling transparent glow

		this.memoa1_array = MemoA1.gen(memoa0_array, shader_string_2d_array, attriblocation_string_2d_array, model_string_array, folder_path/*, shader_string_2d_array*/, shader_id);

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
			memoa2.joint_int_array = (int[])memoa0_array[size - 2].o;
			memoa2.weight_float_array = (float[])memoa0_array[size - 1].o;
			memoa2.max_joint = Byte.parseByte(model_string_array[8]);

			A2_MAP.put(G_LIST.size(), memoa2);
		}
	}
}
