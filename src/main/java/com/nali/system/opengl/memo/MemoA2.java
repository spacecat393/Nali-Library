package com.nali.system.opengl.memo;

import com.nali.Nali;
import com.nali.system.file.FileDataReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MemoA2
{
	public int[] index_int_array;

	public float[] vertex_float_array;
//	public int[] joint_int_array;
//	public short[] joint_short_array;
	public byte[] joint_byte_array;
	public float[] weight_float_array;
	public byte max_joint;

	public void init(String[] model_string_array, String folder_path)
	{
		String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';

		this.index_int_array = FileDataReader.getIntArray(model_folder_string + "index.bin");
		this.vertex_float_array = FileDataReader.getFloatArray(model_folder_string + "vertex.bin");
//		this.joint_int_array = FileDataReader.getIntArray(model_folder_string + "joint.bin");
		try
		{
//			byte[] joint_byte_array = Files.readAllBytes(Paths.get(model_folder_string + "joint.bin"));
//			int joint_byte_array_length = joint_byte_array.length;
//			this.joint_short_array = new short[joint_byte_array_length];
//			for (int i = 0; i < joint_byte_array_length; ++i)
//			{
//				this.joint_short_array[i] = (short)(joint_byte_array[i] & 0xFF);
//			}
			this.joint_byte_array = Files.readAllBytes(Paths.get(model_folder_string + "joint.bin"));
		}
		catch (IOException e)
		{
			Nali.error(e);
		}
		this.weight_float_array = FileDataReader.getFloatArray(model_folder_string + "weight.bin");
		this.max_joint = Byte.parseByte(model_string_array[7]);
	}
}
