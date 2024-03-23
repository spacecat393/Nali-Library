package com.nali.render;

import com.nali.data.BothData;
import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.system.DataLoader;
import com.nali.system.opengl.memory.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.util.Arrays;

import static com.nali.math.M4x4.multiplyVec4Mat4;
import static com.nali.system.Timing.TIMELINE;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.OPENGL_FLOATBUFFER;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.setFloatBuffer;

@SideOnly(Side.CLIENT)
public class SkinningRender extends ObjectRender
{
    public int[] frame_int_array, current_frame_int_array;
    public float[] skinning_float_array, timeline_float_array, final_timeline_float_array, current_mat4 = new float[16];
    public byte[] frame_byte_array;
//    public OpenGLAnimationMemory openglanimationmemory;

//    public long last_time = Minecraft.getSystemTime();

    public SkinningRender(EntitiesRenderMemory entitiesrendermemory, BothData bothdata, DataLoader dataloader, int i)
    {
        super(entitiesrendermemory, bothdata, dataloader, i);

//        int step_models = bothdata.StepModels();
        int max_array_length = bothdata.MaxFrame();

        this.frame_int_array = new int[max_array_length];
        this.current_frame_int_array = new int[max_array_length];
        this.timeline_float_array = new float[max_array_length];
        this.final_timeline_float_array = new float[max_array_length];

//        this.openglanimationmemory = (OpenGLAnimationMemory)this.dataloader.memory_object_array[step_models - 1];
        this.frame_byte_array = new byte[(int)Math.ceil(max_array_length / 8.0D)];

//        this.skinning_float_array = new float[this.openglanimationmemory.bones * 16];
        this.skinning_float_array = new float[((OpenGLAnimationMemory)this.memory_object_array[0]).bones * 16];

        this.setFrame();
    }

    public void setFrame()
    {
        Arrays.fill(this.frame_byte_array, (byte)255);
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
//        int max_bones = this.openglanimationmemory.bones;
        int max_bones = ((OpenGLAnimationMemory)this.memory_object_array[0]).bones;

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
        }
    }

    public int debug;

    public void setSkinning()
    {
//        int max_key = this.openglanimationmemory.length;
        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)this.memory_object_array[0];
        int max_key = openglanimationmemory.length;

//        long current_time = Minecraft.getSystemTime();
//        float timeline = Math.min((current_time - this.last_time) / 10.0F, 1.0F);
//        this.last_time = current_time;

        for (int f = 0; f < this.frame_int_array.length; ++f)
        {
            this.final_timeline_float_array[f] = this.getTime(TIMELINE, f);
        }

