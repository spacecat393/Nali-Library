package com.nali.draw;

import com.nali.Nali;
import com.nali.list.data.NaliData;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.render.RenderO;
import com.nali.system.bytes.ByteReader;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;
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
    //no lighting time
//    public static Map<Integer, float[]> LIGHT_MAP = new HashMap();

//    public static List<Float> PROJECTION_M4X4_FLOAT_LIST = new ArrayList();
//    public static List<Float> MODELVIEW_M4X4_FLOAT_LIST = new ArrayList();
//    public static List<Float> COLOR_V4_FLOAT_LIST = new ArrayList();
//    public static List<Float> LIGHT0POSITION_V4_FLOAT_LIST = new ArrayList();
//    public static Map<Integer, float[]> SKINNING_MAP = new HashMap();
//    public static List<Float> LIGHT_V2_FLOAT_LIST = new ArrayList();
    public static List<DrawWorldData> DRAWWORLDDATA_LIST = new ArrayList();
//    public static List<Integer> FIRST_DATA_ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> SECOND_DATA_ID_INTEGER_LIST = new ArrayList();
    public static int DATA_SIZE,
    DISPLAY_WIDTH,
    DISPLAY_HEIGHT;

//    public static Map<ByteArray, List<Integer>> FIRST_MODEL_MAP = new HashMap();
    public static Map<String, byte[]> KEY_MAP = new HashMap();
    public static Map<byte[], List<Integer>> FIRST_MODEL_MAP = new HashMap();
//    public static List<Integer> FIRST_ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> FIRST_STEP_INTEGER_LIST = new ArrayList();
//    public static int FIRST_SIZE;

//    public static Map<ByteArray, List<Integer>> SECOND_MODEL_MAP = new HashMap();
    public static Map<byte[], List<Integer>> SECOND_MODEL_MAP = new HashMap();
//    public static List<Integer> SECOND_ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> SECOND_STEP_INTEGER_LIST = new ArrayList();
//    public static int SECOND_SIZE;

//    public static void add(ByteArray bytearray)
    public static void add(byte[] byte_array)
    {
//        byte[] byte_array = mtlbspd.getBytes(StandardCharsets.UTF_8);
//        if (bytearray.array[4 + 4 + 4] == 0)
        if ((byte_array[4 + 4 + 4] & 1) == 0)
        {
//            FIRST_DATA_ID_INTEGER_LIST.add(DATA_SIZE);
//            List<Integer> index_integer_list = FIRST_MODEL_MAP.get(bytearray);
            List<Integer> index_integer_list = FIRST_MODEL_MAP.get(byte_array);
            if (index_integer_list == null)
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(DATA_SIZE);
//                FIRST_MODEL_MAP.put(bytearray, arraylist);
                FIRST_MODEL_MAP.put(byte_array, arraylist);
//                FIRST_ID_INTEGER_LIST.add(FIRST_SIZE++);
//                FIRST_STEP_INTEGER_LIST.add(1);
            }
            else
            {
                index_integer_list.add(DATA_SIZE);
//                FIRST_ID_INTEGER_LIST.add(index);
//                FIRST_STEP_INTEGER_LIST.set(index, FIRST_STEP_INTEGER_LIST.get(index) + 1);
            }
        }
        else
        {
//            SECOND_DATA_ID_INTEGER_LIST.add(DATA_SIZE);
//            List<Integer> index_integer_list = SECOND_MODEL_MAP.get(bytearray);
            List<Integer> index_integer_list = SECOND_MODEL_MAP.get(byte_array);
            if (index_integer_list == null)
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(DATA_SIZE);
//                SECOND_MODEL_MAP.put(bytearray, arraylist);
                SECOND_MODEL_MAP.put(byte_array, arraylist);
//                SECOND_ID_INTEGER_LIST.add(SECOND_SIZE++);
//                SECOND_STEP_INTEGER_LIST.add(1);
            }
            else
            {
                index_integer_list.add(DATA_SIZE);
//                SECOND_ID_INTEGER_LIST.add(index);
//                SECOND_STEP_INTEGER_LIST.set(index, SECOND_STEP_INTEGER_LIST.get(index) + 1);
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

//    public static void drawFirst()
//    {
//        ObjectRender.takeDefault();
//        draw(FIRST_MODEL_MAP);
//        ObjectRender.setDefault();
//        FIRST_MODEL_MAP.clear();
//        SECOND_MODEL_MAP.clear();
//        DRAWWORLDDATA_LIST.clear();
//        DATA_SIZE = 0;
//    }

//    public static void drawSecond()
//    {
//        ObjectRender.takeDefault();
//        draw(SECOND_MODEL_MAP);
//        ObjectRender.setDefault();
//    }

//    @SubscribeEvent
//    public static void onRenderWorldLastEvent(RenderWorldLastEvent event)
    public static void init()
    {
        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);

        Minecraft minecraft = Minecraft.getMinecraft();
        int display_width = minecraft.displayWidth,
        display_height = minecraft.displayHeight;

        if (display_width != DISPLAY_WIDTH || display_height != DISPLAY_HEIGHT)
        {
            DISPLAY_WIDTH = display_width;
            DISPLAY_HEIGHT = display_height;

            init(R_MC_FRAMEBUFFER/*, R_M_RENDERBUFFER*/, R_MC_FRAMEBUFFER_TEXTURE, R_MC_RENDERBUFFER_TEXTURE);
//            init(R_MC2_FRAMEBUFFER/*, R_M_RENDERBUFFER*/, R_MC2_FRAMEBUFFER_TEXTURE, R_MC2_RENDERBUFFER_TEXTURE);
            OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_MC2_FRAMEBUFFER);

            //GL_DEPTH_COMPONENT 16 bit
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_MC2_RENDERBUFFER_TEXTURE);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);

            GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_MC2_FRAMEBUFFER_TEXTURE);
            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA16, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer)null);

            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_MC2_RENDERBUFFER_TEXTURE, 0);
            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_MC2_FRAMEBUFFER_TEXTURE, 0);

