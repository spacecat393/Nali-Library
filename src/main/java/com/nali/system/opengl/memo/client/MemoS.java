package com.nali.system.opengl.memo.client;

import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.file.Paths;
import java.util.Arrays;

import static com.nali.Nali.*;

@SideOnly(Side.CLIENT)
public class MemoS
{
    public int program;
    public int[] attriblocation_int_array;
    public int[] uniformlocation_int_array;
    //StringBuilder->ShaderBuffer->ByteBuffer
    public int vert_shader;
    //StringBuilder->ShaderBuffer->ByteBuffer
    public int frag_shader;

    public MemoS(/*MemoHVo memohvo, MemoHFo memohfo*/int vert_shader, int frag_shader, String[] shader_string_array/*, String folder_path*/)
    {
//        this.vert_shader = memohvo.shader;
//        this.frag_shader = memohfo.shader;
        this.vert_shader = vert_shader;
        this.frag_shader = frag_shader;
        this.createBuffer(shader_string_array/*, folder_path*/);
//        if (MemoCurrent.SHADERS < NaliConfig.SHADER.max_shaders)
//        {
////            String shader_folder_string = ID + "/" + shader_string_array[0];
////            String modid_folder_string = ID + "/" + shader_string_array[2];
////            ++MemoCurrent.SHADERS;
////
////            if (GL_SHADING_LANGUAGE_VERSION == null)
////            {
////                GL_SHADING_LANGUAGE_VERSION = GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION);
////
////                if (GL_SHADING_LANGUAGE_VERSION.contains("4.60"))
////                {
////                    GL_SHADING_LANGUAGE_VERSION = "4.60";
////                }
//////                else if (!new File(folder_path + "Shader/" + GL_SHADING_LANGUAGE_VERSION).isDirectory())
////                else if (!new File(modid_folder_string + "Shader/" + GL_SHADING_LANGUAGE_VERSION).isDirectory())
////                {
////                    String gl_version = GL11.glGetString(GL11.GL_VERSION);
////                    I.logger.info("GL_VERSION " + gl_version);
////                    I.error("GL_SHADING_LANGUAGE_VERSION " + GL_SHADING_LANGUAGE_VERSION);
////                }
////            }
////
////            shader_folder_string += "/Shader/" + GL_SHADING_LANGUAGE_VERSION;
//////            byte shader_state = Byte.parseByte(shader_string_array[3]);
//////            this.readVertShader(shader_string_array, folder_path, shader_state);
////            this.readVertShader(shader_string_array, shader_folder_string/*, shader_state*/);
//////            this.readFragShader(shader_string_array, folder_path, shader_state);
////            this.readFragShader(shader_string_array, shader_folder_string/*, shader_state*/);
//////            this.createBuffer(shader_string_array, folder_path/*, 0*/);
//            this.vert_shader =;
//            this.frag_shader =;
////            this.createBuffer(/*shader_string_array, modid_folder_string*//*, 0*/);
//        }
//        else
//        {
//            I.error("MAX_SHADERS");
//        }
    }

//    public void readVertShader(String[] shader_string_array, String folder_path/*, byte shader_state*/)
//    {
////        byte shader_state = Byte.parseByte(shader_string_array[1]);
//        this.vert_shader = new StringBuilder();
//        StringReader.append((StringBuilder)this.vert_shader, folder_path + "/Vertex" + shader_string_array[3]);
//    }
//
//    public void readFragShader(String[] shader_string_array, String folder_path/*, byte shader_state*/)
//    {
////        byte shader_state = Byte.parseByte(shader_string_array[1]);
//        this.frag_shader = new StringBuilder();
//        StringReader.append((StringBuilder)this.frag_shader, folder_path + "/Fragment" + shader_string_array[4]);
//    }

//    public String[][] getAttribLocationString2DArray(String shader_data_folder_string)
//    {
//        return FileDataReader.getMixXStringArray(Paths.get(shader_data_folder_string + "Attrib"));
//    }

//    public String[][] getUniformString2DArray(String shader_data_folder_string)
//    {
//        return FileDataReader.getMixXStringArray(Paths.get(shader_data_folder_string + "Uniform"));
//    }

    public void createBuffer(String[] shader_string_array/*, String folder_path*//*, int max_bones*/)
    {
        String folder_path = ID + "/" + shader_string_array[0];
        String model_folder_string = folder_path + "/shader/" + shader_string_array[1] + "/";
        this.createShaderBuffer(shader_string_array);
//        String[][] attriblocation_string_2d_array = this.getAttribLocationString2DArray(model_folder_string);
        String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(model_folder_string + "attrib.dat"));
        this.attriblocation_int_array = new int[attriblocation_string_2d_array.length];
        for (int i = 0; i < attriblocation_string_2d_array.length; ++i)
        {
            this.attriblocation_int_array[i] = OpenGlHelper.glGetAttribLocation(this.program, attriblocation_string_2d_array[i][0]);
        }

//        String[][] uniform_string_2d_array = this.getUniformString2DArray(model_folder_string);
        String[][] uniform_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(model_folder_string + "uniform.dat"));
        this.uniformlocation_int_array = new int[uniform_string_2d_array.length/* + max_bones*/];
        for (int i = 0; i < uniform_string_2d_array.length; ++i)
        {
            this.uniformlocation_int_array[i] = OpenGlHelper.glGetUniformLocation(this.program, uniform_string_2d_array[i][0]);
        }
    }

    public void createShaderBuffer(String[] error_string_array)
    {
//        this.program = OpenGlHelper.glCreateProgram();
        this.program = OpenGlHelper.glCreateProgram();

//        this.vert_shader = getFrom((StringBuilder)this.vert_shader);
//        this.frag_shader = getFrom((StringBuilder)this.frag_shader);

//        this.vert_shader = OpenGLShader.loadBuffer((ByteBuffer)this.vert_shader, OpenGlHelper.GL_VERTEX_SHADER);
//        this.frag_shader = OpenGLShader.loadBuffer((ByteBuffer)this.frag_shader, OpenGlHelper.GL_FRAGMENT_SHADER);

//        OpenGlHelper.glAttachShader(this.program, (int)this.vert_shader);
        OpenGlHelper.glAttachShader(this.program, this.vert_shader);
//        OpenGlHelper.glAttachShader(this.program, (int)this.frag_shader);
        OpenGlHelper.glAttachShader(this.program, this.frag_shader);

        OpenGlHelper.glLinkProgram(this.program);
        GL20.glValidateProgram(this.program);

        if (OpenGlHelper.glGetProgrami(this.program, OpenGlHelper.GL_LINK_STATUS) == GL11.GL_FALSE)
        {
            warn("F " + Arrays.toString(error_string_array));
//            warn("S " + S_LIST.size());
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
            error(OpenGlHelper.glGetProgramInfoLog(this.program, 32768));
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
