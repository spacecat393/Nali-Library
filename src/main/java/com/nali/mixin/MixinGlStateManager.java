package com.nali.mixin;

import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.nio.FloatBuffer;

//*extra
//won't work with Nothirium
@Mixin(GlStateManager.class)
public abstract class MixinGlStateManager
{
	@Overwrite
	public static void enableFog()
	{

	}

	@Overwrite
	public static void disableFog()
	{

	}

	@Overwrite
	public static void setFog(GlStateManager.FogMode fogMode)
	{

	}

	@Overwrite
	private static void setFog(int param)
	{

	}

	@Overwrite
	public static void setFogDensity(float param)
	{

	}

	@Overwrite
	public static void setFogStart(float param)
	{

	}

	@Overwrite
	public static void setFogEnd(float param)
	{

	}

	@Overwrite
	public static void glFog(int pname, FloatBuffer param)
	{

	}

	@Overwrite
	public static void glFogi(int pname, int param)
	{

	}
////	@Shadow
////	private static int activeTextureUnit;
////	@Final
////	@Shadow
////	private static Object[] textureState = null;
////
////	private static Field textureName_textureState_field;
//
//	@Overwrite
//	public static void bindTexture(int texture)
//	{
////		if (textureName_textureState_field == null)
////		{
////			Field[] field_array = textureState[activeTextureUnit].getClass().getDeclaredFields();
////			for (Field field : field_array)
////			{
////				field;
////			}
////		}
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
//	}
}
