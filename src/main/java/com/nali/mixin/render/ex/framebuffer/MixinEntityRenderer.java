//package com.nali.mixin.render.ex.framebuffer;
//
//import com.nali.NaliConfig;
//import com.nali.draw.DrawWorld;
//import com.nali.draw.DrawWorldExFb;
//import net.minecraft.client.renderer.EntityRenderer;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static com.nali.draw.DrawWorldExFb.EX_FRAMEBUFFER;
//
//@Mixin(EntityRenderer.class)
//public abstract class MixinEntityRenderer
//{
////	@Shadow private float fogColorRed;
////	@Shadow private float fogColorGreen;
////	@Shadow private float fogColorBlue;
////	private static int
////	GL_COLOR_ATTACHMENT0,
////	GL_DEPTH_ATTACHMENT/*,
////	GL_READ_FRAMEBUFFER_BINDING*/;
////	private static int
////	GL_DRAW_FRAMEBUFFER_BINDING,
////	GL_READ_FRAMEBUFFER_BINDING;
//
//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
//	private void nali_renderWorldPassBL3B(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//	{
//		EX_FRAMEBUFFER = NaliConfig.SHADER.ex_framebuffer;
////		TRANSLUCENT = !SECOND_MODEL_MAP.isEmpty();
//		if (EX_FRAMEBUFFER/* && TRANSLUCENT*/)
//		{
//////			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//////			GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//////			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//////			GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////
////	//		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////	//		R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//////			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//////			GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////
////	//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_MCTB_FRAMEBUFFER_TEXTURE, 0);
////	//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_MCTB_RENDERBUFFER_TEXTURE, 0);
////
////			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
////			GL_COLOR_ATTACHMENT0 = OPENGL_INTBUFFER.get(0);
////			GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
////			GL_DEPTH_ATTACHMENT = OPENGL_INTBUFFER.get(0);
////
////	//		Nali.warn("GL_D " + GL_DEPTH_ATTACHMENT);
////	//		Nali.error("GL_C0 " + GL_COLOR_ATTACHMENT0);
////
////	//		R_GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
////
////	//		R_GL_DEPTH_TEST = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
////	//		GL11.glEnable(GL11.GL_DEPTH_TEST);
////
////	//		GL11.glGetInteger(GL11.GL_DEPTH_WRITEMASK, OPENGL_INTBUFFER);
////	//		R_GL_DEPTH_WRITEMASK = OPENGL_INTBUFFER.get(0);
////	//		//test get fog color
////	//		OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
////	//		GL11.glGetFloat(GL11.GL_COLOR_CLEAR_VALUE, OPENGL_FIXED_PIPE_FLOATBUFFER);
////	//		FR = OPENGL_FIXED_PIPE_FLOATBUFFER.get(0);
////	//		FG = OPENGL_FIXED_PIPE_FLOATBUFFER.get(1);
////	//		FB = OPENGL_FIXED_PIPE_FLOATBUFFER.get(2);
////	//		FA = OPENGL_FIXED_PIPE_FLOATBUFFER.get(3);
////
////			GlStateManager.depthMask(true);
//			DrawWorldExFb.init();
//////			//glColorMask
//////			//glDepthMask
//////			//glStencilMask
//////			//glBlendFunc
//////			//glBlendEquation
//////			//glFramebufferAttachmentParameteri
//////			//glReadBuffer
//////			//glDrawBuffers
//////			//glClearColor glClearDepth glClearStencil
//////
//////			//glClear
//////	//		GL11.glGetBoolean(GL11.GL_COLOR_WRITEMASK, OPENGL_BYTEBUFFER);
//////	//
//////	//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
//////	//		R_GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
//////	//		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
//////	//		R_GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);
//////	//
//////	//		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
//////	//		R_GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
//////	//		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
//////	//		R_GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
//////	//		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
//////	//		R_GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
//////	//		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
//////	//		R_GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);
//////			//
//////
////////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//////	//		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
////////			clear(R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
//////
////////			RenderO.takeDefault();
////////			GL11.glDisable(GL11.GL_DEPTH_TEST);
//////
////////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE, 0);
////////			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT/* | GL11.GL_DEPTH_BUFFER_BIT*/);
////////			draw2dDepth(GL_DEPTH_ATTACHMENT, GL_DEPTH_ATTACHMENT);
////////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//////
//////
////////			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
////////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE, 0);
////////			GL11.glReadPixels(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, GL11.GL_RGBA, GL11.GL_FLOAT, PBO_BYTEBUFFER);
//////			GL11.glReadPixels(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, PBO_BYTEBUFFER);
////////			Nali.LOGGER.info("R " + PBO_BYTEBUFFER.get(0));
////////			Nali.LOGGER.info("G " + PBO_BYTEBUFFER.get(1));
////////			Nali.LOGGER.info("B " + PBO_BYTEBUFFER.get(2));
////////			Nali.LOGGER.info("A " + PBO_BYTEBUFFER.get(3));
//////
////////			ByteBuffer bytebuffer = GL15.glMapBuffer(GL21.GL_PIXEL_PACK_BUFFER, GL15.GL_READ_ONLY, 0, PBO_BYTEBUFFER);
//////
//////			GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////			int gl_texture_binding_2d = OPENGL_INTBUFFER.get(0);
//////
//////			GL11.glBindTexture(GL11.GL_TEXTURE_2D, DEPTH0_TEXTURE);
////////			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, PBO_BYTEBUFFER);
////////			ByteBuffer bytebuffer = ByteBuffer.allocateDirect(1920 * 1080 * 4).order(ByteOrder.nativeOrder());
////////			for (int i = 0; i < 1920*1080*4; ++i)
////////			{
////////				int d = i % 32;
////////				if (d > 23)
////////				{
////////					bytebuffer.put(PBO_BYTEBUFFER.get(i));
////////				}
////////			}
//////			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, PBO_BYTEBUFFER);
////////			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
//////
//////			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d);
//////
////////			GL15.glUnmapBuffer(GL21.GL_PIXEL_PACK_BUFFER);
////////			OpenGlHelper.glBindBuffer(GL21.GL_PIXEL_PACK_BUFFER, 0);
//////
//////
////////			RenderO.setDefault();
////			clear(R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
////////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_MCTB_FRAMEBUFFER_TEXTURE, 0);
////////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, PBO_DEPTH_TEXTURE, 0);
//////
////////			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
////////			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, MC_FRAMEBUFFER);
//////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, DEPTH0_TEXTURE, 0);
//////////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//////////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, MC_FRAMEBUFFER);
////////
////////	//		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, MC_FRAMEBUFFER);
////////	//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
////////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, GL_DRAW_FRAMEBUFFER_BINDING);
//////
//////	//		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//////	//		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//////	//		!GL11.glViewport(0, 0, DISPLAY_WIDTH / 4, DISPLAY_HEIGHT / 4);
//////
//////	//		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_MCTB_FRAMEBUFFER);
//////
//////	//		GL11.glColorMask(true, true, true, true);
//////	//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//////	//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//////
//////	//		GL11.glColorMask(OPENGL_BYTEBUFFER.get(0) != 0, OPENGL_BYTEBUFFER.get(1) != 0, OPENGL_BYTEBUFFER.get(2) != 0, OPENGL_BYTEBUFFER.get(3) != 0);
//////
//////	//		GL20.glBlendEquationSeparate(R_GL_BLEND_EQUATION_RGB, R_GL_BLEND_EQUATION_ALPHA);
//////	//		GL14.glBlendFuncSeparate(R_GL_BLEND_SRC_RGB, R_GL_BLEND_DST_RGB, R_GL_BLEND_SRC_ALPHA, R_GL_BLEND_DST_ALPHA);
//////	//		GL11.glClearColor(FR, FG, FB, FA);
//////	//		GL11.glDepthMask(true);
//////	//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//////	//		GL11.glDisable(GL11.GL_CULL_FACE);
//		}
//		else
//		{
//			DrawWorld.run();
//		}
//	}
//
//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.AFTER, ordinal = 3))
//	private void nali_renderWorldPassBL3A(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//	{
////		if (EX_FRAMEBUFFER/* && TRANSLUCENT*/)
////		{
//////		if (R_GL_CULL_FACE)
//////		{
//////			GL11.glEnable(GL11.GL_CULL_FACE);
//////		}
//////		else
//////		{
//////			GL11.glDisable(GL11.GL_CULL_FACE);
//////		}
////
//////		if (R_GL_DEPTH_TEST)
//////		{
//////			GL11.glEnable(GL11.GL_DEPTH_TEST);
//////		}
//////		else
//////		{
//////			GL11.glDisable(GL11.GL_DEPTH_TEST);
//////		}
////
//////		if (R_GL_DEPTH_WRITEMASK == 1)
//////		{
//////			GL11.glDepthMask(true);
//////		}
//////		else
//////		{
//////			GL11.glDepthMask(false);
//////		}
////
//////		GL20.glBlendEquationSeparate(R_GL_BLEND_EQUATION_RGB, R_GL_BLEND_EQUATION_ALPHA);
//////		GL14.glBlendFuncSeparate(R_GL_BLEND_SRC_RGB, R_GL_BLEND_DST_RGB, R_GL_BLEND_SRC_ALPHA, R_GL_BLEND_DST_ALPHA);
//////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
////
//////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, GL_READ_FRAMEBUFFER_BINDING);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////			GlStateManager.depthMask(false);
////
//////			RenderO.takeDefault();
//////			GL11.glDisable(GL11.GL_DEPTH_TEST);
//////
//////			OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//////			GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////			int gl_texture_binding_2d_2 = OPENGL_INTBUFFER.get(0);
//////			int gl_texture_wrap_s_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//////			int gl_texture_wrap_t_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//////			int gl_texture_min_filter_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////			int gl_texture_mag_filter_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////
//////			draw2dLite(GL_DEPTH_ATTACHMENT, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
//////
//////			OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//////			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_2);
//////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, gl_texture_wrap_s_2);
//////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, gl_texture_wrap_t_2);
//////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_2);
//////			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_2);
//////
//////			RenderO.setDefault();
////
//////		!GL11.glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
////
//////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//////
//////		GL30.glBlitFramebuffer
//////		(
//////			0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//////			0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//////			GL11.GL_DEPTH_BUFFER_BIT,
//////			GL11.GL_NEAREST
//////		);
//////
//////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
////
//////		//
//////		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
//////		R_GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);
//////		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
//////		R_GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//////		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
//////		R_GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////		R_GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////		R_GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
//////		R_GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//////		R_GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//////		R_GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////		R_GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////		R_GL_TEXTURE_BINDING_2D_1 = OPENGL_INTBUFFER.get(0);
//////		R_GL_TEXTURE_WRAP_S_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//////		R_GL_TEXTURE_WRAP_T_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//////		R_GL_TEXTURE_MIN_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////		R_GL_TEXTURE_MAG_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////		R_GL_TEXTURE_BINDING_2D_2 = OPENGL_INTBUFFER.get(0);
//////		R_GL_TEXTURE_WRAP_S_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//////		R_GL_TEXTURE_WRAP_T_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//////		R_GL_TEXTURE_MIN_FILTER_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////		R_GL_TEXTURE_MAG_FILTER_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
//////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//////		R_GL_TEXTURE_BINDING_2D_3 = OPENGL_INTBUFFER.get(0);
//////		R_GL_TEXTURE_WRAP_S_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//////		R_GL_TEXTURE_WRAP_T_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//////		R_GL_TEXTURE_MIN_FILTER_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//////		R_GL_TEXTURE_MAG_FILTER_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//////
////////		draw2dMix(Minecraft.getMinecraft().getFramebuffer().framebufferTexture, R_MC_RENDERBUFFER_TEXTURE, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
//////
//////		//
//////		OpenGlHelper.glUseProgram(R_GL_CURRENT_PROGRAM);
//////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, R_GL_ARRAY_BUFFER_BINDING);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_0);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_0);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_1);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_1);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_1);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_1);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_1);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_2);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_2);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_2);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_2);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_2);
//////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_3);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_3);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_3);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_3);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_3);
//////		OpenGlHelper.setActiveTexture(R_GL_ACTIVE_TEXTURE);
//////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D);
////		}
//	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
//	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 19))
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 16))
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;depthMask(Z)V", shift = At.Shift.BEFORE, ordinal = 3))
////@Inject(method = "renderWorldPass", at = @At(value = "HEAD"))
////	@Inject(method = "renderWorldPass", at = @At(value = "TAIL"))
//	private void nali_renderWorldPassSS19(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
//	{
//		if (EX_FRAMEBUFFER)
//		{
////		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//
////		GL30.glBlitFramebuffer
////		(
////			0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
////			0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
////			GL11.GL_DEPTH_BUFFER_BIT,
////			GL11.GL_NEAREST
////		);
//
////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
////		DrawWorld.init();
////		DrawWorld.run();
//////		DrawWorld.drawFirst();
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 19))
////	private void nali_renderWorldPassSS20(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////
////		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_MCH_FRAMEBUFFER);
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "TAIL"))
////	private void nali_renderWorldPassTail(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
////		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
////		R_GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);
////		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
////		R_GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
////		R_GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////		R_GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////		R_GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
////		R_GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////		R_GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
////		R_GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		R_GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////		R_GL_TEXTURE_BINDING_2D_1 = OPENGL_INTBUFFER.get(0);
////		R_GL_TEXTURE_WRAP_S_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////		R_GL_TEXTURE_WRAP_T_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
////		R_GL_TEXTURE_MIN_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		R_GL_TEXTURE_MAG_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////
////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC2_FRAMEBUFFER);
////
////		GL30.glBlitFramebuffer
////		(
////			0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
////			0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
////			GL11.GL_DEPTH_BUFFER_BIT,
////			GL11.GL_NEAREST
////		);
////
////		draw2dDepth(R_MC2_RENDERBUFFER_TEXTURE, R_MC_RENDERBUFFER_TEXTURE);
////
////		OpenGlHelper.glUseProgram(R_GL_CURRENT_PROGRAM);
////		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, R_GL_ARRAY_BUFFER_BINDING);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_0);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_0);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_1);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_1);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_1);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_1);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_1);
////		OpenGlHelper.setActiveTexture(R_GL_ACTIVE_TEXTURE);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D);
//
////		OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
////		OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//			DrawWorldExFb.run();
//		}
//	}
//
////	//bypass and draw later
////	@Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.AFTER))
//////	@Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", shift = At.Shift.AFTER, ordinal = 1))
//////	@Inject(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;bindFramebuffer(Z)V", shift = At.Shift.AFTER))
////	private void nali_updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo callbackinfo)
////	{
////		DrawWorld.run();
////	}
//
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V", shift = At.Shift.BEFORE, ordinal = 19))
////	private void nali_renderWorldPass19(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
////		DrawWorld.run();
////	}
//
////	@Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V", shift = At.Shift.BEFORE))
////	private void renderWorld(float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	@Inject(method = "renderWorldPass", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;renderBlockLayer(Lnet/minecraft/util/BlockRenderLayer;DILnet/minecraft/entity/Entity;)I", shift = At.Shift.BEFORE, ordinal = 3))
////	private void renderSWorldPass(int pass, float partialTicks, long finishTimeNano, CallbackInfo callbackinfo)
////	{
//////		DrawWorld.run();
////		DrawWorld.drawSecond();
////	}
//
////	@Inject(method = "updateFogColor", at = @At(value = "TAIL"))
////	private void nali_updateFogColor(float partialTicks, CallbackInfo ci)
////	{
//////		if (EX_FRAMEBUFFER)
//////		{
////		R = this.fogColorRed;
////		G = this.fogColorGreen;
////		B = this.fogColorBlue;
//////		}
////	}
//}
