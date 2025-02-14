package com.nali.gui.page;

import com.nali.gui.key.Key;
import com.nali.render.RenderO;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public abstract class Page
{
	public static Page PAGE;
	public static int WIDTH, HEIGHT;
	public static int QUAD2D_ARRAY_BUFFER;

	public static List<Page> PAGE_LIST = new ArrayList();
	public static List<Key> KEY_LIST = new ArrayList();

	public float[] v_float_array = new float[]{0.0F, 0.0F};
	public float[] c_float_array = new float[]{1.0F, 1.0F, 1.0F, 1.0F};

	public int
		gl_current_program,
		gl_texture_binding_2d_0,
//		gl_texture_min_filter_0,
//		gl_texture_mag_filter_0,
		gl_array_buffer_binding;

	public void take()
	{
		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
		this.gl_current_program = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
		this.gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);
//		this.gl_texture_min_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//		this.gl_texture_mag_filter_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		this.gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
	}

	public abstract void init();
	public abstract void gen();
	public abstract void draw();
	public abstract void clear();

	public void free()
	{
		OpenGlHelper.glUseProgram(this.gl_current_program);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.gl_texture_binding_2d_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, this.gl_texture_min_filter_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, this.gl_texture_mag_filter_0);
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, this.gl_array_buffer_binding);
	}

	public abstract void render();

	public void set(Page page, Key key)
	{
		this.clear();
		PAGE = page;
		PAGE.init();
		WIDTH = -1;
		Key.KEY = key;
	}

	public abstract void exit();

	public static void exitAll()
	{
		for (int i = PAGE_LIST.size() - 1; i > -1; --i)
		{
			Page page = PAGE_LIST.get(i);
			page.exit();
		}
	}

	public char[] getChar(String string)
	{
		if (string.length() > 20)
		{
			string = string.substring(0, 20) + "...";
		}
		return string.toCharArray();
	}
}
