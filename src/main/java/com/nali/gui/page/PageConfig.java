package com.nali.gui.page;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
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
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("CONFIG".toCharArray()),

			new BoxTextAll("MENU".toCharArray()),//0
			new BoxTextAll("CHECK".toCharArray()),
			new BoxTextAll("SOUND-CONFIG".toCharArray()),
			new BoxTextAll("OPENGL-CONFIG".toCharArray()),

			new BoxTextAll("EXTRA".toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 32) == 32 ? "DISABLE_SP_LOAD YES" : "DISABLE_SP_LOAD NO").toCharArray()),

			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("DONE".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;

		if ((this.state & 4) == 0)
		{
			this.select = 2;
			this.state |= 4;
		}
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
		switch (this.select)
		{
			case 2:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageCheck(), new KeySelect());
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageConfigSound(), new KeyEdit());
				break;
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageConfigOpenGL(), new KeySelect());
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
			Files.write(Paths.get("nali/nali/tmp/config.bin"), NaliConfig.getByteArray());
		}
		catch (IOException e)
		{
			Nali.error(e);
		}
	}
}
