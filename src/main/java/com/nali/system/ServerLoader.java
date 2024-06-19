package com.nali.system;

import com.nali.Nali;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.nali.Nali.I;

@SideOnly(Side.SERVER)
public class ServerLoader
{
    public static void pairSound()
    {
        List<Class> data_class_list = Reflect.getClasses("com.nali.list.data");

        List<String> data_string_list = new ArrayList();
        for (Class data_class : data_class_list)
        {
            data_string_list.add(data_class.getSimpleName().toLowerCase());
        }

        File[] file_array = new File(Nali.ID).listFiles();

        int step = 0;
        for (File file : file_array)
        {
            File[] sound_file_array = new File(file.getPath() + "Sound/").listFiles();
            if (sound_file_array != null)
            {
                String name_file = file.getName();
                for (int i = 0; i < data_string_list.size(); ++i)
                {
                    if (data_string_list.get(i).contains(name_file))
                    {
                        try
                        {
                            data_class_list.get(i).getField("OPENAL_STEP").set(null, step);
                            step += sound_file_array.length;
                        }
                        catch (IllegalAccessException | NoSuchFieldException e)
                        {
                            I.error(e);
                        }
                        break;
                    }
                }
            }
        }
    }
}
