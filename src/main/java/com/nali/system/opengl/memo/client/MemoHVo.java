package com.nali.system.opengl.memo.client;

import com.nali.system.StringReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MemoHVo extends MemoH
{
    public MemoHVo(String file_string/*String[] shader_string_array*/)
    {
//        String shader_folder_string = ID + "/" + shader_string_array[0];
////        String modid_folder_string = ID + "/" + shader_string_array[2] + "/Shader/" + NaliConfig.SHADER.gl_shading_language_version;
//        this.shader = genShader(getFrom(this.get(shader_string_array, shader_folder_string/*, shader_state*/)), OpenGlHelper.GL_VERTEX_SHADER);
        StringBuilder stringbuilder = new StringBuilder();
        StringReader.append(stringbuilder, file_string);

        this.shader = genShader(getFrom(stringbuilder), OpenGlHelper.GL_VERTEX_SHADER, file_string);
    }
//    public static String GL_SHADING_LANGUAGE_VERSION;

//    public MemoHVo(String[] shader_string_array)
//    {
//////        if (MemoCurrent.SHADERS < NaliConfig.SHADER.max_shaders)
//////        {
////        String shader_folder_string = ID + "/" + shader_string_array[0];
////        String modid_folder_string = ID + "/" + shader_string_array[2];
//////            ++MemoCurrent.SHADERS;
////
//////        if (GL_SHADING_LANGUAGE_VERSION == null)
//////        {
//////            GL_SHADING_LANGUAGE_VERSION = GL11.glGetString(GL20.GL_SHADING_LANGUAGE_VERSION);
//////
//////            if (GL_SHADING_LANGUAGE_VERSION.contains("4.60"))
//////            {
//////                GL_SHADING_LANGUAGE_VERSION = "4.60";
//////            }
////////                else if (!new File(folder_path + "Shader/" + GL_SHADING_LANGUAGE_VERSION).isDirectory())
//////            else if (!new File(modid_folder_string + "Shader/" + GL_SHADING_LANGUAGE_VERSION).isDirectory())
//////            {
//////                String gl_version = GL11.glGetString(GL11.GL_VERSION);
//////                I.logger.info("GL_VERSION " + gl_version);
//////                error("GL_SHADING_LANGUAGE_VERSION " + GL_SHADING_LANGUAGE_VERSION);
//////            }
//////        }
////
////        shader_folder_string += "/Shader/" + NaliConfig.SHADER.gl_shading_language_version;
//
//        this.readVertShader(shader_string_array, shader_folder_string/*, shader_state*/);
//
//        this.vert_shader = this.getFrom((StringBuilder)this.vert_shader);
//        this.vert_shader = this.genBuffer((ByteBuffer)this.vert_shader, OpenGlHelper.GL_VERTEX_SHADER);
//        this.createBuffer(shader_string_array, modid_folder_string/*, 0*/);
////        }
////        else
////        {
////            error("MAX_SHADERS");
////        }
//    }

//    @Override
//    public void gen(String[] shader_string_array, String shader_folder_string, String modid_folder_string)
//    {
//    }

//    public StringBuilder get(String[] shader_string_array, String folder_path/*, byte shader_state*/)
//    {
////        byte shader_state = Byte.parseByte(shader_string_array[1]);
//        StringBuilder stringbuilder = new StringBuilder();
//        StringReader.append(stringbuilder, folder_path + "/vert/" + shader_string_array[3] + ".vert");
//        return stringbuilder;
//    }
}
