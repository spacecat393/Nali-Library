package com.nali.mixin;

import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.gui.key.Key;
import com.nali.gui.page.Page;
import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.sound.Sound;
import com.nali.system.ClientLoader;
import com.nali.system.Reflect;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MouseHelper;
import org.lwjgl.MemoryUtil;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.HashSet;

import static com.nali.Nali.warn;
import static com.nali.key.Key.KEY_ARRAY;
import static com.nali.sound.Sound.SOUND_SET;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
	@Shadow public MouseHelper mouseHelper;

	@Shadow @Nullable public GuiScreen currentScreen;

	@Shadow public abstract void displayGuiScreen(@Nullable GuiScreen guiScreenIn);

	@Inject(method = "drawSplashScreen", at = @At(value = "HEAD"), cancellable = true)
	private void nali_drawSplashScreen(TextureManager textureManagerInstance, CallbackInfo ci)
	{
		if ((NaliConfig.STATE & 32) == 32)
		{
			ci.cancel();
		}
	}

	@Inject(method = "runGameLoop", at = @At(value = "HEAD"))
	private void nali_runGameLoop(CallbackInfo callbackinfo)
	{
//		Timing.count();

		EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
		if ((NaliConfig.STATE & 8) == 8 && entityplayersp != null)
		{
			NaliAL.alListener3f(AL10.AL_POSITION, (float)entityplayersp.posX, (float)entityplayersp.posY, (float)entityplayersp.posZ);

			float ry = (float) Math.toRadians(entityplayersp.rotationYaw);
//			float rp = (float) Math.toRadians(entityplayersp.rotationPitch);
//			float rp = 0;

//			float sry = (float)Math.sin(ry);
//			float cry = (float)Math.cos(ry);

//			float srp = (float)Math.sin(rp);
//			float crp = (float)Math.cos(rp);
//			float crp = 1;
			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(new float[]
			{
//				-sry * crp,
//				-srp,
//				cry * crp,
//				sry * srp,
//				crp,
//				-cry * srp
				-(float)Math.sin(ry)/* * crp*/,
				0,
				(float)Math.cos(ry)/* * crp*/,
				0,
				1/*crp*/,
				0
			});
			RenderO.FLOATBUFFER.flip();
			NaliAL.alListenerfv(AL10.AL_ORIENTATION, MemoryUtil.getAddress(RenderO.FLOATBUFFER));
		}
//		else
//		{
//			NaliAL.alListenerfv(AL10.AL_ORIENTATION, MemoryUtil.getAddress(RenderO.FLOATBUFFER));
//
//			NaliAL.alListener3f(AL10.AL_POSITION, 0, 0, 0);
//		}

		for (Sound sound : new HashSet<>(SOUND_SET))
		{
			sound.loop();
		}

		if (Key.KEY == null)
		{
			for (com.nali.key.Key key : KEY_ARRAY)
			{
//			Key key = KEY_ARRAY[i];
//			int key_code = key.getKeyCode();
//			if ((key_code < 0 && Mouse.isButtonDown(key_code + 100)) || (key_code > 0 && Keyboard.isKeyDown(key_code)))
//			{
//			key.run();
//			}
				key.run();
			}
		}
		else
		{
			//add mouse
			KeyBinding.unPressAllKeys();
			this.mouseHelper.ungrabMouseCursor();
			Key.KEY.run();
		}
	}

	@Inject(method = "updateDisplay", at = @At(value = "HEAD"))
	private void nali_updateDisplay(CallbackInfo callbackinfo)
	{
		if (Page.PAGE != null)
		{
			if (this.currentScreen != null)
			{
				this.displayGuiScreen(null);
			}

			boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
			GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, RenderO.INTBUFFER);
			int gl_blend_equation_rgb = RenderO.INTBUFFER.get(0);
			GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, RenderO.INTBUFFER);
			int gl_blend_equation_alpha = RenderO.INTBUFFER.get(0);

			GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, RenderO.INTBUFFER);
			int gl_blend_src_rgb = RenderO.INTBUFFER.get(0);
			GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, RenderO.INTBUFFER);
			int gl_blend_src_alpha = RenderO.INTBUFFER.get(0);
			GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, RenderO.INTBUFFER);
			int gl_blend_dst_rgb = RenderO.INTBUFFER.get(0);
			GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, RenderO.INTBUFFER);
			int gl_blend_dst_alpha = RenderO.INTBUFFER.get(0);

			GL11.glEnable(GL11.GL_BLEND);
			GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
			GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

			//s0-drawColor

			//s1-take
			GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
			int gl_current_program = RenderO.INTBUFFER.get(0);
			GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
			int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
			//e1-take

			MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP + 1);
			OpenGlHelper.glUseProgram(rs.program);
			int v = rs.attriblocation_int_array[0];
			GL20.glEnableVertexAttribArray(v);
			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, Page.QUAD2D_ARRAY_BUFFER);
			GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 2, GL11.GL_FLOAT, false, 0, 0);

			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(new float[2]);
			RenderO.FLOATBUFFER.flip();
			OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], RenderO.FLOATBUFFER);

			RenderO.FLOATBUFFER.clear();
			RenderO.FLOATBUFFER.put(new float[]{0.0F, 0.0F, 0.0F, 0.5F});
			RenderO.FLOATBUFFER.flip();
			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], RenderO.FLOATBUFFER);

			GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
			GL20.glDisableVertexAttribArray(v);

			//s1-free
			OpenGlHelper.glUseProgram(gl_current_program);
			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
			//e1-free

			//e0-drawColor

			Page.PAGE.render();

			if (gl_blend)
			{
				GL11.glEnable(GL11.GL_BLEND);
			}
			else
			{
				GL11.glDisable(GL11.GL_BLEND);
			}
			GL20.glBlendEquationSeparate(gl_blend_equation_rgb, gl_blend_equation_alpha);
			GL14.glBlendFuncSeparate(gl_blend_src_rgb, gl_blend_dst_rgb, gl_blend_src_alpha, gl_blend_dst_alpha);
		}
	}

	@Inject(method = "refreshResources", at = @At(value = "TAIL"))
	private void nali_refreshResources(CallbackInfo callbackinfo)
	{
//		if (Minecraft.getMinecraft().getTextureManager() != null)
//		{
		ClientLoader.preTexture(Reflect.getClasses("com.nali.list.render"));
//		ClientLoader.setRender();
//		}
	}

	@Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/OpenGlHelper;initializeTextures()V", shift = At.Shift.AFTER))
	private void nali_debugLayer_init(CallbackInfo callbackinfo)
	{
		if (NaliConfig.GL_DEBUG)
		{
			GL11.glEnable(KHRDebug.GL_DEBUG_OUTPUT);
			GL11.glEnable(KHRDebug.GL_DEBUG_OUTPUT_SYNCHRONOUS);
			KHRDebugCallback khrdebugcallback = new KHRDebugCallback((source, type, id, severity, message) ->
			{
				warn("KHRDebugCallback source: " + source);
				warn("KHRDebugCallback type: " + type);
				warn("KHRDebugCallback id: " + id);
				warn("KHRDebugCallback severity: " + id);
				warn("KHRDebugCallback message: " + message);
				warn(new Throwable());
			});
			GL43.glDebugMessageCallback(khrdebugcallback);
		}
		ClientLoader clientloader = new ClientLoader();
		clientloader.state |= 1+4;
		clientloader.init();
	}

	@Inject(method = "init", at = @At(value = "TAIL"))
	private void nali_init(CallbackInfo callbackinfo)
	{
		//s0-sound
		if ((NaliConfig.STATE & 8+4) == 8+4 && ClientLoader.BGM_BUFFER != -1)
		{
			NaliAL.alSourceStop(ClientLoader.BGM_SOURCE);
			NaliAL.alDeleteSources(ClientLoader.BGM_SOURCE);
			NaliAL.alDeleteBuffers(ClientLoader.BGM_BUFFER);
			ClientLoader.BGM_SOURCE = -1;
		}
		//e0-sound
	}
}
