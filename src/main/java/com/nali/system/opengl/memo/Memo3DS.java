package com.nali.system.opengl.memo;

import com.nali.system.file.FileDataReader;

public class Memo3DS
{
    public int[] index_int_array;
    public float[] vertices_float_array;

    public Memo3DS(String[] model_string_array, String folder_path)
    {
        String model_folder_string = folder_path + "/Model/" + model_string_array[0] + '/';
        this.index_int_array = FileDataReader.getIntArray(model_folder_string + "/Index");
        this.vertices_float_array = FileDataReader.getFloatArray(model_folder_string + "/Vertices");
    }
}
