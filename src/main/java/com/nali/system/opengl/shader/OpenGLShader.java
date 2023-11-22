package com.nali.system.opengl.shader;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.nali.Nali;

import net.minecraftforge.fml.common.FMLCommonHandler;

@SideOnly(Side.CLIENT)
public class OpenGLShader
{
    public static int load(Object[] object_array, int id, int type)
    {
        // byte[] byte_array = null;

        // try
        // {
        //     byte_array = IOUtils.toByteArray(((StringBuilder)object_array[id]).toString());
        // }
        // catch (IOException ioexception)
        // {
        //     throw new RuntimeException(ioexception);
        // }

        // ByteBuffer bytebuffer = BufferUtils.createByteBuffer(byte_array.length);
        // bytebuffer.put(byte_array);
        // bytebuffer.position(0);

        int shader = GL20.glCreateShader(type);
        GL20.glShaderSource(shader, (StringBuilder)object_array[id]);
        GL20.glCompileShader(shader);
        // GL20.glShaderSource(shader, bytebuffer);
        // GL20.glCompileShader(shader);
        // bytebuffer.clear();

        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            Nali.LOGGER.error(GL20.glGetShaderInfoLog(shader, 1024));
            FMLCommonHandler.instance().exitJava(-1, true);
        }

        return shader;
    }

    public static void delete(int shader)
    {
        GL20.glDeleteShader(shader);
    }
}
