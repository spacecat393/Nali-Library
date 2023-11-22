package com.nali.system.opengl.memory;

import com.nali.Nali;
import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@SideOnly(Side.CLIENT)
public class OpenGLTextureMemory
{
    public static void set(DataLoader dataloader, String mod_id_string)
    {
        Object[] object_array = new Object[2];

        File[] file_array = new File(mod_id_string + "/Textures/").listFiles();
        Object[] texture_object_array = new Object[file_array.length];
        for (int i = 0; i < file_array.length; ++i)
        {
//            FileInputStream fileinputstream = null;

            try
            {

                File file = file_array[i];
                BufferedImage bufferedimage = ImageIO.read(file);
//                String[] string_array = file.getName().split(" ");

//                fileinputstream = new FileInputStream(file);
//                byte[] byte_array = new byte[fileinputstream.available()];
//                fileinputstream.read(byte_array);
                int index = Integer.parseInt(file.getName());
                int width = bufferedimage.getWidth();
                int height = bufferedimage.getHeight();

                ByteBuffer bytebuffer = ByteBuffer.allocateDirect(width * height * 4);
                int[] pixels = new int[width * height];
                bufferedimage.getRGB(0, 0, width, height, pixels, 0, width);
                int[] new_pixels = new int[width * height];

                for (int h = 0; h < height; ++h)
                {
                    if (width >= 0)
                    {
                        System.arraycopy(pixels, h * width, new_pixels, (height - 1 - h) * width, width);
                    }
                }

                for (int pixel : new_pixels)
                {
                    bytebuffer.put((byte) ((pixel >> 16) & 0xFF));
                    bytebuffer.put((byte) ((pixel >> 8) & 0xFF));
                    bytebuffer.put((byte) (pixel & 0xFF));
                    bytebuffer.put((byte) ((pixel >> 24) & 0xFF));
                }

                bytebuffer.flip();

//                int index = Integer.parseInt(string_array[0]);
//                int width = Integer.parseInt(string_array[1]);
//                int height = Integer.parseInt(string_array[2]);
                Object[] temp_texture_object_array = new Object[3];
//                OpenGLBuffer.setTextureByteBuffer(temp_texture_object_array, 0, byte_array, width, height);
                temp_texture_object_array[0] = bytebuffer;
                temp_texture_object_array[1] = width;
                temp_texture_object_array[2] = height;

                texture_object_array[index] = temp_texture_object_array;
            }
            catch (IOException e)
            {
                Nali.LOGGER.error(e.getMessage(), e);
            }
//            finally
//            {
//                if (fileinputstream != null)
//                {
//                    try
//                    {
//                        fileinputstream.close();
//                    }
//                    catch (IOException ioexception)
//                    {
//                        Main.LOGGER.error(ioexception.getMessage(), ioexception);
//                    }
//                }
//            }
        }

        object_array[0] = texture_object_array;
        dataloader.texture_object_array = object_array;
    }
}
