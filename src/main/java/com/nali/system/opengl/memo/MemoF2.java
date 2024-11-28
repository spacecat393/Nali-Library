package com.nali.system.opengl.memo;

import com.nali.Nali;
import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.system.BothLoader;
import com.nali.system.file.FileDataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.nali.math.M4x4.multiplyVec4Mat4;

public class MemoF2
{
	public int
		bone,
		length;

	public float[] bind_pose_float_array;
//	public int[][] back_bone_2d_int_array;
	public short[][] back_bone_2d_short_array;

	public float[] transforms_float_array;

	public MemoF2(String[] frame_string_array, String folder_path)
	{
		String frame_folder_path = folder_path + "/model/" + frame_string_array[0]/* + "/frame/"*/;

		this.bind_pose_float_array = FileDataReader.getFloatArray(frame_folder_path + "bindpose.bin");

		this.bone = this.bind_pose_float_array.length / 16;
//		int[][] bone_2d_int_array = new int[bone][];
		short[][] bone_2d_short_array = new short[this.bone][];

		String bone_folder_path = frame_folder_path + "bone/";
		for (int i = 0; i < this.bone; ++i)
		{
//			bone_2d_int_array[i] = FileDataReader.getIntArray(bone_folder_path + i + ".bin");
			try
			{
				byte[] bone_byte_array = Files.readAllBytes(Paths.get(bone_folder_path + i + ".bin"));
				int bone_byte_length = bone_byte_array.length;
				bone_2d_short_array[i] = new short[bone_byte_length];
				for (int l = 0; l < bone_byte_length; ++l)
				{
//					byte bone_byte = bone_byte_array[l];
//					bone_2d_short_array[i][l] = (short)(bone_byte & 0xFF);
					bone_2d_short_array[i][l] = (short)(bone_byte_array[l] & 0xFF);
				}
			}
			catch (IOException e)
			{
				Nali.error(e);
			}
		}
////		this.back_bone_2d_int_array = new int[bone][];
//		this.back_bone_2d_short_array = new short[this.bone][];
//		for (int j = 0; j < this.bone; ++j)
//		{
////			int[] bone_int_array = bone_2d_int_array[j];
//			short[] bone_short_array = bone_2d_short_array[j];
////			this.back_bone_2d_int_array[j] = new int[bone_int_array.length];
//			this.back_bone_2d_short_array[j] = new short[bone_short_array.length];
//
//			int b_index = 0;
////			for (int b = bone_int_array.length - 1; b > -1; --b)
//			for (int b = bone_short_array.length - 1; b > -1; --b)
//			{
////				int[] b_bones = this.back_bone_2d_int_array[j];
////				b_bones[b_index++] = bone_int_array[b];
//				this.back_bone_2d_short_array[j][b_index++] = bone_short_array[b];
//			}
//		}
		this.back_bone_2d_short_array = bone_2d_short_array;

		this.transforms_float_array = FileDataReader.getFloatArray(frame_folder_path + "transform.bin");
		this.length = this.transforms_float_array.length / 16 / this.bone;
	}

//	public MemoF2(int bone, float[] bind_pose_float_array, int[][] back_bone_2d_int_array, String[] frame_string_array, String folder_path)
	public MemoF2(int bone, float[] bind_pose_float_array, short[][] back_bone_2d_short_array, String[] frame_string_array, String folder_path)
	{
		this.bone = bone;
//		this.transforms_float_array = FileDataReader.getFloatArray(folder_path + "/model/" + frame_string_array[0] + "/frame/transform.bin");
		this.transforms_float_array = FileDataReader.getFloatArray(folder_path + "/model/" + frame_string_array[0] + "/transform.bin");
		this.length = this.transforms_float_array.length / 16 / this.bone;

//		this.bind_pose_float_array = FileDataReader.getFloatArray(folder_path + "/model/" + frame_string_array[0] + "/frame/bindpose.bin");
		//inv
		this.bind_pose_float_array = bind_pose_float_array;
//		this.back_bone_2d_int_array = back_bone_2d_int_array;
		this.back_bone_2d_short_array = back_bone_2d_short_array;
	}

