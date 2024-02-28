package com.nali.render;

import com.nali.data.BothData;
import com.nali.math.M4x4;
import com.nali.system.DataLoader;
import com.nali.system.opengl.memory.OpenGLAnimationMemory;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL20;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FLOATBUFFER;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.setFloatBuffer;

@SideOnly(Side.CLIENT)
public class SkinningRender extends ObjectRender
{
    public int[] frame_int_array, current_frame_int_array;
    public float[] skinning_float_array, timeline_float_array, final_timeline_float_array, current_mat4 = new float[16];
    public boolean[] frame_boolean_array;
    public OpenGLAnimationMemory openglanimationmemory;

    public long last_time = Minecraft.getSystemTime();

    public SkinningRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, DataLoader dataloader)
    {
        super(entitiesrendermemory, bothdata, dataloader);

        int step_models = bothdata.StepModels();
        int max_array_length = bothdata.MaxFrame();

        this.frame_int_array = new int[max_array_length];
        this.current_frame_int_array = new int[max_array_length];
        this.timeline_float_array = new float[max_array_length];
        this.final_timeline_float_array = new float[max_array_length];

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
        GL20.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
    }

    public void initSkinning()
    {
        int max_bones = this.openglanimationmemory.bones;

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
        }
    }

    public int debug;

    public void setSkinning()
    {
        int max_key = this.openglanimationmemory.length;

        long current_time = Minecraft.getSystemTime();
        float timeline = Math.min((current_time - this.last_time) / 10.0F, 1.0F);
        this.last_time = current_time;

        for (int f = 0; f < this.frame_int_array.length; ++f)
        {
            this.final_timeline_float_array[f] = this.getTime(timeline, f);
        }

        for (int i = 0; i < this.openglanimationmemory.bones; ++i)
        {
            System.arraycopy(this.openglanimationmemory.transforms_float_array, (this.frame_int_array[0] + max_key * i) * 16, this.current_mat4, 0, 16);
            M4x4.lerp(this.current_mat4, this.openglanimationmemory.transforms_float_array, 0, (this.current_frame_int_array[0] + max_key * i) * 16, this.final_timeline_float_array[0]);
            M4x4.multiply(this.current_mat4, this.skinning_float_array, 0, i * 16);
//            this.current_frame_int_array[0] = this.frame_int_array[0];

            for (int f = 1; f < this.frame_int_array.length; ++f)
            {
                if (this.frame_boolean_array[f - 1])
                {
                    System.arraycopy(this.openglanimationmemory.transforms_float_array, (this.frame_int_array[f] + max_key * i) * 16, this.current_mat4, 0, 16);
                    M4x4.lerp(this.current_mat4, this.openglanimationmemory.transforms_float_array, 0, (this.current_frame_int_array[f] + max_key * i) * 16, this.final_timeline_float_array[f]);
                    M4x4.multiply(this.current_mat4, this.skinning_float_array, 0, i * 16);

//                    this.current_frame_int_array[f] = this.frame_int_array[f];
                }
            }

            M4x4.inverse(this.skinning_float_array, i * 16);
        }
    }

    public float getTime(float timeline, int index)
    {
        int after = this.current_frame_int_array[index] - this.frame_int_array[index];
        if (after != 1 && after != -1)
        {
            this.current_frame_int_array[index] = this.frame_int_array[index];
            this.timeline_float_array[index] = this.current_frame_int_array[index];
            timeline = 0;
//            if (after != 0)
//            {
//                Nali.LOGGER.info("Fail");
//            }
        }
        else
        {
            if (this.timeline_float_array[index] < this.frame_int_array[index])
            {
                this.timeline_float_array[index] += timeline;
            }
            else if (this.timeline_float_array[index] > this.frame_int_array[index])
            {
                this.timeline_float_array[index] -= timeline;
            }

//            Nali.LOGGER.info("TL " + this.timeline_float_array[index]);
//            Nali.LOGGER.info("CF " + this.frame_int_array[index]);

            int frame = (int)this.timeline_float_array[index];
            timeline = this.timeline_float_array[index] - frame;
            this.current_frame_int_array[index] = frame;

//            Nali.LOGGER.info("T " + timeline);
//            Nali.LOGGER.info("F " + frame);
        }

        return timeline;
    }
}
