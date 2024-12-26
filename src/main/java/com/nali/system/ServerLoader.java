package com.nali.system;

import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.memo.MemoA2;
import com.nali.system.opengl.memo.MemoF2;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.nali.Nali.error;

@SideOnly(Side.SERVER)
public class ServerLoader extends BothLoader
{
//	public static List<Runnable> RUNNABLE_LIST = new ArrayList();

	@Override
	public void load(int max_ordinal, List<Class> data_class_list, File[][] file_2d_array, Map<String, Class>[] data_class_map_array)
	{
		int g_size = 0;
		int n_size = 0;

		for (int i = 0; i < file_2d_array.length; ++i)
		{
			File[] file_array = file_2d_array[i];
			Map<String, Class> data_class_map = data_class_map_array[i];

			for (File file : file_array)
			{
				File model_file = new File(file, "model.dat");
				if (model_file.isFile())
				{
					String name = file.getName();
					String[][] model_string_2d_array = FileDataReader.getMixXStringArray(model_file.toPath());

					try
					{
						data_class_map.get(name).getField("MODEL_STEP").set(null, g_size);
					}
					catch (NoSuchFieldException | IllegalAccessException e)
					{
						error(e);
					}

					for (String[] model_string_array : model_string_2d_array)
					{
						if (model_string_array.length == 9)
						{
							MemoA2 memoa2 = new MemoA2();
							memoa2.init(model_string_array, file.getPath());
							A2_MAP.put(g_size, memoa2);
						}
						++g_size;
					}
				}
			}

			for (File file : file_array)
			{
				File frame_file = new File(file, "frame.dat");
				if (frame_file.isFile())
				{
					String[][] frame_string_2d_array = FileDataReader.getMixXStringArray(frame_file.toPath());

					try
					{
						data_class_map.get(file.getName()).getField("FRAME_STEP").set(null, F2_LIST.size());
					}
					catch (NoSuchFieldException | IllegalAccessException e)
					{
						error(e);
					}

					for (String[] frame_string_array : frame_string_2d_array)
					{
						F2_LIST.add(new MemoF2(frame_string_array, file.getPath()));
					}
				}
			}

			for (File file : file_array)
			{
				File[] sound_file_array = new File(file, "sound").listFiles();
				if (sound_file_array != null)
				{
					try
					{
						data_class_map.get(file.getName()).getField("SOUND_STEP").set(null, n_size);

						for (File sound_file : sound_file_array)
						{
							++n_size;
						}
					}
					catch (IllegalAccessException | NoSuchFieldException e)
					{
						error(e);
					}
				}
			}
		}

		this.loadDa();
	}

//	public static void pairSound()
//	{
//		Map<String, Class> data_class_map = new HashMap();
//		List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
////		for (Class data_class : this.data_class_list)
//		for (Class data_class : data_class_list)
//		{
////			this.data_string_list.add(data_class.getSimpleName().toLowerCase());
//			data_class_map.put(StringReader.get(data_class)[1], data_class);
//		}
//
////		List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
////
////		List<String> data_string_list = new ArrayList();
////		for (Class data_class : data_class_list)
////		{
////			data_string_list.add(data_class.getSimpleName().toLowerCase());
////		}
////		List<Class> data_class_list = I.bothloader.data_class_list;
//		File[] file_array = new File(Nali.ID).listFiles();
//
//		int step = 0;
//		for (File file : file_array)
//		{
//			File[] sound_file_array = new File(file, "sound").listFiles();
//			if (sound_file_array != null)
//			{
//				try
//				{
//					data_class_map.get(file.getName()).getField("SOUND_STEP").set(null, step);
//					step += sound_file_array.length;
//				}
//				catch (IllegalAccessException | NoSuchFieldException e)
//				{
//					error(e);
//				}
//			}
//		}
//	}
}
