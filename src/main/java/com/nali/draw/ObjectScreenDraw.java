package com.nali.draw;

import com.nali.render.ObjectRender;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import org.lwjgl.opengl.GL11;

public class ObjectScreenDraw
{
    public ObjectRender objectrender;

    public ObjectScreenDraw(ObjectRender objectrender)
    {
        this.objectrender = objectrender;
    }

    public void renderScreen(float new_r, float new_g, float new_b, float new_a)
    {
        GL11.glPushMatrix();
        float r = this.objectrender.r;
        float g = this.objectrender.g;
        float b = this.objectrender.b;
        float a = this.objectrender.a;

//        this.objectrender.r = new_r;
//        this.objectrender.g = new_g;
//        this.objectrender.b = new_b;
//        this.objectrender.a = new_a;
        GL11.glColor4f(new_r, new_g, new_b, new_a);

        float sx = (this.objectrender.sx == 0 ? 1.0F : this.objectrender.sx);
        float sy = (this.objectrender.sy == 0 ? 1.0F : this.objectrender.sy);
        float sz = (this.objectrender.sz == 0 ? 1.0F : this.objectrender.sz);
        GL11.glTranslatef(this.objectrender.x, this.objectrender.y, this.objectrender.z);
        GL11.glScalef(sx, sy, sz);
        GL11.glRotatef(this.objectrender.rx, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(this.objectrender.ry, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.objectrender.rz, 0.0F, 0.0F, 1.0F);

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

        this.objectrender.r = r;
        this.objectrender.g = g;
        this.objectrender.b = b;
        this.objectrender.a = a;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public void drawScreen(int index)
    {
        OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)this.objectrender.memory_object_array[index];
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
        this.objectrender.takeDefault(openglobjectmemory);

        this.objectrender.setUniform(openglobjectmemory, openglobjectshadermemory, index);

        GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
        this.objectrender.setDefault(openglobjectmemory);
    }
}
