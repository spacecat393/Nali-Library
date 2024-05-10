package com.nali.mixin;

import com.nali.draw.DrawWorld;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer
{
    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
//    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 1))
    private void nali_renderFWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
    {
        DrawWorld.run();
//        DrawWorld.drawFirst();
    }

//    @Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.AFTER))
//    private void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo callbackinfo)
//    {
//        DrawWorld.run();
//    }

//    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", shift = At.Shift.BEFORE))
//    private void renderWorld(float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
//    private void renderSWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//    {
////        DrawWorld.run();
//        DrawWorld.drawSecond();
//    }
}
