package com.nali.mixin;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//*extra
@Mixin(EntityPlayer.class)
public class MixinEntityPlayer
{
	@Overwrite
	public float getCooledAttackStrength(float adjustTicks)
	{
		return 1.0F;
	}
}
