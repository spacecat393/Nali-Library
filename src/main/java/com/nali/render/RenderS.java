package com.nali.render;

import com.nali.da.IBothDaO;
import com.nali.da.IBothDaS;
import com.nali.system.BothLoader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

//back to simple
//server will handle how frame_byte_array work
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
//	public byte[] frame_byte_array;
//		key_index_byte_array;

//		key_index_byte_array,
//		future_key_byte_array;

//	public short[]
//		time_short_array,
//		future_time_short_array;
//	public short[] time_short_array;
	public short[] key_short_array;

	public float[]
//		frame_float_array,

		skinning_float_array,
		current_mat4 = new float[32];
//		timeline_float_array,
//		future_timeline_float_array,

	public RenderS(BD bd)
	{
		super(bd);
		byte max_frame = this.bd.S_MaxFrame();

//		short mix_max_frame = (short)(max_frame * 2);
//		this.key_index_byte_array = new byte[mix_max_frame];
//		this.frame_float_array = new float[mix_max_frame];
//		this.time_short_array = new short[mix_max_frame];
		this.key_short_array = new short[max_frame];

//		this.frame_byte_array = new byte[(int)Math.ceil(max_frame / 8.0D)];

		this.skinning_float_array = new float[BothLoader.F2_LIST.get(this.bd.S_FrameID()).bone * 16];

//		this.setFrame();
	}

	public static void setFloatBuffer(float[] float_array)
	{
		BONE_FLOATBUFFER.clear();
		BONE_FLOATBUFFER.put(float_array);
		BONE_FLOATBUFFER.flip();
	}

//	public void setFrame()
//	{
//		Arrays.fill(this.frame_byte_array, (byte)255);
//	}

	@Override
//	public void setUniform(MemoG rg, MemoS rs, int index)
	public void setUniform()
	{
		setFloatBuffer(this.skinning_float_array);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[6], false, BONE_FLOATBUFFER);
//		super.setUniform(rg, rs, index);
		super.setUniform();
	}

//	@Override
//	public byte getExtraBit(MemoG rg)
//	{
//		return 2;//skinning
//	}

	public void apply3DSkinningVec4(float[] vec4)
	{
		GL11.glTranslatef(vec4[0] / vec4[3], vec4[1] / vec4[3], vec4[2] / vec4[3]);
	}
}
