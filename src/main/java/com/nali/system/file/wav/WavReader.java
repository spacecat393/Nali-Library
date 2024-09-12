//package com.nali.system.file.wav;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//import static com.nali.Nali.debug;
//import static com.nali.Nali.error;
//import static com.nali.system.bytes.BytesReader.getInt;
//
//public class WavReader
//{
//	public static void read(String string)
//	{
//		try
//		{
//			byte[] byte_array = Files.readAllBytes(Paths.get(string));
//
//			String chunk_id = new String(byte_array, 0, 4);
//			int chunk_size = getInt(byte_array, 4);
//			String format = new String(byte_array, 4+4, 4);
//			String subchunk1_id = new String(byte_array, 4+4+4, 4);
//			int subchunk1_size = getInt(byte_array, 4+4+4+4);
//			char audio_format = getChar(byte_array, 4+4+4+4+4);
//			char num_channels = getChar(byte_array, 4+4+4+4+4+2);
//			int sample_rate = getInt(byte_array, 4+4+4+4+4+2+2);
//			int byte_rate = getInt(byte_array, 4+4+4+4+4+2+2+4);
//			char block_align = getChar(byte_array, 4+4+4+4+4+2+2+4+4);
//			char bits_per_sample = getChar(byte_array, 4+4+4+4+4+2+2+4+4+2);
//			char extra_param_size = getChar(byte_array, 4+4+4+4+4+2+2+4+4+2+2);
//			 int subchunk2_id = getInt(byte_array, 4+4+4+4+4+2+2+4+4+2+2);
////			String subchunk2_id = new String(byte_array, 4+4+4+4+4+2+2+4+4+2+2, 4);
//			int subchunk2_size = getInt(byte_array, 4+4+4+4+4+2+2+4+4+2+2+4);
//			byte[] data_byte_array = new byte[byte_array.length - (4+4+4+4+4+2+2+4+4+2+2+4+4)];
//			System.arraycopy(byte_array, 4+4+4+4+4+2+2+4+4+2+2+4+4, data_byte_array, 0, data_byte_array.length);
//
//			debug("chunk_id " + chunk_id);
//			debug("chunk_size " + chunk_size);
//			debug("format " + format);
//			debug("subchunk1_id " + subchunk1_id);
//			debug("subchunk1_size " + subchunk1_size);
//			debug("audio_format " + (short)audio_format);
//			debug("num_channels " + (short)num_channels);
//			debug("sample_rate " + sample_rate);
//			debug("byte_rate " + byte_rate);
//			debug("block_align " + (short)block_align);
//			debug("bits_per_sample " + (short)bits_per_sample);
//			debug("extra_param_size " + (short)extra_param_size);
//			debug("subchunk2_id " + subchunk2_id);
//			debug("subchunk2_size " + subchunk2_size);
//
//			debug("data_size " + data_byte_array.length);
//			// debug("data " + Arrays.toString(data_byte_array));
//
//			//1 sample == 2 bytes * num_channels
//			//0.0 - 1.0 end
//			//1 / sample_rate +-
//
//			// List<byte[]> ba = new ArrayList();
//			// int in = 0;
//
//			// for (int i = 0; i < data_byte_array.length; i += 2)
//			// {
//			//	 byte ta = data_byte_array[i];
//			//	 byte tb = data_byte_array[i + 1];
//			//	 boolean pass = true;
//			//	 for (byte[] bb : ba)
//			//	 {
//			//		 if (bb[0] == ta && bb[1] == tb)
//			//		 {
//			//			 ++in;
//			//			 pass = false;
//			//			 break;
//			//		 }
//			//	 }
//
//			//	 if (pass)
//			//	 {
//			//		 ba.add(new byte[]{ta, tb});
//			//	 }
//			// }
//			// debug("data " + (ba.size() * 2 + in));
//			// debug("in " + in);
//		}
//		catch (IOException e)
//		{
//			error(e);
//		}
//	}
//
//	public static char getChar(byte[] byte_array, int index)
//	{
//		return (char)(((byte_array[index + 1] & 0xFF) << 8) | (byte_array[index] & 0xFF));
//	}
//}
