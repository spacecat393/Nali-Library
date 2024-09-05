package com.nali.system;

import com.nali.Nali;
import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.render.RenderO;
import com.nali.system.file.FileDataReader;
import com.nali.system.openal.memo.client.MemoN;
import com.nali.system.opengl.memo.client.*;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.ID;
import static com.nali.Nali.error;
import static com.nali.system.opengl.memo.client.MemoC.OPENGL_INTBUFFER;

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
//    public StoreO<MemoGo, MemoS> storeo = new StoreO();
//    public StoreS<MemoGs, MemoSs> stores = new StoreS();
//    public List<MemoTexture> memotexture_list = new ArrayList();
    public static List<Integer> TEXTURE_INTEGER_LIST = new ArrayList();
    public static List<Integer> SOUND_INTEGER_LIST = new ArrayList();
    public static List<MemoG> G_LIST = new ArrayList();//graphic
    public static List<MemoS> S_LIST = new ArrayList();//shader
    public static List<MemoF> F_LIST = new ArrayList();//frame
    public static Map<Integer, MemoA2> A2_MAP = new HashMap();//3d skinning cpu
//    public OpenALMemory openalmemory;
//    public static List<MemoN> MEMON_LIST = new ArrayList();
//    public List<Integer> sound_integer_list = new ArrayList();
//    public List<MemoShade> openalmemo_list = new ArrayList();
//    public List<Integer> vert_integer_list = new ArrayList();
//    public List<Integer> frag_integer_list = new ArrayList();

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
    public static void loadPreInit()
    {
        NaliGL.init();

        File[] file_array = new File(ID).listFiles();

        if (file_array == null)
        {
            return;
        }

        Map<String, Class> data_class_map = new HashMap();
        List<MemoHVo> memohvo_list = new ArrayList();
        List<MemoHVs> memohvs_list = new ArrayList();
        List<MemoHFo> memohfo_list = new ArrayList();

        List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
        for (Class data_class : data_class_list)
        {
            data_class_map.put(StringReader.get(data_class)[1], data_class);
        }

        List<String> shader_name_string_list = new ArrayList();
        List<String> model_name_string_list = new ArrayList();
        List<String[][]> shader_string_2d_array_list = new ArrayList();
        Map<String, String[][]> shader_string_2d_array_map = new HashMap();
        List<String[][]> model_string_2d_array_list = new ArrayList();
        List<MemoA0[]> memoa0_array_list = new ArrayList();
        List<String[][]> attriblocation_string_2d_array_list = new ArrayList();
//        List<String[][]> new_shader_string_2d_array_list = new ArrayList();
        List<Integer> shader_id_integer_list = new ArrayList();
//        List<int[]> index_int_array_list = new ArrayList();

//        List<Integer> frag_integer_list = new ArrayList();
//        List<Integer> vert_o_integer_list = new ArrayList();
        Map<String, Integer> frag_integer_map = new HashMap();
        Map<String, Integer> vert_o_integer_map = new HashMap();
//        List<Integer> vert_s_integer_list = new ArrayList();

//        String path_string;
        for (File file : file_array)
        {
            File shader_file = new File(file + "/shader.dat");
            if (shader_file.isFile())
            {
                String name = file.getName();
                shader_name_string_list.add(name);
                String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());
                shader_string_2d_array_list.add(shader_string_2d_array);
                shader_string_2d_array_map.put(name, shader_string_2d_array);
            }

            File model_file = new File(file + "/model.dat");
            if (model_file.isFile())
            {
                String name_string = file.getName();
                model_name_string_list.add(name_string);
                String[][] model_string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
                model_string_2d_array_list.add(model_string_2d_array);

                for (String[] model_string_array : model_string_2d_array)
                {
//                    int shader_id = Integer.parseInt(model_string_array[2]);
                    int shader_id = Integer.parseInt(model_string_array[3]);
                    shader_id_integer_list.add(shader_id);
//                    path_string = Nali.ID + "/" + shader_name_string_list.get(i);

//                    String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(path_string + model_string_array[2] + "/shader.dat"));
//                    new_shader_string_2d_array_list.add(shader_string_2d_array);
                    String[][] shader_string_2d_array = shader_string_2d_array_map.get(model_string_array[2]);

                    String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + shader_string_2d_array[shader_id][0] + "/shader/" + shader_string_2d_array[shader_id][1] + "/attrib.dat"));
                    attriblocation_string_2d_array_list.add(attriblocation_string_2d_array);

                    memoa0_array_list.add(MemoA0.gen(model_string_array, attriblocation_string_2d_array, Nali.ID + "/" + name_string/*, shader_id*/));
                }
            }
        }

        for (File file : file_array)
        {
            File[] texture_file_array = new File(file + "/texture").listFiles();
            if (texture_file_array != null)
            {
//                String name_file = file.getName();
//                for (int i = 0; i < this.data_string_list.size(); ++i)
//                {
//                    if (this.data_string_list.get(i).contains(name_file))
//                    {
                try
                {
//                    int step = this.memotexture_list.size();
                    int step = TEXTURE_INTEGER_LIST.size();
//                    this.data_class_list.get(i).getField("TEXTURE_STEP").set(null, step);
                    data_class_map.get(file.getName()).getField("TEXTURE_STEP").set(null, step);

                    for (File texture_file : texture_file_array)
                    {
                        TEXTURE_INTEGER_LIST.add(null);
                    }

                    GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
                    for (File texture_file : texture_file_array)
                    {
                        String name = texture_file.getName();
                        int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
                        TEXTURE_INTEGER_LIST.set(index + step, MemoT.gen(texture_file));
//                        this.texture_integer_list.add(this.genTexture(texture_file));
                    }
                    GL11.glBindTexture(GL11.GL_TEXTURE_2D, OPENGL_INTBUFFER.get(0));
//                    Nali.check();
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    error(e);
                }
//                        break;
//                    }
//                }
            }
        }

