package com.nali.system;

import com.nali.Nali;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.memory.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class DataLoader
{
    public Object[] memory_object_array;
    public OpenGLTextureMemory opengltexturememorydata;
    public OpenGLObjectShaderMemory[] openglobjectshadermemory_array;

    public static void setModels(DataLoader dataloader, String mod_id_string, String shaders_folder_path, boolean vulkan_shader)
    {
        mod_id_string = Reference.MOD_ID + '/' + mod_id_string;
        DataLoader.check(mod_id_string);

        String folder_path = mod_id_string + '/';

        dataloader.opengltexturememorydata = new OpenGLTextureMemory(mod_id_string);

        Object[] model_object_array = FileDataReader.getMixXStringArray(folder_path + "ModelsList");
        int model_length = model_object_array.length;
        dataloader.memory_object_array = new Object[model_length];

        String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "ShaderList");
        for (int i = 0; i < model_length; ++i)
        {
            String[] model_string_array = (String[])model_object_array[i];

            switch (model_string_array.length)
            {
                case 1:
                {
                    dataloader.memory_object_array[i] = new OpenGLAnimationMemory(model_string_array, folder_path);
                    break;
                }
                case 4:
                {
                    dataloader.memory_object_array[i] = new OpenGLObjectMemory(model_string_array, folder_path, shader_string_2d_array, shaders_folder_path);
                    break;
                }
                case 5:
                {
                    dataloader.memory_object_array[i] = new OpenGLSkinningMemory(model_string_array, folder_path, shader_string_2d_array, shaders_folder_path);
                    break;
                }
                default:
                {
                    Nali.error(model_string_array[0] + " CORRUPTED_DATA");
                    break;
                }
            }
        }

        int shader_length = shader_string_2d_array.length;
        dataloader.openglobjectshadermemory_array = new OpenGLObjectShaderMemory[shader_length];

        for (int i = 0; i < shader_length; ++i)
        {
            String[] shader_string_array = shader_string_2d_array[i];

            if (shader_string_array.length == 3)
            {
                dataloader.openglobjectshadermemory_array[i] = new OpenGLSkinningShaderMemory(shader_string_array, folder_path, shaders_folder_path, vulkan_shader);
            }
            else
            {
                dataloader.openglobjectshadermemory_array[i] = new OpenGLObjectShaderMemory(shader_string_array, folder_path, shaders_folder_path, vulkan_shader);
            }
        }

        for (int i = 0; i < model_length; ++i)
        {
            if (dataloader.memory_object_array[i] instanceof OpenGLObjectMemory)
            {
                OpenGLObjectMemory openglobjectmemorydata = (OpenGLObjectMemory)dataloader.memory_object_array[i];
                int shader_id = (int)openglobjectmemorydata.shader;
                openglobjectmemorydata.shader = dataloader.openglobjectshadermemory_array[shader_id];
            }
        }
    }

    public static void check(String mod_id_string)
    {
        File file = new File(mod_id_string);
        if (!file.isDirectory())
        {
            Nali.error('\"' + file.getPath() + "\" NOT_FOUND");
        }
    }
}
