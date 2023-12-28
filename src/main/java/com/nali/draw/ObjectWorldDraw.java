package com.nali.draw;

import com.nali.config.MyConfig;
import com.nali.render.ObjectRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.util.function.Consumer;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_INTBUFFER;

public class ObjectWorldDraw
{
    public static Consumer<OpenGLObjectMemory> DRAW_CONSUMER;
    public ObjectRender objectrender;

    public ObjectWorldDraw(ObjectRender objectrender)
    {
        this.objectrender = objectrender;
    }

    public void renderWorld(/*double ox, double oy, double oz, float scale*/)
    {
        GL11.glPushMatrix();
//        GL11.glTranslated(ox, oy, oz);

        float rx = -90.0F;
//        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(rx, 1.0F, 0.0F, 0.0F);

        for (int i = 0; i < this.objectrender.memory_object_array.length; ++i)
        {
            if (this.objectrender.model_boolean_array[i])
            {
                this.drawWorld(i);
            }
        }

//        GL11.glRotatef(-rx, 1.0F, 0.0F, 0.0F);
//        GL11.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
//
//        GL11.glTranslated(-ox, -oy, -oz);
        GL11.glPopMatrix();
    }

    public void drawWorld(int index)
    {
        OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)this.objectrender.memory_object_array[index];
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
        this.objectrender.takeDefault(openglobjectmemory);

        if (this.objectrender.glow_boolean_array[index])
        {
            this.objectrender.lig_b = 208.0F;
            this.objectrender.lig_s = 240.0F;
//            GL11.glColor4f(1.0F, 1.0F, 1.0F, this.objectrender.a);
        }
        else
        {
            this.objectrender.updateLightCoord();
        }

        this.objectrender.setUniform(openglobjectmemory, openglobjectshadermemory, index);

        DRAW_CONSUMER.accept(openglobjectmemory);

        this.objectrender.setDefault(openglobjectmemory);
    }

    public static void setTUsingFrameBufferIndex()
    {
        DRAW_CONSUMER = (openglobjectmemory) ->
        {
            GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
            int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
            GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
            int read_frame_buffer = OPENGL_INTBUFFER.get(0);

            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, MyConfig.FRAME.frame_buffer_index);

            GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);

            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
        };
    }

    public static void setFUsingFrameBufferIndex()
    {
        DRAW_CONSUMER = (openglobjectmemory) ->
        {
            GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
        };
    }

    public static void loadWithConfig()
    {
        if (MyConfig.FRAME.using_frame_buffer_index)
        {
            ObjectWorldDraw.setTUsingFrameBufferIndex();
        }
        else
        {
            ObjectWorldDraw.setFUsingFrameBufferIndex();
        }
    }
}
