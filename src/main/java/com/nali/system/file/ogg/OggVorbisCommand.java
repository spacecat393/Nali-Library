//package com.nali.system.file.ogg;
//
//import static com.nali.Nali.debug;
//
//public class OggVorbisCommand
//{
////	public String title,
////	version,
////	album,
////	tracknumber,
////	artist,
////	performer,
////	copyright,
////	license,
////	organization,
////	description,
////	genre,
////	date,
////	location,
////	contact,
////	isrc;
//	public String[] string_array = new String[15];
//
//	public static OggVorbisCommand get(OggHeader oggheader, byte[] byte_array, int index)
//	{
//		OggVorbisCommand oggvorbiscommand = new OggVorbisCommand();
//		for (int i = 0; i < 15; ++i)
//		{
//			oggvorbiscommand.string_array[i] = new String(byte_array, index, oggheader.packet_sizes[i]);
//			index += oggheader.packet_sizes[i];
//		}
//		debug("title " + oggvorbiscommand.string_array[0]);
//		debug("version " + oggvorbiscommand.string_array[1]);
//		debug("album " + oggvorbiscommand.string_array[2]);
//		debug("tracknumber " + oggvorbiscommand.string_array[3]);
//		debug("artist " + oggvorbiscommand.string_array[4]);
//		debug("performer " + oggvorbiscommand.string_array[5]);
//		debug("copyright " + oggvorbiscommand.string_array[6]);
//		debug("license " + oggvorbiscommand.string_array[7]);
//		debug("organization " + oggvorbiscommand.string_array[8]);
//		debug("description " + oggvorbiscommand.string_array[9]);
//		debug("genre " + oggvorbiscommand.string_array[10]);
//		debug("date " + oggvorbiscommand.string_array[11]);
//		debug("location " + oggvorbiscommand.string_array[12]);
//		debug("contact " + oggvorbiscommand.string_array[13]);
//		debug("isrc " + oggvorbiscommand.string_array[14]);
//		return oggvorbiscommand;
//	}
//}
