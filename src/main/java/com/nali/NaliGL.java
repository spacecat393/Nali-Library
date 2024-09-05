package com.nali;

import java.io.File;

import static com.nali.Nali.error;

public class NaliGL
{
    static
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
            error(os_name);
        }
        System.load(new File("nali/nali/c/libNaliGL." + type).getAbsolutePath());
    }

    public static native void init();
    public static native int glVertexArrayBinding();
    public static native int glGenVertexArrays();
    public static native void glBindVertexArray(int array);
    public static native void glDrawElementsTUi0(int indices_count);
}
