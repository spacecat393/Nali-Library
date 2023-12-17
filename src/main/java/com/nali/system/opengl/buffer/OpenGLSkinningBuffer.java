package com.nali.system.opengl.buffer;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@SideOnly(Side.CLIENT)
public class OpenGLSkinningBuffer
{
    public static void load(Object[] object_array)
    {
        Object[] buffer_object_array = (Object[])object_array[6];

//        int vertexarrays = GL30.glGenVertexArrays();
//        GL30.glBindVertexArray(vertexarrays);
        int buffer = GL15.glGenBuffers();

//        buffer_object_array[1] = vertexarrays;
        buffer_object_array[1] = buffer;
        buffer_object_array[2] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[1]);
        buffer_object_array[3] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[2]);
        // buffer_object_array[3] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[3]);

        // buffer_object_array[8] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[11]);
//        buffer_object_array[5] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[7]);
        buffer_object_array[4] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[7]);
        buffer_object_array[5] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[8]);
        buffer_object_array[6] = OpenGLBuffer.loadFloatBuffer((FloatBuffer)object_array[9]);

//        set(object_array);
    }
    public static void set(Object[] object_array)
    {
        Object[] buffer_object_array = (Object[])object_array[6];
        Object[] attriblocation_object_array = (Object[])((Object[])((Object[])buffer_object_array[0])[0])[4];

//        GL30.glBindVertexArray((int)buffer_object_array[1]);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, (int)buffer_object_array[1]);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)object_array[0], GL15.GL_STATIC_DRAW);

        OpenGLBuffer.setFloatBuffer((int)attriblocation_object_array[0], (int)buffer_object_array[2], 3);
        OpenGLBuffer.setFloatBuffer((int)attriblocation_object_array[1], (int)buffer_object_array[3], 2);
        // OpenGLBuffer.setFloatBuffer(2, (int)buffer_object_array[3], 3);

        // OpenGLBuffer.setFloatBuffer(2, (int)buffer_object_array[8], (byte)object_array[18]);
//        OpenGLBuffer.setFloatBuffer(2, (int)buffer_object_array[5], 4);
        OpenGLBuffer.setFloatBuffer((int)attriblocation_object_array[2], (int)buffer_object_array[4], 4);
        OpenGLBuffer.setFloatBuffer((int)attriblocation_object_array[3], (int)buffer_object_array[5], 4);
        OpenGLBuffer.setFloatBuffer((int)attriblocation_object_array[4], (int)buffer_object_array[6], 3);
    }
    public static void delete(Object[] object_array)
    {
        Object[] buffer_object_array = (Object[])object_array[6];

        if (buffer_object_array[1] != null)
        {
            GL15.glDeleteBuffers((int)buffer_object_array[2]);
            GL15.glDeleteBuffers((int)buffer_object_array[3]);
            GL15.glDeleteBuffers((int)buffer_object_array[4]);
            GL15.glDeleteBuffers((int)buffer_object_array[5]);
            GL15.glDeleteBuffers((int)buffer_object_array[6]);
            GL15.glDeleteBuffers((int)buffer_object_array[1]);
//                GL30.glDeleteVertexArrays((int)buffer_object_array[-1]);

            buffer_object_array[1] = null;
        }
    }
}
