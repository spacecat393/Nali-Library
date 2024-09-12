//package com.nali.system.opengl.memo;
//
//import com.nali.Nali;
//import com.nali.math.M4x4;
//import com.nali.math.Quaternion;
//import com.nali.system.file.FileDataReader;
//
//import static com.nali.Nali.I;
//import static com.nali.math.M4x4.multiplyVec4Mat4;
//
//public class Memo3DS
//{
//	public int max_bones;
//	public float[] bind_poses_float_array;
//	public int[][] back_bones_2d_int_array;
//
//	public int[] index_int_array;
//	public float[] vertices_float_array;
//	public byte max_joints;
//	public float[] joints_float_array;
//	public float[] weights_float_array;
//
//	public Memo3DS(String[] model_string_array, String[] shader_string_array, String folder_path)
//	{
//		String model_folder_string = folder_path + "/Model/" + model_string_array[0] + '/';
//		String model_folder_path = Nali.ID + "/" + shader_string_array[5] + "/Model/" + shader_string_array[6];
//		String animation_string = "/Animation/";
//
//		this.bind_poses_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "BindPoses");
//		this.max_bones = this.bind_poses_float_array.length / 16;
//		int[][] bones_2d_int_array = new int[max_bones][];
//		for (int i = 0; i < this.max_bones; ++i)
//		{
//			bones_2d_int_array[i] = FileDataReader.getIntArray(model_folder_path + animation_string + "Bones/" + i);
//		}
//		this.back_bones_2d_int_array = new int[this.max_bones][];
//
//		for (int j = 0; j < this.max_bones; ++j)
//		{
//			int[] bones = bones_2d_int_array[j];
//			this.back_bones_2d_int_array[j] = new int[bones.length];
//
//			int b_index = 0;
//			for (int b = bones.length - 1; b > -1; --b)
//			{
//				int[] b_bones = this.back_bones_2d_int_array[j];
//				b_bones[b_index++] = bones[b];
//			}
//		}
//
//		this.index_int_array = FileDataReader.getIntArray(model_folder_string + "/Index");
//		this.vertices_float_array = FileDataReader.getFloatArray(model_folder_string + "/Vertices");
//		this.max_joints = Byte.parseByte(model_string_array[7]);
//		this.joints_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
//		this.weights_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");
//	}
//
//	public void initSkinning(MemoAnimation memoanimation)
//	{
//		int max_bones = memoanimation.bones;
//
//		for (int i = 0; i < max_bones; ++i)
//		{
//			System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
//		}
//	}
//
//	public void setSkinning(MemoAnimation memoanimation)
//	{
//		int max_key = memoanimation.length;
//
//		for (int i = 0; i < memoanimation.bones; ++i)
//		{
//			for (int f = 0; f < this.frame_int_array.length; ++f)
//			{
//				if ((this.frame_byte_array[f / 8] >> f % 8 & 1) == 1)
//				{
//					M4x4.multiply(memoanimation.transforms_float_array, this.skinning_float_array, (this.frame_int_array[f] + max_key * i) * 16, i * 16);
//				}
//			}
//
//			M4x4.inverse(this.skinning_float_array, i * 16);
//		}
//	}
//
//	public float[] getScale3DSkinning(float[] skinning_float_array, float scale, float x, float y, float z, float x0, float y0, float z0, int i, int v)
//	{
//		Memo3DS memo3ds = I.bothloader.memo3d_list.get(i);
//		int vi = memo3ds.index_int_array[v] * 3;
//
//		float[] main_vec4_float_array = new float[4];
//		float[] temp_vec4_float_array = new float[4];
//
//		for (int j = 0; j < this.max_joints; ++j)
//		{
//			int ji = memo3ds.index_int_array[v] * this.max_joints + j;
//			int joints = (int)this.joints_float_array[ji];
//
//			if (joints != -1)
//			{
//				temp_vec4_float_array[0] = memo3ds.vertices_float_array[vi] + x0;
//				temp_vec4_float_array[1] = memo3ds.vertices_float_array[vi + 1] + y0;
//				temp_vec4_float_array[2] = memo3ds.vertices_float_array[vi + 2] + z0;
//				temp_vec4_float_array[3] = 1.0F;
//
//				for (int b = 0; b < this.back_bones_2d_int_array[joints].length; ++b)
//				{
//					int index = this.back_bones_2d_int_array[joints][b] * 16;
//					float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
//					System.arraycopy(this.bind_poses_float_array, index, bindpose_mat4, 0, 16);
//					System.arraycopy(skinning_float_array, index, skinning_mat4, 0, 16);
//
//					M4x4.inverse(bindpose_mat4, 0);
//					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
//
//					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);
//
//					M4x4.inverse(bindpose_mat4, 0);
//					temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
//				}
//
//				float weights = this.weights_float_array[ji];
//
//				temp_vec4_float_array[0] *= weights;
//				temp_vec4_float_array[1] *= weights;
//				temp_vec4_float_array[2] *= weights;
//				temp_vec4_float_array[3] *= weights;
//
//				main_vec4_float_array[0] += temp_vec4_float_array[0];
//				main_vec4_float_array[1] += temp_vec4_float_array[1];
//				main_vec4_float_array[2] += temp_vec4_float_array[2];
//				main_vec4_float_array[3] += temp_vec4_float_array[3];
//			}
//		}
//
//		main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new float[]
//		{
//			scale, 0.0F, 0.0F, 0.0F,
//			0.0F, scale, 0.0F, 0.0F,
//			0.0F, 0.0F, scale, 0.0F,
//			0.0F, 0.0F, 0.0F, 1.0F,
//		});
//		main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
//		main_vec4_float_array[0] += x;
//		main_vec4_float_array[1] += y;
//		main_vec4_float_array[2] += z;
////		main_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
//
//		return main_vec4_float_array;
//	}
//}
