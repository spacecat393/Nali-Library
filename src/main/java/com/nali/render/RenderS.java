package com.nali.render;

import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.draw.DrawWorldData;
import com.nali.math.M4x4;
import com.nali.system.opengl.memo.MemoF2;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;
import java.util.Arrays;

import static com.nali.system.BothLoader.F2_LIST;

@SideOnly(Side.CLIENT)
public class RenderS
<
	BD extends IBothDaO & IBothDaS
> extends RenderO<BD>
{
	public static int MAX_BONE;
	public static FloatBuffer BONE_FLOATBUFFER;

	//use on cpu
	public float scale;

	//key(byte) time(short)
	//time shouldn't warp
	//like fire
	//time != key/frame
//	public int[] frame_int_array, current_frame_int_array;
	public byte[]
		frame_byte_array,
		key_index_byte_array;

//		key_index_byte_array,
//		future_key_byte_array;

//	public short[]
//		time_short_array,
//		future_time_short_array;
	public short[] time_short_array;

	public float[]
		frame_float_array,

		skinning_float_array,
		current_mat4 = new float[32];
//		timeline_float_array,
//		future_timeline_float_array,

	public RenderS(BD bd)
	{
		byte max_frame = bd.S_MaxFrame();

		short mix_max_frame = (short)(max_frame * 2);
		this.key_index_byte_array = new byte[mix_max_frame];
		this.frame_float_array = new float[mix_max_frame];
		this.time_short_array = new short[mix_max_frame];

		this.frame_byte_array = new byte[(int)Math.ceil(max_frame / 8.0D)];

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
		setFloatBuffer(this.skinning_float_array);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7], false, BONE_FLOATBUFFER);
		super.setUniform(rg, rs, index);
	}

	public void initSkinning(BD bd)
	{
		int max_bones = F2_LIST.get(bd.S_FrameID()).bone;

		for (int i = 0; i < max_bones; ++i)
		{
			System.arraycopy(M4x4.IDENTITY, 0, this.skinning_float_array, i * 16, 16);
		}
	}

	public void setSkinning(BD bd)
	{
		int frame_id = bd.S_FrameID();
		MemoF2 bf2 = F2_LIST.get(frame_id);
		int max_frame = bd.S_MaxFrame();
		short max_key = bf2.max_key;
		short[] key_short_array = bd.S_KeyShortArray();
//		byte fps = bd.S_FPS();

		for (int i = 0; i < max_frame; ++i)
		{
			int index = i * 2;
			//to this
			this.frame_float_array[index] = (this.time_short_array[index] & 0xFFFF) / ((float)Short.MAX_VALUE / key_short_array[this.key_index_byte_array[index] + 2]);
			++index;
			this.frame_float_array[index] = (this.time_short_array[index] & 0xFFFF) / ((float)Short.MAX_VALUE / key_short_array[this.key_index_byte_array[index] + 2]);
		}

		for (int i = 0; i < bf2.bone; ++i)
		{
			for (int l = 0; l < max_frame; ++l)
			{
				if ((this.frame_byte_array[l / 8] >> l % 8 & 1) == 1)
				{
					//clean
					int index = l * 2;
					float start_frame = this.frame_float_array[index];
					float end_frame = this.frame_float_array[index + 1];
					float new_start_frame = start_frame - (int)start_frame;
					float new_end_frame = end_frame - (int)end_frame;
					int start_key_frame = (int)Math.ceil(start_frame);
					int end_key_frame = (int)Math.ceil(end_frame);
					short min_start_key = key_short_array[this.key_index_byte_array[index]];
					short max_start_key = key_short_array[this.key_index_byte_array[index] + 2];

					short min_end_key = key_short_array[this.key_index_byte_array[index + 1]];
					short max_end_key = key_short_array[this.key_index_byte_array[index + 1] + 2];
					if (start_key_frame < min_start_key)
					{
						start_key_frame = max_start_key;
					}
					else if (start_key_frame > max_start_key)
					{
						start_key_frame = min_start_key;
					}

					if (end_key_frame < min_end_key)
					{
						end_key_frame = max_end_key;
					}
					else if (end_key_frame > max_end_key)
					{
						end_key_frame = min_end_key;
					}

					System.arraycopy(bf2.transforms_float_array, ((int)start_frame + max_key * i) * 16, this.current_mat4, 0, 16);
					System.arraycopy(bf2.transforms_float_array, ((int)end_frame + max_key * i) * 16, this.current_mat4, 16, 16);

					M4x4.lerp(this.current_mat4, bf2.transforms_float_array, 0, (start_key_frame + max_key * i) * 16, new_start_frame);
					M4x4.lerp(this.current_mat4, bf2.transforms_float_array, 0, (end_key_frame + max_key * i) * 16, new_end_frame);

					M4x4.lerp(this.current_mat4, this.current_mat4, 0, 16, Minecraft.getMinecraft().getRenderPartialTicks());
					M4x4.multiply(this.current_mat4, this.skinning_float_array, 0, i * 16);
				}
			}

			M4x4.inverse(this.skinning_float_array, i * 16);
		}
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
