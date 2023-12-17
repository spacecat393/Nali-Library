package com.nali.system.opengl;

public class OpenGLMemoryData
{
    //int[N] -> IntBuffer
    public Object index;
    //float[3] -> FloatBuffer
    public Object vertices;
    //float[2] -> FloatBuffer
    public Object texcoord;
    //float[3] -> FloatBuffer
    public Object normals;
    public int index_length;
    //culling, texture_state
    public byte state;
    public OpenGLShaderBufferData openglshaderbufferdata;
}
