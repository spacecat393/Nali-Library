package com.nali.system;

import com.nali.Nali;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.Map;

import static com.nali.Nali.I;

@SideOnly(Side.SERVER)
public class ServerLoader
{
    public static void pairSound()
    {
//        List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");
//
//        List<String> data_string_list = new ArrayList();
//        for (Class data_class : data_class_list)
//        {
//            data_string_list.add(data_class.getSimpleName().toLowerCase());
//        }
//        List<Class> data_class_list = I.bothloader.data_class_list;
        Map<String, Class> data_class_map = I.bothloader.data_class_map;

        File[] file_array = new File(Nali.ID).listFiles();

        int step = 0;
        for (File file : file_array)
        {
            File[] sound_file_array = new File(file.getPath() + "Sound/").listFiles();
            if (sound_file_array != null)
            {
                try
                {
                    data_class_map.get(file.getName()).getField("OPENAL_STEP").set(null, step);
                    step += sound_file_array.length;
                }
                catch (IllegalAccessException | NoSuchFieldException e)
                {
                    I.error(e);
                }
            }
        }
    }
}
