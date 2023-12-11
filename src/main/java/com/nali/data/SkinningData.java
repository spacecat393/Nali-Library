package com.nali.data;

import com.nali.math.M4x4;
import com.nali.math.WorldMath;
import com.nali.system.DataLoader;
import com.nali.system.opengl.buffer.OpenGLSkinningBuffer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class SkinningData extends MainData
{
//    public Object[] animation_address_object_array = null;
    public int[] frame_int_array = null;
    public float[] skinning_float_array = null;
    public boolean[] frame_boolean_array = null;
    public Object[] animation_object_array = null;

    public SkinningData(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);

        int max_part = bothdata.MaxPart();
        int step_models = bothdata.StepModels();
        int max_array_length = bothdata.MaxFrame();

        this.screen_float_array = new float[11];

        this.rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.screen_rgba_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};
        this.float_array = new float[5];
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
        this.setGlow();
        this.setBooleanArraylist();
    }

    @Override
    public void set(Object[] object_array)
    {
        OpenGLSkinningBuffer.set(object_array);
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
