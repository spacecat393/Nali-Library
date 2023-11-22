package com.nali.system.opengl.buffer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

@SideOnly(Side.CLIENT)
public class OpenGLTextureBuffer
{
    public static void load(Object[] object_array)
    {
        Object[] texture_object_array = (Object[])object_array[0];

        int length = texture_object_array.length;
        Object[] temp_image_buffer_array = new Object[length];
        for (int i = 0; i < length; ++i)
        {
            Object[] temp_texture_object_array = (Object[])texture_object_array[i];
            temp_image_buffer_array[i] = OpenGLBuffer.loadTextureBuffer((ByteBuffer)temp_texture_object_array[0], (int)temp_texture_object_array[1], (int)temp_texture_object_array[2], (byte)-1);
        }
        object_array[1] = temp_image_buffer_array;
    }

    public static void delete(Object[] object_array)
    {
        if (object_array[1] != null)
        {
            for (int i = 0; i < ((Object[])object_array[1]).length; ++i)
            {
                GL11.glDeleteTextures((int)((Object[])object_array[1])[i]);
            }

            object_array[1] = null;
        }
    }
}
