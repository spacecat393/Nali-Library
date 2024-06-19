package com.nali.system.file;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class FileDataReader
{
    public static float[] getFloatArray(String path_string)
    {
        try
        {
//            String data_string = new String(Files.readAllBytes(Paths.get(path_string)));
//            String[] space_string_array = data_string.split(" ");
//            float[] float_array = new float[space_string_array.length];
            byte[] byte_array = Files.readAllBytes(Paths.get(path_string));
            int byte_array_length = byte_array.length;
            float[] float_array = new float[byte_array_length / 4];

//            String number_string = "";
//            int index = 0;
//            for (int i = 0; i < data_string.length(); ++i)
            for (int i = 0; i < byte_array_length; i += 4)
            {
                int temp_int = ((byte_array[i + 3] & 0xFF) << 24) |
                ((byte_array[i + 2] & 0xFF) << 16) |
                ((byte_array[i + 1] & 0xFF) << 8) |
                (byte_array[i] & 0xFF);

                float_array[i / 4] = Float.intBitsToFloat(temp_int);

//                float_array[i / 4] = Float.intBitsToFloat(temp_int);
//                if (data_string.charAt(i) == ' ')
//                {
//                    float_array[index] = Float.parseFloat(number_string);
//                    number_string = "";
//                    ++index;
//                }
//                else
//                {
//                    number_string += data_string.charAt(i);
//                }
            }

//            float_array[index] = Float.parseFloat(number_string);
            return float_array;
        }
        catch (IOException e)
        {
            I.error(e);
        }

        return null;
    }

    public static int[] getIntArray(String path_string)
    {
        try
        {
//            String data_string = new String(Files.readAllBytes(Paths.get(path_string)));
//            String[] space_string_array = data_string.split(" ");
//            int[] int_array = new int[space_string_array.length];
            byte[] byte_array = Files.readAllBytes(Paths.get(path_string));
            int byte_array_length = byte_array.length;
            int[] int_array = new int[byte_array_length / 4];
//            String number_string = "";
//            int index = 0;

//            for (int i = 0; i < data_string.length(); ++i)
            for (int i = 0; i < byte_array_length; i += 4)
            {
                int_array[i / 4] = ((byte_array[i + 3] & 0xFF) << 24) |
                ((byte_array[i + 2] & 0xFF) << 16) |
                ((byte_array[i + 1] & 0xFF) << 8) |
                (byte_array[i] & 0xFF);
//                if (data_string.charAt(i) == ' ')
//                {
//                    int_array[index] = Integer.parseInt(number_string);
//                    number_string = "";
//                    ++index;
//                }
//                else
//                {
//                    number_string += data_string.charAt(i);
//                }
            }

//            int_array[index] = Integer.parseInt(number_string);
            return int_array;
        }
        catch (IOException e)
        {
            I.error(e);
        }

        return null;
    }

    public static float[] getFloatIntArray(String path_string)
    {
        try
        {
//            String data_string = new String(Files.readAllBytes(Paths.get(path_string)));
//            String[] space_string_array = data_string.split(" ");
//            int[] int_array = new int[space_string_array.length];
            byte[] byte_array = Files.readAllBytes(Paths.get(path_string));
            int byte_array_length = byte_array.length;
            float[] float_int_array = new float[byte_array_length / 4];
//            String number_string = "";
//            int index = 0;

//            for (int i = 0; i < data_string.length(); ++i)
            for (int i = 0; i < byte_array_length; i += 4)
            {
                float_int_array[i / 4] = ((byte_array[i + 3] & 0xFF) << 24) |
                        ((byte_array[i + 2] & 0xFF) << 16) |
                        ((byte_array[i + 1] & 0xFF) << 8) |
                        (byte_array[i] & 0xFF);
//                if (data_string.charAt(i) == ' ')
//                {
//                    int_array[index] = Integer.parseInt(number_string);
//                    number_string = "";
//                    ++index;
//                }
//                else
//                {
//                    number_string += data_string.charAt(i);
//                }
            }

//            int_array[index] = Integer.parseInt(number_string);
            return float_int_array;
        }
        catch (IOException e)
        {
            I.error(e);
        }

        return null;
    }

//    public static Object[] getMixXIntArray(String path_string)
//    {
//        Object[] object_array = null;
//
//        try
//        {
//            String data_string = new String(Files.readAllBytes(Paths.get(path_string)));
//            String[] newline_string_array = data_string.split("\n");
//            object_array = new Object[newline_string_array.length];
//
//            for (int i = 0; i < newline_string_array.length; ++i)
//            {
//                String[] space_string_array = newline_string_array[i].split(" ");
//                int int_array_length = space_string_array.length;
//                int[] int_array = new int[int_array_length];
//
//                for (int l = 0; l < int_array_length; ++l)
//                {
//                    int_array[l] = Integer.parseInt(space_string_array[l]);
//                }
//
//                object_array[i] = int_array;
//            }
//        }
//        catch (IOException ioexception)
//        {
//            Main.LOGGER.error(ioexception.getMessage(), ioexception);
//        }
//
//        return object_array;
//    }

//    public static void getIntArray(String path_string, char end_char, Object[] object_array, int object_index)
//    {
//        try
//        {
//            byte[] byte_array = Files.readAllBytes(Paths.get(path_string));
////            String data_string = new String(Files.readAllBytes(Paths.get(path_string)));
////            String[] space_string_array = data_string.split(" ");
////            int[] int_array = new int[space_string_array.length];
//            ByteBuffer bytebuffer = ByteBuffer.wrap(byte_array);
//            int int_array_size = bytebuffer.remaining() / 4;
//            int[] int_array = new int[int_array_size];
//            object_array[object_index] = int_array;
//
//            int index = 0;
//            String number_string = "";
//
////            for (int i = 0; i < data_string.length(); ++i)
//            for (int i = 0; i < int_array_size; ++i)
//            {
//                int number = ((byteArray[0] & 0xFF) << 24) |
//                        ((byteArray[1] & 0xFF) << 16) |
//                        ((byteArray[2] & 0xFF) << 8) |
//                        (byteArray[3] & 0xFF);
//                int value = bytebuffer.getInt(i);
//
//                if (value == )
//                {
//
//                }
//                else
//                {
//                    int_array[i] = bytebuffer.getInt(value);
//                }
////                if (data_string.charAt(i) == end_char)
////                {
////                    int_array[index] = Integer.parseInt(number_string);
////                    number_string = "";
////                    ++index;
////                }
////                else
////                {
////                    number_string += data_string.charAt(i);
////                }
//            }
////            int_array[index] = Integer.parseInt(number_string);
//        }
//        catch (IOException ioexception)
//        {
//            Main.LOGGER.error(ioexception.getMessage(), ioexception);
//        }
//    }

//    public static String[][] getMixXStringArray(String path_string)
    public static String[][] getMixXStringArray(Path path)
    {
        String[][] string_2d_array = null;

        try
        {
//            String data_string = new String(Files.readAllBytes(Paths.get(path_string)));
            String data_string = new String(Files.readAllBytes(path));
            String[] newline_string_array = data_string.split("\n");
            string_2d_array = new String[newline_string_array.length][];

            for (int i = 0; i < newline_string_array.length; ++i)
            {
                string_2d_array[i] = newline_string_array[i].split(" ");
            }
        }
        catch (IOException e)
        {
            I.error(e);
        }

        return string_2d_array;
    }
}
