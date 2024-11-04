package com.nali.gui.box;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class BoxV
{
	public float x0, y0, x1, y1, v_width, v_height;

	public float[] createQuad()
	{
		float nx1 = (2.0F * this.x0 / this.v_width) - 1.0F;
		float ny1 = (2.0F * this.y0 / this.v_height) - 1.0F;

		float nx2 = (2.0F * this.x1 / this.v_width) - 1.0F;
		float ny2 = (2.0F * this.y1 / this.v_height) - 1.0F;

		return new float[]
		{
			nx1, ny2,
			nx1, ny1,
			nx2, ny1,

			nx1, ny2,
			nx2, ny1,
			nx2, ny2,
		};
	}
}
