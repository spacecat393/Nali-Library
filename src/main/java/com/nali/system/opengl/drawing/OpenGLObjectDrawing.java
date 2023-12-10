package com.nali.system.opengl.drawing;

import com.nali.config.MyConfig;
import com.nali.data.MainData;
import com.nali.data.ObjectData;
import com.nali.mixin.IMixinActiveRenderInfo;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class OpenGLObjectDrawing
{
    public static Consumer<ObjectData> DRAW_CONSUMER;
//    public static Consumer<ObjectEntities> SET_UNIFORM_WORLD_CONSUMER;
//    public static Consumer<ObjectEntities> SET_UNIFORM_SCREEN_CONSUMER;

    public static void startObjectGL(ObjectData objectdata)
    {
//        if (!DataLoader.REINIT_OPENGL_BUFFER)
//        {
        MainData.takeDefault(objectdata);

        Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

//        SET_UNIFORM_WORLD_CONSUMER.accept(objectentities);

        GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, IMixinActiveRenderInfo.PROJECTION());
        GL20.glUniformMatrix4((int)temp_uniform_object_array[1], true, IMixinActiveRenderInfo.MODELVIEW());
//        DataLoader.OPENGL_FLOATBUFFER.limit(16);
        DataLoader.setFloatBuffer(objectdata.m4x4_array[0].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);

//        if (objectdata.rgba_float_array[3] < 1.0F)
//        {
//            GL11.glEnable(GL11.GL_BLEND);
////            GL11.glEnable(GL11.GL_ALPHA_TEST);
////            GL11.glAlphaFunc(GL11.GL_LESS, 1.0F);
////            GL11.glFrontFace(GL11.GL_CW);
////            GL11.glDepthMask(false);
//        }
////        else
////        {
////            GL11.glDisable(GL11.GL_BLEND);
////        }

//        DataLoader.OPENGL_FLOATBUFFER.limit(4);
        if (objectdata.glow_boolean_array[DataLoader.SCREEN_INDEX])
        {
            GL20.glUniform4((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(new float[]{1.0F, 1.0F, 1.0F, objectdata.rgba_float_array[3]}, true));
        }
        else
        {
            GL20.glUniform4((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(objectdata.rgba_float_array, true));
//                    GL20.glUniform4((int)temp_uniform_object_array[3], MixinRenderHelper.COLOR_BUFFER());
        }

//        GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, OpenGLBuffer.createFloatBuffer(WorldMath.PROJECT_M4X4.mat, true));
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, OpenGLBuffer.createFloatBuffer(objectdata.m4x4_array[1].mat, true));
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, OpenGLBuffer.createFloatBuffer(objectdata.m4x4_array[0].mat, true));
//
//        if (objectdata.glow_boolean_array[objectdata.index])
//        {
//            GL20.glUniform3((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(new float[]{1.0F, 1.0F, 1.0F}, true));
//        }
//        else
//        {
//            GL20.glUniform3((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(objectdata.rgb_float_array, true));
//        }

        GL20.glUniform1i((int)temp_uniform_object_array[4], 0);

        objectdata.setUniform(temp_uniform_object_array);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        // GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
        OpenGLBuffer.setTextureBuffer((int)((Object[])objectdata.dataloader.texture_object_array[1])[objectdata.texture_index_int_array[DataLoader.SCREEN_INDEX]], (byte)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[5]);

        DRAW_CONSUMER.accept(objectdata);

        MainData.setDefault(objectdata);
//        }
    }

    public static void startScreenObjectGL(ObjectData objectdata)
    {
        MainData.takeDefault(objectdata);

        Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

//        SET_UNIFORM_SCREEN_CONSUMER.accept(objectentities);
        //            DataLoader.OPENGL_FLOATBUFFER.limit(16);
        DataLoader.setFloatBuffer(objectdata.m4x4_array[1].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
        DataLoader.setFloatBuffer(objectdata.m4x4_array[2].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
        DataLoader.setFloatBuffer(objectdata.m4x4_array[3].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.OPENGL_FLOATBUFFER.limit(4);

//        if (objectdata.screen_rgba_float_array[3] < 1.0F)
//        {
//            GL11.glEnable(GL11.GL_BLEND);
////            GL11.glEnable(GL11.GL_ALPHA_TEST);
////            GL11.glAlphaFunc(GL11.GL_LESS, 1.0F);
////            GL11.glFrontFace(GL11.GL_CW);
////            GL11.glDepthMask(false);
//        }
////        else
////        {
////            GL11.glDisable(GL11.GL_BLEND);
////        }

        DataLoader.setFloatBuffer(objectdata.screen_rgba_float_array);

        GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, OpenGLBuffer.createFloatBuffer(objectdata.m4x4_array[2].mat, true));
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, OpenGLBuffer.createFloatBuffer(objectdata.m4x4_array[3].mat, true));
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, OpenGLBuffer.createFloatBuffer(objectdata.m4x4_array[4].mat, true));
//        GL20.glUniform3((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(WorldMath.SCREEN_RGB_FLOAT_ARRAY, true));

        GL20.glUniform1i((int)temp_uniform_object_array[4], 0);

        objectdata.setUniform(temp_uniform_object_array);

        // GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
        OpenGLBuffer.setTextureBuffer((int)((Object[])objectdata.dataloader.texture_object_array[1])[objectdata.texture_index_int_array[DataLoader.SCREEN_INDEX]], (byte)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[5]);

        GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
        MainData.setDefault(objectdata);
    }

