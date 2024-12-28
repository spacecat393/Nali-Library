package com.nali.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

//*extra
@Mixin(RayTraceResult.class)
public interface IMixinRayTraceResult
{
	@Accessor("blockPos")
	void blockPos(BlockPos blockpos);
}
