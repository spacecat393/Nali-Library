package com.nali.config;

import com.nali.Nali;
import com.nali.system.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid = Reference.MOD_ID)
public class MyConfig
{
    @Config.Name("Using Multi Uniform")
    @Config.Comment("Rendering")
    @Config.RequiresMcRestart
    public static boolean USING_MULTI_UNIFORM = true;

//    public static final Color COLOR = new Color();
//    public static final Light LIGHT = new Light();
    public static final Frame FRAME = new Frame();

//    public static class Color
//    {
//        @Config.Name("R")
//        @Config.Comment("Red Color")
//        public double r = 0.0D;
//        @Config.Name("G")
//        @Config.Comment("Green Color")
//        public double g = 0.0D;
//        @Config.Name("B")
//        @Config.Comment("Blue Color")
//        public double b = 0.0D;
//    }

//    public static class Light
//    {
//        @Config.Name("Min Light")
//        @Config.Comment("Lowest for Light")
//        public double min_light = 0.1D;
//        @Config.Name("Min Brightness")
//        @Config.Comment("Lowest for Brightness")
//        public double min_brightness = 0.1D;
//
//        @Config.Name("Min Sun Brightness Factor")
//        @Config.Comment("Lowest for Sun Brightness Factor")
//        public double min_sun_brightness_factor = 0.0D;
//        @Config.Name("Sun Light")
//        @Config.Comment("Add Sun Light")
//        public double sun_light = 0.5D;
//
//        @Config.Name("Moon Light")
//        @Config.Comment("Add Moon Light")
//        public double moon_light = 0.0D;
//    }

    public static class Frame
    {
        @Config.Name("Using Frame Buffer Index")
        @Config.Comment("Rendering")
        @Config.RequiresMcRestart
        public boolean using_frame_buffer_index = false;

        @Config.Name("Frame Buffer Index")
        @Config.Comment("Rendering")
        @Config.RequiresMcRestart
        public int frame_buffer_index = 5;
    }

    public static void registerConfig()
    {
//        String comment = "Lighting";
//        COLOR.r = Nali.CONFIGURATION.getFloat("R Color", "color", (float)COLOR.r, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        COLOR.g = Nali.CONFIGURATION.getFloat("G Color", "color", (float)COLOR.g, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        COLOR.b = Nali.CONFIGURATION.getFloat("B Color", "color", (float)COLOR.b, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        LIGHT.min_light = Nali.CONFIGURATION.getFloat("Min Light", "light", (float)LIGHT.min_light, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        LIGHT.min_brightness = Nali.CONFIGURATION.getFloat("Min Brightness", "light", (float)LIGHT.min_brightness, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        LIGHT.min_sun_brightness_factor = Nali.CONFIGURATION.getFloat("Min Sun Brightness Factor", "light", (float)LIGHT.min_sun_brightness_factor, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        LIGHT.sun_light = Nali.CONFIGURATION.getFloat("Sun Light", "light", (float)LIGHT.sun_light, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        LIGHT.moon_light = Nali.CONFIGURATION.getFloat("Moon Light", "light", (float)LIGHT.moon_light, Float.MIN_VALUE, Float.MAX_VALUE, comment);
        ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
        String comment = "Rendering";
        USING_MULTI_UNIFORM = Nali.CONFIGURATION.getBoolean("Using Multi Uniform", Configuration.CATEGORY_GENERAL, USING_MULTI_UNIFORM, comment);
        FRAME.using_frame_buffer_index = Nali.CONFIGURATION.getBoolean("Using Frame Buffer Index", "frame", FRAME.using_frame_buffer_index, comment);
        FRAME.frame_buffer_index = Nali.CONFIGURATION.getInt("Frame Buffer Index", "frame", FRAME.frame_buffer_index, Integer.MIN_VALUE, Integer.MAX_VALUE, comment);
    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
    public static class ConfigEvent
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Reference.MOD_ID))
            {
                if (Nali.CONFIGURATION.hasChanged())
                {
                    Nali.CONFIGURATION.save();
                }
            }
        }
    }
}
