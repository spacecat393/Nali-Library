package com.nali.system.opengl.shader;

import com.nali.Nali;
import com.nali.config.MyConfig;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class OpenGLSkinningShader
{
    public static void create(Object[] object_array)
    {
        int program = GL20.glCreateProgram();
        // int max_bones = ((ArrayList<Integer>)object_array[15]).size();
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
//            int error = GL11.glGetError();
//            if (error != GL11.GL_NO_ERROR)
//            {
//                Nali.LOGGER.error(error);
//            }
            Nali.LOGGER.error(GL20.glGetProgramInfoLog(program, 1024));
            FMLCommonHandler.instance().exitJava(-1, true);
        }

        attriblocation_object_array[0] = GL20.glGetAttribLocation(program, "vertices");
        attriblocation_object_array[1] = GL20.glGetAttribLocation(program, "texcoord");
        // GL20.glGetAttribLocation(program, "normals");
        attriblocation_object_array[2] = GL20.glGetAttribLocation(program, "weights");
        attriblocation_object_array[3] = GL20.glGetAttribLocation(program, "joints");

        // attriblocation_object_array[0] = 0;
        // attriblocation_object_array[1] = 1;
        // attriblocation_object_array[2] = 2;
        // attriblocation_object_array[3] = 3;

        GL20.glBindAttribLocation(program, 0, "vertices");
        GL20.glBindAttribLocation(program, 1, "texcoord");
        // GL20.glBindAttribLocation(program, 2, "normals");
        GL20.glBindAttribLocation(program, 2, "joints");
        GL20.glBindAttribLocation(program, 3, "weights");

        Object[] uniform_string_object_array = (Object[])object_array[1];
        int uniform_length = 0;

        if (uniform_string_object_array != null)
        {
            uniform_length = uniform_string_object_array.length;
        }

        boolean multi_uniform = MyConfig.USING_MULTI_UNIFORM;

        int max_bones = ((int)object_array[4]);
        // Object[] uniform_object_array = new Object[6];
        Object[] uniform_object_array = new Object[5 + (multi_uniform ? max_bones : 1)];

//        uniform_object_array[0] = GL20.glGetUniformLocation(program, "bindposes");
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

        if (multi_uniform)
        {
            for (int i = 0; i < max_bones; ++i)
            {
                uniform_object_array[i + 5] = GL20.glGetUniformLocation(program, "animation" + i);
                // uniform_object_array[i + 5] = 5 + i;
            }

            for (int i = 0; i < uniform_length; ++i)
            {
                String[] string_array = (String[])uniform_string_object_array[i];
                uniform_object_array[5 + max_bones + i] = GL20.glGetUniformLocation(program, string_array[0]);
                // uniform_object_array[i + 5] = 5 + max_bones + i;
            }
        }
        else
        {
            uniform_object_array[5] = GL20.glGetUniformLocation(program, "animation");
            // uniform_object_array[5] = 5;

            for (int i = 0; i < uniform_length; ++i)
            {
                String[] string_array = (String[])uniform_string_object_array[i];
                uniform_object_array[5 + i] = GL20.glGetUniformLocation(program, string_array[0]);
                // uniform_object_array[5 + i] = 5 + i;
            }
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

    public static String getVertexHeaderShaderString(Object[] object_array, boolean vulkan_shader)
    {
        return "#version 430\n" +
        "precision highp float;\n" +

        "layout(location = 0) in vec3 vertices;\n" +
        "layout(location = 1) in vec2 texcoord;\n" +

        "layout(location = 2) in ivec4 joints;\n" +
        "layout(location = 3) in vec4 weights;\n";
    }
}
