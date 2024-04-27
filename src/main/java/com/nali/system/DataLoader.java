package com.nali.system;

import com.nali.Nali;
import com.nali.config.MyConfig;
import com.nali.data.BothData;
import com.nali.data.GuiObjectData;
import com.nali.render.ObjectRender;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memory.OpenALMemory;
import com.nali.system.opengl.memory.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.Comparator;
import java.util.List;

@SideOnly(Side.CLIENT)
public class DataLoader
{
    public static Object[][] MIX_MEMORY_OBJECT_ARRAY;
    public Object[] memory_object_array;
    public OpenGLTextureMemory opengltexturememory;
    public OpenGLObjectShaderMemory[] openglobjectshadermemory_array;
    public OpenALMemory openalmemory;

    public static void setModels(DataLoader dataloader, String mod_id_string)
    {
        mod_id_string = Reference.MOD_ID + '/' + mod_id_string;
        DataLoader.check(mod_id_string);

        String folder_path = mod_id_string + '/';

        dataloader.opengltexturememory = new OpenGLTextureMemory(folder_path);

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
                    dataloader.memory_object_array[i] = new OpenGLObjectMemory(model_string_array, folder_path, shader_string_2d_array);
                    break;
                }
                case 5:
                {
                    dataloader.memory_object_array[i] = new OpenGLSkinningMemory(model_string_array, folder_path, shader_string_2d_array);
                    break;
                }
                default:
                {
                    Nali.error(model_string_array[0] + " CORRUPTED_DATA");
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
                dataloader.openglobjectshadermemory_array[i] = new OpenGLSkinningShaderMemory(shader_string_array, folder_path);
            }
            else
            {
                dataloader.openglobjectshadermemory_array[i] = new OpenGLObjectShaderMemory(shader_string_array, folder_path);
            }
        }

        for (int i = 0; i < model_length; ++i)
        {
            if (dataloader.memory_object_array[i] instanceof OpenGLObjectMemory)
            {
                OpenGLObjectMemory openglobjectmemorydata = (OpenGLObjectMemory)dataloader.memory_object_array[i];
                int shader_id = (int)openglobjectmemorydata.shader;
                openglobjectmemorydata.shader = dataloader.openglobjectshadermemory_array[shader_id];

                if (MyConfig.SHADER.pre_shader)
                {
                    new ObjectRender(new GuiObjectData(i, 1), dataloader, new Object[]{dataloader.memory_object_array[i]})
                    {
                        @Override
                        public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
                        {
                        }
                    }.objectscreendraw.renderScreen();
                }
            }
        }
    }

    public static void setSounds(DataLoader dataloader, String mod_id_string)
    {
        mod_id_string = Reference.MOD_ID + '/' + mod_id_string;
//        DataLoader.check(mod_id_string);

        String folder_path = mod_id_string + '/';
//        if (new File(folder_path + "Sounds").isDirectory())
//        {
        dataloader.openalmemory = new OpenALMemory(folder_path);
//        }
    }

    public static void check(String mod_id_string)
    {
        File file = new File(mod_id_string);
        if (!file.isDirectory())
        {
            Nali.error('\"' + file.getPath() + "\" NOT_FOUND");
        }
    }

    public static void setMemory()
    {
        List<Class> RENDER_CLASS_LIST = Reflect.getClasses("com.nali.list.render");
        RENDER_CLASS_LIST.sort(Comparator.comparing(Class::getName));
        int size = RENDER_CLASS_LIST.size();
        MIX_MEMORY_OBJECT_ARRAY = new Object[size][];

        int index = 0;
        for (int i = 0; i < size; ++i)
        {
            try
            {
                Class clasz = RENDER_CLASS_LIST.get(i);
                DataLoader dataloader = (DataLoader)clasz.getField("DATALOADER").get(null);
                BothData bothdata = (BothData)clasz.getField("BOTHDATA").get(null);
                clasz.getField("ID").set(null, index++);
                int max_part = bothdata.MaxPart();
                MIX_MEMORY_OBJECT_ARRAY[i] = new Object[max_part];
                System.arraycopy(dataloader.memory_object_array, bothdata.StepModels(), MIX_MEMORY_OBJECT_ARRAY[i], 0, max_part);
            }
            catch (NoSuchFieldException | IllegalAccessException e)
            {
                Nali.error(e);
            }
        }
    }
}
