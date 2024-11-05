package com.nali.system;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class Command
{
	public static boolean isCommandLive(String command_string)
	{
		try
		{
			get(command_string).redirectErrorStream(true).start().destroy();
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
	}

	public static ProcessBuilder get(String... command_string)
	{
		ProcessBuilder processbuilder;
		String os_name = System.getProperty("os.name").toLowerCase();
		if (os_name.contains("win"))
		{
			String[] windows_command = new String[command_string.length + 2];
			windows_command[0] = "cmd";
			windows_command[1] = "/c";
			System.arraycopy(command_string, 0, windows_command, 2, command_string.length);

			processbuilder = new ProcessBuilder(windows_command);
		}
		else
		{
			processbuilder = new ProcessBuilder(command_string);
		}

		return processbuilder;
	}

	public static String executeString(ProcessBuilder processbuilder)
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

	public static int executeInt(ProcessBuilder processbuilder)
	{
		return Integer.parseInt(executeString(processbuilder));
	}

	public static byte[] executeByteArray(ProcessBuilder processbuilder)
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