	public float[] get3DSkinning(float[] skinning_float_array, float x, float y, float z, float x0, float y0, float z0, int i, int v)
	{
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.openglobjectmemory_array[i];
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.object_array[i];
//		MemoG rg = G_LIST.get(i);
//		MemoF rf = F_LIST.get(this.rc.FrameID());
		MemoA2 ra2 = BothLoader.A2_MAP.get(i);

		int[] index_int_array = ra2.index_int_array;
		float[] vertex_float_array = ra2.vertex_float_array;
//		int[][] back_bones_2d_int_array = this.back_bone_2d_int_array;
		short[][] back_bones_2d_short_array = this.back_bone_2d_short_array;

		int vi = index_int_array[v] * 3;

		byte max_joint = ra2.max_joint;
		float[] main_vec4_float_array = new float[4];
		float[] temp_vec4_float_array = new float[4];

		for (int j = 0; j < max_joint; ++j)
		{
			int ji = index_int_array[v] * max_joint + j;
//			int joint = ra2.joint_int_array[ji];
			short joint = ra2.joint_short_array[ji];

			if (joint != -1)
			{
				temp_vec4_float_array[0] = vertex_float_array[vi] + x0;
				temp_vec4_float_array[1] = vertex_float_array[vi + 1] + y0;
				temp_vec4_float_array[2] = vertex_float_array[vi + 2] + z0;
				temp_vec4_float_array[3] = 1.0F;

//				OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
//				OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)this.dataloader.openglobjectshadermemory_array[openglskinningmemory.shader_id];
//				MemoS rs = S_LIST.get(this.getShaderID(rg)/*openglskinningmemory.shader_id*/);

//				for (int b = 0; b < back_bones_2d_int_array[joint].length; ++b)
				for (int b = 0; b < back_bones_2d_short_array[joint].length; ++b)
				{
//					int index = back_bones_2d_int_array[joint][b] * 16;
					int index = back_bones_2d_short_array[joint][b] * 16;
					float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
					System.arraycopy(this.bind_pose_float_array, index, bindpose_mat4, 0, 16);
					System.arraycopy(skinning_float_array, index, skinning_mat4, 0, 16);

					M4x4.inverse(bindpose_mat4, 0);
					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

					M4x4.inverse(bindpose_mat4, 0);
					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
				}

				float weights = ra2.weight_float_array[ji];

				temp_vec4_float_array[0] *= weights;
				temp_vec4_float_array[1] *= weights;
				temp_vec4_float_array[2] *= weights;
				temp_vec4_float_array[3] *= weights;

				main_vec4_float_array[0] += temp_vec4_float_array[0];
				main_vec4_float_array[1] += temp_vec4_float_array[1];
				main_vec4_float_array[2] += temp_vec4_float_array[2];
				main_vec4_float_array[3] += temp_vec4_float_array[3];
			}
		}

		main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
		main_vec4_float_array[0] += x;
		main_vec4_float_array[1] += y;
		main_vec4_float_array[2] += z;

		return main_vec4_float_array;
	}