//            GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_MC_RENDERBUFFER_TEXTURE);
//            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
//
//            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_MC_RENDERBUFFER_TEXTURE, 0);

            init(R_M_FRAMEBUFFER/*, R_M_RENDERBUFFER*/, R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
            init(R_S_FRAMEBUFFER/*, R_S_RENDERBUFFER*/, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
            init(R_T_FRAMEBUFFER/*, R_T_RENDERBUFFER*/, R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
            init(R_G_FRAMEBUFFER/*, R_G_RENDERBUFFER*/, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
            init(R_TG_FRAMEBUFFER/*, R_TG_RENDERBUFFER*/, R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE);
        }
        clear(R_MC_FRAMEBUFFER);

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);

        GL30.glBlitFramebuffer
        (
            0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
            0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
            GL11.GL_DEPTH_BUFFER_BIT,
            GL11.GL_NEAREST
        );

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
    }

    public static void run()
    {
        GL11.glGetBoolean(GL11.GL_COLOR_WRITEMASK, OPENGL_BYTEBUFFER);
        //GL11.glDepthMask
//        GL11.glColorMask(true, true, true, true);
        Minecraft minecraft = Minecraft.getMinecraft();
        Framebuffer framebuffer = minecraft.getFramebuffer();
//        float[] float_array = framebuffer.framebufferColor;
//        GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
//        boolean gl_fog = GL11.glIsEnabled(GL11.GL_FOG);
//        GL11.glDisable(GL11.GL_FOG);
        RenderO.takeDefault();
        GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        R_GL_DRAW_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
        R_GL_READ_FRAMEBUFFER_BINDING = OPENGL_INTBUFFER.get(0);
//        GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
//        R_GL_RENDERBUFFER_BINDING = OPENGL_INTBUFFER.get(0);

//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, 0);
//        GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE, OPENGL_INTBUFFER);
//        int depth_texture = OPENGL_INTBUFFER.get(0);
//
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_MC_RENDERBUFFER_TEXTURE, 0);

//        if (display_width != DISPLAY_WIDTH || display_height != DISPLAY_HEIGHT)
//        {
//            DISPLAY_WIDTH = display_width;
//            DISPLAY_HEIGHT = display_height;
//
////            GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_MC_RENDERBUFFER_TEXTURE);
////            GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
////
////            OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_MC_RENDERBUFFER_TEXTURE, 0);
//
//            init(R_M_FRAMEBUFFER/*, R_M_RENDERBUFFER*/, R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
////            init(R_S_FRAMEBUFFER, R_S_RENDERBUFFER, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
////            init(R_T_FRAMEBUFFER, R_T_RENDERBUFFER, R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
////            init(R_G_FRAMEBUFFER, R_G_RENDERBUFFER, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
////            init(R_TG_FRAMEBUFFER, R_TG_RENDERBUFFER, R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE);
//        }

//        init(R_M_FRAMEBUFFER, R_M_RENDERBUFFER, R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
//        init(R_S_FRAMEBUFFER, R_S_RENDERBUFFER, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
//        init(R_T_FRAMEBUFFER, R_T_RENDERBUFFER, R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
//        init(R_G_FRAMEBUFFER, R_G_RENDERBUFFER, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
//        init(R_TG_FRAMEBUFFER, R_TG_RENDERBUFFER, R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE);
        clear(R_TG_FRAMEBUFFER/*, R_TG_RENDERBUFFER*/);
        clear(R_G_FRAMEBUFFER/*, R_G_RENDERBUFFER*/);
        clear(R_S_FRAMEBUFFER/*, R_S_RENDERBUFFER*/);
        clear(R_M_FRAMEBUFFER/*, R_M_RENDERBUFFER*/);

        draw(FIRST_MODEL_MAP);

        clear(R_T_FRAMEBUFFER/*, R_T_RENDERBUFFER*/);
        draw(SECOND_MODEL_MAP);

//        PROJECTION_M4X4_FLOAT_LIST.clear();
//        MODELVIEW_M4X4_FLOAT_LIST.clear();
//        COLOR_V4_FLOAT_LIST.clear();
//        LIGHT0POSITION_V4_FLOAT_LIST.clear();
//        SKINNING_MAP.clear();
//        LIGHT_V2_FLOAT_LIST.clear();
//        LIGHT_MAP.clear();

        DRAWWORLDDATA_LIST.clear();
//        FIRST_DATA_ID_INTEGER_LIST.clear();
//        SECOND_DATA_ID_INTEGER_LIST.clear();
        DATA_SIZE = 0;

        FIRST_MODEL_MAP.clear();
//        FIRST_ID_INTEGER_LIST.clear();
//        FIRST_STEP_INTEGER_LIST.clear();
//        FIRST_SIZE = 0;

        SECOND_MODEL_MAP.clear();
//        SECOND_ID_INTEGER_LIST.clear();
//        SECOND_STEP_INTEGER_LIST.clear();
//        SECOND_SIZE = 0;
//        }

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_GL_RENDERBUFFER_BINDING);
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depth_texture, 0);

        int main_framebuffer_texture = framebuffer.framebufferTexture;
