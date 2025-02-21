package com.nali.system;

import com.nali.NaliConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;

@SideOnly(Side.CLIENT)
public class Time
{
	public static long TIME;
	public static long DELTA;

	public static double LINE;

	public static void update()
	{
		long time = Sys.getTime();
		DELTA = time - TIME;
		TIME = time;

		if (NaliConfig.NEED_EXTRA)
		{
			//extra 2f
//			long tr = Sys.getTimerResolution();
			LINE = Math.min((double)DELTA/* / tr * tr*/ / 40.0D, 1.0F);
		}
		else
		{
			LINE = Math.min((double)DELTA / Sys.getTimerResolution() / 20.0D, 1.0F);
		}
//		Nali.warn("FT " + Minecraft.getMinecraft().getTickLength());
//		Nali.warn("LINE " + LINE);
//		LINE = 1.0F;
	}
}
