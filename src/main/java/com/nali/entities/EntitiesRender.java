package com.nali.entities;

import com.nali.math.Quaternion;
import com.nali.math.WorldMath;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLObjectBuffer;
import com.nali.system.opengl.buffer.OpenGLSkinningBuffer;
import com.nali.system.opengl.buffer.OpenGLTextureBuffer;
import com.nali.system.opengl.shader.OpenGLObjectShader;
import com.nali.system.opengl.shader.OpenGLSkinningShader;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import java.util.Random;
import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public class EntitiesRender
{
    public static Consumer<Float> FREE_FUNCTION = (f) -> {};
//    public static Consumer<Float> DRAW_FUNCTION = (f) -> {};
    public static float PLUS_MAX_X = 0.00043633231F;
    public static long TIME;

    public static void set()
    {
        if (!DataLoader.FIRST_LOAD_USED)
        {
            EntitiesRender.FREE_FUNCTION = EntitiesRender::setFreeSkinning;
//            EntitiesRender.DRAW_FUNCTION = EntitiesRender::setDraw;

            WorldMath.WORLD_M4X4 = new Quaternion(1.57079632679F, -3.14159265359F, 0.0F).getM4x4();
//        WorldMath.PROJECT_M4X4 = new M4x4();
//        WorldMath.RGB_FLOAT_ARRAY = new float[3];
//            WorldMath.SCREEN_RGBA_FLOAT_ARRAY = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

            WorldMath.FREE_SKINNING_OBJECT_ARRAY = new Object[7];
            WorldMath.FREE_SKINNING_OBJECT_ARRAY[0] = 0.0F;
            WorldMath.FREE_SKINNING_OBJECT_ARRAY[1] = 0.0F;
            WorldMath.FREE_SKINNING_OBJECT_ARRAY[2] = 0.0F;
            WorldMath.FREE_SKINNING_OBJECT_ARRAY[3] = 0.0F;
            WorldMath.FREE_SKINNING_OBJECT_ARRAY[4] = 0.0F;
            WorldMath.FREE_SKINNING_OBJECT_ARRAY[5] = 0.0F;

//        DataLoader.setClientConfig(Reference.MOD_ID);
            DataLoader.setBy();

            DataLoader.FIRST_LOAD_USED = true;
        }
    }

    public static void load(DataLoader dataloader)
    {
//        FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY = new Object[3];
//        FieldsReflectLoader.reflectProjectField();

        if (dataloader.REINIT_OPENGL_BUFFER)
        {
            // try
            // {
            //     Thread.sleep(1000);
            // }
            // catch (InterruptedException interruptedexception)
            // {
            //     Main.LOGGER.error(interruptedexception.getMessage(), interruptedexception);
            // }

            OpenGLTextureBuffer.delete(dataloader.texture_object_array);

            for (int i = 0; i < dataloader.shader_object_array.length; ++i)
            {
                Object[] object_array = (Object[])dataloader.shader_object_array[i];

                if (object_array != null)
                {
                    Object[] buffer_object_array = (Object[])object_array[0];

                    if (buffer_object_array[0] != null)
                    {
                        switch (object_array.length)
                        {
                            case 4:
                            {
                                OpenGLObjectShader.delete(object_array);
                                break;
                            }
                            case 5:
                            {
                                OpenGLSkinningShader.delete(object_array);
                                break;
                            }
                            default:
                            {
                                break;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < dataloader.model_object_array.length; ++i)
            {
                Object[] object_array = (Object[])dataloader.model_object_array[i];

                if (object_array != null)
                {
                    switch (object_array.length)
                    {
                        case 7:
                        {
                            OpenGLObjectBuffer.delete(object_array);
                            break;
                        }
                        case 9:
                        {
                            OpenGLSkinningBuffer.delete(object_array);
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                }
            }

            OpenGLTextureBuffer.load(dataloader.texture_object_array);

            for (int i = 0; i < dataloader.shader_object_array.length; ++i)
            {
                Object[] object_array = (Object[])dataloader.shader_object_array[i];

                if (object_array != null)
                {
                    switch (object_array.length)
                    {
                        case 4:
                        {
                            OpenGLObjectShader.create(object_array);
                            break;
                        }
                        case 5:
                        {
                            OpenGLSkinningShader.create(object_array);

                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                }
            }

            GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, DataLoader.OPENGL_INTBUFFER);
            int buffer = DataLoader.OPENGL_INTBUFFER.get(0);

            for (int i = 0; i < dataloader.model_object_array.length; ++i)
            {
                Object[] object_array = (Object[])dataloader.model_object_array[i];

                if (object_array != null)
                {
                    switch (object_array.length)
                    {
                        case 7:
                        {
                            OpenGLObjectBuffer.load(object_array);
                            break;
                        }
                        case 9:
                        {
                            OpenGLSkinningBuffer.load(object_array);
                            break;
                        }
                        default:
                        {
                            break;
                        }
                    }
                }
            }

            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, buffer);

            dataloader.REINIT_OPENGL_BUFFER = false;
        }
    }

    public static void setFreeSkinning(float partialTicks)
    {
        World world = Minecraft.getMinecraft().world;
        Random random = world.rand;
        long time = world.getWorldTime();

        if (TIME - time != 0)
        {
//            Main.LOGGER.info(time);
            Object[] object_array = WorldMath.FREE_SKINNING_OBJECT_ARRAY;
            float x = (float)object_array[0];
            float y = (float)object_array[1];
            float z = (float)object_array[2];
            float max_x = (float)object_array[3];
            float max_y = (float)object_array[4];
            float max_z = (float)object_array[5];

            if (x > max_x - PLUS_MAX_X && x < max_x + PLUS_MAX_X)
            {
                max_x = (float)Math.toRadians(random.nextInt(2) - random.nextInt(2));
            }
            else
            {
                if (x > max_x)
                {
                    x -= PLUS_MAX_X * partialTicks;
                }
                else
                {
                    x += PLUS_MAX_X * partialTicks;
                }
            }

            if (y > max_y - PLUS_MAX_X && y < max_y + PLUS_MAX_X)
            {
                max_y = (float)Math.toRadians(random.nextInt(2) - random.nextInt(2));
            }
            else
            {
                if (y > max_y)
                {
                    y -= PLUS_MAX_X * partialTicks;
                }
                else
                {
                    y += PLUS_MAX_X * partialTicks;
                }
            }

            if (z > max_z - PLUS_MAX_X && z < max_z + PLUS_MAX_X)
            {
                max_z = (float)Math.toRadians(random.nextInt(2) - random.nextInt(2));
            }
            else
            {
                if (z > max_z)
                {
                    z -= PLUS_MAX_X * partialTicks;
                }
                else
                {
                    z += PLUS_MAX_X * partialTicks;
                }
            }

            object_array[0] = x;
            object_array[1] = y;
            object_array[2] = z;
            object_array[3] = max_x;
            object_array[4] = max_y;
            object_array[5] = max_z;
            object_array[6] = new Quaternion(x, y, z).getM4x4();
            // }
        }

        TIME = time;
    }

//    public static void setDraw(float partialTicks)
//    {
//        // far -> near
//        // opaque -> translucent -> transparent
//        for (SkinningEntities skinningentities : DataLoader.SKINNINGENTITIES_ARRAYLIST)
//        {
//            SkinningData skinningdata = (SkinningData)skinningentities.client_object;
//            for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < skinningdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
//            {
//                if (skinningdata.model_boolean_array[DataLoader.SCREEN_INDEX])
//                {
//                    skinningdata.texture_index_int_array[DataLoader.SCREEN_INDEX] = skinningentities.getDataManager().get(skinningentities.getIntegerDataParameterArray()[DataLoader.SCREEN_INDEX]);
//
//                    // for (int i = 0; i < skinning_bones_integer_arraylist.size(); ++i)
//                    // {
//                    //     int index = entity.frame * 9;
//                    //     float[] animation_float_array = animation_float_array_arraylist.get(animation_bones_integer_arraylist.get(i));
//                    //     M4x4 temp_m4x4 = new Quaternion(animation_float_array[index + 3], animation_float_array[index + 4], animation_float_array[index + 5]).getM4x4();
//                    //     M4x4 scale_m4x4 = new M4x4();
//                    //     scale_m4x4.scale(animation_float_array[index + 6], animation_float_array[index + 7], animation_float_array[index + 8]);
//                    //     temp_m4x4.multiply(scale_m4x4.mat);
//                    //     temp_m4x4.translate(animation_float_array[index], animation_float_array[index + 1], animation_float_array[index + 2]);
//                    //     temp_m4x4.cloneMat(skinning_float_array, skinning_bones_integer_arraylist.get(i) * 16);
//                    //     // System.arraycopy(temp_m4x4.mat, 0, entity.skinning_float_array_arraylist.get(x), skinning_bones_integer_arraylist.get(i) * 16, 16);
//                    // }
//
//                    OpenGLSkinningDrawing.startSkinningGL(skinningentities);
//                }
//            }
//        }
//
//        for (ObjectEntities objectentities : DataLoader.OBJECTENTITIES_ARRAYLIST)
//        {
//            ObjectData objectdata = (ObjectData)objectentities.client_object;
//            for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < objectdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
//            {
//                if (objectdata.model_boolean_array[DataLoader.SCREEN_INDEX])
//                {
//                    objectdata.texture_index_int_array[DataLoader.SCREEN_INDEX] = objectentities.getDataManager().get(objectentities.getIntegerDataParameterArray()[DataLoader.SCREEN_INDEX]);
//
//                    OpenGLObjectDrawing.startObjectGL(objectentities);
//                }
//            }
//        }
//
//        DataLoader.SKINNINGENTITIES_ARRAYLIST.clear();
//        DataLoader.OBJECTENTITIES_ARRAYLIST.clear();
//    }
}