//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//
//        GL30.glBlitFramebuffer
//        (
//            0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//            0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT,
//            GL11.GL_COLOR_BUFFER_BIT,
//            GL11.GL_NEAREST
//        );

        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_MC_FRAMEBUFFER);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);

////        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, 0);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//        drawQuadDepth(R_MC_RENDERBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
////        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);

//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC2_FRAMEBUFFER);
//        drawQuadDepth(R_MC_RENDERBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
////        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);

//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT/* | GL11.GL_DEPTH_BUFFER_BIT*/);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);

//        GL11.glDisable(GL11.GL_ALPHA_TEST);
//        GL11.glDisable(GL11.GL_DITHER);
//        GL11.glDisable(GL11.GL_LINE_SMOOTH);
//        GL11.glDisable(GL11.GL_POLYGON_OFFSET_FILL);
//        GL11.glDisable(GL11.GL_POLYGON_OFFSET_LINE);
//        GL11.glDisable(GL11.GL_POLYGON_OFFSET_POINT);
//        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
//        GL11.glDisable(GL11.GL_SCISSOR_TEST);
//        GL11.glDisable(GL11.GL_STENCIL_TEST);

//        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT/* | GL11.GL_DEPTH_BUFFER_BIT*/);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ZERO);
//        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_SRC_ALPHA);//t

