package com.nali.mixin.render.ex.framebuffer;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;

@Mixin(Framebuffer.class)
public abstract class MixinFramebuffer
{
    @Shadow public int framebufferTextureWidth;

    @Shadow public int framebufferTextureHeight;

    @Shadow public int framebufferTexture;

    private int mc_depth_texture;

    //    @Redirect(method = "createFramebuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;glTexImage2D(IIIIIIIILjava/nio/IntBuffer;)V"))
    @Inject(method = "createFramebuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;framebufferClear()V", shift = At.Shift.BEFORE, ordinal = 1))
    private void nali_createFramebuffer(int width, int height, CallbackInfo ci)
    {
        this.mc_depth_texture = GL11.glGenTextures();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc_depth_texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, this.mc_depth_texture, 0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.framebufferTexture);
//        GlStateManager.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA16, this.framebufferTextureWidth, this.framebufferTextureHeight, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, null);
    }

    @Inject(method = "deleteFramebuffer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OpenGlHelper;glDeleteRenderbuffers(I)V", shift = At.Shift.AFTER))
    private void nali_deleteFramebuffer(CallbackInfo ci)
    {
        GL11.glDeleteTextures(this.mc_depth_texture);
    }
}