//        for (int i = 0; i < this.openglanimationmemory.bones; ++i)
        for (int i = 0; i < openglanimationmemory.bones; ++i)
        {
            for (int f = 0; f < this.frame_int_array.length; ++f)
            {
                if ((this.frame_byte_array[f / 8] >> f % 8 & 1) == 1)
                {
//                    System.arraycopy(this.openglanimationmemory.transforms_float_array, (this.frame_int_array[f] + max_key * i) * 16, this.current_mat4, 0, 16);
                    System.arraycopy(openglanimationmemory.transforms_float_array, (this.frame_int_array[f] + max_key * i) * 16, this.current_mat4, 0, 16);
//                    M4x4.lerp(this.current_mat4, this.openglanimationmemory.transforms_float_array, 0, (this.current_frame_int_array[f] + max_key * i) * 16, this.final_timeline_float_array[f]);
                    M4x4.lerp(this.current_mat4, openglanimationmemory.transforms_float_array, 0, (this.current_frame_int_array[f] + max_key * i) * 16, this.final_timeline_float_array[f]);
                    M4x4.multiply(this.current_mat4, this.skinning_float_array, 0, i * 16);
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

    public float[] get3DSkinning(float x, float y, float z, float x0, float y0, float z0, int i, int v)
    {
        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];

        int vi = openglskinningmemory.index_int_array[v] * 3;

        byte max_joints = openglskinningmemory.max_joints;
        float[] main_vec4_float_array = new float[4];
        float[] temp_vec4_float_array = new float[4];

        for (int j = 0; j < max_joints; ++j)
        {
            int ji = openglskinningmemory.index_int_array[v] * max_joints + j;
            int joints = (int)openglskinningmemory.joints_float_array[ji];

            if (joints != -1)
            {
                temp_vec4_float_array[0] = openglskinningmemory.vertices_float_array[vi] + x0;
                temp_vec4_float_array[1] = openglskinningmemory.vertices_float_array[vi + 1] + y0;
                temp_vec4_float_array[2] = openglskinningmemory.vertices_float_array[vi + 2] + z0;
                temp_vec4_float_array[3] = 1.0F;

                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;

                for (int b = 0; b < openglskinningshadermemory.back_bones_2d_int_array[joints].length; ++b)
                {
                    int index = openglskinningshadermemory.back_bones_2d_int_array[joints][b] * 16;
                    float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
                    System.arraycopy(openglskinningshadermemory.bind_poses_float_array, index, bindpose_mat4, 0, 16);
                    System.arraycopy(this.skinning_float_array, index, skinning_mat4, 0, 16);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
                }

                float weights = openglskinningmemory.weights_float_array[ji];

                temp_vec4_float_array[0] *= weights;
                temp_vec4_float_array[1] *= weights;
                temp_vec4_float_array[2] *= weights;
                temp_vec4_float_array[3] *= weights;

                main_vec4_float_array[0] += temp_vec4_float_array[0];
                main_vec4_float_array[1] += temp_vec4_float_array[1];
                main_vec4_float_array[2] += temp_vec4_float_array[2];
                main_vec4_float_array[3] += temp_vec4_float_array[3];
            }
        }

        main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
        main_vec4_float_array[0] += x;
        main_vec4_float_array[1] += y;
        main_vec4_float_array[2] += z;

        return main_vec4_float_array;
    }

    public float[] getScale3DSkinning(float x, float y, float z, float x0, float y0, float z0, int i, int v)
    {
        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];

        int vi = openglskinningmemory.index_int_array[v] * 3;

        byte max_joints = openglskinningmemory.max_joints;
        float[] main_vec4_float_array = new float[4];
        float[] temp_vec4_float_array = new float[4];

        for (int j = 0; j < max_joints; ++j)
        {
            int ji = openglskinningmemory.index_int_array[v] * max_joints + j;
            int joints = (int)openglskinningmemory.joints_float_array[ji];

            if (joints != -1)
            {
                temp_vec4_float_array[0] = openglskinningmemory.vertices_float_array[vi] + x0;
                temp_vec4_float_array[1] = openglskinningmemory.vertices_float_array[vi + 1] + y0;
                temp_vec4_float_array[2] = openglskinningmemory.vertices_float_array[vi + 2] + z0;
                temp_vec4_float_array[3] = 1.0F;

                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;

                for (int b = 0; b < openglskinningshadermemory.back_bones_2d_int_array[joints].length; ++b)
                {
                    int index = openglskinningshadermemory.back_bones_2d_int_array[joints][b] * 16;
                    float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
                    System.arraycopy(openglskinningshadermemory.bind_poses_float_array, index, bindpose_mat4, 0, 16);
                    System.arraycopy(this.skinning_float_array, index, skinning_mat4, 0, 16);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
                }

                float weights = openglskinningmemory.weights_float_array[ji];

                temp_vec4_float_array[0] *= weights;
                temp_vec4_float_array[1] *= weights;
                temp_vec4_float_array[2] *= weights;
                temp_vec4_float_array[3] *= weights;

                main_vec4_float_array[0] += temp_vec4_float_array[0];
                main_vec4_float_array[1] += temp_vec4_float_array[1];
                main_vec4_float_array[2] += temp_vec4_float_array[2];
                main_vec4_float_array[3] += temp_vec4_float_array[3];
            }
        }

        main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new float[]
        {
            this.entitiesrendermemory.scale, 0.0F, 0.0F, 0.0F,
            0.0F, this.entitiesrendermemory.scale, 0.0F, 0.0F,
            0.0F, 0.0F, this.entitiesrendermemory.scale, 0.0F,
            0.0F, 0.0F, 0.0F, 1.0F,
        });
        main_vec4_float_array = multiplyVec4Mat4(main_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);
        main_vec4_float_array[0] += x;
        main_vec4_float_array[1] += y;
        main_vec4_float_array[2] += z;
//        main_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat);

        return main_vec4_float_array;
    }

    public float[] getMat43DSkinning(int i, int v)
    {
        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];

        byte max_joints = openglskinningmemory.max_joints;
        float[] mat4_float_array = new float[16];
        System.arraycopy(M4x4.IDENTITY, 0, mat4_float_array, 0, 16);

//        for (int j = 0; j < 1; ++j)
//        {
        int ji = openglskinningmemory.index_int_array[v] * max_joints;// + j;
        int joints = (int)openglskinningmemory.joints_float_array[ji];

        if (joints != -1)
        {
            OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;

            for (int b = 0; b < openglskinningshadermemory.back_bones_2d_int_array[joints].length; ++b)
            {
                M4x4.multiply(this.skinning_float_array, mat4_float_array, openglskinningshadermemory.back_bones_2d_int_array[joints][b] * 16, 0);
            }
        }
//        }

//        M4x4.multiply(new Quaternion(-1.571F, 0.0F, 0.0F).getM4x4().mat, mat4_float_array, 0, 0);
        return mat4_float_array;
    }

    public void apply3DSkinningVec4(float[] vec4)
    {
//        if (vec4[3] == 0.0F)
//        {
//            vec4[3] = 1.0F;
//        }

//        GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(vec4[0] / vec4[3], vec4[1] / vec4[3], vec4[2] / vec4[3]);
//        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//        float[] mat4 = new float[]
//        {
//            1, 0, 0, 0,
//            0, 1, 0, 0,
//            0, 0, 1, 0,
//            vec4[0], vec4[1], vec4[2], vec4[3]
//        };
//        OpenGLCurrentMemory.setFloatBuffer(mat4);
//        GL11.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
    }
}