//        drawQuad(R_MC_FRAMEBUFFER_TEXTURE);
//        GL11.glColorMask(true, true, true, false);
//        drawQuadBlur(R_MC_FRAMEBUFFER_TEXTURE, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
        drawQuadBlur(main_framebuffer_texture, R_MC_RENDERBUFFER_TEXTURE, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
        drawQuad(main_framebuffer_texture, R_MC_RENDERBUFFER_TEXTURE, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);

//        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC2_FRAMEBUFFER);
        drawQuadDepth(R_MC_RENDERBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
////        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_MC_FRAMEBUFFER);
        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);

//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, 0, 0);
//        GL11.glDepthMask(false);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_MC_FRAMEBUFFER);
//        drawQuadDepth(R_MC_RENDERBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
//        OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//        GL11.glDepthMask(true);
//        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_MC_RENDERBUFFER_TEXTURE, 0);

        drawQuad(main_framebuffer_texture, R_MC2_FRAMEBUFFER_TEXTURE, R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
//        drawQuad(main_framebuffer_texture, R_MC_RENDERBUFFER_TEXTURE, R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);

//        drawQuadBlur(main_framebuffer_texture, R_MC_FRAMEBUFFER_TEXTURE, R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE);
//        drawQuad(main_framebuffer_texture, R_MC_FRAMEBUFFER_TEXTURE, R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
//        drawQuad(main_framebuffer_texture, R_MC_FRAMEBUFFER_TEXTURE, R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE);

//        //outline
//        GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
//        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_SRC_ALPHA);

//        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

        //color block
//        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_ALPHA, GL11.GL_CONSTANT_COLOR);

//        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_CONSTANT_COLOR);
//        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
//        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ZERO);
//        GL11.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_SRC_COLOR);
//        GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_SRC_COLOR);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_COLOR);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_SRC_ALPHA);
//        GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_SRC_ALPHA);
//        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE);
//        GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
//        GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ONE);
//        GL11.glBlendFunc(GL11.GL_SRC_ALPHA_SATURATE, GL11.GL_ZERO);
//        drawQuadBlur(R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);

//        drawQuadBlur(minecraft.getFramebuffer().framebufferTexture, R_M_RENDERBUFFER_TEXTURE);

        RenderO.setDefault();
        OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//        if (gl_fog)
//        {
//            GL11.glEnable(GL11.GL_FOG);
//        }
//        else
//        {
//            GL11.glDisable(GL11.GL_FOG);
//        }
//        GL11.glClearColor(float_array[0], float_array[1], float_array[2], float_array[3]);
//        GL11.glColorMask(OPENGL_BYTEBUFFER.get(0) != 0, OPENGL_BYTEBUFFER.get(1) != 0, OPENGL_BYTEBUFFER.get(2) != 0, OPENGL_BYTEBUFFER.get(3) != 0);
    }

//    public static void draw(Map<ByteArray, List<Integer>> model_map)
    public static void draw(Map<byte[], List<Integer>> model_map)
    {
        //        if (MyConfig.SHADER.gl_draw_elements_instanced)
//        {
//            int group = 0;
        byte[][] keyset_byte_2d_array = model_map.keySet().toArray(new byte[0][]);
        List<Integer>[] values_integer_list = model_map.values().toArray(new ArrayList[0]);

        //0
//        ObjectRender.takeDefault();

//        int step_size = 0;
        for (int g = 0; g < keyset_byte_2d_array.length; ++g)
        {
//            byte[] byte_array = ((ByteArray)keyset_object_array[g]).array;
            byte[] byte_array = keyset_byte_2d_array[g];
            List<Integer> index_integer_list = values_integer_list[g];
//            DataLoader dataloader = DATALOADER_LIST.get(BytesReader.getInt(byte_array, 4 + 4 + 4 + 1));
//            OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)dataloader.object_array[BytesReader.getInt(byte_array, 0)];
            MemoG rg = G_LIST.get(ByteReader.getInt(byte_array, 0));
            MemoS rs = S_LIST.get(ByteReader.getInt(byte_array, 4 + 4));
//            MemoG memogo;
//                int texture_id = BytesReader.getInt(byte_array, 4);
//            OpenGLObjectShaderMemory openglobjectshadermemory = dataloader.openglobjectshadermemory_array[BytesReader.getInt(byte_array, 4 + 4)];
//            MemoS memoso;

//            if ((byte_array[4 + 4 + 4] & 2) == 2)
//            {
//                memogo = I.clientloader.stores.rg_list.get(ByteReader.getInt(byte_array, 0));
//                memoso = I.clientloader.stores.rs_list.get(ByteReader.getInt(byte_array, 4 + 4));
//            }
//            else
//            {
//                memogo = I.clientloader.storeo.rg_list.get(ByteReader.getInt(byte_array, 0));
//                memoso = I.clientloader.storeo.rs_list.get(ByteReader.getInt(byte_array, 4 + 4));
//            }
//            float lig_b = BytesReader.getFloat(byte_array, 4 + 4 + 4);
//            float lig_s = BytesReader.getFloat(byte_array, 4 + 4 + 4 + 4);
//                int animation_id = BytesReader.getInt(byte_array, 4 + 4 + 4 + 4 + 4);
//                if (animation_id != -1)

            RenderO.enableBuffer(rg, rs);
//            int max = step_integer_list.get(g);
//            for (int i = 0; i < openglobjectshadermemory.attriblocation_int_array.length; ++i)
//            {
//                GL33.glVertexAttribDivisor(openglobjectshadermemory.attriblocation_int_array[i], max);
//            }

//            //1e
//            OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
//            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//            OpenGLBuffer.setTextureBuffer(Minecraft.getMinecraft().getFramebuffer().framebufferTexture, (byte)0);

            //1
            boolean should_set_texture = false;
            OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5], 1);
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
                setTexture(rg, rs, byte_array);
                should_set_texture = true;
            }

            for (Integer integer : index_integer_list)
            {
//                ObjectRender.enableBuffer(openglobjectmemory, openglobjectshadermemory);
//                int index = step_size + id_integer_list.get(i);
//                DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(data_id_integer_list.get(step_size + i));
                DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(integer);

//                boolean animation = drawworlddata.skinning_float_array != null;
//                if (animation)
//                {
//                    OPENGL_FLOATBUFFER.limit(drawworlddata.skinning_float_array.length);
//                }

//                RenderO.setTransparent((byte_array[4 + 4 + 4] & 1) == 1);
//                if ((byte_array[4 + 4 + 4] & 1) == 1)
//                {
//                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_T_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_T_RENDERBUFFER);
//                }

//                //1
//                OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
//                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//                OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer) Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

//                //1
//                OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
//                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//                OpenGLBuffer.setTextureBuffer(BytesReader.getInt(byte_array, 4), (byte)(openglobjectmemory.state & 1));

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
                boolean glow = (rg.state & 8) == 8;
                boolean transparent = (byte_array[4 + 4 + 4] & 1) == 1;
//                if (transparent && glow)
//                {
//                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_TG_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_TG_RENDERBUFFER);
//                }
//                else if (transparent)
//                {
//                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_T_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_T_RENDERBUFFER);
//                }
//                else if (glow)
//                {
//                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_G_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_G_RENDERBUFFER);
//                }
                if (transparent)
                {
//                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_S_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_S_RENDERBUFFER);
                    clear(R_S_FRAMEBUFFER/*, R_S_RENDERBUFFER*/);
                }
                else if (glow)
                {
                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_G_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_G_RENDERBUFFER);
                }

                if (glow/* || (byte_array[4 + 4 + 4] & 1) == 1*/)
                {
                    OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//                    FloatBuffer floatbuffer = ByteBuffer.allocateDirect(2 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//                    floatbuffer.put(-1.0f);
//                    floatbuffer.put(-1.0f);
//                    floatbuffer.flip();
                    OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
//                    OpenGlHelper.glUniform2(openglobjectshadermemory.uniformlocation_int_array[7], -1.0F, -1.0F);
                }
                else
                {
                    OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_b);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_s);
                    OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//                    FloatBuffer floatbuffer = ByteBuffer.allocateDirect(2 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
