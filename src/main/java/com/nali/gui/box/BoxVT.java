package com.nali.gui.box;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class BoxVT extends BoxV
{
	public float u0, v0, u1, v1 = 5, t_width = 170, t_height = 5;//font 170x5

	@Override
	public float[] createQuad()
	{
		float nx1 = (2.0F * this.x0 / this.v_width) - 1.0F;
		float ny1 = (2.0F * this.y0 / this.v_height) - 1.0F;

		float nx2 = (2.0F * this.x1 / this.v_width) - 1.0F;
		float ny2 = (2.0F * this.y1 / this.v_height) - 1.0F;
//		float nx1 = x0 / v_width;
//		float ny1 = y0 / v_height;
//
//		float nx2 = x1 / v_width;
//		float ny2 = y1 / v_height;

		float nu1 = this.u0 / this.t_width;
		float nv1 = this.v0 / this.t_height;

		float nu2 = this.u1 / this.t_width;
		float nv2 = this.v1 / this.t_height;

		return new float[]
		{
			nx1, ny2, nu1, nv2,
			nx1, ny1, nu1, nv1,
			nx2, ny1, nu2, nv1,

			nx1, ny2, nu1, nv2,
			nx2, ny1, nu2, nv1,
			nx2, ny2, nu2, nv2
		};
//		//scale -y
//		return new float[]
//		{
//			nx1, ny1, nu1, nv2,
//			nx1, ny2, nu1, nv1,
//			nx2, ny2, nu2, nv1,
//
//			nx1, ny1, nu1, nv2,
//			nx2, ny2, nu2, nv1,
//			nx2, ny1, nu2, nv2,
//		};
//		//rotate z 180
//		return new float[]
//		{
//			nx2, ny1, nu1, nv2,
//			nx2, ny2, nu1, nv1,
//			nx1, ny2, nu2, nv1,
//
//			nx2, ny1, nu1, nv2,
//			nx1, ny2, nu2, nv1,
//			nx1, ny1, nu2, nv2,
//		};
	}
}
