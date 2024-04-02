package com.nali.networks;

import com.nali.Nali;
import com.nali.system.Reference;
import com.nali.system.Reflect;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Comparator;
import java.util.List;

public class NetworksRegistry
{
    public static SimpleNetworkWrapper I = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static void register()
    {
        List<Class> handlers_class_list = Reflect.getClasses("com.nali.list.handlers");
        List<Class> messages_class_list = Reflect.getClasses("com.nali.list.messages");
        handlers_class_list.sort(Comparator.comparing(Class::getName));
        messages_class_list.sort(Comparator.comparing(Class::getName));

        for (int i = 0; i < handlers_class_list.size(); ++i)
        {
            try
            {
                Class messages_clasz = messages_class_list.get(i);
                I.registerMessage(handlers_class_list.get(i), messages_clasz, i, (Side)messages_clasz.getField("SIDE").get(null));
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                Nali.error(e);
            }
        }
    }
}