//    public static void takeDefault(ObjectData objectdata)
//    {
//        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
//        IntBuffer opengl_intbuffer = DataLoader.OPENGL_INTBUFFER;
//        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
//        Object[] attriblocation_object_array = (Object[])temp_object_array[4];
//
//        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, opengl_intbuffer);
//        opengl_object_array[0] = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, opengl_intbuffer);
//        opengl_object_array[2] = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, opengl_intbuffer);
//        opengl_object_array[3] = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, opengl_intbuffer);
//        opengl_object_array[4] = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, opengl_intbuffer);
//        opengl_object_array[12] = opengl_intbuffer.get(0);
//
//        OpenGLObjectBuffer.set((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX]);
//
//        GL20.glUseProgram((int)temp_object_array[2]);
//
//        for (Object o : attriblocation_object_array)
//        {
//            GL20.glEnableVertexAttribArray((int)o);
//        }
//
//        if (!GL11.glIsEnabled(GL11.GL_DEPTH_TEST))
//        {
//            GL11.glEnable(GL11.GL_DEPTH_TEST);
//            opengl_object_array[5] = false;
//        }
//
////        if (!GL11.glIsEnabled(GL11.GL_CULL_FACE))
////        {
//        if ((byte)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[4] == 0)
//        {
//            GL11.glDisable(GL11.GL_CULL_FACE);
//            opengl_object_array[6] = true;
//        }
//        else
//        {
//            GL11.glEnable(GL11.GL_CULL_FACE);
//            opengl_object_array[6] = false;
//        }
////        }
//
//        if (!GL11.glIsEnabled(GL11.GL_BLEND))
//        {
//            GL11.glEnable(GL11.GL_BLEND);
//            opengl_object_array[7] = false;
//        }
//
//        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, opengl_intbuffer);
//        int i0 = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, opengl_intbuffer);
//        int i1 = opengl_intbuffer.get(0);
//
//        if (i0 != GL14.GL_FUNC_ADD || i1 != GL14.GL_FUNC_ADD)
//        {
//            GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//            opengl_object_array[8] = i0;
//            opengl_object_array[9] = i1;
//        }
//
//        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, opengl_intbuffer); //GL_BLEND_SRC
//        i0 = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, opengl_intbuffer); //GL_BLEND_DST
//        i1 = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, opengl_intbuffer);
//        int i2 = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, opengl_intbuffer);
//        int i3 = opengl_intbuffer.get(0);
//        if (i0 != GL11.GL_SRC_ALPHA || i2 != GL11.GL_ONE_MINUS_SRC_ALPHA || i1 != GL11.GL_ONE || i3 != GL11.GL_ZERO)
//        {
//            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//            opengl_object_array[10] = i0;
//            opengl_object_array[11] = i1;
//            opengl_object_array[13] = i2;
//            opengl_object_array[14] = i3;
//        }
//    }
//
//    public static void setDefault(ObjectData objectdata)
//    {
//        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
//        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
//        Object[] attriblocation_object_array = (Object[])temp_object_array[4];
//
//        for (Object o : attriblocation_object_array)
//        {
//            GL20.glDisableVertexAttribArray((int)o);
//        }
//
//        GL20.glUseProgram((int)opengl_object_array[0]);
//        GL13.glActiveTexture((int)opengl_object_array[12]);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, (int)opengl_object_array[2]);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, (int)opengl_object_array[3]);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, (int)opengl_object_array[4]);
//
//        if (opengl_object_array[5] != null)
//        {
//            GL11.glDisable(GL11.GL_DEPTH_TEST);
//            opengl_object_array[5] = null;
//        }
//
//        if (opengl_object_array[6] != null)
//        {
//            if ((boolean)opengl_object_array[6])
//            {
//                GL11.glEnable(GL11.GL_CULL_FACE);
//            }
//            else
//            {
//                GL11.glDisable(GL11.GL_CULL_FACE);
//            }
//            opengl_object_array[6] = null;
//        }
//
//        if (opengl_object_array[7] != null)
//        {
//            GL11.glDisable(GL11.GL_BLEND);
//            opengl_object_array[7] = null;
//        }
//
//        if (opengl_object_array[8] != null)
//        {
//            GL20.glBlendEquationSeparate((int)opengl_object_array[8], (int)opengl_object_array[9]);
//            opengl_object_array[8] = null;
//            opengl_object_array[9] = null;
//        }
//
//        if (opengl_object_array[10] != null)
//        {
//            GL14.glBlendFuncSeparate((int)opengl_object_array[10], (int)opengl_object_array[13], (int)opengl_object_array[11], (int)opengl_object_array[14]);
//            opengl_object_array[10] = null;
//            opengl_object_array[11] = null;
//            opengl_object_array[13] = null;
//            opengl_object_array[14] = null;
//        }
//    }

    public static void setTUsingFrameBufferIndex()
    {
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

    public static void setFUsingFrameBufferIndex()
    {
        OpenGLObjectDrawing.DRAW_CONSUMER = (objectdata) ->
        {
            // GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, (int)object_array[6]);
            GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
        };
    }

//    public static void setTUsingMultiUniform()
//    {
//        OpenGLObjectDrawing.SET_UNIFORM_WORLD_CONSUMER = (objectentities) ->
//        {
//            ObjectData objectdata = (ObjectData)objectentities.client_object;
//            Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];
//
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, MixinActiveRenderInfo.PROJECTION());
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[1], true, MixinActiveRenderInfo.MODELVIEW());
////            DataLoader.OPENGL_FLOATBUFFER.limit(16);
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[0].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//
////            DataLoader.OPENGL_FLOATBUFFER.limit(4);
//            if (objectdata.glow_boolean_array[DataLoader.SCREEN_INDEX])
//            {
//                GL20.glUniform4((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(new float[]{1.0F, 1.0F, 1.0F, objectdata.rgba_float_array[3]}, true));
//            }
//            else
//            {
//                GL20.glUniform4((int)temp_uniform_object_array[3], OpenGLBuffer.createFloatBuffer(objectdata.rgba_float_array, true));
////                    GL20.glUniform4((int)temp_uniform_object_array[3], MixinRenderHelper.COLOR_BUFFER());
//            }
//        };
//
//        OpenGLObjectDrawing.SET_UNIFORM_SCREEN_CONSUMER = (objectentities) ->
//        {
//            ObjectData objectdata = (ObjectData)objectentities.client_object;
//            Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];
//
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[1].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[2].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[3].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
////            DataLoader.OPENGL_FLOATBUFFER.limit(4);
//            DataLoader.setFloatBuffer(objectdata.screen_rgba_float_array);
//            GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//        };
//    }
//
//    public static void setFUsingMultiUniform()
//    {
//        OpenGLObjectDrawing.SET_UNIFORM_WORLD_CONSUMER = (objectentities) ->
//        {
//            ObjectData objectdata = (ObjectData)objectentities.client_object;
//            Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];
//
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, MixinActiveRenderInfo.PROJECTION());
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[1], true, MixinActiveRenderInfo.MODELVIEW());
//
////            DataLoader.OPENGL_FLOATBUFFER.limit(16);
////                GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, (FloatBuffer)FieldsReflectLoader.getField(FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY, 0, null));
////                DataLoader.setFloatBuffer(WorldMath.VIEW_M4X4.mat);
////                GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
////                DataLoader.setFloatBuffer(skinningdata.m4x4_array[0].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//
////            DataLoader.OPENGL_FLOATBUFFER.limit(4);
//            if (objectdata.glow_boolean_array[DataLoader.SCREEN_INDEX])
//            {
//                DataLoader.setFloatBuffer(new float[]{1.0F, 1.0F, 1.0F, objectdata.rgba_float_array[3]});
//                GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//            }
//            else
//            {
//                DataLoader.setFloatBuffer(objectdata.rgba_float_array);
//                GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
////                    GL20.glUniform4((int)temp_uniform_object_array[3], MixinRenderHelper.COLOR_BUFFER());
//            }
//        };
//
//        OpenGLObjectDrawing.SET_UNIFORM_SCREEN_CONSUMER = (objectentities) ->
//        {
//            ObjectData objectdata = (ObjectData)objectentities.client_object;
//            Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])objectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];
//
////            DataLoader.OPENGL_FLOATBUFFER.limit(16);
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[1].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[2].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(objectdata.m4x4_array[3].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
////            DataLoader.OPENGL_FLOATBUFFER.limit(4);
//            DataLoader.setFloatBuffer(objectdata.screen_rgba_float_array);
//            GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//        };
//    }
}
