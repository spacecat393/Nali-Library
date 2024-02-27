package com.nali.draw;

import com.nali.render.ObjectRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import org.lwjgl.opengl.GL11;

public class ObjectScreenDraw
{
    public ObjectRender objectrender;
//    public float r = 1.0F, g = 1.0F, b = 1.0F, a = 1.0F;
//    public float sr = 1.0F, sg = 1.0F, sb = 1.0F, sa = 1.0F;
//    public float width, height;
    public float x, y, z = 50.0F;
    public float rx = -90.0F, ry, rz;
    public float sx = -25.0F, sy = -25.0F, sz = -25.0F;

    public ObjectScreenDraw(ObjectRender objectrender)
    {
        this.objectrender = objectrender;
    }

    public void renderScreen()
    {
        GL11.glPushMatrix();
//        GL11.glPushAttrib(GL11.GL_COLOR_BUFFER_BIT);
//        float r = this.objectrender.r;
//        float g = this.objectrender.g;
//        float b = this.objectrender.b;
//        float a = this.objectrender.a;

//        this.objectrender.r = new_r;
//        this.objectrender.g = new_g;
//        this.objectrender.b = new_b;
//        this.objectrender.a = new_a;
//        GL11.glColor4f(this.objectrender.sr, this.objectrender.sg, this.objectrender.sb, this.objectrender.sa);

        GL11.glTranslatef(this.x, this.y, this.z);
        GL11.glScalef(this.sx, this.sy, this.sz);
        GL11.glRotatef(this.rx, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(this.ry, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.rz, 0.0F, 0.0F, 1.0F);

        this.objectrender.multiplyAnimation();

        for (int i = 0; i < this.objectrender.memory_object_array.length; ++i)
        {
            if (this.objectrender.model_boolean_array[i])
            {
                this.drawScreen(i);
            }
        }

//        GL11.glRotatef(-this.objectrender.rz, 0.0F, 0.0F, 1.0F);
//        GL11.glRotatef(-this.objectrender.ry, 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(-this.objectrender.rx, 1.0F, 0.0F, 0.0F);
//        GL11.glScalef(1.0F / sx, 1.0F / sy, 1.0F / sz);
//        GL11.glTranslatef(-this.objectrender.x, -this.objectrender.y, -this.objectrender.z);

//        this.objectrender.r = r;
//        this.objectrender.g = g;
//        this.objectrender.b = b;
//        this.objectrender.a = a;
//        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void drawScreen(int index)
    {
        OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)this.objectrender.memory_object_array[index];
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;

        this.objectrender.takeDefault(openglobjectmemory);

        this.setUniform(openglobjectmemory, openglobjectshadermemory, index);

        GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
        this.objectrender.setDefault(openglobjectmemory);
    }

    public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
    {
        this.objectrender.setFixedPipe(openglobjectshadermemory);
        this.objectrender.setTextureUniform(openglobjectmemory, openglobjectshadermemory, index);
        if (this.objectrender.objectworlddraw.lig_b != -1.0F)
        {
            this.objectrender.setLightMapUniform(openglobjectshadermemory);
        }
        this.objectrender.setLightCoord(openglobjectshadermemory);
        this.objectrender.setUniform(openglobjectmemory, openglobjectshadermemory, index);
    }
}
