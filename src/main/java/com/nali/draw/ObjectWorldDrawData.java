//package com.nali.draw;
//
//import com.nali.config.MyConfig;
//import com.nali.render.ObjectRender;
//import com.nali.system.Reference;
//import com.nali.system.opengl.memory.OpenGLObjectMemory;
//import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.client.event.RenderWorldLastEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL31;
//import org.lwjgl.opengl.GL33;
//
//import java.nio.FloatBuffer;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@SideOnly(Side.CLIENT)
//@Mod.EventBusSubscriber(modid = Reference.MOD_ID, value = Side.CLIENT)
//public class ObjectWorldDrawData
//{
//    public static List<FloatBuffer> PROJECTION_M4X4_FLOATBUFFER_LIST = new ArrayList();
//    public static List<FloatBuffer> MODELVIEW_M4X4_FLOATBUFFER_LIST = new ArrayList();
//    public static List<FloatBuffer> COLOR_V4_FLOATBUFFER_LIST = new ArrayList();
//    public static List<FloatBuffer> LIGHT0POSITION_V4_FLOATBUFFER_LIST = new ArrayList();
//
//    public static List<Byte> SKINNING_RENDER_LIST = new ArrayList();
//    public static byte INDEX;
//
//    public static Map<!, Integer> MODEL_MAP = new HashMap();
//    public static List<Integer> ID_INTEGER_LIST = new ArrayList();
//    public static List<Integer> STEP_INTEGER_LIST = new ArrayList();
//    public static int SIZE;
//
//    public static int add(int model, long shader, int texture, boolean skinning)
//    {
//        Integer index = MODEL_MAP.get();
//        if (index == null)
//        {
//            MODEL_MAP.put(openglobjectmemory, SIZE);
//            ID_INTEGER_LIST.add(SIZE++);
//            STEP_INTEGER_LIST.add(1);
//        }
//        else
//        {
//            STEP_INTEGER_LIST.set(index, STEP_INTEGER_LIST.get(index) + 1);
//        }
//
//        if (INDEX % 128 == 0)
//        {
//            if (skinning)
//            {
//                SKINNING_RENDER_LIST.add((byte)1);
//            }
//            else
//            {
//                SKINNING_RENDER_LIST.add((byte)0);
//            }
//            INDEX = 1;
//        }
//        else if (skinning)
//        {
//            int current_index = SKINNING_RENDER_LIST.size() - 1;
//            byte current = (byte)(SKINNING_RENDER_LIST.get(current_index) | INDEX);
//            SKINNING_RENDER_LIST.set(current_index, current);
//        }
//        INDEX *= 2;
//
//        return index;
//    }
//
//    @SubscribeEvent
//    public static void onRenderWorldLastEvent(RenderWorldLastEvent event)
//    {
//        if (MyConfig.SHADER.gl_draw_elements_instanced)
//        {
//            Object[] model_object_array = MODEL_MAP.keySet().toArray();
//            for (int i : ID_INTEGER_LIST)//part
//            {
//                OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[0], false, PROJECTION_M4X4_FLOATBUFFER_LIST.get(i));
//                OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[1], false, MODELVIEW_M4X4_FLOATBUFFER_LIST.get(i));
//                OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[3], COLOR_V4_FLOATBUFFER_LIST.get(i));
//                OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[2], LIGHT0POSITION_V4_FLOATBUFFER_LIST.get(i));
//
//                for (int l : )
//                {
//                    Object[] model_array = (Object[])model_object_array[i];
//                    OpenGLObjectMemory openglobjectmemory = this.objectrender.getMemory(i);
//                    OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
////                    GL33.glVertexAttribDivisor(i, 1);
//                    GL31.glDrawElementsInstanced(OpenGlHelper.GL_TRIANGLES, openglobjectmemory.index_length, OpenGlHelper.GL_UNSIGNED_INT, 0, 1);
//                }
//            }
//
//            PROJECTION_M4X4_FLOATBUFFER_LIST.clear();
//            MODELVIEW_M4X4_FLOATBUFFER_LIST.clear();
//            COLOR_V4_FLOATBUFFER_LIST.clear();
//            LIGHT0POSITION_V4_FLOATBUFFER_LIST.clear();
//
//            SKINNING_RENDER_LIST.clear();
//            INDEX = 0;
//
//            MODEL_MAP.clear();
//            ID_INTEGER_LIST.clear();
//            STEP_INTEGER_LIST.clear();
//            SIZE = 0;
//        }
//    }
//}