//                    floatbuffer.put(drawworlddata.lig_b);
//                    floatbuffer.put(drawworlddata.lig_s);
//                    floatbuffer.flip();
                    OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
//                    OpenGlHelper.glUniform2(openglobjectshadermemory.uniformlocation_int_array[7], drawworlddata.lig_b, drawworlddata.lig_s);
                }

//                if (animation)
                if (drawworlddata.skinning_float_array != null)
                {
//                    OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)dataloader.object_array[animation_id];
//                    float[] skinning_float_array = SKINNING_MAP.get(i);
                    setFloatBuffer(drawworlddata.skinning_float_array);
                    OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
//                    OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[8], false, OPENGL_FLOATBUFFER);
                }

//                clear(R_M_FRAMEBUFFER, R_M_RENDERBUFFER);
//                OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
//                OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
//                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
                GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
//                ObjectRender.disableBuffer(openglobjectshadermemory);

                if (transparent && glow)
                {
                    RenderO.disableBuffer(rs);
                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_TG_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_TG_RENDERBUFFER);
                    drawQuad(R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
                    RenderO.enableBufferBack(rg, rs);

                    if (should_set_texture)
                    {
                        setTexture(rg, rs, byte_array);
                    }

                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
                }
                else if (transparent)
                {
                    RenderO.disableBuffer(rs);
                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_T_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_T_RENDERBUFFER);
                    drawQuad(R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
                    RenderO.enableBufferBack(rg, rs);

                    if (should_set_texture)
                    {
                        setTexture(rg, rs, byte_array);
                    }

                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
                }
                else if (glow)
                {
                    OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
//                    OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//                    OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//                    OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_GL_RENDERBUFFER_BINDING);
                }
            }

            RenderO.disableBuffer(rs);
            //                GL33.glVertexAttribDivisor(i, );
