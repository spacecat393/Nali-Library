package com.nali.system.opengl.memory;

import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.IntBuffer;
import java.util.ArrayList;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
public class OpenGLObjectMemory
{
    public ArrayList<OpenGLAttribMemory> openglattribmemory_arraylist = new ArrayList<OpenGLAttribMemory>();
    //int[N] -> IntBuffer
    public Object index;
    public int index_length;
    public byte culling;
    public byte texture_state;
    //shader_id_int -> OpenGLShaderData
    public Object shader;
    public int element_array_buffer;

    public float[] vertices_float_array;
    public int[] index_int_array;

    public OpenGLObjectMemory(String[] model_string_array, String folder_path, String[][] shader_string_2d_array)
    {
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER, OPENGL_INTBUFFER);
        GL_ELEMENT_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        this.createBufferFromFile(model_string_array, folder_path, shader_string_2d_array);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, GL_ELEMENT_ARRAY_BUFFER_BINDING);
    }

    public void createBufferFromFile(String[] model_string_array, String folder_path, String[][] shader_string_2d_array)
    {
        String model_folder_string = folder_path + "Models/" + model_string_array[0] + '/';
        this.texture_state = (byte)Integer.parseInt(model_string_array[1]);
        int shader_id = Integer.parseInt(model_string_array[2]);
        this.culling = (byte)Integer.parseInt(model_string_array[3]);

        this.shader = shader_id;

        this.element_array_buffer = GL15.glGenBuffers();
        this.index_int_array = FileDataReader.getIntArray(model_folder_string + "/Index");
        this.index_length = this.index_int_array.length;
        this.index = OpenGLBuffer.createIntBuffer(this.index_int_array, true);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.element_array_buffer);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)this.index, GL15.GL_STATIC_DRAW);

        String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "Shaders/" + shader_string_2d_array[(int)this.shader][0] + "/Attrib");
        this.createBufferAttribLocation(model_string_array, folder_path, shader_string_2d_array, attriblocation_string_2d_array, attriblocation_string_2d_array.length);
    }

    public void createBufferAttribLocation(String[] model_string_array, String folder_path, String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, int length)
    {
        String model_folder_string = folder_path + "Models/" + model_string_array[0] + '/';

        this.vertices_float_array = FileDataReader.getFloatArray(model_folder_string + "/Vertices");

        for (int i = 0; i < length; ++i)
        {
            String[] attriblocation_string_array = attriblocation_string_2d_array[i];
            String attriblocation_name_string = attriblocation_string_array[0];
            this.openglattribmemory_arraylist.add(new OpenGLAttribMemory(FileDataReader.getFloatArray(model_folder_string + Character.toUpperCase(attriblocation_name_string.charAt(0)) + attriblocation_name_string.substring(1)), (byte)Integer.parseInt(attriblocation_string_array[1])));
        }
    }
}
