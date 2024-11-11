package com.nali.gui.page;

import com.nali.gui.box.text.BoxText09;
import com.nali.gui.box.text.BoxTextAZ;
import com.nali.list.data.NaliData;
import com.nali.system.ClientLoader;
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
	public BoxText09 config_date_boxtext09;
	public BoxTextAZ[] boxtextaz_array;

	@Override
	public void init()
	{
		//s0-date
		try
		{
			BasicFileAttributes basicfileattributes = Files.readAttributes(Paths.get("nali/nali/tmp/config.bin"), BasicFileAttributes.class);
			Instant instant = basicfileattributes.lastModifiedTime().toInstant();
			LocalDateTime localdatetime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

			this.config_date_boxtext09 = new BoxText09((String.format("%02d", localdatetime.getMonthValue()) + String.format("%02d", localdatetime.getDayOfMonth()) + localdatetime.getYear()).toCharArray());
		}
		catch (Exception ex)
		{
			error(ex);
		}
		//e0-date

		this.boxtextaz_array = new BoxTextAZ[]
		{
			new BoxTextAZ("NALICRAFT".toCharArray()),
			new BoxTextAZ("IS".toCharArray()),
			new BoxTextAZ("LOADING".toCharArray())
		};
	}

	@Override
	public void gen()
	{
		//854 x 480
		int wh20 = Math.min((int)(0.0234192037470726F * WIDTH), (int)(0.041666668F * HEIGHT));
		int wh10 = Math.min((int)(0.011709602F * WIDTH), (int)(0.020833334F * HEIGHT));
		int wh5 = Math.min((int)(0.005854801F * WIDTH), (int)(0.010416667F * HEIGHT));
		this.config_date_boxtext09.gen
		(
			wh20,
			(int)(wh20 * 2.5F),
			wh10, wh5, WIDTH, HEIGHT
		);
		this.boxtextaz_array[0].gen
		(
			wh20,
			wh20,
			wh20, wh10, WIDTH, HEIGHT
		);
		this.boxtextaz_array[1].gen
		(
			wh20 + (wh20 + wh10) * 9,
			wh20,
			wh10, wh5, WIDTH, HEIGHT
		);
		this.boxtextaz_array[2].gen
		(
			wh20 + (wh20 + wh10) * 9 + (wh10 + wh5) * 2 + wh20,
			wh20,
			wh10, wh5, WIDTH, HEIGHT
		);
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
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		this.config_date_boxtext09.draw(rs, this.v_float_array, this.c_float_array);
		for (BoxTextAZ boxtextaz : this.boxtextaz_array)
		{
			boxtextaz.draw(rs, this.v_float_array, this.c_float_array);
		}

		//s0-shader
		GL20.glDisableVertexAttribArray(v);
		//e0-shader
	}

	@Override
	public void clear()
	{
		OpenGlHelper.glDeleteBuffers(this.config_date_boxtext09.array_buffer);
		for (BoxTextAZ boxtextaz : this.boxtextaz_array)
		{
			OpenGlHelper.glDeleteBuffers(boxtextaz.array_buffer);
		}
	}

	@Override
	public void render()
	{
		this.take();
		int width = Display.getWidth();
		int height = Display.getHeight();
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

	@Override
	public void exit()
	{
	}
}
