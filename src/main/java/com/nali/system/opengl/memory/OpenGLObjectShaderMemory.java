package com.nali.system.opengl.memory;

import com.nali.Nali;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.OpenGLShader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class OpenGLObjectShaderMemory
{
    public int program;
    public int[] attriblocation_int_array;
    public int[] uniformlocation_int_array;
    //StringBuilder->ShaderBuffer
    public Object vert_shader;
    //StringBuilder->ShaderBuffer
    public Object frag_shader;

    public OpenGLObjectShaderMemory(String[] shader_string_array, String folder_path, String shaders_folder_path, boolean vulkan_shader)
    {
        this.readVertShader(shader_string_array, folder_path, shaders_folder_path, vulkan_shader);
        this.readFragShader(shader_string_array, folder_path, shaders_folder_path, vulkan_shader);
        this.createBuffer(folder_path + shaders_folder_path + shader_string_array[0]);
    }

    public void readVertShader(String[] shader_string_array, String folder_path, String shaders_folder_path, boolean vulkan_shader)
    {
        byte shader_state = (byte)Integer.parseInt(shader_string_array[1]);

        this.vert_shader = new StringBuilder();
        StringReader.append((StringBuilder)this.vert_shader, folder_path + shaders_folder_path + "Vertex" + shader_state);
    }

    public void readFragShader(String[] shader_string_array, String folder_path, String shaders_folder_path, boolean vulkan_shader)
    {
        byte shader_state = (byte)Integer.parseInt(shader_string_array[1]);

        this.frag_shader = new StringBuilder();
        StringReader.append((StringBuilder)this.frag_shader, folder_path + shaders_folder_path + "Fragment" + shader_state);
    }

    public String[][] getAttribLocationString2DArray(String shader_data_folder_string)
    {
        return FileDataReader.getMixXStringArray(shader_data_folder_string + "/Attrib");
    }

    public String[][] getUniformString2DArray(String shader_data_folder_string)
    {
        return FileDataReader.getMixXStringArray(shader_data_folder_string + "/Uniform");
    }

    public void createBuffer(String model_folder_string)
    {
        this.createShaderBuffer();
        String[][] attriblocation_string_2d_array = this.getAttribLocationString2DArray(model_folder_string);
        int max_bones = this.getMaxBones();
        this.attriblocation_int_array = new int[attriblocation_string_2d_array.length];
        for (int i = 0; i < attriblocation_string_2d_array.length; ++i)
        {
            this.attriblocation_int_array[i] = GL20.glGetAttribLocation(this.program, attriblocation_string_2d_array[i][0]);
        }

        String[][] uniform_string_2d_array = this.getUniformString2DArray(model_folder_string);
        this.uniformlocation_int_array = new int[uniform_string_2d_array.length + max_bones];
        for (int i = 0; i < uniform_string_2d_array.length; ++i)
        {
            this.uniformlocation_int_array[i] = GL20.glGetUniformLocation(this.program, uniform_string_2d_array[i][0]);
        }
    }

    public void createShaderBuffer()
    {
        this.program = GL20.glCreateProgram();
        this.vert_shader = OpenGLShader.loadBuffer((StringBuilder)this.vert_shader, GL20.GL_VERTEX_SHADER);
        this.frag_shader = OpenGLShader.loadBuffer((StringBuilder)this.frag_shader, GL20.GL_FRAGMENT_SHADER);

        GL20.glAttachShader(this.program, (int)this.vert_shader);
        GL20.glAttachShader(this.program, (int)this.frag_shader);

        GL20.glLinkProgram(this.program);
        GL20.glValidateProgram(this.program);

        if (GL20.glGetProgrami(this.program, GL20.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
//            int error = GL11.glGetError();
//            if (error != GL11.GL_NO_ERROR)
//            {
//                Nali.error(GLU.gluErrorString(error));
//            }
            Nali.error(GL20.glGetProgramInfoLog(this.program, 1024));
        }
    }

    public void deleteBuffer()
    {
        GL20.glDeleteProgram(this.program);
        OpenGLShader.delete((int)this.vert_shader);
        OpenGLShader.delete((int)this.frag_shader);
    }

    public int getMaxBones()
    {
        return 0;
    }
}
