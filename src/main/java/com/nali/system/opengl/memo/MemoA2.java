package com.nali.system.opengl.memo;

import com.nali.system.file.FileDataReader;

public class MemoA2
{
	public int[] index_int_array;

	public float[] vertex_float_array;
	public int[] joint_int_array;
	public float[] weight_float_array;
	public byte max_joint;

	public void init(String[] model_string_array, String folder_path)
	{
		String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';

		this.index_int_array = FileDataReader.getIntArray(model_folder_string + "index.bin");
		this.vertex_float_array = FileDataReader.getFloatArray(model_folder_string + "vertex.bin");
		this.joint_int_array = FileDataReader.getIntArray(model_folder_string + "joint.bin");
		this.weight_float_array = FileDataReader.getFloatArray(model_folder_string + "weight.bin");
		this.max_joint = Byte.parseByte(model_string_array[8]);
	}
}
