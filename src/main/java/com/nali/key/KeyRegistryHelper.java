package com.nali.key;

import com.nali.Nali;
import com.nali.system.Reflect;
import com.nali.system.StringReader;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

@SideOnly(Side.CLIENT)
public class KeyRegistryHelper
{
    public static List<Class> KEY_CLASS_LIST = Reflect.getClasses("com.nali.list.key");
    static
    {
        KEY_CLASS_LIST.sort(Comparator.comparing(Class::getName));
    }

    public static void set()
    {
        for (Class clasz : KEY_CLASS_LIST)
        {
            try
            {
                ClientRegistry.registerKeyBinding((KeyBinding)clasz.getConstructor(String.class).newInstance(StringReader.get(clasz)[0]));
            }
            catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e)
            {
                Nali.error(e);
            }
        }
    }
}
