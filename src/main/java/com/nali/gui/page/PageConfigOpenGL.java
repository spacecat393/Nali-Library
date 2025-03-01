package com.nali.gui.page;

import com.nali.NaliConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageConfigOpenGL extends PageSelect
{
	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"OPENGL-CONFIG".toCharArray(),

			"SHADER".toCharArray(),
			((NaliConfig.STATE & 16) == 16 ? "FREE_MEMORY YES" : "FREE_MEMORY NO").toCharArray(),
			((NaliConfig.STATE & 1) == 1 ? "PRE_SHADER YES" : "PRE_SHADER NO").toCharArray(),
			((NaliConfig.STATE & 2) == 2 ? "USE_SWITCH YES" : "USE_SWITCH NO").toCharArray(),

			"ACTION".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 2;
			this.fl |= BF_SET_SELECT;
		}

		super.init();
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				NaliConfig.STATE ^= 16;
				break;
			case 3:
				NaliConfig.STATE ^= 1;
				break;
			case 4:
				NaliConfig.STATE ^= 2;
				break;
			case 6:
				this.back();
				break;
		}
	}
}
