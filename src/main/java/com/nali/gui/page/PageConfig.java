package com.nali.gui.page;

import com.nali.NaliConfig;
import com.nali.gui.box.BoxColor;
import com.nali.gui.box.BoxTextAll;
import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class PageConfig
{
	public byte state;
//	public String type_string = "_";

	public byte[] group_byte_array;
	public BoxTextAll[] boxtextall_array;
	public float[] v_float_array = new float[]{0.0F, 0.0F};
	public float[] c_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

	public float scroll;
//		scroll,
//		max_scroll;

	public BoxColor boxcolor = new BoxColor();
	public byte select = 5;

//	public MemoS rs;
	public byte gl_state;
	public int
//		v,
		gl_current_program,
		gl_texture_binding_2d_0,
		gl_texture_min_filter_0,
		gl_texture_mag_filter_0,
		gl_array_buffer_binding,

		gl_blend_equation_rgb,
		gl_blend_equation_alpha,

		gl_blend_src_rgb,
		gl_blend_src_alpha,
		gl_blend_dst_rgb,
		gl_blend_dst_alpha;

	public void take()
	{
		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
		this.gl_current_program = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
		this.gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
		this.gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
		this.gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		this.gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
		this.gl_state |= (GL11.glIsEnabled(GL11.GL_BLEND) ? 1 : 0);

		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, RenderO.INTBUFFER);
		this.gl_blend_equation_rgb = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, RenderO.INTBUFFER);
		this.gl_blend_equation_alpha = RenderO.INTBUFFER.get(0);

		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, RenderO.INTBUFFER);
		this.gl_blend_src_rgb = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, RenderO.INTBUFFER);
		this.gl_blend_src_alpha = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, RenderO.INTBUFFER);
		this.gl_blend_dst_rgb = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, RenderO.INTBUFFER);
		this.gl_blend_dst_alpha = RenderO.INTBUFFER.get(0);

		GL11.glEnable(GL11.GL_BLEND);
		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
	}

	public void init()
	{
//		//s0-shader
//		this.rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
//		OpenGlHelper.glUseProgram(this.rs.program);
//		this.v = this.rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(this.v);
//		//e0-shader

		//s0-stuff
		this.state |= ClientLoader.isCommandLive("yt-dlp") ? 1 : 0;
		this.state |= ClientLoader.isCommandLive("ffmpeg") ? 2 : 0;
		//e0-stuff

		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("PRE-LOAD".toCharArray()),

			new BoxTextAll("COMMAND".toCharArray()),//0
			new BoxTextAll(((this.state & 2) == 2 ? "FFMPEG ABLE" : "FFMPEG UNABLE").toCharArray()),
			new BoxTextAll(((this.state & 1) == 1 ? "YT-DLP ABLE" : "YT-DLP UNABLE").toCharArray()),
//			new BoxTextAll("7ZIP".toCharArray()),

//			new BoxTextAll("DOWNLOAD FILE".toCharArray()),
			new BoxTextAll("START PROCESS".toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 4) == 4 ? "USE_YT-DLP YES" : "USE_YT-DLP NO").toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 8) == 8 ? "USE_FFMPEG YES" : "USE_FFMPEG NO").toCharArray()),
//			new BoxTextAll("READ SOUND".toCharArray()),

			new BoxTextAll("OPENAL".toCharArray()),
			new BoxTextAll(new StringBuilder("AL_GAIN ").append(NaliConfig.AL_GAIN).toString().toCharArray()),
			new BoxTextAll(new StringBuilder("AL_PITCH ").append(NaliConfig.AL_PITCH).toString().toCharArray()),

			new BoxTextAll("OPENGL".toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 1) == 1 ? "PRE_SHADER YES" : "PRE_SHADER NO").toCharArray()),
			new BoxTextAll(((NaliConfig.STATE & 2) == 2 ? "USE_SWITCH YES" : "USE_SWITCH NO").toCharArray()),
//			new BoxTextAll(new StringBuilder("GLSL ").append(NaliConfig.GLSL).toString().toCharArray()),
			new BoxTextAll(new StringBuilder("ATTRIBUTE ").append(NaliConfig.ATTRIBUTE.toUpperCase()).toString().toCharArray())
		};
		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[3 / 8] |= 1 << 3 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;
		this.group_byte_array[9 / 8] |= 1 << 9 % 8;
