//package com.nali.system.file.ogg;
//
//import static com.nali.Nali.debug;
//import static com.nali.system.bytes.BytesReader.getInt;
//
//public class OggVorbisCode
//{
//	public int vorbis_version;
//	public byte audio_channels;
//	public int audio_sample_rate;
//	public int bitrate_maximum;
//	public int bitrate_nominal;
//	public int bitrate_minimum;
//	public byte blocksize_0;
//	public byte blocksize_1;
//	public byte framing_flag;
//
//	public static OggVorbisCode get(byte[] byte_array, int index)
//	{
////		int packet_data_start_index = 27 + oggheader.page_segments;
////		if (oggheader.header_type == 2)//30
////		{
//		int size = 7;
//		String codec_identifier = new String(byte_array, index, size);
//
//		// Codec Identifier			 | Codecs Parameter
//		// -----------------------------------------------------------
//		//  char[5]: 'BBCD\0'			| dirac
//		//  char[5]: '\177FLAC'		  | flac
//		//  char[7]: '\x80theora'		| theora
//		//  char[7]: '\x01vorbis'		| vorbis
//		//  char[8]: 'CELT	'		  | celt
//		//  char[8]: 'CMML\0\0\0\0'	  | cmml
//		//  char[8]: '\213JNG\r\n\032\n' | jng
//		//  char[8]: '\x80kate\0\0\0'	| kate
//		//  char[8]: 'OggMIDI\0'		 | midi
//		//  char[8]: '\212MNG\r\n\032\n' | mng
//		//  char[8]: 'PCM	 '		  | pcm
//		//  char[8]: '\211PNG\r\n\032\n' | png
//		//  char[8]: 'Speex   '		  | speex
//		//  char[8]: 'YUV4MPEG'		  | yuv4mpeg
//		debug("codec_identifier " + codec_identifier);
//		if (codec_identifier.contains("vorbis"))
//		{
//			OggVorbisCode oggvorbis = new OggVorbisCode();
//			size += index;
//			oggvorbis.vorbis_version = getInt(byte_array, size);
//			oggvorbis.audio_channels = byte_array[size + 4];
//			oggvorbis.audio_sample_rate = getInt(byte_array, size + 4+1);
//			oggvorbis.bitrate_maximum = getInt(byte_array, size + 4+1+4);
//			oggvorbis.bitrate_nominal = getInt(byte_array, size + 4+1+4+4);
//			oggvorbis.bitrate_minimum = getInt(byte_array, size + 4+1+4+4+4);
//			oggvorbis.blocksize_0 = (byte)(byte_array[size + 4+1+4+4+4+4] & 0xF);
//			oggvorbis.blocksize_1 = (byte)((byte_array[size + 4+1+4+4+4+4] >> 4) & 0xF);
//			oggvorbis.framing_flag = (byte)(byte_array[size + 4+1+4+4+4+4+1] & 1);
//			debug("vorbis_version " + oggvorbis.vorbis_version);
//			debug("audio_channels " + oggvorbis.audio_channels);
//			debug("audio_sample_rate " + oggvorbis.audio_sample_rate);
//			debug("bitrate_maximum " + oggvorbis.bitrate_maximum);
//			debug("bitrate_nominal " + oggvorbis.bitrate_nominal);
//			debug("bitrate_minimum " + oggvorbis.bitrate_minimum);
//			debug("blocksize_0 " + oggvorbis.blocksize_0);
//			debug("blocksize_1 " + oggvorbis.blocksize_1);
//			debug("framing_flag " + oggvorbis.framing_flag);
//			return oggvorbis;
//		}
////			packet_data_start_index += size + 4+1+4+4+4+4+1+1;
////		}
////		int max = byte_array.length - packet_data_start_index;
////		while (packet_data_start_index < max)
////		{
////			for (int i = 0; i < oggheader.page_segments; i++)
////			{
////				byte[] packetData = new byte[oggheader.packet_sizes[i]];
////				System.arraycopy(byte_array, packet_data_start_index, packetData, 0, oggheader.packet_sizes[i]);
////				debug("Packet " + (i + 1) + " data: " + Arrays.toString(packetData));
////				packet_data_start_index += oggheader.packet_sizes[i];
////			}
////		}
////		debug("index " + packet_data_start_index);
////		debug("max " + max);
//		return null;
//	}
//}
