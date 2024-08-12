package com.nali;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.Nali.ID;

@SideOnly(Side.CLIENT)
@Config(modid = ID)
public class NaliConfig
{
//    @Config.Name("Using Multi Uniform")
//    @Config.Comment("Rendering")
//    @Config.RequiresMcRestart
//    public static boolean USING_MULTI_UNIFORM = true;

//    public static final Color COLOR = new Color();
//    public static final Light LIGHT = new Light();
//    @Config.Name("Frame Settings")
//    public static final Frame FRAME = new Frame();
    @Config.Name("Shader")
    public static final Shader SHADER = new Shader();

    @Config.Name("Sound")
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

//    public static class Frame
//    {
//        @Config.Name("Using Frame Buffer Index")
//        @Config.Comment("When other rendering mod use own frame-buffer.")
//        @Config.RequiresMcRestart
//        public boolean using_frame_buffer_index = false;
//
//        @Config.Name("Frame Buffer Index")
//        @Config.Comment("Match which frame-buffer is rendering.")
//        @Config.RequiresMcRestart
//        public int frame_buffer_index = 5;
//    }

    public static class Shader
    {
//        @Config.Name("Ex-Framebuffer")
//        @Config.Comment("Post-processing effects")
//        public boolean ex_framebuffer = false;

        @Config.Name("Pre-Shaders")
        @Config.Comment("First compile")
        @Config.RequiresMcRestart
        public boolean pre_shader = false;

        @Config.Name("GL_SHADING_LANGUAGE_VERSION")
        @Config.Comment("\"120\" \"460\"")
        @Config.RequiresMcRestart
        public String gl_shading_language_version = "460";

        @Config.Name("ATTRIBUTE")
        @Config.Comment("\"attribute\" \"in\"")
        @Config.RequiresMcRestart
        public String attribute = "in";

//        @Config.Name("Sort")
//        @Config.Comment("Draw later with order.")
//        @Config.RequiresMcRestart
//        public boolean sort = true;

//        @Config.Name("Max Shaders")
//        @Config.Comment("Compile on next load")
//        @Config.RequiresMcRestart
//        public int max_shaders = Integer.MAX_VALUE;

//        @Config.Name("Max Bones")
//        @Config.Comment("Animation Mat4")
//        @Config.RequiresMcRestart
//        public int max_bones = 220 * 16;
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

    @Mod.EventBusSubscriber(modid = ID, value = Side.CLIENT)
    public static class ConfigEvent
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(ID))
            {
                ConfigManager.sync(ID, Config.Type.INSTANCE);
            }
        }
    }
}
