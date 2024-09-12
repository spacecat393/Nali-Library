package com.nali.system.opengl.memo.client;

import com.nali.math.M4x4;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.Nali.ID;
import static com.nali.NaliConfig.SHADER;

@SideOnly(Side.CLIENT)
public class MemoHVs extends MemoH
{
//	public int max_bones;
	public int bone;
	public float[] bind_pose_float_array;
	public int[][] back_bone_2d_int_array;

	public MemoHVs(String[] shader_string_array)
	{
		String folder_path = ID + "/" + shader_string_array[0] + "/shader/" + SHADER.gl_shading_language_version + "/";

//		super(shader_string_array);
//	}
//
//	@Override
//	public StringBuilder get(String[] shader_string_array, String folder_path/*, byte shader_state*/)
//	{
//		folder_path = shader_string_array[4];
//		byte shader_state = Byte.parseByte(shader_string_array[1]);

//		String model_folder_path = folder_path + "/Models/" + shader_string_array[5];
//		String model_folder_path = Reference.MOD_ID + "/" + shader_string_array[4] + "/Model/" + shader_string_array[5];
//		String model_folder_path = ID + "/" + shader_string_array[5] + "/model/" + shader_string_array[6];
		String model_folder_path = ID + "/" + shader_string_array[6] + "/model/" + shader_string_array[7];
		String frame_string = "/frame/";

//		float[] bind_poses_float_array = FileDataReader.getFloatArray(model_folder_path + frame_string + "BindPoses");
		this.bind_pose_float_array = FileDataReader.getFloatArray(model_folder_path + frame_string + "bindpose.bin");

		this.bone = this.bind_pose_float_array.length / 16;
//		this.max_bones = this.bind_poses_float_array.length / 16;
		int[][] bone_2d_int_array = new int[this.bone][];
		for (int i = 0; i < this.bone; ++i)
		{
			bone_2d_int_array[i] = FileDataReader.getIntArray(model_folder_path + frame_string + "bone/" + i + ".bin");
		}

		StringBuilder stringbuilder = new StringBuilder();
//		this.vert_shader = stringbuilder;
//		StringReader.append(stringbuilder, folder_path + "/Shaders/" + GL_SHADING_LANGUAGE_VERSION + "/Vertex" + shader_state + 0);
//		String file_string = /*Reference.MOD_ID + "/" + */folder_path + "/Vertex" + shader_state;
		byte[] byte_array = shader_string_array[3].getBytes();
		String file_string = /*Reference.MOD_ID + "/" + */folder_path + "vert/s/" + new String(byte_array, 1, byte_array.length - 1);
		StringReader.append(stringbuilder, file_string + "0.vert");
//		stringbuilder.append(getVertexHeaderShaderString());

		StringBuilder[] bone_stringbuilder_array = new StringBuilder[this.bone * 2];
		for (int i = 0; i < bone_stringbuilder_array.length; i++)
		{
			bone_stringbuilder_array[i] = new StringBuilder();
		}
		int bindpose_size = this.bind_pose_float_array.length / 16;
//		stringbuilder.append("mat4 bindpose[" + bindpose_size + "] = mat4[](");
//
		for (int j = 0; j < bindpose_size; ++j)
		{
//			stringbuilder.append("mat4(");

			int bindpose_index = (j + 1) * 16;
			for (int b = j * 16; b < bindpose_index; ++b)
			{
//				stringbuilder.append(String.valueOf(this.bind_pose_float_array[b]));
				bone_stringbuilder_array[j].append(String.valueOf(this.bind_pose_float_array[b]));

				if (b < bindpose_index - 1)
				{
//					stringbuilder.append(", ");
					bone_stringbuilder_array[j].append(", ");
				}
			}

//			stringbuilder.append(")");

//			if (j < bindpose_size - 1)
//			{
//				stringbuilder.append(", ");
//			}
		}

//		stringbuilder.append(");\nmat4 invbindpose[" + bindpose_size + "] = mat4[](");
//
		for (int j = 0; j < bindpose_size; ++j)
		{
//			stringbuilder.append("mat4(");
//
			M4x4.inverse(this.bind_pose_float_array, j * 16);
			int bindpose_index = (j + 1) * 16;
			for (int b = j * 16; b < bindpose_index; ++b)
			{
//				stringbuilder.append(String.valueOf(this.bind_pose_float_array[b]));
				bone_stringbuilder_array[j + this.bone].append(String.valueOf(this.bind_pose_float_array[b]));

				if (b < bindpose_index - 1)
				{
//					stringbuilder.append(", ");
					bone_stringbuilder_array[j + this.bone].append(", ");
				}
			}
//
//			stringbuilder.append(")");
//
//			if (j < bindpose_size - 1)
//			{
//				stringbuilder.append(", ");
//			}
		}
//
//		stringbuilder.append(");\n");

//		for (int i = 0; i < max_bones; ++i)
//		{
//			stringbuilder.append("uniform mat4 frame").append(StringReader.convertNumberToLetter(i + ";\n");
//		}
		stringbuilder.append("uniform mat4 frame[" + this.bone + "];\n");

		//
		byte max_joint = Byte.parseByte(shader_string_array[8]);
		if (max_joint == 1)
		{
//			stringbuilder.append(SHADER.attribute + " int joint;\n" + SHADER.attribute + " float weight;\n");
			stringbuilder.append(SHADER.attribute + " float joint;\n" + SHADER.attribute + " float weight;\n");
		}
		else
		{
//			stringbuilder.append(SHADER.attribute + " ivec" + max_joint + " joint;\n" + SHADER.attribute + " vec" + max_joint + " weight;\n");
			stringbuilder.append(SHADER.attribute + " vec" + max_joint + " joint;\n" + SHADER.attribute + " vec" + max_joint + " weight;\n");
		}
		//

		StringReader.append(stringbuilder, file_string + "1.vert");

		//
//		if (max_joint == 1)
//		{
//			stringbuilder.append("j = joint;\nw = weight;\n}\n");
//		}
//		else
//		String j_string = "joint";
		String w_string = "weight";
		String temp_v = "vertex_v4";
		String temp_n = "normal_v4";
		if (max_joint == 1)
		{
			stringbuilder.append("vec4 vertex_v4 = vec4(vertex, 1.0);\nvec4 normal_v4 = vec4(normal, 0.0);\nint j = int(joint);\n");
		}
		else
		{
//			j_string = "j";
			w_string = "w";
			temp_v = "temp_vertex_v4";
			temp_n = "temp_normal_v4";
			stringbuilder.append("vec4 vertex_v4 = vec4(0);\nvec4 normal_v4 = vec4(0);\nfor (int i = 0; i < " + max_joint + "; ++i)\n{\nvec4 temp_vertex_v4 = vec4(vertex, 1.0);\nvec4 temp_normal_v4 = vec4(normal, 0.0);\nint j = 0;\nfloat w = 0.0;\n");
			if (SHADER.use_switch)
			{
				stringbuilder.append("switch (i)\n{\n");
				for (int i = 0; i < max_joint; ++i)
				{
					stringbuilder.append("case " + i + ":\nj = int(joint[" + i + "]);\nw = weight[" + i + "];\nbreak;\n");
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

					stringbuilder.append(head + " (i == " + i + ")\n{\nj = int(joint[" + i + "]);\nw = weight[" + i + "];\n}\n");
				}
			}
		}
		//

//		int[][] back_bones_2d_int_array = new int[this.max_bones][];
		this.back_bone_2d_int_array = new int[this.bone][];

		for (int j = 0; j < this.bone; ++j)
		{
			int[] bone_int_array = bone_2d_int_array[j];
			this.back_bone_2d_int_array[j] = new int[bone_int_array.length];

			int b_index = 0;
			for (int b = bone_int_array.length - 1; b > -1; --b)
			{
				int[] b_bones = this.back_bone_2d_int_array[j];
				b_bones[b_index++] = bone_int_array[b];
			}
		}

		if (SHADER.use_switch)
		{
			stringbuilder.append("switch (j)\n{\n");
			for (int j = 0; j < bone_2d_int_array.length; ++j)
			{
				int[] back_bone_int_array = this.back_bone_2d_int_array[j];

				stringbuilder.append("case " + j + ":\n");
				for (int bone : back_bone_int_array)
				{
//					stringbuilder.append(temp_v + " *= bindpose[" + bone + "];\n");
//					stringbuilder.append(temp_v + " *= frame[" + bone + "];\n");
//					stringbuilder.append(temp_v + " *= invbindpose[" + bone + "];\n");
//					stringbuilder.append(temp_n + " *= bindpose[" + bone + "];\n");
//					stringbuilder.append(temp_n + " *= frame[" + bone + "];\n");
//					stringbuilder.append(temp_n + " *= invbindpose[" + bone + "];\n");
					this.setSB(stringbuilder, temp_v, temp_n, bone, bone_stringbuilder_array);
				}
				stringbuilder.append("break;\n");
			}
			stringbuilder.append("}\n");
		}
		else
		{
			for (int j = 0; j < bone_2d_int_array.length; ++j)
			{
				int[] back_bone_int_array = this.back_bone_2d_int_array[j];

				String head = "else if";

				if (j == 0)
				{
					head = "if";
				}

				stringbuilder.append(head + " (j == " + j + ")\n{\n");
	//			stringbuilder.append(head).append(" (" + j_string + " == " + j + ")\n{\n");
				for (int bone : back_bone_int_array)
				{
//					stringbuilder.append(temp_v + " *= bindpose[" + bone + "];\n");
//	//				stringbuilder.append("temp_vertex_v4 *= frame").append(StringReader.convertNumberToLetter(bone + ";\n");
//					stringbuilder.append(temp_v + " *= frame[" + bone + "];\n");
//					stringbuilder.append(temp_v + " *= invbindpose[" + bone + "];\n");
//					stringbuilder.append(temp_n + " *= bindpose[" + bone + "];\n");
//	//				stringbuilder.append("temp_normal_v4 *= frame").append(StringReader.convertNumberToLetter(bone + ";\n");
//					stringbuilder.append(temp_n + " *= frame[" + bone + "];\n");
//					stringbuilder.append(temp_n + " *= invbindpose[" + bone + "];\n");
//	//				stringbuilder.append("temp_normal_v4 *= temp_vertex_v4;\n");
					this.setSB(stringbuilder, temp_v, temp_n, bone, bone_stringbuilder_array);
				}
				stringbuilder.append("}\n");
			}
		}

		if (max_joint == 1)
		{
			stringbuilder.append("vertex_v4 *= " + w_string + ";\nnormal_v4 *= " + w_string + ";\n");
		}
		else
		{
//			stringbuilder.append("temp_vertex_v4 *= w;\ntemp_normal_v4 *= w;\nvertex_v4 += temp_vertex_v4;\nnormal_v4 += temp_normal_v4;\n}\n");
			stringbuilder.append("temp_vertex_v4 *= " + w_string + ";\ntemp_normal_v4 *= " + w_string + ";\nvertex_v4 += temp_vertex_v4;\nnormal_v4 += temp_normal_v4;\n}\n");
		}

		StringReader.append(stringbuilder, file_string + "2.vert");

		this.shader = genShader(getFrom(stringbuilder), OpenGlHelper.GL_VERTEX_SHADER, file_string);
	}

