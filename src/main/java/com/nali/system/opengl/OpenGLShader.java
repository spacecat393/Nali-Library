package com.nali.system.opengl;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class OpenGLShader
{
    public static int loadBuffer(ByteBuffer bytebuffer, int type)
    {
        int shader = OpenGlHelper.glCreateShader(type);
//        int shader = OpenGlHelper.glCreateShader(type);
//        OpenGlHelper.glShaderSource(shader, stringbuilder);
//        OpenGlHelper.glShaderSource(shader, bytebuffer);
        OpenGlHelper.glShaderSource(shader, bytebuffer);
//        OpenGlHelper.glCompileShader(shader);
        OpenGlHelper.glCompileShader(shader);

//        if (OpenGlHelper.glGetShaderi(shader, OpenGlHelper.GL_COMPILE_STATUS) == OpenGlHelper.GL_FALSE)
        if (OpenGlHelper.glGetShaderi(shader, OpenGlHelper.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
//            int error = OpenGlHelper.glGetError();
//            if (error != OpenGlHelper.GL_NO_ERROR)
//            {
//                Nali.error(GLU.gluErrorString(error));
//            }
//            Nali.error(OpenGlHelper.glGetShaderInfoLog(shader, 1024));
            I.error(OpenGlHelper.glGetShaderInfoLog(shader, 1024));
        }

        return shader;
    }

//    public static void delete(int shader)
//    {
////        OpenGlHelper.glDeleteShader(shader);
//        OpenGlHelper.glDeleteShader(shader);
//    }
}
