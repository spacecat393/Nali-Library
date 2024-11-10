package com.nali.mixin;

import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.gui.key.Key;
import com.nali.gui.page.Page;
import com.nali.render.RenderO;
import com.nali.sound.Sound;
import com.nali.system.ClientLoader;
import com.nali.system.Timing;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import org.lwjgl.MemoryUtil;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.opengl.KHRDebugCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;

import static com.nali.Nali.warn;
import static com.nali.key.Key.KEY_ARRAY;
import static com.nali.sound.Sound.SOUND_SET;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft
{
	@Inject(method = "runGameLoop", at = @At(value = "HEAD"))
	private void nali_runGameLoop(CallbackInfo callbackinfo)
	{
		Timing.count();

		EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
		if (entityplayersp != null)
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
			Key.KEY.run();
		}
	}

	@Inject(method = "updateDisplay", at = @At(value = "HEAD"))
	private void nali_updateDisplay(CallbackInfo callbackinfo)
	{
		if (Page.PAGE != null)
		{
			Page.PAGE.render();
		}
	}

	@Inject(method = "refreshResources", at = @At(value = "TAIL"))
	private void nali_refreshResources(CallbackInfo callbackinfo)
	{
//		if (Minecraft.getMinecraft().getTextureManager() != null)
//		{
		ClientLoader.setRender();
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
		ClientLoader.loadPreInit();
	}

	@Inject(method = "init", at = @At(value = "TAIL"))
	private void nali_init(CallbackInfo callbackinfo)
	{
		//s0-sound
		if ((NaliConfig.STATE & 8+4) == 8+4)
		{
			NaliAL.alSourceStop(ClientLoader.BGM_SOURCE);
			NaliAL.alDeleteSources(ClientLoader.BGM_SOURCE);
			NaliAL.alDeleteBuffers(ClientLoader.BGM_BUFFER);
			ClientLoader.BGM_SOURCE = -1;
		}
		//e0-sound
	}
}
