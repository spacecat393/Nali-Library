package com.nali.render;

import com.nali.data.BothData;
import com.nali.math.M4x4;
import com.nali.system.DataLoader;
import com.nali.system.opengl.memory.OpenGLAnimationMemory;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FLOATBUFFER;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.setFloatBuffer;

@SideOnly(Side.CLIENT)
public class SkinningRender extends ObjectRender
{
//    public static Object[] FREE_SKINNING_OBJECT_ARRAY;
    public int[] frame_int_array;
    public float[] skinning_float_array;
    public boolean[] frame_boolean_array;
    public OpenGLAnimationMemory openglanimationmemory;

    public SkinningRender(BothData bothdata, DataLoader dataloader)
    {
        super(bothdata, dataloader);

        int step_models = bothdata.StepModels();
        int max_array_length = bothdata.MaxFrame();

        this.frame_int_array = new int[max_array_length];

        this.openglanimationmemory = (OpenGLAnimationMemory)this.dataloader.memory_object_array[step_models - 1];
        this.frame_boolean_array = new boolean[max_array_length];

        for (int i = 1; i < max_array_length; ++i)
        {
            this.frame_boolean_array[i] = false;
        }

        this.skinning_float_array = new float[this.openglanimationmemory.bones * 16];

        this.setFrame();
    }

    public void setFrame()
    {

    }

    @Override
    public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
    {
        super.setUniform(openglobjectmemory, openglobjectshadermemory, index);

        setFloatBuffer(this.skinning_float_array);
        GL20.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[3], false, OPENGL_FLOATBUFFER);

//        int start_index = openglobjectshadermemory.uniformlocation_int_array.length - this.openglanimationmemory.bones;
//        for (int i = 0; i < this.skinning_float_array.length; i += 16)
//        {
//            float[] float_array = new float[16];
//
//            System.arraycopy(this.skinning_float_array, i, float_array, 0, 16);
//            setFloatBuffer(float_array);
//            GL20.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[start_index + i / 16], false, OPENGL_FLOATBUFFER);
//        }
    }

    public void initSkinning()
    {
        int max_bones = this.openglanimationmemory.bones;

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
        }

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
        }
    }

    public void setSkinning()
    {
        int max_key = this.openglanimationmemory.length;

        for (int i = 0; i < this.openglanimationmemory.bones; ++i)
        {
            M4x4.multiply(this.openglanimationmemory.transforms_float_array, this.skinning_float_array, (this.frame_int_array[0] + max_key * i) * 16, i * 16);

            for (int f = 1; f < this.frame_int_array.length; ++f)
            {
                if (this.frame_boolean_array[f - 1])
                {
                    M4x4.multiply(this.openglanimationmemory.transforms_float_array, this.skinning_float_array, (this.frame_int_array[f] + max_key * i) * 16, i * 16);
                }
            }
            M4x4.inverse(this.skinning_float_array, i * 16);
        }
    }
}
