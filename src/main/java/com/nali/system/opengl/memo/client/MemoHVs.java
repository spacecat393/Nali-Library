package com.nali.system.opengl.memo.client;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.math.M4x4;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.nali.Nali.ID;

@SideOnly(Side.CLIENT)
public class MemoHVs extends MemoH
{
	public int bone;
	public float[] bind_pose_float_array;
	public byte[][] bone_2d_byte_array;

	public MemoHVs(String[] shader_string_array)
	{
		String folder_path = ID + "/" + shader_string_array[0] + "/shader/"/* + NaliConfig.GLSL + "/"*/;
		String model_folder_path = ID + "/" + shader_string_array[6] + "/model/" + shader_string_array[7] + "/";

		this.bind_pose_float_array = FileDataReader.getFloatArray(model_folder_path + "bindpose.bin");

		this.bone = this.bind_pose_float_array.length / 16;
		this.bone_2d_byte_array = new byte[this.bone][];
		String bone_folder_path = model_folder_path + "bone/";
		for (int i = 0; i < this.bone; ++i)
		{
			try
			{
				this.bone_2d_byte_array[i] = Files.readAllBytes(Paths.get(bone_folder_path + i + ".bin"));
			}
			catch (IOException e)
			{
				Nali.error(e);
			}
		}

		StringBuilder stringbuilder = new StringBuilder();
		byte[] byte_array = shader_string_array[3].getBytes();
		StringBuilder file_stringbuilder = new StringBuilder(folder_path).append("vert/s/").append(new String(byte_array, 1, byte_array.length - 1));
		StringReader.append(stringbuilder, file_stringbuilder + "0.vert");

		StringBuilder[] bone_stringbuilder_array = new StringBuilder[this.bone * 2];
		for (int i = 0; i < bone_stringbuilder_array.length; i++)
		{
			bone_stringbuilder_array[i] = new StringBuilder();
		}

		int bindpose_size = this.bind_pose_float_array.length / 16;

		for (int j = 0; j < bindpose_size; ++j)
		{
			int bindpose_index = (j + 1) * 16;
			for (int b = j * 16; b < bindpose_index; ++b)
			{
				bone_stringbuilder_array[j].append(this.bind_pose_float_array[b]);

				if (b < bindpose_index - 1)
				{
					bone_stringbuilder_array[j].append(", ");
				}
			}
		}

		for (int j = 0; j < bindpose_size; ++j)
		{
			M4x4.inverse(this.bind_pose_float_array, j * 16);
			int bindpose_index = (j + 1) * 16;
			for (int b = j * 16; b < bindpose_index; ++b)
			{
				bone_stringbuilder_array[j + this.bone].append(this.bind_pose_float_array[b]);

				if (b < bindpose_index - 1)
				{
					bone_stringbuilder_array[j + this.bone].append(", ");
				}
			}
		}

		stringbuilder.append("uniform mat4 frame[").append(this.bone).append("];\n");

		byte max_joint = Byte.parseByte(shader_string_array[8]);
		if (max_joint == 1)
		{
			stringbuilder.append(NaliConfig.ATTRIBUTE).append(" float joint;\n")
				.append(NaliConfig.ATTRIBUTE).append(" float weight;\n");
		}
		else
		{
			stringbuilder.append(NaliConfig.ATTRIBUTE).append(" vec").append(max_joint).append(" joint;\n")
				.append(NaliConfig.ATTRIBUTE).append(" vec").append(max_joint).append(" weight;\n");
		}

		StringReader.append(stringbuilder, file_stringbuilder + "1.vert");

		String w_string = "weight";
		String temp_v = "vertex_v4";
		String temp_n = "normal_v4";
		if (max_joint == 1)
		{
			stringbuilder.append("vec4 vertex_v4 = vec4(vertex, 1.0);\nvec4 normal_v4 = vec4(normal, 0.0);\nint j = int(joint);\n");
		}
		else
		{
			w_string = "w";
			temp_v = "temp_vertex_v4";
			temp_n = "temp_normal_v4";
			stringbuilder.append("vec4 vertex_v4 = vec4(0);\nvec4 normal_v4 = vec4(0);\nfor (int i = 0; i < ").append(max_joint).append("; ++i)\n{\nvec4 temp_vertex_v4 = vec4(vertex, 1.0);\nvec4 temp_normal_v4 = vec4(normal, 0.0);\nint j = 0;\nfloat w = 0.0;\n");
			if ((NaliConfig.STATE & 2) == 2)
			{
				stringbuilder.append("switch (i)\n{\n");
				for (int i = 0; i < max_joint; ++i)
				{
					stringbuilder.append("case ").append(i).append(":\nj = int(joint[").append(i).append("]);\nw = weight[").append(i).append("];\nbreak;\n");
				}
				stringbuilder.append("}\n");
			}
			else
			{
				for (int i = 0; i < max_joint; ++i)
				{
					String head = "else if";

					if (i == 0)
					{
						head = "if";
					}

					stringbuilder.append(head).append(" (i == ").append(i).append(")\n{\nj = int(joint[").append(i).append("]);\nw = weight[").append(i).append("];\n}\n");
				}
			}
		}

		if ((NaliConfig.STATE & 2) == 2)
		{
			//switch won't work with float and slow than if else
			stringbuilder.append("switch (j)\n{\n");
			for (int j = 0; j < this.bone_2d_byte_array.length; ++j)
			{
				byte[] bone_byte_array = this.bone_2d_byte_array[j];

				stringbuilder.append("case ").append(j).append(":\n");
				for (byte bone : bone_byte_array)
				{
					this.setSB(stringbuilder, temp_v, temp_n, bone, bone_stringbuilder_array);
				}
				stringbuilder.append("break;\n");
			}
			stringbuilder.append("}\n");
		}
		else
		{
			for (int j = 0; j < this.bone_2d_byte_array.length; ++j)
			{
				byte[] bone_byte_array = this.bone_2d_byte_array[j];

				String head = "else if";

				if (j == 0)
				{
					head = "if";
				}

				stringbuilder.append(head).append(" (j == ").append(j).append(")\n{\n");
				for (byte bone : bone_byte_array)
				{
					this.setSB(stringbuilder, temp_v, temp_n, bone, bone_stringbuilder_array);
				}
				stringbuilder.append("}\n");
			}
		}

		if (max_joint == 1)
		{
			stringbuilder.append("vertex_v4 *= ").append(w_string).append(";\nnormal_v4 *= ").append(w_string).append(";\n");
		}
		else
		{
			stringbuilder.append("temp_vertex_v4 *= ").append(w_string).append(";\ntemp_normal_v4 *= ").append(w_string).append(";\nvertex_v4 += temp_vertex_v4;\nnormal_v4 += temp_normal_v4;\n}\n");
		}

		StringReader.append(stringbuilder, file_stringbuilder + "2.vert");

		this.shader = genShader(getFrom(stringbuilder), OpenGlHelper.GL_VERTEX_SHADER, file_stringbuilder.toString());
	}

	public void setSB(StringBuilder stringbuilder, String temp_v, String temp_n, byte bone, StringBuilder[] bone_stringbuilder_array)
	{
		short new_bone = (short)(bone & 0xFF);
		stringbuilder.append(temp_v).append(" *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[new_bone]);
		stringbuilder.append(");\n");

		stringbuilder.append(temp_v).append(" *= frame[").append(new_bone).append("];\n");

		stringbuilder.append(temp_v).append(" *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[new_bone + this.bone]);
		stringbuilder.append(");\n");

		stringbuilder.append(temp_n).append(" *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[new_bone]);
		stringbuilder.append(");\n");

		stringbuilder.append(temp_n).append(" *= frame[").append(new_bone).append("];\n");

		stringbuilder.append(temp_n).append(" *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[new_bone + this.bone]);
		stringbuilder.append(");\n");
	}
}
