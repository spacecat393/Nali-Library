package com.nali.mixin;

import com.nali.NaliConfig;
import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SplashProgress.class)
public abstract class MixinSplashProgress
{
	@Inject(remap = false, method = "start", at = @At(value = "HEAD"), cancellable = true)
	private static void nali_start(CallbackInfo ci)
	{
		if ((NaliConfig.STATE & 32) == 32)
		{
			ci.cancel();
		}
	}
//	@Shadow(remap = false) private static Thread thread;
//	@Mutable
//	@Shadow(remap = false) @Final private static Semaphore mutex;
//
//	static
//	{
//		thread = new Thread(() -> {});
//		mutex = new DSemaphore();
//	}
//
//	//	@Inject(method = "finish", at = @At("TAIL"), remap = false)
////	private static void nali_finish(CallbackInfo ci)
//	@Overwrite(remap = false)
//	public static void finish()
//	{
//	}
//
//	@Overwrite(remap = false)
//	public static void start()
//	{
//	}
//
//	@Overwrite(remap = false)
//	public static void resume()
//	{
//	}
//
//	@Overwrite(remap = false)
//	public static void pause()
//	{
//	}
//
//	@Overwrite(remap = false)
//	public static void drawVanillaScreen(TextureManager renderEngine)
//	{
//	}
//
//	@Overwrite(remap = false)
//	public static void clearVanillaResources(TextureManager renderEngine, ResourceLocation mojangLogo)
//	{
//	}
}
