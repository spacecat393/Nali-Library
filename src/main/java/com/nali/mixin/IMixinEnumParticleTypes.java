package com.nali.mixin;

import net.minecraft.util.EnumParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(EnumParticleTypes.class)
public interface IMixinEnumParticleTypes
{
	@Accessor("PARTICLES")
	static Map<Integer, EnumParticleTypes> PARTICLES()
	{
		return null;
	}

	@Accessor("BY_NAME")
	static Map<String, EnumParticleTypes> BY_NAME()
	{
		return null;
	}
}
