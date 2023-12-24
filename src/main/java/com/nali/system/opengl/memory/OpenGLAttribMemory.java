package com.nali.system.opengl.memory;

import com.nali.system.opengl.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.FloatBuffer;

@SideOnly(Side.CLIENT)
public class OpenGLAttribMemory
{
    //float[N] -> FloatBuffer
    public Object object;
    public int buffer;
    public byte size;

    public OpenGLAttribMemory(float[] float_array, byte size)
    {
        this.object = OpenGLBuffer.createFloatBuffer(float_array, true);
        this.buffer = OpenGLBuffer.loadFloatBuffer((FloatBuffer)this.object);
        this.size = size;
    }

    public OpenGLAttribMemory(Object object, int buffer, byte size)
    {
        this.object = object;
        this.buffer = buffer;
        this.size = size;
    }
}
