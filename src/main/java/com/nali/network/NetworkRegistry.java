package com.nali.network;

import com.nali.Nali;
import com.nali.system.Reflect;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Comparator;
import java.util.List;

import static com.nali.Nali.error;

public class NetworkRegistry
{
    public static SimpleNetworkWrapper I = net.minecraftforge.fml.common.network.NetworkRegistry.INSTANCE.newSimpleChannel(Nali.ID);

    public static void register()
    {
        List<Class> handler_class_list = Reflect.getClasses("com.nali.list.network.handler");
        List<Class> message_class_list = Reflect.getClasses("com.nali.list.network.message");
        handler_class_list.sort(Comparator.comparing(Class::getName));
        message_class_list.sort(Comparator.comparing(Class::getName));

        for (int i = 0; i < handler_class_list.size(); ++i)
        {
            try
            {
                Class message_clasz = message_class_list.get(i);
                I.registerMessage(handler_class_list.get(i), message_clasz, i, (Side)message_clasz.getField("SIDE").get(null));
            }
            catch (IllegalAccessException | NoSuchFieldException e)
            {
                error(e);
            }
        }
    }
}
