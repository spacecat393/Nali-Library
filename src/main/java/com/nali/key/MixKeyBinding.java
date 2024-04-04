package com.nali.key;

import net.minecraft.client.settings.KeyBinding;

public class MixKeyBinding extends KeyBinding
{
    public MixKeyBinding(String[] string_array, int key)
    {
        super("key." + string_array[1] + "." + string_array[0], key, "key.categories." + string_array[1]);
    }
}