//		new BoxTextAll(this.type_string.toCharArray())
	}

//	public void update(int width, int height, int wh20, int h20)
//	{
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[5].array_buffer);
//		this.boxtextall_array[5] = new BoxTextAll(((NaliConfig.STATE & 4) == 4 ? "USE_YT-DLP YES" : "USE_YT-DLP NO").toCharArray());
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[6].array_buffer);
//		this.boxtextall_array[6] = new BoxTextAll(((NaliConfig.STATE & 8) == 8 ? "USE_FFMPEG YES" : "USE_FFMPEG NO").toCharArray());
//
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[8].array_buffer);
//		this.boxtextall_array[8] = new BoxTextAll(new StringBuilder("AL_GAIN ").append(NaliConfig.AL_GAIN).toString().toCharArray());
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[9].array_buffer);
//		this.boxtextall_array[9] = new BoxTextAll(new StringBuilder("AL_PITCH ").append(NaliConfig.AL_PITCH).toString().toCharArray());
//
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[11].array_buffer);
//		this.boxtextall_array[11] = new BoxTextAll(((NaliConfig.STATE & 1) == 1 ? "PRE_SHADER YES" : "PRE_SHADER NO").toCharArray());
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[12].array_buffer);
//		this.boxtextall_array[12] = new BoxTextAll(((NaliConfig.STATE & 2) == 2 ? "USE_SWITCH YES" : "USE_SWITCH NO").toCharArray());
//		OpenGlHelper.glDeleteBuffers(this.boxtextall_array[13].array_buffer);
//		this.boxtextall_array[13] = new BoxTextAll(new StringBuilder("ATTRIBUTE ").append(NaliConfig.ATTRIBUTE.toUpperCase()).toString().toCharArray());
//
//		int max_length = 0;
//		for (byte i = 1; i < this.boxtextall_array.length; ++i)
//		{
//			max_length = Math.max(max_length, this.boxtextall_array[i].char_array.length);
//		}
//
//		int wh10 = Math.min((int)(0.011709602F * width), (int)(0.020833334F * height));
//		int x = width / 2 - (wh20 + wh10) * max_length / 2;
//		int y = (int)((1.0F - 0.041666668F) * height) + h20;
//		int wh40 = wh20 * 2;
//		for (byte i = 1; i < this.boxtextall_array.length; ++i)
//		{
//			byte index = (byte)(i - 1);
//			byte bit = (byte)(1 << index % 8);
//
//			this.boxtextall_array[i].gen
//			(
//				x - ((this.group_byte_array[index / 8] & bit) == bit ? wh20 : 0),
//				y - wh40 * i,
//				wh20, wh10, width, height
//			);
//		}
//	}

	public void gen(int width, int height, int wh20, int h20)
	{
		//854 x 480
//		int h20 = (int)(0.041666668F * height);
//		int wh20 = Math.min((int)(0.0234192037470726F * width), h20);
		int wh10 = Math.min((int)(0.011709602F * width), (int)(0.020833334F * height));
//		int wh5 = Math.min((int)(0.005854801F * width), (int)(0.010416667F * height));
		this.boxtextall_array[0].gen
		(
			wh20,
			wh20,
			wh20, wh10, width, height
		);

		int max_length = 0;
		for (byte i = 1; i < this.boxtextall_array.length; ++i)
		{
			max_length = Math.max(max_length, this.boxtextall_array[i].char_array.length);
		}

		int x = width / 2 - (wh20 + wh10) * max_length / 2;
		int y = (int)((1.0F - 0.041666668F) * height) + h20;
		int wh40 = wh20 * 2;
		for (byte i = 1; i < this.boxtextall_array.length; ++i)
		{
			byte index = (byte)(i - 1);
			byte bit = (byte)(1 << index % 8);

			this.boxtextall_array[i].gen
			(
				x - ((this.group_byte_array[index / 8] & bit) == bit ? wh20 : 0),
				y - wh40 * i,
				wh20, wh10, width, height
			);
		}

		y -= wh40 * this.select;
		this.boxcolor.gen
		(
			wh20,
			y,
			x - wh10,
			y + wh20,
			width, height
		);
	}

	public void draw(int width, int height, int wh20)
	{
		//s0-shader
		MemoS rs0 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
		OpenGlHelper.glUseProgram(rs0.program);
		int v0 = rs0.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v0);
		//e0-shader

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		this.boxtextall_array[0].draw(rs0, this.v_float_array, this.c_float_array);

		//s0-shader
		GL20.glDisableVertexAttribArray(v0);
		//e0-shader

		boolean gl_scissor_test = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);
		GL11.glGetInteger(GL11.GL_SCISSOR_BOX, RenderO.INTBUFFER);

		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(0, wh20 * 3, width, height - wh20 * 4);

		//s0-shader
		MemoS rs1 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP + 1);
		OpenGlHelper.glUseProgram(rs1.program);
		int v1 = rs1.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v1);
		//e0-shader

		this.v_float_array[1] = this.scroll;
		//s0-select
