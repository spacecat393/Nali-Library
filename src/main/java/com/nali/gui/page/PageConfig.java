package com.nali.gui.page;

import com.nali.NaliConfig;
import com.nali.gui.box.BoxTextAll;
import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class PageConfig
{
	public String type_string = "_";
	public BoxTextAll[] boxtextaz_array;
	public float[] v_float_array = new float[]{0.0F, 0.0F};
	public float[] c_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

	public MemoS rs;
	public int
		v,
		gl_current_program,
		gl_texture_binding_2d_0,
		gl_texture_min_filter_0,
		gl_texture_mag_filter_0,
		gl_array_buffer_binding;

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
	}

	public void init()
	{
		//s0-shader
		this.rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
		OpenGlHelper.glUseProgram(this.rs.program);
		this.v = this.rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(this.v);
		//e0-shader

		this.boxtextaz_array = new BoxTextAll[]
		{
			new BoxTextAll(this.type_string.toCharArray()),
			new BoxTextAll(NaliConfig.ATTRIBUTE_STRING.toCharArray()),
			new BoxTextAll(NaliConfig.GL_SHADING_LANGUAGE_VERSION_STRING.toCharArray()),

			new BoxTextAll("PRE_LO12A D".toCharArray()),

			new BoxTextAll("YT_DLP".toCharArray()),
			new BoxTextAll("FFMPEG".toCharArray()),
			new BoxTextAll("7ZIP".toCharArray()),

			new BoxTextAll("DOWNLOAD FILE".toCharArray()),
			new BoxTextAll("USE YT_DLP".toCharArray()),
			new BoxTextAll("USE FFMPEG".toCharArray()),
			new BoxTextAll("READ SOUND".toCharArray()),

			new BoxTextAll("OPENAL".toCharArray()),
			new BoxTextAll("AL_GAIN".toCharArray()),
			new BoxTextAll("AL_PITCH".toCharArray()),

			new BoxTextAll("OPENGL".toCharArray()),
			new BoxTextAll("PRE_SHADER".toCharArray()),
			new BoxTextAll("USE_SWITCH".toCharArray()),

			new BoxTextAll("ATTRIBUTE".toCharArray()),
			new BoxTextAll("GL_SHADING_LANGUAGE_VERSION".toCharArray()),
		};
	}

	public void gen(int width, int height)
	{
		//854 x 480
		int wh20 = Math.min((int)(0.0234192037470726F * width), (int)(0.041666668F * height));
		int wh10 = Math.min((int)(0.011709602F * width), (int)(0.020833334F * height));
//		int wh5 = Math.min((int)(0.005854801F * width), (int)(0.010416667F * height));
		this.boxtextaz_array[3].gen
		(
			wh20,
			wh20,
			wh20, wh10, width, height
		);
		this.gen02(width, height);
	}

	public void regen(int width, int height)
	{
//		this.boxtextaz_array[0].set(this.type_string.toCharArray());
//		this.boxtextaz_array[1].set(NaliConfig.ATTRIBUTE_STRING.toCharArray());
//		this.boxtextaz_array[2].set(NaliConfig.GL_SHADING_LANGUAGE_VERSION_STRING.toCharArray());
		this.gen02(width, height);
	}

	public void gen02(int width, int height)
	{
//		this.boxtextaz_array[].gen
//		(
//			width, height
//		);
//		this.boxtextaz_array[].gen
//		(
//			width, height
//		);
	}

	public void draw()
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		this.boxtextaz_array[3].draw(this.rs, this.v_float_array, this.c_float_array);
//		for (byte i = 0; i < this.boxtextaz_array.length; ++i)
//		{
//			this.boxtextaz_array[i].draw(this.rs, this.v_float_array, this.c_float_array);
//		}
	}

	public void free()
	{
		//s0-shader
		GL20.glDisableVertexAttribArray(this.v);
		//e0-shader

		OpenGlHelper.glUseProgram(this.gl_current_program);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.gl_texture_binding_2d_0);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, this.gl_texture_min_filter_0);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, this.gl_texture_mag_filter_0);
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.gl_array_buffer_binding);
	}

	public void clear()
	{
		for (byte i = 0; i < this.boxtextaz_array.length; ++i)
		{
			OpenGlHelper.glDeleteBuffers(this.boxtextaz_array[i].array_buffer);
		}
	}
}
