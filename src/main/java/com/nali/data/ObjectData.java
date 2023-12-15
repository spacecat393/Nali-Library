package com.nali.data;

import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLObjectBuffer;
import com.nali.system.opengl.drawing.OpenGLObjectDrawing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class ObjectData extends MainData
{
    public ObjectData(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);

        int max_part = bothdata.MaxPart();
        int step_models = bothdata.StepModels();

        this.screen_float_array = new float[11];

        this.rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.screen_rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.float_array = new float[3];
        this.texture_index_int_array = new int[max_part];
//        this.m4x4_array = new M4x4[4];

        this.model_address_object_array = new Object[max_part];
        this.model_boolean_array = new boolean[max_part];
        this.glow_boolean_array = new boolean[max_part];

        for (int i = 0; i < max_part; ++i)
        {
            this.model_boolean_array[i] = false;
        }

        System.arraycopy(this.dataloader.model_object_array, step_models, this.model_address_object_array, 0, max_part);

//        for (int i = 0; i < this.m4x4_array.length; ++i)
//        {
//            this.m4x4_array[i] = new M4x4();
//        }
//
//        WorldMath.WORLD_M4X4.cloneMat(this.m4x4_array[0].mat, 0);
        this.setGlow();
        this.setBooleanArraylist();
    }

    public void render()
    {
////        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
////        width = (float)scaledresolution.getScaledWidth_double();
////        height = (float)scaledresolution.getScaledHeight_double();
//
////        // body_rot
////        this.float_array[0] = 0.0F;
////        // head_pitch
////        this.float_array[1] = 0.0F;
//
////        float max = 1.0F;
////        float image_aspect_ratio = width / height;
////        float r = max * image_aspect_ratio;
//
//        float image_aspect_ratio = this.screen_float_array[0] / this.screen_float_array[1];
////        float r = max * image_aspect_ratio;
//        this.m4x4_array[0] = M4x4.getOrthographic(-1.0F, 1.0F, -image_aspect_ratio, image_aspect_ratio, 0.1F, 100.0F);
//        this.m4x4_array[1] = new M4x4();
//        this.m4x4_array[2] = new M4x4();
//        float new_x = (2.0F * this.screen_float_array[2]) / this.screen_float_array[0] - 1.0F;
//        float new_y = 1.0F - (2.0F * this.screen_float_array[3]) / this.screen_float_array[1];
//        this.m4x4_array[1].translate(new_x * image_aspect_ratio, new_y, /*-1.0F*/this.screen_float_array[4]);
//        M4x4 temp_m4x4 = new M4x4();
//        M4x4 temp2_m4x4 = new Quaternion(this.screen_float_array[5], this.screen_float_array[6], this.screen_float_array[7]).getM4x4();
//
////        float scale_factor = 0.2F / scaled_resolution.getScaleFactor();
////        float scale_factor = (0.2F * this.s) * scaledResolution.getScaleFactor();
//
////        float scale = 0.2F * this.s;// * image_aspect_ratio;
//        temp_m4x4.scale(this.screen_float_array[8], this.screen_float_array[9], this.screen_float_array[10]);
//        this.m4x4_array[2].multiply(temp_m4x4.mat);
//        this.m4x4_array[2].multiply(temp2_m4x4.mat);
////        this.m4x4_array[2].multiply(new Quaternion(-1.57079632679F, 0.0F, 0.0F).getM4x4().mat);

        float sx = (this.screen_float_array[8] == 0 ? 1.0F : this.screen_float_array[8]);
        float sy = (this.screen_float_array[9] == 0 ? 1.0F : this.screen_float_array[9]);
        float sz = (this.screen_float_array[10] == 0 ? 1.0F : this.screen_float_array[10]);
        GL11.glTranslatef(this.screen_float_array[2], this.screen_float_array[3], this.screen_float_array[4]);
        GL11.glScalef(sx, sy, sz);
        GL11.glRotatef(this.screen_float_array[5], 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(this.screen_float_array[6], 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(this.screen_float_array[7], 0.0F, 0.0F, 1.0F);
        GL11.glColor4f(this.screen_rgba_float_array[0], this.screen_rgba_float_array[1], this.screen_rgba_float_array[2], this.screen_rgba_float_array[3]);

        this.multiplyAnimation();

        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < this.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (this.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
                OpenGLObjectDrawing.startScreenObjectGL(this);
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glRotatef(-this.screen_float_array[7], 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(-this.screen_float_array[6], 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.screen_float_array[5], 1.0F, 0.0F, 0.0F);
        GL11.glScalef(1.0F / sx, 1.0F / sy, 1.0F / sz);
        GL11.glTranslatef(-this.screen_float_array[2], -this.screen_float_array[3], -this.screen_float_array[4]);
    }

    @Override
    public void set(Object[] object_array)
    {
        OpenGLObjectBuffer.set(object_array);
    }

    @Override
    public void setBooleanArraylist()
    {
        Arrays.fill(this.model_boolean_array, true);
    }

    @Override
    public void setGlow()
    {

    }

    @Override
    public void multiplyAnimation()
    {

    }

    @Override
    public void setUniform(Object[] object_array)
    {

    }
}
