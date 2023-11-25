package com.nali.entities.data;

import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.IntBuffer;

@SideOnly(Side.CLIENT)
public abstract class MainData
{
    public Object[] model_address_object_array;

    public static void takeDefault(MainData maindata)
    {
        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
        IntBuffer opengl_intbuffer = DataLoader.OPENGL_INTBUFFER;
        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])maindata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
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

        maindata.set((Object[])maindata.model_address_object_array[DataLoader.SCREEN_INDEX]);

        GL20.glUseProgram((int)temp_object_array[2]);

        for (Object o : attriblocation_object_array)
        {
            GL20.glEnableVertexAttribArray((int)o);
        }

        opengl_object_array[5] = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

//        opengl_object_array[15] = GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK);
//        opengl_object_array[15] = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
//        opengl_object_array[15] = GL11.glIsEnabled(GL11.GL_LIGHTING);
//        GL11.glDisable(GL11.GL_LIGHTING);
//        GL11.glEnable(GL11.GL_ALPHA_TEST);
//        GL11.glGetInteger(GL11.GL_FRONT_FACE, opengl_intbuffer);
//        opengl_object_array[15] = opengl_intbuffer.get(0);

//        if (!GL11.glIsEnabled(GL11.GL_CULL_FACE))
//        {
        opengl_object_array[6] = GL11.glIsEnabled(GL11.GL_CULL_FACE);

        if ((byte)((Object[])maindata.model_address_object_array[DataLoader.SCREEN_INDEX])[4] == 0)
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
        else
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
//        switch ((byte)((Object[])maindata.model_address_object_array[DataLoader.SCREEN_INDEX])[4])
//        {
//            case 0:
//            {
////                GL11.glDepthMask(false);
////                GL11.glEnable(GL11.GL_ALPHA_TEST);
////                GL11.glAlphaFunc(GL11.GL_LESS, 1.0F);
////                GL11.glFrontFace(GL11.GL_CW);
//                GL11.glDisable(GL11.GL_CULL_FACE);
//                break;
//            }
//            case 1:
//            {
////                GL11.glDepthMask(false);
////                GL11.glEnable(GL11.GL_ALPHA_TEST);
////                GL11.glAlphaFunc(GL11.GL_LESS, 1.0F);
////                GL11.glFrontFace(GL11.GL_CW);
//                GL11.glEnable(GL11.GL_CULL_FACE);
//                break;
//            }
//            case 2:
//            {
////                GL11.glDepthMask(true);
//                GL11.glDisable(GL11.GL_CULL_FACE);
//                break;
//            }
//            case 3:
//            {
////                GL11.glDepthMask(true);
//                GL11.glEnable(GL11.GL_CULL_FACE);
//                break;
//            }
//            default:
//            {
//                break;
//            }
//        }
//        }

        opengl_object_array[7] = GL11.glIsEnabled(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, opengl_intbuffer);
        int i0 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, opengl_intbuffer);
        int i1 = opengl_intbuffer.get(0);

        GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
        opengl_object_array[8] = i0;
        opengl_object_array[9] = i1;

        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, opengl_intbuffer); //GL_BLEND_SRC
        i0 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, opengl_intbuffer); //GL_BLEND_DST
        i1 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, opengl_intbuffer);
        int i2 = opengl_intbuffer.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, opengl_intbuffer);
        int i3 = opengl_intbuffer.get(0);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        opengl_object_array[10] = i0;
        opengl_object_array[11] = i1;
        opengl_object_array[13] = i2;
        opengl_object_array[14] = i3;
    }

    public static void setDefault(MainData maindata)
    {
        Object[] opengl_object_array = DataLoader.OPENGL_OBJECT_ARRAY;
        Object[] temp_object_array = (Object[])((Object[])((Object[])((Object[])maindata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0];
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

        if ((boolean)opengl_object_array[5])
        {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
        else
        {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }

//        GL11.glFrontFace((int)opengl_object_array[15]);
//        GL11.glDepthMask((boolean)opengl_object_array[16]);
//        if ((boolean)opengl_object_array[15])
//        {
//            GL11.glEnable(GL11.GL_ALPHA_TEST);
//        }
//        else
//        {
//            GL11.glDisable(GL11.GL_ALPHA_TEST);
//        }
//        if ((boolean)opengl_object_array[15])
//        {
//            GL11.glEnable(GL11.GL_LIGHTING);
//        }
//        else
//        {
//            GL11.glDisable(GL11.GL_LIGHTING);
//        }

        if ((boolean)opengl_object_array[6])
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
        else
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }

        if ((boolean)opengl_object_array[7])
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        else
        {
            GL11.glDisable(GL11.GL_BLEND);
        }

        GL20.glBlendEquationSeparate((int)opengl_object_array[8], (int)opengl_object_array[9]);

        GL14.glBlendFuncSeparate((int)opengl_object_array[10], (int)opengl_object_array[13], (int)opengl_object_array[11], (int)opengl_object_array[14]);
    }

    public abstract void set(Object[] object_array);
}
