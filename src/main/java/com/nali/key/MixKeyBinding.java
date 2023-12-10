package com.nali.key;

import net.minecraft.client.settings.KeyBinding;

public class MixKeyBinding extends KeyBinding
{
    public MixKeyBinding(String name, int key, String mod_id)
    {
        super("key." + name, key, "key.categories." + mod_id);
    }
}
