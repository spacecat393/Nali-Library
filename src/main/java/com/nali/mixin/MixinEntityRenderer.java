package com.nali.mixin;

import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//*extra
@Mixin(EntityRenderer.class)
public abstract class MixinEntityRenderer
{
	@Shadow
	private float farPlaneDistance;

	//won't work with Nothirium
	@Overwrite
	private void setupFog(int startCoords, float partialTicks)
	{

	}

	@Overwrite
	private void hurtCameraEffect(float partialTicks)
	{

	}

	@Overwrite
	private float getFOVModifier(float partialTicks, boolean useFOVSetting)
	{
		return 90.0F;
	}

	@Redirect(method = "setupCameraTransform", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;farPlaneDistance:F", ordinal = 0))
	private void nali_setupCameraTransform(EntityRenderer instance, float value)
	{
		this.farPlaneDistance = 64 * 16;
//		this.farPlaneDistance = 64 * 64 * 64 * 64 * 64;
	}
//	//	private static BoxImage BOXIMAGE;
//	private static RenderSky RENDERSKY = new RenderSky();
//
//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 6))
//	private void nali_renderWorldPassE(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//	{
//		GL11.glDisable(GL11.GL_FOG);
//
////take draw free
//		RenderO.take();
//		GL11.glDepthMask(false);
//		GL11.glPushMatrix();
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.4F);
//		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
////		Minecraft minecraft = Minecraft.getMinecraft();
////		EntityPlayerSP entityplayersp = minecraft.player;
////		GL11.glTranslated(0.0D, (entityplayersp.prevPosY + (entityplayersp.posY - entityplayersp.prevPosY) * minecraft.getRenderPartialTicks()), 0.0D);
//		GL11.glScalef(200.0F, -200.0F, 200.0F);
////		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		RENDERSKY.draw(BothDaSky.IDA);
//		GL11.glPopMatrix();
////		boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
////		boolean gl_depth_test = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
////		RenderO.FLOATBUFFER.limit(16);
////		GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF, RenderO.FLOATBUFFER);
////		float gl_alpha_test_ref = RenderO.FLOATBUFFER.get(0);
////		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);
//////		boolean gl_alpha_test = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
////
//////		GL11.glDisable(GL11.GL_ALPHA_TEST);
////		GL11.glEnable(GL11.GL_DEPTH_TEST);
////		GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK, RenderO.BYTEBUFFER);
////		int gl_depth_writemask = RenderO.BYTEBUFFER.get(0);
////		GL11.glDepthMask(false);
////
////		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, RenderO.INTBUFFER);
////		int gl_blend_equation_rgb = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, RenderO.INTBUFFER);
////		int gl_blend_equation_alpha = RenderO.INTBUFFER.get(0);
////
////		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, RenderO.INTBUFFER);
////		int gl_blend_src_rgb = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, RenderO.INTBUFFER);
////		int gl_blend_src_alpha = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, RenderO.INTBUFFER);
////		int gl_blend_dst_rgb = RenderO.INTBUFFER.get(0);
////		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, RenderO.INTBUFFER);
////		int gl_blend_dst_alpha = RenderO.INTBUFFER.get(0);
////
////		GL11.glEnable(GL11.GL_BLEND);
////		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
////		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
////
////
//////		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
//////		int gl_current_program = RenderO.INTBUFFER.get(0);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
//////		int gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
//////		int gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////		int gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
//////		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
//////
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP + 1));
////////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
////////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//////
////////		if (BOXIMAGE == null)
////////		{
////////			int width = Display.getWidth();
////////			int height = Display.getHeight();
////////			BOXIMAGE = new BoxImage();
////////			BOXIMAGE.x0 = 0;
////////			BOXIMAGE.y0 = 0;
////////			BOXIMAGE.x1 = width;
////////			BOXIMAGE.y1 = height;
////////
////////			BOXIMAGE.u0 = 0;
////////			BOXIMAGE.v0 = 0;
////////			BOXIMAGE.u1 = 1920;
////////			BOXIMAGE.v1 = 1080;
////////
////////			BOXIMAGE.v_width = width;
////////			BOXIMAGE.v_height = height;
////////			BOXIMAGE.t_width = 1920;
////////			BOXIMAGE.t_height = 1080;
////////			BOXIMAGE.gen();
////////		}
//////
//////		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
//////		OpenGlHelper.glUseProgram(rs.program);
//////		int v = rs.attriblocation_int_array[0];
//////		GL20.glEnableVertexAttribArray(v);
//////
////////		BOXIMAGE.draw(rs, new float[2], new float[]{1.0F, 1.0F, 1.0F, 0.1F});
//////
//////		GL20.glDisableVertexAttribArray(v);
//////
//////		OpenGlHelper.glUseProgram(gl_current_program);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_0);
//////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//
//
////		GL11.glAlphaFunc(GL11.GL_GREATER, gl_alpha_test_ref);
//////		if (gl_alpha_test)
//////		{
//////			GL11.glEnable(GL11.GL_ALPHA_TEST);
//////		}
//////		else
//////		{
//////			GL11.glDisable(GL11.GL_ALPHA_TEST);
//////		}
////		if (gl_blend)
////		{
////			GL11.glEnable(GL11.GL_BLEND);
////		}
////		else
////		{
////			GL11.glDisable(GL11.GL_BLEND);
////		}
////		GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
////		GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);
////
////		GL11.glDepthMask(gl_depth_writemask == 1);
////		if (gl_depth_test)
////		{
////			GL11.glEnable(GL11.GL_DEPTH_TEST);
////		}
////		else
////		{
////			GL11.glDisable(GL11.GL_DEPTH_TEST);
////		}
//
////		ci.cancel();
//		RenderO.free();
//	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderEntities(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;F)V", shift = At.Shift.AFTER))
////	private void nali_renderWorldPassE(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		RenderO.take();
////
////		draw(FIRST_MODEL_MAP);
////
////		if (!SECOND_MODEL_MAP.isEmpty())
////		{
////			GL11.glDepthMask(false);
////			draw(SECOND_MODEL_MAP);
////		}
////
////		RenderO.free();
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 2))
////	private void nali_renderWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
//////		FIRST_MODEL_MAP.clear();
//////		SECOND_MODEL_MAP.clear();
//////		DRAWWORLDDATA_LIST.clear();
//////		DATA_SIZE = 0;
////
////		DrawWorld.run();
//////		Nali.LOGGER.info("END");
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 3))
////	private void nali_renderWorldPassA(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		if (!SECOND_MODEL_MAP.isEmpty())
////		{
////			RenderO.take();
////
////			GL11.glDepthMask(false);
////			draw(SECOND_MODEL_MAP);
////			SECOND_MODEL_MAP.clear();
////
////			RenderO.free();
////		}
////
////		DRAWWORLDDATA_LIST.clear();
////		DATA_SIZE = 0;
////		FIRST_MODEL_MAP.clear();
////	}
}
