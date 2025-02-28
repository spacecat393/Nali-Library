package com.nali.gui.box;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxVT
{
	public final static byte B_FA_U0 = 6;
	public final static byte B_FA_V0 = 7;
	public final static byte B_FA_U1 = 8;
	public final static byte B_FA_V1 = 9;
	public final static byte B_FA_T_WIDTH = 10;
	public final static byte B_FA_T_HEIGHT = 11;

//	public float u0, v0, u1, v1 = 5, t_width = 170, t_height = 5;//font 170x5

	public static void set(float[] float_array, float[] w_float_array, int index)
	{
		BoxV.set(float_array, w_float_array, index, (byte)2);

		float
//			x0 = float_array[BoxV.B_FA_X0],
//			y0 = float_array[BoxV.B_FA_Y0],
//			x1 = float_array[BoxV.B_FA_X1],
//			y1 = float_array[BoxV.B_FA_Y1],
//			v_width = float_array[BoxV.B_FA_V_WIDTH],
//			v_height = float_array[BoxV.B_FA_V_HEIGHT],
			u0 = float_array[B_FA_U0],
			v0 = float_array[B_FA_V0],
			u1 = float_array[B_FA_U1],
			v1 = float_array[B_FA_V1],
			t_width = float_array[B_FA_T_WIDTH],
			t_height = float_array[B_FA_T_HEIGHT];

//		float nx1 = (2.0F * x0 / v_width) - 1.0F;
//		float ny1 = (2.0F * y0 / v_height) - 1.0F;
//
//		float nx2 = (2.0F * x1 / v_width) - 1.0F;
//		float ny2 = (2.0F * y1 / v_height) - 1.0F;

		float nu1 = u0 / t_width;
		float nv1 = v0 / t_height;

		float nu2 = u1 / t_width;
		float nv2 = v1 / t_height;

//		w_float_array[index] = nx1;
//		w_float_array[index + 1] = ny2;
		w_float_array[index + 2] = nu1;
		w_float_array[index + 3] = nv2;

//		w_float_array[index + 4] = nx1;
//		w_float_array[index + 5] = ny1;
		w_float_array[index + 6] = nu1;
		w_float_array[index + 7] = nv1;

//		w_float_array[index + 8] = nx2;
//		w_float_array[index + 9] = ny1;
		w_float_array[index + 10] = nu2;
		w_float_array[index + 11] = nv1;

//		w_float_array[index + 12] = nx1;
//		w_float_array[index + 13] = ny2;
		w_float_array[index + 14] = nu1;
		w_float_array[index + 15] = nv2;

//		w_float_array[index + 16] = nx2;
//		w_float_array[index + 17] = ny1;
		w_float_array[index + 18] = nu2;
		w_float_array[index + 19] = nv1;

//		w_float_array[index + 20] = nx2;
//		w_float_array[index + 21] = ny2;
		w_float_array[index + 22] = nu2;
		w_float_array[index + 23] = nv2;
	}
}
