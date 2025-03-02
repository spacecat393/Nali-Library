package com.nali.gui.page;

import com.nali.gui.box.Box;
import com.nali.gui.box.BoxV;
import com.nali.gui.box.BoxVT;
import com.nali.gui.box.text.BoxText09;
import com.nali.gui.box.text.BoxTextAZ;
import com.nali.list.data.NaliData;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public class PageLoad extends Page
{
//	public BoxText09 config_date_boxtext09;
//	public BoxTextAZ[] boxtextaz_array;
	public char[][] char_2d_array;
	public int[] int_array;
	public float[]
		float_array,
		w_float_array;
	public int
		t_count,

		array_buffer = -1;

	@Override
	public void init()
	{
		this.char_2d_array = new char[4][];

		//s0-date
		try
		{
			BasicFileAttributes basicfileattributes = Files.readAttributes(Paths.get("nali/nali/tmp/config"), BasicFileAttributes.class);
			Instant instant = basicfileattributes.lastModifiedTime().toInstant();
			LocalDateTime localdatetime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

			this.char_2d_array[0] = (String.format("%02d", localdatetime.getMonthValue()) + String.format("%02d", localdatetime.getDayOfMonth()) + localdatetime.getYear()).toCharArray();
			BoxText09.set(this.char_2d_array[0]);
		}
		catch (Exception ex)
		{
			error(ex);
		}
		//e0-date

		this.char_2d_array[1] = "NALICRAFT".toCharArray();
		this.char_2d_array[2] = "IS".toCharArray();
		this.char_2d_array[3] = "LOADING".toCharArray();
		BoxTextAZ.set(this.char_2d_array[1]);
		BoxTextAZ.set(this.char_2d_array[2]);
		BoxTextAZ.set(this.char_2d_array[3]);

		this.int_array = new int[BoxText09.B_IAL];
		this.float_array = new float[BoxV.B_FAL + BoxVT.B_FAL];

		this.float_array[BoxVT.B_FA_T_WIDTH] = 170;
		this.float_array[BoxVT.B_FA_T_HEIGHT] = 5;

		this.float_array[BoxVT.B_FA_V1] = 5;

		for (char[] char_array : this.char_2d_array)
		{
			this.t_count += char_array.length;
		}
		this.w_float_array = new float[this.t_count * BoxVT.B_WFAL];
		this.t_count *= 6;
	}

	@Override
	public void gen()
	{
		this.clear();
		//854 x 480
		int wh20 = Math.min((int)(0.0234192037470726F * Box.WIDTH), (int)(0.041666668F * Box.HEIGHT));
		int wh10 = Math.min((int)(0.011709602F * Box.WIDTH), (int)(0.020833334F * Box.HEIGHT));
		int wh5 = Math.min((int)(0.005854801F * Box.WIDTH), (int)(0.010416667F * Box.HEIGHT));
		this.float_array[BoxV.B_FA_V_WIDTH] = Box.WIDTH;
		this.float_array[BoxV.B_FA_V_HEIGHT] = Box.HEIGHT;

		this.int_array[BoxText09.B_IA_X] = wh20;
		this.int_array[BoxText09.B_IA_Y] = (int)(wh20 * 2.5F);
		this.int_array[BoxText09.B_IA_SIZE] = wh10;
		this.int_array[BoxText09.B_IA_SPACE] = wh5;
		int index = 0;
		char[] char_array = this.char_2d_array[0];
		BoxText09.set(this.int_array, this.float_array, this.w_float_array, char_array, index);
		index += char_array.length * BoxVT.B_WFAL;

//		this.int_array[BoxTextAZ.B_IA_X] = wh20;
		this.int_array[BoxTextAZ.B_IA_Y] = wh20;
		this.int_array[BoxTextAZ.B_IA_SIZE] = wh20;
		this.int_array[BoxTextAZ.B_IA_SPACE] = wh10;
		char_array = this.char_2d_array[1];
		BoxTextAZ.set(this.int_array, this.float_array, this.w_float_array, char_array, index);
		index += char_array.length * BoxVT.B_WFAL;

		this.int_array[BoxTextAZ.B_IA_X] = wh20 + (wh20 + wh10) * 9;
//		this.int_array[BoxTextAZ.B_IA_Y] = wh20;
		this.int_array[BoxTextAZ.B_IA_SIZE] = wh10;
		this.int_array[BoxTextAZ.B_IA_SPACE] = wh5;
		char_array = this.char_2d_array[2];
		BoxTextAZ.set(this.int_array, this.float_array, this.w_float_array, char_array, index);
		index += char_array.length * BoxVT.B_WFAL;

		this.int_array[BoxTextAZ.B_IA_X] = wh20 + (wh20 + wh10) * 9 + (wh10 + wh5) * 2 + wh20;
//		this.int_array[BoxTextAZ.B_IA_Y] = wh20;
//		this.int_array[BoxTextAZ.B_IA_SIZE] = wh10;
//		this.int_array[BoxTextAZ.B_IA_SPACE] = wh5;
		BoxTextAZ.set(this.int_array, this.float_array, this.w_float_array, this.char_2d_array[3], index);

		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(this.w_float_array));
	}

	@Override
	public void draw()
	{
		//s0-shader
		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);
		//e0-shader

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, ClientLoader.TEXTURE_INTEGER_LIST.get(NaliData.TEXTURE_STEP));
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

//		this.config_date_boxtext09.draw(rs, this.v_float_array, this.c_float_array);
//		for (BoxTextAZ boxtextaz : this.boxtextaz_array)
//		{
//			boxtextaz.draw(rs, this.v_float_array, this.c_float_array);
//		}
		Box.draw(rs, this.v_float_array, this.c_float_array, this.array_buffer, BoxVT.B_AP_SIZE, 0, this.t_count);

		//s0-shader
		GL20.glDisableVertexAttribArray(v);
		//e0-shader
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

	@Override
	public void exit()
	{
	}
}
