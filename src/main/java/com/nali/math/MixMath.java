package com.nali.math;

public class MixMath
{
    // public static float TIME = 0.6662F;

//    public static double lerp(double t, double s, double e)
//    {
//        return s + t * (e - s);
//    }

    public static float wrapDegrees(float f0)
    {
        float f = f0 % 360.0F;

        if (f >= 180.0F)
        {
            f -= 360.0F;
        }

        if (f < -180.0F)
        {
            f += 360.0F;
        }

        return f;
    }

//    public static float rotLerp(float f0, float f1, float f2)
//    {
//        return f1 + f0 * wrapDegrees(f2 - f1);
//    }

    public static float invert(float rotation)
    {
        rotation += 180.0F;
        rotation %= 360.0F;

        if (rotation < 0)
        {
            rotation += 360.0F;
        }

        if (rotation > 180.0f)
        {
            rotation -= 360.0F;
        }

        rotation %= 360.0F;

        return rotation;
    }

    // public static float wave(float amplitude, float frequency, float time, float phase_shift)
    // {
    //     return amplitude * (float) Math.sin(2 * Math.PI * frequency * time + phase_shift) + amplitude;
    // }

    // public static float lerp(float a, float b, float t)
    // {
    //     return a + t * (b - a);
    // }

    // public static float radian(float degree)
    // {
    //     return degree * 3.14159265359F / 180.0F;
    // }

    // public static float degree(float radian)
    // {
    //     return radian * 180.0F / 3.14159265359F;
    // }

    // public static float interpolate(float value, float max, float min)
    // {
    //     return (max - min) * value + min;
    //     //min * (1 - value) + max * value
    //     //(max * value) + ((1 - value) * min)
    // }

    // public static float linear_interpolate(float value, float max0, float min0, float max1, float min1)
    // {
    //     return (max0 - value) / (max0 - min0) * min1 + (min0 - value) / (min0 - max0) * max1;
    // }

    public static float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks)
    {
        float f;

        for (f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F)
        {
            ;
        }

        while (f >= 180.0F)
        {
            f -= 360.0F;
        }

        return prevYawOffset + partialTicks * f;
    }
}
