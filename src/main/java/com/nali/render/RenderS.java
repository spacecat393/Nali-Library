package com.nali.render;

import com.nali.data.IBothDaSn;
import com.nali.data.client.IClientDaS;
import com.nali.draw.DrawWorldData;
import com.nali.math.M4x4;
import com.nali.math.Quaternion;
import com.nali.system.opengl.memo.client.MemoAnimation;
import com.nali.system.opengl.memo.client.MemoGs;
import com.nali.system.opengl.memo.client.MemoSs;
import com.nali.system.opengl.memo.client.store.StoreS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

import static com.nali.math.M4x4.multiplyVec4Mat4;
import static com.nali.system.Timing.TIMELINE;
import static com.nali.system.opengl.memo.client.MemoCurrent.OPENGL_FLOATBUFFER;
import static com.nali.system.opengl.memo.client.MemoCurrent.setFloatBuffer;

@SideOnly(Side.CLIENT)
public class RenderS</*SD extends ISoundN, */BD extends IBothDaSn, RG extends MemoGs, RS extends MemoSs, RST extends StoreS<RG, RS>, RC extends IClientDaS> extends RenderO<RG, RS, RST, RC>
{
    public BD bd;

    public float scale;

    public int[] frame_int_array, current_frame_int_array;
    public float[] skinning_float_array, timeline_float_array, final_timeline_float_array, current_mat4 = new float[16];
    public byte[] frame_byte_array;
//    public OpenGLAnimationMemory openglanimationmemory;

//    public long last_time = Minecraft.getSystemTime();

    public RenderS(RST rst, RC rc, BD bd)
    {
        super(rst, rc);
        this.bd = bd;

//        int step_models = bothdata.StepModels();
        int max_array_length = this.bd.MaxFrame();

        this.frame_int_array = new int[max_array_length];
        this.current_frame_int_array = new int[max_array_length];
        this.timeline_float_array = new float[max_array_length];
        this.final_timeline_float_array = new float[max_array_length];

//        this.openglanimationmemory = (OpenGLAnimationMemory)this.dataloader.memory_object_array[step_models - 1];
        this.frame_byte_array = new byte[(int)Math.ceil(max_array_length / 8.0D)];

//        this.skinning_float_array = new float[this.openglanimationmemory.bones * 16];
//        this.skinning_float_array = new float[((OpenGLAnimationMemory)this.memory_object_array[0]).bones * 16];
//        this.skinning_float_array = new float[dataloader.openglanimationmemory_list.get(((SkinningClientData)clientdata).AnimationID()).bones * 16];
//        this.skinning_float_array = new float[((OpenGLAnimationMemory)dataloader.object_array[((SkinningClientData)clientdata).AnimationID()]).bones * 16];
        this.skinning_float_array = new float[this.rst.memoanimation_list.get(this.rc.AnimationID()).bones * 16];

        this.setFrame();
    }

    public void setFrame()
    {
        Arrays.fill(this.frame_byte_array, (byte)255);
    }

    @Override
    public void setUniform(RG rg, RS rs, int index)
    {
//        OPENGL_FLOATBUFFER.limit(this.skinning_float_array.length);
        setFloatBuffer(this.skinning_float_array);
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
//        OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[8], false, OPENGL_FLOATBUFFER);
        super.setUniform(rg, rs, index);
    }

//    @Override
//    public OpenGLObjectMemory getMemory(int i)
//    {
//        return (OpenGLObjectMemory)this.memory_object_array[i + 1];
//    }

//    @Override
//    public int getMaxMemory()
//    {
//        return this.memory_object_array.length - 1;
//    }

    public void initSkinning(/*MemoAnimation memoanimation*/)
    {
//        int max_bones = this.openglanimationmemory.bones;
//        int max_bones = ((OpenGLAnimationMemory)this.memory_object_array[0]).bones;
//        int max_bones = memoanimation.bones;
        int max_bones = this.rst.memoanimation_list.get(this.rc.AnimationID()).bones;

        for (int i = 0; i < max_bones; ++i)
        {
            System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
        }
    }

