package com.nali.system.opengl.memo.client;

import com.nali.Nali;
import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.file.Paths;
import java.util.Arrays;

import static com.nali.Nali.ID;

@SideOnly(Side.CLIENT)
public class MemoS
{
	public int program;
	public int[] attriblocation_int_array;
	public int[] uniformlocation_int_array;

	public MemoS(int vert_shader, int frag_shader, String[] shader_string_array)
	{
		this.createBuffer(shader_string_array, vert_shader, frag_shader);
	}

	public void createBuffer(String[] shader_string_array, int vert_shader, int frag_shader)
	{
		String folder_path = ID + "/" + shader_string_array[0];
		String model_folder_string = folder_path + "/shader/" + shader_string_array[1] + "/";
		this.createShaderBuffer(shader_string_array, vert_shader, frag_shader);
		String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(model_folder_string + "attrib.dat"));
		this.attriblocation_int_array = new int[attriblocation_string_2d_array.length];
		for (int i = 0; i < attriblocation_string_2d_array.length; ++i)
		{
			this.attriblocation_int_array[i] = OpenGlHelper.glGetAttribLocation(this.program, attriblocation_string_2d_array[i][0]);
		}

		String[][] uniform_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(model_folder_string + "uniform.dat"));
		this.uniformlocation_int_array = new int[uniform_string_2d_array.length];
		for (int i = 0; i < uniform_string_2d_array.length; ++i)
		{
			this.uniformlocation_int_array[i] = OpenGlHelper.glGetUniformLocation(this.program, uniform_string_2d_array[i][0]);
		}
	}

	public void createShaderBuffer(String[] error_string_array, int vert_shader, int frag_shader)
	{
		this.program = OpenGlHelper.glCreateProgram();

		OpenGlHelper.glAttachShader(this.program, vert_shader);
		OpenGlHelper.glAttachShader(this.program, frag_shader);

		OpenGlHelper.glLinkProgram(this.program);
		GL20.glValidateProgram(this.program);

		if (OpenGlHelper.glGetProgrami(this.program, OpenGlHelper.GL_LINK_STATUS) == GL11.GL_FALSE)
		{
			Nali.warn("F " + Arrays.toString(error_string_array));
			Nali.error(OpenGlHelper.glGetProgramInfoLog(this.program, 32768));
		}

//		//reset memory
//		OpenGlHelper.glDeleteProgram(this.program);
//		this.program = OpenGlHelper.glCreateProgram();
//		OpenGlHelper.glAttachShader(this.program, vert_shader);
//		OpenGlHelper.glAttachShader(this.program, frag_shader);
//		OpenGlHelper.glLinkProgram(this.program);
	}
}
