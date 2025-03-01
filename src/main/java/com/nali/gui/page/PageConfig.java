package com.nali.gui.page;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SideOnly(Side.CLIENT)
public class PageConfig extends PageSelect
{
	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"CONFIG".toCharArray(),

			"MENU".toCharArray(),//0
			"CHECK".toCharArray(),
			"SOUND-CONFIG".toCharArray(),
			"OPENGL-CONFIG".toCharArray(),

			"EXTRA".toCharArray(),
			((NaliConfig.STATE & 32) == 32 ? "DISABLE_SP_LOAD YES" : "DISABLE_SP_LOAD NO").toCharArray(),

			"ACTION".toCharArray(),
			"DONE".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 2;
			this.fl |= BF_SET_SELECT;
		}

		super.init();
	}

	@Override
	public void exit()
	{
		this.save();
		super.exit();
	}

	@Override
	public void back()
	{
		this.save();
		super.back();
	}

	@Override
	public void enter()
	{
//		Nali.warn("S " + this.select);
		PageSelect ps;
		PageEdit pe;
		switch (this.select)
		{
			case 2:
				ps = new PageCheck();
				this.set(ps, new KeySelect(ps));
				break;
			case 3:
				pe = new PageConfigSound();
				this.set(pe, new KeyEdit(pe));
				break;
			case 4:
				ps = new PageConfigOpenGL();
				this.set(ps, new KeySelect(ps));
				break;
			case 6:
				NaliConfig.STATE ^= 32;
				break;
			case 8:
				this.back();
				break;
		}
	}

	public void save()
	{
		try
		{
			Files.write(Paths.get("nali/nali/tmp/config"), NaliConfig.getByteArray());
		}
		catch (IOException e)
		{
			Nali.error(e);
		}
	}
}
