//package com.nali.mixin;
//
//import net.minecraft.client.renderer.GlStateManager;
//import org.lwjgl.opengl.OpenGlHelper;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//import java.lang.reflect.Field;
//
//@Mixin(GlStateManager.class)
//public abstract class MixinGlStateManager
//{
//    @Shadow
//    private static int activeTextureUnit;
//    @Final
//    @Shadow
//    private static Object[] textureState = null;
//
//    private static Field textureName_textureState_field;
//
//    @Overwrite
//    public static void bindTexture(int texture)
//    {
////        if (textureName_textureState_field == null)
////        {
////            Field[] field_array = textureState[activeTextureUnit].getClass().getDeclaredFields();
////            for (Field field : field_array)
////            {
////                field;
////            }
////        }
//
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//    }
//}
