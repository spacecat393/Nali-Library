package com.nali.system.opengl.drawing;

import com.nali.entities.data.SkinningData;
import com.nali.entities.skinning.SkinningEntities;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLBuffer;
import com.nali.system.opengl.buffer.OpenGLSkinningBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class OpenGLSkinningDrawing
{
//    public static int VERTEXARRAYS;
    public static Consumer<SkinningData> DRAW_CONSUMER;
    public static Consumer<SkinningEntities> SET_UNIFORM_WORLD_CONSUMER;
    public static Consumer<SkinningEntities> SET_UNIFORM_SCREEN_CONSUMER;
    public static BiConsumer<float[], Object[]> SET_UNIFORM_BICONSUMER;

    public static void startSkinningGL(SkinningEntities skinningentities)
    {
//        if (!DataLoader.REINIT_OPENGL_BUFFER)
//        {
        SkinningData skinningdata = (SkinningData)skinningentities.client_object;
        takeDefault(skinningdata);

        Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

        // GL20.glUniformMatrix4fv((int)temp_uniform_object_array[0], false, (FloatBuffer)object_array[13]);
        // GL20.glUniformMatrix4fv((int)temp_uniform_object_array[1], false, skinning_float_array);

        // GL20.glUniformMatrix4fv((int)temp_uniform_object_array[0], false, (float[])skinningdata.skinning_object_array[skinningdata.index]);
        // GL20.glUniformMatrix4fv((int)temp_uniform_object_array[5], false, ((float[])skinningdata.skinning_object_array[skinningdata.index]));

        SET_UNIFORM_BICONSUMER.accept(skinningdata.skinning_float_array, temp_uniform_object_array);
        SET_UNIFORM_WORLD_CONSUMER.accept(skinningentities);

//            GameSettings gamesettings = Minecraft.getMinecraft().gameSettings;
//            Main.LOGGER.info("Third = " + gamesettings.thirdPersonView);
        //
//            if (this.mc.gameSettings.viewBobbing)
//            {
//
//            }//((EntityLivingBase)entity).isPlayerSleeping()
//            FloatBuffer float_buffer0 = (FloatBuffer)FieldsReflectLoader.getField(FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY, 0, null);

//            GL20.glUniformMatrix4((int)temp_uniform_object_array[0], true, (FloatBuffer)FieldsReflectLoader.getField(FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY, 0, null));
//            DataLoader.setFloatBuffer(WorldMath.VIEW_M4X4.mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(skinningdata.m4x4_array[0].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//
//            if (skinningdata.glow_boolean_array[skinningdata.index])
//            {
//                DataLoader.setFloatBuffer(new float[]{1.0F, 1.0F, 1.0F});
//                GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//            }
//            else
//            {
//                DataLoader.setFloatBuffer(skinningdata.rgb_float_array);
//                GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//            }

        GL20.glUniform1i((int)temp_uniform_object_array[4], 0);

        skinningentities.setUniform(temp_uniform_object_array);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        // GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
        OpenGLBuffer.setTextureBuffer((int)((Object[])skinningdata.dataloader.texture_object_array[1])[skinningdata.texture_index_int_array[DataLoader.SCREEN_INDEX]], (byte)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[5]);

        DRAW_CONSUMER.accept(skinningdata);

        setDefault(skinningdata);
//        }
    }

    public static void startScreenSkinningGL(SkinningEntities skinningentities)
    {
        SkinningData skinningdata = (SkinningData)skinningentities.client_object;
        takeDefault(skinningdata);

        Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

        // GL20.glUniformMatrix4fv((int)temp_uniform_object_array[0], false, (float[])skinningdata.skinning_object_array[SkinningEntities.SCREEN_INDEX]);
        SET_UNIFORM_BICONSUMER.accept(skinningdata.skinning_float_array, temp_uniform_object_array);
        SET_UNIFORM_SCREEN_CONSUMER.accept(skinningentities);

//        DataLoader.setFloatBuffer(skinningdata.m4x4_array[1].mat);
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
//        DataLoader.setFloatBuffer(skinningdata.m4x4_array[2].mat);
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
//        DataLoader.setFloatBuffer(skinningdata.m4x4_array[3].mat);
//        GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//        DataLoader.setFloatBuffer(WorldMath.SCREEN_RGB_FLOAT_ARRAY);
//        GL20.glUniform3((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);

//        {
//            long function_pointer = GLContext.getCapabilities().glUniformMatrix4fv;
//            nglUniformMatrix4fv(location, matrices.remaining() >> 4, transpose, MemoryUtil.getAddress(matrices), function_pointer);
//        }

        GL20.glUniform1i((int)temp_uniform_object_array[4], 0);

        skinningentities.setUniform(temp_uniform_object_array);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        // GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
        OpenGLBuffer.setTextureBuffer((int)((Object[])skinningdata.dataloader.texture_object_array[1])[skinningdata.texture_index_int_array[DataLoader.SCREEN_INDEX]], (byte)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[5]);

//        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, (int)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3]);
        GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);

        setDefault(skinningdata);
    }

    public static void takeDefault(SkinningData skinningdata)
    {
//        GL11.glEnable(GL11.GL_VERTEX_ARRAY);
//        GL11.glEnable(GL11.GL_NORMAL_ARRAY);
        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
        IntBuffer opengl_intbuffer = DataLoader.OPENGL_INTBUFFER;
        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
        Object[] attriblocation_object_array = (Object[])temp_object_array[4];

        // GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        // GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);

        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, opengl_intbuffer);
        opengl_object_array[0] = opengl_intbuffer.get(0);
