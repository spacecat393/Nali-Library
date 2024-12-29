package com.nali.gui.page;

import com.nali.NaliConfig;
import com.nali.gui.box.text.BoxTextAll;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageConfigOpenGL extends PageSelect
{
	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("OPENGL-CONFIG".toCharArray()),

			new BoxTextAll("SHADER".toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 16) == 16 ? "FREE_MEMORY YES" : "FREE_MEMORY NO").toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 1) == 1 ? "PRE_SHADER YES" : "PRE_SHADER NO").toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 2) == 2 ? "USE_SWITCH YES" : "USE_SWITCH NO").toCharArray()),

			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 2;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
			{
				NaliConfig.STATE ^= 16;
				break;
			}
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