//                GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, STEP_INTEGER_LIST.get());

//            step_size += step_integer_list.get(g);
        }

        //0
//        ObjectRender.setDefault();
    }

    public static void setTexture(MemoG rg, MemoS rs, byte[] byte_array)
    {
        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5], 1);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
        setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        setTextureBuffer(ByteReader.getInt(byte_array, 4), (byte)(rg.state & 1));
    }

    public static void init(int framebuffer/*, int renderbuffer*/, int framebuffer_texture, int renderbuffer_texture)
    {
        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer);
//        OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, DISPLAY_WIDTH, DISPLAY_HEIGHT);
//        OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, renderbuffer);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderbuffer_texture);
        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);

        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, framebuffer_texture, 0);
        OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, renderbuffer_texture, 0);
    }

    public static void clear(int framebuffer/*, int renderbuffer*/)
    {
        OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//        OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public static void drawQuad(int s_framebuffer_texture, int depth_texture/*, int s_renderbuffer_texture*/, int framebuffer_texture, int renderbuffer_texture)
    {
        MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 1);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
        GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, s_framebuffer_texture);//Minecraft.getMinecraft().getFramebuffer().framebufferTexture
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth_texture);//s_renderbuffer_texture R_MC_RENDERBUFFER_TEXTURE
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 2);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[3], 3);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderbuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        GL20.glDisableVertexAttribArray(v);
    }

    public static void drawQuadBlur(int main_framebuffer_texture, int depth_texture, int framebuffer_texture, int renderbuffer_texture)
    {
        MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 2);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
        GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, main_framebuffer_texture);//Minecraft.getMinecraft().getFramebuffer().framebufferTexture
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth_texture);//s_renderbuffer_texture R_MC_RENDERBUFFER_TEXTURE
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 2);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[3], 3);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderbuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        GL20.glDisableVertexAttribArray(v);
    }

    public static void drawQuad(int framebuffer_texture)
    {
        MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 3);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
        GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        GL20.glDisableVertexAttribArray(v);
    }

    public static void drawQuadDepth(int main_renderbuffer_texture, int own_renderbuffer_texture)
    {
        MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 4);
        OpenGlHelper.glUseProgram(rs.program);
        int v = rs.attriblocation_int_array[0];
        GL20.glEnableVertexAttribArray(v);

        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
        GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, main_renderbuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, own_renderbuffer_texture);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

        GL20.glDisableVertexAttribArray(v);
    }
}
