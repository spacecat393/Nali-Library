package com.nali.mixin;

import com.nali.dumb.DSemaphore;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.SplashProgress;
import org.spongepowered.asm.mixin.*;

import java.util.concurrent.Semaphore;

@Mixin(SplashProgress.class)
public abstract class MixinSplashProgress
{
	@Shadow(remap = false) private static Thread thread;
	@Mutable
	@Shadow(remap = false) @Final private static Semaphore mutex;

	static
	{
		thread = new Thread(() -> {});
		mutex = new DSemaphore();
	}

	//	@Inject(method = "finish", at = @At("TAIL"), remap = false)
//	private static void nali_finish(CallbackInfo ci)
	@Overwrite(remap = false)
	public static void finish()
	{
	}

	@Overwrite(remap = false)
	public static void start()
	{
	}

	@Overwrite(remap = false)
	public static void resume()
	{
	}

	@Overwrite(remap = false)
	public static void pause()
	{
	}

	@Overwrite(remap = false)
	public static void drawVanillaScreen(TextureManager renderEngine)
	{
	}

	@Overwrite(remap = false)
	public static void clearVanillaResources(TextureManager renderEngine, ResourceLocation mojangLogo)
	{
	}
}
