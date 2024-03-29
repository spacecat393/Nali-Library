package com.nali.draw;

import com.nali.config.MyConfig;
import com.nali.render.ObjectRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import java.util.function.Consumer;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_INTBUFFER;

@SideOnly(Side.CLIENT)
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
//        OBJECT_ARRAYLIST.add(this.objectrender);
//        DOUBLE_ARRAYLIST.add(ox);
//        DOUBLE_ARRAYLIST.add(oy);
//        DOUBLE_ARRAYLIST.add(oz);
//        FLOAT_ARRAYLIST.add(this.objectrender.entitiesrendermemory.scale)
//        int should_add = 0;
//        //Array here
////
////        if ((this.objectrender.glow_byte_array[index / 8] >> index % 8 & 1) == 1)
////        {
////            this.objectrender.lig_b = -1.0F;
////            this.objectrender.lig_s = -1.0F;
////        }
////        else
////        {
////            this.objectrender.updateLightCoord();
////        }
//
//        for (int i = 0; i < OBJECT_ARRAYLIST.size(); ++i)
//        {
//            for (int l = 0; l < this.objectrender.memory_object_array.length; ++l)
//            {
//                if ((this.objectrender.model_byte_array[i / 8] >> i % 8 & 1) == 1)
//                {
//                    if (OBJECT_ARRAYLIST.get(i) == this.objectrender.memory_object_array[l])
//                    {
//                        should_add = -1;
//                        INTEGER_ARRAYLIST.add(i);
//                        POS_DOUBLE_2D_ARRAYLIST.get(i).add(ox);
//                        POS_DOUBLE_2D_ARRAYLIST.get(i).add(oy);
//                        POS_DOUBLE_2D_ARRAYLIST.get(i).add(oz);
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (should_add != -1)
//        {
//            OBJECT_ARRAYLIST.add(this.objectrender.memory_object_array[should_add]);
//            int index = this.objectrender.memory_object_array.length;
//            INTEGER_ARRAYLIST.add(index);
//            POS_DOUBLE_2D_ARRAYLIST.add(new ArrayList<Double>());
//            POS_DOUBLE_2D_ARRAYLIST.get(index).add(ox);
//            POS_DOUBLE_2D_ARRAYLIST.get(index).add(oy);
//            POS_DOUBLE_2D_ARRAYLIST.get(index).add(oz);
//            LIG_FLOAT_2D_ARRAYLIST.add(new ArrayList<Float>());
//            LIG_FLOAT_2D_ARRAYLIST.get(index).add(ox);
//            LIG_FLOAT_2D_ARRAYLIST.get(index).add(oy);
//            LIG_FLOAT_2D_ARRAYLIST.get(index).add(oz);
//        }

        GL11.glPushMatrix();
//        GL11.glTranslated(ox, oy, oz);

//        GL11.glScalef(scale, scale, scale);

        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);

        for (int i = 0; i < this.objectrender.getMaxMemory(); ++i)
        {
            if ((this.objectrender.model_byte_array[i / 8] >> i % 8 & 1) == 1)
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
//        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
        OpenGLObjectMemory openglobjectmemory = this.objectrender.getMemory(index);
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
        this.objectrender.takeDefault(openglobjectmemory);

        if ((this.objectrender.glow_byte_array[index / 8] >> index % 8 & 1) == 1)
        {
            this.objectrender.lig_b = -1.0F;
            this.objectrender.lig_s = -1.0F;
//            GL11.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], this.objectrender.a);
        }
        else
        {
//            GL11.glColor4f(this.objectrender.r, this.objectrender.g, this.objectrender.b, this.objectrender.a);
            this.objectrender.updateLightCoord();
        }

        this.setUniform(openglobjectmemory, openglobjectshadermemory, index);

        DRAW_CONSUMER.accept(openglobjectmemory);

//        GL11.glPopAttrib();
        this.objectrender.setDefault(openglobjectmemory);
    }

    public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
    {
        this.objectrender.setFixedPipe(openglobjectshadermemory);
        this.objectrender.setTextureUniform(openglobjectmemory, openglobjectshadermemory, index);
        this.objectrender.setLightMapUniform(openglobjectshadermemory);
        this.objectrender.setLightCoord(openglobjectshadermemory);
        this.objectrender.setUniform(openglobjectmemory, openglobjectshadermemory, index);
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
