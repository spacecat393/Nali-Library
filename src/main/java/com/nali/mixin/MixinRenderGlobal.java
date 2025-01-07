package com.nali.mixin;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.nali.draw.DrawWorld.*;

//@Mixin(value = RenderGlobal.class, priority = Integer.MAX_VALUE)
@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal
{
	private static int PASS;

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos$PooledMutableBlockPos;release()V", shift = At.Shift.AFTER))
	private void nali_renderWorldR(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		PASS = net.minecraftforge.client.MinecraftForgeClient.getRenderPass();

		if (PASS == 0)
		{
//			Nali.warn("0");
			runE();
		}
	}

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;setRenderOutlines(Z)V", shift = At.Shift.BEFORE, ordinal = 1))
	private void nali_renderWorldO(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		if (PASS == 0)
		{
//			Nali.warn("1");
			runEG();
		}
	}

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;postRenderDamagedBlocks()V", shift = At.Shift.BEFORE))
	private void nali_renderWorldB(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		if (PASS == 0)
		{
//			Nali.warn("2");
			runT();
			clear();
		}
	}
}
