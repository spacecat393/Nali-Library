package com.nali.system;

import com.nali.system.opengl.memo.MemoA2;
import com.nali.system.opengl.memo.MemoF2;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.*;

public abstract class BothLoader
{
	public static List<MemoF2> F2_LIST = new ArrayList();//frame
	public static Map<Integer, MemoA2> A2_MAP = new HashMap();

	public void init()
	{
		File[] file_array = new File(ID).listFiles();
		if (file_array != null)
		{
			//s0-data
			int max_ordinal = 0;

			List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
//		for (Class data_class : data_class_list)
//		{
//			try
//			{
//				data_class.getMethod("run").invoke(null);
//			}
//			catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
//			{
//			}
//		}

			for (Class data_class : data_class_list)
			{
				try
				{
					Field field = data_class.getField("ORDINAL");
					max_ordinal = Math.max(max_ordinal, (byte)field.get(null));
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}
			}
			++max_ordinal;

			Map<String, Class>[] data_class_map_array = new Map[max_ordinal];
			int[] ordinal_int_array = new int[max_ordinal];
			for (int i = 0; i < max_ordinal; ++i)
			{
				data_class_map_array[i] = new HashMap();
			}
			for (Class data_class : data_class_list)
			{
				try
				{
					Field field = data_class.getField("ORDINAL");
					byte ordinal = (byte)field.get(null);
					data_class_map_array[ordinal].put(StringReader.get(data_class)[1], data_class);
					++ordinal_int_array[ordinal];
				}
				catch (IllegalAccessException | NoSuchFieldException e)
				{
					error(e);
				}
			}

			File[][] file_2d_array = new File[max_ordinal][];
			for (int i = 0; i < max_ordinal; ++i)
			{
				file_2d_array[i] = new File[ordinal_int_array[i]];
			}

			int[] add_int_array = new int[max_ordinal];
			for (File file : file_array)
			{
				String name = file.getName();
				for (int i = 0; i < max_ordinal; ++i)
				{
					if (data_class_map_array[i].get(name) != null)
					{
						file_2d_array[i][add_int_array[i]++] = file;
						break;
					}
				}
			}
			//e0-data

			this.load(max_ordinal, data_class_list, file_2d_array, data_class_map_array);
		}
		else
		{
			warn("SKIP_DATA");
		}
	}
	public abstract void load(int max_ordinal, List<Class> data_class_list, File[][] file_2d_array, Map<String, Class>[] data_class_map_array);
}
