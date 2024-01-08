package com.nali.system.opengl.memory;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@SideOnly(Side.CLIENT)
public class OpenGLCurrentMemory
{
    public static IntBuffer OPENGL_INTBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
    public static FloatBuffer OPENGL_FLOATBUFFER;
    public static int OPENGL_FLOATBUFFER_SIZE;
    public static int GL_CURRENT_PROGRAM;
    public static int GL_ELEMENT_ARRAY_BUFFER_BINDING;
    public static int GL_TEXTURE_BINDING_2D;
    public static int GL_ARRAY_BUFFER_BINDING;
    public static int GL_ACTIVE_TEXTURE;
    public static int GL_BLEND_EQUATION_RGB;
    public static int GL_BLEND_EQUATION_ALPHA;
    public static int GL_BLEND_SRC_RGB;
    public static int GL_BLEND_SRC_ALPHA;
    public static int GL_BLEND_DST_RGB;
    public static int GL_BLEND_DST_ALPHA;

    public static int GL_TEXTURE_WRAP_S;
    public static int GL_TEXTURE_WRAP_T;
    public static int GL_TEXTURE_MIN_FILTER;
    public static int GL_TEXTURE_MAG_FILTER;

    public static boolean GL_DEPTH_TEST;
    public static boolean GL_CULL_FACE;
    public static boolean GL_BLEND;

//    public static Object COLORSTATE_INSTANCE;
//    public static Field RED_FIELD, GREEN_FIELD, BLUE_FIELD, ALPHA_FIELD;
//    public static float RED, GREEN, BLUE, ALPHA;

//    static
//    {
//        COLORSTATE_INSTANCE = ObfuscationReflectionHelper.getPrivateValue(GlStateManager.class, null, "colorState");
//        RED_FIELD = ObfuscationReflectionHelper.findField(COLORSTATE_INSTANCE.getClass(), "red");
//        GREEN_FIELD = ObfuscationReflectionHelper.findField(COLORSTATE_INSTANCE.getClass(), "green");
//        BLUE_FIELD = ObfuscationReflectionHelper.findField(COLORSTATE_INSTANCE.getClass(), "blue");
//        ALPHA_FIELD = ObfuscationReflectionHelper.findField(COLORSTATE_INSTANCE.getClass(), "alpha");
//    }

    public static void setFloatBuffer(float[] float_array)
    {
//        OPENGL_FLOATBUFFER.limit(float_array.length);
        OPENGL_FLOATBUFFER.clear();
        OPENGL_FLOATBUFFER.put(float_array);
        OPENGL_FLOATBUFFER.flip();
    }

//    public static void takeColor()
//    {
//        try
//        {
//            RED = RED_FIELD.getFloat(COLORSTATE_INSTANCE);
//            GREEN = GREEN_FIELD.getFloat(COLORSTATE_INSTANCE);
//            BLUE = BLUE_FIELD.getFloat(COLORSTATE_INSTANCE);
//            ALPHA = ALPHA_FIELD.getFloat(COLORSTATE_INSTANCE);
//        }
//        catch (IllegalAccessException e)
//        {
//            Nali.error(e);
//        }
//    }
}
