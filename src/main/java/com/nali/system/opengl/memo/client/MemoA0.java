package com.nali.system.opengl.memo.client;

import com.nali.system.file.FileDataReader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MemoA0
{
//    public float[] float_array;
    public Object o;
    public byte size;
//    public int type;

    public MemoA0(Object o, byte size)
    {
        this.o = o;
        this.size = size;
//        this.type = GL11.GL_FLOAT;
    }

//    public MemoA0(int[] int_array, byte size)
//    {
//        this.o = int_array;
//        this.size = size;
////        this.type = GL11.GL_INT;
//    }

    public static MemoA0[] gen(String[] model_string_array, String[][] attriblocation_string_2d_array, String folder_path/*, String[][] shader_string_2d_array*//*, int shader_id*/)
    {
        String model_folder_string = folder_path + "/model/" + model_string_array[0] + '/';
//        String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + model_string_array[0] + "/ShaderList"));
//        String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + shader_string_2d_array[shader_id][0] + "/Shader/" + shader_string_2d_array[shader_id][1] + "/Attrib"));

        int length = attriblocation_string_2d_array.length;
        MemoA0[] memoa0_array = new MemoA0[length];
        if (model_string_array.length == 9)
        {
            String[] attriblocation_string_array;
            for (int i = 0; i < length - 2; ++i)
            {
                attriblocation_string_array = attriblocation_string_2d_array[i];
                String attriblocation_name_string = attriblocation_string_array[0];
                memoa0_array[i] = new MemoA0(FileDataReader.getFloatArray(model_folder_string + attriblocation_name_string + ".bin"/*Character.toUpperCase(attriblocation_name_string.charAt(0)) + attriblocation_name_string.substring(1)*/), Byte.parseByte(attriblocation_string_array[1]));
            }

            length -= 2;
            memoa0_array[length] = new MemoA0(FileDataReader.getIntArray(model_folder_string + attriblocation_string_2d_array[length][0] + ".bin"), Byte.parseByte(model_string_array[8]));
            length += 1;
            memoa0_array[length] = new MemoA0(FileDataReader.getFloatArray(model_folder_string + attriblocation_string_2d_array[length][0] + ".bin"), Byte.parseByte(model_string_array[8]));
        }
        else
        {
            for (int i = 0; i < length; ++i)
            {
                String[] attriblocation_string_array = attriblocation_string_2d_array[i];
                String attriblocation_name_string = attriblocation_string_array[0];
                memoa0_array[i] = new MemoA0(FileDataReader.getFloatArray(model_folder_string + attriblocation_name_string + ".bin"/*Character.toUpperCase(attriblocation_name_string.charAt(0)) + attriblocation_name_string.substring(1)*/), Byte.parseByte(attriblocation_string_array[1]));
            }
        }

        return memoa0_array;
//        switch (shader_string_2d_array[shader_id].length)
//        {
//            case 5:
//                return genO(/*model_string_array, */model_folder_string/*folder_path*//*, shader_string_2d_array*/, attriblocation_string_2d_array, attriblocation_string_2d_array.length);
//            case 7:
//                return genS(model_string_array, model_folder_string/*folder_path*//*, shader_string_2d_array*/, attriblocation_string_2d_array, attriblocation_string_2d_array.length);
//            default:
//                error("MEMOATTRIB");
//        }
//
//        return null;
    }

//    public static MemoA0[] genO(/*String[] model_string_array, */String model_folder_string/*, String folder_path*//*, String[][] shader_string_2d_array*/, String[][] attriblocation_string_2d_array, int length)
//    {
//        MemoA0[] memoa0_array = new MemoA0[length];
//        for (int i = 0; i < length; ++i)
//        {
//            String[] attriblocation_string_array = attriblocation_string_2d_array[i];
//            String attriblocation_name_string = attriblocation_string_array[0];
//            memoa0_array[i] = new MemoA0(FileDataReader.getFloatArray(model_folder_string + Character.toUpperCase(attriblocation_name_string.charAt(0)) + attriblocation_name_string.substring(1)), Byte.parseByte(attriblocation_string_array[1]));
//        }
//
//        return memoa0_array;
//    }

//    public static MemoA0[] genS(String[] model_string_array, String model_folder_string/*, String folder_path*//*, String[][] shader_string_2d_array*/, String[][] attriblocation_string_2d_array, int length)
//    {
//        MemoA1[] memoa1_array = genO(/*model_string_array, */model_folder_string/*, folder_path*//*, shader_string_2d_array*/, attriblocation_string_2d_array, length - 2);
//        byte max_joints = Byte.parseByte(model_string_array[7]);
//
//        float[] joints_float_array = FileDataReader.getFloatIntArray(model_folder_string + "/Joints");
//        float[] weights_float_array = FileDataReader.getFloatArray(model_folder_string + "/Weights");
//
//        memoa1_array[length + 1] = new MemoA1(temp_joints_float_array, limit_max_joints);
//        memoa1_array[length + 2] = new MemoA1(temp_weights_float_array, limit_max_joints);
//        return memoa1_array;
//    }
}
