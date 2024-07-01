package com.nali.system.opengl.memo.client;

import com.nali.Nali;
import com.nali.system.file.FileDataReader;
import com.nali.system.opengl.OpenGLBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public class MemoGo
{
    public List<MemoAttrib> openglattribmemo_arraylist = new ArrayList();
    //int[N] -> IntBuffer
    public Object index;
    public int index_length;
//    //shader_id_int -> OpenGLShaderData
//    public Object shader;
    public int element_array_buffer;

    public float[] vertices_float_array;
    public int[] index_int_array;

    public byte state;//texture_state culling transparent glow
    public int /*model_id, */texture_id, shader_id;

    public MemoGo(String[] model_string_array, String folder_path/*, String[][] shader_string_2d_array*/)
    {
//        String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Reference.MOD_ID + "/" + model_string_array[2] + "/Shader"));
        String[][] shader_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(folder_path + "/ShaderList"));
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER, OPENGL_INTBUFFER);
        GL_ELEMENT_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        this.createBufferFromFile(model_string_array, folder_path, shader_string_2d_array);
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);
        OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, GL_ELEMENT_ARRAY_BUFFER_BINDING);
    }

    public void createBufferFromFile(String[] model_string_array, String folder_path, String[][] shader_string_2d_array)
    {
        String model_folder_string = folder_path + "/Model/" + model_string_array[0] + '/';
//        this.state |= Byte.parseByte(model_string_array[1]);//texture_state
        //default_texture
        this.texture_id = Integer.parseInt(model_string_array[1]);
        this.shader_id = Integer.parseInt(model_string_array[2]);
//        this.shader_id = Integer.parseInt(model_string_array[3]);

        this.state = (byte)(Byte.parseByte(model_string_array[3]) | 2 * Byte.parseByte(model_string_array[4]) | 4 * Byte.parseByte(model_string_array[5]) | 8 * Byte.parseByte(model_string_array[6]));//texture_state culling transparent glow
//        this.state = (byte)(Byte.parseByte(model_string_array[4]) | 2 * Byte.parseByte(model_string_array[5]) | 4 * Byte.parseByte(model_string_array[6]) | 8 * Byte.parseByte(model_string_array[7]));//texture_state culling transparent glow

//        int shader_id = Integer.parseInt(model_string_array[2]);
//        this.shader_id = Integer.parseInt(model_string_array[2]);
//        this.state |= (byte)(2 * Integer.parseInt(model_string_array[3]));//culling

//        this.shader = shader_id;

        this.element_array_buffer = OpenGlHelper.glGenBuffers();
        this.index_int_array = FileDataReader.getIntArray(model_folder_string + "/Index");
        this.index_length = this.index_int_array.length;
        this.index = OpenGLBuffer.createIntByteBuffer(this.index_int_array, true);
        OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.element_array_buffer);
        OpenGlHelper.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (ByteBuffer)this.index, OpenGlHelper.GL_STATIC_DRAW);

//        String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(folder_path + "Shaders/" + shader_string_2d_array[(int)this.shader][0] + "/Attrib");
        String[][] attriblocation_string_2d_array = FileDataReader.getMixXStringArray(Paths.get(Nali.ID + "/" + shader_string_2d_array[this.shader_id][0] + "/Shader/" + shader_string_2d_array[this.shader_id][1] + "/Attrib"));
        this.createBufferAttribLocation(model_string_array, model_folder_string/*folder_path*/, shader_string_2d_array, attriblocation_string_2d_array, attriblocation_string_2d_array.length);
    }

    public void createBufferAttribLocation(String[] model_string_array, String model_folder_string/*, String folder_path*/, String[][] shader_string_2d_array, String[][] attriblocation_string_2d_array, int length)
    {
//        String model_folder_string = folder_path + "/Model/" + model_string_array[0] + '/';

        this.vertices_float_array = FileDataReader.getFloatArray(model_folder_string + "/Vertices");

        for (int i = 0; i < length; ++i)
        {
            String[] attriblocation_string_array = attriblocation_string_2d_array[i];
            String attriblocation_name_string = attriblocation_string_array[0];
            this.openglattribmemo_arraylist.add(new MemoAttrib(FileDataReader.getFloatArray(model_folder_string + Character.toUpperCase(attriblocation_name_string.charAt(0)) + attriblocation_name_string.substring(1)), Byte.parseByte(attriblocation_string_array[1])));
        }
    }
}