	public void setSB(StringBuilder stringbuilder, String temp_v, String temp_n, int bone, StringBuilder[] bone_stringbuilder_array)
	{
		stringbuilder.append(temp_v + " *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[bone]);
		stringbuilder.append(");");

		stringbuilder.append(temp_v + " *= frame[" + bone + "];\n");

		stringbuilder.append(temp_v + " *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[bone + this.bone]);
		stringbuilder.append(");");
		//

		stringbuilder.append(temp_n + " *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[bone]);
		stringbuilder.append(");");

		stringbuilder.append(temp_n + " *= frame[" + bone + "];\n");

		stringbuilder.append(temp_n + " *= mat4(");
		stringbuilder.append(bone_stringbuilder_array[bone + this.bone]);
		stringbuilder.append(");");
		//

//		stringbuilder.append(temp_v + " *= bindpose[" + bone + "];\n");
//		stringbuilder.append(temp_v + " *= frame[" + bone + "];\n");
//		stringbuilder.append(temp_v + " *= invbindpose[" + bone + "];\n");
//		stringbuilder.append(temp_n + " *= bindpose[" + bone + "];\n");
//		stringbuilder.append(temp_n + " *= frame[" + bone + "];\n");
//		stringbuilder.append(temp_n + " *= invbindpose[" + bone + "];\n");
	}
}
