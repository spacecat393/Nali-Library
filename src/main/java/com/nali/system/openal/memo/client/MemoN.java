package com.nali.system.openal.memo.client;

import com.nali.NaliAL;
import com.nali.system.file.FFmpeg;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.MemoryUtil;
import org.lwjgl.openal.AL10;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@SideOnly(Side.CLIENT)
public class MemoN
{
	public static int gen(String path)
	{
		byte[] byte_array = FFmpeg.getSounds(path);
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(byte_array.length << 2).order(ByteOrder.nativeOrder());
		bytebuffer.put(byte_array);
		bytebuffer.flip();//?

		int sound_buffer = NaliAL.alGenBuffers();
//		NaliAL.alBufferData(sound_buffer, AL10.AL_FORMAT_MONO16, MemoryUtil.getAddress(bytebuffer), bytebuffer.remaining(), FFmpeg.getSampleRate(path));
		NaliAL.alBufferData(sound_buffer, AL10.AL_FORMAT_STEREO16, MemoryUtil.getAddress(bytebuffer), bytebuffer.limit(), FFmpeg.getSampleRate(path));
		return sound_buffer;
	}
}
