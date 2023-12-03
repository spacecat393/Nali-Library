package com.nali.entities.object;

import com.nali.entities.data.ObjectData;
import com.nali.math.*;
import com.nali.system.DataLoader;
import com.nali.system.opengl.drawing.OpenGLObjectDrawing;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ObjectEntitiesRender<T extends ObjectEntities> extends Render<T>
{
    public ObjectEntitiesRender(RenderManager rendermanager)
    {
        super(rendermanager);
    }

    @Override
    public boolean shouldRender(T objectentities, ICamera camera, double camX, double camY, double camZ)
    {
        ObjectData objectdata = (ObjectData)objectentities.client_object;
        objectdata.should_render = super.shouldRender(objectentities, camera, camX, camY, camZ);
        return objectdata.should_render;
    }

    @Override
    public void doRender(T objectentities, double ox, double oy, double oz, float entityYaw, float partialTicks)
    {
        ObjectData objectdata = (ObjectData)objectentities.client_object;
//        EntityDataManager entitydatamanager = objectentities.getDataManager();
        this.shadowOpaque *= objectdata.float_array[0];
        this.shadowSize *= objectdata.float_array[0];

        WorldMath.WORLD_M4X4.cloneMat(objectdata.m4x4_array[0].mat, 0);
        M4x4 scale_m4x4 = new M4x4();
        float scale = objectdata.float_array[0];
        scale_m4x4.scale(scale, scale, scale);
        objectdata.m4x4_array[0].multiply(scale_m4x4.mat);

        objectdata.m4x4_array[0].translate((float)ox, (float)oy/* - eye_height + 0.03F/* - (player_sleep ? 0.3F : 0.0F)*/, (float)oz); // world

        LightingMath.set(objectentities, objectdata.rgba_float_array, partialTicks);
//        int color = ((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapColors()[0];
//        float alpha = ((color >> 24) & 0xFF) / 255.0F;
//        float red = ((color >> 16) & 0xFF) / 255.0F;
//        float green = ((color >> 8) & 0xFF) / 255.0F;
//        float blue = (color & 0xFF) / 255.0F;
//        objectdata.rgb_float_array[0] = red;
//        objectdata.rgb_float_array[1] = green;
//        objectdata.rgb_float_array[2] = blue;

        this.updateData(objectentities, objectdata, partialTicks);

//        DataLoader.OBJECTENTITIES_ARRAYLIST.add(objectentities);
        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < objectdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (objectdata.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
//                objectdata.texture_index_int_array[DataLoader.SCREEN_INDEX] = entitydatamanager.get(objectentities.getIntegerDataParameterArray()[DataLoader.SCREEN_INDEX]);
                OpenGLObjectDrawing.startObjectGL(objectentities);
            }
        }

        super.doRender(objectentities, ox, oy, oz, entityYaw, partialTicks);
    }

    public void renderOnScreen(T objectentities, float width, float height, float x, float y, float s)
    {
        ObjectData objectdata = (ObjectData)objectentities.client_object;
//        float max = 1.0F;
        float image_aspect_ratio = width / height;
//        float r = max * image_aspect_ratio;

        objectdata.m4x4_array[1] = M4x4.getOrthographic(-1.0F, 1.0F, -image_aspect_ratio, image_aspect_ratio, 0.1F, 100.0F);
        objectdata.m4x4_array[2] = new M4x4();
        objectdata.m4x4_array[3] = new M4x4();
        float new_x = (2.0F * x) / width - 1.0F;
        float new_y = 1.0F - (2.0F * y) / height;
        objectdata.m4x4_array[2].translate(new_x * image_aspect_ratio, new_y, -10.0F);
        M4x4 temp_m4x4 = new M4x4();

//        float scale = s;// * image_aspect_ratio;
        temp_m4x4.scale(s, s, s);
        objectdata.m4x4_array[2].multiply(temp_m4x4.mat);
        objectdata.m4x4_array[3] = new M4x4();
        objectdata.m4x4_array[3].multiply(new Quaternion(-1.57079632679F, 0.0F, 0.0F).getM4x4().mat);

        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < objectdata.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (objectdata.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
                OpenGLObjectDrawing.startScreenObjectGL(objectentities);
            }
        }
    }

    public void updateData(T objectentities, ObjectData objectdata, float partialTicks)
    {
        // head_rot
        objectdata.float_array[1] = (float)Math.toRadians(MixMath.invert(MixMath.interpolateRotation(objectentities.rotationYaw, objectentities.prevRotationYaw, partialTicks)));
        // head_pitch
        objectdata.float_array[2] = (float)Math.toRadians(objectentities.prevRotationPitch + (objectentities.rotationPitch - objectentities.prevRotationPitch) * partialTicks);

        this.multiplyAnimation(objectentities);
    }

    public abstract void multiplyAnimation(T skinningentities);
}
