package com.nali.mixin;

import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//*extra
@Mixin(Render.class)
public abstract class MixinRender<T extends Entity>
{
	@Overwrite
	public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ)
	{
		AxisAlignedBB axisalignedbb = livingEntity.getRenderBoundingBox().grow(0.5D);

		if (axisalignedbb.hasNaN() || axisalignedbb.getAverageEdgeLength() == 0.0D)
		{
			axisalignedbb = new AxisAlignedBB(livingEntity.posX - 2.0D, livingEntity.posY - 2.0D, livingEntity.posZ - 2.0D, livingEntity.posX + 2.0D, livingEntity.posY + 2.0D, livingEntity.posZ + 2.0D);
		}

//        Nali.I.logger.info("livingEntity " + livingEntity);
//        Nali.I.logger.info("isInRangeToRender3d " + livingEntity.isInRangeToRender3d(camX, camY, camZ));
//        Nali.I.logger.info("ignoreFrustumCheck " + livingEntity.ignoreFrustumCheck);
//        Nali.I.logger.info("isBoundingBoxInFrustum " + camera.isBoundingBoxInFrustum(axisalignedbb));
		return /*livingEntity.isInRangeToRender3d(camX, camY, camZ) && (*/livingEntity.ignoreFrustumCheck || camera.isBoundingBoxInFrustum(axisalignedbb)/*)*/;
	}
}


