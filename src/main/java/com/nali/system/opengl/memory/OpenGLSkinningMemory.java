package com.nali.system.opengl.memory;

import com.nali.system.DataLoader;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.buffer.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OpenGLSkinningMemory
{
    public static void set(DataLoader dataloader, String[] model_string_array, String folder_path, int object_index)
    {
        String model_folder_path = folder_path + "Models/" + model_string_array[0];
        // String animation_string = "/Animation/";
        // String shaders_folder_path = "/Shaders/";

        byte texture_state = (byte)Integer.parseInt(model_string_array[1]);
        int shader_id = Integer.parseInt(model_string_array[2]);
        byte culling = (byte)Integer.parseInt(model_string_array[3]);
//        byte depth_mask = (byte)Integer.parseInt(model_string_array[4]);
        byte max_joints = (byte)Integer.parseInt(model_string_array[4]);

        Object[] object_array = new Object[9];
        object_array[0] = FileDataReader.getIntArray(model_folder_path + "/Index");
        object_array[1] = FileDataReader.getFloatArray(model_folder_path + "/Vertices");
        object_array[2] = FileDataReader.getFloatArray(model_folder_path + "/Texcoord");
        // object_array[3] = new ArrayList<float[]>();
        // FileDataReader.getXFloatArrayList(model_folder_path + "/Normals", object_array[3], 3);
        // FileDataReader.getFloatArray(model_folder_path + "/BonesM4x4", object_array, 3);

        // object_array[5] = new StringBuilder();
        // try (BufferedReader bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Fragment" + shader_state)))
        // {
        //     String line = null;
        //     while ((line = bufferedreader.readLine()) != null)
        //     {
        //         ((StringBuilder)object_array[5]).append(line + "\n");
        //     }
        //     bufferedreader.close();
        // }
        // catch (IOException ioexception)
        // {
        //     Main.LOGGER.error(ioexception.getMessage(), ioexception);
        // }

        object_array[3] = ((int[])object_array[0]).length;
//        object_array[4] = (byte)(culling + depth_mask * 2);
        object_array[4] = culling;
        object_array[5] = texture_state;
        object_array[6] = new Object[6];
        ((Object[])object_array[6])[0] = shader_id;
        // object_array[10] = new Object[13];
//        float[] joints_float_array = FileDataReader.getFloatArray(model_folder_path + "/Joints");
        int[] joints_int_array = FileDataReader.getIntArray(model_folder_path + "/Joints");
        float[] weights_float_array = FileDataReader.getFloatArray(model_folder_path + "/Weights");
        // object_array[13] = FileDataReader.getFloatArray(model_folder_path + "/BindPoses");

        if (max_joints == 4)
        {
//            object_array[7] = joints_float_array;
            object_array[7] = joints_int_array;
            object_array[8] = weights_float_array;
        }
        else
        {
            int step = 4 - max_joints;
//            int joints_float_array_length = joints_float_array.length;
            int joints_int_array_length = joints_int_array.length;
            int new_size = joints_int_array_length + (joints_int_array_length / max_joints) * step;
//            float[] temp_joints_float_array = new float[new_size];
            int[] temp_joints_int_array = new int[new_size];
            float[] temp_weights_float_array = new float[new_size];
            int index = 0;
            int temp_index = 0;
//            for (int x = 0; x < temp_joints_float_array.length; x += 4)
            for (int x = 0; x < temp_joints_int_array.length; x += 4)
            {
                for (int y = 0; y < max_joints; ++y)
                {
//                    temp_joints_float_array[temp_index] = joints_float_array[index];
                    temp_joints_int_array[temp_index] = joints_int_array[index];
                    temp_weights_float_array[temp_index++] = weights_float_array[index++];
                }

                for (int y = 0; y < step; ++y)
                {
//                    temp_joints_float_array[temp_index] = -1;
                    temp_joints_int_array[temp_index] = -1;
                    temp_weights_float_array[temp_index++] = 0.0F;
                }
            }

//            object_array[7] = temp_joints_float_array;
            object_array[7] = temp_joints_int_array;
            object_array[8] = temp_weights_float_array;
        }

//        File[] file_array = new File(model_folder_path + "/Bones").listFiles();
        // int bones_file_array_length = new File(model_folder_path + "/Bones").listFiles().length;
        // Object[] bones_object_array = new Object[bones_file_array_length];
        // for (int i = 0; i < bones_file_array_length; ++i)
        // {
        //     bones_object_array[i] = FileDataReader.getIntArray(model_folder_path + "/Bones/" + i);
        // }
        // object_array[14] = bones_object_array;

        // object_array[15] = FileDataReader.getIntArray(model_folder_path + animation_string + "/SkinningBones");
        // object_array[16] = FileDataReader.getIntArray(model_folder_path + animation_string + "/AnimationBones");
        // object_array[17] = new ArrayList<M4x4>();
        // FileDataReader.getM4x4ArrayList(model_folder_path + "/VisualBones", object_array[17]);
        // object_array[18] = max_joints;

        // object_array[4] = new StringBuilder();
        // StringBuilder vertex_stringbuilder = (StringBuilder)object_array[4];

        // if (shader_state == 2)
        // {
        //     ArrayList<float[]> joints_float_array_arraylist = new ArrayList<float[]>();
        //     object_array[11] = joints_float_array_arraylist;
        //     FileDataReader.getXFloatArrayList(model_folder_path + "/Joints", object_array[11], max_joints);

        //     try
        //     {
        //         vertex_stringbuilder.append(OpenGLSkinningShader.getLowVertexHeaderShaderString(object_array)).append("\n");

        //         String line = null;
        //         BufferedReader vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 0));
        //         while ((line = vertex_bufferedreader.readLine()) != null)
        //         {
        //             vertex_stringbuilder.append(line).append("\n");
        //         }
        //         vertex_bufferedreader.close();

        //         switch (max_joints)
        //         {
        //             case 2:
        //             {
        //                 vertex_stringbuilder.append("gl_Position = ((animation_transform[int(joints.x)] + animation_transform[int(joints.y)]) * (vec4(vertices, 1.0) * (weights.x + weights.y))) * world * view * project;\n");

        //                 break;
        //             }
        //             case 3:
        //             {
        //                 vertex_stringbuilder.append("gl_Position = ((animation_transform[int(joints.x)] + animation_transform[int(joints.y)] + animation_transform[int(joints.z)]) * (vec4(vertices, 1.0) * (weights.x + weights.y + weights.z))) * world * view * project;\n");

        //                 break;
        //             }
        //             case 4:
        //             {
        //                 vertex_stringbuilder.append("gl_Position = ((animation_transform[int(joints.x)] + animation_transform[int(joints.y)] + animation_transform[int(joints.z)] + animation_transform[int(joints.w)]) * (vec4(vertices, 1.0) * (weights.x + weights.y + weights.z + weights.w))) * world * view * project;\n");

        //                 break;
        //             }
        //             default:
        //             {
        //                 vertex_stringbuilder.append("gl_Position = (animation_transform[int(joints)] * (vec4(vertices, 1.0) * weights)) * world * view * project;\n");
        //             }
        //         }

        //         vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 1));
        //         while ((line = vertex_bufferedreader.readLine()) != null)
        //         {
        //             vertex_stringbuilder.append(line).append("\n");
        //         }
        //         vertex_bufferedreader.close();
        //     }
        //     catch (IOException ioexception)
        //     {
        //         Summer.LOGGER.log(Level.SEVERE, ioexception.getMessage(), ioexception);
        //     }

        //     // for (int j = 0; j < bindposes_m4x4_arraylist.size(); ++j)
        //     // {
        //     //     bindposes_m4x4_arraylist.get(j).inverse();
        //     // }

        //     for (int j = 0; j < vertices_float_array_arraylist.size(); ++j)
        //     {
        //         float[] joints_float_array = joints_float_array_arraylist.get(j);

        //         for (int p = 0; p < joints_float_array.length; ++p)
        //         {
        //             if ((int)joints_float_array[p] != -1)
        //             {
        //                 int[] bones = bones_int_array_arraylist.get((int)joints_float_array[p]);
        //                 M4x4 m4x4 = new M4x4();

        //                 for (int l = 0; l < bones.length; ++l)
        //                 {
        //                     // M4x4.add(bindposes_m4x4_arraylist.get(bones[l]).mat, float_array, 0, 0);
        //                     m4x4.multiply(bindposes_m4x4_arraylist.get(bones[l]).mat);
        //                 }
        //                 m4x4.multiply(vertices_float_array_arraylist.get(j), 1.0F);
        //             }
        //             else
        //             {
        //                 break;
        //             }
        //         }
        //     }

        //     // for (int j = 0; j < bindposes_m4x4_arraylist.size(); ++j)
        //     // {
        //     //     bindposes_m4x4_arraylist.get(j).inverse();
        //     // }

        //     // ArrayList<M4x4> new_bindposes_m4x4_arraylist = new ArrayList<M4x4>();

        //     // for (int j = 0; j < bones_int_array_arraylist.size(); ++j)
        //     // {
        //     //     new_bindposes_m4x4_arraylist.add(new M4x4());

        //     //     int[] bones = bones_int_array_arraylist.get(j);

        //     //     for (int l = 0; l < bones.length; ++l)
        //     //     {
        //     //         new_bindposes_m4x4_arraylist.get(j).multiply(bindposes_m4x4_arraylist.get(bones[l]).mat);
        //     //     }

        //     //     // for (int l = bones.length - 1; l > -1; --l)
        //     //     // {
        //     //     //     new_bindposes_m4x4_arraylist.get(j).multiply(bindposes_m4x4_arraylist.get(bones[l]).mat);
        //     //     // }
        //     // }

        //     // object_array[13] = new_bindposes_m4x4_arraylist;

        //     OpenGLBuffer.setFloatBuffer(object_array, 11, max_joints, true);
        // }
        // else
        // {
            // object_array[11] = FileDataReader.getIntArray(model_folder_path + "/Joints");

            // for (int j = 0; j < vertices_float_array_arraylist.size(); ++j)
            // {
            //     int[] joints_int_array = joints_int_array_arraylist.get(j);

            //     for (int p = 0; p < joints_int_array.length; ++p)
            //     {
            //         if ((int)joints_int_array[p] != -1)
            //         {
            //             int[] bones = bones_int_array_arraylist.get((int)joints_int_array[p]);
            //             M4x4 m4x4 = new M4x4();

            //             for (int l = 0; l < bones.length; ++l)
            //             {
            //                 m4x4.multiply(bindposes_m4x4_arraylist.get(bones[l]).mat);
            //             }
            //             m4x4.multiply(vertices_float_array_arraylist.get(j), 1.0F);
            //         }
            //         else
            //         {
            //             break;
            //         }
            //     }
            // }

            // try
            // {
            //     vertex_stringbuilder.append(OpenGLSkinningShader.getVertexHeaderShaderString(object_array) + "\n");

            //     for (int j = 0; j < bones_file_array_length; ++j)
            //     {
            //         int[] bones = (int[])bones_object_array[j];

            //         vertex_stringbuilder.append("int bones" + j + "[" + bones.length + "]" + " = int[](");

            //         for (int b = bones.length - 1; b > -1; --b)
            //         {
            //             vertex_stringbuilder.append("" + bones[b]);

            //             if (b > 0)
            //             {
            //                 vertex_stringbuilder.append(", ");
            //             }
            //         }

            //         vertex_stringbuilder.append(");\n");
            //     }

            //     // float[] bones_float_array = (float[])object_array[3];
            //     // int bones_size = bones_float_array.length / 16;
            //     // vertex_stringbuilder.append("mat4 bones" + "[" + bones_size + "]" + " = mat4[](");

            //     // for (int j = 0; j < bones_size; ++j)
            //     // {
            //     //     vertex_stringbuilder.append("mat4(");

            //     //     int bindposes_index = (j + 1) * 16;
            //     //     for (int b = j * 16; b < bindposes_index; ++b)
            //     //     {
            //     //         vertex_stringbuilder.append("" + bones_float_array[b]);

            //     //         if (b < bindposes_index - 1)
            //     //         {
            //     //             vertex_stringbuilder.append(", ");
            //     //         }
            //     //     }

            //     //     vertex_stringbuilder.append(")");

            //     //     if (j < bones_size - 1)
            //     //     {
            //     //         vertex_stringbuilder.append(", ");
            //     //     }
            //     // }

            //     // vertex_stringbuilder.append(");\n");
            //     // object_array[3] = null;

            //     float[] bindposes_float_array = (float[])object_array[13];
            //     int bindposes_size = bindposes_float_array.length / 16;
            //     vertex_stringbuilder.append("mat4 bindposes" + "[" + bindposes_size + "]" + " = mat4[](");

            //     for (int j = 0; j < bindposes_size; ++j)
            //     {
            //         vertex_stringbuilder.append("mat4(");

            //         int bindposes_index = (j + 1) * 16;
            //         for (int b = j * 16; b < bindposes_index; ++b)
            //         {
            //             vertex_stringbuilder.append("" + bindposes_float_array[b]);

            //             if (b < bindposes_index - 1)
            //             {
            //                 vertex_stringbuilder.append(", ");
            //             }
            //         }

            //         vertex_stringbuilder.append(")");

            //         if (j < bindposes_size - 1)
            //         {
            //             vertex_stringbuilder.append(", ");
            //         }
            //     }

            //     vertex_stringbuilder.append(");\n");

            //     // for (int i = 0; i < bindposes_float_array.length; i += 16)
            //     // {
            //     //     M4x4.inverse(bindposes_float_array, i);
            //     // }

            //     String line = null;
            //     BufferedReader vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 0));
            //     while ((line = vertex_bufferedreader.readLine()) != null)
            //     {
            //         vertex_stringbuilder.append(line + "\n");
            //     }
            //     vertex_bufferedreader.close();

            //     boolean multi_uniform = (byte)DataLoader.CLIENT_OBJECT_ARRAY[1] == 1;

            //     if (multi_uniform)
            //     {
            //         vertex_stringbuilder.append("\nmat4 getAnimation(int b)\n{\n");
            //         for (int j = 0; j < bindposes_size; ++j)
            //         {
            //             String head = "else if";

            //             if (j == 0)
            //             {
            //                 head = "if";
            //             }

            //             vertex_stringbuilder.append(head + " (b == " + j + ")\n{return animation" + j + ";\n}\n");
            //         }
            //         vertex_stringbuilder.append("return mat4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);\n}\n");
            //     }

            //     String transform_vec_string = "\nvec4 transformVec(int b, vec4 vertices_vec4)\n" +
            //     "{\n" +
            //     "mat4 skinning_mat4 = bindposes[b];\n" +
            //     "vertices_vec4 *= skinning_mat4;\n";
            //     // temp_normals_vec4 *= skinning_mat4;

            //     if (multi_uniform)
            //     {
            //         transform_vec_string += "skinning_mat4 *= getAnimation(b);\n";
            //     }
            //     else
            //     {
            //         transform_vec_string += "skinning_mat4 *= animation[b];\n";
            //     }

            //     // skinning_mat4 *= animation[b];
            //     transform_vec_string += "skinning_mat4 = inverse(skinning_mat4);\n" +
            //     "vertices_vec4 *= skinning_mat4;\n" +
            //     // temp_normals_vec4 *= skinning_mat4;

            //     "return vertices_vec4;\n" +
            //     "}\n";

            //     vertex_stringbuilder.append(transform_vec_string);

            //     vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 1));
            //     while ((line = vertex_bufferedreader.readLine()) != null)
            //     {
            //         vertex_stringbuilder.append(line + "\n");
            //     }
            //     vertex_bufferedreader.close();

            //     if (shader_state != 2 && shader_state != 4)
            //     {
            //         vertex_stringbuilder.append("for (int i = 0; i < " + max_joints + "; ++i)\n{\n");
            //     }
            //     vertex_stringbuilder.append("vec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
            //     //     vertex_stringbuilder.append("if (joints[i] != -1)\n{\nvec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
            //     // }
            //     // else
            //     // {
            //     //     vertex_stringbuilder.append("if (joints != -1)\n{\nvec4 temp_vertices_vec4 = vec4(vertices, 1.0);\n");
            //     // }

            //     vertex_stringbuilder.append("int joint = " + (max_joints == 1 || max_joints > 4 ? "joints" : "joints[i]") + ";\n");
            //     vertex_stringbuilder.append("float weight = " + (max_joints == 1 || max_joints > 4 ? "weights" : "weights[i]") + ";\n");

            //     for (int j = 0; j < bones_object_array.length; ++j)
            //     {
            //         int[] bones = (int[])bones_object_array[j];
            //         String head = "else if";

            //         if (j == 0)
            //         {
            //             head = "if";
            //         }

            //         vertex_stringbuilder.append(head + " (joint == " + j + ")\n{\nfor (int b = 0; b < " + bones.length + "; ++b)\n{\ntemp_vertices_vec4 = transformVec(bones" + j + "[b], temp_vertices_vec4);\n}\n}\n");
            //     }

            //     vertex_bufferedreader = new BufferedReader(new FileReader(folder_path + shaders_folder_path + "Vertex" + shader_state + 2));
            //     while ((line = vertex_bufferedreader.readLine()) != null)
            //     {
            //         vertex_stringbuilder.append(line + "\n");
            //     }
            //     vertex_bufferedreader.close();
            // }
            // catch (IOException ioexception)
            // {
            //     Main.LOGGER.error(ioexception.getMessage(), ioexception);
            // }

            // OpenGLBuffer.createIntBuffer(object_array, 11, true);
        // }

        OpenGLBuffer.createIntBuffer(object_array, 0, true);

        object_array[1] = OpenGLBuffer.createFloatBuffer((float[])object_array[1], true);
        object_array[2] = OpenGLBuffer.createFloatBuffer((float[]) object_array[2], true);
        // OpenGLBuffer.setFloatBuffer(object_array, 3, 3, true);

//        object_array[7] = OpenGLBuffer.createFloatBuffer((float[])object_array[7], true);
        OpenGLBuffer.createIntBuffer(object_array, 7, true);
        object_array[8] = OpenGLBuffer.createFloatBuffer((float[])object_array[8], true);
        // OpenGLBuffer.setM4x4FloatBuffer(object_array, 13, true);

        dataloader.model_object_array[object_index] = object_array;
    }
}
