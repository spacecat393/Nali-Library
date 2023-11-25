package com.nali.system.opengl.drawing;

import com.nali.entities.data.GUIObjectData;
import com.nali.entities.data.MainData;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class OpenGLGUIObjectDrawing
{
//    public static Consumer<GUIObjectData> SET_UNIFORM_SCREEN_CONSUMER;

    public static void startScreenObjectGL(GUIObjectData guiobjectdata)
    {
        MainData.takeDefault(guiobjectdata);

        Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];

//        SET_UNIFORM_SCREEN_CONSUMER.accept(guiobjectdata);
//        DataLoader.OPENGL_FLOATBUFFER.limit(16);
        DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[0].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
        DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[1].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
        DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[2].mat);
        GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//        DataLoader.OPENGL_FLOATBUFFER.limit(4);

//        if (guiobjectdata.screen_rgba_float_array[3] < 1.0F)
//        {
////            GL11.glEnable(GL11.GL_ALPHA_TEST);
////            GL11.glAlphaFunc(GL11.GL_LESS, 1.0F);
////            GL11.glFrontFace(GL11.GL_CW);
////            GL11.glDepthMask(false);
//        }

        DataLoader.setFloatBuffer(guiobjectdata.screen_rgba_float_array);

        GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);

        GL20.glUniform1i((int)temp_uniform_object_array[4], 0);

        guiobjectdata.setUniform(temp_uniform_object_array);

        OpenGLBuffer.setTextureBuffer((int)((Object[])guiobjectdata.dataloader.texture_object_array[1])[guiobjectdata.texture_index_int_array[DataLoader.SCREEN_INDEX]], (byte)((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[5]);

        GL11.glDrawElements(GL11.GL_TRIANGLES, (int)((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[3], GL11.GL_UNSIGNED_INT, 0);
        MainData.setDefault(guiobjectdata);
    }

//    public static void setTUsingMultiUniform()
//    {
//        OpenGLGUIObjectDrawing.SET_UNIFORM_SCREEN_CONSUMER = (guiobjectdata) ->
//        {
//            Object[] temp_uniform_object_array = (Object[])((Object[])((Object[])((Object[])((Object[])guiobjectdata.model_address_object_array[DataLoader.SCREEN_INDEX])[6])[0])[0])[3];
//
//            DataLoader.OPENGL_FLOATBUFFER.limit(16);
//            DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[0].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[0], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[1].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[1], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.setFloatBuffer(guiobjectdata.m4x4_array[2].mat);
//            GL20.glUniformMatrix4((int)temp_uniform_object_array[2], false, DataLoader.OPENGL_FLOATBUFFER);
//            DataLoader.OPENGL_FLOATBUFFER.limit(4);
//            DataLoader.setFloatBuffer(guiobjectdata.screen_rgba_float_array);
//            GL20.glUniform4((int)temp_uniform_object_array[3], DataLoader.OPENGL_FLOATBUFFER);
//        };
//    }
//
//    public static void setFUsingMultiUniform()
//    {
//
//    }
}
