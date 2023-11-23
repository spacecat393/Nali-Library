package com.nali.system;

import com.google.common.reflect.ClassPath;
import com.nali.Nali;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reflect
{
    public static List<Class> getClasses(String package_name)
    {
        ArrayList<Class> class_arraylist = new ArrayList<Class>();

        try
        {
            ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader());
            for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses(package_name))
            {
                class_arraylist.add(Class.forName(package_name + "." + classInfo.getSimpleName()));
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            Nali.LOGGER.error(e.getMessage(), e);
        }

        return class_arraylist;
//
//        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//        String path = package_name.replace('.', '/');
//
//        Enumeration<URL> url_enumeration = null;
//
//        try
//        {
//            url_enumeration = classloader.getResources(path);
//        }
//        catch (IOException e)
//        {
//            Nali.LOGGER.error(e.getMessage(), e);
//        }
//
//        List<File> file_list = new ArrayList<>();
//
//        while (url_enumeration.hasMoreElements())
//        {
//            URL url = url_enumeration.nextElement();
//            file_list.add(new File(url.getFile()));
//        }
//
//        List<Class> class_list = new ArrayList<>();
//        for (File file : file_list)
//        {
//            class_list.addAll(findClasses(file, package_name));
//        }
//
//        return class_list;
    }

//    public static List<Class> findClasses(File directory_file, String package_name)
//    {
//        List<Class> class_list = new ArrayList<>();
//        if (!directory_file.exists())
//        {
//            return class_list;
//        }
//
//        File[] file_array = directory_file.listFiles();
//        if (file_array == null)
//        {
//            return class_list;
//        }
//
//        for (File file : file_array)
//        {
//            if (file.isDirectory())
//            {
//                assert !file.getName().contains(".");
//                class_list.addAll(findClasses(file, package_name + "." + file.getName()));
//            }
//            else if (file.getName().endsWith(".class"))
//            {
//                String className = package_name + '.' + file.getName().substring(0, file.getName().length() - 6);
//                try
//                {
//                    class_list.add(Class.forName(className));
//                }
//                catch (ClassNotFoundException e)
//                {
//                    Nali.LOGGER.error(e.getMessage(), e);
//                }
//            }
//        }
//
//        return class_list;
//    }
//    public static Object[] CLIENT_REFLECT_OBJECTS_ARRAY;
//
//    public static Object getField(Object[] object_array, int id, Object object)
//    {
//        try
//        {
//            return ((Field)object_array[id]).get(object);
//        }
//        catch (IllegalArgumentException | IllegalAccessException exception)
//        {
//            Main.LOGGER.error(exception.getMessage(), exception);
//        }
//
//        return null;
//    }
//
//    public static void reflectProjectField()
//    {
////        {
////            try
////            {
////                {
////                    Field field = ContextCapabilities.class.getDeclaredField("glUniformMatrix4fv");
////                    field.setAccessible(true);
////                    CLIENT_REFLECT_OBJECTS_ARRAY[1] = field;
////                }
////
////                Method method = GL20.class.getDeclaredMethod("nglUniformMatrix4fv", int.class, int.class, boolean.class, long.class, long.class);
////                method.setAccessible(true);
////                CLIENT_REFLECT_OBJECTS_ARRAY[2] = method;
////            }
////            catch (NoSuchFieldException e)
////            {
////                Main.LOGGER.error(e.getMessage(), e);
////            }
////            catch (NoSuchMethodException e)
////            {
////                Main.LOGGER.error(e.getMessage(), e);
////            }
////        }
////        int id = 0;
//        Field[] field_array = Project.class.getDeclaredFields();
//
//        for (int i = 0; i < field_array.length; ++i)
//        {
//            if (field_array[i].getType().equals(FloatBuffer.class))
//            {
////                if (id == 2)
////                {
//                field_array[i].setAccessible(true);
//
//                try
//                {
//                    CLIENT_REFLECT_OBJECTS_ARRAY[0] = field_array[i];
//                }
//                catch (IllegalArgumentException illegalargumentexception)
//                {
//                    Main.LOGGER.error(illegalargumentexception.getMessage(), illegalargumentexception);
//                }
//
//                break;
////                }
////                ++id;
//            }
//        }
//    }
}
