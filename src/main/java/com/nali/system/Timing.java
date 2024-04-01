package com.nali.system;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class Timing
{
    public static long LAST_TIME;
    public static long DELTA;
    public static float TD;
    public static float TICK = 0.05F;
    public static float TIMELINE;

    public static void count()
    {
        long current_time = Minecraft.getSystemTime();//System.currentTimeMillis();
        DELTA = current_time - LAST_TIME;
        TIMELINE =  Math.min(DELTA / 10.0F, 1.0F);
        LAST_TIME = current_time;
        TD = TICK * DELTA;
    }
}
