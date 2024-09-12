//package com.nali.system.opengl.memo.client;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class MemoSs extends MemoSo
//{
////	public float[] bind_poses_float_array;
////	public int[][] back_bones_2d_int_array;
////	public int[][] bones_2d_int_array;
//
//	public MemoSs(String[] shader_string_array/*, String folder_path*/)
//	{
//		super(shader_string_array/*, folder_path*/);
//	}
//
////	@Override
////	public void createBuffer(String[] shader_string_array, String folder_path, int max_bones)
////	{
////		max_bones = new File(folder_path + "Models/" + shader_string_array[2] + "/Animation/Bones").listFiles().length;
////		super.createBuffer(shader_string_array, folder_path, max_bones);
////
////		int start_index = this.uniformlocation_int_array.length - max_bones;
////		for (int i = 0; i < max_bones; ++i)
////		{
////			this.uniformlocation_int_array[start_index + i] = OpenGlHelper.glGetUniformLocation(this.program, "animation" + StringReader.convertNumberToLetter(i));
////		}
////	}
//
////	@Override
////	public int getMaxBones()
////	{
////		return this.max_bones;
////	}
//
////	public static String getVertexHeaderShaderString()
////	{
////		return "#version 120\n" +
////				"precision highp float;\n" +
////				"attribute vec3 vertices;\n" +
////				"attribute vec2 texcoord;\n" +
////				"attribute vec4 joints;\n" +
////				"attribute vec4 weights;\n" +
////				"attribute vec3 normals;\n";
////	}
//}
