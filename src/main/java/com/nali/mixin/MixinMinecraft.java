package com.nali.mixin;

import com.nali.render.SoundRender;
import com.nali.system.Timing;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;

import static com.nali.render.SoundRender.SOUNDRENDER_SET;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
    @Inject(method = "runGameLoop", at = @At(value = "HEAD"))
    @Mutable
    private void runGameLoop(CallbackInfo callbackinfo)
    {
        Timing.count();
        for (SoundRender soundrender : new HashSet<>(SOUNDRENDER_SET))
        {
            soundrender.loop();
        }
    }
}
