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
            Nali.error(e);
        }

        return class_arraylist;
    }
}
