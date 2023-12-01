package com.nali.entities.data;

import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.math.WorldMath;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLObjectBuffer;
import com.nali.system.opengl.drawing.OpenGLGUIObjectDrawing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GUIObjectData extends MainData
{
    public DataLoader dataloader;
    public float[] float_array; // body_rot head_rot net_head_yaw head_pitch
    public float[] screen_rgba_float_array;
//    public Object[] model_address_object_array;
    public int[] texture_index_int_array;
    public M4x4[] m4x4_array; // world view
    public boolean[] model_boolean_array;
    public boolean[] glow_boolean_array;

    public float s = 0.5F;
    public float rx;
    public float ry;
    public float rz;
    public float x;
    public float y;

    public GUIObjectData(DataLoader dataloader)
    {
        this.dataloader = dataloader;
        int max_part = this.getMaxPart();
        int step_models = this.getStepModels();

        this.screen_rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.float_array = new float[2];
        this.texture_index_int_array = new int[max_part];
        this.m4x4_array = new M4x4[3];

        this.model_address_object_array = new Object[max_part];
        this.model_boolean_array = new boolean[max_part];
        this.glow_boolean_array = new boolean[max_part];

        for (int i = 0; i < max_part; ++i)
        {
            this.model_boolean_array[i] = false;
        }

        System.arraycopy(this.dataloader.model_object_array, step_models, this.model_address_object_array, 0, max_part);

        for (int i = 0; i < this.m4x4_array.length; ++i)
        {
            this.m4x4_array[i] = new M4x4();
        }

        WorldMath.WORLD_M4X4.cloneMat(this.m4x4_array[0].mat, 0);
        this.setGlow();
        this.setBooleanArraylist();
    }

    public void render(float width, float height)
    {
//        ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
//        width = (float)scaledresolution.getScaledWidth_double();
//        height = (float)scaledresolution.getScaledHeight_double();

        // body_rot
        this.float_array[0] = 0.0F;
        // head_pitch
        this.float_array[1] = 0.0F;

        float max = 1.0F;
//        float image_aspect_ratio = width / height;
//        float r = max * image_aspect_ratio;

        float image_aspect_ratio = width / height;
        float r = max * image_aspect_ratio;
        this.m4x4_array[0] = M4x4.getOrthographic(-max, max, -r, r, 0.1F, 100.0F);
        this.m4x4_array[1] = new M4x4();
        this.m4x4_array[2] = new M4x4();
        float new_x = (2.0F * this.x) / width - 1.0F;
        float new_y = 1.0F - (2.0F * this.y) / height;
        this.m4x4_array[1].translate(new_x * image_aspect_ratio, new_y, -1.0F);
        M4x4 temp_m4x4 = new M4x4();
        M4x4 temp2_m4x4 = new Quaternion(this.rx, this.ry, this.rz).getM4x4();

//        float scale_factor = 0.2F / scaled_resolution.getScaleFactor();
//        float scale_factor = (0.2F * this.s) * scaledResolution.getScaleFactor();

        float scale = 0.2F * this.s * image_aspect_ratio;
        temp_m4x4.scale(scale, scale, scale);
        this.m4x4_array[2].multiply(temp_m4x4.mat);
        this.m4x4_array[2].multiply(temp2_m4x4.mat);
//        this.m4x4_array[2].multiply(new Quaternion(-1.57079632679F, 0.0F, 0.0F).getM4x4().mat);

        this.multiplyAnimation();

        for (DataLoader.SCREEN_INDEX = 0; DataLoader.SCREEN_INDEX < this.model_address_object_array.length; ++DataLoader.SCREEN_INDEX)
        {
            if (this.model_boolean_array[DataLoader.SCREEN_INDEX])
            {
                OpenGLGUIObjectDrawing.startScreenObjectGL(this);
            }
        }
    }

    @Override
    public void set(Object[] object_array)
    {
        OpenGLObjectBuffer.set(object_array);
    }

    public abstract int getMaxPart();
    public abstract int getStepModels();
    public abstract void setBooleanArraylist();
    public abstract void setGlow();
    public abstract void multiplyAnimation();
    public abstract void setUniform(Object[] object_array);
}
