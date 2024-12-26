package com.nali.gui.page;

import com.nali.gui.box.BoxColor;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public abstract class PageSelect extends Page
{
	public byte state;//enter_mode loop
	public float scroll;

	public BoxColor boxcolor = new BoxColor();
	public byte select;

	public byte[] group_byte_array;
	public BoxTextAll[] boxtextall_array;

	public float
		wh10,
		h20,
		wh20,
		wh40,
		x, y;

	public MemoS
		rs0,
		rs1;

	public PageSelect()
	{
		this.rs0 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
		this.rs1 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP + 1);
	}

	@Override
	public void gen()
	{
		this.boxtextall_array[0].gen
		(
			this.wh20,
			this.wh20,
			this.wh20, this.wh10, WIDTH, HEIGHT
		);

		int max_length = 0;
		for (byte i = 1; i < this.boxtextall_array.length; ++i)
		{
			max_length = Math.max(max_length, this.boxtextall_array[i].char_array.length);
		}

		this.x = WIDTH / 2.0F - (this.wh20 + this.wh10) * max_length / 2;
		this.y = ((1.0F - 0.041666668F) * HEIGHT) + this.h20;

		for (byte i = 1; i < this.boxtextall_array.length; ++i)
		{
			byte index = (byte)(i - 1);
			byte bit = (byte)(1 << index % 8);

			this.boxtextall_array[i].gen
			(
				this.x - ((this.group_byte_array[index / 8] & bit) == bit ? this.wh20 : 0),
				this.y - this.wh40 * i,
				this.wh20, this.wh10, WIDTH, HEIGHT
			);
		}

		this.genBoxColor();

		this.boxcolor.v_width = WIDTH;
		this.boxcolor.v_height = HEIGHT;
		this.boxcolor.gen();
	}

	public void genBoxColor()
	{
		float y = this.y - this.wh40 * this.select;
		this.boxcolor.x0 = this.wh20;
		this.boxcolor.y0 = y;
		this.boxcolor.x1 = this.x - this.wh10;
		this.boxcolor.y1 = y + this.wh20;
	}

	@Override
	public void draw()
	{
		//s0-shader
		OpenGlHelper.glUseProgram(this.rs0.program);
		int v0 = this.rs0.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v0);
		//e0-shader

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		this.boxtextall_array[0].draw(this.rs0, this.v_float_array, this.c_float_array);

		//s0-shader
		GL20.glDisableVertexAttribArray(v0);
		//e0-shader

		boolean gl_scissor_test = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);
		GL11.glGetInteger(GL11.GL_SCISSOR_BOX, RenderO.INTBUFFER);

		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(0, (int)(this.wh20 * 3), WIDTH, (int)(HEIGHT - this.wh20 * 4));

		//s0-shader
		OpenGlHelper.glUseProgram(this.rs1.program);
		int v1 = this.rs1.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v1);
		//e0-shader

		//s0-select
		this.v_float_array[1] = this.scroll;
		this.drawBoxColor();
		//e0-select

		//s0-shader
		GL20.glDisableVertexAttribArray(v1);
		//e0-shader

//		//s0-shader
		OpenGlHelper.glUseProgram(this.rs0.program);
		GL20.glEnableVertexAttribArray(v0);
//		//e0-shader

		this.drawBoxTextAll();
		this.v_float_array[1] = 0;

		//s0-shader
		GL20.glDisableVertexAttribArray(v0);
		//e0-shader

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

	public void drawBoxColor()
	{
		this.boxcolor.draw(this.rs1, this.v_float_array, new float[]{0.0F, 1.0F, 0.0F, 1.0F});
	}

	public void drawBoxTextAll()
	{
		for (byte i = 1; i < this.boxtextall_array.length; ++i)
		{
			this.boxtextall_array[i].draw(this.rs0, this.v_float_array, this.c_float_array);
		}
	}

	@Override
	public void clear()
	{
		for (BoxTextAll boxtextall : this.boxtextall_array)
		{
			OpenGlHelper.glDeleteBuffers(boxtextall.array_buffer);
		}
		OpenGlHelper.glDeleteBuffers(this.boxcolor.array_buffer);
	}

	@Override
	public void render()
	{
		this.take();

		int width = Display.getWidth();
		int height = Display.getHeight();

		this.wh10 = Math.min((0.011709602F * width), (0.020833334F * height));
		this.h20 = (0.041666668F * height);
		this.wh20 = Math.min((0.0234192037470726F * width), this.h20);
		this.wh40 = Math.min((0.0234192037470726F * 2 * width), (0.041666668F * 2 * height));

		if (WIDTH != width || HEIGHT != height)
		{
			WIDTH = width;
			HEIGHT = height;
			GL11.glViewport(0, 0, width, height);
			this.gen();
		}

//		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		this.draw();
		this.free();
	}

	public void next(byte step)
	{
		while (true)
		{
			if (this.select == 2/*5*/ && step < 0)
			{
				this.select = (byte)(this.boxtextall_array.length - 1);
			}
			else if (this.select == this.boxtextall_array.length - 1 && step > 0)
			{
				this.select = 2/*5*/;
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

	public abstract void enter();

	public void back()
	{
		if (PAGE_LIST.isEmpty())
		{
			this.exit();
		}
		else
		{
			int index = PAGE_LIST.size() - 1;
			this.set(PAGE_LIST.get(index), KEY_LIST.get(index));
			PAGE_LIST.remove(index);
			KEY_LIST.remove(index);
		}
	}

	@Override
	public void exit()
	{
		this.state |= 2;
	}
}
