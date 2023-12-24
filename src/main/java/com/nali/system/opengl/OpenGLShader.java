package com.nali.system.opengl;

import com.nali.Nali;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.glu.GLU;

@SideOnly(Side.CLIENT)
public class OpenGLShader
{
    public static int loadBuffer(StringBuilder stringbuilder, int type)
    {
        int shader = GL20.glCreateShader(type);
        GL20.glShaderSource(shader, stringbuilder);
        GL20.glCompileShader(shader);

        if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
        {
            int error = GL11.glGetError();
            if (error != GL11.GL_NO_ERROR)
            {
                Nali.error(GLU.gluErrorString(error));
            }

            Nali.error(GL20.glGetShaderInfoLog(shader, 1024));
        }

        return shader;
    }

    public static void delete(int shader)
    {
        GL20.glDeleteShader(shader);
    }
}