////        int i = 0;
////        String path_string;
////        for (String[][] string_2d_array : shader_string_2d_array_list)
//        int vert_s = 0;
//        for (String[][] string_2d_array : shader_string_2d_array_list)
//        {
//            //            path_string = Nali.ID + "/" + shader_name_string_list.get(i);
////            if (x != 0)
////            {
////                vert_s_integer_list.add(string_2d_array.length);
////            }
//
//            vert_s_integer_list.add(vert_s);
//            for (String[] string_array : string_2d_array)
//            {
//                if (string_array.length == 7)
//                {
//                    memohvs_list.add(new MemoHVs(string_array));
//                    ++vert_s;
//                }
//            }
//        }

        for (File file : file_array)
        {
            File[] vert_file_array = new File(file + "/shader/" + NaliConfig.SHADER.gl_shading_language_version + "/vert/o").listFiles();
            if (vert_file_array != null)
            {
                int step = memohvo_list.size();
//                vert_o_integer_list.add(step);
                vert_o_integer_map.put(file.getName(), step);

                for (File vert_file : vert_file_array)
                {
                    memohvo_list.add(null);
                }

                for (File vert_file : vert_file_array)
                {
                    String name = vert_file.getName();
                    int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
                    memohvo_list.set(index + step, new MemoHVo(vert_file.toString()));
                }
//                Nali.check();
            }
        }

        for (File file : file_array)
        {
            File[] frag_file_array = new File(file + "/shader/" + NaliConfig.SHADER.gl_shading_language_version + "/frag").listFiles();
            if (frag_file_array != null)
            {
                int step = memohfo_list.size();
//                frag_integer_list.add(step);
                frag_integer_map.put(file.getName(), step);

                for (File frag_file : frag_file_array)
                {
                    memohfo_list.add(null);
                }

                for (File frag_file : frag_file_array)
                {
                    String name = frag_file.getName();
                    int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
                    memohfo_list.set(index + step, new MemoHFo(frag_file.toString()));
                }
//                Nali.check();
            }
        }

        int i = 0/*, l = 0*/;
