package com.nali;

import java.io.File;

import static com.nali.Nali.warn;

public class NaliGL
{
//	public static byte STATE;

	static
	{
		if (NaliConfig.VAO)
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

			File file = new File("nali/nali/c/libNaliGL." + type);
			if (file.exists())
			{
				System.load(file.getAbsolutePath());
			}
			else
			{
				warn("SKIP_GL_POINTER");
//			STATE |= 1;
			}
		}
	}

	public static native void init();
	public static native int glVertexArrayBinding();
	public static native int glGenVertexArrays();
	public static native void glBindVertexArray(int array);
	public static native void glDrawElementsTUi0(int indices_count);
}
