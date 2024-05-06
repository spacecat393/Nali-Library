package com.nali.system.bytes;

import java.util.Arrays;

public class ByteArray
{
    public byte[] array;

    public ByteArray(byte[] array)
    {
        this.array = array;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(array);
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof ByteArray))
        {
            return false;
        }
        ByteArray bytearray = (ByteArray)object;
        return Arrays.equals(array, bytearray.array);
    }
}
