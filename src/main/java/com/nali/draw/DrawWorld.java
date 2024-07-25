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
    public static int DATA_SIZE;

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
        if (byte_array[4 + 4 + 4] == 0)
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
    public static void run()
    {
        RenderO.takeDefault();
        draw(FIRST_MODEL_MAP);
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
        RenderO.setDefault();
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
                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
                setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

                OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 0);
                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
                setTextureBuffer(ByteReader.getInt(byte_array, 4), (byte)(rg.state & 1));
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

                RenderO.setTransparent((byte_array[4 + 4 + 4] & 1) == 1);

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
                if ((rg.state & 8) == 8)
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

                GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
//                ObjectRender.disableBuffer(openglobjectshadermemory);
            }

            RenderO.disableBuffer(rs);
            //                GL33.glVertexAttribDivisor(i, );
//                GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, STEP_INTEGER_LIST.get());

//            step_size += step_integer_list.get(g);
        }

        //0
//        ObjectRender.setDefault();
    }
}
