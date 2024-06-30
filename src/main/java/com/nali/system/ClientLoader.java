package com.nali.system;

import com.nali.NaliConfig;
import com.nali.render.RenderO;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memo.OpenALMemo;
import com.nali.system.opengl.memo.client.*;
import com.nali.system.opengl.store.StoreO;
import com.nali.system.opengl.store.StoreS;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.nali.Nali.I;
import static com.nali.Nali.ID;

@SideOnly(Side.CLIENT)
public class ClientLoader
{
//    public List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
//    public List<String> data_string_list = new ArrayList();
//    public Map<String, Class> data_class_map = new HashMap();
//    public static Object[][] MIX_MEMORY_OBJECT_ARRAY;
//    public Object[] memory_object_array;
//    public Object[] object_array;
//    public List<Object> object_list = new ArrayList();
//    public OpenGLObjectShaderMemory[] openglobjectshadermemory_array;
//    public OpenGLTextureMemory opengltexturememory;
    public StoreO<MemoGo, MemoSo> storeo = new StoreO();
    public StoreS<MemoGs, MemoSs> stores = new StoreS();
    public List<MemoTexture> opengltexturememo_list = new ArrayList();
//    public OpenALMemory openalmemory;
    public List<OpenALMemo> openalmemo_list = new ArrayList();

//    static
//    {
//        for (Class data_class : this.data_class_list)
//        {
//            this.data_string_list.add(data_class.getSimpleName().toLowerCase());
//        }
//    }

//    public static List<DataLoader> DATALOADER_LIST = new ArrayList();
//    public static int MAX;
//    public int index;
    public void loadPreInit()
    {
//        List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
////        for (Class data_class : this.data_class_list)
//        for (Class data_class : data_class_list)
//        {
////            this.data_string_list.add(data_class.getSimpleName().toLowerCase());
//            this.data_class_map.put(data_class.getSimpleName().toLowerCase(), data_class);
//        }
        Map<String, Class> data_class_map = I.bothloader.data_class_map;

        File[] file_array = new File(ID).listFiles();

        if (file_array == null)
        {
            return;
        }

        for (File file : file_array)
        {
            File[] texture_file_array = new File(file.getPath() + "/Texture").listFiles();
            if (texture_file_array != null)
            {
//                String name_file = file.getName();
//                for (int i = 0; i < this.data_string_list.size(); ++i)
//                {
//                    if (this.data_string_list.get(i).contains(name_file))
//                    {
                try
                {
                    int step = this.opengltexturememo_list.size();
//                    this.data_class_list.get(i).getField("TEXTURE_STEP").set(null, step);
                    data_class_map.get(file.getName()).getField("TEXTURE_STEP").set(null, step);

                    for (File texture_file : texture_file_array)
                    {
                        this.opengltexturememo_list.add(null);
                    }

                    for (File texture_file : texture_file_array)
                    {
                        String name = texture_file.getName();
                        int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
                        this.opengltexturememo_list.set(index + step, new MemoTexture(texture_file));
                    }
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    I.error(e);
                }
//                        break;
//                    }
//                }
            }
        }

        for (File file : file_array)
        {
            File shader_file = new File(file.getPath() + "/ShaderOList");
            if (shader_file.isFile())
            {
//                String name_file = file.getName();
//                for (int i = 0; i < this.data_string_list.size(); ++i)
//                {
//                    if (this.data_string_list.get(i).contains(name_file))
//                    {
                String[][] string_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());
                try
                {
//                    this.data_class_list.get(i).getField("SHADER_STEP").set(null, this.openglobjectshadermemo_list.size());
                    data_class_map.get(file.getName()).getField("SHADER_O_STEP").set(null, this.storeo.rs_list.size());
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    I.error(e);
                }

                for (String[] string_array : string_2d_array)
                {
                    this.storeo.rs_list.add(new MemoSo(string_array/*, shader_file.getPath()*/));
                }
//                        break;
//                    }
//                }
            }
        }

