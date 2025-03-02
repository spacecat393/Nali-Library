package com.nali.gui.page;

import com.nali.gui.box.Box;
import com.nali.gui.box.BoxV;
import com.nali.gui.box.BoxVT;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.list.data.NaliData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public abstract class PageSelect/*<N extends Number>*/ extends Page
{
	public final static byte BF_ENTER_MODE = 1;
	public final static byte BF_EXIT_LOOP = 2;
	public final static byte BF_SET_SELECT = 4;
	public byte fl;//enter_mode loop set_select
	public float scroll;

	public float[] gc_float_array = new float[]{0.0F, 1.0F, 0.0F, 1.0F};

//	public BoxColor boxcolor = new BoxColor();
	public byte select;
	public byte min_select = 2;
//	public N select;
//	public N min_select;//2
	public float[]
		float_array,
		w_float_array;
	public char[][] char_2d_array;
	public int
		ca0_t_count,
		ta_t_count,
		array_buffer = -1;
//		color_buffer_array = -1,
//		text_buffer_array = -1;

	public byte[] group_byte_array;
//	public BoxTextAll[] boxtextall_array;

	public float
		wh10,
		h20,
		wh20,
		wh40,
		x, y;

	public MemoS
		rs0,
		rs1;

	public PageSelect(/*N min_select*/)
	{
//		this.min_select = min_select;
//		this.min_select = this.createN(2);
		this.rs0 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
		this.rs1 = ClientLoader.S_LIST.get(NaliData.SHADER_STEP + 1);

//		this.float_array = new float[18];
		this.float_array = new float[BoxV.B_FAL + BoxVT.B_FAL + BoxTextAll.B_FAL];

		this.float_array[BoxVT.B_FA_T_WIDTH] = 170;
		this.float_array[BoxVT.B_FA_T_HEIGHT] = 5;
	}

	@Override
	public void init()
	{
		this.ca0_t_count = this.char_2d_array[0].length;

		this.ta_t_count = 0;
		for (int i = 1; i < this.char_2d_array.length; ++i)
		{
			this.ta_t_count += this.char_2d_array[i].length;
		}

		this.w_float_array = new float[BoxV.B_WFAL + (this.ca0_t_count + this.ta_t_count) * BoxVT.B_WFAL];

		this.ca0_t_count *= 6;
		this.ta_t_count *= 6;
	}

	@Override
	public void gen()
	{
		this.clear();
		this.float_array[BoxV.B_FA_V_WIDTH] = Box.WIDTH;
		this.float_array[BoxV.B_FA_V_HEIGHT] = Box.HEIGHT;

		this.genP();

		this.genBoxColor();
		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(this.w_float_array));
	}

	public void genP()
	{
		this.float_array[BoxTextAll.B_FA_X] = this.wh20;
		this.float_array[BoxTextAll.B_FA_Y] = this.wh20;
		this.float_array[BoxTextAll.B_FA_SIZE] = this.wh20;
		this.float_array[BoxTextAll.B_FA_SPACE] = this.wh10;
		BoxTextAll.set(this.float_array, this.w_float_array, this.char_2d_array[0], BoxV.B_WFAL);

//		int max_length = 0;
		byte max_length = 0;
//		for (int i = 1; i < this.boxtextall_array.length; ++i)
		for (byte i = 1; i < this.char_2d_array.length; ++i)
		{
//			max_length = Math.max(max_length, this.boxtextall_array[i].char_array.length);
			max_length = (byte)Math.max(max_length, this.char_2d_array[i].length);
		}

		this.x = Box.WIDTH / 2.0F - (this.wh20 + this.wh10) * max_length / 2;
		this.y = ((1.0F - 0.041666668F) * Box.HEIGHT) + this.h20;

//		for (int i = 1; i < this.boxtextall_array.length; ++i)
		int bta_index = BoxV.B_WFAL + this.char_2d_array[0].length * BoxVT.B_WFAL;
		for (byte i = 1; i < this.char_2d_array.length; ++i)
		{
//			int index = i - 1;
			byte index = (byte)(i - 1);
			byte bit = (byte)(1 << index % 8);

			this.float_array[BoxTextAll.B_FA_X] = this.x - ((this.group_byte_array[index / 8] & bit) == bit ? this.wh20 : 0);
			this.float_array[BoxTextAll.B_FA_Y] = this.y - this.wh40 * i;
//			this.float_array[BoxTextAll.B_FA_SIZE] = this.wh20;
//			this.float_array[BoxTextAll.B_FA_SPACE] = this.wh10;
			char[] char_array = this.char_2d_array[i];
			BoxTextAll.set(this.float_array, this.w_float_array, char_array, bta_index);
			bta_index += char_array.length * BoxVT.B_WFAL;
		}
	}

	public void genBoxColor()
	{
//		float y = this.y - this.wh40 * this.select.intValue();
		float y = this.y - this.wh40 * this.select;
		this.float_array[BoxV.B_FA_X0] = this.wh20;
		this.float_array[BoxV.B_FA_Y0] = y;
		this.float_array[BoxV.B_FA_X1] = this.x - this.wh10;
		this.float_array[BoxV.B_FA_Y1] = y + this.wh20;
		BoxV.set(this.float_array, this.w_float_array, 0, (byte)0);
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
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		Box.draw(this.rs0, this.v_float_array, this.c_float_array, this.array_buffer, BoxVT.B_AP_SIZE, 3, this.ca0_t_count);

		//s0-shader
		GL20.glDisableVertexAttribArray(v0);
		//e0-shader

		boolean gl_scissor_test = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);
		GL11.glGetInteger(GL11.GL_SCISSOR_BOX, RenderO.INTBUFFER);

		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(0, (int)(this.wh20 * 3), Box.WIDTH, (int)(Box.HEIGHT - this.wh20 * 4));

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
		Box.draw(this.rs1, this.v_float_array, this.gc_float_array, this.array_buffer, BoxV.B_AP_SIZE, 0, 6);
	}

	public void drawBoxTextAll()
	{
		Box.draw(this.rs0, this.v_float_array, this.c_float_array, this.array_buffer, BoxVT.B_AP_SIZE, 3 + this.ca0_t_count, this.ta_t_count);
//		for (byte i = 1; i < this.boxtextall_array.length; ++i)
//		{
//			this.boxtextall_array[i].draw(this.rs0, this.v_float_array, this.c_float_array);
//		}
	}

	@Override
	public void clear()
	{
		if (this.array_buffer != -1)
		{
			OpenGlHelper.glDeleteBuffers(this.array_buffer);
		}
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

		if (Box.WIDTH != width || Box.HEIGHT != height)
		{
			Box.WIDTH = width;
			Box.HEIGHT = height;
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
//			if (this.select.equals(this.min_select) && step < 0)
			if (this.select == this.min_select && step < 0)
			{
//				this.select = this.createN(this.boxtextall_array.length - 1);
				this.select = (byte)(this.char_2d_array.length - 1);
			}
//			else if (this.select.equals(this.createN(this.boxtextall_array.length - 1)) && step > 0)
			else if (this.select == this.char_2d_array.length - 1 && step > 0)
			{
				this.select = this.min_select;
			}
			else
			{
//				this.select = this.pN(this.select, step);
				this.select += step;
			}

//			int index = this.select.intValue() - 1;
//			int index = this.select - 1;
			byte index = (byte)(this.select - 1);
			byte bit = (byte)(1 << index % 8);
			if ((this.group_byte_array[index / 8] & bit) != bit)
			{
				return;
			}
		}
	}

	public abstract void enter();

	@Override
	public void exit()
	{
		this.fl |= BF_EXIT_LOOP;
	}

//	public abstract N createN(int i);
//	public abstract N pN(N n, int i);
}
