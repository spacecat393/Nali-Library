package com.nali;

import java.io.File;

import static com.nali.Nali.warn;

public class NaliAL
{
	public static void load()
	{
		if ((NaliConfig.STATE & 8) == 8)
		{
			String os_name = System.getProperty("os.name").toLowerCase();
			String type = "";
			if (os_name.contains("win"))
			{
				type += "dll";
			}
			else if (os_name.contains("nix") || os_name.contains("nux") || os_name.contains("aix"))
			{
				type += "so";
			}
			else if (os_name.contains("mac"))
			{
				type += "dylib";
			}
			else
			{
				warn(os_name);
			}

			File file = new File("nali/nali/c/libNaliAL." + type);
			if (file.exists())
			{
				System.load(file.getAbsolutePath());
			}
			else
			{
				warn("SKIP_AL_POINTER");
			}
		}
	}

	public static native void init();
//	public static native long alcOpenDevice();
	public static native int alGenBuffers();
	public static native int alGenSources();
	public static native void alBufferData(int buffer, int format, long data, int data_size, int freq);
	public static native void alSourcei(int source, int pname, int value);
	public static native void alSourcef(int source, int pname, float value);
	public static native void alSourcePlay(int source);
	public static native void alSourceStop(int source);
}
