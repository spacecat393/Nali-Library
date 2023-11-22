package com.nali.system.opengl.drawing;

import com.nali.entities.data.GUIObjectData;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLBuffer;
import com.nali.system.opengl.buffer.OpenGLObjectBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class OpenGLGUIObjectDrawing
{
    public static Consumer<GUIObjectData> SET_UNIFORM_SCREEN_CONSUMER;

    public static void startScreenObjectGL(GUIObjectData guiobjectdata)
    {
        takeDefault(guiobjectdata);

        Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

        SET_UNIFORM_SCREEN_CONSUMER.accept(guiobjectdata);

        GL20.glUniform1i((int)temp_uniform_object_array[4], 0);

        guiobjectdata.setUniform(temp_uniform_object_array);

        OpenGLBuffer.setTextureBuffer((int)((Object[])guiobjectdata.dataloader.texture_object_array[1])[guiobjectdata.texture_index_int_array[DataLoader.SCREEN_INDEX]], (byte)((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[5]);

        GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
        setDefault(guiobjectdata);
    }

    public static void takeDefault(GUIObjectData guiobjectdata)
    {
        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
        IntBuffer opengl_intbuffer = DataLoader.OPENGL_INTBUFFER;
        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
        Object[] attriblocation_object_array = (Object[])temp_object_array[4];

        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, opengl_intbuffer);
        opengl_object_array[0] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, opengl_intbuffer);
        opengl_object_array[2] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, opengl_intbuffer);
        opengl_object_array[3] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, opengl_intbuffer);
        opengl_object_array[4] = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, opengl_intbuffer);
        opengl_object_array[12] = opengl_intbuffer.get(0);

        OpenGLObjectBuffer.set((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX]);

        GL20.glUseProgram((int)temp_object_array[2]);

        for (Object o : attriblocation_object_array)
        {
            GL20.glEnableVertexAttribArray((int)o);
        }

        if (!GL11.glIsEnabled(GL11.GL_DEPTH_TEST))
        {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            opengl_object_array[5] = false;
        }

//        if (!GL11.glIsEnabled(GL11.GL_CULL_FACE))
//        {
        if ((byte)((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[4] == 0)
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
            opengl_object_array[6] = true;
        }
        else
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
            opengl_object_array[6] = false;
        }
//        }

        if (!GL11.glIsEnabled(GL11.GL_BLEND))
        {
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
        if (i0 != GL11.GL_SRC_ALPHA || i2 != GL11.GL_ONE_MINUS_SRC_ALPHA || i1 != GL11.GL_ONE || i3 != GL11.GL_ZERO)
        {
            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
            opengl_object_array[10] = i0;
            opengl_object_array[11] = i1;
            opengl_object_array[13] = i2;
            opengl_object_array[14] = i3;
        }
    }

    public static void setDefault(GUIObjectData guiobjectdata)
    {
        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
        Object[] attriblocation_object_array = (Object[])temp_object_array[4];

        for (Object o : attriblocation_object_array)
        {
            GL20.glDisableVertexAttribArray((int)o);
        }

        GL20.glUseProgram((int)opengl_object_array[0]);
        GL13.glActiveTexture((int)opengl_object_array[12]);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, (int)opengl_object_array[2]);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, (int)opengl_object_array[3]);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, (int)opengl_object_array[4]);

        if (opengl_object_array[5] != null)
        {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            opengl_object_array[5] = null;
        }

        if (opengl_object_array[6] != null)
        {
            if ((boolean)opengl_object_array[6])
            {
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
            else
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
            }
            opengl_object_array[6] = null;
        }

        if (opengl_object_array[7] != null)
        {
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
            GL14.glBlendFuncSeparate((int)opengl_object_array[10], (int)opengl_object_array[13], (int)opengl_object_array[11], (int)opengl_object_array[14]);
            opengl_object_array[10] = null;
            opengl_object_array[11] = null;
            opengl_object_array[13] = null;
            opengl_object_array[14] = null;
        }
    }
}
