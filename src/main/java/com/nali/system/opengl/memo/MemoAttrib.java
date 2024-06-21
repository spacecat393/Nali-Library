package com.nali.system.opengl.memo;

import com.nali.system.opengl.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.ByteBuffer;

@SideOnly(Side.CLIENT)
public class MemoAttrib
{
    //float[N] -> FloatBuffer
    public Object object;
    public int buffer;
    public byte size;

    public MemoAttrib(float[] float_array, byte size)
    {
        this.object = OpenGLBuffer.createFloatByteBuffer(float_array, true);
        this.buffer = OpenGLBuffer.loadFloatBuffer((ByteBuffer)this.object);
        this.size = size;
    }

    public MemoAttrib(Object object, int buffer, byte size)
    {
        this.object = object;
        this.buffer = buffer;
        this.size = size;
    }
}
