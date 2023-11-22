package com.nali.items;

import com.nali.Nali;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

public class ItemsRegistry
{
    public static void registerItems(RegistryEvent.Register<Item> event, Field[] field_array)
    {
        for (Field field : field_array)
        {
            try
            {
                event.getRegistry().register((Item) field.get(null));
            }
            catch (IllegalAccessException e)
            {
                Nali.LOGGER.error(e.getMessage(), e);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels(ModelRegistryEvent event, Field[] field_array)
    {
        for (Field field : field_array)
        {
            try
            {
                Item item = (Item) field.get(null);
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
            catch (IllegalAccessException e)
            {
                Nali.LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
