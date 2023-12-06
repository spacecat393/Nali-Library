package com.nali.key;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.Map;
import java.util.WeakHashMap;

@SideOnly(Side.CLIENT)
public class KeyTick
{
    public static Map<Integer, Boolean> KEY_MAP = new WeakHashMap<>();

    public static void addKey(int key)
    {
        if (Keyboard.getEventKeyState())
        {
            KEY_MAP.put(key, true);
        }
        else
        {
            KEY_MAP.remove(key);
        }
    }
}
