package com.nali.mixin;

import com.nali.NaliAL;
import com.nali.NaliConfig;
import com.nali.gui.key.Key;
import com.nali.gui.page.Page;
import com.nali.sound.Sound;
import com.nali.system.ClientLoader;
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
//		//use this thread
//		SharedDrawable shareddrawable = null;
//		Drawable drawable = Display.getDrawable();
//		try
//		{
////			Drawable d = IMixinSplashProgress.d();
////			d.releaseContext();
////			d.destroy();
////			drawable.destroy();
////			drawable.releaseContext();
//			shareddrawable = new SharedDrawable(drawable);
//			shareddrawable.makeCurrent();
//		}
//		catch (LWJGLException e)
//		{
//			error(e);
//		}
//
////		Display.update();
////		ClientLoader.render();
//		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//		PageLoad pageload = new PageLoad();
//		pageload.init();
//		pageload.render();
//		pageload.clear();
//		Display.update();
////		while(true)if(false)break;
//
//		this.setRender();
//
////		ClientLoader.loadInit();
//		if ((NaliConfig.STATE & 1) == 1)
//		{
//			for (Class render_class : Reflect.getClasses("com.nali.list.render.s"))
//			{
//				try
//				{
//					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAS").get(null), render_class.getField("IBOTHDASN").get(null))).draw();
//				}
//				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
//				{
//					error(e);
//				}
//			}
//
//			for (Class render_class : Reflect.getClasses("com.nali.list.render.o"))
//			{
//				try
//				{
//					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAO").get(null))).draw();
//				}
//				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
//				{
//					error(e);
//				}
//			}
//		}
//
//		//use current thread
//		try
//		{
//			shareddrawable.releaseContext();
//			shareddrawable.destroy();
//			drawable.makeCurrent();
//		}
//		catch (LWJGLException e)
//		{
//			error(e);
//		}

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
