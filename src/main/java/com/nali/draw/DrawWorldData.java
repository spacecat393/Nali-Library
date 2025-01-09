package com.nali.draw;

public class DrawWorldData
{
	public float[] projection_m4x4_float = new float[16],
	modelview_m4x4_float = new float[16],
	color_v4_float = new float[4],
	light0position_v4_float = new float[4],
	skinning_float_array;
	public float light_b, light_s;
//	public byte state;//glow/outline
}
