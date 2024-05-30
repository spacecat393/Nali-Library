package com.nali.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHelper
{
    //net.minecraft.client.gui.Gui
    public static void drawModalRectWithCustomSizedTexture(double x, double y, double u, double v, double width, double height, double textureWidth, double textureHeight)
    {
        double f = 1.0F / textureWidth;
        double f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(x, y + height, 0.0D).tex(u * f, (v + height) * f1).endVertex();
        bufferbuilder.pos(x + width, y + height, 0.0D).tex((u + width) * f, (v + height) * f1).endVertex();
        bufferbuilder.pos(x + width, y, 0.0D).tex((u + width) * f, v * f1).endVertex();
        bufferbuilder.pos(x, y, 0.0D).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    public static int getTextureBuffer(/*TextureManager texturemanager, */ResourceLocation resourcelocation)
    {
//        Nali.LOGGER.info("start T");
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
//        Nali.LOGGER.info("texturemanager " + texturemanager);
        ITextureObject itextureobject = texturemanager.getTexture(resourcelocation);
//        Nali.LOGGER.info("F itextureobject " + itextureobject);

        if (itextureobject == null)
        {
//            Nali.LOGGER.info("load!");
            itextureobject = new SimpleTexture(resourcelocation);
//            Nali.LOGGER.info("SimpleTexture " + itextureobject);
            texturemanager.loadTexture(resourcelocation, itextureobject);
        }
//        Nali.LOGGER.info("itextureobject " + itextureobject);

        return itextureobject.getGlTextureId();
    }

    //    public static void blit(int x, int y, float tx, float ty, int txw, int tyw, int w, int h)
//    {
//        blit(x, y, txw, tyw, tx, ty, txw, tyw, w, h);
//    }
//
//    public static void blit(int x, int y, int stxw, int styw, float tx, float ty, int etxw, int etyw, int w, int h)
//    {
//        innerBlit(x, x + stxw, y, y + styw, 0, etxw, etyw, tx, ty, w, h);
//    }
//
//    public static void innerBlit(int sx, int ex, int sy, int ey, int z, int etxw, int etyw, float tx, float ty, int w, int h)
//    {
//        innerBlit(sx, ex, sy, ey, z, (tx + 0.0F) / (float)w, (tx + (float)etxw) / (float)w, (ty + 0.0F) / (float)h, (ty + (float)etyw) / (float)h);
//    }
//
//    public static void innerBlit(int sx, int ex, int sy, int ey, int z, float stx, float sty, float etx, float ety)
//    {
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder bufferbuilder = tessellator.getBuffer();
//        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
//
//        bufferbuilder.pos(sx, ey, z).tex(stx, ety).endVertex();
//        bufferbuilder.pos(ex, ey, z).tex(sty, ety).endVertex();
//        bufferbuilder.pos(ex, sy, z).tex(sty, etx).endVertex();
//        bufferbuilder.pos(sx, sy, z).tex(stx, etx).endVertex();
//
//        tessellator.draw();
//    }

//    public static OpenGLTextureMemory OPENGLTEXTUREMEMORY = new OpenGLTextureMemory(com.nali.system.Reference.MOD_ID + "/" + Reference.MOD_ID);
}