//        String path_string;
        for (String[][] string_2d_array : shader_string_2d_array_list)
        {
//            path_string = Nali.ID + "/" + shader_name_string_list.get(i);
//            int step = S_LIST.size();

            try
            {
                data_class_map.get(shader_name_string_list.get(i++)).getField("SHADER_STEP").set(null, S_LIST.size());
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                error(e);
            }

            for (String[] string_array : string_2d_array)
            {
                String shader_string = string_array[3];
                byte[] byte_array = shader_string.getBytes();
                int v = Integer.parseInt(new String(byte_array, 1, byte_array.length - 1));
//                int f = Integer.parseInt(string_array[4]) + frag_integer_list.get(l);
                int f = Integer.parseInt(string_array[5]) + frag_integer_map.get(string_array[4]);
                if (shader_string.charAt(0) == 's')
                {
                    MemoHVs memohvs = new MemoHVs(string_array);
                    memohvs_list.add(memohvs);
//                    S_LIST.add(new MemoS(memohvs_list.get(v + vert_s_integer_list.get(l)).shader, memohfo_list.get(f).shader, string_array, path_string));
                    S_LIST.add(new MemoS(memohvs.shader, memohfo_list.get(f).shader, string_array/*, path_string*/));
                }
                else
                {
//                    S_LIST.add(new MemoS(memohvo_list.get(v + vert_o_integer_list.get(l)).shader, memohfo_list.get(f).shader, string_array/*, path_string*/));
                    S_LIST.add(new MemoS(memohvo_list.get(v + vert_o_integer_map.get(string_array[2])).shader, memohfo_list.get(f).shader, string_array/*, path_string*/));
                }
//                Nali.check();
            }

//            ++l;
        }

//        for (File file : file_array)
//        {
//            File shader_file = new File(file.getPath() + "/ShaderSList");
//            if (shader_file.isFile())
//            {
//                String[][] tring_2d_array = FileDataReader.getMixXStringArray(shader_file.toPath());
//                try
//                {
//                    this.data_class_map.get(file.getName()).getField("SHADER_S_STEP").set(null, this.stores.rs_list.size());
//                }
//                catch (IllegalAccessException | NoSuchFieldException e)
//                {
//                    error(e);
//                }
//
//                for (String[] string_array : tring_2d_array)
//                {
//                    this.stores.rs_list.add(new MemoSs(string_array/*, shader_file.getPath()*/));
//                }
//            }
//        }

        i = 0;
        int l = 0;
//        String path_string;
        for (String[][] string_2d_array : model_string_2d_array_list)
        {
            String path_string = Nali.ID + "/" + model_name_string_list.get(i);

            try
            {
                data_class_map.get(model_name_string_list.get(i++)).getField("MODEL_STEP").set(null, G_LIST.size());
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                error(e);
            }

            GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
            int gl_array_buffer_binding = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
            int gl_element_array_buffer_binding = OPENGL_INTBUFFER.get(0);

//            GL11.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING, OPENGL_INTBUFFER);
//            int gl_vertex_array_binding = OPENGL_INTBUFFER.get(0);
            int gl_vertex_array_binding = NaliGL.glVertexArrayBinding();

            for (String[] string_array : string_2d_array)
            {
                G_LIST.add(new MemoG(/*index_int_array_list, */memoa0_array_list.get(l), shader_string_2d_array_map.get(string_array[2]), attriblocation_string_2d_array_list.get(l), shader_id_integer_list.get(l++), string_array, path_string));
            }

//            GL30.glBindVertexArray(gl_vertex_array_binding);
            NaliGL.glBindVertexArray(gl_vertex_array_binding);

            OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
            OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, gl_element_array_buffer_binding);
//            Nali.check();
        }

//        for (File file : file_array)
//        {
//            File model_file = new File(file.getPath() + "/ModelSList");
//            if (model_file.isFile())
//            {
////                String name_file = file.getName();
////                for (int i = 0; i < this.data_string_list.size(); ++i)
////                {
////                    if (this.data_string_list.get(i).contains(name_file))
////                    {
////                String[][] string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());
////                try
////                {
//////                    this.data_class_list.get(i).getField("MODEL_S_STEP").set(null, this.openglskinningmemo_list.size());
////                    this.data_class_map.get(file.getName()).getField("MODEL_S_STEP").set(null, this.stores.rg_list.size());
////                }
////                catch (IllegalAccessException | NoSuchFieldException e)
////                {
////                    I.error(e);
////                }
//
////                for (String[] string_array : string_2d_array)
//                for (String[] string_array : FileDataReader.getMixXStringArray(model_file.toPath()))
//                {
//                    this.stores.rg_list.add(new MemoGs(string_array, file.getPath()));
//                }
////                        break;
////                    }
////                }
//            }
//        }

        i = 0;
        for (File file : file_array)
        {
            File model_file = new File(file + "/frame.dat");
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
                    data_class_map.get(file.getName()).getField("FRAME_STEP").set(null, F_LIST.size());
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    error(e);
                }

                for (String[] string_array : string_2d_array)
                {
                    MemoHVs memohvs = memohvs_list.get(i++);
                    MemoF memof = new MemoF(memohvs.bone, string_array, file.getPath());
                    memof.bind_pose_float_array = memohvs.bind_pose_float_array;
                    memof.back_bone_2d_int_array = memohvs.back_bone_2d_int_array;

                    F_LIST.add(memof);
                }
