package com.nali.draw;

import com.nali.mixin.IMixinEntityRenderer;
import com.nali.render.ObjectRender;
import com.nali.system.DataLoader;
import com.nali.system.Reference;
import com.nali.system.bytes.ByteArray;
import com.nali.system.bytes.BytesReader;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nali.system.DataLoader.DATALOADER_LIST;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
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

    public static Map<ByteArray, List<Integer>> FIRST_MODEL_MAP = new HashMap();
//    public static List<Integer> FIRST_ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> FIRST_STEP_INTEGER_LIST = new ArrayList();
//    public static int FIRST_SIZE;

    public static Map<ByteArray, List<Integer>> SECOND_MODEL_MAP = new HashMap();
//    public static List<Integer> SECOND_ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> SECOND_STEP_INTEGER_LIST = new ArrayList();
//    public static int SECOND_SIZE;

    public static void add(ByteArray bytearray)
    {
//        byte[] byte_array = mtlbspd.getBytes(StandardCharsets.UTF_8);
        if (bytearray.array[4 + 4 + 4] == 0)
        {
//            FIRST_DATA_ID_INTEGER_LIST.add(DATA_SIZE);
            List<Integer> index_integer_list = FIRST_MODEL_MAP.get(bytearray);
            if (index_integer_list == null)
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(DATA_SIZE);
                FIRST_MODEL_MAP.put(bytearray, arraylist);
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
            List<Integer> index_integer_list = SECOND_MODEL_MAP.get(bytearray);
            if (index_integer_list == null)
            {
                ArrayList arraylist = new ArrayList();
                arraylist.add(DATA_SIZE);
                SECOND_MODEL_MAP.put(bytearray, arraylist);
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

//    @SubscribeEvent
//    public static void onRenderWorldLastEvent(RenderWorldLastEvent event)
    public static void run()
    {
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
    }

    public static void draw(Map<ByteArray, List<Integer>> model_map)
    {
        //        if (MyConfig.SHADER.gl_draw_elements_instanced)
//        {
//            int group = 0;
        Object[] keyset_object_array = model_map.keySet().toArray();
        Object[] values_object_array = model_map.values().toArray();

        //0
        ObjectRender.takeDefault();

//        int step_size = 0;
        for (int g = 0; g < keyset_object_array.length; ++g)
        {
            byte[] byte_array = ((ByteArray)keyset_object_array[g]).array;
            List<Integer> index_integer_list = (List)values_object_array[g];
            DataLoader dataloader = DATALOADER_LIST.get(BytesReader.getInt(byte_array, 4 + 4 + 4 + 1));
            OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)dataloader.object_array[BytesReader.getInt(byte_array, 0)];
//                int texture_id = BytesReader.getInt(byte_array, 4);
            OpenGLObjectShaderMemory openglobjectshadermemory = dataloader.openglobjectshadermemory_array[BytesReader.getInt(byte_array, 4 + 4)];
//            float lig_b = BytesReader.getFloat(byte_array, 4 + 4 + 4);
//            float lig_s = BytesReader.getFloat(byte_array, 4 + 4 + 4 + 4);
//                int animation_id = BytesReader.getInt(byte_array, 4 + 4 + 4 + 4 + 4);
//                if (animation_id != -1)

            ObjectRender.enableBuffer(openglobjectmemory, openglobjectshadermemory);
//            int max = step_integer_list.get(g);
//            for (int i = 0; i < openglobjectshadermemory.attriblocation_int_array.length; ++i)
//            {
//                GL33.glVertexAttribDivisor(openglobjectshadermemory.attriblocation_int_array[i], max);
//            }

            //1
            OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
            OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
//            for (int i = 0; i < max; ++i)
//            float[] skinning_float_array = drawworlddata.skinning_float_array;

            //1
            OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
            OpenGLBuffer.setTextureBuffer(BytesReader.getInt(byte_array, 4), (byte)(openglobjectmemory.state & 1));

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

                ObjectRender.setTransparent(byte_array[4 + 4 + 4] == 1);

//                //1
//                OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
//                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//                OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer) Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());

//                //1
//                OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
//                OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//                OpenGLBuffer.setTextureBuffer(BytesReader.getInt(byte_array, 4), (byte)(openglobjectmemory.state & 1));

                OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
                OpenGLBuffer.put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.projection_m4x4_float, 16);
                OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
                OpenGLBuffer.put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.modelview_m4x4_float, 16);
                OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
                OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
                OpenGLBuffer.put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.color_v4_float, 4);
                OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[3], OPENGL_FIXED_PIPE_FLOATBUFFER);
                OpenGLBuffer.put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.light0position_v4_float, 4);
                OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);
                if ((openglobjectmemory.state & 8) == 8) {
                    GL20.glUniform2f(openglobjectshadermemory.uniformlocation_int_array[6], -1.0F, -1.0F);
                } else {
                    GL20.glUniform2f(openglobjectshadermemory.uniformlocation_int_array[6], drawworlddata.lig_b, drawworlddata.lig_s);
                }

//                if (animation)
                if (drawworlddata.skinning_float_array != null) {
//                    OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)dataloader.object_array[animation_id];
//                    float[] skinning_float_array = SKINNING_MAP.get(i);
                    setFloatBuffer(drawworlddata.skinning_float_array);
                    OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
                }

                GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
//                ObjectRender.disableBuffer(openglobjectshadermemory);
            }

            ObjectRender.disableBuffer(openglobjectshadermemory);
            //                GL33.glVertexAttribDivisor(i, );
//                GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, STEP_INTEGER_LIST.get());

//            step_size += step_integer_list.get(g);
        }

        //0
        ObjectRender.setDefault();
    }
}
