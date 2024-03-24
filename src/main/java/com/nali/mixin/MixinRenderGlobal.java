//package com.nali.mixin;
//
//import com.nali.render.ObjectRender;
//import net.minecraft.client.renderer.RenderGlobal;
//import net.minecraft.client.renderer.culling.ICamera;
//import net.minecraft.entity.Entity;
//import org.lwjgl.opengl.GL11;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static com.nali.draw.ObjectToDraw.DOUBLE_ARRAYLIST;
//import static com.nali.draw.ObjectToDraw.OBJECT_ARRAYLIST;
//
//@Mixin(RenderGlobal.class)
//public abstract class MixinRenderGlobal
//{
////    @Inject(method = "renderEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos$PooledMutableBlockPos;release()V", shift = At.Shift.BEFORE))
//    @Inject(method = "renderEntities", at = @At(value = "HEAD"))
//    private void renderEntities(Entity renderViewEntity, ICamera camera, float partialTicks, CallbackInfo ci)
//    {
//        for (int i = 0; i < OBJECT_ARRAYLIST.size(); ++i)
//        {
//            ObjectRender objectrender = (ObjectRender)OBJECT_ARRAYLIST.get(i);
//            GL11.glPushMatrix();
//
//            int next = i * 3;
//            float s = objectrender.entitiesrendermemory.scale;
//            GL11.glTranslated(DOUBLE_ARRAYLIST.get(next), DOUBLE_ARRAYLIST.get(next + 1), DOUBLE_ARRAYLIST.get(next + 2));
//            GL11.glScalef(s, s, s);
//            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//
//            for (int l = 0; l < objectrender.memory_object_array.length - 1; ++l)
//            {
//                if ((objectrender.model_byte_array[l / 8] >> l % 8 & 1) == 1)
//                {
//                    objectrender.objectworlddraw.drawWorld(l);
//                }
//            }
//
//            GL11.glPopMatrix();
////            GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, 1);
//        }
//
//        OBJECT_ARRAYLIST.clear();
//    }
//}
