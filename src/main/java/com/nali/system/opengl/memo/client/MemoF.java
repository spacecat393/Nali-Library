//package com.nali.system.opengl.memo.client;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class MemoF
//{
//	//mat4[F*B*N]
////	public float[] transforms_float_array;
//	//	public byte[] idlebones_byte_array;
////	public float[] visual_bones_float_array;
////	public int[][] bones_2d_int_array;
////	public int
////		bone,
////		length;
//
////	//share
////	public float[] bind_pose_float_array;
////	public int[][] back_bone_2d_int_array;
//
//	public MemoF(int bone, float[] transforms_float_array/*, String[] model_string_array, String folder_path*/)
//	{
////		String model_folder_path = folder_path + "/model/" + model_string_array[0];
////		String animation_string = "/frame/";
//
////		if (new File(model_folder_path, animation_string).isDirectory())
////		{
////			this.transforms_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "transform.bin");
//
////			try
////			{
////				this.idlebones_byte_array = Files.readAllBytes(Paths.get(model_folder_path + "/IdleBones"));
////			}
////			catch (IOException e)
////			{
////				Nali.error(e);
////			}
////
////			this.length = (this.transforms_float_array.length / 16) / this.idlebones_byte_array.length;
////			this.visual_bones_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "VisualBones");
////
////			int max_bones = this.visual_bones_float_array.length / 16;
////			int[][] bones_2d_int_array = new int[max_bones][];
////			for (int i = 0; i < max_bones; ++i)
////			{
////				bones_2d_int_array[i] = FileDataReader.getIntArray(model_folder_path + animation_string + "Bones/" + i);
////			}
////
////			int[][] back_bones_2d_int_array = new int[max_bones][];
////
////			for (int j = 0; j < max_bones; ++j)
////			{
////				int[] bones = bones_2d_int_array[j];
////				back_bones_2d_int_array[j] = new int[bones.length];
////
////				int b_index = 0;
////				for (int b = bones.length - 1; b > -1; --b)
////				{
////					int[] b_bones = back_bones_2d_int_array[j];
////					b_bones[b_index++] = bones[b];
////				}
////			}
////
////			this.bones_2d_int_array = back_bones_2d_int_array;
//
////			this.bone = new File(model_folder_path + animation_string + "bone").listFiles().length;
//		this.bone = bone;
////		this.length = (this.transforms_float_array.length / 16) / this.bone;
////		this.length = (int)(new File(folder_path + "/model/" + model_string_array[0] + "/frame/transform.bin").length() / 16 / this.bone);
//		this.length = transforms_float_array.length / 16 / this.bone;
////		}
//	}
//}
