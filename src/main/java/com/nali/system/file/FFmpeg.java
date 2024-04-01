package com.nali.system.file;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class FFmpeg
{
    public static byte[] get(String string)
    {
        String[] ffmpegCommand =
        {
            "ffmpeg",
            "-i", string,
            "-f", "s16le",
            "pipe:1"
        };

        try
        {
            ProcessBuilder processbuilder = new ProcessBuilder(ffmpegCommand);
            Process process = processbuilder.start();

            InputStream inputstream = process.getInputStream();
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            byte[] buffer_byte_array = new byte[4096];

            int bytesRead;
            while ((bytesRead = inputstream.read(buffer_byte_array)) != -1)
            {
                bytearrayoutputstream.write(buffer_byte_array, 0, bytesRead);
            }

            byte[] byte_array = bytearrayoutputstream.toByteArray();

////            debug("Data " + Arrays.toString(byte_array));
//            debug("size " + byte_array.length);

            inputstream.close();
            return byte_array;
        }
        catch (IOException e)
        {
            error(e);
        }

        return null;
    }

    public static int getSampleRate(String string)
    {
        ProcessBuilder processbuilder = new ProcessBuilder("ffprobe", "-v", "error", "-select_streams", "a:0", "-show_entries", "stream=sample_rate", "-of", "default=noprint_wrappers=1:nokey=1", string);

        try
        {
            Process process = processbuilder.start();

            InputStream inputstream = process.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);

            StringBuilder stringbuilder = new StringBuilder();
            int ch;
            while ((ch = inputstreamreader.read()) != -1)
            {
                stringbuilder.append((char)ch);
            }

            process.waitFor();
            process.destroy();
            return Integer.parseInt(stringbuilder.toString().trim());
        }
        catch (IOException | NumberFormatException | InterruptedException e)
        {
            error(e);
        }

        return -1;
    }
}
