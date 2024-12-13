//package com.nali.system;
//
//import com.nali.Nali;
//import net.minecraft.client.Minecraft;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.Sys;
//
//@SideOnly(Side.CLIENT)
//public class Timing
//{
//	public static long LAST_TIME;
//	public static long DELTA;
////	public static float TD;
////	public static float TICK = 0.05F;
//	public static float TIMELINE;
//
//	public static void count()
//	{
//		long current_time = Sys.getTime();//Minecraft.getSystemTime();//System.currentTimeMillis();
//		DELTA = current_time - LAST_TIME;
//		TIMELINE = Math.min((float)DELTA / Sys.getTimerResolution(), 1.0F);
//		LAST_TIME = current_time;
////		TD = TICK * DELTA;
//		Nali.warn("TIMELINE " + TIMELINE);
//		Nali.warn("getTickLength " + Minecraft.getMinecraft().getTickLength());
//		Nali.warn("getRenderPartialTicks " + Minecraft.getMinecraft().getRenderPartialTicks());
//	}
//}
