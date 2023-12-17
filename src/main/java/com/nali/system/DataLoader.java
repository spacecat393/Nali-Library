package com.nali.system;

import com.nali.Nali;
import com.nali.config.MyConfig;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.drawing.OpenGLObjectDrawing;
import com.nali.system.opengl.drawing.OpenGLSkinningDrawing;
import com.nali.system.opengl.memory.*;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

@SideOnly(Side.CLIENT)
public class DataLoader
{
//    public static Object[] SERVER_OBJECT_ARRAY;

    public Object[] model_object_array;
    public Object[] texture_object_array;
    public Object[] shader_object_array;
    public boolean REINIT_OPENGL_BUFFER = true;
    public static boolean FIRST_LOAD_USED;
    public static IntBuffer OPENGL_INTBUFFER;
    public static Object[] OPENGL_OBJECT_ARRAY;
    public static FloatBuffer OPENGL_FLOATBUFFER;
    public static int MAX_OPENGL_FLOATBUFFER_SIZE;
    public static int SCREEN_INDEX = 0;

    public static long LAST_TIME;
    public static long DELTA;
    public static float TD;
    public static float TICK = 0.05F;

//    public static ArrayList<SkinningEntities> SKINNINGENTITIES_ARRAYLIST;
//    public static ArrayList<ObjectEntities> OBJECTENTITIES_ARRAYLIST;

//    public static void setServerConfig(String mod_id_string)
//    {
//        DataLoader.check(mod_id_string);
//
//        String folder_path = mod_id_string + '/';
//
//        Object[] server_object_array = FileDataReader.getMixXStringArray(folder_path + "Server/Config");
//        int server_length = server_object_array.length;
//        SERVER_OBJECT_ARRAY = new Object[server_length];
//
//        for (int i = 0; i < server_length; ++i)
//        {
//            String string = ((String[])server_object_array[i])[0];
//            SERVER_OBJECT_ARRAY[i] = (byte)Integer.parseInt(string);
//        }
//    }

//    public static void setClientConfig(String mod_id_string)
//    {
//        DataLoader.check(mod_id_string);
//
//        String folder_path = mod_id_string + '/';
//
//        Object[] client_object_array = FileDataReader.getMixXStringArray(folder_path + "Client/Config");
//        int client_length = client_object_array.length;
//        CLIENT_OBJECT_ARRAY = new Object[client_length];
//
//        String string = ((String[])client_object_array[0])[0];
//        CLIENT_OBJECT_ARRAY[0] = Integer.parseInt(string);
//
//        string = ((String[])client_object_array[1])[0];
//        CLIENT_OBJECT_ARRAY[1] = (byte)Integer.parseInt(string);
//
//        // for (int i = 0; i < client_length; ++i)
//        // {
//        //     String string = ((String[])client_object_array[i])[0];
//        //     CLIENT_OBJECT_ARRAY[i] = Integer.parseInt(string);
//        // }
//    }

