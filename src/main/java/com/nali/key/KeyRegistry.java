package com.nali.key;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyRegistry
{
    public static KeyBinding register(String name, int key, String mod_id)
    {
        KeyBinding keyBinding = new KeyBinding("key." + mod_id + "." + name, key, "key.categories." + mod_id);
        ClientRegistry.registerKeyBinding(keyBinding);
        return keyBinding;
    }
}
