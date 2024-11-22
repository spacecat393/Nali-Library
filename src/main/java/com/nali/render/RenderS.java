package com.nali.render;

import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.draw.DrawWorldData;
import com.nali.math.M4x4;
import com.nali.system.opengl.memo.MemoF2;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.Arrays;

import static com.nali.system.BothLoader.F2_LIST;
import static com.nali.system.Timing.TIMELINE;

@SideOnly(Side.CLIENT)
public class RenderS
<
	BD extends IBothDaO & IBothDaS
> extends RenderO<BD>
{
	public static int MAX_BONE;
	public static FloatBuffer BONE_FLOATBUFFER;

	public float scale;

	public int[] frame_int_array, current_frame_int_array;
	public float[] skinning_float_array, timeline_float_array, final_timeline_float_array, current_mat4 = new float[16];
	public byte[] frame_byte_array;
//	public OpenGLAnimationMemory openglanimationmemory;

//	public long last_time = Minecraft.getSystemTime();

	public RenderS(BD bd/*RC rc*/)
	{
//		super(rc);

//		int step_models = bothdata.StepModels();
		int max_array_length = bd.S_MaxFrame();

		this.frame_int_array = new int[max_array_length];
		this.current_frame_int_array = new int[max_array_length];
		this.timeline_float_array = new float[max_array_length];
		this.final_timeline_float_array = new float[max_array_length];

//		this.openglanimationmemory = (OpenGLAnimationMemory)this.dataloader.memory_object_array[step_models - 1];
		this.frame_byte_array = new byte[(int)Math.ceil(max_array_length / 8.0D)];

//		this.skinning_float_array = new float[this.openglanimationmemory.bones * 16];
//		this.skinning_float_array = new float[((OpenGLAnimationMemory)this.memory_object_array[0]).bones * 16];
//		this.skinning_float_array = new float[dataloader.openglanimationmemory_list.get(((SkinningClientData)clientdata).AnimationID()).bones * 16];
//		this.skinning_float_array = new float[((OpenGLAnimationMemory)dataloader.object_array[((SkinningClientData)clientdata).AnimationID()]).bones * 16];
		this.skinning_float_array = new float[F2_LIST.get(bd.S_FrameID()).bone * 16];

		this.setFrame();
	}

	public static void setFloatBuffer(float[] float_array)
	{
		BONE_FLOATBUFFER.clear();
		BONE_FLOATBUFFER.put(float_array);
		BONE_FLOATBUFFER.flip();
	}

	public void setFrame()
	{
		Arrays.fill(this.frame_byte_array, (byte)255);
	}

	@Override
	public void setUniform(MemoG rg, MemoS rs, int index)
	{
//		OPENGL_FLOATBUFFER.limit(this.skinning_float_array.length);
		setFloatBuffer(this.skinning_float_array);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7/*+1*/], false, BONE_FLOATBUFFER);
//		OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[8], false, OPENGL_FLOATBUFFER);
		super.setUniform(rg, rs, index);
	}

//	@Override
//	public OpenGLObjectMemory getMemory(int i)
//	{
//		return (OpenGLObjectMemory)this.memory_object_array[i + 1];
//	}

//	@Override
//	public int getMaxMemory()
//	{
//		return this.memory_object_array.length - 1;
//	}

	public void initSkinning(BD bd/*MemoAnimation memoanimation*/)
	{
//		int max_bones = this.openglanimationmemory.bones;
//		int max_bones = ((OpenGLAnimationMemory)this.memory_object_array[0]).bones;
//		int max_bones = memoanimation.bones;
		int max_bones = F2_LIST.get(bd.S_FrameID()).bone;

		for (int i = 0; i < max_bones; ++i)
		{
			System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
		}
	}

	public void setSkinning(BD bd/*MemoAnimation memoanimation*/)
	{
		int frame_id = bd.S_FrameID();
		MemoF2 bf2 = F2_LIST.get(frame_id);
//		int max_key = this.openglanimationmemory.length;
//		OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)this.memory_object_array[0];
		int max_key = bf2.length;

//		long current_time = Minecraft.getSystemTime();
//		float timeline = Math.min((current_time - this.last_time) / 10.0F, 1.0F);
//		this.last_time = current_time;

		for (int i = 0; i < this.frame_int_array.length; ++i)
		{
			this.final_timeline_float_array[i] = this.getTime(TIMELINE, i);
		}

//		for (int i = 0; i < this.openglanimationmemory.bones; ++i)
		for (int i = 0; i < bf2.bone; ++i)
		{
			for (int l = 0; l < this.frame_int_array.length; ++l)
			{
				if ((this.frame_byte_array[l / 8] >> l % 8 & 1) == 1)
				{
//					System.arraycopy(this.openglanimationmemory.transforms_float_array, (this.frame_int_array[f] + max_key * i) * 16, this.current_mat4, 0, 16);
					System.arraycopy(bf2.transforms_float_array, (this.frame_int_array[l] + max_key * i) * 16, this.current_mat4, 0, 16);
//					M4x4.lerp(this.current_mat4, this.openglanimationmemory.transforms_float_array, 0, (this.current_frame_int_array[f] + max_key * i) * 16, this.final_timeline_float_array[f]);
					M4x4.lerp(this.current_mat4, bf2.transforms_float_array, 0, (this.current_frame_int_array[l] + max_key * i) * 16, this.final_timeline_float_array[l]);
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
//			if (after != 0)
//			{
//				Nali.LOGGER.info("Fail");
//			}
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

//			Nali.LOGGER.info("TL " + this.timeline_float_array[index]);
//			Nali.LOGGER.info("CF " + this.frame_int_array[index]);

			int frame = (int)this.timeline_float_array[index];
			timeline = this.timeline_float_array[index] - frame;
			this.current_frame_int_array[index] = frame;

//			Nali.LOGGER.info("T " + timeline);
//			Nali.LOGGER.info("F " + frame);
		}

		return timeline;
	}

	@Override
	public void startDrawLater(BD bd, DrawWorldData drawworlddata)
	{
		super.startDrawLater(bd, drawworlddata);
		drawworlddata.skinning_float_array = this.skinning_float_array;
	}

	@Override
	public byte getExtraBit(MemoG rg)
	{
		return 2;//skinning
	}

	public void apply3DSkinningVec4(float[] vec4)
	{
		GL11.glTranslatef(vec4[0] / vec4[3], vec4[1] / vec4[3], vec4[2] / vec4[3]);
	}
}
