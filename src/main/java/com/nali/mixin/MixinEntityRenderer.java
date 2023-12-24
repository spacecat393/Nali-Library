package com.nali.mixin;

import com.nali.entities.EntitiesRender;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer
{
    @Inject(method = "renderWorld", at = @At("HEAD"))
    private void renderWorld(float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
    {
        EntitiesRender.FREE_FUNCTION.accept(partialTicks);
    }
}
