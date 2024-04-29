package com.nali.config;

import com.nali.system.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Config(modid = Reference.MOD_ID)
public class MyConfig
{
//    @Config.Name("Using Multi Uniform")
//    @Config.Comment("Rendering")
//    @Config.RequiresMcRestart
//    public static boolean USING_MULTI_UNIFORM = true;

//    public static final Color COLOR = new Color();
//    public static final Light LIGHT = new Light();
    @Config.Name("Frame Settings")
    public static final Frame FRAME = new Frame();
    @Config.Name("Shader Settings")
    public static final Shader SHADER = new Shader();

    @Config.Name("Sound Settings")
    public static final Sound SOUND = new Sound();

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
        @Config.Comment("When other rendering mod use own frame-buffer.")
        @Config.RequiresMcRestart
        public boolean using_frame_buffer_index = false;

        @Config.Name("Frame Buffer Index")
        @Config.Comment("Match which frame-buffer is rendering.")
        @Config.RequiresMcRestart
        public int frame_buffer_index = 5;
    }

    public static class Shader
    {
        @Config.Name("Pre-Shaders")
        @Config.Comment("Load first then no need to wait.")
        @Config.RequiresMcRestart
        public boolean pre_shader = false;

        @Config.Name("Max Shaders")
        @Config.Comment("Limit pre-shader if you run on Integrated GPU then try to Add More until it launch to main menu. [Add More is up on your Ram that can pre-shader how much on launch]")
        @Config.RequiresMcRestart
        public int max_shaders = Integer.MAX_VALUE;

        @Config.Name("Max Bones")
        @Config.Comment("M4x4 Buffer for Animation.")
        @Config.RequiresMcRestart
        public int max_bones = 220 * 16;
    }

    public static class Sound
    {
        @Config.Name("ffmpeg")
        @Config.RequiresMcRestart
        public boolean ffmpeg = true;

        @Config.Name("AL_GAIN")
        public float al_gain = 1.0F;

        @Config.Name("AL_PITCH")
        public float al_pitch = 1.0F;
    }

//    public static void registerConfig()
//    {
////        String comment = "Lighting";
////        COLOR.r = Nali.CONFIGURATION.getFloat("R Color", "color", (float)COLOR.r, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        COLOR.g = Nali.CONFIGURATION.getFloat("G Color", "color", (float)COLOR.g, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        COLOR.b = Nali.CONFIGURATION.getFloat("B Color", "color", (float)COLOR.b, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        LIGHT.min_light = Nali.CONFIGURATION.getFloat("Min Light", "light", (float)LIGHT.min_light, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        LIGHT.min_brightness = Nali.CONFIGURATION.getFloat("Min Brightness", "light", (float)LIGHT.min_brightness, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        LIGHT.min_sun_brightness_factor = Nali.CONFIGURATION.getFloat("Min Sun Brightness Factor", "light", (float)LIGHT.min_sun_brightness_factor, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        LIGHT.sun_light = Nali.CONFIGURATION.getFloat("Sun Light", "light", (float)LIGHT.sun_light, Float.MIN_VALUE, Float.MAX_VALUE, comment);
////        LIGHT.moon_light = Nali.CONFIGURATION.getFloat("Moon Light", "light", (float)LIGHT.moon_light, Float.MIN_VALUE, Float.MAX_VALUE, comment);
//        ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
//        String comment = "Rendering";
////        USING_MULTI_UNIFORM = Nali.CONFIGURATION.getBoolean("Using Multi Uniform", Configuration.CATEGORY_GENERAL, USING_MULTI_UNIFORM, comment);
//        FRAME.using_frame_buffer_index = Nali.CONFIGURATION.getBoolean("Using Frame Buffer Index", "frame", FRAME.using_frame_buffer_index, comment);
//        FRAME.frame_buffer_index = Nali.CONFIGURATION.getInt("Frame Buffer Index", "frame", FRAME.frame_buffer_index, Integer.MIN_VALUE, Integer.MAX_VALUE, comment);
//    }

    @Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
    public static class ConfigEvent
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(Reference.MOD_ID))
            {
                ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
//                Nali.CONFIGURATION.save();
            }
        }
    }
}
