package com.nali.gui.page;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.KeySelect;
import com.nali.system.Command;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class PageCheck extends PageSelect
{
	public byte state;//display_yt-dlp display_ffmpeg SmallPointer NaliGL NaliAL

	public PageCheck()
	{
		String[] file_name_array = new File("nali/nali/c").list();
		this.select = 9;
		this.state |=
			(Command.isCommandLive("yt-dlp") ? 1 : 0) |
			(Command.isCommandLive("ffmpeg") ? 2 : 0);
		for (String string : file_name_array)
		{
			if (string.contains("libSmallPointer"))
			{
				this.state |= 4;
			}
			else if (string.contains("libNaliGL"))
			{
				this.state |= 8;
			}
			else if (string.contains("libNaliAL"))
			{
				this.state |= 16;
			}
		}
	}

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("CHECK".toCharArray()),

			new BoxTextAll("POINTER".toCharArray()),
			new BoxTextAll(((this.state & 4) == 4 ? "SMALL-POINTER ABLE" : "SMALL-POINTER UNABLE").toCharArray()),
			new BoxTextAll(((this.state & 8) == 8 ? "NALI-GL ABLE" : "NALI-GL UNABLE").toCharArray()),
			new BoxTextAll(((this.state & 16) == 16 ? "NALI-AL ABLE" : "NALI-AL UNABLE").toCharArray()),

			new BoxTextAll("COMMAND".toCharArray()),
			new BoxTextAll(((this.state & 2) == 2 ? "FFMPEG ABLE" : "FFMPEG UNABLE").toCharArray()),
			new BoxTextAll(((this.state & 1) == 1 ? "YT-DLP ABLE" : "YT-DLP UNABLE").toCharArray()),
//			new BoxTextAll("7ZIP".toCharArray()),

			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;
	}

	@Override
	public void next(byte step)
	{
	}

	@Override
	public void enter()
	{
		this.back();
	}

	@Override
	public void back()
	{
		this.set(new PageConfig(), new KeySelect());
	}
}
