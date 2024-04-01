package com.nali.system.openal.memory;

import com.nali.system.file.FFmpeg;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.openal.AL10;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SideOnly(Side.CLIENT)
public class OpenALMemory
{
    public int[] sound_buffer_int_array;
//    public int[] sample_rate_int_array;

    public OpenALMemory(String mod_id_string)
    {
        File[] file_array = new File(mod_id_string + "Sounds/").listFiles();
        int size = file_array.length;
        this.sound_buffer_int_array = new int[size];
//        this.sample_rate_int_array = new int[size];

        for (File file : file_array)
        {
            String name = file.getName();
            String path = file.getPath();
            int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));

            byte[] byte_array = FFmpeg.get(path);
            ByteBuffer bytebuffer = ByteBuffer.allocateDirect(byte_array.length << 2).order(ByteOrder.nativeOrder());
            bytebuffer.put(byte_array);
            bytebuffer.flip();

            this.sound_buffer_int_array[index] = AL10.alGenBuffers();
//            this.sample_rate_int_array[index] = FFmpeg.getSampleRate(path);
            AL10.alBufferData(this.sound_buffer_int_array[index], AL10.AL_FORMAT_MONO16, bytebuffer, FFmpeg.getSampleRate(path));
        }
    }
}