    public static void setModels(DataLoader dataloader, String mod_id_string, String shaders_folder_path, boolean vulkan_shader)
    {
        DataLoader.check(mod_id_string);

        String folder_path = mod_id_string + '/';

        OPENGL_OBJECT_ARRAY = new Object[15];

        OPENGL_INTBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asIntBuffer();

        OpenGLTextureMemory.set(dataloader, mod_id_string);

        Object[] model_object_array = FileDataReader.getMixXStringArray(folder_path + "ModelsList");
        int model_length = model_object_array.length;
        dataloader.model_object_array = new Object[model_length];

        for (int i = 0; i < model_length; ++i)
        {
            String[] model_string_array = (String[])model_object_array[i];

            File model_folder_file = new File(folder_path + "Models/" + model_string_array[0]);

            if (model_folder_file.isDirectory())
            {
                switch (model_string_array.length)
                {
                    case 1:
                    {
                        OpenGLAnimationMemory.set(dataloader, model_string_array, folder_path, i);
                        break;
                    }
                    case 4:
                    {
                        OpenGLObjectMemory.set(dataloader, model_string_array, folder_path, i);
                        break;
                    }
                    case 5:
                    {
                        OpenGLSkinningMemory.set(dataloader, model_string_array, folder_path, i);
                        break;
                    }
                    default:
                    {
                        Nali.LOGGER.error("No " + model_string_array[0] + " Engine");
                        break;
                    }
                }
            }
            else
            {
                dataloader.model_object_array[i] = null;
            }
        }

        Object[] shader_object_array = FileDataReader.getMixXStringArray(folder_path + "ShaderList");
        int shader_length = shader_object_array.length;
        dataloader.shader_object_array = new Object[shader_length];

        // boolean vulkan_shader = shaders_folder_path.equals("/VulkanShaders/");

        for (int i = 0; i < shader_length; ++i)
        {
            String[] shader_string_array = (String[])shader_object_array[i];

            OpenGLShaderMemory.set(dataloader, shader_string_array, folder_path, shaders_folder_path, vulkan_shader, i);
        }

        for (int i = 0; i < model_length; ++i)
        {
            Object[] temp_model_object_array = (Object[])dataloader.model_object_array[i];

            if (temp_model_object_array != null)
            {
                switch (temp_model_object_array.length)
                {
                    case 8: case 10:
                    {
                        int shader_id = (int)((Object[])temp_model_object_array[6])[0];
                        ((Object[])temp_model_object_array[6])[0] = dataloader.shader_object_array[shader_id];
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
            }
        }

        OPENGL_FLOATBUFFER = ByteBuffer.allocateDirect((MAX_OPENGL_FLOATBUFFER_SIZE != 0 ? MAX_OPENGL_FLOATBUFFER_SIZE : 16) << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//        if ((byte)DataLoader.CLIENT_OBJECT_ARRAY[1] == 1)
//        {
//            MAX_OPENGL_FLOATBUFFER_ARRAY_SIZE = 5;
//        }
//
//        OPENGL_FLOATBUFFER_ARRAY = new FloatBuffer[MAX_OPENGL_FLOATBUFFER_ARRAY_SIZE];
//        for (int i = 0; i < MAX_OPENGL_FLOATBUFFER_ARRAY_SIZE; ++i)
//        {
//            OPENGL_FLOATBUFFER_ARRAY[i] = ByteBuffer.allocateDirect(1 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//        }
    }

    public static void check(String mod_id_string)
    {
        if (!new File(mod_id_string).isDirectory())
        {
            Nali.LOGGER.error("No Data Found!");
            Nali.LOGGER.warn("Please paste " + mod_id_string + " Folder to .minecraft!");
            FMLCommonHandler.instance().exitJava(-1, true);
        }
    }

    //    public static void setBy(Object[] client_object_array)
    public static void setBy()
    {
//        SKINNINGENTITIES_ARRAYLIST = new ArrayList<>();
//        OBJECTENTITIES_ARRAYLIST = new ArrayList<>();

        if (MyConfig.FRAME.using_frame_buffer_index)
        {
            OpenGLSkinningDrawing.setTUsingFrameBufferIndex();
            OpenGLObjectDrawing.setTUsingFrameBufferIndex();
        }
        else
        {
            OpenGLSkinningDrawing.setFUsingFrameBufferIndex();
            OpenGLObjectDrawing.setFUsingFrameBufferIndex();
        }

        if (MyConfig.USING_MULTI_UNIFORM)
        {
            OpenGLSkinningDrawing.setTUsingMultiUniform();
//            OpenGLObjectDrawing.setTUsingMultiUniform();
//            OpenGLGUIObjectDrawing.setTUsingMultiUniform();
        }
        else
        {
            OpenGLSkinningDrawing.setFUsingMultiUniform();
//            OpenGLObjectDrawing.setFUsingMultiUniform();
//            OpenGLGUIObjectDrawing.setFUsingMultiUniform();
        }
    }

//    public static void main(String[] args)
//    {
//        DataLoader.setClientConfig("run/" + Reference.MOD_ID);
//        DataLoader.setBy(DataLoader.CLIENT_OBJECT_ARRAY);
//        DataLoader.setModels("run/" + Reference.MOD_ID, "/OpenGLShaders/", false);
//
//        for (int i = 0; i < SHADER_OBJECT_ARRAY.length; ++i)
//        {
//            Object[] shader_object_array = (Object[])SHADER_OBJECT_ARRAY[i];
//
//            if (shader_object_array != null)
//            {
//                FileDataWriter.bytes("Results/" + i + ".vert", ((StringBuilder)shader_object_array[2]).toString().getBytes());
//                FileDataWriter.bytes("Results/" + i + ".frag", ((StringBuilder)shader_object_array[3]).toString().getBytes());
//            }
//        }
//    }

    public static void setFloatBuffer(float[] float_array)
    {
        DataLoader.OPENGL_FLOATBUFFER.limit(float_array.length);
//        DataLoader.OPENGL_FLOATBUFFER.position(pos);
//        DataLoader.OPENGL_FLOATBUFFER.limit(float_array.length);
        DataLoader.OPENGL_FLOATBUFFER.clear();
//        DataLoader.OPENGL_FLOATBUFFER.position(0);
        DataLoader.OPENGL_FLOATBUFFER.put(float_array);
//        DataLoader.OPENGL_FLOATBUFFER.position(0);
        DataLoader.OPENGL_FLOATBUFFER.flip();
    }
}
