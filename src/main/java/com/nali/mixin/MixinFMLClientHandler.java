package com.nali.mixin;

import com.nali.NaliConfig;
import com.nali.da.IBothDaO;
import com.nali.gui.page.Page;
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
import java.util.Comparator;
import java.util.List;

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
		Page.WIDTH = -1;
		pageload.render();
		pageload.clear();
		Display.update();

		ClientLoader.preTexture(Reflect.getClasses("com.nali.list.render"));
//		ClientLoader.setRender();

		if ((NaliConfig.STATE & 1) == 1)
		{
			List<Class> render_class_list = Reflect.getClasses("com.nali.list.render");
			List<Class> da_class_list = Reflect.getClasses("com.nali.list.da");
			render_class_list.sort(Comparator.comparing(Class::getName));
			da_class_list.sort(Comparator.comparing(Class::getName));

			for (int i = 0; i < render_class_list.size(); ++i)
			{
				Class render_class = render_class_list.get(i);
				Class da_class = da_class_list.get(i);

				try
				{
					IBothDaO bd = (IBothDaO)da_class.getField("IDA").get(null);
					((RenderO)render_class.getConstructors()[0].newInstance()).draw(bd);
				}
				catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchFieldException e)
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
		}
	}
}
