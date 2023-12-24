package com.nali.entities;

import com.nali.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;
import java.util.function.Consumer;

import static com.nali.render.SkinningRender.FREE_SKINNING_OBJECT_ARRAY;

@SideOnly(Side.CLIENT)
public class EntitiesRender
{
    public static Consumer<Float> FREE_FUNCTION = (f) -> {};
    public static float PLUS_MAX_X = 0.00043633231F;
    public static long TIME;

    public static void supportEntities()
    {
        EntitiesRender.FREE_FUNCTION = EntitiesRender::setFreeSkinning;

        FREE_SKINNING_OBJECT_ARRAY = new Object[7];
        FREE_SKINNING_OBJECT_ARRAY[0] = 0.0F;
        FREE_SKINNING_OBJECT_ARRAY[1] = 0.0F;
        FREE_SKINNING_OBJECT_ARRAY[2] = 0.0F;
        FREE_SKINNING_OBJECT_ARRAY[3] = 0.0F;
        FREE_SKINNING_OBJECT_ARRAY[4] = 0.0F;
        FREE_SKINNING_OBJECT_ARRAY[5] = 0.0F;
    }

    public static void setFreeSkinning(float partialTicks)
    {
        World world = Minecraft.getMinecraft().world;
        Random random = world.rand;
        long time = world.getWorldTime();

        if (TIME - time != 0)
        {
            Object[] object_array = FREE_SKINNING_OBJECT_ARRAY;
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
        }

        TIME = time;
    }
}
