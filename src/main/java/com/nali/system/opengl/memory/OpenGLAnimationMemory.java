package com.nali.system.opengl.memory;

import com.nali.Nali;
import com.nali.system.DataLoader;
import com.nali.system.file.FileDataReader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SideOnly(Side.CLIENT)
public class OpenGLAnimationMemory
{
    public static void set(DataLoader dataloader, String[] model_string_array, String folder_path, int object_index)
    {
        String model_folder_path = folder_path + "Models/" + model_string_array[0];
        String animation_string = "/Animation/";

        if (new File(model_folder_path + animation_string).isDirectory())
        {
            Object[] object_array = new Object[3];

            object_array[0] = FileDataReader.getFloatArray(model_folder_path + animation_string + "/Transforms");

            try
            {
                object_array[1] = Files.readAllBytes(Paths.get(model_folder_path + "/IdleBones"));
            }
            catch (IOException ioexception)
            {
                Nali.LOGGER.error(ioexception.getMessage(), ioexception);
            }

            object_array[2] = (((float[])object_array[0]).length / 16) / ((byte[])object_array[1]).length;

            dataloader.model_object_array[object_index] = object_array;
        }
    }
}
