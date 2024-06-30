package com.nali.system.opengl.memo.client;

import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.nio.ByteBuffer;

@SideOnly(Side.CLIENT)
public class MemoGs extends MemoGo
{
    public float[] joints_float_array;
    public float[] weights_float_array;
    public byte max_joints;
//    public int animation_id;

    public MemoGs(String[] model_string_array, String folder_path/*, String[][] shader_string_2d_array*/)
    {
        super(model_string_array, folder_path/*, shader_string_2d_array*/);
    }

    @Override
    public void createBufferAttribLocation(String[] model_string_array, String model_folder_string/*, String folder_path*/, String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, int length)
    {
        super.createBufferAttribLocation(model_string_array, model_folder_string/*, folder_path*/, shader_string_2d_array, attriblocation_string_2d_array, length - 2);
//        String model_folder_string = folder_path + "Models/" + model_string_array[0] + '/';
//        byte max_joints = Byte.parseByte(model_string_array[4]);
//        this.max_joints = Byte.parseByte(model_string_array[4]);
//        this.animation_id = Integer.parseInt(model_string_array[7]);
        this.max_joints = Byte.parseByte(model_string_array[7]);
//        this.max_joints = Byte.parseByte(model_string_array[8]);

//        float[] joints_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
        this.joints_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
//        float[] weights_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");
        this.weights_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");
        float[] temp_joints_float_array = this.joints_float_array;
        float[] temp_weights_float_array = this.weights_float_array;

        byte limit_max_joints = 4;

        if (this.max_joints != limit_max_joints)
        {
            int step = limit_max_joints - this.max_joints;
            int joints_float_array_length = this.joints_float_array.length;
            int new_size = joints_float_array_length + (joints_float_array_length / this.max_joints) * step;
            temp_joints_float_array = new float[new_size];
            temp_weights_float_array = new float[new_size];
            int index = 0;
            int temp_index = 0;

            for (int x = 0; x < temp_joints_float_array.length; x += limit_max_joints)
            {
                for (int y = 0; y < this.max_joints; ++y)
                {
                    temp_joints_float_array[temp_index] = this.joints_float_array[index];
                    temp_weights_float_array[temp_index++] = this.weights_float_array[index++];
                }

                for (int y = 0; y < step; ++y)
                {
                    temp_joints_float_array[temp_index] = -1;
                    temp_weights_float_array[temp_index++] = 0.0F;
                }
            }

//            this.joints_float_array = temp_joints_float_array;
//            this.weights_float_array = temp_weights_float_array;
        }

//        FloatBuffer joints_floatbuffer = OpenGLBuffer.createFloatBuffer(temp_joints_float_array, true);
        ByteBuffer joints_bytebuffer = OpenGLBuffer.createFloatByteBuffer(temp_joints_float_array, true);
        this.openglattribmemo_arraylist.add(new MemoAttrib(joints_bytebuffer, OpenGLBuffer.loadFloatBuffer(joints_bytebuffer), limit_max_joints));
//        FloatBuffer weights_floatbuffer = OpenGLBuffer.createFloatBuffer(temp_weights_float_array, true);
        ByteBuffer weights_bytebuffer = OpenGLBuffer.createFloatByteBuffer(temp_weights_float_array, true);
        this.openglattribmemo_arraylist.add(new MemoAttrib(weights_bytebuffer, OpenGLBuffer.loadFloatBuffer(weights_bytebuffer), limit_max_joints));
    }
}
