package com.nali.system.openal.memo.client;

import com.nali.Nali;
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
		int sound_buffer = NaliAL.alGenBuffers();
		int channels = Integer.parseInt(FFmpeg.getSelect(path, "channels"));
//		String channel_layout = FFmpeg.getSelect(path, "channel_layout");
		int format = -1;
		if (channels == 2)
		{
			format = AL10.AL_FORMAT_STEREO16;
//			if (channel_layout.equals("stereo"))
//			{
//				format = AL10.AL_FORMAT_STEREO16;
//			}
//			else if (channel_layout.equals("mono"))
//			{
//				format = AL10.AL_FORMAT_MONO16;
//			}
//			else
//			{
//				Nali.error("CHANNEL_LAYOUT " + channel_layout);
//			}
		}
		else if (channels == 1)
		{
			format = AL10.AL_FORMAT_MONO16;
//			if (channel_layout.equals("stereo"))
//			{
//				format = AL10.AL_FORMAT_STEREO8;
//			}
//			else if (channel_layout.equals("mono"))
//			{
//				format = AL10.AL_FORMAT_MONO8;
//			}
//			else
//			{
//				Nali.error("CHANNEL_LAYOUT " + channel_layout);
//			}
		}
		else
		{
			Nali.error("CHANNELS " + channels);
		}

		byte[] byte_array = FFmpeg.getSounds(path);
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(byte_array.length).order(ByteOrder.nativeOrder());
		bytebuffer.put(byte_array);
		bytebuffer.flip();

		//read sound follow ffmpeg file
		NaliAL.alBufferData(sound_buffer, format, MemoryUtil.getAddress(bytebuffer), bytebuffer.limit(), Integer.parseInt(FFmpeg.getSelect(path, "sample_rate")));
		return sound_buffer;
	}
}
