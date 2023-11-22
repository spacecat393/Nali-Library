package com.nali.entities;

import com.nali.Nali;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EntitiesRegistry
{
    public static void register(String name, String mod_id, int id, Class entity_class, Object mod, int main_color, int sub_color)
    {
        EntityRegistry.registerModEntity(new ResourceLocation(mod_id, name), entity_class, name, id, mod, 64, 1, true, main_color, sub_color);
    }

    @SideOnly(Side.CLIENT)
    public static void onModelRegistryEvent(List<Class> render_class_list, List<Class> entity_class_list)
    {
        for (int index = 0; index < entity_class_list.size(); ++index)
        {
            try
            {
                Class render_class = render_class_list.get(index);
                Constructor constructor = render_class.getConstructor(RenderManager.class);
                RenderingRegistry.registerEntityRenderingHandler(entity_class_list.get(index), new IRenderFactory()
                {
                    @Override
                    public Render createRenderFor(RenderManager rendermanager)
                    {
                        try
                        {
                            return (Render)constructor.newInstance(rendermanager);
                        }
                        catch (InstantiationException | InvocationTargetException | IllegalAccessException e)
                        {
                            Nali.LOGGER.error(e.getMessage(), e);
                        }

                        return null;
                    }
                });
            }
            catch (NoSuchMethodException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}
