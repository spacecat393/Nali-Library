package com.nali.mixin;

import com.nali.system.ServerLoader;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DedicatedServer.class)
public abstract class MixinDedicatedServer
{
	@Inject(method = "init", at = @At(value = "HEAD"))
	private void nali_init(CallbackInfoReturnable<Boolean> cir)
	{
		new ServerLoader().init();
	}
}
