//package com.nali.system.file;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import com.nali.Main;
//
//public class FileDataWriter
//{
//    public static void bytes(String path_string, byte[] string_byte)
//    {
//        try
//        {
//            File file = new File(path_string);
//
//            FileOutputStream fileoutputstream = new FileOutputStream(file);
//            fileoutputstream.write(string_byte);
//            fileoutputstream.close();
//        }
//        catch (IOException ioexception)
//        {
//            Main.LOGGER.error(ioexception.getMessage(), ioexception);
//        }
//    }
//}
