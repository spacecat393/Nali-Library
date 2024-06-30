package com.nali.system.opengl.memo.client;

import com.nali.system.file.FileDataReader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

@SideOnly(Side.CLIENT)
public class MemoAnimation
{
    //mat4[F*B*N]
    public float[] transforms_float_array;
//    public byte[] idlebones_byte_array;
//    public float[] visual_bones_float_array;
//    public int[][] bones_2d_int_array;
    public int bones;
    public int length;

    public MemoAnimation(String[] model_string_array, String folder_path)
    {
        String model_folder_path = folder_path + "/Model/" + model_string_array[0];
        String animation_string = "/Animation/";

        if (new File(model_folder_path + animation_string).isDirectory())
        {
            this.transforms_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "/Transforms");

//            try
//            {
//                this.idlebones_byte_array = Files.readAllBytes(Paths.get(model_folder_path + "/IdleBones"));
//            }
//            catch (IOException e)
//            {
//                Nali.error(e);
//            }
//
//            this.length = (this.transforms_float_array.length / 16) / this.idlebones_byte_array.length;
//            this.visual_bones_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "VisualBones");
//
//            int max_bones = this.visual_bones_float_array.length / 16;
//            int[][] bones_2d_int_array = new int[max_bones][];
//            for (int i = 0; i < max_bones; ++i)
//            {
//                bones_2d_int_array[i] = FileDataReader.getIntArray(model_folder_path + animation_string + "Bones/" + i);
//            }
//
//            int[][] back_bones_2d_int_array = new int[max_bones][];
//
//            for (int j = 0; j < max_bones; ++j)
//            {
//                int[] bones = bones_2d_int_array[j];
//                back_bones_2d_int_array[j] = new int[bones.length];
//
//                int b_index = 0;
//                for (int b = bones.length - 1; b > -1; --b)
//                {
//                    int[] b_bones = back_bones_2d_int_array[j];
//                    b_bones[b_index++] = bones[b];
//                }
//            }
//
//            this.bones_2d_int_array = back_bones_2d_int_array;

            this.bones = new File(model_folder_path + animation_string + "Bones").listFiles().length;
            this.length = (this.transforms_float_array.length / 16) / this.bones;
        }
    }
}
