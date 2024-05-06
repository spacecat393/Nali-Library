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
//    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/EntityRenderer;updateLightmap(F)V", shift = At.Shift.AFTER))
    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", shift = At.Shift.BEFORE))
    private void renderWorld(float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
    {
        DrawWorld.run();
    }
}