//                        break;
//                    }
//                }
            }
//            Nali.check();
        }
    }

    public static void loadInit()
    {
        if (NaliConfig.SOUND.ffmpeg)
        {
            File[] file_array = new File(ID).listFiles();

            if (file_array == null)
            {
                return;
            }

            Map<String, Class> data_class_map = new HashMap();
            List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
            for (Class data_class : data_class_list)
            {
                data_class_map.put(StringReader.get(data_class)[1], data_class);
            }

            for (File file : file_array)
            {
                File[] sound_file_array = new File(file + "/sound").listFiles();
                if (sound_file_array != null)
                {
//                    String name_file = file.getName();
//                    for (int i = 0; i < this.data_string_list.size(); ++i)
//                    {
//                        if (this.data_string_list.get(i).contains(name_file))
//                        {
                    try
                    {
                        int step = SOUND_INTEGER_LIST.size();
//                        this.data_class_list.get(i).getField("OPENAL_STEP").set(null, this.openalmemo_list.size());
                        data_class_map.get(file.getName()).getField("SOUND_STEP").set(null, step);

                        for (File sound_file : sound_file_array)
                        {
                            SOUND_INTEGER_LIST.add(null);
                        }

                        for (File sound_file : sound_file_array)
                        {
                            String name = sound_file.getName();
                            int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
                            SOUND_INTEGER_LIST.set(index + step, MemoN.gen(sound_file));
                        }
                    }
                    catch (IllegalAccessException | NoSuchFieldException e)
                    {
                        error(e);
                    }
//                            break;
//                        }
//                    }
                }
            }
        }

        if (NaliConfig.SHADER.pre_shader)
        {
            for (Class render_class : Reflect.getClasses("com.nali.list.render.s"))
            {
                try
                {
                    ((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAS").get(null), render_class.getField("IBOTHDASN").get(null))).draw();
                }
                catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
                {
                    error(e);
                }
            }

            for (Class render_class : Reflect.getClasses("com.nali.list.render.o"))
            {
                try
                {
                    ((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAO").get(null))).draw();
                }
                catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
                {
                    error(e);
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

//    public int genTexture(File file)
//    {
//        try
//        {
//            BufferedImage bufferedimage = ImageIO.read(file);
//
//            int width = bufferedimage.getWidth();
//            int height = bufferedimage.getHeight();
//
//            ByteBuffer bytebuffer = ByteBuffer.allocateDirect(width * height * 4);
//            int[] pixels = new int[width * height];
//            bufferedimage.getRGB(0, 0, width, height, pixels, 0, width);
//            int[] new_pixels = new int[width * height];
//
//            for (int h = 0; h < height; ++h)
//            {
////                if (width >= 0)
////                {
//                System.arraycopy(pixels, h * width, new_pixels, (height - 1 - h) * width, width);
////                }
//            }
//
//            for (int pixel : new_pixels)
//            {
//                bytebuffer.put((byte)((pixel >> 16) & 0xFF));
//                bytebuffer.put((byte)((pixel >> 8) & 0xFF));
//                bytebuffer.put((byte)(pixel & 0xFF));
//                bytebuffer.put((byte)((pixel >> 24) & 0xFF));
//            }
//
//            bytebuffer.flip();
//
//            int texture_buffer = GL11.glGenTextures();
//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture_buffer);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, bytebuffer);
//
//            return texture_buffer;
//        }
//        catch (IOException e)
//        {
//            I.error(e);
//        }
//
//        return -1;
//    }
}
