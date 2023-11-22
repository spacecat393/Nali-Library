package com.nali.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;
import java.util.WeakHashMap;

@SideOnly(Side.CLIENT)
public class KeyTick
{
    public static Map<Integer, Boolean> KEY_MAP = new WeakHashMap<>();
}
