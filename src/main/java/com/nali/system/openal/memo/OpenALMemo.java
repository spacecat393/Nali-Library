package com.nali.system.openal.memo;

import com.nali.system.file.FFmpeg;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.openal.AL10;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SideOnly(Side.CLIENT)
public class OpenALMemo
{
//    public int[] sound_buffer_int_array;
//    public int[] sample_rate_int_array;
    public int sound_buffer;

    public OpenALMemo(File file)
    {
//        this.sound_buffer_int_array = new int[sound_file_array.length];
//        this.sample_rate_int_array = new int[size];

//        String name = file.getName();
        String path = file.getPath();
//        int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));

        byte[] byte_array = FFmpeg.getSounds(path);
        ByteBuffer bytebuffer = ByteBuffer.allocateDirect(byte_array.length << 2).order(ByteOrder.nativeOrder());
        bytebuffer.put(byte_array);
        bytebuffer.flip();

//        this.sound_buffer_int_array[index] = AL10.alGenBuffers();
        this.sound_buffer = AL10.alGenBuffers();
//            this.sample_rate_int_array[index] = FFmpeg.getSampleRate(path);
//        AL10.alBufferData(this.sound_buffer_int_array[index], AL10.AL_FORMAT_MONO16, bytebuffer, FFmpeg.getSampleRate(path));
        AL10.alBufferData(this.sound_buffer, AL10.AL_FORMAT_MONO16, bytebuffer, FFmpeg.getSampleRate(path));
    }
}
