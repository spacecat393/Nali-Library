package com.nali.draw;

import com.nali.Nali;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.render.RenderO;
import com.nali.system.bytes.ByteReader;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.render.RenderO.setLightMapBuffer;
import static com.nali.render.RenderO.setTextureBuffer;
import static com.nali.system.ClientLoader.G_LIST;
import static com.nali.system.ClientLoader.S_LIST;
import static com.nali.system.opengl.memo.client.MemoC.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Nali.ID, value = Side.CLIENT)
public class DrawWorld
{
    public static List<DrawWorldData> DRAWWORLDDATA_LIST = new ArrayList();
    public static int
    DATA_SIZE;

    public static Map<String, byte[]> KEY_MAP = new HashMap();
    public static Map<byte[], List<Integer>> FIRST_MODEL_MAP = new HashMap();
    public static Map<byte[], List<Integer>> SECOND_MODEL_MAP = new HashMap();
//    public static Map<byte[], List<Integer>> GLOW_MAP = new HashMap();

    public static void add(byte[] byte_array)
    {
        if ((byte_array[4 + 4 + 4] & 1) == 0)
        {
            List<Integer> index_integer_list = FIRST_MODEL_MAP.get(byte_array);
            if (index_integer_list == null)
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(DATA_SIZE);
                FIRST_MODEL_MAP.put(byte_array, arraylist);
            }
            else
            {
                index_integer_list.add(DATA_SIZE);
            }
        }
        else
        {
            List<Integer> index_integer_list = SECOND_MODEL_MAP.get(byte_array);
            if (index_integer_list == null)
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(DATA_SIZE);
                SECOND_MODEL_MAP.put(byte_array, arraylist);
            }
            else
            {
                index_integer_list.add(DATA_SIZE);
            }
        }
    }

    public static void add(float[] float_array, FloatBuffer floatbuffer)
    {
        for (int i = 0; i < floatbuffer.limit(); ++i)
        {
            float_array[i] = floatbuffer.get(i);
        }
    }

    public static void run()
    {
//        Minecraft minecraft = Minecraft.getMinecraft();
//        int display_width = minecraft.displayWidth,
//        display_height = minecraft.displayHeight;
//        if (display_width != DISPLAY_WIDTH || display_height != DISPLAY_HEIGHT)
//        {
//            GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//            R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//            GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//            R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//            GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//            R_GL_RENDERBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//            DISPLAY_WIDTH = display_width;
//            DISPLAY_HEIGHT = display_height;
//
//            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, MC_FRAMEBUFFER);
//            OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, MC_RENDERBUFFER);
//
//            OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, DISPLAY_WIDTH, DISPLAY_HEIGHT);
//            OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, MC_RENDERBUFFER);
//
//            OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_GL_RENDERBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//        }

        RenderO.takeDefault();

        draw(FIRST_MODEL_MAP);

        if (!SECOND_MODEL_MAP.isEmpty())
        {
//            GL11.glDepthMask(false);

//            GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
//            R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
////            GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////            R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//
//            GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
//            R_GL_DEPTH_ATTACHMENT = OPENGL_INTBUFFER.get(0);
//
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, MC_FRAMEBUFFER);
//
//            GL30.glBlitFramebuffer
//            (
//                0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//                0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//                GL11.GL_DEPTH_BUFFER_BIT,
//                GL11.GL_NEAREST
//            );
//
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, MC_RENDERBUFFER, 0);
////            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

            draw(SECOND_MODEL_MAP);
            SECOND_MODEL_MAP.clear();
//
//            OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
////            OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_GL_DEPTH_ATTACHMENT, 0);
        }

        DRAWWORLDDATA_LIST.clear();
        DATA_SIZE = 0;
        FIRST_MODEL_MAP.clear();

        RenderO.setDefault();
    }

    public static void draw(Map<byte[], List<Integer>> model_map)
    {
        byte[][] keyset_byte_2d_array = model_map.keySet().toArray(new byte[0][]);
        List<Integer>[] values_integer_list = model_map.values().toArray(new ArrayList[0]);

        for (int g = 0; g < keyset_byte_2d_array.length; ++g)
        {
            byte[] byte_array = keyset_byte_2d_array[g];
            List<Integer> index_integer_list = values_integer_list[g];
            MemoG rg = G_LIST.get(ByteReader.getInt(byte_array, 0));
            MemoS rs = S_LIST.get(ByteReader.getInt(byte_array, 4 + 4));

            RenderO.enableBuffer(rg, rs);

            if ((byte_array[4 + 4 + 4] & 4) == 4)//color
            {
                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
                setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

                int color = ByteReader.getInt(byte_array, 4);
                OPENGL_FIXED_PIPE_FLOATBUFFER.limit(3);
                OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
                OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
                OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
                OPENGL_FIXED_PIPE_FLOATBUFFER.put((color & 0xFF) / 255.0F);
                OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
                OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
                OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], OPENGL_FIXED_PIPE_FLOATBUFFER);
            }
            else
            {
                OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5], 1);
                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
                setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

                OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 0);
                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
                setTextureBuffer(ByteReader.getInt(byte_array, 4), (byte)(rg.state & 1));
            }

            for (Integer integer : index_integer_list)
            {
                DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(integer);

//                boolean transparent = (byte_array[4 + 4 + 4] & 1) == 1;
//                RenderO.setTransparentStart(transparent);

                OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
                put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.projection_m4x4_float, 16);
                OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
                put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.modelview_m4x4_float, 16);
                OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
                OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
                put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.color_v4_float, 4);
                OpenGlHelper.glUniform4(rs.uniformlocation_int_array[3], OPENGL_FIXED_PIPE_FLOATBUFFER);
                put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.light0position_v4_float, 4);
                OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);

                if ((rg.state & 8) == 8)
                {
                    OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
                    OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
                }
                else
                {
                    OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_b);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_s);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
                    OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
                }

                if (drawworlddata.skinning_float_array != null)
                {
                    setFloatBuffer(drawworlddata.skinning_float_array);
                    OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
                }

//                OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, rg.ebo);
                GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
            }

            RenderO.disableBuffer(rs);
        }
    }
}
