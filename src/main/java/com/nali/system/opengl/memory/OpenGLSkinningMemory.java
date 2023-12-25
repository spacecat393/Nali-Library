package com.nali.system.opengl.memory;

import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.FloatBuffer;

@SideOnly(Side.CLIENT)
public class OpenGLSkinningMemory extends OpenGLObjectMemory
{
    public OpenGLSkinningMemory(String[] model_string_array, String folder_path, String[][] shader_string_2d_array, String shaders_folder_path)
    {
        super(model_string_array, folder_path, shader_string_2d_array, shaders_folder_path);
    }

    @Override
    public void createBufferAttribLocation(String[] model_string_array, String folder_path, String[][] shader_string_2d_array, String shaders_folder_path, String[][] attriblocation_string_2d_array, int length)
    {
        super.createBufferAttribLocation(model_string_array, folder_path, shader_string_2d_array, shaders_folder_path, attriblocation_string_2d_array, length - 2);
        String model_folder_string = folder_path + "Models/" + model_string_array[0] + '/';
        byte max_joints = (byte)Integer.parseInt(model_string_array[4]);

        float[] joints_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
        float[] weights_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");

        byte limit_max_joints = 4;

        if (max_joints != limit_max_joints)
        {
            int step = limit_max_joints - max_joints;
            int joints_float_array_length = joints_float_array.length;
            int new_size = joints_float_array_length + (joints_float_array_length / max_joints) * step;
            float[] temp_joints_float_array = new float[new_size];
            float[] temp_weights_float_array = new float[new_size];
            int index = 0;
            int temp_index = 0;

            for (int x = 0; x < temp_joints_float_array.length; x += limit_max_joints)
            {
                for (int y = 0; y < max_joints; ++y)
                {
                    temp_joints_float_array[temp_index] = joints_float_array[index];
                    temp_weights_float_array[temp_index++] = weights_float_array[index++];
                }

                for (int y = 0; y < step; ++y)
                {
                    temp_joints_float_array[temp_index] = -1;
                    temp_weights_float_array[temp_index++] = 0.0F;
                }
            }

            joints_float_array = temp_joints_float_array;
            weights_float_array = temp_weights_float_array;
        }

        FloatBuffer joints_floatbuffer = OpenGLBuffer.createFloatBuffer(joints_float_array, true);
        this.openglattribmemory_arraylist.add(new OpenGLAttribMemory(joints_floatbuffer, OpenGLBuffer.loadFloatBuffer(joints_floatbuffer), limit_max_joints));
        FloatBuffer weights_floatbuffer = OpenGLBuffer.createFloatBuffer(weights_float_array, true);
        this.openglattribmemory_arraylist.add(new OpenGLAttribMemory(weights_floatbuffer, OpenGLBuffer.loadFloatBuffer(weights_floatbuffer), limit_max_joints));
    }
}
