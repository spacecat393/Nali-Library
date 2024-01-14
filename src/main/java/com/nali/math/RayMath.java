//package com.nali.math;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.util.math.AxisAlignedBB;
//import net.minecraft.util.math.Vec3d;
//
//public class RayMath
//{
//    // check ray offset is better than viewTarget
//    // public static boolean viewTarget(Entity entity, int i)
//	// {
//	// 	// north
//    //     if (((entity.getYRot() > 134.0F && entity.getYRot() < 226.0F) || (entity.getYRot() > -226.0F && entity.getYRot() < -134.0F)))
//    //     {
//    //         if (i == 1)
//    //         {
//    //             return true;
//    //         }
//    //         else
//    //         {
//    //             return false;
//    //         }
//    //     }
//    // 	// east
//    //     else if ((entity.getYRot() > 224.0F && entity.getYRot() < 316.0F) || (entity.getYRot() > -136.0F && entity.getYRot() < -44.0F))
//    //     {
//    //         if (i == 2)
//    //         {
//    //             return true;
//    //         }
//    //         else
//    //         {
//    //             return false;
//    //         }
//    //     }
//	// 	// west
//    //     else if ((entity.getYRot() > 44.0F && entity.getYRot() < 136.0F) || (entity.getYRot() > -316.0F && entity.getYRot() < -224.0F))
//    //     {
//    //         if (i == 3)
//    //         {
//    //             return true;
//    //         }
//    //         else
//    //         {
//    //             return false;
//    //         }
//    //     }
//    // 	// south
//    //     else
//    //     {
//    //         if (i == 4)
//    //         {
//    //             return true;
//    //         }
//    //         else
//    //         {
//    //             return false;
//    //         }
//    //     }
//	// }
//
//	// public static boolean targetsView00(Entity player, Entity entity)
//	// {
//	// 	// player:north target:south
//    //     // player:east target:west
//    //     // player:south target:north
//    //     // player:west target:east
//	// 	return (viewTarget(player, 1) && viewTarget(entity, 4)) || (viewTarget(player, 2) && viewTarget(entity, 3)) || (viewTarget(player, 4) && viewTarget(entity, 1)) || (viewTarget(player, 3) && viewTarget(entity, 2));
//	// }
//
//	// public static boolean targetsView01(Entity player, Entity entity)
//	// {
//	// 	// player:north target:west
//    //     // player:east target:north
//    //     // player:south target:east
//    //     // player:west target:south
//	// 	return (viewTarget(player, 1) && viewTarget(entity, 3)) || (viewTarget(player, 2) && viewTarget(entity, 1)) || (viewTarget(player, 4) && viewTarget(entity, 2)) || (viewTarget(player, 3) && viewTarget(entity, 4));
//	// }
//
//	// public static boolean targetsView02(Entity player, Entity entity)
//	// {
//	// 	// player:north target:east
//    //     // player:east target:south
//    //     // player:south target:west
//    //     // player:west target:north
//	// 	return (viewTarget(player, 1) && viewTarget(entity, 2)) || (viewTarget(player, 2) && viewTarget(entity, 4)) || (viewTarget(player, 4) && viewTarget(entity, 3)) || (viewTarget(player, 3) && viewTarget(entity, 1));
//	// }
//
//    // public static V3 getViewV3(float x, float y)
//    // {
//    //     float f = x * ((float)Math.PI / 180.0F);
//    //     float f1 = -y * ((float)Math.PI / 180.0F);
//    //     float f2 = (float)Math.cos(f1);
//    //     float f3 = (float)Math.sin(f1);
//    //     float f4 = (float)Math.cos(f);
//    //     float f5 = (float)Math.sin(f);
//    //     return new V3(f3 * f4, -f5, f2 * f4);
//    // }
//
////    public static boolean isFront(Entity player, Entity target)
////    {
////        float target_yaw_radians = (float)Math.toRadians(target.rotationYaw);
////        float target_pitch_radians = (float)Math.toRadians(target.rotationPitch);
////        Vec3d target_forward = new Vec3d(-Math.sin(target_yaw_radians) * Math.cos(target_pitch_radians), -Math.sin(target_pitch_radians), Math.cos(target_yaw_radians) * Math.cos(target_pitch_radians)).normalize();
////
////        float player_yaw_radians = (float)Math.toRadians(player.rotationYaw);
////        float player_pitch_radians = (float)Math.toRadians(player.rotationPitch);
////        Vec3d player_forward = new Vec3d(-Math.sin(player_yaw_radians) * Math.cos(player_pitch_radians), -Math.sin(player_pitch_radians), Math.cos(player_yaw_radians) * Math.cos(player_pitch_radians)).normalize();
////
////
////        Vec3d target_position = target.getPositionVector();
////        Vec3d player_position = player.getPositionVector();
////
////        Vec3d target2player_position_vec3d = player_position.subtract(target_position).normalize();
////        Vec3d player2target_position_vec3d = target_position.subtract(player_position).normalize();
////
////
////        double fb_target_dot_vec3d = target_forward.dotProduct(target2player_position_vec3d);
////        double fb_player_dot_vec3d = player_forward.dotProduct(player2target_position_vec3d);
////
////        // double lr_target_dot_vec3d = target_forward.cross(new Vec3d(0.0D, 1.0D, 0.0D)).dot(target2player_position_vec3d);
////        // double lr_player_dot_vec3d = player_forward.cross(new Vec3d(0.0D, 1.0D, 0.0D)).dot(player2target_position_vec3d);
////
////        return fb_target_dot_vec3d > 0.0D && fb_player_dot_vec3d > 0.0D;
////        // return fb_target_dot_vec3d < 0.0D && fb_player_dot_vec3d > 0.0D; back
////    }
//
////    public static Vec3d calculateView(float x, float y)
////    {
////        float f = x * ((float)Math.PI / 180.0F);
////        float f1 = -y * ((float)Math.PI / 180.0F);
////        float f2 = (float)Math.cos(f1);
////        float f3 = (float)Math.sin(f1);
////        float f4 = (float)Math.cos(f);
////        float f5 = (float)Math.sin(f);
////        return new Vec3d(f3 * f4, -f5, f2 * f4);
////    }
//
//    public static boolean targetsView(Entity player, AxisAlignedBB axisalignedbb)
//    {
//        byte step = 0;
//        boolean result = false;
//        Vec3d view_vec3d = player.getLookVec();
//        Vec3d start_vec3d = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
//        view_vec3d = view_vec3d.scale(0.05);
//
//        for (Vec3d end_vec3d = start_vec3d.add(view_vec3d); !result && step < 50; ++step)
//        {
//            end_vec3d = end_vec3d.add(view_vec3d);
//            result = axisalignedbb.contains(end_vec3d);
//        }
//
//        return result;
//    }
//}
