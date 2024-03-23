//package com.nali.mixin;
//
//import com.nali.render.SkinningRender;
//import net.minecraft.client.renderer.RenderGlobal;
//import net.minecraft.client.renderer.culling.ICamera;
//import net.minecraft.entity.Entity;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static com.nali.draw.ObjectToDraw.OBJECT_ARRAYLIST;
//
//@Mixin(RenderGlobal.class)
//public abstract class MixinRenderGlobal
//{
//    @Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos$PooledMutableBlockPos;release()V", shift = At.Shift.BEFORE))
//    private void renderEntities(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
//    {
//        GL11.glPushMatrix();
//        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//
//        for (Object object : OBJECT_ARRAYLIST)
//        {
////            GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, 1);
//        }
//
//        GL11.glPopMatrix();
//        OBJECT_ARRAYLIST.clear();
//    }
//}
