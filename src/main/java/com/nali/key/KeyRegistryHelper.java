//package com.nali.key;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.lang.reflect.Method;
//
//@SideOnly(Side.CLIENT)
//public class KeyRegistryHelper
//{
//    public static Method[] DETECT_METHOD_ARRAY;
////    public static List<Class> KEY_CLASS_LIST = Reflect.getClasses("com.nali.list.key");
////    public static List<Class> FUNCTION_CLASS_LIST = Reflect.getClasses("com.nali.list.function");
////    static
////    {
////        KEY_CLASS_LIST.sort(Comparator.comparing(Class::getName));
////    }
//
////    public static void set()
////    {
////        List<Class> key_class_list = Reflect.getClasses("com.nali.list.key");
////        DETECT_METHOD_ARRAY = new Method[key_class_list.size()];
//////        for (Class clasz : KEY_CLASS_LIST)
////        for (int i = 0; i < key_class_list.size(); ++i)
////        {
////            try
////            {
////                Class clasz = key_class_list.get(i);
////
////                DETECT_METHOD_ARRAY[i] = clasz.getMethod("detect");
////            }
////            catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e)
////            {
////                Nali.error(e);
////            }
////        }
////
//////        for (Class clasz : FUNCTION_CLASS_LIST)
//////        {
//////            try
//////            {
//////                clasz.getConstructor().newInstance();
//////            }
//////            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//////            {
//////                Nali.error(e);
//////            }
//////        }
////    }
//}
