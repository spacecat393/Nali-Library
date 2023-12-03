package com.nali.entities.data;

import com.nali.entities.object.ObjectEntities;
import com.nali.math.M4x4;
import com.nali.math.WorldMath;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLObjectBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ObjectData extends MainData
{
    public DataLoader dataloader;
    public boolean should_render;
    public float[] float_array; // scale head_rot head_pitch
    public float[] rgba_float_array;
    public float[] screen_rgba_float_array;
//    public Object[] model_address_object_array;
//    public int index;
    public int[] texture_index_int_array;
    public M4x4[] m4x4_array; // world view
    public boolean[] model_boolean_array;
    public boolean[] glow_boolean_array;

    public ObjectData(DataLoader dataloader, ObjectEntities objectentities)
    {
        this.dataloader = dataloader;
        int max_part = objectentities.getMaxPart();
        int step_models = objectentities.getStepModels();

        this.rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.screen_rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.float_array = new float[3];
        this.texture_index_int_array = new int[max_part];
        this.m4x4_array = new M4x4[4];

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
        objectentities.setGlow(this);
        objectentities.setBooleanArraylist(this);
    }

    @Override
    public void set(Object[] object_array)
    {
        OpenGLObjectBuffer.set(object_array);
    }
}
