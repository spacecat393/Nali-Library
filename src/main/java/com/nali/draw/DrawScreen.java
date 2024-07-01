package com.nali.draw;

import com.nali.data.client.IClientDaO;
import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DrawScreen<RG extends MemoGo, RS extends MemoSo, RST extends StoreO<RG, RS>, RC extends IClientDaO, R extends RenderO<RG, RS, RST, RC>>
{
    public R r;
//    public ObjectRender objectrender;
//    public float r = 1.0F, g = 1.0F, b = 1.0F, a = 1.0F;
//    public float sr = 1.0F, sg = 1.0F, sb = 1.0F, sa = 1.0F;
//    public float width, height;
    public float x, y, z = 50.0F;
    public float rx = -90.0F, ry, rz;
    public float sx = -25.0F, sy = -25.0F, sz = -25.0F;

//    public ObjectScreenDraw(ObjectRender objectrender)
//    {
//        this.objectrender = objectrender;
//    }
    public DrawScreen(R r)
    {
        this.r = r;
    }

    public void scale(float s)
    {
        this.sx = s;
        this.sy = s;
        this.sz = s;
    }

    public void render()
    {
        GL11.glPushMatrix();
//        OpenGlHelper.glPushAttrib(OpenGlHelper.GL_COLOR_BUFFER_BIT);
//        float r = this.objectrender.r;
//        float g = this.objectrender.g;
//        float b = this.objectrender.b;
//        float a = this.objectrender.a;

//        this.objectrender.r = new_r;
//        this.objectrender.g = new_g;
//        this.objectrender.b = new_b;
//        this.objectrender.a = new_a;
//        OpenGlHelper.glColor4f(this.objectrender.sr, this.objectrender.sg, this.objectrender.sb, this.objectrender.sa);

        GL11.glTranslatef(this.x, this.y, this.z);
        GL11.glScalef(this.sx, this.sy, this.sz);
        GL11.glRotatef(this.rx, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(this.ry, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.rz, 0.0F, 0.0F, 1.0F);

//        this.objectrender.multiplyAnimation();

        this.r.draw();
//        for (int i = this.objectrender.clientdata.StartPart(); i < this.objectrender.clientdata.EndPart(); ++i)
//        {
//            if ((this.objectrender.model_byte_array[i / 8] >> i % 8 & 1) == 1)
//            {
//                OpenGLObjectMemory openglobjectmemory = this.objectrender.dataloader.openglobjectmemory_array[i];
//                OpenGLObjectShaderMemory openglobjectshadermemory = this.objectrender.dataloader.openglobjectshadermemory_array[openglobjectmemory.shader_id];
//                this.drawScreen(openglobjectmemory, openglobjectshadermemory, i);
//            }
//        }

//        OpenGlHelper.glRotatef(-this.objectrender.rz, 0.0F, 0.0F, 1.0F);
//        OpenGlHelper.glRotatef(-this.objectrender.ry, 0.0F, 1.0F, 0.0F);
//        OpenGlHelper.glRotatef(-this.objectrender.rx, 1.0F, 0.0F, 0.0F);
//        OpenGlHelper.glScalef(1.0F / sx, 1.0F / sy, 1.0F / sz);
//        OpenGlHelper.glTranslatef(-this.objectrender.x, -this.objectrender.y, -this.objectrender.z);

//        this.objectrender.r = r;
//        this.objectrender.g = g;
//        this.objectrender.b = b;
//        this.objectrender.a = a;
//        OpenGlHelper.glPopAttrib();
        GL11.glPopMatrix();
    }

//    public void drawScreen(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
//    {
//        this.objectrender.takeDefault(openglobjectmemory, openglobjectshadermemory);
//
//        this.setUniform(openglobjectmemory, openglobjectshadermemory, index);
//
//        GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
//        this.objectrender.setDefault(openglobjectshadermemory);
//    }
//
//    public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
//    {
//        this.objectrender.setFixedPipe(openglobjectshadermemory);
//        this.objectrender.setTextureUniform(openglobjectmemory, openglobjectshadermemory);
//        if (this.objectrender.lig_b != -1.0F)
//        {
//            this.objectrender.setLightMapUniform(openglobjectshadermemory);
//        }
//        this.objectrender.setLightCoord(openglobjectshadermemory);
//        this.objectrender.setUniform(openglobjectmemory, openglobjectshadermemory, index);
//    }
}
