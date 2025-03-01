package com.nali.gui.page;

import com.nali.system.Command;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class PageCheck/*<N extends Byte>*/ extends PageSelect/*<N>*/
{
	public byte state;//display_yt-dlp display_ffmpeg SmallPointer NaliGL NaliAL

	@Override
	public void init()
	{
		String[] file_name_array = new File("nali/nali/c").list();
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

		this.char_2d_array = new char[][]
		{
			"CHECK".toCharArray(),

			"POINTER".toCharArray(),
			((this.state & 4) == 4 ? "SMALL-POINTER ABLE" : "SMALL-POINTER UNABLE").toCharArray(),
			((this.state & 8) == 8 ? "NALI-GL ABLE" : "NALI-GL UNABLE").toCharArray(),
			((this.state & 16) == 16 ? "NALI-AL ABLE" : "NALI-AL UNABLE").toCharArray(),

			"COMMAND".toCharArray(),
			((this.state & 2) == 2 ? "FFMPEG ABLE" : "FFMPEG UNABLE").toCharArray(),
			((this.state & 1) == 1 ? "YT-DLP ABLE" : "YT-DLP UNABLE").toCharArray(),

			"ACTION".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;
//		this.select = this.createN(9);
		this.select = 9;

		super.init();
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

//	@Override
//	public N createN(int i)
//	{
//		return (N)Byte.valueOf((byte)i);
//	}
//
//	@Override
//	public N pN(N n, int i)
//	{
//		return this.createN(n.byteValue() + (byte)i);
//	}
}
