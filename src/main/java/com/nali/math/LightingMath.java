//package com.nali.math;
//
//import com.nali.config.MyConfig;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.entity.EntityPlayerSP;
//import net.minecraft.entity.Entity;
//import net.minecraft.init.MobEffects;
//import net.minecraft.world.World;
//
//public class LightingMath
//{
//    public static int block(int i)
//    {
//        return (i & 0xFFFF) >> 4;
//    }
//
////    public static int sky(int i)
////    {
////        return i >> 20 & '\uffff';
////    }
//
//    public static void set(Entity entity, float[] rgb_float_array, float partialTicks)
//    {
//        Minecraft minecraft = Minecraft.getMinecraft();
//        EntityPlayerSP entityplayersp = minecraft.player;
////
//        if (entityplayersp.isPotionActive(MobEffects.NIGHT_VISION) || entity.isBurning())
//        {
//            rgb_float_array[0] = 1.0F;
//            rgb_float_array[1] = 1.0F;
//            rgb_float_array[2] = 1.0F;
//        }
//        else
//        {
//////            World world = entity.world;
//////            BlockPos blockpos = entity.getPosition().up(1);
//////            BlockInfo blockinfo = new BlockInfo(minecraft.getBlockColors());
//////            blockinfo.setWorld(world);
//////            blockinfo.setBlockPos(blockpos);
//////            blockinfo.setState(world.getBlockState(blockpos));
//////            blockinfo.updateShift();
//////            blockinfo.updateFlatLighting();
//////
//////            int up_side_brightness = blockinfo.getPackedLight()[1];
//////            float block = ((float)((up_side_brightness >> 0x04) & 0xF) * 0x20) / 0xFFFF;//block?
//////            float sky = ((float)((up_side_brightness >> 0x14) & 0xF) * 0x20) / 0xFFFF;//sky?
////////            float sky_light = 0.0F;
////////            float block_light = 0.0F;
////////            float light = 0.0F;
////////            if (lightmap0 > block_light) block_light = lightmap0;
////////            if (lightmap1 > sky_light) sky_light = lightmap1;
////////            light = (lightmap0 + lightmap1) / 2.0F;
//////            float light = (block + sky) / 2.0F;
//////            rgb_float_array[0] = light;
//////            rgb_float_array[1] = light;
//////            rgb_float_array[2] = light;
////
//            int light = entity.getBrightnessForRender();
//            // int sky_light = entity.getLevel().getLightLevel(LightType.SKY, entity.getBlockPos()); // get current sky light level
//            // int block_light = entity.getLevel().getLightLevel(LightType.BLOCK, entity.getBlockPos());
////            int sky_light = sky(light);
//            int block_light = block(light);
//            // float light_level = ((sky_light + block_light) / 2.0F) / 15.0F;
//            World world = entity.getEntityWorld();
//            float sun_light = world.getSunBrightnessFactor(partialTicks);
//
//            float r = (float) MyConfig.COLOR.r;//WorldMath.RGB_FLOAT_ARRAY[0];
//            float g = (float)MyConfig.COLOR.g;//WorldMath.RGB_FLOAT_ARRAY[1];
//            float b = (float)MyConfig.COLOR.b;//WorldMath.RGB_FLOAT_ARRAY[2];
//
////            float light_level = (sky_light / 15.0F + block_light / 15.0F + 0.5F) / 3.0F;
//
//            float light_level = block_light / 15.0F;
////            if (sky_light > 10)
////            {
////                light_level = ((sky_light * 1.5F + block_light / 1.5F) / 2.0F) / 15.0F;
////            }
////            else
////            {
////                light_level = ((10.0F * 1.5F + block_light / 1.5F) / 2.0F) / 15.0F;
////            }
//
//            float brightness = entity.getEntityWorld().getLightBrightness(entity.getPosition());
//
//            if (brightness < MyConfig.LIGHT.min_brightness)
//            {
//                brightness = (float)MyConfig.LIGHT.min_brightness;
//            }
//
//            if (light_level < MyConfig.LIGHT.min_light)
//            {
//                light_level = (float)MyConfig.LIGHT.min_light;
//            }
//
//            if (sun_light < MyConfig.LIGHT.min_sun_brightness_factor)
//            {
//                sun_light = (float)MyConfig.LIGHT.min_sun_brightness_factor;
//            }
//
//            float final_light = light_level * brightness;
//
//            if (sun_light == 0.0F)
//            {
//                if (light_level > 0)
//                {
//                    final_light += (float)MyConfig.LIGHT.moon_light;
//                }
//            }
//            else
//            {
//                final_light += (sun_light + (float)MyConfig.LIGHT.sun_light) / 2.0F;
//            }
//
//            if (final_light > 1.0F)
//            {
//                final_light = 1.0F;
//            }
//
//            rgb_float_array[0] = r + final_light;
//            rgb_float_array[1] = g + final_light;
//            rgb_float_array[2] = b + final_light;
//
////            if (brightness > 0.75F)
////            {
////                rgb_float_array[0] = (r * light_level + ((block_light / 15.0F) / 4.0F)) * brightness;
////                rgb_float_array[1] = (g * light_level + ((block_light / 15.0F) / 4.0F)) * brightness;
////                rgb_float_array[2] = (b * light_level - ((block_light / 15.0F) / 16.0F)) * brightness;
////            }
////            else
////            {
////                rgb_float_array[0] = (r * light_level + ((block_light / 15.0F) / 4.0F)) * 0.75F;
////                rgb_float_array[1] = (g * light_level + ((block_light / 15.0F) / 4.0F)) * 0.75F;
////                rgb_float_array[2] = (b * light_level - ((block_light / 15.0F) / 16.0F)) * 0.75F;
////            }
//
////        world.provider.getLightmapColors(partialTicks, world.getSunBrightness(1.0F), sky_light, block_light, rgb_float_array);
//
////        rgb_float_array[0] = MathHelper.clamp(rgb_float_array[0], 0f, 1f);
////        rgb_float_array[1] = MathHelper.clamp(rgb_float_array[1], 0f, 1f);
////        rgb_float_array[2] = MathHelper.clamp(rgb_float_array[2], 0f, 1f);
//        }
//    }
//}
