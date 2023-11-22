package com.nali.mixin;

import com.nali.entities.EntitiesRender;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer
{
//    @Shadow
//    private int rendererUpdateCount;

    @Inject(method = "renderWorld", at = @At("HEAD"))
    private void renderWorld(float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
    {
        EntitiesRender.FREE_FUNCTION.accept(partialTicks);
//        Minecraft minecraft = Minecraft.getMinecraft();
//        RenderManager rendermanager = minecraft.getRenderManager();
//        GameSettings gamesettings = minecraft.gameSettings;
//        EntityPlayerSP player = minecraft.player;
//        float eye_height = player.getEyeHeight();
//        boolean player_sleep = player.isPlayerSleeping();

//        float yaw = PomiMath.rotLerp(partialTicks, player.prevRotationYaw, player.rotationYaw);
//        float pitch = PomiMath.rotLerp(partialTicks, player.prevRotationPitch, player.rotationPitch);
//
//        float hx = 0.0F;
//        float hy = -eye_height + 0.03F;
//        float hz = 0.0F;
//
//        float hrx = 0.0F;
//        float hry = 0.0F;
//        float hrz = 0.0F;

        //make entity render follow view
        //hurt
//        {
//            if (minecraft.getRenderViewEntity() instanceof EntityLivingBase)
//            {
//                EntityLivingBase entitylivingbase = (EntityLivingBase)this.mc.getRenderViewEntity();
//                float f = (float)entitylivingbase.hurtTime - partialTicks;
//
//                if (entitylivingbase.getHealth() <= 0.0F)
//                {
//                    float f1 = (float)entitylivingbase.deathTime + partialTicks;
//                    GlStateManager.rotate(40.0F - 8000.0F / (f1 + 200.0F), 0.0F, 0.0F, 1.0F);
//                }
//
//                if (f < 0.0F)
//                {
//                    return;
//                }
//
//                f = f / (float)entitylivingbase.maxHurtTime;
//                f = MathHelper.sin(f * f * f * f * (float)Math.PI);
//                float f2 = entitylivingbase.attackedAtYaw;
//                GlStateManager.rotate(-f2, 0.0F, 1.0F, 0.0F);
//                GlStateManager.rotate(-f * 14.0F, 0.0F, 0.0F, 1.0F);
//                GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
//            }
//        }

//        if (gamesettings.viewBobbing && minecraft.getRenderViewEntity() instanceof EntityPlayer)
//        {
//            EntityPlayer entityplayer = (EntityPlayer)minecraft.getRenderViewEntity();
//            float f = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
//            float f1 = -(entityplayer.distanceWalkedModified + f * partialTicks);
//            float f2 = (float)Math.toRadians(entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * partialTicks);
//            float f3 = (float)Math.toRadians(-entityplayer.prevCameraPitch + (-entityplayer.cameraPitch - -entityplayer.prevCameraPitch) * partialTicks);
//            hx -= MathHelper.sin(f1 * (float)Math.PI) * f2 * 0.5F;
//            hy += Math.abs(MathHelper.cos(f1 * (float)Math.PI) * f2);
////            hrz += MathHelper.sin(f1 * (float)Math.PI) * f2 * 3.0F;
////            hry -= MathHelper.sin(f1 * (float)Math.PI) * f2 * 3.0F;
//            hrx -= Math.abs(MathHelper.cos(f1 * (float)Math.PI - 0.2F) * f2) * 5.0F;
//            hrx -= f3;
//        }
//
////need project
////        M4x4 m4x4 = new M4x4();
////        float f1 = player.prevTimeInPortal + (player.timeInPortal - player.prevTimeInPortal) * partialTicks;
////
////        if (f1 > 0.0F)
////        {
////            int i = 20;
////
////            if (player.isPotionActive(MobEffects.NAUSEA))
////            {
////                i = 7;
////            }
////
////            float f2 = 5.0F / (f1 * f1 + 5.0F) - f1 * 0.04F;
//////            f2 = f2 * f2;
//////            hry += ((float)this.rendererUpdateCount + partialTicks) * (float)i;
//////            hrz += ((float)this.rendererUpdateCount + partialTicks) * (float)i;
////            float nyz = (float)Math.toRadians(PomiMath.invert(-((float)this.rendererUpdateCount + partialTicks) * (float)i));
////            float yz = (float)Math.toRadians(PomiMath.invert(((float)this.rendererUpdateCount + partialTicks) * (float)i));
////            float s = 1.0F / f2;
//////            M4x4 pts_m4x4 = new M4x4();
//////            pts_m4x4.scale(1.0F + s, 1.0F, 1.0F);
////            M4x4 ts_m4x4 = new M4x4();
////            ts_m4x4.scale(s, 1.0F, 1.0F);
////
////            m4x4.multiply(M4x4.rotate(yz, 0.0F, 1.0F, 1.0F).mat);
////            m4x4.multiply(ts_m4x4.mat);
////            m4x4.multiply(M4x4.rotate(nyz, 0.0F, 1.0F, 1.0F).mat);
////
//////            m4x4.multiply(new Quaternion(0.0F, yz, 0.0F).getM4x4().mat);
//////            m4x4.multiply(ts_m4x4.mat);
//////            m4x4.multiply(new Quaternion(0.0F, nyz, 0.0F).getM4x4().mat);
////
//////            m4x4.multiply(ts_m4x4.mat);
//////            m4x4.multiply(pts_m4x4.mat);
//////
//////            m4x4.multiply(new Quaternion(0.0F, 0.0F, yz).getM4x4().mat);
//////            m4x4.rotateZ(nyz);
//////            m4x4.rotateY(nyz);
//////
//////            m4x4.multiply(ts_m4x4.mat);
////////            GlStateManager.rotate(, 0.0F, 1.0F, 1.0F);
////////            GlStateManager.scale(1.0F / f2, 1.0F, 1.0F);
////////            hry += -((float)this.rendererUpdateCount + partialTicks) * (float)i;
////////            m4x4.rotateZ(yz);
////////            m4x4.rotateY(yz);
//////            m4x4.multiply(new Quaternion(0.0F, 0.0F, nyz).getM4x4().mat);
//////            m4x4.multiply(ts_m4x4.mat);
//////
////////            hrz += -((float)this.rendererUpdateCount + partialTicks) * (float)i;
////////            GlStateManager.rotate(, 0.0F, 1.0F, 1.0F);
////        }
//
//        if (gamesettings.thirdPersonView > 0 && !player_sleep)
//        {
//            double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)partialTicks;
//            double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)partialTicks + (double)eye_height;
//            double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)partialTicks;
//
//            float thirdPersonDistancePrev = 4.0F;
//            double d3 = (double)(thirdPersonDistancePrev + (4.0F - thirdPersonDistancePrev) * partialTicks);
//
//            if (!gamesettings.debugCamEnable)
//            {
//                //                GlStateManager.translate(0.0F, 0.0F, (float)(-d3));
//                //            }
//                //            else
//                //            {
//
//                if (gamesettings.thirdPersonView == 2)
//                {
//                    pitch += 180.0F;
//                }
//
//                double d4 = (double)(-MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F)) * d3;
//                double d5 = (double)(MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F)) * d3;
//                double d6 = (double)(-MathHelper.sin(pitch * 0.017453292F)) * d3;
//
//                for (int i = 0; i < 8; ++i)
//                {
//                    float f3 = (float)((i & 1) * 2 - 1);
//                    float f4 = (float)((i >> 1 & 1) * 2 - 1);
//                    float f5 = (float)((i >> 2 & 1) * 2 - 1);
//                    f3 = f3 * 0.1F;
//                    f4 = f4 * 0.1F;
//                    f5 = f5 * 0.1F;
//                    RayTraceResult raytraceresult = minecraft.world.rayTraceBlocks(new Vec3d(d0 + (double)f3, d1 + (double)f4, d2 + (double)f5), new Vec3d(d0 - d4 + (double)f3 + (double)f5, d1 - d6 + (double)f4, d2 - d5 + (double)f5));
//
//                    if (raytraceresult != null)
//                    {
//                        double d7 = raytraceresult.hitVec.distanceTo(new Vec3d(d0, d1, d2));
//
//                        if (d7 < d3)
//                        {
//                            d3 = d7;
//                        }
//                    }
//                }
//                //
//                //                if (gamesettings.thirdPersonView == 2)
//                //                {
//                //                    GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
//                //                }
//                //
//                //                GlStateManager.rotate(view_entity.rotationPitch - f2, 1.0F, 0.0F, 0.0F);
//                //                GlStateManager.rotate(view_entity.rotationYaw - f1, 0.0F, 1.0F, 0.0F);
//                //                GlStateManager.translate(0.0F, 0.0F, (float)(-d3));
//                //                GlStateManager.rotate(f1 - view_entity.rotationYaw, 0.0F, 1.0F, 0.0F);
//                //                GlStateManager.rotate(f2 - view_entity.rotationPitch, 1.0F, 0.0F, 0.0F);
//            }
//
//            if (gamesettings.thirdPersonView == 2)
//            {
//                hrx += (float)Math.toRadians(-pitch);
//                hry += (float)Math.toRadians(-yaw);
//                hrz += (float)Math.toRadians(180);
//            }
//            else
//            {
//                hrx += (float)Math.toRadians(pitch);
//                hry += (float)Math.toRadians(yaw);
//            }
//
//            hz -= (float)d3;
//        }
//        else
//        {
//            hrx += (float)Math.toRadians(pitch);
//            hry += (float)Math.toRadians(yaw);
//
//            if (player_sleep)
//            {
//                //            d1 += (double)f;
//                hy -= 0.7F;
//            }
//            else
//            {
//                hz += 0.05F;
//            }
//        }

//        m4x4.multiply(new Quaternion(hrx, hry, hrz).getM4x4().mat);
//        WorldMath.VIEW_M4X4 = m4x4; // view
//        WorldMath.VIEW_M4X4 = new Quaternion(hrx, hry, hrz).getM4x4(); // view
//        WorldMath.VIEW_M4X4.translate(hx, hy, hz);
//        WorldMath.VIEW_M4X4.multiply(m4x4.mat);
    }
}
