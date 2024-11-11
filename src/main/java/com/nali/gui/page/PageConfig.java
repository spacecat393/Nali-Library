package com.nali.gui.page;

import com.nali.NaliConfig;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageConfig extends PageSelect
{
	public PageConfig()
	{
		this.select = 2;
	}

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("CONFIG".toCharArray()),

			new BoxTextAll("MENU".toCharArray()),//0
			new BoxTextAll("CHECK".toCharArray()),
			new BoxTextAll("SOUND-CONFIG".toCharArray()),

//			new BoxTextAll("DOWNLOAD FILE".toCharArray()),
//			new BoxTextAll("START PROCESS".toCharArray()),
//			new BoxTextAll(((NaliConfig.STATE & 4) == 4 ? "USE_YT-DLP YES" : "USE_YT-DLP NO").toCharArray()),
//			new BoxTextAll(((NaliConfig.STATE & 8) == 8 ? "USE_FFMPEG YES" : "USE_FFMPEG NO").toCharArray()),
//			new BoxTextAll("READ SOUND".toCharArray()),

			new BoxTextAll("OPENGL".toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 1) == 1 ? "PRE_SHADER YES" : "PRE_SHADER NO").toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 2) == 2 ? "USE_SWITCH YES" : "USE_SWITCH NO").toCharArray()),

			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};
		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[3 / 8] |= 1 << 3 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;
	}

	@Override
	public void enter()
	{
//		Nali.warn("S " + this.select);
		switch (this.select)
		{
			case 2:
				this.set(new PageCheck(), new KeySelect());
				break;
			case 3:
				this.set(new PageConfigSound(), new KeyEdit());
				break;
			case 5:
				NaliConfig.STATE ^= 1;
				break;
			case 6:
				NaliConfig.STATE ^= 2;
				break;
			case 8:
				this.back();
				break;
		}
	}

	@Override
	public void back()
	{
	}
}
