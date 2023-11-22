package com.nali.system.opengl.shader;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import com.nali.Nali;

import net.minecraftforge.fml.common.FMLCommonHandler;

@SideOnly(Side.CLIENT)
public class OpenGLObjectShader
{
    public static void create(Object[] object_array)
    {
        int program = GL20.glCreateProgram();

        Object[] temp_object_array = (Object[])object_array[0];
        Object[] attriblocation_object_array = (Object[])temp_object_array[4];
        temp_object_array[0] = OpenGLShader.load(object_array, 2, GL20.GL_VERTEX_SHADER);
        temp_object_array[1] = OpenGLShader.load(object_array, 3, GL20.GL_FRAGMENT_SHADER);

        GL20.glAttachShader(program, (int)temp_object_array[0]);
        GL20.glAttachShader(program, (int)temp_object_array[1]);

        GL20.glLinkProgram(program);
        GL20.glValidateProgram(program);

        int status = GL20.glGetProgrami(program, GL20.GL_LINK_STATUS);
        if (status == GL11.GL_FALSE)
        {
            String log = GL20.glGetProgramInfoLog(program, 1024);
            Nali.LOGGER.error("OpenGLObjectShader:\n" + log);
            FMLCommonHandler.instance().exitJava(-1, true);
        }

        attriblocation_object_array[0] = GL20.glGetAttribLocation(program, "vertices");
        attriblocation_object_array[1] = GL20.glGetAttribLocation(program, "texcoord");
        // GL20.glGetAttribLocation(program, "normals");

        // attriblocation_object_array[0] = 0;
        // attriblocation_object_array[1] = 1;

        GL20.glBindAttribLocation(program, 0, "vertices");
        GL20.glBindAttribLocation(program, 1, "texcoord");
        // GL20.glBindAttribLocation(program, 2, "normals");

        Object[] uniform_string_object_array = (Object[])object_array[1];
        int uniform_length = 0;

        if (uniform_string_object_array != null)
        {
            uniform_length = uniform_string_object_array.length;
        }

        Object[] uniform_object_array = new Object[5 + uniform_length];

        uniform_object_array[0] = GL20.glGetUniformLocation(program, "project");
        uniform_object_array[1] = GL20.glGetUniformLocation(program, "view");
        uniform_object_array[2] = GL20.glGetUniformLocation(program, "world");
        uniform_object_array[3] = GL20.glGetUniformLocation(program, "light_color");
        uniform_object_array[4] = GL20.glGetUniformLocation(program, "texture_sampler");

        // uniform_object_array[0] = 0;
        // uniform_object_array[1] = 1;
        // uniform_object_array[2] = 2;
        // uniform_object_array[3] = 3;
        // uniform_object_array[4] = 4;

        for (int i = 0; i < uniform_length; ++i)
        {
            String[] string_array = (String[])uniform_string_object_array[i];
            uniform_object_array[5 + i] = GL20.glGetUniformLocation(program, string_array[0]);
            // uniform_object_array[5 + i] = 5 + i;
        }

        temp_object_array[2] = program;
        temp_object_array[3] = uniform_object_array;
    }

    public static void delete(Object[] object_array)
    {
        Object[] temp_object_array = (Object[])object_array[0];

        GL20.glDeleteProgram((int)temp_object_array[2]);
        OpenGLShader.delete((int)temp_object_array[0]);
        OpenGLShader.delete((int)temp_object_array[1]);
    }
}
