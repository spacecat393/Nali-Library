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
		List<Class> class_list = new ArrayList();

		try
		{
			ClassPath classpath = ClassPath.from(Thread.currentThread().getContextClassLoader());
			for (ClassPath.ClassInfo classinfo : classpath.getTopLevelClasses(package_name))
			{
				class_list.add(classinfo.load());
//				class_list.add(Class.forName(package_name + "." + classinfo.getSimpleName()));
			}
		}
		catch (IOException/* | ClassNotFoundException*/ e)
		{
			error(e);
		}

		return class_list;
	}
}
