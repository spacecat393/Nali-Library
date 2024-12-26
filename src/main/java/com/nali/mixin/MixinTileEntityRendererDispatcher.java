package com.nali.mixin;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//*extra
@Mixin(TileEntityRendererDispatcher.class)
public abstract class MixinTileEntityRendererDispatcher
{
	@Redirect(method = "render(Lnet/minecraft/tileentity/TileEntity;FI)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/tileentity/TileEntity;getMaxRenderDistanceSquared()D"))
	public double nali_render(TileEntity instance)
	{
		return Double.MAX_VALUE;
	}
}
