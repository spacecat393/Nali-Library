package com.nali.system.bytes;

import java.util.UUID;

public class ByteReader
{
//	public static NBTTagCompound deserializeNBT(byte[] data)
//	{
//		ByteBuf bytebuf = ByteBufAllocator.DEFAULT.buffer();
//		bytebuf.writeBytes(data);
//		NBTTagCompound nbttagcompound = ByteBufUtils.readTag(bytebuf);
//		bytebuf.release();
//		return nbttagcompound;
//	}

	public static int getInt(byte[] byte_array, int index)
	{
		int i = 0;

		for (byte b = 0; b < Integer.BYTES; b++)
		{
			i |= (byte_array[b + index] & 0xFF) << (b * 8);
		}

		return i;
//		return ((byte_array[index + 3] & 0xFF) << 24) |
//				((byte_array[index + 2] & 0xFF) << 16) |
//				((byte_array[index + 1] & 0xFF) << 8) |
//				(byte_array[index] & 0xFF);
	}

	public static short getShort(byte[] byte_array, int index)
	{
		short i = 0;

		for (byte b = 0; b < Short.BYTES; b++)
		{
			i |= (byte_array[b + index] & 0xFF) << (b * 8);
		}

		return i;
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

		for (byte b = 0; b < Long.BYTES; b++)
		{
			l |= (long)(byte_array[b + index] & 0xFF) << (b * 8);
		}

		return l;
	}
}
