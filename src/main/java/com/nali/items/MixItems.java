package com.nali.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MixItems extends Item
{
    public MixItems(String name, String mod_id, CreativeTabs creativetabs)
    {
        this.setRegistryName(mod_id, name);
        this.setCreativeTab(creativetabs);
        this.setTranslationKey(name);
    }
}
