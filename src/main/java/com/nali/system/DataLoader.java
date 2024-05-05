package com.nali.system;

import com.nali.config.MyConfig;
import com.nali.data.client.FastClientData;
import com.nali.render.ObjectRender;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memory.OpenALMemory;
import com.nali.system.opengl.memory.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class DataLoader
{
//    public static Object[][] MIX_MEMORY_OBJECT_ARRAY;
//    public Object[] memory_object_array;
    public Object[] object_array;
    public OpenGLObjectShaderMemory[] openglobjectshadermemory_array;
//    public List<OpenGLAnimationMemory> openglanimationmemory_list = new ArrayList();
    public OpenGLTextureMemory opengltexturememory;
    public OpenALMemory openalmemory;

//    public static List<DataLoader> DATALOADER_LIST = new ArrayList();
//    public static int MAX;
//    public int index;

    public static void setModels(DataLoader dataloader, String mod_id_string)
    {
        mod_id_string = Reference.MOD_ID + '/' + mod_id_string;
        DataLoader.check(mod_id_string);

        String folder_path = mod_id_string + '/';

        dataloader.opengltexturememory = new OpenGLTextureMemory(folder_path);

        String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "ShaderList");
        int length = shader_string_2d_array.length;
        dataloader.openglobjectshadermemory_array = new OpenGLObjectShaderMemory[length];

        for (int i = 0; i < length; ++i)
        {
            String[] string_array = shader_string_2d_array[i];
            switch (string_array.length)
            {
                case 2:
                    dataloader.openglobjectshadermemory_array[i] = new OpenGLObjectShaderMemory(string_array, folder_path);
                    break;
                case 3:
                    dataloader.openglobjectshadermemory_array[i] = new OpenGLSkinningShaderMemory(string_array, folder_path);
                    break;
                default:
                    error("SHADER_LIST " + i);
            }
        }

        Object[] object_array = FileDataReader.getMixXStringArray(folder_path + "ModelList");
        length = object_array.length;
        dataloader.object_array = new Object[length];

        for (int i = 0; i < length; ++i)
        {
            String[] string_array = (String[])object_array[i];
            switch (string_array.length)
            {
                case 1:
//                    dataloader.openglanimationmemory_list.add(new OpenGLAnimationMemory(string_array, folder_path));
                    dataloader.object_array[i] = new OpenGLAnimationMemory(string_array, folder_path);
                    break;
                case 7:
                    dataloader.object_array[i] = new OpenGLObjectMemory(string_array, folder_path, shader_string_2d_array);
                    break;
                case 8:
                    dataloader.object_array[i] = new OpenGLSkinningMemory(string_array, folder_path, shader_string_2d_array);
                    break;
                default:
                    error("MODEL_LIST " + i);
            }
        }

        if (MyConfig.SHADER.pre_shader)
        {
            for (int i = 0; i < length; ++i)
            {
                if (dataloader.object_array[i] instanceof OpenGLObjectMemory)
                {
                    new ObjectRender(null, new FastClientData(i, i + 1), dataloader)
                    {
                        @Override
                        public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
                        {
                        }
                    }.draw();
                }
            }
        }

//        for (int i = 0; i < model_length; ++i)
//        {
//            if (dataloader.memory_object_array[i] instanceof OpenGLObjectMemory)
//            {
//                OpenGLObjectMemory openglobjectmemorydata = (OpenGLObjectMemory)dataloader.memory_object_array[i];
//                int shader_id = (int)openglobjectmemorydata.shader;
//                openglobjectmemorydata.shader = dataloader.openglobjectshadermemory_array[shader_id];
//
//                if (MyConfig.SHADER.pre_shader)
//                {
//                    new ObjectRender(new GuiObjectData(i, 1), dataloader, new Object[]{dataloader.memory_object_array[i]})
//                    {
//                        @Override
//                        public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
//                        {
//                        }
//                    }.objectscreendraw.renderScreen();
//                }
//            }
//        }

//        DATALOADER_LIST.add(dataloader);
//        dataloader.index = MAX++;
    }

    public static void setSounds(DataLoader dataloader, String mod_id_string)
    {
        if (MyConfig.SOUND.ffmpeg)
        {
            mod_id_string = Reference.MOD_ID + '/' + mod_id_string;
//        DataLoader.check(mod_id_string);

            String folder_path = mod_id_string + '/';
//        if (new File(folder_path + "Sounds").isDirectory())
//        {
            dataloader.openalmemory = new OpenALMemory(folder_path);
//        }
        }
    }

    public static void check(String mod_id_string)
    {
        File file = new File(mod_id_string);
        if (!file.isDirectory())
        {
            error('\"' + file.getPath() + "\" NOT_FOUND");
        }
    }

//    public static void setMemory()
//    {
//        List<Class> RENDER_CLASS_LIST = Reflect.getClasses("com.nali.list.render");
//        RENDER_CLASS_LIST.sort(Comparator.comparing(Class::getName));
//        int size = RENDER_CLASS_LIST.size();
//        MIX_MEMORY_OBJECT_ARRAY = new Object[size][];
//
//        int index = 0;
//        for (int i = 0; i < size; ++i)
//        {
//            try
//            {
//                Class clasz = RENDER_CLASS_LIST.get(i);
//                DataLoader dataloader = (DataLoader)clasz.getField("DATALOADER").get(null);
//                BothData bothdata = (BothData)clasz.getField("BOTHDATA").get(null);
//                clasz.getField("ID").set(null, index++);
//                int max_part = bothdata.MaxPart();
//                MIX_MEMORY_OBJECT_ARRAY[i] = new Object[max_part];
//                System.arraycopy(dataloader.memory_object_array, bothdata.StepModels(), MIX_MEMORY_OBJECT_ARRAY[i], 0, max_part);
//            }
//            catch (NoSuchFieldException | IllegalAccessException e)
//            {
//                Nali.error(e);
//            }
//        }
//    }
}
