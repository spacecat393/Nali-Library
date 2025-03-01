package com.nali.mixin;

import com.nali.NaliConfig;
import com.nali.gui.box.Box;
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

import java.util.Comparator;
import java.util.List;

import static com.nali.Nali.error;

@Mixin(FMLClientHandler.class)
public abstract class MixinFMLClientHandler
{
//	@Redirect(remap = false, method = "beginMinecraftLoading", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/fml/client/SplashProgress;start()V"))
//	private void nali_beginMinecraftLoading()
//	{
//		if ((NaliConfig.STATE & 32) == 0)
//		{
//			SplashProgress.start();
//		}
//	}

	@Inject(remap = false, method = "finishMinecraftLoading", at = @At(value = "TAIL"))
	private void nali_finishMinecraftLoading(CallbackInfo callbackinfo)
	{
		GL11.glClearColor(0, 0, 0, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		if ((NaliConfig.STATE & 32) == 0)
		{
			Display.update();
		}

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		PageLoad pageload = new PageLoad();
		pageload.init();
		Box.WIDTH = -1;
		pageload.render();
		pageload.clear();
		Display.update();

//		List<Class> da_class_list = Reflect.getClasses("com.nali.list.da");
//		for (Class da_class : da_class_list)
//		{
//			try
//			{
//				Field field = da_class.getField("IDA");
//				field.set(null, field.getType().newInstance());
//			}
//			catch (NoSuchFieldException | IllegalAccessException | InstantiationException e)
//			{
//				error(e);
//			}
//		}
//		List<Class> block_class_list = Reflect.getClasses("com.nali.list.block");
//		for (Class block_class : block_class_list)
//		{
//			try
//			{
//				block_class.getMethod("newC").invoke(null);
//			}
//			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//			{
//				error(e);
//			}
//		}

		ClientLoader.preTexture(Reflect.getClasses("com.nali.list.render"));
//		ClientLoader.setRender();

		if ((NaliConfig.STATE & 1) == 1)
		{
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, Float.MAX_VALUE);
			RenderO.take();

			List<Class> render_class_list = Reflect.getClasses("com.nali.list.render");
//			List<Class> da_class_list = Reflect.getClasses("com.nali.list.da");
			render_class_list.sort(Comparator.comparing(Class::getName));
//			da_class_list.sort(Comparator.comparing(Class::getName));

			for (int i = 0; i < render_class_list.size(); ++i)
			{
				Class render_class = render_class_list.get(i);
//				Class da_class = da_class_list.get(i);

				try
				{
//					IBothDaO bd = (IBothDaO)da_class.getField("IDA").get(null);
					((RenderO)render_class.newInstance()).doDraw(/*bd*/);
				}
				catch (IllegalAccessException | InstantiationException/* | NoSuchFieldException*/ e)
				{
					error(e);
				}
			}
//			for (Class render_class : Reflect.getClasses("com.nali.list.render.o"))
//			{
//				try
//				{
//					((RenderO)render_class.getConstructors()[0].newInstance(render_class.getField("IDA").get(null))).draw();
//				}
//				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
//				{
//					error(e);
//				}
//			}

			RenderO.free();
			GL11.glPopMatrix();
		}

		if ((NaliConfig.STATE & 16) == 16)
		{
			//need test
			//free memory and rebuild
			ClientLoader clientloader = new ClientLoader();
			clientloader.free();
			clientloader.init();
			ClientLoader.preTexture(Reflect.getClasses("com.nali.list.render"));
		}
	}
}
