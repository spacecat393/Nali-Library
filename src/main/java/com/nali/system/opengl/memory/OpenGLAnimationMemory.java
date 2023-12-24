package com.nali.system.opengl.memory;

import com.nali.Nali;
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
    //mat4[F*B*N]
    public float[] transforms_float_array;
    public byte[] idlebones_byte_array;
    public int length;

    public OpenGLAnimationMemory(String[] model_string_array, String folder_path)
    {
        String model_folder_path = folder_path + "Models/" + model_string_array[0];
        String animation_string = "/Animation/";

        if (new File(model_folder_path + animation_string).isDirectory())
        {
            this.transforms_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "/Transforms");

            try
            {
                this.idlebones_byte_array = Files.readAllBytes(Paths.get(model_folder_path + "/IdleBones"));
            }
            catch (IOException e)
            {
                Nali.error(e);
            }

            this.length = (this.transforms_float_array.length / 16) / this.idlebones_byte_array.length;
        }
    }
}
