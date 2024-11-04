package com.nali.mixin;

import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.sound.Sound;
import com.nali.system.ClientLoader;
import com.nali.system.Reflect;
import com.nali.system.Timing;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.KHRDebug;
import org.lwjgl.opengl.KHRDebugCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;

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
		for (Sound sound : new HashSet<>(SOUND_SET))
		{
			sound.loop();
		}

		for (int i = 0; i < KEY_ARRAY.length; ++i)
		{
//			Key key = KEY_ARRAY[i];
//			int key_code = key.getKeyCode();
//			if ((key_code < 0 && Mouse.isButtonDown(key_code + 100)) || (key_code > 0 && Keyboard.isKeyDown(key_code)))
//			{
//			key.run();
//			}
			KEY_ARRAY[i].run();
		}
	}

	@Inject(method = "refreshResources", at = @At(value = "TAIL"))
	private void nali_refreshResources(CallbackInfo callbackinfo)
	{
		if (Minecraft.getMinecraft().getTextureManager() != null)
		{
			this.setRender();
		}
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
		this.setRender();
		ClientLoader.loadInit();
		//s0-sound
		if ((NaliConfig.STATE & 8) == 8)
		{
			NaliAL.alSourceStop(ClientLoader.BGM_SOURCE);
		}
		//e0-sound
	}

	private void setRender()
	{
		this.preTexture(Reflect.getClasses("com.nali.list.render.o"));
		this.preTexture(Reflect.getClasses("com.nali.list.render.s"));
	}

	private void preTexture(List<Class> render_class_list)
	{
		for (Class render_class : render_class_list)
		{
			try
			{
				render_class.getMethod("setTextureMap").invoke(null);
			}
			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
			{
			}
		}
	}
}
