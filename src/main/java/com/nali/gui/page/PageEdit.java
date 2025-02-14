package com.nali.gui.page;

import com.nali.gui.box.text.BoxTextAllMax;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PageEdit/*<N extends Number>*/ extends PageSelect/*<N>*/
{
	public StringBuilder input_stringbuilder = new StringBuilder();
	public BoxTextAllMax boxtextallmax;
	public int select_box;

	@Override
	public void init()
	{
		this.boxtextallmax = new BoxTextAllMax(this.input_stringbuilder.toString().toCharArray());
	}

	@Override
	public void gen()
	{
		//854 x 480
//		int h20 = (int)(0.041666668F * height);
//		int wh20 = Math.min((int)(0.0234192037470726F * width), h20);
//		int wh5 = Math.min((int)(0.005854801F * width), (int)(0.010416667F * height));
		super.gen();
		this.boxtextallmax.gen
		(
			this.wh20,
			this.y - this.wh40,
			this.wh20, this.wh10, this.wh20, WIDTH, HEIGHT
		);
	}

	@Override
	public void genBoxColor()
	{
		if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
		{
			this.boxcolor.x0 = 0;
			this.boxcolor.y0 = 0;
			this.boxcolor.x1 = this.wh10;
			this.boxcolor.y1 = this.wh20;
		}
		else
		{
			super.genBoxColor();
		}
	}

	@Override
	public void draw()
	{
		super.draw();
	}

	@Override
	public void drawBoxColor()
	{
		if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
		{
			float nl_ss = this.wh20 + this.wh10;
			float nl_x = this.wh20, nl_y = 0;

			for (int i = 0; i < this.select_box; ++i)
			{
				if (nl_x > WIDTH - nl_ss)
				{
					nl_x = this.wh20;
					nl_y -= nl_ss;
				}
				nl_x += nl_ss;
			}

			nl_x -= this.wh10;
			float x = nl_x / WIDTH * 2;
			float y = (1.0F - 0.041666668F + (this.h20 - this.wh40 + nl_y) / HEIGHT) * 2;
			this.v_float_array[0] += x;
			this.v_float_array[1] += y;
			this.boxcolor.draw(this.rs1, this.v_float_array, new float[]{0.0F, 1.0F, 0.0F, 1.0F});
			this.v_float_array[0] -= x;
			this.v_float_array[1] -= y;
		}
		else
		{
			super.drawBoxColor();
		}
	}

	@Override
	public void drawBoxTextAll()
	{
		if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
		{
			this.boxtextallmax.draw(this.rs0, this.v_float_array, this.c_float_array);
		}
		else
		{
			super.drawBoxTextAll();
		}
	}

	@Override
	public void clear()
	{
		super.clear();
		OpenGlHelper.glDeleteBuffers(this.boxtextallmax.array_buffer);
	}
}
