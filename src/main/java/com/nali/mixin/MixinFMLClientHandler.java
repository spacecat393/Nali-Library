package com.nali.mixin;

import com.nali.NaliConfig;
import com.nali.gui.page.PageLoad;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.Reflect;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.InvocationTargetException;

import static com.nali.Nali.error;

@Mixin(FMLClientHandler.class)
public abstract class MixinFMLClientHandler
{
	@Inject(remap = false, method = "finishMinecraftLoading", at = @At(value = "TAIL"))
	private void nali_finishMinecraftLoading(CallbackInfo callbackinfo)
	{
		GL11.glClearColor(0, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		Display.update();

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		PageLoad pageload = new PageLoad();
		pageload.init();
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		pageload.gen(Display.getWidth(), Display.getHeight());
		pageload.render();
		pageload.clear();
		Display.update();

		ClientLoader.setRender();

		if ((NaliConfig.STATE & 1) == 1)
		{
			for (Class render_class : Reflect.getClasses("com.nali.list.render.s"))
			{
				try
				{
					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAS").get(null), render_class.getField("IBOTHDASN").get(null))).draw();
				}
				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
				{
					error(e);
				}
			}

			for (Class render_class : Reflect.getClasses("com.nali.list.render.o"))
			{
				try
				{
					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("ICLIENTDAO").get(null))).draw();
				}
				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
				{
					error(e);
				}
			}
		}
	}
}
