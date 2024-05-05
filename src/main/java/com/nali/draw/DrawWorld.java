//package com.nali.draw;
//
//import com.nali.config.MyConfig;
//import com.nali.mixin.IMixinEntityRenderer;
//import com.nali.render.ObjectRender;
//import com.nali.system.DataLoader;
//import com.nali.system.Reference;
//import com.nali.system.bytes.BytesReader;
//import com.nali.system.opengl.OpenGLBuffer;
//import com.nali.system.opengl.memory.OpenGLObjectMemory;
//import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.client.event.RenderWorldLastEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL13;
//import org.lwjgl.opengl.GL20;
//import org.lwjgl.opengl.GL31;
//
//import java.nio.FloatBuffer;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.nali.system.DataLoader.DATALOADER_LIST;
//import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FLOATBUFFER;
//import static com.nali.system.opengl.memory.OpenGLCurrentMemory.setFloatBuffer;
//
//@SideOnly(Side.CLIENT)
//@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
//public class DrawWorld
//{
//    public static List<FloatBuffer> PROJECTION_M4X4_FLOATBUFFER_LIST = new ArrayList();
//    public static List<FloatBuffer> MODELVIEW_M4X4_FLOATBUFFER_LIST = new ArrayList();
//    public static List<FloatBuffer> COLOR_V4_FLOATBUFFER_LIST = new ArrayList();
//    public static List<FloatBuffer> LIGHT0POSITION_V4_FLOATBUFFER_LIST = new ArrayList();
//    public static Map<Integer, float[]> SKINNING_MAP = new HashMap();
//
//    public static Map<String, Integer> MODEL_MAP = new HashMap();
//    public static List<Integer> ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> STEP_INTEGER_LIST = new ArrayList();
//    public static int SIZE;
//
//    public static int add(String mtlbspd)
//    {
//        Integer index = MODEL_MAP.get(mtlbspd);
//        if (index == null)
//        {
//            MODEL_MAP.put(mtlbspd, SIZE);
//            ID_INTEGER_LIST.add(SIZE++);
//            STEP_INTEGER_LIST.add(1);
//        }
//        else
//        {
//            STEP_INTEGER_LIST.set(index, STEP_INTEGER_LIST.get(index) + 1);
//        }
//
//        return index;
//    }
//
//    @SubscribeEvent
//    public static void onRenderWorldLastEvent(RenderWorldLastEvent event)
//    {
//        List<Integer> SORT_INTEGER_LIST = new ArrayList();
////        if (MyConfig.SHADER.gl_draw_elements_instanced)
////        {
////            int group = 0;
//        Object[] object_array = MODEL_MAP.keySet().toArray();
//        for (int g = 0; g < ; ++g)
//        {
//            byte[] byte_array = ((String)object_array[g]).getBytes();
//            DataLoader dataloader = DATALOADER_LIST.get(BytesReader.getInt(byte_array, 4 + 4 + 4 + 4 + 4 + 1));
//            OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)dataloader.object_array[BytesReader.getInt(byte_array, 0)];
////                int texture_id = BytesReader.getInt(byte_array, 4);
//            OpenGLObjectShaderMemory openglobjectshadermemory = dataloader.openglobjectshadermemory_array[BytesReader.getInt(byte_array, 4 + 4)];
//            float lig_b = BytesReader.getFloat(byte_array, 4 + 4 + 4);
//            float lig_s = BytesReader.getFloat(byte_array, 4 + 4 + 4 + 4);
////                int animation_id = BytesReader.getInt(byte_array, 4 + 4 + 4 + 4 + 4);
////                if (animation_id != -1)
//
//            //1
//            ObjectRender.takeDefault(openglobjectmemory, openglobjectshadermemory);
//
//            //1
//            OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
//            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//            OpenGLBuffer.setTextureBuffer(BytesReader.getInt(byte_array, 4), (byte)(openglobjectmemory.state & 1));
//
//            //1
//            OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
//            OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//            OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
//
//            for (int i = 0; i < STEP_INTEGER_LIST.get(g); ++i)
//            {
//                ObjectRender.setTransparent(byte_array[4 + 4 + 4 + 4 + 4] == 1);
//
//                OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[0], false, PROJECTION_M4X4_FLOATBUFFER_LIST.get());
//                OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[1], false, MODELVIEW_M4X4_FLOATBUFFER_LIST.get());
//                OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[3], COLOR_V4_FLOATBUFFER_LIST.get());
//                OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[2], LIGHT0POSITION_V4_FLOATBUFFER_LIST.get());
//
//                GL20.glUniform2f(openglobjectshadermemory.uniformlocation_int_array[6], lig_b, lig_s);
//
//                float[] skinning_float_array = SKINNING_MAP.get(i);
//                if (skinning_float_array != null)
//                {
////                    OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)dataloader.object_array[animation_id];
////                    float[] skinning_float_array = SKINNING_MAP.get(i);
//                    setFloatBuffer(skinning_float_array);
//                    OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
//                }
//
//                GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
//            }
//            //                GL33.glVertexAttribDivisor(i, );
////                GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, STEP_INTEGER_LIST.get());
//
//            //1
//            ObjectRender.setDefault(openglobjectshadermemory);
//        }
//
//        PROJECTION_M4X4_FLOATBUFFER_LIST.clear();
//        MODELVIEW_M4X4_FLOATBUFFER_LIST.clear();
//        COLOR_V4_FLOATBUFFER_LIST.clear();
//        LIGHT0POSITION_V4_FLOATBUFFER_LIST.clear();
//        SKINNING_MAP.clear();
//
//        MODEL_MAP.clear();
//        ID_INTEGER_LIST.clear();
//        STEP_INTEGER_LIST.clear();
//        SIZE = 0;
////        }
//    }
//}
