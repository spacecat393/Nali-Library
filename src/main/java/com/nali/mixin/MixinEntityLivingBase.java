package com.nali.mixin;

import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//*extra
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase/* extends Entity*/
{
//	@Shadow public int hurtTime;
//	@Shadow public int maxHurtTime;
//	@Shadow public int maxHurtResistantTime;

//	public MixinEntityLivingBase(World worldIn)
//	{
//		super(worldIn);
//	}

//	@Inject(remap = false, method = "onUpdate", at = @At(value = "TAIL"))
//	private void nali_onUpdate(CallbackInfo ci)
//	{
//		this.hurtTime = 0;
//		this.maxHurtTime = 0;
//		this.hurtResistantTime = 0;
//		this.maxHurtResistantTime = 0;
//	}

//	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;hurtTime:I"))
//	private void nali_attackEntityFrom_hurtTime(EntityLivingBase instance, int value)
//	{
////		this.hurtTime = 0;
//	}
//
//	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;maxHurtTime:I"))
//	private void nali_attackEntityFrom_maxHurtTime(EntityLivingBase instance, int value)
//	{
/// /		this.maxHurtTime = 0;
//	}

	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;hurtResistantTime:I", ordinal = 1))
	private void nali_attackEntityFrom_hurtResistantTime(EntityLivingBase instance, int value)
	{
//		this.hurtResistantTime = 0;
	}

//	@Redirect(method = "attackEntityFrom", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;maxHurtResistantTime:I"))
//	private int nali_attackEntityFrom_maxHurtResistantTime(EntityLivingBase instance)
//	{
//		return 0;
//	}
}
