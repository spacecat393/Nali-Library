package com.nali.system.opengl.memo;

import com.nali.NaliConfig;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.OpenGLShader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Paths;

import static com.nali.Nali.I;
import static com.nali.Nali.ID;
import static com.nali.system.opengl.OpenGLBuffer.getFrom;

@SideOnly(Side.CLIENT)
public class OpenGLObjectShaderMemo
{
    public static String GL_SHADING_LANGUAGE_VERSION;
    public int program;
    public int[] attriblocation_int_array;
    public int[] uniformlocation_int_array;
    //StringBuilder->ShaderBuffer->ByteBuffer
    public Object vert_shader;
    //StringBuilder->ShaderBuffer->ByteBuffer
    public Object frag_shader;

    public OpenGLObjectShaderMemo(String[] shader_string_array/*, String folder_path*/)
    {
        if (OpenGLCurrentMemo.SHADERS < NaliConfig.SHADER.max_shaders)
        {
            String shader_folder_string = ID + "/" + shader_string_array[0];
            String modid_folder_string = ID + "/" + shader_string_array[2];
            ++OpenGLCurrentMemo.SHADERS;

            if (GL_SHADING_LANGUAGE_VERSION == null)
            {
                GL_SHADING_LANGUAGE_VERSION = GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION);

                if (GL_SHADING_LANGUAGE_VERSION.contains("4.60"))
                {
                    GL_SHADING_LANGUAGE_VERSION = "4.60";
                }
//                else if (!new File(folder_path + "Shader/" + GL_SHADING_LANGUAGE_VERSION).isDirectory())
                else if (!new File(modid_folder_string + "Shader/" + GL_SHADING_LANGUAGE_VERSION).isDirectory())
                {
                    String gl_version = GL11.glGetString(GL11.GL_VERSION);
                    I.logger.info("GL_VERSION " + gl_version);
                    I.error("GL_SHADING_LANGUAGE_VERSION " + GL_SHADING_LANGUAGE_VERSION);
                }
            }

            shader_folder_string += "/Shader/" + GL_SHADING_LANGUAGE_VERSION;
//            byte shader_state = Byte.parseByte(shader_string_array[3]);
//            this.readVertShader(shader_string_array, folder_path, shader_state);
            this.readVertShader(shader_string_array, shader_folder_string/*, shader_state*/);
//            this.readFragShader(shader_string_array, folder_path, shader_state);
            this.readFragShader(shader_string_array, shader_folder_string/*, shader_state*/);
//            this.createBuffer(shader_string_array, folder_path/*, 0*/);
            this.createBuffer(shader_string_array, modid_folder_string/*, 0*/);
        }
        else
        {
            I.error("MAX_SHADERS");
        }
    }

    public void readVertShader(String[] shader_string_array, String folder_path/*, byte shader_state*/)
    {
//        byte shader_state = Byte.parseByte(shader_string_array[1]);
        this.vert_shader = new StringBuilder();
        StringReader.append((StringBuilder)this.vert_shader, folder_path + "/Vertex" + shader_string_array[3]);
    }

    public void readFragShader(String[] shader_string_array, String folder_path/*, byte shader_state*/)
    {
//        byte shader_state = Byte.parseByte(shader_string_array[1]);
        this.frag_shader = new StringBuilder();
        StringReader.append((StringBuilder)this.frag_shader, folder_path + "/Fragment" + shader_string_array[4]);
    }

    public String[][] getAttribLocationString2DArray(String shader_data_folder_string)
    {
        return FileDataReader.getMixXStringArray(Paths.get(shader_data_folder_string + "Attrib"));
    }

    public String[][] getUniformString2DArray(String shader_data_folder_string)
    {
        return FileDataReader.getMixXStringArray(Paths.get(shader_data_folder_string + "Uniform"));
    }

    public void createBuffer(String[] shader_string_array, String folder_path/*, int max_bones*/)
    {
        String model_folder_string = folder_path + "/Shader/" + shader_string_array[1] + "/";
        this.createShaderBuffer();
        String[][] attriblocation_string_2d_array = this.getAttribLocationString2DArray(model_folder_string);
        this.attriblocation_int_array = new int[attriblocation_string_2d_array.length];
        for (int i = 0; i < attriblocation_string_2d_array.length; ++i)
        {
            this.attriblocation_int_array[i] = OpenGlHelper.glGetAttribLocation(this.program, attriblocation_string_2d_array[i][0]);
        }

        String[][] uniform_string_2d_array = this.getUniformString2DArray(model_folder_string);
        this.uniformlocation_int_array = new int[uniform_string_2d_array.length/* + max_bones*/];
        for (int i = 0; i < uniform_string_2d_array.length; ++i)
        {
            this.uniformlocation_int_array[i] = OpenGlHelper.glGetUniformLocation(this.program, uniform_string_2d_array[i][0]);
        }
    }

    public void createShaderBuffer()
    {
//        this.program = OpenGlHelper.glCreateProgram();
        this.program = OpenGlHelper.glCreateProgram();

        this.vert_shader = getFrom((StringBuilder)this.vert_shader);
        this.frag_shader = getFrom((StringBuilder)this.frag_shader);

        this.vert_shader = OpenGLShader.loadBuffer((ByteBuffer)this.vert_shader, OpenGlHelper.GL_VERTEX_SHADER);
        this.frag_shader = OpenGLShader.loadBuffer((ByteBuffer)this.frag_shader, OpenGlHelper.GL_FRAGMENT_SHADER);

//        OpenGlHelper.glAttachShader(this.program, (int)this.vert_shader);
        OpenGlHelper.glAttachShader(this.program, (int)this.vert_shader);
//        OpenGlHelper.glAttachShader(this.program, (int)this.frag_shader);
        OpenGlHelper.glAttachShader(this.program, (int)this.frag_shader);

        OpenGlHelper.glLinkProgram(this.program);
        GL20.glValidateProgram(this.program);

        if (OpenGlHelper.glGetProgrami(this.program, OpenGlHelper.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
//            int error = OpenGlHelper.glGetError();
//            if (error != OpenGlHelper.GL_NO_ERROR)
//            {
//                Nali.error(GLU.gluErrorString(error));
//            }
//            String string = OpenGlHelper.glGetProgramInfoLog(this.program, 1024);
//            if (!string.isEmpty())
//            {
//                Nali.error(string);
//            }
            I.error(OpenGlHelper.glGetProgramInfoLog(this.program, 1024));
        }
    }

//    public void deleteBuffer()
//    {
//        OpenGlHelper.glDeleteProgram(this.program);
//        OpenGLShader.delete((int)this.vert_shader);
//        OpenGLShader.delete((int)this.frag_shader);
//    }

//    public int getMaxBones()
//    {
//        return 0;
//    }
}
