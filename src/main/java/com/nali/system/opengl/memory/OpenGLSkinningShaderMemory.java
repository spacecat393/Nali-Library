package com.nali.system.opengl.memory;

import com.nali.math.M4x4;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OpenGLSkinningShaderMemory extends OpenGLObjectShaderMemory
{
    public int max_bones;
    public float[] bind_poses_float_array;
    public int[][] back_bones_2d_int_array;
//    public int[][] bones_2d_int_array;

    public OpenGLSkinningShaderMemory(String[] shader_string_array, String folder_path)
    {
        super(shader_string_array, folder_path);
    }

    @Override
    public void readVertShader(String[] shader_string_array, String folder_path)
    {
        byte shader_state = (byte)Integer.parseInt(shader_string_array[1]);

        String model_folder_path = folder_path + "Models/" + shader_string_array[2];
        String animation_string = "/Animation/";

//        float[] bind_poses_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "BindPoses");
        this.bind_poses_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "BindPoses");

//        int max_bones = this.bind_poses_float_array.length / 16;
        this.max_bones = this.bind_poses_float_array.length / 16;
        int[][] bones_2d_int_array = new int[max_bones][];
        for (int i = 0; i < this.max_bones; ++i)
        {
            bones_2d_int_array[i] = FileDataReader.getIntArray(model_folder_path + animation_string + "Bones/" + i);
        }

        StringBuilder vertex_stringbuilder = new StringBuilder();
        this.vert_shader = vertex_stringbuilder;
        StringReader.append(vertex_stringbuilder, folder_path + "Shaders/Vertex" + shader_state + 0);
//        vertex_stringbuilder.append(getVertexHeaderShaderString());

        int bindposes_size = this.bind_poses_float_array.length / 16;
        vertex_stringbuilder.append("mat4 bindposes[").append(String.valueOf(bindposes_size)).append("] = mat4[](");

        for (int j = 0; j < bindposes_size; ++j)
        {
            vertex_stringbuilder.append("mat4(");

            int bindposes_index = (j + 1) * 16;
            for (int b = j * 16; b < bindposes_index; ++b)
            {
                vertex_stringbuilder.append(String.valueOf(this.bind_poses_float_array[b]));

                if (b < bindposes_index - 1)
                {
                    vertex_stringbuilder.append(", ");
                }
            }

            vertex_stringbuilder.append(")");

            if (j < bindposes_size - 1)
            {
                vertex_stringbuilder.append(", ");
            }
        }

        vertex_stringbuilder.append(");\n");

        vertex_stringbuilder.append("mat4 inverse_bindposes[").append(String.valueOf(bindposes_size)).append("] = mat4[](");

        for (int j = 0; j < bindposes_size; ++j)
        {
            vertex_stringbuilder.append("mat4(");

            M4x4.inverse(this.bind_poses_float_array, j * 16);
            int bindposes_index = (j + 1) * 16;
            for (int b = j * 16; b < bindposes_index; ++b)
            {
                vertex_stringbuilder.append(String.valueOf(this.bind_poses_float_array[b]));

                if (b < bindposes_index - 1)
                {
                    vertex_stringbuilder.append(", ");
                }
            }

            vertex_stringbuilder.append(")");

            if (j < bindposes_size - 1)
            {
                vertex_stringbuilder.append(", ");
            }
        }

        vertex_stringbuilder.append(");\n");

//        for (int i = 0; i < max_bones; ++i)
//        {
//            vertex_stringbuilder.append("uniform mat4 animation").append(StringReader.convertNumberToLetter(i)).append(";\n");
//        }
        vertex_stringbuilder.append("uniform mat4 animation[").append(String.valueOf(this.max_bones)).append("];\n");

        StringReader.append(vertex_stringbuilder, folder_path + "Shaders/Vertex" + shader_state + 1);

//        int[][] back_bones_2d_int_array = new int[this.max_bones][];
        this.back_bones_2d_int_array = new int[this.max_bones][];

        for (int j = 0; j < this.max_bones; ++j)
        {
            int[] bones = bones_2d_int_array[j];
            this.back_bones_2d_int_array[j] = new int[bones.length];

            int b_index = 0;
            for (int b = bones.length - 1; b > -1; --b)
            {
                int[] b_bones = this.back_bones_2d_int_array[j];
                b_bones[b_index++] = bones[b];
            }
        }

        for (int j = 0; j < bones_2d_int_array.length; ++j)
        {
            int[] bones = this.back_bones_2d_int_array[j];
            String head = "else if";

            if (j == 0)
            {
                head = "if";
            }

            vertex_stringbuilder.append(head).append(" (joint == ").append(String.valueOf(j)).append(")\n{\n");
            for (int bone : bones)
            {
                vertex_stringbuilder.append("temp_vertices_vec4 *= bindposes[").append(String.valueOf(bone)).append("];\n");
//                vertex_stringbuilder.append("temp_vertices_vec4 *= animation").append(StringReader.convertNumberToLetter(bone)).append(";\n");
                vertex_stringbuilder.append("temp_vertices_vec4 *= animation[").append(String.valueOf(bone)).append("];\n");
                vertex_stringbuilder.append("temp_vertices_vec4 *= inverse_bindposes[").append(String.valueOf(bone)).append("];\n");
                vertex_stringbuilder.append("temp_normals_vec4 *= bindposes[").append(String.valueOf(bone)).append("];\n");
//                vertex_stringbuilder.append("temp_normals_vec4 *= animation").append(StringReader.convertNumberToLetter(bone)).append(";\n");
                vertex_stringbuilder.append("temp_normals_vec4 *= animation[").append(String.valueOf(bone)).append("];\n");
                vertex_stringbuilder.append("temp_normals_vec4 *= inverse_bindposes[").append(String.valueOf(bone)).append("];\n");
//                vertex_stringbuilder.append("temp_normals_vec4 *= temp_vertices_vec4;\n");
            }
            vertex_stringbuilder.append("}\n");
        }

        StringReader.append(vertex_stringbuilder, folder_path + "Shaders/Vertex" + shader_state + 2);
    }

//    @Override
//    public void createBuffer(String[] shader_string_array, String folder_path, int max_bones)
//    {
//        max_bones = new File(folder_path + "Models/" + shader_string_array[2] + "/Animation/Bones").listFiles().length;
//        super.createBuffer(shader_string_array, folder_path, max_bones);
//
//        int start_index = this.uniformlocation_int_array.length - max_bones;
//        for (int i = 0; i < max_bones; ++i)
//        {
//            this.uniformlocation_int_array[start_index + i] = GL20.glGetUniformLocation(this.program, "animation" + StringReader.convertNumberToLetter(i));
//        }
//    }

//    @Override
//    public int getMaxBones()
//    {
//        return this.max_bones;
//    }

//    public static String getVertexHeaderShaderString()
//    {
//        return "#version 120\n" +
//                "precision highp float;\n" +
//                "attribute vec3 vertices;\n" +
//                "attribute vec2 texcoord;\n" +
//                "attribute vec4 joints;\n" +
//                "attribute vec4 weights;\n" +
//                "attribute vec3 normals;\n";
//    }
}
