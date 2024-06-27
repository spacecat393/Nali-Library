package com.nali.system.bytes;

import java.util.UUID;

public class ByteWriter
{
    public static void set(byte[] byte_array, int i, int index)
    {
//        byte_array[index] = (byte)(i & 0xFF);
//        byte_array[index + 1] = (byte)((i >> 8) & 0xFF);
//        byte_array[index + 2] = (byte)((i >> 16) & 0xFF);
//        byte_array[index + 3] = (byte)((i >> 24) & 0xFF);
        for (int b = 0; b < Integer.BYTES; b++)
        {
            byte_array[index + b] = (byte) ((i >> (b * 8)) & 0xFF);
        }
    }

    public static void set(byte[] byte_array, float f, int index)
    {
        int i = Float.floatToIntBits(f);
        set(byte_array, i, index);
    }

    public static void set(byte[] byte_array, UUID uuid, int index)
    {
        long most = uuid.getMostSignificantBits();
        long least = uuid.getLeastSignificantBits();

        for (int i = index; i < 8 + index; ++i)
        {
            int shift = 8 * (-i - 1 + index);
            byte_array[i] = (byte) (most >> shift);
            byte_array[8 + i] = (byte) (least >> shift);
        }
    }

    public static void set(byte[] byte_array, long l, int index)
    {
        for (int b = 0; b < Long.BYTES; b++)
        {
            byte_array[index + b] = (byte) ((l >> (b * 8)) & 0xFF);
        }
    }
}