    public void setSkinning(/*MemoAnimation memoanimation*/)
    {
        MemoAnimation memoanimation = this.rst.memoanimation_list.get(this.rc.AnimationID());
//        int max_key = this.openglanimationmemory.length;
//        OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)this.memory_object_array[0];
        int max_key = memoanimation.length;

//        long current_time = Minecraft.getSystemTime();
//        float timeline = Math.min((current_time - this.last_time) / 10.0F, 1.0F);
//        this.last_time = current_time;

        for (int f = 0; f < this.frame_int_array.length; ++f)
        {
            this.final_timeline_float_array[f] = this.getTime(TIMELINE, f);
        }

//        for (int i = 0; i < this.openglanimationmemory.bones; ++i)
        for (int i = 0; i < memoanimation.bones; ++i)
        {
            for (int f = 0; f < this.frame_int_array.length; ++f)
            {
                if ((this.frame_byte_array[f / 8] >> f % 8 & 1) == 1)
                {
//                    System.arraycopy(this.openglanimationmemory.transforms_float_array, (this.frame_int_array[f] + max_key * i) * 16, this.current_mat4, 0, 16);
                    System.arraycopy(memoanimation.transforms_float_array, (this.frame_int_array[f] + max_key * i) * 16, this.current_mat4, 0, 16);
//                    M4x4.lerp(this.current_mat4, this.openglanimationmemory.transforms_float_array, 0, (this.current_frame_int_array[f] + max_key * i) * 16, this.final_timeline_float_array[f]);
                    M4x4.lerp(this.current_mat4, memoanimation.transforms_float_array, 0, (this.current_frame_int_array[f] + max_key * i) * 16, this.final_timeline_float_array[f]);
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
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.openglobjectmemory_array[i];
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.object_array[i];
        RG rg = this.rst.rg_list.get(i);

        int vi = rg.index_int_array[v] * 3;

        byte max_joints = rg.max_joints;
        float[] main_vec4_float_array = new float[4];
        float[] temp_vec4_float_array = new float[4];

        for (int j = 0; j < max_joints; ++j)
        {
            int ji = rg.index_int_array[v] * max_joints + j;
            int joints = (int)rg.joints_float_array[ji];

            if (joints != -1)
            {
                temp_vec4_float_array[0] = rg.vertices_float_array[vi] + x0;
                temp_vec4_float_array[1] = rg.vertices_float_array[vi + 1] + y0;
                temp_vec4_float_array[2] = rg.vertices_float_array[vi + 2] + z0;
                temp_vec4_float_array[3] = 1.0F;

//                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
//                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)this.dataloader.openglobjectshadermemory_array[openglskinningmemory.shader_id];
                RS rs = this.rst.rs_list.get(this.getShaderID(rg)/*openglskinningmemory.shader_id*/);

                for (int b = 0; b < rs.back_bones_2d_int_array[joints].length; ++b)
                {
                    int index = rs.back_bones_2d_int_array[joints][b] * 16;
                    float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
                    System.arraycopy(rs.bind_poses_float_array, index, bindpose_mat4, 0, 16);
                    System.arraycopy(this.skinning_float_array, index, skinning_mat4, 0, 16);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
                }

                float weights = rg.weights_float_array[ji];

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
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.object_array[i];
        RG rg = this.rst.rg_list.get(i);

        int vi = rg.index_int_array[v] * 3;

        byte max_joints = rg.max_joints;
        float[] main_vec4_float_array = new float[4];
        float[] temp_vec4_float_array = new float[4];

        for (int j = 0; j < max_joints; ++j)
        {
            int ji = rg.index_int_array[v] * max_joints + j;
            int joints = (int)rg.joints_float_array[ji];

            if (joints != -1)
            {
                temp_vec4_float_array[0] = rg.vertices_float_array[vi] + x0;
                temp_vec4_float_array[1] = rg.vertices_float_array[vi + 1] + y0;
                temp_vec4_float_array[2] = rg.vertices_float_array[vi + 2] + z0;
                temp_vec4_float_array[3] = 1.0F;

//                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
//                OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)this.dataloader.openglobjectshadermemory_array[openglskinningmemory.shader_id];
                RS rs = this.rst.rs_list.get(this.getShaderID(rg)/*openglskinningmemory.shader_id*/);

                for (int b = 0; b < rs.back_bones_2d_int_array[joints].length; ++b)
                {
                    int index = rs.back_bones_2d_int_array[joints][b] * 16;
                    float[] bindpose_mat4 = new float[16], skinning_mat4 = new float[16];
                    System.arraycopy(rs.bind_poses_float_array, index, bindpose_mat4, 0, 16);
                    System.arraycopy(this.skinning_float_array, index, skinning_mat4, 0, 16);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);

                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, skinning_mat4);

                    M4x4.inverse(bindpose_mat4, 0);
                    temp_vec4_float_array = multiplyVec4Mat4(temp_vec4_float_array, bindpose_mat4);
                }

                float weights = rg.weights_float_array[ji];

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
            this.scale, 0.0F, 0.0F, 0.0F,
            0.0F, this.scale, 0.0F, 0.0F,
            0.0F, 0.0F, this.scale, 0.0F,
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
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.memory_object_array[i];
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.openglobjectmemory_array[i];
//        OpenGLSkinningMemory openglskinningmemory = (OpenGLSkinningMemory)this.dataloader.object_array[i];
        RG rg = this.rst.rg_list.get(i);

        byte max_joints = rg.max_joints;
        float[] mat4_float_array = new float[16];
        System.arraycopy(M4x4.IDENTITY, 0, mat4_float_array, 0, 16);

//        for (int j = 0; j < 1; ++j)
//        {
        int ji = rg.index_int_array[v] * max_joints;// + j;
        int joints = (int)rg.joints_float_array[ji];

        if (joints != -1)
        {
//            OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)openglskinningmemory.shader;
//            OpenGLSkinningShaderMemory openglskinningshadermemory = (OpenGLSkinningShaderMemory)this.dataloader.openglobjectshadermemory_array[openglskinningmemory.shader_id];
            RS rs = this.rst.rs_list.get(this.getShaderID(rg)/*openglskinningmemory.shader_id*/);

            for (int b = 0; b < rs.back_bones_2d_int_array[joints].length; ++b)
            {
                M4x4.multiply(this.skinning_float_array, mat4_float_array, rs.back_bones_2d_int_array[joints][b] * 16, 0);
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

//        OpenGlHelper.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glTranslatef(vec4[0] / vec4[3], vec4[1] / vec4[3], vec4[2] / vec4[3]);
//        OpenGlHelper.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
//        float[] mat4 = new float[]
//        {
//            1, 0, 0, 0,
//            0, 1, 0, 0,
//            0, 0, 1, 0,
//            vec4[0], vec4[1], vec4[2], vec4[3]
//        };
//        OpenGLCurrentMemory.setFloatBuffer(mat4);
//        OpenGlHelper.glMultMatrix(OpenGLCurrentMemory.OPENGL_FLOATBUFFER);
    }

    @Override
    public void updateDataLater(DrawWorldData drawworlddata)
    {
        super.updateDataLater(drawworlddata);
        drawworlddata.skinning_float_array = this.skinning_float_array;
    }

    @Override
    public byte getExtraBit(RG rg)
    {
        return 2;//skinning
    }
}
