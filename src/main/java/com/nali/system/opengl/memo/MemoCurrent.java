package com.nali.system.opengl.memo;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@SideOnly(Side.CLIENT)
public class MemoCurrent
{
    public static IntBuffer OPENGL_INTBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
//    public static FloatBuffer CPU_OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
    public static FloatBuffer
    OPENGL_FIXED_PIPE_FLOATBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer(),
    OPENGL_FLOATBUFFER;

    public static int
    SHADERS,
//    MY_CURRENT_PROGRAM = -1,
    GL_CURRENT_PROGRAM,
    GL_ELEMENT_ARRAY_BUFFER_BINDING,
    GL_ARRAY_BUFFER_BINDING,
    GL_BLEND_EQUATION_RGB,
    GL_BLEND_EQUATION_ALPHA,
    GL_BLEND_SRC_RGB,
    GL_BLEND_SRC_ALPHA,
    GL_BLEND_DST_RGB,
    GL_BLEND_DST_ALPHA,

    GL_ACTIVE_TEXTURE,
//    GL_ACTIVE_TEXTURE_0;
//    GL_ACTIVE_TEXTURE_1;
//    GL_ACTIVE_TEXTURE_2;
    GL_TEXTURE_BINDING_2D,
    GL_TEXTURE_BINDING_2D_0,
    GL_TEXTURE_BINDING_2D_1,
    GL_TEXTURE_BINDING_2D_2,

//    GL_FRONT_FACE;

    GL_DEPTH_WRITEMASK,
//    GL_DEPTH_FUNC;

//    GL_MATRIX_MODE;

    GL_TEXTURE_WRAP_S,
    GL_TEXTURE_WRAP_S_0,
    GL_TEXTURE_WRAP_S_1,
    GL_TEXTURE_WRAP_S_2,
    GL_TEXTURE_WRAP_T,
    GL_TEXTURE_WRAP_T_0,
    GL_TEXTURE_WRAP_T_1,
    GL_TEXTURE_WRAP_T_2,
    GL_TEXTURE_MIN_FILTER,
    GL_TEXTURE_MIN_FILTER_0,
    GL_TEXTURE_MIN_FILTER_1,
    GL_TEXTURE_MIN_FILTER_2,
    GL_TEXTURE_MAG_FILTER,
    GL_TEXTURE_MAG_FILTER_0,
    GL_TEXTURE_MAG_FILTER_1,
    GL_TEXTURE_MAG_FILTER_2;

    public static boolean
    GL_DEPTH_TEST,
    GL_CULL_FACE,
    GL_BLEND,
    GL_TEXTURE_2D,
    GL_LIGHTING;

    public static float GL_LINE_WIDTH;

    public static float[] GL_CURRENT_COLOR = new float[4];

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
        OPENGL_FLOATBUFFER.limit(float_array.length);
        OPENGL_FLOATBUFFER.clear();
        OPENGL_FLOATBUFFER.put(float_array);
        OPENGL_FLOATBUFFER.flip();
    }

//    public static void setBones(int max_bone)
//    {
//        if (OPENGL_FLOATBUFFER_SIZE < max_bone)
//        {
//            OPENGL_FLOATBUFFER_SIZE = max_bone;
////            Nali.LOGGER.info("SIZE " + OpenGLCurrentMemory.OPENGL_FLOATBUFFER_SIZE);
////            if (OPENGL_FLOATBUFFER != null)
////            {
////                cleanFloatBuffer(OPENGL_FLOATBUFFER);
////            }
//
//            OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect(OPENGL_FLOATBUFFER_SIZE << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//        }
//    }

//    public static void cleanFloatBuffer(FloatBuffer buffer)
//    {
//        if (buffer.isDirect())
//        {
//            try
//            {
//                Field buffer_field = buffer.getClass().getDeclaredField("hb");
//                buffer_field.setAccessible(true);
//                ByteBuffer byteBuffer = (ByteBuffer)buffer_field.get(buffer);
//
//                Field cleaner_field = byteBuffer.getClass().getDeclaredField("cleaner");
//                cleaner_field.setAccessible(true);
//                sun.misc.Cleaner cleaner = (sun.misc.Cleaner)cleaner_field.get(byteBuffer);
//
//                cleaner.clean();
//            }
//            catch (Exception e)
//            {
//                Nali.error(e);
//            }
//        }
//    }

//    public static void cleanDirectBuffer(ByteBuffer buffer)
//    {
//        if (buffer.isDirect())
//        {
//            try
//            {
//                Field field = buffer.getClass().getDeclaredField("cleaner");
//                field.setAccessible(true);
//                sun.misc.Cleaner cleaner = (sun.misc.Cleaner)field.get(buffer);
//                cleaner.clean();
//            }
//            catch (Exception e)
//            {
//                Nali.error(e);
//            }
//        }
//    }

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
