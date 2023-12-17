package com.nali.system.opengl.memory;

import com.nali.Nali;
import com.nali.config.MyConfig;
import com.nali.system.DataLoader;
import com.nali.system.StringReader;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.shader.OpenGLSkinningShader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public class OpenGLShaderMemory
{
    public static void set(DataLoader dataloader, String[] model_string_array, String folder_path, String shaders_folder_path, boolean vulkan_shader, int object_index)
    {
        String model_folder_path = folder_path + "Models/" + model_string_array[0];
        String animation_string = "/Animation/";

        byte shader_state = (byte)Integer.parseInt(model_string_array[1]);

        boolean have_animation_folder = new File(model_folder_path + animation_string).isDirectory();

        Object[] object_array = new Object[have_animation_folder ? 5 : 4];
        object_array[0] = new Object[5];

        if (new File(model_folder_path + "/Uniform").exists())
        {
            Object[] uniform_object_array = FileDataReader.getMixXStringArray(model_folder_path + "/Uniform");
            object_array[1] = uniform_object_array;
        }

        if (have_animation_folder)
        {
            ((Object[])object_array[0])[4] = new Object[5];
            float[] bind_poses_float_array = FileDataReader.getFloatArray(model_folder_path + animation_string + "BindPoses");

            int bones_file_array_length = new File(model_folder_path + animation_string + "Bones").listFiles().length;
            Object[] bones_object_array = new Object[bones_file_array_length];
            for (int i = 0; i < bones_file_array_length; ++i)
            {
                bones_object_array[i] = FileDataReader.getIntArray(model_folder_path + animation_string + "Bones/" + i);
            }

            if (!MyConfig.USING_MULTI_UNIFORM)
            {
                int max_size = bind_poses_float_array.length;
                if (DataLoader.MAX_OPENGL_FLOATBUFFER_SIZE < max_size)
                {
                    DataLoader.MAX_OPENGL_FLOATBUFFER_SIZE = max_size;
                }
            }

            int max_bones = bind_poses_float_array.length / 16;
            object_array[4] = max_bones;

            try
            {
                StringBuilder vertex_stringbuilder = new StringBuilder();
                object_array[2] = vertex_stringbuilder;
                vertex_stringbuilder.append(OpenGLSkinningShader.getVertexHeaderShaderString(object_array, vulkan_shader));

//                for (int j = 0; j < bones_file_array_length; ++j)
//                {
//                    int[] bones = (int[])bones_object_array[j];
//
//                    vertex_stringbuilder.append("int bones" + StringReader.convertNumberToLetter(j) + "[" + bones.length + "]" + " = int[](");
//
//                    for (int b = bones.length - 1; b > -1; --b)
//                    {
//                        vertex_stringbuilder.append("" + bones[b]);
//
//                        if (b > 0)
//                        {
//                            vertex_stringbuilder.append(", ");
//                        }
//                    }
//
//                    vertex_stringbuilder.append(");\n");
//                }

                // float[] bones_float_array = (float[])object_array[3];
                // int bones_size = bones_float_array.length / 16;
                // vertex_stringbuilder.append("mat4 bones" + "[" + bones_size + "]" + " = mat4[](");

                // for (int j = 0; j < bones_size; ++j)
                // {
                //     vertex_stringbuilder.append("mat4(");

                //     int bindposes_index = (j + 1) * 16;
                //     for (int b = j * 16; b < bindposes_index; ++b)
                //     {
                //         vertex_stringbuilder.append("" + bones_float_array[b]);

                //         if (b < bindposes_index - 1)
                //         {
                //             vertex_stringbuilder.append(", ");
                //         }
                //     }

                //     vertex_stringbuilder.append(")");

                //     if (j < bones_size - 1)
                //     {
                //         vertex_stringbuilder.append(", ");
                //     }
                // }

                // vertex_stringbuilder.append(");\n");
                // object_array[3] = null;

                int bindposes_size = bind_poses_float_array.length / 16;
                vertex_stringbuilder.append("mat4 bindposes[").append(String.valueOf(bindposes_size)).append("] = mat4[](");

                for (int j = 0; j < bindposes_size; ++j)
                {
                    vertex_stringbuilder.append("mat4(");

                    int bindposes_index = (j + 1) * 16;
                    for (int b = j * 16; b < bindposes_index; ++b)
                    {
                        vertex_stringbuilder.append("" + bind_poses_float_array[b]);

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

                // for (int i = 0; i < bindposes_float_array.length; i += 16)
                // {
                //     M4x4.inverse(bindposes_float_array, i);
                // }

                if (vulkan_shader)
                {
                    vertex_stringbuilder.append("layout(binding = 0) uniform ObjectUniform\n{\n");
                }

                boolean multi_uniform = MyConfig.USING_MULTI_UNIFORM;

                if (multi_uniform)
                {
                    for (int i = 0; i < max_bones; ++i)
                    {
                        vertex_stringbuilder.append("uniform mat4 animation").append(StringReader.convertNumberToLetter(i)).append(";\n");
                    }
                }
                else
                {
                    vertex_stringbuilder.append("uniform mat4[").append(String.valueOf(max_bones)).append("] animation;\n");
                }

                String line = null;
                BufferedReader vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 0));
                while ((line = vertex_bufferedreader.readLine()) != null)
                {
                    vertex_stringbuilder.append(line).append("\n");
                }
                vertex_bufferedreader.close();

                //120 error
//                if (multi_uniform)
//                {
//                    vertex_stringbuilder.append("\nmat4 getAnimation(int b)\n{\n");
//                    for (int j = 0; j < bindposes_size; ++j)
//                    {
//                        String head = "else if";
//
//                        if (j == 0)
//                        {
//                            head = "if";
//                        }
//
//                        if (vulkan_shader)
//                        {
//                            vertex_stringbuilder.append(head + " (b == " + j + ")\n{return objectuniform.animation" + j + ";\n}\n");
//                        }
//                        else
//                        {
//                            vertex_stringbuilder.append(head + " (b == " + j + ")\n{return animation" + StringReader.convertNumberToLetter(j) + ";\n}\n");
//                        }
//                    }
//                    vertex_stringbuilder.append("return mat4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);\n}\n");
//                }
                //end

                //work on 430
//                String transform_vec_string = "\nvec4 transformVec(int b, vec4 vertices_vec4)\n" +
//                "{\n" +
//                "mat4 skinning_mat4 = bindposes[b];\n" +
//                "vertices_vec4 *= skinning_mat4;\n";
//                // temp_normals_vec4 *= skinning_mat4;
//
//                if (multi_uniform)
//                {
//                    transform_vec_string += "skinning_mat4 *= getAnimation(b);\n";
//                }
//                else
//                {
//                    if (vulkan_shader)
//                    {
//                        transform_vec_string += "skinning_mat4 *= objectuniform.animation[b];\n";
//                    }
//                    else
//                    {
//                        transform_vec_string += "skinning_mat4 *= animation[b];\n";
//                    }
//                }
//
//                // skinning_mat4 *= animation[b];
//                transform_vec_string += "skinning_mat4 = inverse(skinning_mat4);\n" +
//                "vertices_vec4 *= skinning_mat4;\n" +
//                // temp_normals_vec4 *= skinning_mat4;
//
//                "return vertices_vec4;\n" +
//                "}\n";
//
//                vertex_stringbuilder.append(transform_vec_string);
                //end
                String transform_vec_string = "\nvec4 transformVec(mat4 bindpose, vec4 vertices_vec4, mat4 animation)\n" +
                "{\n" +
                "mat4 skinning_mat4 = bindpose;\n" +
                "vertices_vec4 *= skinning_mat4;\n";
                // temp_normals_vec4 *= skinning_mat4;

                if (multi_uniform)
                {
                    transform_vec_string += "skinning_mat4 *= animation;\n";
                }
                else
                {
                    if (vulkan_shader)
                    {
                        transform_vec_string += "skinning_mat4 *= objectuniform.animation[b];\n";
                    }
                    else
                    {
                        transform_vec_string += "skinning_mat4 *= animation[b];\n";
                    }
                }

                // skinning_mat4 *= animation[b];
                transform_vec_string += "skinning_mat4 = inverse(skinning_mat4);\n" +
                "vertices_vec4 *= skinning_mat4;\n" +
                // temp_normals_vec4 *= skinning_mat4;
                "return vertices_vec4;\n" +
                "}\n";
                vertex_stringbuilder.append(transform_vec_string);

                vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 1));
                while ((line = vertex_bufferedreader.readLine()) != null)
                {
                    vertex_stringbuilder.append(line).append("\n");
                }
                vertex_bufferedreader.close();

                // if (shader_state != 2 && shader_state != 4)
                // {
                //     vertex_stringbuilder.append("for (int i = 0; i < " + max_joints + "; ++i)\n{\n");
                // }
                // vertex_stringbuilder.append("vec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
                //     vertex_stringbuilder.append("if (joints[i] != -1)\n{\nvec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
                // }
                // else
                // {
                //     vertex_stringbuilder.append("if (joints != -1)\n{\nvec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
                // }

                // vertex_stringbuilder.append("int joint = " + (max_joints == 1 || max_joints > 4 ? "joints" : "joints[i]") + ";\n");
                // vertex_stringbuilder.append("float weight = " + (max_joints == 1 || max_joints > 4 ? "weights" : "weights[i]") + ";\n");

//                vertex_stringbuilder.append("float please_work = weights[3]");
//                if (multi_uniform)
//                {
//                    for (int i = 0; i < bindposes_size; ++i)
//                    {
//                        vertex_stringbuilder.append(" + animation" + StringReader.convertNumberToLetter(i) + "[3].y");
//                    }
//                }
//                vertex_stringbuilder.append(";\n");

//                vertex_stringbuilder.append("for (int i = 0; i < 4; ++i)\n" +
//                        "    {\n" +
//                        "        vec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
//
////                vertex_stringbuilder.append("int joint = int(joints[i]) + int(please_work);\n");
//                vertex_stringbuilder.append("int joint = int(joints[0]);\n");
//                vertex_stringbuilder.append("float weight = weights[0];\n");

                Object[] back_bones = new Object[bones_file_array_length];

                for (int j = 0; j < bones_file_array_length; ++j)
                {
                    int[] bones = (int[])bones_object_array[j];
                    back_bones[j] = new int[bones.length];

                    int b_index = 0;
                    for (int b = bones.length - 1; b > -1; --b)
                    {
                        int[] b_bones = (int[])back_bones[j];
                        b_bones[b_index++] = bones[b];
                    }
                }

                for (int j = 0; j < bones_object_array.length; ++j)
                {
                    int[] bones = (int[])back_bones[j];
                    String head = "else if";

                    if (j == 0)
                    {
                        head = "if";
                    }

//                    vertex_stringbuilder.append(head + " (joint == " + j + ")\n{\nfor (int b = 0; b < " + bones.length + "; ++b)\n{\ntemp_vertices_vec4 = transformVec(bones" + StringReader.convertNumberToLetter(j) + "[b], temp_vertices_vec4);\n}\n}\n");

                    vertex_stringbuilder.append(head).append(" (joint == ").append(String.valueOf(j)).append(")\n{\n");
                    for (int b = 0; b < bones.length; ++b)
                    {
                        vertex_stringbuilder.append("temp_vertices_vec4 = transformVec(bindposes[").append(String.valueOf(bones[b])).append("], temp_vertices_vec4, animation").append(StringReader.convertNumberToLetter(bones[b])).append(");\n");
                        vertex_stringbuilder.append("temp_normals_vec4 *= temp_vertices_vec4;\n");
                    }
                    vertex_stringbuilder.append("}\n");
                    //

//                    vertex_stringbuilder.append(head + " (joint == " + j + ")\n{\n");
//                    for (int b = 0; b < bones.length; ++b)
//                    {
//                        String transform_vec_string = "{\nmat4 skinning_mat4 = bindposes[bones" + StringReader.convertNumberToLetter(j) + "[" + b + "]];\n" +
//                                "temp_vertices_vec4 *= skinning_mat4;\n";
//                        // temp_normals_vec4 *= skinning_mat4;
//
//                        if (multi_uniform)
//                        {
//                            transform_vec_string += "skinning_mat4 *= animation" + StringReader.convertNumberToLetter(bones[b]) + ";\n";
//                        }
//                        else
//                        {
//                            if (vulkan_shader)
//                            {
//                                transform_vec_string += "skinning_mat4 *= objectuniform.animation[b];\n";
//                            }
//                            else
//                            {
//                                transform_vec_string += "skinning_mat4 *= animation[b];\n";
//                            }
//                        }
//
//                        // skinning_mat4 *= animation[b];
//                        transform_vec_string += "skinning_mat4 = inverse(skinning_mat4);\n" +
//                                "temp_vertices_vec4 *= skinning_mat4;\n}\n";
//                                // temp_normals_vec4 *= skinning_mat4;
//                        vertex_stringbuilder.append(transform_vec_string);
//                    }
//                    vertex_stringbuilder.append("}\n");

                    //
//                    vertex_stringbuilder.append(head + " (joint == " + j + ")\n{\nfor (int b = 0; b < " + bones.length + "; ++b)\n{\n");
//                    String transform_vec_string = "mat4 skinning_mat4 = bindposes[b];\n" +
//                            "temp_vertices_vec4 *= skinning_mat4;\n";
//                    // temp_normals_vec4 *= skinning_mat4;
//
//                    if (multi_uniform)
//                    {
//                        transform_vec_string += "skinning_mat4 *= getAnimation(b);\n";
//                    }
//                    else
//                    {
//                        if (vulkan_shader)
//                        {
//                            transform_vec_string += "skinning_mat4 *= objectuniform.animation[b];\n";
//                        }
//                        else
//                        {
//                            transform_vec_string += "skinning_mat4 *= animation[b];\n";
//                        }
//                    }
//
//                    // skinning_mat4 *= animation[b];
//                    transform_vec_string += "skinning_mat4 = inverse(skinning_mat4);\n" +
//                            "temp_vertices_vec4 *= skinning_mat4;\n" +
//                            // temp_normals_vec4 *= skinning_mat4;
//                            "\n}\n}\n";
//
//                    vertex_stringbuilder.append(transform_vec_string);
//                    //
                }

                vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 2));
                while ((line = vertex_bufferedreader.readLine()) != null)
                {
                    vertex_stringbuilder.append(line).append("\n");
                }
                vertex_bufferedreader.close();
            }
            catch (IOException ioexception)
            {
                Nali.LOGGER.error(ioexception.getMessage(), ioexception);
            }
        }
        else
        {
            if (shader_state == 0)
            {
                return;
            }

            ((Object[])object_array[0])[4] = new Object[3];
            object_array[2] = new StringBuilder();
            try (BufferedReader bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state)))
            {
                String line = null;
                while ((line = bufferedreader.readLine()) != null)
                {
                    ((StringBuilder)object_array[2]).append(line).append("\n");
                }
                bufferedreader.close();
            }
            catch (IOException ioexception)
            {
                Nali.LOGGER.error(ioexception.getMessage(), ioexception);
            }
        }

        object_array[3] = new StringBuilder();
        try (BufferedReader bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Fragment" + shader_state)))
        {
            String line = null;
            while ((line = bufferedreader.readLine()) != null)
            {
                ((StringBuilder)object_array[3]).append(line).append("\n");
            }
            bufferedreader.close();
        }
        catch (IOException ioexception)
        {
            Nali.LOGGER.error(ioexception.getMessage(), ioexception);
        }

        dataloader.shader_object_array[object_index] = object_array;
    }
}
