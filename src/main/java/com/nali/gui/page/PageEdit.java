package com.nali.gui.page;

import com.nali.gui.box.Box;
import com.nali.gui.box.BoxV;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.box.text.BoxTextAllMax;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class PageEdit/*<N extends Number>*/ extends PageSelect/*<N>*/
{
	public StringBuilder input_stringbuilder = new StringBuilder();
//	public BoxTextAllMax boxtextallmax;

	public int
		select_box,
		ca1_t_count;

	@Override
	public void init()
	{
		super.init();
//		this.boxtextallmax = new BoxTextAllMax(this.input_stringbuilder.toString().toCharArray());
	}

	@Override
	public void gen()
	{
		//854 x 480
//		int h20 = (int)(0.041666668F * height);
//		int wh20 = Math.min((int)(0.0234192037470726F * width), h20);
//		int wh5 = Math.min((int)(0.005854801F * width), (int)(0.010416667F * height));
		super.gen();
		this.float_array[BoxTextAll.B_FA_X] = this.wh20;
		this.float_array[BoxTextAll.B_FA_Y] = this.y - this.wh40;
		this.float_array[BoxTextAll.B_FA_SIZE] = this.wh20;
		this.float_array[BoxTextAll.B_FA_SPACE] = this.wh10;
		this.float_array[BoxTextAllMax.B_FA_NEW_LINE] = this.wh20;
		char[] char_array = this.char_2d_array[this.char_2d_array.length - 1];
		this.ca1_t_count = char_array.length * 6;
		BoxTextAllMax.set(this.float_array, this.w_float_array, char_array, this.ca0_t_count + this.ta_t_count);
	}

	@Override
	public void genBoxColor()
	{
		if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
		{
			this.float_array[BoxV.B_FA_X0] = 0;
			this.float_array[BoxV.B_FA_Y0] = 0;
			this.float_array[BoxV.B_FA_X1] = this.wh10;
			this.float_array[BoxV.B_FA_Y1] = this.wh20;
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
				if (nl_x > Box.WIDTH - nl_ss)
				{
					nl_x = this.wh20;
					nl_y -= nl_ss;
				}
				nl_x += nl_ss;
			}

			nl_x -= this.wh10;
			float x = nl_x / Box.WIDTH * 2;
			float y = (1.0F - 0.041666668F + (this.h20 - this.wh40 + nl_y) / Box.HEIGHT) * 2;
			this.v_float_array[0] += x;
			this.v_float_array[1] += y;
			super.drawBoxColor();
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
//			this.boxtextallmax.draw(this.rs0, this.v_float_array, this.c_float_array);
			Box.draw(this.rs0, this.v_float_array, this.c_float_array, this.array_buffer, 4, 3 + this.ca0_t_count + this.ta_t_count, this.ca1_t_count);
		}
		else
		{
			super.drawBoxTextAll();
		}
	}

//	@Override
//	public void clear()
//	{
//		super.clear();
//		this.boxtextallmax.clear();
//	}
}