        for (File file : file_array)
        {
            File shader_file = new File(file.getPath() + "/ShaderSList");
            if (shader_file.isFile())
            {
                String[][] tring_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());
                try
                {
                    data_class_map.get(file.getName()).getField("SHADER_S_STEP").set(null, this.stores.rs_list.size());
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    I.error(e);
                }

                for (String[] string_array : tring_2d_array)
                {
                    this.stores.rs_list.add(new MemoSs(string_array/*, shader_file.getPath()*/));
                }
            }
        }

        for (File file : file_array)
        {
            File model_file = new File(file.getPath() + "/ModelOList");
            if (model_file.isFile())
            {
//                String name_file = file.getName();
//                for (int i = 0; i < this.data_string_list.size(); ++i)
//                {
//                    if (this.data_string_list.get(i).contains(name_file))
//                    {
                String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
                try
                {
//                    this.data_class_list.get(i).getField("MODEL_O_STEP").set(null, this.openglobjectmemo_list.size());
                    data_class_map.get(file.getName()).getField("MODEL_O_STEP").set(null, this.storeo.rg_list.size());
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    I.error(e);
                }

                for (String[] string_array : string_2d_array)
                {
                    this.storeo.rg_list.add(new MemoGo(string_array, file.getPath()));
                }
//                        break;
//                    }
//                }
            }
        }

        for (File file : file_array)
        {
            File model_file = new File(file.getPath() + "/ModelSList");
            if (model_file.isFile())
            {
//                String name_file = file.getName();
//                for (int i = 0; i < this.data_string_list.size(); ++i)
//                {
//                    if (this.data_string_list.get(i).contains(name_file))
//                    {
//                String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
//                try
//                {
////                    this.data_class_list.get(i).getField("MODEL_S_STEP").set(null, this.openglskinningmemo_list.size());
//                    this.data_class_map.get(file.getName()).getField("MODEL_S_STEP").set(null, this.stores.rg_list.size());
//                }
//                catch (IllegalAccessException | NoSuchFieldException e)
//                {
//                    I.error(e);
//                }

//                for (String[] string_array : string_2d_array)
                for (String[] string_array : FileDataReader.getMixXStringArray(model_file.toPath()))
                {
                    this.stores.rg_list.add(new MemoGs(string_array, file.getPath()));
                }
//                        break;
//                    }
//                }
            }
        }

        for (File file : file_array)
        {
            File model_file = new File(file.getPath() + "/AnimationList");
            if (model_file.isFile())
            {
//                String name_file = file.getName();
//                for (int i = 0; i < this.data_string_list.size(); ++i)
//                {
//                    if (this.data_string_list.get(i).contains(name_file))
//                    {
                String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
                try
                {
//                    this.data_class_list.get(i).getField("ANIMATION_STEP").set(null, this.openglskinningmemo_list.size());
                    data_class_map.get(file.getName()).getField("ANIMATION_STEP").set(null, this.stores.memoanimation_list.size());
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    I.error(e);
                }

                for (String[] string_array : string_2d_array)
                {
                    this.stores.memoanimation_list.add(new MemoAnimation(string_array, file.getPath()));
                }
//                        break;
//                    }
//                }
            }
        }
    }

    public void loadInit()
    {
        if (NaliConfig.SOUND.ffmpeg)
        {
            File[] file_array = new File(ID).listFiles();

            if (file_array == null)
            {
                return;
            }

            for (File file : file_array)
            {
                File[] sound_file_array = new File(file.getPath() + "/Sound").listFiles();
                if (sound_file_array != null)
                {
//                    String name_file = file.getName();
//                    for (int i = 0; i < this.data_string_list.size(); ++i)
//                    {
//                        if (this.data_string_list.get(i).contains(name_file))
//                        {
                    try
                    {
                        int step = this.openalmemo_list.size();
//                        this.data_class_list.get(i).getField("OPENAL_STEP").set(null, this.openalmemo_list.size());
                        I.bothloader.data_class_map.get(file.getName()).getField("OPENAL_STEP").set(null, this.openalmemo_list.size());

                        for (File sound_file : sound_file_array)
                        {
                            this.openalmemo_list.add(null);
                        }

                        for (File sound_file : sound_file_array)
                        {
                            String name = sound_file.getName();
                            int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
                            this.openalmemo_list.set(index + step, new OpenALMemo(sound_file));
                        }
                    }
                    catch (IllegalAccessException | NoSuchFieldException e)
                    {
                        I.error(e);
                    }
//                            break;
//                        }
//                    }
                }
            }
        }

        if (NaliConfig.SHADER.pre_shader)
        {
            List<Class> render_class_list = Reflect.getClasses("com.nali.list.render");
            for (Class render_class : render_class_list)
            {
                try
                {
                    Constructor constructor = render_class.getConstructors()[0];
                    Class[] parameter_types_class_array = constructor.getParameterTypes();

                    Object[] args_object_array = new Object[parameter_types_class_array.length];
                    Arrays.fill(args_object_array, null);

                    ((RenderO)constructor.newInstance(args_object_array)).draw();
                }
                catch (IllegalAccessException | InstantiationException | InvocationTargetException e)
                {
                    I.error(e);
                }
            }
        }

//        this.data_class_list = null;
//        this.data_string_list = null;
//        this.data_class_map = null;
    }