//		this.boxcolor.draw(rs1, this.v_float_array, this.c_float_array);
		this.boxcolor.draw(rs1, this.v_float_array, new float[]{0.0F, 1.0F, 0.0F, 1.0F});
		//e0-select

		//s0-shader
		GL20.glDisableVertexAttribArray(v1);
		//e0-shader

//		//s0-shader
//		MemoS rs0 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
		OpenGlHelper.glUseProgram(rs0.program);
//		int v0 = rs0.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v0);
//		//e0-shader

		for (byte i = 1; i < this.boxtextall_array.length; ++i)
		{
			this.boxtextall_array[i].draw(rs0, this.v_float_array, this.c_float_array);
		}

		//s0-shader
		GL20.glDisableVertexAttribArray(v0);
		//e0-shader
		this.v_float_array[1] = 0;

		GL11.glScissor(RenderO.INTBUFFER.get(0), RenderO.INTBUFFER.get(1), RenderO.INTBUFFER.get(2), RenderO.INTBUFFER.get(3));
		if (gl_scissor_test)
		{
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
		}
		else
		{
			GL11.glDisable(GL11.GL_SCISSOR_TEST);
		}
	}

	public void free()
	{
//		//s0-shader
//		GL20.glDisableVertexAttribArray(this.v);
//		//e0-shader

		OpenGlHelper.glUseProgram(this.gl_current_program);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.gl_texture_binding_2d_0);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, this.gl_texture_min_filter_0);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, this.gl_texture_mag_filter_0);
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.gl_array_buffer_binding);
		if ((this.gl_state & 1) == 1)
		{
			GL11.glEnable(GL11.GL_BLEND);
		}
		else
		{
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL20.glBlendEquationSeparate(this.gl_blend_equation_rgb, this.gl_blend_equation_alpha);
		GL14.glBlendFuncSeparate(this.gl_blend_src_rgb, this.gl_blend_dst_rgb, this.gl_blend_src_alpha, this.gl_blend_dst_alpha);
	}

	public void clear()
	{
		for (byte i = 0; i < this.boxtextall_array.length; ++i)
		{
			OpenGlHelper.glDeleteBuffers(this.boxtextall_array[i].array_buffer);
		}
	}

	public void next(byte step)
	{
		while (true)
		{
			if (this.select == 5 && step < 0)
			{
				this.select = (byte)(this.boxtextall_array.length - 1);
			}
			else if (this.select == this.boxtextall_array.length - 1 && step > 0)
			{
				this.select = 5;
			}
			else
			{
				this.select += step;
			}

			byte index = (byte)(this.select - 1);
			byte bit = (byte)(1 << index % 8);
			if ((this.group_byte_array[index / 8] & bit) != bit)
			{
				return;
			}
		}
	}

	public void enter()
	{
		switch (this.select)
		{
			case 5:
			{
				NaliConfig.STATE ^= 4;
				break;
			}
			case 6:
			{
				NaliConfig.STATE ^= 8;
				break;
			}
			case 8:
			{
				break;
			}
			case 9:
			{
				break;
			}
			case 11:
			{
				NaliConfig.STATE ^= 1;
				break;
			}
			case 12:
			{
				NaliConfig.STATE ^= 2;
				break;
			}
			case 13:
			{
				if (NaliConfig.ATTRIBUTE != "in")
				{
					NaliConfig.ATTRIBUTE = "in";
				}
				else
				{
					NaliConfig.ATTRIBUTE = "attribute";
				}
				break;
			}
		}
	}
}
