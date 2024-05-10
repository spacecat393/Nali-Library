//package com.nali.key;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@SideOnly(Side.CLIENT)
//public class KeyTick
//{
//    public static Set<Integer> KEY_SET = new HashSet();
////    public static Set<Integer> KKEY_SET = new HashSet();
////    public static Set<Integer> MKEY_SET = new HashSet();
//
//    public static void addKKey(int key)
//    {
//        if (Keyboard.getEventKeyState())
////        if (Keyboard.isKeyDown(key))
//        {
//            KEY_SET.add(key);
////            KKEY_SET.add(key);
//        }
//        else
//        {
////            Nali.LOGGER.info("K " + key);
//            KEY_SET.remove(key);
////            KKEY_SET.remove(key);
//        }
//    }
//
//    public static void addMKey(int key)
//    {
//        if (Mouse.getEventButtonState())
////        if (Mouse.isButtonDown(key + 100))
//        {
//            KEY_SET.add(key);
////            MKEY_SET.add(key);
//        }
//        else
//        {
////            Nali.LOGGER.info("M " + key);
//            KEY_SET.remove(key);
////            MKEY_SET.remove(key);
//        }
//    }
//}
