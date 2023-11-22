package com.nali.mixin;

import com.nali.system.DataLoader;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
    @Inject(method = "runGameLoop", at = @At(value = "HEAD"))
    private void runGameLoop(CallbackInfo callbackinfo)
    {
        long current_time = System.currentTimeMillis();
        DataLoader.DELTA = current_time - DataLoader.LAST_TIME;
        DataLoader.LAST_TIME = current_time;
        DataLoader.TD = DataLoader.TICK * DataLoader.DELTA;
    }
}
