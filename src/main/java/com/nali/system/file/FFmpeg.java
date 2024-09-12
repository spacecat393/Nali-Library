package com.nali.system.file;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class FFmpeg
{
	public static byte[] getSounds(String path_string)
	{
		return executeByteArray(new ProcessBuilder
		(
			"ffmpeg",
			"-i", path_string,
			"-f", "s16le", "pipe:1"
		));
	}

	public static byte[] getTextures(String path_string)
	{
		return executeByteArray(new ProcessBuilder
		(
			"ffmpeg",
			"-i", path_string,
			"-f", "rawvideo",
			"-pix_fmt", "rgba",
			"-"
		));
	}

	public static int getSampleRate(String path_string)
	{
		return executeInt(new ProcessBuilder
		(
			"ffprobe", "-v", "error",
			"-select_streams", "a:0",
			"-show_entries", "stream=sample_rate",
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

	private static String executeString(ProcessBuilder processbuilder)
	{
		try
		{
			Process process = processbuilder.start();
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			StringBuilder stringbuilder = new StringBuilder();
			while ((line = bufferedreader.readLine()) != null)
			{
				stringbuilder.append(line);
			}

			BufferedReader error_bufferedreader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			StringBuilder error_stringbuilder = new StringBuilder();
			while ((line = error_bufferedreader.readLine()) != null)
			{
				error_stringbuilder.append(line).append("\n");
			}
			if (process.waitFor() != 0)
			{
				error(error_stringbuilder.toString());
			}

			bufferedreader.close();
			error_bufferedreader.close();
			process.destroy();

			return stringbuilder.toString().trim();
		}
		catch (IOException | NumberFormatException | InterruptedException e)
		{
			error(e);
		}

		return null;
	}

	private static int executeInt(ProcessBuilder processbuilder)
	{
		return Integer.parseInt(executeString(processbuilder));
	}

	private static byte[] executeByteArray(ProcessBuilder processbuilder)
	{
		try
		{
			Process process = processbuilder.start();
			ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
			byte[] buffer_byte_array = new byte[4096];

			int bytes_read;
			while ((bytes_read = process.getInputStream().read(buffer_byte_array)) != -1)
			{
				bytearrayoutputstream.write(buffer_byte_array, 0, bytes_read);
			}

			BufferedReader error_bufferedreader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			StringBuilder error_stringbuilder = new StringBuilder();
			String line;
			while ((line = error_bufferedreader.readLine()) != null)
			{
				error_stringbuilder.append(line).append("\n");
			}
			if (process.waitFor() != 0)
			{
				error(error_stringbuilder.toString());
			}

			byte[] byte_array = bytearrayoutputstream.toByteArray();

			bytearrayoutputstream.close();
			error_bufferedreader.close();
			process.destroy();

			return byte_array;
		}
		catch (IOException | InterruptedException e)
		{
			error(e);
		}

		return null;
	}
}
