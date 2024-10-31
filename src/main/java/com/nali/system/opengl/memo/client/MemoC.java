//package com.nali.system.opengl.memo.client;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class MemoC
//{
////	private void renderEntity(float x, float y, Minecraft minecraft, EntityEntry entityEntry)
////	{
////		Entity entity = entityEntry.newInstance(minecraft.player.world);
////		Render<Entity> render = minecraft.getRenderManager().getEntityRenderObject(entity);
////
////		intBuffer.clear();
////		floatBuffer.clear();
////
////		// color
////		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, floatBuffer);
////		float colorR = floatBuffer.get(0);
////		float colorG = floatBuffer.get(1);
////		float colorB = floatBuffer.get(2);
////		float colorA = floatBuffer.get(3);
////
////		// blendState
////		boolean blendState = GL11.glIsEnabled(GL11.GL_BLEND);
////
////		// depthTest
////		boolean depthTest = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
////
////		// cullFace
////		boolean cullFace = GL11.glIsEnabled(GL11.GL_CULL_FACE);
////
////		// light0
////		boolean light0 = GL11.glIsEnabled(GL11.GL_LIGHT0);
////
////		// light1
////		boolean light1 = GL11.glIsEnabled(GL11.GL_LIGHT1);
////
////		// colorMat
////		boolean colorMat = GL11.glIsEnabled(GL11.GL_COLOR_MATERIAL);
////
////		boolean alphaTest = GL11.glIsEnabled(GL11.GL_ALPHA_TEST);
////		boolean gl_rescale_normal = GL11.glIsEnabled(GL12.GL_RESCALE_NORMAL);
////		boolean gl_lighting = GL11.glIsEnabled(GL11.GL_LIGHTING);
////
////		// colorMatFace & colorMatMode
////		GL11.glGetInteger(GL11.GL_COLOR_MATERIAL_FACE, intBuffer);
////		int colorMatFace = intBuffer.get(0);
////		GL11.glGetInteger(GL11.GL_COLOR_MATERIAL_PARAMETER, intBuffer);
////		int colorMatMode = intBuffer.get(0);
////
////		// depthWriteMask
////		GL11.glGetInteger(GL11.GL_DEPTH_WRITEMASK, intBuffer);
////		int depthWriteMask = intBuffer.get(0);
////
////		// shadeModel
////		GL11.glGetInteger(GL11.GL_SHADE_MODEL, intBuffer);
////		int shadeModel = intBuffer.get(0);
////
////		// lightModelAmbient
////		GL11.glGetFloat(GL11.GL_LIGHT_MODEL_AMBIENT, floatBuffer);
////		float lightModelAmbientR = floatBuffer.get(0);
////		float lightModelAmbientG = floatBuffer.get(1);
////		float lightModelAmbientB = floatBuffer.get(2);
////		float lightModelAmbientA = floatBuffer.get(3);
////
////		//s-l
////		float[] light00InfoRs = new float[4];
////		float[] light00InfoGs = new float[4];
////		float[] light00InfoBs = new float[4];
////		float[] light00InfoAs = new float[4];
////		GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_SPOT_DIRECTION, floatBuffer);
////		light00InfoRs[0] = floatBuffer.get(0);
////		light00InfoGs[0] = floatBuffer.get(1);
////		light00InfoBs[0] = floatBuffer.get(2);
////		light00InfoAs[0] = floatBuffer.get(3);
////		GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, floatBuffer);
////		light00InfoRs[1] = floatBuffer.get(0);
////		light00InfoGs[1] = floatBuffer.get(1);
////		light00InfoBs[1] = floatBuffer.get(2);
////		light00InfoAs[1] = floatBuffer.get(3);
////		GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, floatBuffer);
////		light00InfoRs[2] = floatBuffer.get(0);
////		light00InfoGs[2] = floatBuffer.get(1);
////		light00InfoBs[2] = floatBuffer.get(2);
////		light00InfoAs[2] = floatBuffer.get(3);
////		GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, floatBuffer);
////		light00InfoRs[3] = floatBuffer.get(0);
////		light00InfoGs[3] = floatBuffer.get(1);
////		light00InfoBs[3] = floatBuffer.get(2);
////		light00InfoAs[3] = floatBuffer.get(3);
////
////		float[] light01InfoRs = new float[4];
////		float[] light01InfoGs = new float[4];
////		float[] light01InfoBs = new float[4];
////		float[] light01InfoAs = new float[4];
////		GL11.glGetLight(GL11.GL_LIGHT1, GL11.GL_SPOT_DIRECTION, floatBuffer);
////		light01InfoRs[0] = floatBuffer.get(0);
////		light01InfoGs[0] = floatBuffer.get(1);
////		light01InfoBs[0] = floatBuffer.get(2);
////		light01InfoAs[0] = floatBuffer.get(3);
////		GL11.glGetLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, floatBuffer);
////		light01InfoRs[1] = floatBuffer.get(0);
////		light01InfoGs[1] = floatBuffer.get(1);
////		light01InfoBs[1] = floatBuffer.get(2);
////		light01InfoAs[1] = floatBuffer.get(3);
////		GL11.glGetLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, floatBuffer);
////		light01InfoRs[2] = floatBuffer.get(0);
////		light01InfoGs[2] = floatBuffer.get(1);
////		light01InfoBs[2] = floatBuffer.get(2);
////		light01InfoAs[2] = floatBuffer.get(3);
////		GL11.glGetLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, floatBuffer);
////		light01InfoRs[3] = floatBuffer.get(0);
////		light01InfoGs[3] = floatBuffer.get(1);
////		light01InfoBs[3] = floatBuffer.get(2);
////		light01InfoAs[3] = floatBuffer.get(3);
////		//e-l
////
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, intBuffer);
////		int R_GL_TEXTURE_BINDING_2D = intBuffer.get(0);
////		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, intBuffer);
////		int R_GL_ACTIVE_TEXTURE = intBuffer.get(0);
////
////		GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, intBuffer);
////		int R_GL_TEXTURE_BINDING_2D_0 = intBuffer.get(0);
////		int R_GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////		int R_GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
////		int R_GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		int R_GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////
////		//s-w0
////		boolean texture0 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
////
//////		GL11.glGetTexEnv(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_COLOR, );
////		int gl_texture_env_mode0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE);
////		int gl_combine_rgb0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB);
////		int gl_combine_alpha0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA);
////		int gl_source0_rgb0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB);
////		int gl_source0_alpha0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA);
////		int gl_operand0_rgb0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB);
////		int gl_operand0_alpha0 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA);
////		//e-w0
////
////		GlStateManager.setActiveTexture(GL13.GL_TEXTURE1);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, intBuffer);
////		int R_GL_TEXTURE_BINDING_2D_1 = intBuffer.get(0);
////		int R_GL_TEXTURE_WRAP_S_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////		int R_GL_TEXTURE_WRAP_T_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
////		int R_GL_TEXTURE_MIN_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		int R_GL_TEXTURE_MAG_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////
////		//s-w1
////		boolean texture1 = GL11.glIsEnabled(GL11.GL_TEXTURE_2D);
////
//////		GL11.glGetTexEnv(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_COLOR, );
////		int gl_texture_env_mode1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE);
////		int gl_combine_rgb1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB);
////		int gl_combine_alpha1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA);
////		int gl_source0_rgb1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB);
////		int gl_source0_alpha1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA);
////		int gl_operand0_rgb1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB);
////		int gl_operand0_alpha1 = GL11.glGetTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA);
////		//e-w1
////
////		//<editor-fold desc="rendering">
////		GlStateManager.pushMatrix();
////		GlStateManager.translate(x, y, 10);
////		GlStateManager.scale(-10f, 10f, 10f);
////		GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
////
////		GlStateManager.setActiveTexture(R_GL_ACTIVE_TEXTURE);
//////		GlStateManager.bindTexture(R_GL_TEXTURE_BINDING_2D);
////
////		render.doRender(entity, 0, 0, 0, 0, 0);
////
////		GlStateManager.popMatrix();
////		//</editor-fold>
////
////		// free alphaTest
////		if (alphaTest)
////			GlStateManager.enableAlpha();
////		else
////			GlStateManager.disableAlpha();
////
////		if (gl_rescale_normal)
////			GlStateManager.enableRescaleNormal();
////		else
////			GlStateManager.disableRescaleNormal();
////
////		if (gl_lighting)
////			GlStateManager.enableLighting();
////		else
////			GlStateManager.disableLighting();
////
////		//s-ll
////		setFloatBuffer4(floatBuffer, light01InfoRs[0], light01InfoGs[0], light01InfoBs[0], light01InfoAs[0]);
////		GlStateManager.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, floatBuffer);
////		setFloatBuffer4(floatBuffer, light01InfoRs[1], light01InfoGs[1], light01InfoBs[1], light01InfoAs[1]);
////		GlStateManager.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, floatBuffer);
////		setFloatBuffer4(floatBuffer, light01InfoRs[2], light01InfoGs[2], light01InfoBs[2], light01InfoAs[2]);
////		GlStateManager.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, floatBuffer);
////		setFloatBuffer4(floatBuffer, light01InfoRs[3], light01InfoGs[3], light01InfoBs[3], light01InfoAs[3]);
////		GlStateManager.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, floatBuffer);
////
////		setFloatBuffer4(floatBuffer, light00InfoRs[0], light00InfoGs[0], light00InfoBs[0], light00InfoAs[0]);
////		GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, floatBuffer);
////		setFloatBuffer4(floatBuffer, light00InfoRs[1], light00InfoGs[1], light00InfoBs[1], light00InfoAs[1]);
////		GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, floatBuffer);
////		setFloatBuffer4(floatBuffer, light00InfoRs[2], light00InfoGs[2], light00InfoBs[2], light00InfoAs[2]);
////		GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, floatBuffer);
////		setFloatBuffer4(floatBuffer, light00InfoRs[3], light00InfoGs[3], light00InfoBs[3], light00InfoAs[3]);
////		GlStateManager.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, floatBuffer);
////		//e-ll
////
////		GlStateManager.setActiveTexture(GL13.GL_TEXTURE0);
////		GlStateManager.bindTexture(R_GL_TEXTURE_BINDING_2D_0);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_0);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_0);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_0);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_0);
////
////		//s-r0
////		if (texture0)
////			GlStateManager.enableTexture2D();
////		else
////			GlStateManager.disableTexture2D();
////
//////		GlStateManager.glTexEnv(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_COLOR, gl_texture_env_color0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, gl_texture_env_mode0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, gl_combine_rgb0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, gl_combine_alpha0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, gl_source0_rgb0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA, gl_source0_alpha0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB, gl_operand0_rgb0);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA, gl_operand0_alpha0);
////		//s-r0
////
////		GlStateManager.setActiveTexture(GL13.GL_TEXTURE1);
////		GlStateManager.bindTexture(R_GL_TEXTURE_BINDING_2D_1);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_1);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_1);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_1);
////		GlStateManager.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_1);
////
////		//s-r1
////		if (texture1)
////			GlStateManager.enableTexture2D();
////		else
////			GlStateManager.disableTexture2D();
////
//////		GlStateManager.glTexEnv(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_COLOR, gl_texture_env_color1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, gl_texture_env_mode1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_RGB, gl_combine_rgb1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_COMBINE_ALPHA, gl_combine_alpha1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_RGB, gl_source0_rgb1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_SOURCE0_ALPHA, gl_source0_alpha1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_RGB, gl_operand0_rgb1);
////		GlStateManager.glTexEnvi(GL11.GL_TEXTURE_ENV, OpenGlHelper.GL_OPERAND0_ALPHA, gl_operand0_alpha1);
////		//s-r1
////
////		GlStateManager.setActiveTexture(R_GL_ACTIVE_TEXTURE);
////		GlStateManager.bindTexture(R_GL_TEXTURE_BINDING_2D);
////
////		// free lightModelAmbient
////		setFloatBuffer4(floatBuffer, lightModelAmbientR, lightModelAmbientG, lightModelAmbientB, lightModelAmbientA);
////		GlStateManager.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, floatBuffer);
////
////		// free shadeModel
////		GlStateManager.shadeModel(shadeModel);
////
////		// free depthWriteMask
////		GlStateManager.depthMask(depthWriteMask == 1);
////
////		// free colorMatFace & colorMatMode
////		GlStateManager.colorMaterial(colorMatFace, colorMatMode);
////
////		// free colorMat
////		if (colorMat)
////			GlStateManager.enableColorMaterial();
////		else
////			GlStateManager.disableColorMaterial();
////
////		// free light1
////		if (light1)
////			GlStateManager.enableLight(1);
////		else
////			GlStateManager.disableLight(1);
////
////		// free light0
////		if (light0)
////			GlStateManager.enableLight(0);
////		else
////			GlStateManager.disableLight(0);
////
////		// free cullFace
////		if (cullFace)
////			GlStateManager.enableCull();
////		else
////			GlStateManager.disableCull();
////
////		// free depthTest
////		if (depthTest)
////			GlStateManager.enableDepth();
////		else
////			GlStateManager.disableDepth();
////
////		// free blendState
////		if (blendState)
////			GlStateManager.enableBlend();
////		else
////			GlStateManager.disableBlend();
////
////		// free color
////		GlStateManager.color(colorR, colorG, colorB, colorA);
////	}
//
////	private void setFloatBuffer4(FloatBuffer floatBuffer, float... floats)
////	{
////		floatBuffer.clear();
////		floatBuffer.put(floats);
////		floatBuffer.flip();
////	}
//}
