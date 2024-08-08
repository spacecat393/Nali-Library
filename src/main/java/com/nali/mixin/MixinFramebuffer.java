//package com.nali.mixin;
//
//import net.minecraft.client.renderer.GlStateManager;
//import net.minecraft.client.shader.Framebuffer;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Redirect;
//
//import java.nio.IntBuffer;
//
//@Mixin(Framebuffer.class)
//public abstract class MixinFramebuffer
//{
//    @Shadow public int framebufferTextureWidth;
//
//    @Shadow public int framebufferTextureHeight;
//
//    @Redirect(method = "createFramebuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;glTexImage2D(IIIIIIIILjava/nio/IntBuffer;)V"))
//    private void nali_createFramebuffer(int target, int level, int internalFormat, int width, int height, int border, int format, int type, IntBuffer pixels)
//    {
//        GlStateManager.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA16, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, null);
//    }
//}
