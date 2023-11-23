package com.nali.system;

import com.nali.Nali;
import com.nali.config.MyConfig;
import com.nali.entities.data.ObjectData;
import com.nali.entities.data.SkinningData;
import com.nali.math.WorldMath;
import com.nali.mixin.MixinActiveRenderInfo;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.buffer.OpenGLBuffer;
import com.nali.system.opengl.drawing.OpenGLGUIObjectDrawing;
import com.nali.system.opengl.drawing.OpenGLObjectDrawing;
import com.nali.system.opengl.drawing.OpenGLSkinningDrawing;
import com.nali.system.opengl.memory.*;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

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
                    case 7: case 9:
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
        if (MyConfig.FRAME.using_frame_buffer_index)
        {
            OpenGLSkinningDrawing.DRAW_CONSUMER = (skinningdata) ->
            {
                IntBuffer opengl_intbuffer = DataLoader.OPENGL_INTBUFFER;
                GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, opengl_intbuffer);
                int draw_frame_buffer = opengl_intbuffer.get(0);
                GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, opengl_intbuffer);
                int read_frame_buffer = opengl_intbuffer.get(0);

                GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, MyConfig.FRAME.frame_buffer_index);

                // int[] widthArr = new int[1];
                // GL30.glGetFramebufferAttachmentParameteriv(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, 0x8CD4, widthArr);
                // int draw_width = widthArr[0];
                // GL30.glGetFramebufferAttachmentParameteriv(GL30.GL_READ_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, 0x8CD4, widthArr);
                // int read_width = widthArr[0];

                // int[] heightArr = new int[1];
                // GL30.glGetFramebufferAttachmentParameteriv(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, 0x8CD1, heightArr);
                // int draw_height = heightArr[0];
                // GL30.glGetFramebufferAttachmentParameteriv(GL30.GL_READ_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, 0x8CD1, heightArr);
                // int read_height = heightArr[0];

                // GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
                GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
                // GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, (int)object_array[6]);
                // GL30.glBlitFramebuffer(0, 0, read_width, read_height, 0, 0, draw_width, draw_height, GL11.GL_COLOR_BUFFER_BIT, GL11.GL_NEAREST);

                GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
                GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
            };

            OpenGLObjectDrawing.DRAW_CONSUMER = (objectdata) ->
            {
                IntBuffer opengl_intbuffer = DataLoader.OPENGL_INTBUFFER;
                GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, opengl_intbuffer);
                int draw_frame_buffer = opengl_intbuffer.get(0);
                GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, opengl_intbuffer);
                int read_frame_buffer = opengl_intbuffer.get(0);

                GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, MyConfig.FRAME.frame_buffer_index);

                GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);

                GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
                GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
            };
        }
        else
        {
            OpenGLSkinningDrawing.DRAW_CONSUMER = (skinningdata) ->
            {
                // GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, (int)object_array[6]);
                GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
            };

            OpenGLObjectDrawing.DRAW_CONSUMER = (objectdata) ->
            {
                // GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, (int)object_array[6]);
                GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
            };
        }

        if (MyConfig.USING_MULTI_UNIFORM)
        {
            OpenGLSkinningDrawing.SET_UNIFORM_BICONSUMER = (skinning_float_array, temp_uniform_object_array) ->
            {
                for (int i = 0; i < skinning_float_array.length; i += 16)
                {
                    float[] float_array = new float[16];
                    // System.arraycopy(M4x4.IDENTITY, 0, float_array, 0, 16);

                    // if (i == 0)
                    // {
                    System.arraycopy(skinning_float_array, i, float_array, 0, 16);
                    // }
                    DataLoader.setFloatBuffer(float_array);
                    GL20.glUniformMatrix4((int)temp_uniform_object_array[(i / 16) + 5], false, DataLoader.OPENGL_FLOATBUFFER);
                }
            };

            OpenGLSkinningDrawing.SET_UNIFORM_WORLD_CONSUMER = (skinningentities) ->
            {
                SkinningData skinningdata = (SkinningData)skinningentities.client_object;
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, MixinActiveRenderInfo.PROJECTION());
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], true, MixinActiveRenderInfo.MODELVIEW());

