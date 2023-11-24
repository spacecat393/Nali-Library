package com.nali.entities.data;

import com.nali.entities.skinning.SkinningEntities;
import com.nali.math.M4x4;
import com.nali.math.WorldMath;
import com.nali.system.DataLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkinningData
{
    public DataLoader dataloader;
    public float[] float_array = null; // body_rot head_rot net_head_yaw head_pitch
    public float[] rgba_float_array = null;
    public float[] screen_rgba_float_array = null;
    public Object[] model_address_object_array = null;
//    public Object[] animation_address_object_array = null;
//    public int index = 0;
    public int[] frame_int_array = null;
    public int[] texture_index_int_array = null;
    public M4x4[] m4x4_array = null; // world view
    public float[] skinning_float_array = null;
    public boolean[] model_boolean_array = null;
    public boolean[] frame_boolean_array = null;
    public boolean[] glow_boolean_array = null;
    public Object[] animation_object_array = null;

    public SkinningData(DataLoader dataloader, SkinningEntities skinningentities)
    {
        this.dataloader = dataloader;
        int max_part = skinningentities.getMaxPart();
        int step_models = skinningentities.getStepModels();
        int max_array_length = skinningentities.getIntegerDataParameterArray().length - max_part;

        this.rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.screen_rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.float_array = new float[4];
        this.texture_index_int_array = new int[max_part];
        this.m4x4_array = new M4x4[4];
        this.frame_int_array = new int[max_array_length];

        this.animation_object_array = (Object[])this.dataloader.model_object_array[step_models - 1];
        this.model_address_object_array = new Object[max_part];
        this.model_boolean_array = new boolean[max_part];
        this.glow_boolean_array = new boolean[max_part];
        this.frame_boolean_array = new boolean[max_array_length];

        // this.opengl_object_array = new Object[13];

        // // if (Client.OF)
        // // {
        // //     this.opengl_intbuffer_array = new IntBuffer[11];
        // // }
        // // else
        // // {
        // this.opengl_intbuffer_array = new IntBuffer[9];
        // // }

        // for (int i = 0; i < this.opengl_intbuffer_array.length; ++i)
        // {
        //     this.opengl_intbuffer_array[i] = ByteBuffer.allocateDirect(1 << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        // }

        for (int i = 0; i < max_part; ++i)
        {
            this.model_boolean_array[i] = false;
        }

        for (int i = 1; i < max_array_length; ++i)
        {
            this.frame_boolean_array[i] = false;
        }

        System.arraycopy(this.dataloader.model_object_array, step_models, this.model_address_object_array, 0, max_part);

        this.skinning_float_array = new float[((byte[])this.animation_object_array[1]).length * 16];

        // free_skinning_object_array = new Object[7];
        // free_skinning_object_array[0] = 0.0F;
        // free_skinning_object_array[1] = 0.0F;
        // free_skinning_object_array[2] = 0.0F;
        // free_skinning_object_array[3] = 0.0F;
        // free_skinning_object_array[4] = 0.0F;
        // free_skinning_object_array[5] = 0.0F;

        for (int i = 0; i < this.m4x4_array.length; ++i)
        {
            this.m4x4_array[i] = new M4x4();
        }

        WorldMath.WORLD_M4X4.cloneMat(this.m4x4_array[0].mat, 0);
        skinningentities.setGlow(this);
        skinningentities.setBooleanArraylist(this);
    }
}
