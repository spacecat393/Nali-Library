package com.nali.key;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MixKeyBinding extends KeyBinding
{
    public MixKeyBinding(String[] string_array, Integer key)
    {
        super(getDescription(string_array), key, "key.categories." + string_array[1]);
    }

    public static String getDescription(String[] string_array)
    {
        return "key." + string_array[1] + "." + string_array[0];
    }
}
