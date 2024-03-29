package com.nali.system.bytes;

import java.util.UUID;

public class BytesReader
{
    public static int getInt(byte[] byte_array, int index)
    {
        return ((byte_array[index + 3] & 0xFF) << 24) |
        ((byte_array[index + 2] & 0xFF) << 16) |
        ((byte_array[index + 1] & 0xFF) << 8) |
        (byte_array[index] & 0xFF);
    }

    public static float getFloat(byte[] byte_array, int index)
    {
        int i = getInt(byte_array, index);
        return Float.intBitsToFloat(i);
    }

    public static UUID getUUID(byte[] byte_array, int index)
    {
        long most = 0;
        long least = 0;

        for (int i = index; i < 8 + index; i++)
        {
            most = (most << 8) | (byte_array[i] & 0xFF);
            least = (least << 8) | (byte_array[8 + i] & 0xFF);
        }

        return new UUID(most, least);
    }

    public static long getLong(byte[] byte_array, int index)
    {
        long l = 0;

        for (int i = 0; i < 8; i++)
        {
            l |= (long)(byte_array[i + index] & 0xFF) << (i * 8);
        }

        return l;
    }
}