//    public static void setModels(DataLoader dataloader, String mod_id_string)
//    {
//        mod_id_string = ID + '/' + mod_id_string;
//        DataLoader.check(mod_id_string);
//
//        String folder_path = mod_id_string + '/';
//
//        dataloader.opengltexturememory = new OpenGLTextureMemory(folder_path);
//
//        String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "ShaderList");
//        int length = shader_string_2d_array.length;
//        dataloader.openglobjectshadermemory_array = new OpenGLObjectShaderMemory[length];
//
//        for (int i = 0; i < length; ++i)
//        {
//            String[] string_array = shader_string_2d_array[i];
//            switch (string_array.length)
//            {
//                case 2:
//                    dataloader.openglobjectshadermemory_array[i] = new OpenGLObjectShaderMemory(string_array, folder_path);
//                    break;
//                case 3:
//                    dataloader.openglobjectshadermemory_array[i] = new OpenGLSkinningShaderMemory(string_array, folder_path);
//                    break;
//                default:
//                    error("SHADER_LIST " + i);
//            }
//        }
//
//        Object[] object_array = FileDataReader.getMixXStringArray(folder_path + "ModelList");
//        length = object_array.length;
//        dataloader.object_array = new Object[length];
//
//        for (int i = 0; i < length; ++i)
//        {
//            String[] string_array = (String[])object_array[i];
//            switch (string_array.length)
//            {
//                case 1:
////                    dataloader.openglanimationmemory_list.add(new OpenGLAnimationMemory(string_array, folder_path));
//                    dataloader.object_array[i] = new OpenGLAnimationMemory(string_array, folder_path);
//                    break;
//                case 7:
//                    dataloader.object_array[i] = new OpenGLObjectMemory(string_array, folder_path, shader_string_2d_array);
//                    break;
//                case 8:
//                    dataloader.object_array[i] = new OpenGLSkinningMemory(string_array, folder_path, shader_string_2d_array);
//                    break;
//                default:
//                    error("MODEL_LIST " + i);
//            }
//        }
//
//        if (MyConfig.SHADER.pre_shader)
//        {
//            for (int i = 0; i < length; ++i)
//            {
//                if (dataloader.object_array[i] instanceof OpenGLObjectMemory)
//                {
//                    new ObjectRender(null, new FastClientData(i, i + 1), dataloader)
//                    {
//                        @Override
//                        public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
//                        {
//                        }
//                    }.draw();
//                }
//            }
//        }
//
////        for (int i = 0; i < model_length; ++i)
////        {
////            if (dataloader.memory_object_array[i] instanceof OpenGLObjectMemory)
////            {
////                OpenGLObjectMemory openglobjectmemorydata = (OpenGLObjectMemory)dataloader.memory_object_array[i];
////                int shader_id = (int)openglobjectmemorydata.shader;
////                openglobjectmemorydata.shader = dataloader.openglobjectshadermemory_array[shader_id];
////
////                if (MyConfig.SHADER.pre_shader)
////                {
////                    new ObjectRender(new GuiObjectData(i, 1), dataloader, new Object[]{dataloader.memory_object_array[i]})
////                    {
////                        @Override
////                        public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
////                        {
////                        }
////                    }.objectscreendraw.renderScreen();
////                }
////            }
////        }
//
//        DATALOADER_LIST.add(dataloader);
//        dataloader.index = MAX++;
//    }

//    public static void setSounds(DataLoader dataloader, String mod_id_string)
//    {
//        if (MyConfig.SOUND.ffmpeg)
//        {
//            mod_id_string = ID + '/' + mod_id_string;
////        DataLoader.check(mod_id_string);
//
//            String folder_path = mod_id_string + '/';
////        if (new File(folder_path + "Sounds").isDirectory())
////        {
//            dataloader.openalmemory = new OpenALMemory(folder_path);
////        }
//        }
//    }

//    public static void check(String mod_id_string)
//    {
//        File file = new File(mod_id_string);
//        if (!file.isDirectory())
//        {
//            error('\"' + file.getPath() + "\" NOT_FOUND");
//        }
//    }

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
