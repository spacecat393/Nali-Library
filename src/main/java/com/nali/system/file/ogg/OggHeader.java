//package com.nali.system.file.ogg;
//
//import java.util.Arrays;
//
//import static com.nali.Nali.debug;
//import static com.nali.system.bytes.BytesReader.getInt;
//import static com.nali.system.bytes.BytesReader.getLong;
//
//public class OggHeader
//{
//    public byte version;
//    public byte header_type;
//    public long granule_position;
//    public int bitstream_serial_number;
//    public int page_sequence_number;
//    public int crc_checksum;
//    public byte page_segments;
//    public byte[] segment_table;
//    public int[] packet_sizes;
//
//    public static OggHeader get(byte[] byte_array, int index)
//    {
//        String capture_pattern = new String(byte_array, index, 4);
//        debug("capture_pattern " + capture_pattern);
//        if (capture_pattern.equals("OggS"))
//        {
//            OggHeader oggheader = new OggHeader();
//            oggheader.version = byte_array[index + 4];
//
//            // byte value
//            // 5    bitflags: 0x01: unset = fresh packet
//            //                      set = continued packet
//            //                0x02: unset = not first page of logical bitstream
//            //                      set = first page of logical bitstream (bos)
//            //                0x04: unset = not last page of logical bitstream
//            //                      set = last page of logical bitstream (eos)
//            oggheader.header_type = (byte)(byte_array[index + 4+1] & 0xFF);
//
//            oggheader.granule_position = getLong(byte_array, index + 4+1+1);
//            oggheader.bitstream_serial_number = getInt(byte_array, index + 4+1+1+8);
//            oggheader.page_sequence_number = getInt(byte_array, index + 4+1+1+8+4);
//            oggheader.crc_checksum = getInt(byte_array, index + 4+1+1+8+4+4);
//            oggheader.page_segments = (byte)(byte_array[index + 4+1+1+8+4+4+4] & 0xFF);
//
//            debug("version " + oggheader.version);
//            debug("header_type " + oggheader.header_type);
//            debug("granule_position " + oggheader.granule_position);
//            debug("bitstream_serial_number " + oggheader.bitstream_serial_number);
//            debug("page_sequence_number " + oggheader.page_sequence_number);
//            debug("crc_checksum " + oggheader.crc_checksum);
//            debug("page_segments " + oggheader.page_segments);
//
//            oggheader.segment_table = new byte[oggheader.page_segments];
//            System.arraycopy(byte_array, index + 27, oggheader.segment_table, 0, oggheader.page_segments);
//
//            debug("segment_table " + Arrays.toString(oggheader.segment_table));
//
//            oggheader.packet_sizes = new int[oggheader.page_segments];
//            for (int i = 0; i < oggheader.page_segments; i++)
//            {
//                byte s = (byte)(oggheader.segment_table[i] & 0xFF);
//                if (s == -1)
//                {
//                    s = 0;
//                }
//                oggheader.packet_sizes[i] = s;
//            }
//
//            return oggheader;
//        }
//
//        return null;
//    }
//}
