package com.nali.system.file;

import com.nali.system.Command;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FFmpeg
{
	//ffmpeg -ss 00:00:10 -i '' -t 10 -f s16le pipe:1
	public static byte[] getSounds(String path_string)
	{
		//s16le 16bit
		//s8 8bit
		return Command.executeByteArray(Command.get
		(
			"ffmpeg",
			"-i", path_string,
			"-f", "s16le", "pipe:1"
		));
	}

//	public static byte[] getTextures(String path_string)
//	{
//		return executeByteArray(ClientLoader.get
//		(
//			"ffmpeg",
//			"-i", path_string,
//			"-f", "rawvideo",
//			"-pix_fmt", "rgba", "-"
//		));
//	}

	//sample_rate
	//channels
	//channel_layout
	public static String getSelect(String path_string, String data)
	{
		return Command.executeString(Command.get
		(
			"ffprobe",
			"-v", "error",
			"-select_streams", "a:0",
			"-show_entries", "stream=" + data,
			"-of", "default=noprint_wrappers=1:nokey=1",
			path_string
		));
//		try
//		{
//			Process process = processbuilder.start();
//
//			InputStream inputstream = process.getInputStream();
//			InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
//
//			StringBuilder stringbuilder = new StringBuilder();
//			int ch;
//			while ((ch = inputstreamreader.read()) != -1)
//			{
//				stringbuilder.append((char)ch);
//			}
//
//			process.waitFor();
//			process.destroy();
//			return Integer.parseInt(stringbuilder.toString().trim());
//		}
//		catch (IOException | NumberFormatException | InterruptedException e)
//		{
//			error(e);
//		}
//		return -1;
	}

//	public static void get(String path_string, String fps_string)
//	{
////		String[] extractFramesCmd = {"ffmpeg", "-i", path_string, "-vf", "select='eq(n," + fps_string + ")',setpts=N/(FRAME_RATE*TB)", outputImageFilePath};
////		String[] extractAudioCmd =
////		{
////			"ffmpeg",
////			"-i", path_string,
////			"-vf", "select='eq(n," + fps_string + ")', atrim=start=0:end=1",
////			"-map", "a", "-acodec", "pcm_s16le", "-ar", "44100", "-ac", "2",
////			outputAudioFilePath
////		};
//	}

//	private static int getFrameRate(String path_string)
//	{
//		String output = executeString(new ProcessBuilder
//		(
//			"ffmpeg",
//			"-i", path_string,
//			"-v", "0",
//			"-select_streams", "v:0",
//			"-print_format", "json",
//			"-show_entries", "stream=r_frame_rate",
//			"-of", "json"
//		));
//
//		String frameRateString = output.substring(output.indexOf("r_frame_rate") + 15, output.indexOf("r_frame_rate") + 20);
//		String[] frameRateParts = frameRateString.split("/");
//		double frameRate = Double.parseDouble(frameRateParts[0]) / Double.parseDouble(frameRateParts[1]);
//
//		return (int)frameRate;
//	}
}
