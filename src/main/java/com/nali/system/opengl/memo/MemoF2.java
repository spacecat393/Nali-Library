package com.nali.system.opengl.memo;

import com.nali.Nali;
import com.nali.da.IBothDaS;
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
	public int bone;
	public short max_key;

	//inv bindpose
	public float[] bind_pose_float_array;
	public byte[][] bone_2d_byte_array;

	public float[] transforms_float_array;

	public MemoF2(String[] frame_string_array, String folder_path)
	{
		String frame_folder_path = folder_path + "/model/" + frame_string_array[0]/* + "/frame/"*/;

		this.bind_pose_float_array = FileDataReader.getFloatArray(frame_folder_path + "bindpose.bin");

		this.bone = this.bind_pose_float_array.length / 16;

		this.bone_2d_byte_array = new byte[this.bone][];

		String bone_folder_path = frame_folder_path + "bone/";
		for (int i = 0; i < this.bone; ++i)
		{
			M4x4.inverse(this.bind_pose_float_array, i * 16);
			try
			{
				this.bone_2d_byte_array[i] = Files.readAllBytes(Paths.get(bone_folder_path + i + ".bin"));
			}
			catch (IOException e)
			{
				Nali.error(e);
			}
		}

		this.transforms_float_array = FileDataReader.getFloatArray(frame_folder_path + "transform.bin");
		this.max_key = (short)(this.transforms_float_array.length / 16 / this.bone);
	}

	public MemoF2(int bone, float[] bind_pose_float_array, byte[][] bone_2d_byte_array, String[] frame_string_array, String folder_path)
	{
		this.bone = bone;
		this.transforms_float_array = FileDataReader.getFloatArray(folder_path + "/model/" + frame_string_array[0] + "/transform.bin");
		this.max_key = (short)(this.transforms_float_array.length / 16 / this.bone);

		this.bind_pose_float_array = bind_pose_float_array;
		this.bone_2d_byte_array = bone_2d_byte_array;
	}

	public float[] get3DSkinning(float[] skinning_float_array, float x, float y, float z, float x0, float y0, float z0, int i, int v)
	{
		MemoA2 ra2 = BothLoader.A2_MAP.get(i);

		int[] index_int_array = ra2.index_int_array;
		float[] vertex_float_array = ra2.vertex_float_array;

		int vi = index_int_array[v] * 3;

		byte max_joint = ra2.max_joint;
		float[] main_vec4_float_array = new float[4];
		float[] temp_vec4_float_array = new float[4];

		for (int j = 0; j < max_joint; ++j)
		{
			int ji = index_int_array[v] * max_joint + j;
			short joint = ra2.joint_short_array[ji];

			if (joint != -1)
			{
				temp_vec4_float_array[0] = vertex_float_array[vi] + x0;
				temp_vec4_float_array[1] = vertex_float_array[vi + 1] + y0;
				temp_vec4_float_array[2] = vertex_float_array[vi + 2] + z0;
				temp_vec4_float_array[3] = 1.0F;

				for (int b = 0; b < this.bone_2d_byte_array[joint].length; ++b)
				{
					int index = (this.bone_2d_byte_array[joint][b] & 0xFF) * 16;
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
		MemoA2 ra2 = BothLoader.A2_MAP.get(i);

		int[] index_int_array = ra2.index_int_array;
		float[] vertex_float_array = ra2.vertex_float_array;

		int vi = index_int_array[v] * 3;

		byte max_joint = ra2.max_joint;
		float[] main_vec4_float_array = new float[4];
		float[] temp_vec4_float_array = new float[4];

		for (int j = 0; j < max_joint; ++j)
		{
			int ji = index_int_array[v] * max_joint + j;
			short joint = ra2.joint_short_array[ji];

			if (joint != -1)
			{
				temp_vec4_float_array[0] = vertex_float_array[vi] + x0;
				temp_vec4_float_array[1] = vertex_float_array[vi + 1] + y0;
				temp_vec4_float_array[2] = vertex_float_array[vi + 2] + z0;
				temp_vec4_float_array[3] = 1.0F;

				for (int b = 0; b < this.bone_2d_byte_array[joint].length; ++b)
				{
					int index = (this.bone_2d_byte_array[joint][b] & 0xFF) * 16;
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
		MemoA2 ra2 = BothLoader.A2_MAP.get(i);

		byte max_joint = ra2.max_joint;
		float[] mat4_float_array = new float[16];
		System.arraycopy(M4x4.IDENTITY, 0, mat4_float_array, 0, 16);

		int ji = ra2.index_int_array[v] * max_joint;// + j;
		short joint = ra2.joint_short_array[ji];

		if (joint != -1)
		{
			for (int b = 0; b < this.bone_2d_byte_array[joint].length; ++b)
			{
				M4x4.multiply(skinning_float_array, mat4_float_array, (this.bone_2d_byte_array[joint][b] & 0xFF) * 16, 0);
			}
		}
		return mat4_float_array;
	}

	public void initSkinning(IBothDaS bd, float[] skinning_float_array)
	{
		int max_bones = BothLoader.F2_LIST.get(bd.S_FrameID()).bone;

		for (int i = 0; i < max_bones; ++i)
		{
			System.arraycopy(M4x4.IDENTITY, 0, skinning_float_array, i * 16, 16);
		}
	}

	public void setSkinning(IBothDaS bd, float[] skinning_float_array, short[] key_short_array)
	{
		int frame_id = bd.S_FrameID();
		MemoF2 bf2 = BothLoader.F2_LIST.get(frame_id);
		int max_frame = bd.S_MaxFrame();
		short max_key = bf2.max_key;
//		short[] key_short_array = bd.S_KeyShortArray();
//		byte fps = bd.S_FPS();

//		for (int i = 0; i < max_frame; ++i)
//		{
//			int index = i * 2;
//			//to this
//			this.frame_float_array[index] = (this.time_short_array[index] & 0xFFFF) / ((float)Short.MAX_VALUE / key_short_array[this.key_index_byte_array[index] + 2]);
//			++index;
//			this.frame_float_array[index] = (this.time_short_array[index] & 0xFFFF) / ((float)Short.MAX_VALUE / key_short_array[this.key_index_byte_array[index] + 2]);
//		}

		for (int i = 0; i < bf2.bone; ++i)
		{
			for (int l = 0; l < max_frame; ++l)
			{
//				if ((this.frame_byte_array[l / 8] >> l % 8 & 1) == 1)
//				{
				//clean
//					int index = l * 2;
//					float start_frame = this.frame_float_array[index];
//					float end_frame = this.frame_float_array[index + 1];
//					float new_start_frame = start_frame - (int)start_frame;
//					float new_end_frame = end_frame - (int)end_frame;
////					int start_key_frame = (int)Math.ceil(start_frame);
////					int end_key_frame = (int)Math.ceil(end_frame);
//					int start_key_frame;
//					int end_key_frame;
////					short min_start_key = key_short_array[this.key_index_byte_array[index]];
////					short max_start_key = key_short_array[this.key_index_byte_array[index] + 2];
////
////					short min_end_key = key_short_array[this.key_index_byte_array[index + 1]];
////					short max_end_key = key_short_array[this.key_index_byte_array[index + 1] + 2];
////					start_key_frame += min_start_key;
////					end_key_frame += min_end_key;
//
//					if (start_frame < end_frame)
//					{
//						start_key_frame = max_start_key;
//						end_key_frame = ;
//					}
//					else if (start_frame > end_frame)
//					{
//						start_key_frame = min_start_key;
//					}
//
//					System.arraycopy(bf2.transforms_float_array, ((int)start_frame + max_key * i) * 16, this.current_mat4, 0, 16);
//					System.arraycopy(bf2.transforms_float_array, ((int)end_frame + max_key * i) * 16, this.current_mat4, 16, 16);
//
//					M4x4.lerp(this.current_mat4, bf2.transforms_float_array, 0, (start_key_frame + max_key * i) * 16, new_start_frame);
//					M4x4.lerp(this.current_mat4, bf2.transforms_float_array, 0, (end_key_frame + max_key * i) * 16, new_end_frame);
//
//					M4x4.lerp(this.current_mat4, this.current_mat4, 0, 16, Minecraft.getMinecraft().getRenderPartialTicks());
//					M4x4.multiply(this.current_mat4, this.skinning_float_array, 0, i * 16);
				M4x4.multiply(bf2.transforms_float_array, skinning_float_array, (key_short_array[l] + max_key * i) * 16, i * 16);
//				}
			}

			M4x4.inverse(skinning_float_array, i * 16);
		}
	}
}
