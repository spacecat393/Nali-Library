//package com.nali.mixin;
//
//import net.minecraft.client.renderer.EntityRenderer;
//import org.spongepowered.asm.mixin.Mixin;
//
//@Mixin(EntityRenderer.class)
//public abstract class MixinEntityRenderer
//{
////    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;F)V", shift = At.Shift.AFTER))
////    private void nali_renderWorldPassE(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////    {
////        RenderO.takeDefault();
////
////        draw(FIRST_MODEL_MAP);
////
////        if (!SECOND_MODEL_MAP.isEmpty())
////        {
////            GL11.glDepthMask(false);
////            draw(SECOND_MODEL_MAP);
////        }
////
////        RenderO.setDefault();
////    }
//
////    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
////    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 2))
////    private void nali_renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////    {
//////        FIRST_MODEL_MAP.clear();
//////        SECOND_MODEL_MAP.clear();
//////        DRAWWORLDDATA_LIST.clear();
//////        DATA_SIZE = 0;
////
////        DrawWorld.run();
//////        Nali.LOGGER.info("END");
////    }
//
////    @Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 3))
////    private void nali_renderWorldPassA(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////    {
////        if (!SECOND_MODEL_MAP.isEmpty())
////        {
////            RenderO.takeDefault();
////
////            GL11.glDepthMask(false);
////            draw(SECOND_MODEL_MAP);
////            SECOND_MODEL_MAP.clear();
////
////            RenderO.setDefault();
////        }
////
////        DRAWWORLDDATA_LIST.clear();
////        DATA_SIZE = 0;
////        FIRST_MODEL_MAP.clear();
////    }
//}
