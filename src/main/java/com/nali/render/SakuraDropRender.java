package com.nali.render;

import com.nali.system.opengl.OpenGLBuffer;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;

import java.util.Map;
import java.util.WeakHashMap;

import static com.nali.Nali.RANDOM;
import static com.nali.system.Timing.TD;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
public class SakuraDropRender
{
    public static Map<Integer, SakuraDropRender> SAKURADROPGUIDATA_MAP = new WeakHashMap<>();
    public static int INDEX;
    public int id, texture_buffer, texture_width, texture_height;

    public long time, max_time;
    public float mrx, mry, mrz;
    public float ms;
    public float fy;

    public float x, y, z;
    public float rx, ry, rz;
    public float sx, sy, sz;

    public SakuraDropRender(int texture_buffer, int texture_width, int texture_height)
    {
        this.texture_buffer = texture_buffer;
        this.texture_width = texture_width;
        this.texture_height = texture_height;

        this.time = Minecraft.getSystemTime();//System.currentTimeMillis();
        this.ms = 1.0F / (RANDOM.nextInt(3) + 8);
        this.mrx = (45.0F / (RANDOM.nextInt(3) + 5)) - (45.0F / (RANDOM.nextInt(3) + 5));
        this.mry = (45.0F / (RANDOM.nextInt(3) + 5)) - (45.0F / (RANDOM.nextInt(3) + 5));
        this.mrz = (45.0F / (RANDOM.nextInt(3) + 5)) - (45.0F / (RANDOM.nextInt(3) + 5));
        this.max_time = 1000 + RANDOM.nextInt(2500);
        this.fy = -3.0F / (RANDOM.nextInt(3) + 1);
        float s = 1.0F / (RANDOM.nextInt(2) + 2);
        this.sx = s;
        this.sy = s;
        this.sz = s;

        ++INDEX;

        if (INDEX == Integer.MAX_VALUE)
        {
            INDEX = 0;
        }

        this.id = INDEX;
        SakuraDropRender.SAKURADROPGUIDATA_MAP.put(this.id, this);
    }

    public void renderScreen(float new_r, float new_g, float new_b, float new_a)
    {
        this.fy += 0.1F * TD;
        this.y += this.fy * TD;
//        this.s -= this.ms * TD;
        this.rx -= this.mrx * TD;
        this.ry -= this.mry * TD;
        this.rz -= this.mrz * TD;

        if (Minecraft.getSystemTime()/*System.currentTimeMillis()*/ - this.time >= this.max_time)
        {
            SakuraDropRender.SAKURADROPGUIDATA_MAP.remove(this.id);
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(this.x, this.y, this.z);
        GL11.glColor4f(new_r, new_g, new_b, new_a);

//        GL11.glTranslatef(this.texture_width / 2.0F, this.texture_height / 2.0F, 0);
        GL11.glScalef(this.sx == 0 ? 1.0F : this.sx, this.sy == 0 ? 1.0F : this.sy, this.sz == 0 ? 1.0F : this.sz);
        GL11.glRotatef(this.rx, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(this.ry, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.rz, 0.0F, 0.0F, 1.0F);

        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);

        GL_TEXTURE_WRAP_S = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
        GL_TEXTURE_WRAP_T = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
        GL_TEXTURE_MIN_FILTER = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_CULL_FACE);

        GL_BLEND = GL11.glIsEnabled(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);

        GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
        GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
        GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        OpenGLBuffer.setTextureBuffer(this.texture_buffer, (byte)0);

        RenderHelper.drawModalRectWithCustomSizedTexture(-this.texture_width / 2.0F, this.texture_height / 2.0F, 0, 0, this.texture_width, -this.texture_height, this.texture_width, -this.texture_height);
//        RenderHelper.drawModalRectWithCustomSizedTexture(-8, -8, 0, 0, 16, 16, 16, 16);

        GL13.glActiveTexture(GL_ACTIVE_TEXTURE);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER);

        if (GL_CULL_FACE)
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
        else
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }

        if (GL_BLEND)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        else
        {
            GL11.glDisable(GL11.GL_BLEND);
        }

        GL20.glBlendEquationSeparate(GL_BLEND_EQUATION_RGB, GL_BLEND_EQUATION_ALPHA);
        GL14.glBlendFuncSeparate(GL_BLEND_SRC_RGB, GL_BLEND_DST_RGB, GL_BLEND_SRC_ALPHA, GL_BLEND_DST_ALPHA);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

//    public void renderWorld()
//    {
//        GL11.glPushMatrix();
////        GL11.glScalef(0.25F, 0.25F, 0.25F);
//        GL11.glTranslated(8 / 64.0F, 8 / 64.0F, 0);
////        GL11.glTranslated(ox, oy, oz);
////        GL11.glRotatef(-180.0F, 0.0F, 0.0F, 1.0F);
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////        GL11.glTranslated(-16, -16, 0);
////        GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
////        Minecraft.getMinecraft().getTextureManager().bindTexture(com.nali.render.RenderHelper.GUI0_RESOURCELOCATION);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture_buffer);
//        RenderHelper.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, -16 / 64.0F, -16 / 64.0F, -16 / 64.0F, -16 / 64.0F);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, OPENGL_INTBUFFER.get(0));
//        GL11.glPopMatrix();
//    }
}
