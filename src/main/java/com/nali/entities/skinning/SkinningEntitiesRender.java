package com.nali.entities.skinning;

import com.nali.entities.data.SkinningData;
import com.nali.math.*;
import com.nali.system.DataLoader;
import com.nali.system.opengl.drawing.OpenGLSkinningDrawing;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class SkinningEntitiesRender<T extends SkinningEntities> extends Render<T>
{
    public SkinningEntitiesRender(RenderManager rendermanager)
    {
        super(rendermanager);
    }

    @Override
    public void doRender(T skinningentities, double ox, double oy, double oz, float entityYaw, float partialTicks)
    {
//        Minecraft minecraft = Minecraft.getMinecraft();
        SkinningData skinningdata = (SkinningData)skinningentities.client_object;

//        FloatBuffer project_floatbuffer = (FloatBuffer)FieldsReflectLoader.getField(FieldsReflectLoader.CLIENT_REFLECT_OBJECTS_ARRAY, 0, null);
        //
//        FieldsReflectLoader.reflectRenderGlobal1ClippingHelperField();
//        ClippingHelper clippinghelper = ClippingHelperImpl.getInstance();
//        WorldMath.PROJECT_M4X4.mat = clippinghelper.clippingMatrix;
//        skinningdata.m4x4_array[1].mat = clippinghelper.modelviewMatrix;
//        float[] matrix_float_array = clippinghelper.clippingMatrix;
//        WorldMath.PROJECT_M4X4.mat[0] = matrix_float_array[0];
//        WorldMath.PROJECT_M4X4.mat[1] = matrix_float_array[4];
//        WorldMath.PROJECT_M4X4.mat[2] = matrix_float_array[8];
//        WorldMath.PROJECT_M4X4.mat[3] = matrix_float_array[12];
//        WorldMath.PROJECT_M4X4.mat[4] = matrix_float_array[1];
//        WorldMath.PROJECT_M4X4.mat[5] = matrix_float_array[5];
//        WorldMath.PROJECT_M4X4.mat[6] = matrix_float_array[9];
//        WorldMath.PROJECT_M4X4.mat[7] = matrix_float_array[13];
//        WorldMath.PROJECT_M4X4.mat[8] = matrix_float_array[2];
//        WorldMath.PROJECT_M4X4.mat[9] = matrix_float_array[6];
//        WorldMath.PROJECT_M4X4.mat[10] = matrix_float_array[10];
//        WorldMath.PROJECT_M4X4.mat[11] = matrix_float_array[14];
//        WorldMath.PROJECT_M4X4.mat[12] = matrix_float_array[3];
//        WorldMath.PROJECT_M4X4.mat[13] = matrix_float_array[7];
//        WorldMath.PROJECT_M4X4.mat[14] = matrix_float_array[11];
//        WorldMath.PROJECT_M4X4.mat[15] = matrix_float_array[15];

        // for (int i = 0; i < clippinghelper.projectionMatrix.length; ++i)
        // {
        //     Main.LOGGER.info("Mat " + i + " : " + clippinghelper.projectionMatrix[i]);
        // }
        //
        //FreeSkinning

        //

        EntityDataManager entitydatamanager = skinningentities.getDataManager();

        // MinecraftClient.getInstance().execute(() ->
//        RenderManager rendermanager = minecraft.getRenderManager();
//        Entity view_entity = minecraft.getRenderViewEntity();

        WorldMath.WORLD_M4X4.cloneMat(skinningdata.m4x4_array[0].mat, 0);
        M4x4 scale_m4x4 = new M4x4();
        float scale = entitydatamanager.get(skinningentities.getFloatDataParameterArray()[0]);
        scale_m4x4.scale(scale, scale, scale);
        skinningdata.m4x4_array[0].multiply(scale_m4x4.mat);

//        float eye_height = view_entity.getEyeHeight();
//        double xx = PomiMath.lerp((double)partialTicks, skinningentities.lastTickPosX, skinningentities.posX);
//        double yy = PomiMath.lerp((double)partialTicks, skinningentities.lastTickPosY, skinningentities.posY);
//        double zz = PomiMath.lerp((double)partialTicks, skinningentities.lastTickPosZ, skinningentities.posZ);

//        boolean player_sneak = ((EntityLivingBase)view_entity).isSneaking();

//        skinningdata.m4x4_array[0].translate((float)(rendermanager.viewerPosX - xx), (float)(yy - rendermanager.viewerPosY)/* - eye_height + 0.03F/* - (player_sleep ? 0.3F : 0.0F)*/, (float)(rendermanager.viewerPosZ - zz)); // world
        skinningdata.m4x4_array[0].translate((float)ox, (float)oy/* - eye_height + 0.03F/* - (player_sleep ? 0.3F : 0.0F)*/, (float)oz); // world
//        WorldMath.VIEW_M4X4.translatePlus((float)(rendermanager.viewerPosX - xx), (float)(yy - rendermanager.viewerPosY)/* - eye_height + 0.03F/* - (player_sleep ? 0.3F : 0.0F)*/, (float)(rendermanager.viewerPosZ - zz)); // world
//        skinningdata.m4x4_array[0].translate((float)ox, (float)oy, (float)oz); // world

        // entity.m4x4_array[2].mat = new float[] // perspective
        // {
        //     project_matrix4f.m00(), project_matrix4f.m10(), project_matrix4f.m20(), project_matrix4f.m30(),
        //     project_matrix4f.m01(), project_matrix4f.m11(), project_matrix4f.m21(), project_matrix4f.m31(),
        //     project_matrix4f.m02(), project_matrix4f.m12(), project_matrix4f.m22(), project_matrix4f.m32(),
        //     project_matrix4f.m03(), project_matrix4f.m13(), project_matrix4f.m23(), project_matrix4f.m33()
        // };

        // entity.m4x4_array[2] = WorldMath.PROJECT_M4X4;
        // entity.m4x4_array[2].mat[0] = (float)FieldsReflectLoader.getField(temp_object_array, 0, project_matrix4f);
        // entity.m4x4_array[2].mat[1] = (float)FieldsReflectLoader.getField(temp_object_array, 4, project_matrix4f);
        // entity.m4x4_array[2].mat[2] = (float)FieldsReflectLoader.getField(temp_object_array, 8, project_matrix4f);
        // entity.m4x4_array[2].mat[3] = (float)FieldsReflectLoader.getField(temp_object_array, 12, project_matrix4f);
        // entity.m4x4_array[2].mat[4] = (float)FieldsReflectLoader.getField(temp_object_array, 1, project_matrix4f);
        // entity.m4x4_array[2].mat[5] = (float)FieldsReflectLoader.getField(temp_object_array, 5, project_matrix4f);
        // entity.m4x4_array[2].mat[6] = (float)FieldsReflectLoader.getField(temp_object_array, 9, project_matrix4f);
        // entity.m4x4_array[2].mat[7] = (float)FieldsReflectLoader.getField(temp_object_array, 13, project_matrix4f);
        // entity.m4x4_array[2].mat[8] = (float)FieldsReflectLoader.getField(temp_object_array, 2, project_matrix4f);
        // entity.m4x4_array[2].mat[9] = (float)FieldsReflectLoader.getField(temp_object_array, 6, project_matrix4f);
        // entity.m4x4_array[2].mat[10] = (float)FieldsReflectLoader.getField(temp_object_array, 10, project_matrix4f);
        // entity.m4x4_array[2].mat[11] = (float)FieldsReflectLoader.getField(temp_object_array, 14, project_matrix4f);
        // entity.m4x4_array[2].mat[12] = (float)FieldsReflectLoader.getField(temp_object_array, 3, project_matrix4f);
        // entity.m4x4_array[2].mat[13] = (float)FieldsReflectLoader.getField(temp_object_array, 7, project_matrix4f);
        // entity.m4x4_array[2].mat[14] = (float)FieldsReflectLoader.getField(temp_object_array, 11, project_matrix4f);
        // entity.m4x4_array[2].mat[15] = (float)FieldsReflectLoader.getField(temp_object_array, 15, project_matrix4f);

        // body_rot
        skinningdata.float_array[0] = (float)Math.toRadians(MixMath.invert(MixMath.interpolateRotation(skinningentities.prevRenderYawOffset, skinningentities.renderYawOffset, partialTicks)));
        // head_rot
        skinningdata.float_array[1] = (float)Math.toRadians(MixMath.invert(MixMath.interpolateRotation(skinningentities.prevRotationYawHead, skinningentities.rotationYawHead, partialTicks)));
        // net_head_yaw
        skinningdata.float_array[2] = skinningdata.float_array[1] - skinningdata.float_array[0];
        // head_pitch
        skinningdata.float_array[3] = (float)Math.toRadians(skinningentities.prevRotationPitch + (skinningentities.rotationPitch - skinningentities.prevRotationPitch) * partialTicks);

        for (int i = 0; i < skinningdata.frame_int_array.length; ++i)
        {
            skinningdata.frame_int_array[i] = entitydatamanager.get(skinningentities.getIntegerDataParameterArray()[skinningentities.getMaxPart() + i]);
        }

        //

        LightingMath.set(skinningentities, skinningdata.rgb_float_array, partialTicks);

        int max_bones = (int)((Object[])((Object[])((Object[])skinningdata.model_address_object_array[0])[6])[0])[4];

        //

        float[] animation_float_array = (float[])skinningdata.animation_object_array[0];
        byte[] bones_byte_array = (byte[])skinningdata.animation_object_array[1];
        int max_key = (int)skinningdata.animation_object_array[2];

        // for (skinningdata.index = 0; skinningdata.index < entity.model_object_array.length; ++skinningdata.index)
        // {
            // if (entity.model_boolean_array[skinningdata.index])
            // {
                // int[] skinning_bones_int_array = (int[])((Object[])entity.model_object_array[skinningdata.index])[15];

                for (int i = 0; i < max_bones; ++i)
                {
                    System.arraycopy(M4x4.IDENTITY, 0, skinningdata.skinning_float_array, i * 16, 16);
                }
            // }
        // }

        this.multiplyAnimation(skinningentities);

        // ArrayList<M4x4> visualbones_m4x4_arraylist = (ArrayList<M4x4>)entity.object_array_arraylist.get(x)[17];

        // int[] skinning_bones_int_array = (int[])((Object[])entity.model_object_array[skinningdata.index])[15];
        // int[] animation_bones_int_array = (int[])((Object[])entity.model_object_array[skinningdata.index])[16];

        for (int z = 0; z < bones_byte_array.length; ++z)
        {
            if (bones_byte_array[z] != 0)
            {
                // Object[] object_array = entity.free_skinning_object_array_arraylist.get(0); // we need more id for do more free idle but it won't worth
                ((M4x4)WorldMath.FREE_SKINNING_OBJECT_ARRAY[6]).multiply(skinningdata.skinning_float_array, z * 16);
            }
        }

        // this.animate(entity);

        for (int i = 0; i < max_bones; ++i)
        {
            // M4x4[] animation_m4x4_array = animation_m4x4_array_arraylist.get(animation_bones_int_array[i]);
            // M4x4 temp0_m4x4 = new M4x4();
            // M4x4 temp1_m4x4 = new M4x4();

            M4x4.multiply(animation_float_array, skinningdata.skinning_float_array, (skinningdata.frame_int_array[0] + max_key * i) * 16, i * 16);

            // animation_m4x4_array[entity.int_array[1]].multiply(skinning_float_array, skinning_bones_int_array[i]);
            // animation_m4x4_array[entity.int_array[1]].multiply(skinning_float_array, skinning_bones_integer_arraylist.get(i));
            // animation_m4x4_array[entity.frame_int_array[0]].cloneMat(temp0_m4x4.mat, 0);
            // temp0_m4x4.inverse();
            // temp0_m4x4.mat[3] -= visualbones_m4x4_arraylist.get(skinning_bones_integer_arraylist.get(i) / 16).mat[3];
            // temp0_m4x4.mat[7] -= visualbones_m4x4_arraylist.get(skinning_bones_integer_arraylist.get(i) / 16).mat[7];
            // temp0_m4x4.mat[11] -= visualbones_m4x4_arraylist.get(skinning_bones_integer_arraylist.get(i) / 16).mat[11];
            // temp0_m4x4.inverse();

            for (int f = 1; f < skinningdata.frame_int_array.length; ++f)
            {
                if (skinningdata.frame_boolean_array[f - 1])
                {
                    M4x4.multiply(animation_float_array, skinningdata.skinning_float_array, (skinningdata.frame_int_array[f] + max_key * i) * 16, i * 16);
                    // animation_m4x4_array[entity.int_array[f]].multiply(skinning_float_array, skinning_bones_int_array[i]);
                }
            }
            // animation_m4x4_array[entity.frame_int_array[1]].cloneMat(temp1_m4x4.mat, 0);
            // temp1_m4x4.inverse();
            // temp1_m4x4.mat[3] -= visualbones_m4x4_arraylist.get(skinning_bones_integer_arraylist.get(i) / 16).mat[3];
            // temp1_m4x4.mat[7] -= visualbones_m4x4_arraylist.get(skinning_bones_integer_arraylist.get(i) / 16).mat[7];
            // temp1_m4x4.mat[11] -= visualbones_m4x4_arraylist.get(skinning_bones_integer_arraylist.get(i) / 16).mat[11];
            // temp1_m4x4.inverse();
            // M4x4.inverse(skinning_float_array, skinning_bones_integer_arraylist.get(i));
            // temp0_m4x4.multiply(temp1_m4x4.mat);

            // temp0_m4x4.multiply(skinning_float_array, skinning_bones_integer_arraylist.get(i));
            // System.arraycopy(temp_m4x4.mat, 0, entity.skinning_float_array_arraylist.get(x), skinning_bones_integer_arraylist.get(i) * 16, 16);
        }

        // float[] bindposes_float_array = (float[])((Object[])entity.model_object_array[skinningdata.index])[13];
        // Object[] bindposes_bones_object_array = (Object[])((Object[])entity.model_object_array[skinningdata.index])[14];

        // float[] new_skinning_float_array = new float[skinning_float_array.length];

        // for (int i = 0; i < skinning_bones_int_array.length; ++i)
        // {
        //     int[] bindposes_bones_int_array = (int[])bindposes_bones_object_array[skinning_bones_int_array[i] / 16];//i
        //     int index = skinning_bones_int_array[i];//i * 16

        //     System.arraycopy(M4x4.IDENTITY, 0, new_skinning_float_array, index, 16);

        //     for (int x = 0; x < bindposes_bones_int_array.length; ++x)
        //     {
        //         M4x4.multiply(bindposes_float_array, new_skinning_float_array, bindposes_bones_int_array[x] * 16, index);
        //         // M4x4.multiply(skinning_float_array, new_skinning_float_array, bindposes_bones_int_array[x] * 16, index);
        //         // M4x4.inverse(new_skinning_float_array, index);
        //     }
        // }

        // entity.skinning_object_array[skinningdata.index] = new_skinning_float_array;



        // for (skinningdata.index = 0; skinningdata.index < entity.model_object_array.length; ++skinningdata.index)
        // {
        //     if (entity.model_boolean_array[skinningdata.index])
        //     {
        //         int[] skinning_bones_int_array = (int[])((Object[])entity.model_object_array[skinningdata.index])[15];

        //         for (int i = 0; i < skinning_bones_int_array.length; ++i)
        //         {
        //             float[] skinning_float_array = (float[])entity.skinning_object_array[skinningdata.index];
        //             System.arraycopy(M4x4.IDENTITY, 0, skinning_float_array, i * 16, 16);
        //         }
        //     }
        // }

        // this.multiplyAnimation(entity);

        //!
        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < skinningdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (skinningdata.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
                skinningdata.texture_index_int_array[DataLoader.SCREEN_INDEX] = entitydatamanager.get(skinningentities.getIntegerDataParameterArray()[DataLoader.SCREEN_INDEX]);

                // for (int i = 0; i < skinning_bones_integer_arraylist.size(); ++i)
                // {
                //     int index = entity.frame * 9;
                //     float[] animation_float_array = animation_float_array_arraylist.get(animation_bones_integer_arraylist.get(i));
                //     M4x4 temp_m4x4 = new Quaternion(animation_float_array[index + 3], animation_float_array[index + 4], animation_float_array[index + 5]).getM4x4();
                //     M4x4 scale_m4x4 = new M4x4();
                //     scale_m4x4.scale(animation_float_array[index + 6], animation_float_array[index + 7], animation_float_array[index + 8]);
                //     temp_m4x4.multiply(scale_m4x4.mat);
                //     temp_m4x4.translate(animation_float_array[index], animation_float_array[index + 1], animation_float_array[index + 2]);
                //     temp_m4x4.cloneMat(skinning_float_array, skinning_bones_integer_arraylist.get(i) * 16);
                //     // System.arraycopy(temp_m4x4.mat, 0, entity.skinning_float_array_arraylist.get(x), skinning_bones_integer_arraylist.get(i) * 16, 16);
                // }

                OpenGLSkinningDrawing.startSkinningGL(skinningentities);
            }
        }

//        float max = 4F;
//        float image_aspect_ratio = (float)100.0F / (float)100.0F;
//        float r = max * image_aspect_ratio, t = max;
//        float l = -r, b = -t;
//        skinningdata.m4x4_array[2] = M4x4.getOrthographic(b, t, l, r, 0.1F, 100.0F);
//        skinningdata.m4x4_array[3] = new M4x4();
//        skinningdata.m4x4_array[4] = new M4x4();
//        skinningdata.m4x4_array[4].translate(0.5F, 20.0F / 50 + 0.5F / 50, -10.0F);
//        M4x4 temp_m4x4 = new M4x4();
//        float hs = 300.0F / 50;
//        temp_m4x4.scale(hs, hs, hs);
//        skinningdata.m4x4_array[4].multiply(temp_m4x4.mat);
//        skinningdata.m4x4_array[4].multiply(new Quaternion(-1.57079632679F, 0.0F, 0.0F).getM4x4().mat);
//
//        for (int i = 0; i < skinningdata.model_address_object_array.length; ++i)
//        {
//            SkinningData.SCREEN_INDEX = i;
//            if (skinningdata.model_boolean_array[SkinningData.SCREEN_INDEX])
//            {
//                OpenGLSkinningDrawing.startScreenSkinningGL(skinningentities);
//            }
//        }

//        WorldMath.VIEW_M4X4.translatePlus(-(float)(rendermanager.viewerPosX - xx), -(float)(yy - rendermanager.viewerPosY)/* - eye_height + 0.03F/* - (player_sleep ? 0.3F : 0.0F)*/, -(float)(rendermanager.viewerPosZ - zz)); // world
        super.doRender(skinningentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void renderOnScreen(T skinningentities, float width, float height, float x, float y)
    {
        SkinningData skinningdata = (SkinningData)skinningentities.client_object;
//        float max = 4F;
//        float image_aspect_ratio = (float)minecraft.displayWidth / (float)minecraft.displayHeight;
//        float r = max * image_aspect_ratio;
//        skinningdata.m4x4_array[1] = M4x4.getOrthographic(-max, max, -r, r, 0.1F, 100.0F);
//        skinningdata.m4x4_array[2] = new M4x4();
//        skinningdata.m4x4_array[3] = new M4x4();
//        skinningdata.m4x4_array[3].translate(x, 450.0F / height + y / height, -10.0F);
//        M4x4 temp_m4x4 = new M4x4();
//        float hs = 300.0F / height;
//        temp_m4x4.scale(hs, hs, hs);
//        skinningdata.m4x4_array[3].multiply(temp_m4x4.mat);
//        skinningdata.m4x4_array[3].multiply(new Quaternion(-1.57079632679F, 0.0F, 0.0F).getM4x4().mat);
//
//        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < skinningdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
//        {
//            if (skinningdata.model_boolean_array[DataLoader.SCREEN_INDEX])
//            {
//                OpenGLSkinningDrawing.startScreenSkinningGL(skinningentities);
//            }
//        }

        float max = 1.0F;
//        float image_aspect_ratio = width / height;
//        float r = max * image_aspect_ratio;

        skinningdata.m4x4_array[1] = M4x4.getOrthographic(-max, max, -max, max, 0.1F, 100.0F);
        skinningdata.m4x4_array[2] = new M4x4();
        skinningdata.m4x4_array[3] = new M4x4();
        float new_x = (2.0F * x) / width - 1.0F;
        float new_y = 1.0F - (2.0F * y) / height;
        skinningdata.m4x4_array[2].translate(new_x, new_y, -10.0F);
        M4x4 temp_m4x4 = new M4x4();
//        M4x4 temp2_m4x4 = new Quaternion(this.rx, this.ry, this.rz).getM4x4();

        temp_m4x4.scale(0.2F/* * this.s*/, 0.3F/* * this.s*/, 1.0F);
        skinningdata.m4x4_array[2].multiply(temp_m4x4.mat);
//        skinningdata.m4x4_array[2].multiply(temp2_m4x4.mat);
        skinningdata.m4x4_array[3] = new M4x4();
        skinningdata.m4x4_array[3].multiply(new Quaternion(-1.57079632679F, 0.0F, 0.0F).getM4x4().mat);

        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < skinningdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (skinningdata.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
                OpenGLSkinningDrawing.startScreenSkinningGL(skinningentities);
            }
        }
    }

    // public void drawIcon(T skinningentities, PoseStack posestack, int x, int y)
    // {
    //     RenderSystem.setShaderTexture(0, this.getEntityTexture(skinningentities));
    //     GuiComponent.blit(posestack, x + 152, y + 28, 0, 0, 0, 27, 27, 27, 27);
    // }

    public abstract void multiplyAnimation(T skinningentities);
}
