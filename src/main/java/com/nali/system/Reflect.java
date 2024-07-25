package com.nali.system;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.nali.Nali.error;

public class Reflect
{
    public static List<Class> getClasses(String package_name)
    {
        List<Class> class_arraylist = new ArrayList();

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
            error(e);
        }

        return class_arraylist;
    }
}