//        GL11.glGetInteger(GL30.GL_VERTEX_ARRAY_BINDING, opengl_intbuffer);
//        opengl_object_array[1] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, opengl_intbuffer);
        opengl_object_array[2] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, opengl_intbuffer);
        opengl_object_array[3] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, opengl_intbuffer);
        opengl_object_array[4] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, opengl_intbuffer);
        opengl_object_array[12] = opengl_intbuffer.get(0);

        // opengl_object_array[12] = GlStateManager.getActiveTexture();

        // can move preload here
        //

//        VERTEXARRAYS = GL30.glGenVertexArrays();
//        GL30.glBindVertexArray(VERTEXARRAYS);
//        GL30.glBindVertexArray(0);

        // if (temp_object_array[0] == null)
        // {
        //     OpenGLSkinningBuffer.load(skinningdata.model_object_arraylist.get(index));
        // }
        // else
        // {
        OpenGLSkinningBuffer.set((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX]);
        // }

        GL20.glUseProgram((int)temp_object_array[2]);

        for (Object o : attriblocation_object_array)
        {
            GL20.glEnableVertexAttribArray((int)o);
        }

        if (!GL11.glIsEnabled(GL11.GL_DEPTH_TEST))
        {
            // GlStateManager.enableDepth();
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            opengl_object_array[5] = false;
        }

//        if (!GL11.glIsEnabled(GL11.GL_CULL_FACE))
//        {
        if ((byte)((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[4] == 0)
        {
             // GlStateManager.disableCull();
             GL11.glDisable(GL11.GL_CULL_FACE);
             opengl_object_array[6] = true;
        }
        else
        {
             // GlStateManager.enableCull();
             GL11.glEnable(GL11.GL_CULL_FACE);
             opengl_object_array[6] = false;
        }
//        }

        if (!GL11.glIsEnabled(GL11.GL_BLEND))
        {
            // GlStateManager.enableBlend();
            GL11.glEnable(GL11.GL_BLEND);
            opengl_object_array[7] = false;
        }

        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, opengl_intbuffer);
        int i0 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, opengl_intbuffer);
        int i1 = opengl_intbuffer.get(0);

        if (i0 != GL14.GL_FUNC_ADD || i1 != GL14.GL_FUNC_ADD)
        {
            GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
            opengl_object_array[8] = i0;
            opengl_object_array[9] = i1;
        }

        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, opengl_intbuffer); //GL_BLEND_SRC
        i0 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, opengl_intbuffer); //GL_BLEND_DST
        i1 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, opengl_intbuffer);
        int i2 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, opengl_intbuffer);
        int i3 = opengl_intbuffer.get(0);
