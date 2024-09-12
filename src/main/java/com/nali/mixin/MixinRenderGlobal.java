package com.nali.mixin;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.nali.draw.DrawWorld.*;

@Mixin(RenderGlobal.class)
public abstract class MixinRenderGlobal
{
	private static int PASS;
//	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 1))
	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos$PooledMutableBlockPos;release()V", shift = At.Shift.AFTER))
//	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;postRenderDamagedBlocks()V", shift = At.Shift.BEFORE))
	private void nali_renderWorldR(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		PASS = net.minecraftforge.client.MinecraftForgeClient.getRenderPass();

		if (PASS == 0)
		{
//			Nali.LOGGER.info("END0");
			runE();
//			DATA_SIZE = 0;
//			MODEL_MAP.clear();
//			TRANSLUCENT_MAP.clear();
//			GLOW_MAP.clear();
//			DRAWWORLDDATA_LIST.clear();
		}
//		else
//		{
//			Nali.LOGGER.info("D");
//		}
	}

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderManager;setRenderOutlines(Z)V", shift = At.Shift.BEFORE, ordinal = 1))
	private void nali_renderWorldO(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		if (PASS == 0)
		{
//			Nali.LOGGER.info("END1");
			runEG();
		}
	}

	@Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;postRenderDamagedBlocks()V", shift = At.Shift.BEFORE))
	private void nali_renderWorldB(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
	{
		if (PASS == 0)
		{
			runT();
//			Nali.LOGGER.info("END2");
			clear();
		}
	}
}
