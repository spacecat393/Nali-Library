package com.nali.gui.page;

import com.nali.NaliConfig;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.box.text.BoxTextAllMax;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageConfigSound extends Page
{
	public BoxTextAllMax boxtextallmax;

	public byte[] group_byte_array;
	public BoxTextAll[] boxtextall_array;

	@Override
	public void init()
	{
		this.boxtextallmax = new BoxTextAllMax(this.input_stringbuilder.toString().toCharArray());

		String bgm_id;
		if (NaliConfig.BGM_ID.length() > 11)
		{
			bgm_id = NaliConfig.BGM_ID.substring(0, 11) + "...";
		}
		else
		{
			bgm_id = ("BGM_ID " + NaliConfig.BGM_ID);
		}

		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SOUND-CONFIG".toCharArray()),

			new BoxTextAll("START PROCESS".toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 4) == 4 ? "USE_YT-DLP YES" : "USE_YT-DLP NO").toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 8) == 8 ? "USE_FFMPEG YES" : "USE_FFMPEG NO").toCharArray()),

			new BoxTextAll("AL_GAIN".toCharArray()),
			new BoxTextAll(("AL_GAIN " + NaliConfig.AL_GAIN).toCharArray()),
			new BoxTextAll(("AL_PITCH " + NaliConfig.AL_PITCH).toCharArray()),

			new BoxTextAll("AL_PITCH".toCharArray()),

			new BoxTextAll("EXTRA".toCharArray()),
			new BoxTextAll(bgm_id.toCharArray()),
		};
	}

	@Override
	public void gen()
	{

	}

	@Override
	public void draw()
	{

	}

	@Override
	public void clear()
	{

	}

	@Override
	public void render()
	{

	}
}
