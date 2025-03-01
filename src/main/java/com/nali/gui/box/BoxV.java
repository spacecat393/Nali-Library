package com.nali.gui.box;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class BoxV
{
	public final static byte B_FA_X0 = 0;
	public final static byte B_FA_Y0 = 1;
	public final static byte B_FA_X1 = 2;
	public final static byte B_FA_Y1 = 3;
	public final static byte B_FA_V_WIDTH = 4;
	public final static byte B_FA_V_HEIGHT = 5;
	public final static byte B_FAL = 6;

	public final static byte B_WFAL = 12;

	public final static byte B_AP_SIZE = 2;

	public static void set(float[] float_array, float[] w_float_array, int index, byte step)
	{
		float
			x0 = float_array[B_FA_X0],
			y0 = float_array[B_FA_Y0],
			x1 = float_array[B_FA_X1],
			y1 = float_array[B_FA_Y1],
			v_width = float_array[B_FA_V_WIDTH],
			v_height = float_array[B_FA_V_HEIGHT];

		float nx1 = (2.0F * x0 / v_width) - 1.0F;
		float ny1 = (2.0F * y0 / v_height) - 1.0F;

		float nx2 = (2.0F * x1 / v_width) - 1.0F;
		float ny2 = (2.0F * y1 / v_height) - 1.0F;

		w_float_array[index] = nx1;
		w_float_array[index + 1] = ny2;

		index += step;

		w_float_array[index + 2] = nx1;
		w_float_array[index + 3] = ny1;

		index += step;

		w_float_array[index + 4] = nx2;
		w_float_array[index + 5] = ny1;

		index += step;

		w_float_array[index + 6] = nx1;
		w_float_array[index + 7] = ny2;

		index += step;

		w_float_array[index + 8] = nx2;
		w_float_array[index + 9] = ny1;

		index += step;

		w_float_array[index + 10] = nx2;
		w_float_array[index + 11] = ny2;
	}
}