	public float[] getScale3DSkinning(float scale, float[] skinning_float_array, float x, float y, float z, float x0, float y0, float z0, int i, int v)
	{
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.object_array[i];
//		RG rg = this.rst.rg_list.get(i);
//		MemoF rf = F_LIST.get(this.rc.FrameID());
		MemoA2 ra2 = BothLoader.A2_MAP.get(i);

		int[] index_int_array = ra2.index_int_array;
		float[] vertex_float_array = ra2.vertex_float_array;
//		int[][] back_bones_2d_int_array = this.back_bone_2d_int_array;
		short[][] back_bones_2d_short_array = this.back_bone_2d_short_array;

		int vi = index_int_array[v] * 3;

		byte max_joint = ra2.max_joint;
		float[] main_vec4_float_array = new float[4];
		float[] temp_vec4_float_array = new float[4];

		for (int j = 0; j < max_joint; ++j)
		{
			int ji = index_int_array[v] * max_joint + j;
//			int joint = ra2.joint_int_array[ji];
			short joint = ra2.joint_short_array[ji];

			if (joint != -1)
			{
				temp_vec4_float_array[0] = vertex_float_array[vi] + x0;
				temp_vec4_float_array[1] = vertex_float_array[vi + 1] + y0;
				temp_vec4_float_array[2] = vertex_float_array[vi + 2] + z0;
				temp_vec4_float_array[3] = 1.0F;

//				OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
//				OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)this.dataloader.openglobjectshadermemory_array[openglskinningmemory.shader_id];
//				RS rs = this.rst.rs_list.get(this.getShaderID(rg)/*openglskinningmemory.shader_id*/);

//				for (int b = 0; b < back_bones_2d_int_array[joint].length; ++b)
				for (int b = 0; b < back_bones_2d_short_array[joint].length; ++b)
				{
//					int index = back_bones_2d_int_array[joint][b] * 16;
					int index = back_bones_2d_short_array[joint][b] * 16;
					float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
					System.arraycopy(this.bind_pose_float_array, index, bindpose_mat4, 0, 16);
					System.arraycopy(skinning_float_array, index, skinning_mat4, 0, 16);

					M4x4.inverse(bindpose_mat4, 0);
					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

					M4x4.inverse(bindpose_mat4, 0);
					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
				}

				float weights = ra2.weight_float_array[ji];

				temp_vec4_float_array[0] *= weights;
				temp_vec4_float_array[1] *= weights;
				temp_vec4_float_array[2] *= weights;
				temp_vec4_float_array[3] *= weights;

				main_vec4_float_array[0] += temp_vec4_float_array[0];
				main_vec4_float_array[1] += temp_vec4_float_array[1];
				main_vec4_float_array[2] += temp_vec4_float_array[2];
				main_vec4_float_array[3] += temp_vec4_float_array[3];
			}
		}

		main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new float[]
		{
			scale, 0.0F, 0.0F, 0.0F,
			0.0F, scale, 0.0F, 0.0F,
			0.0F, 0.0F, scale, 0.0F,
			0.0F, 0.0F, 0.0F, 1.0F,
		});
		main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
		main_vec4_float_array[0] += x;
		main_vec4_float_array[1] += y;
		main_vec4_float_array[2] += z;
//		main_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);

		return main_vec4_float_array;
	}

	public float[] getMat43DSkinning(float[] skinning_float_array, int i, int v)
	{
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.openglobjectmemory_array[i];
//		OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.object_array[i];
//		RG rg = this.rst.rg_list.get(i);
//		MemoF rf = F_LIST.get(this.rc.FrameID());
		MemoA2 ra2 = BothLoader.A2_MAP.get(i);

//		int[][] back_bones_2d_int_array = this.back_bone_2d_int_array;
		short[][] back_bones_2d_short_array = this.back_bone_2d_short_array;

		byte max_joint = ra2.max_joint;
		float[] mat4_float_array = new float[16];
		System.arraycopy(M4x4.IDENTITY, 0, mat4_float_array, 0, 16);

//		for (int j = 0; j < 1; ++j)
//		{
		int ji = ra2.index_int_array[v] * max_joint;// + j;
//		int joint = ra2.joint_int_array[ji];
		short joint = ra2.joint_short_array[ji];

		if (joint != -1)
		{
//			OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
//			OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)this.dataloader.openglobjectshadermemory_array[openglskinningmemory.shader_id];
//			RS rs = this.rst.rs_list.get(this.getShaderID(rg)/*openglskinningmemory.shader_id*/);

//			for (int b = 0; b < back_bones_2d_int_array[joint].length; ++b)
			for (int b = 0; b < back_bones_2d_short_array[joint].length; ++b)
			{
//				M4x4.multiply(skinning_float_array, mat4_float_array, back_bones_2d_int_array[joint][b] * 16, 0);
				M4x4.multiply(skinning_float_array, mat4_float_array, back_bones_2d_short_array[joint][b] * 16, 0);
			}
		}
//		}

//		M4x4.multiply(new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat, mat4_float_array, 0, 0);
		return mat4_float_array;
	}

//	public void apply3DSkinningVec4(float[] vec4)
//	{
//		GL11.glTranslatef(vec4[0] / vec4[3], vec4[1] / vec4[3], vec4[2] / vec4[3]);
//	}
}