//                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, (FloatBuffer)FieldsReflectLoader.getField(FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY, 0, null));
//                DataLoader.setFloatBuffer(WorldMath.VIEW_M4X4.mat);
//                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.OPENGL_FLOATBUFFER.limit(16);
                DataLoader.setFloatBuffer(skinningdata.m4x4_array[0].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);

                if (skinningdata.glow_boolean_array[DataLoader.SCREEN_INDEX])
                {
                    DataLoader.setFloatBuffer(new float[]{1.0F, 1.0F, 1.0F});
                    GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
                }
                else
                {
                    DataLoader.setFloatBuffer(skinningdata.rgb_float_array);
                    GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
                }
            };

            OpenGLSkinningDrawing.SET_UNIFORM_SCREEN_CONSUMER = (skinningentities) ->
            {
                SkinningData skinningdata = (SkinningData)skinningentities.client_object;
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[SCREEN_INDEX])[6])[0])[0])[3];

                DataLoader.setFloatBuffer(skinningdata.m4x4_array[1].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(skinningdata.m4x4_array[2].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(skinningdata.m4x4_array[3].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.OPENGL_FLOATBUFFER.limit(3);
                DataLoader.setFloatBuffer(WorldMath.SCREEN_RGB_FLOAT_ARRAY);
                GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
            };

            OpenGLObjectDrawing.SET_UNIFORM_WORLD_CONSUMER = (objectentities) ->
            {
                ObjectData objectdata = (ObjectData)objectentities.client_object;
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, MixinActiveRenderInfo.PROJECTION());
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], true, MixinActiveRenderInfo.MODELVIEW());
                DataLoader.OPENGL_FLOATBUFFER.limit(16);
                DataLoader.setFloatBuffer(objectdata.m4x4_array[0].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);

                if (objectdata.glow_boolean_array[DataLoader.SCREEN_INDEX])
                {
                    GL20.glUniform3((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(new float[]{1.0F, 1.0F, 1.0F}, true));
                }
                else
                {
                    GL20.glUniform3((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(objectdata.rgb_float_array, true));
                }
            };

            OpenGLObjectDrawing.SET_UNIFORM_SCREEN_CONSUMER = (objectentities) ->
            {
                ObjectData objectdata = (ObjectData)objectentities.client_object;
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[SCREEN_INDEX])[6])[0])[0])[3];

                DataLoader.setFloatBuffer(objectdata.m4x4_array[1].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(objectdata.m4x4_array[2].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(objectdata.m4x4_array[3].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.OPENGL_FLOATBUFFER.limit(3);
                DataLoader.setFloatBuffer(WorldMath.SCREEN_RGB_FLOAT_ARRAY);
                GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
            };

            OpenGLGUIObjectDrawing.SET_UNIFORM_SCREEN_CONSUMER = (guiobjectdata) ->
            {
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])guiobjectdata.model_address_object_array[SCREEN_INDEX])[6])[0])[0])[3];

                DataLoader.OPENGL_FLOATBUFFER.limit(16);
                DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[0].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[1].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[2].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.OPENGL_FLOATBUFFER.limit(3);
                DataLoader.setFloatBuffer(WorldMath.SCREEN_RGB_FLOAT_ARRAY);
                GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
            };
        }
        else
        {
            OpenGLSkinningDrawing.SET_UNIFORM_BICONSUMER = (skinning_float_array, temp_uniform_object_array) ->
            {
                DataLoader.OPENGL_FLOATBUFFER.limit(skinning_float_array.length);
                DataLoader.setFloatBuffer(skinning_float_array);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[5], false, DataLoader.OPENGL_FLOATBUFFER);
            };

            OpenGLSkinningDrawing.SET_UNIFORM_WORLD_CONSUMER = (skinningentities) ->
            {
                SkinningData skinningdata = (SkinningData)skinningentities.client_object;
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, MixinActiveRenderInfo.PROJECTION());
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], true, MixinActiveRenderInfo.MODELVIEW());

                DataLoader.OPENGL_FLOATBUFFER.limit(16);
//                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, (FloatBuffer)FieldsReflectLoader.getField(FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY, 0, null));
//                DataLoader.setFloatBuffer(WorldMath.VIEW_M4X4.mat);
//                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
//                DataLoader.setFloatBuffer(skinningdata.m4x4_array[0].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);

                if (skinningdata.glow_boolean_array[DataLoader.SCREEN_INDEX])
                {
                    DataLoader.setFloatBuffer(new float[]{1.0F, 1.0F, 1.0F});
                    GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
                }
                else
                {
                    DataLoader.setFloatBuffer(skinningdata.rgb_float_array);
                    GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
                }
            };

            OpenGLSkinningDrawing.SET_UNIFORM_SCREEN_CONSUMER = (skinningentities) ->
            {
                SkinningData skinningdata = (SkinningData)skinningentities.client_object;
                Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[SCREEN_INDEX])[6])[0])[0])[3];

                DataLoader.setFloatBuffer(skinningdata.m4x4_array[1].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(skinningdata.m4x4_array[2].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.setFloatBuffer(skinningdata.m4x4_array[3].mat);
                GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
                DataLoader.OPENGL_FLOATBUFFER.limit(3);
                DataLoader.setFloatBuffer(WorldMath.SCREEN_RGB_FLOAT_ARRAY);
                GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
            };
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
//        DataLoader.OPENGL_FLOATBUFFER.position(pos);
//        DataLoader.OPENGL_FLOATBUFFER.limit(float_array.length);
        DataLoader.OPENGL_FLOATBUFFER.clear();
//        DataLoader.OPENGL_FLOATBUFFER.position(0);
        DataLoader.OPENGL_FLOATBUFFER.put(float_array);
//        DataLoader.OPENGL_FLOATBUFFER.position(0);
        DataLoader.OPENGL_FLOATBUFFER.flip();
    }
}
