package com.nali.system.opengl.memo.client;

import com.nali.NaliConfig;
import com.nali.math.M4x4;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.nali.Nali.ID;

@SideOnly(Side.CLIENT)
public class MemoHVs extends MemoH
{
//    public int max_bones;
    public int bone;
    public float[] bind_pose_float_array;
    public int[][] back_bone_2d_int_array;

    public MemoHVs(String[] shader_string_array)
    {
        String folder_path = ID + "/" + shader_string_array[0] + "/shader/" + NaliConfig.SHADER.gl_shading_language_version + "/";

//        super(shader_string_array);
//    }
//
//    @Override
//    public StringBuilder get(String[] shader_string_array, String folder_path/*, byte shader_state*/)
//    {
//        folder_path = shader_string_array[4];
//        byte shader_state = Byte.parseByte(shader_string_array[1]);

//        String model_folder_path = folder_path + "/Models/" + shader_string_array[5];
//        String model_folder_path = Reference.MOD_ID + "/" + shader_string_array[4] + "/Model/" + shader_string_array[5];
        String model_folder_path = ID + "/" + shader_string_array[5] + "/model/" + shader_string_array[6];
        String frame_string = "/frame/";

//        float[] bind_poses_float_array = FileDataReader.getFloatArray(model_folder_path + frame_string + "BindPoses");
        this.bind_pose_float_array = FileDataReader.getFloatArray(model_folder_path + frame_string + "bindpose.bin");

        this.bone = this.bind_pose_float_array.length / 16;
//        this.max_bones = this.bind_poses_float_array.length / 16;
        int[][] bone_2d_int_array = new int[this.bone][];
        for (int i = 0; i < this.bone; ++i)
        {
            bone_2d_int_array[i] = FileDataReader.getIntArray(model_folder_path + frame_string + "bone/" + i + ".bin");
        }

        StringBuilder stringbuilder = new StringBuilder();
//        this.vert_shader = stringbuilder;
//        StringReader.append(stringbuilder, folder_path + "/Shaders/" + GL_SHADING_LANGUAGE_VERSION + "/Vertex" + shader_state + 0);
//        String file_string = /*Reference.MOD_ID + "/" + */folder_path + "/Vertex" + shader_state;
        byte[] byte_array = shader_string_array[3].getBytes();
        String file_string = /*Reference.MOD_ID + "/" + */folder_path + "vert/s/" + new String(byte_array, 1, byte_array.length - 1);
        StringReader.append(stringbuilder, file_string + "0.vert");
//        stringbuilder.append(getVertexHeaderShaderString());

        int bindpose_size = this.bind_pose_float_array.length / 16;
        stringbuilder.append("mat4 bindpose[").append(String.valueOf(bindpose_size)).append("] = mat4[](");

        for (int j = 0; j < bindpose_size; ++j)
        {
            stringbuilder.append("mat4(");

            int bindpose_index = (j + 1) * 16;
            for (int b = j * 16; b < bindpose_index; ++b)
            {
                stringbuilder.append(String.valueOf(this.bind_pose_float_array[b]));

                if (b < bindpose_index - 1)
                {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(")");

            if (j < bindpose_size - 1)
            {
                stringbuilder.append(", ");
            }
        }

        stringbuilder.append(");\n");

        stringbuilder.append("mat4 invbindpose[").append(String.valueOf(bindpose_size)).append("] = mat4[](");

        for (int j = 0; j < bindpose_size; ++j)
        {
            stringbuilder.append("mat4(");

            M4x4.inverse(this.bind_pose_float_array, j * 16);
            int bindpose_index = (j + 1) * 16;
            for (int b = j * 16; b < bindpose_index; ++b)
            {
                stringbuilder.append(String.valueOf(this.bind_pose_float_array[b]));

                if (b < bindpose_index - 1)
                {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(")");

            if (j < bindpose_size - 1)
            {
                stringbuilder.append(", ");
            }
        }

        stringbuilder.append(");\n");

//        for (int i = 0; i < max_bones; ++i)
//        {
//            stringbuilder.append("uniform mat4 frame").append(StringReader.convertNumberToLetter(i)).append(";\n");
//        }
        stringbuilder.append("uniform mat4 frame[").append(String.valueOf(this.bone)).append("];\n");

        StringReader.append(stringbuilder, file_string + "1.vert");

//        int[][] back_bones_2d_int_array = new int[this.max_bones][];
        this.back_bone_2d_int_array = new int[this.bone][];

        for (int j = 0; j < this.bone; ++j)
        {
            int[] bone_int_array = bone_2d_int_array[j];
            this.back_bone_2d_int_array[j] = new int[bone_int_array.length];

            int b_index = 0;
            for (int b = bone_int_array.length - 1; b > -1; --b)
            {
                int[] b_bones = this.back_bone_2d_int_array[j];
                b_bones[b_index++] = bone_int_array[b];
            }
        }

        for (int j = 0; j < bone_2d_int_array.length; ++j)
        {
            int[] back_bone_int_array = this.back_bone_2d_int_array[j];
            String head = "else if";

            if (j == 0)
            {
                head = "if";
            }

            stringbuilder.append(head).append(" (j == ").append(String.valueOf(j)).append(")\n{\n");
            for (int bone : back_bone_int_array)
            {
                stringbuilder.append("temp_vertex_vec4 *= bindpose[").append(String.valueOf(bone)).append("];\n");
//                stringbuilder.append("temp_vertex_vec4 *= frame").append(StringReader.convertNumberToLetter(bone)).append(";\n");
                stringbuilder.append("temp_vertex_vec4 *= frame[").append(String.valueOf(bone)).append("];\n");
                stringbuilder.append("temp_vertex_vec4 *= invbindpose[").append(String.valueOf(bone)).append("];\n");
                stringbuilder.append("temp_normal_vec4 *= bindpose[").append(String.valueOf(bone)).append("];\n");
//                stringbuilder.append("temp_normal_vec4 *= frame").append(StringReader.convertNumberToLetter(bone)).append(";\n");
                stringbuilder.append("temp_normal_vec4 *= frame[").append(String.valueOf(bone)).append("];\n");
                stringbuilder.append("temp_normal_vec4 *= invbindpose[").append(String.valueOf(bone)).append("];\n");
//                stringbuilder.append("temp_normal_vec4 *= temp_vertex_vec4;\n");
            }
            stringbuilder.append("}\n");
        }

        StringReader.append(stringbuilder, file_string + "2.vert");

        this.shader = genShader(getFrom(stringbuilder), OpenGlHelper.GL_VERTEX_SHADER);
    }
}
