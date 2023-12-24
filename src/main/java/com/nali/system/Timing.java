package com.nali.system;

import net.minecraft.client.Minecraft;

public class Timing
{
    public static long LAST_TIME;
    public static long DELTA;
    public static float TD;
    public static float TICK = 0.05F;

    public static void count()
    {
        long current_time = Minecraft.getSystemTime();//System.currentTimeMillis();
        DELTA = current_time - LAST_TIME;
        LAST_TIME = current_time;
        TD = TICK * DELTA;
    }
}