//        if (i0 != GL11.GL_ONE || i1 != GL11.GL_ONE_MINUS_SRC_ALPHA)
        if (i0 != GL11.GL_SRC_ALPHA || i2 != GL11.GL_ONE_MINUS_SRC_ALPHA || i1 != GL11.GL_ONE || i3 != GL11.GL_ZERO)
        {
            // GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
//            GL14.glBlendFuncSeparate(i0, i2, i1, i3);
//            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
            opengl_object_array[10] = i0;
            opengl_object_array[11] = i1;
            opengl_object_array[13] = i2;
            opengl_object_array[14] = i3;
        }

//        for (int i = 0; i < opengl_intbuffer_array.length; ++i)
//        {
//            opengl_intbuffer_array[i].clear();
//        }
    }

    public static void setDefault(SkinningData skinningdata)
    {
        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])skinningdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
        Object[] attriblocation_object_array = (Object[])temp_object_array[4];

        for (Object o : attriblocation_object_array)
        {
            GL20.glDisableVertexAttribArray((int)o);
        }
        // GL20.glDisableVertexAttribArray(4);

//        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, 0, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//        GL30.glBindVertexArray(0);
//        GL20.glUseProgram(0);
//        GL11.glDisable(GL11.GL_VERTEX_ARRAY);
//        GL11.glDisable(GL11.GL_NORMAL_ARRAY);

        GL20.glUseProgram((int)opengl_object_array[0]);
        GL13.glActiveTexture((int)opengl_object_array[12]);
//         // GlStateManager.setActiveTexture((int)opengl_object_array[12]);
//        GL30.glBindVertexArray((int)opengl_object_array[1]);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, (int)opengl_object_array[2]);
// //        GlStateManager.bindTexture((Integer)opengl_object_array[3]);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, (int)opengl_object_array[3]);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, (int)opengl_object_array[4]);

        if (opengl_object_array[5] != null)
        {
            // GlStateManager.disableDepth();
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            opengl_object_array[5] = null;
        }

        if (opengl_object_array[6] != null)
        {
            if ((boolean)opengl_object_array[6])
            {
                GL11.glEnable(GL11.GL_CULL_FACE);
                // GlStateManager.enableCull();
            }
            else
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
                // GlStateManager.disableCull();
            }
            opengl_object_array[6] = null;
        }

        if (opengl_object_array[7] != null)
        {
            // GlStateManager.disableBlend();
            GL11.glDisable(GL11.GL_BLEND);
            opengl_object_array[7] = null;
        }

        if (opengl_object_array[8] != null)
        {
            GL20.glBlendEquationSeparate((int)opengl_object_array[8], (int)opengl_object_array[9]);
            opengl_object_array[8] = null;
            opengl_object_array[9] = null;
        }

        if (opengl_object_array[10] != null)
        {
            // GlStateManager.blendFunc((int)opengl_object_array[10], (int)opengl_object_array[11]);
//            GL11.glBlendFunc((int)opengl_object_array[10], (int)opengl_object_array[11]);
            GL14.glBlendFuncSeparate((int)opengl_object_array[10], (int)opengl_object_array[13], (int)opengl_object_array[11], (int)opengl_object_array[14]);
            opengl_object_array[10] = null;
            opengl_object_array[11] = null;
            opengl_object_array[13] = null;
            opengl_object_array[14] = null;
        }

//        GL30.glDeleteVertexArrays(VERTEXARRAYS);
    }
}
