package com.nali.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//*extra
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity
{
	@Shadow public int hurtTime;
	@Shadow public int maxHurtTime;
	@Shadow public int maxHurtResistantTime;

	public MixinEntityLivingBase(World worldIn)
	{
		super(worldIn);
	}

	@Inject(remap = false, method = "onUpdate", at = @At(value = "TAIL"))
	private void nali_onUpdate(CallbackInfo ci)
	{
		this.hurtTime = 0;
		this.maxHurtTime = 0;
		this.hurtResistantTime = 0;
		this.maxHurtResistantTime = 0;
	}
}
