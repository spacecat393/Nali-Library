package com.nali.system.bytes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

import java.util.UUID;

public class ByteWriter
{
	public static byte[] serializeNBT(NBTTagCompound compound)
	{
		ByteBuf bytebuf = ByteBufAllocator.DEFAULT.buffer();
		ByteBufUtils.writeTag(bytebuf, compound);
		byte[] byte_array = new byte[bytebuf.readableBytes()];
		bytebuf.readBytes(byte_array);
		bytebuf.release();
		return byte_array;
	}

	public static void set(byte[] byte_array, int i, int index)
	{
//		byte_array[index] = (byte)(i & 0xFF);
//		byte_array[index + 1] = (byte)((i >> 8) & 0xFF);
//		byte_array[index + 2] = (byte)((i >> 16) & 0xFF);
//		byte_array[index + 3] = (byte)((i >> 24) & 0xFF);
		for (byte b = 0; b < Integer.BYTES; b++)
		{
			byte_array[index + b] = (byte) ((i >> (b * 8)) & 0xFF);
		}
	}

	public static void set(byte[] byte_array, short s, int index)
	{
		for (byte b = 0; b < Short.BYTES; b++)
		{
			byte_array[index + b] = (byte) ((s >> (b * 8)) & 0xFF);
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
		for (byte b = 0; b < Long.BYTES; b++)
		{
			byte_array[index + b] = (byte) ((l >> (b * 8)) & 0xFF);
		}
	}
}
