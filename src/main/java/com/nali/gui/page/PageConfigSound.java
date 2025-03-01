package com.nali.gui.page;

import com.nali.NaliConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageConfigSound extends PageEdit
{
	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"SOUND-CONFIG".toCharArray(),

			"START PROCESS".toCharArray(),
			((NaliConfig.STATE & 4) == 4 ? "USE_YT-DLP YES" : "USE_YT-DLP NO").toCharArray(),
			((NaliConfig.STATE & 8) == 8 ? "USE_FFMPEG YES" : "USE_FFMPEG NO").toCharArray(),

			"AL_GAIN".toCharArray(),
			this.getChar("AL_GAIN_ALL " + NaliConfig.FLOAT_ARRAY[0]),
			this.getChar("AL_GAIN_BGM " + NaliConfig.FLOAT_ARRAY[1]),
			this.getChar("AL_GAIN_ENTITY " + NaliConfig.FLOAT_ARRAY[2]),
			this.getChar("AL_GAIN_BLOCK " + NaliConfig.FLOAT_ARRAY[3]),
			this.getChar("AL_GAIN_EFFECT " + NaliConfig.FLOAT_ARRAY[4]),

			"AL_PITCH".toCharArray(),
			this.getChar("AL_PITCH_ALL " + NaliConfig.FLOAT_ARRAY[5]),
			this.getChar("AL_PITCH_BGM " + NaliConfig.FLOAT_ARRAY[6]),
			this.getChar("AL_PITCH_ENTITY " + NaliConfig.FLOAT_ARRAY[7]),
			this.getChar("AL_PITCH_BLOCK " + NaliConfig.FLOAT_ARRAY[8]),
			this.getChar("AL_PITCH_EFFECT " + NaliConfig.FLOAT_ARRAY[9]),

			"EXTRA".toCharArray(),
			this.getChar("BGM_ID " + NaliConfig.BGM_ID),

			"ACTION".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[3 / 8] |= 1 << 3 % 8;
		this.group_byte_array[9 / 8] |= 1 << 9 % 8;
		this.group_byte_array[15 / 8] |= 1 << 15 % 8;
		this.group_byte_array[17 / 8] |= 1 << 17 % 8;

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
		boolean select_pitch = this.select > 10 && this.select < 16;
		if (select_pitch || (this.select > 4 && this.select < 10))
		{
			byte index;
			if (select_pitch)
			{
				index = (byte)(this.select - 6);
			}
			else
			{
				index = (byte)(this.select - 5);
			}

			if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
			{
				try
				{
					NaliConfig.FLOAT_ARRAY[index] = Float.parseFloat(this.input_stringbuilder.toString());
				}
				catch (Exception e)
				{
				}
			}
			else
			{
				this.input_stringbuilder.setLength(0);
				this.input_stringbuilder.append(NaliConfig.FLOAT_ARRAY[index]);
				this.select_box = this.input_stringbuilder.length();
			}
			this.fl ^= BF_ENTER_MODE;
			this.scroll = 0;
		}
		else
		{
			switch (this.select)
			{
				case 2:
				{
					NaliConfig.STATE ^= 4;
					break;
				}
				case 3:
				{
					NaliConfig.STATE ^= 8;
					break;
				}
				case 17:
				{
					if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
					{
						NaliConfig.BGM_ID = this.input_stringbuilder.toString();
					}
					else
					{
						this.input_stringbuilder.setLength(0);
						this.input_stringbuilder.append(NaliConfig.BGM_ID);
						this.select_box = this.input_stringbuilder.length();
					}
					this.fl ^= BF_ENTER_MODE;
					this.scroll = 0;
					break;
				}
				case 19:
					this.back();
					break;
			}
		}
	}
}
