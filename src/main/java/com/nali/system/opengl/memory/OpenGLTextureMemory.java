package com.nali.system.opengl.memory;

import com.nali.Nali;
import com.nali.system.opengl.OpenGLBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_INTBUFFER;

@SideOnly(Side.CLIENT)
public class OpenGLTextureMemory
{
//    //ByteBuffer -> TextureBuffer
//    public Object[] texture_array;
//    public int[] width_int_array;
//    public int[] height_int_array;
    public int texture_buffer;

    public OpenGLTextureMemory(/*String mod_id_string*/File file)
    {
//        File[] file_array = new File(mod_id_string + "Textures/").listFiles();
//        int size = file_array.length;
//        this.texture_array = new Object[size];
//        this.width_int_array = new int[size];
//        this.height_int_array = new int[size];
//
//        for (File file : file_array)
//        {
        try
        {
            BufferedImage bufferedimage = ImageIO.read(file);

//            String name = file.getName();
//            int index = Integer.parseInt(new String(name.getBytes(), 0, name.lastIndexOf('.')));
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
                bytebuffer.put((byte)((pixel >> 16) & 0xFF));
                bytebuffer.put((byte)((pixel >> 8) & 0xFF));
                bytebuffer.put((byte)(pixel & 0xFF));
                bytebuffer.put((byte)((pixel >> 24) & 0xFF));
            }

            bytebuffer.flip();

//            this.texture_array[index] = bytebuffer;
//            this.width_int_array[index] = width;
//            this.height_int_array[index] = height;

            GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        loadBuffer();
            this.texture_buffer = OpenGLBuffer.loadTextureBuffer(bytebuffer, width, height);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, OPENGL_INTBUFFER.get(0));
        }
        catch (IOException e)
        {
            Nali.error(e);
        }
//        }
    }

//    public void loadBuffer()
//    {
//        for (int i = 0; i < this.texture_array.length; ++i)
//        {
//            this.texture_array[i] = OpenGLBuffer.loadTextureBuffer((ByteBuffer)this.texture_array[i], this.width_int_array[i], this.height_int_array[i]);
//        }
//    }

//    public void deleteBuffer()
//    {
//        for (Object o : this.texture_array)
//        {
//            GL11.glDeleteTextures((int)o);
//        }
//    }
}
