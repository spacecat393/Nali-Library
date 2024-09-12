//package com.nali.system.file.ogg;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
//import static com.nali.Nali.error;
//
////https://xiph.org/ogg/
//public class OggMemory
//{
//	public List<OggHeader> oggheader_arraylist = new ArrayList();
//	public OggVorbisCode oggvorbiscode;
//	public OggVorbisCommand oggvorbiscommand;
//
//	public void read(String string)
//	{
//		try
//		{
//			byte[] byte_array = Files.readAllBytes(Paths.get(string));
//
//			int index = 0;
//			do
//			{
//				OggHeader oggheader = OggHeader.get(byte_array, index);
//				this.oggheader_arraylist.add(oggheader);
//				index += 27;
//
//				index += oggheader.page_segments;
//				if (oggheader.header_type == 2)//30 size
//				{
//					this.oggvorbiscode = OggVorbisCode.get(byte_array, index);
//				}
//				else if (oggheader.page_segments == 15)//after code || framing_flag == 1
//				{
//					this.oggvorbiscommand = OggVorbisCommand.get(oggheader, byte_array, index);
//				}
//				else
//				{
//					error("END");
//				}
//
//				for (int s : oggheader.packet_sizes)
//				{
//					index += s;
//				}
//			}
//			while (index < byte_array.length - 27);
//		}
//		catch (IOException e)
//		{
//			error(e);
//		}
//	}
//}
