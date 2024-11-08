package com.nali.system.opengl.memo.client;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static com.nali.Nali.error;
import static com.nali.Nali.warn;

@SideOnly(Side.CLIENT)
public abstract class MemoH
{
	public int shader;//vert frag

	public static ByteBuffer getFrom(StringBuilder stringbuilder)
	{
		String string = stringbuilder.toString();
		ByteBuffer bytebuffer = ByteBuffer.allocateDirect(string.length()).order(ByteOrder.nativeOrder());
		bytebuffer.put(string.getBytes());
		bytebuffer.flip();
		return bytebuffer;
	}

	public static int genShader(ByteBuffer bytebuffer, int type, String info_string)
	{
		int shader = OpenGlHelper.glCreateShader(type);
//		int shader = OpenGlHelper.glCreateShader(type);
//		OpenGlHelper.glShaderSource(shader, stringbuilder);
//		OpenGlHelper.glShaderSource(shader, bytebuffer);
		OpenGlHelper.glShaderSource(shader, bytebuffer);
//		OpenGlHelper.glCompileShader(shader);
		OpenGlHelper.glCompileShader(shader);

//		if (OpenGlHelper.glGetShaderi(shader, OpenGlHelper.GL_COMPILE_STATUS) == OpenGlHelper.GL_FALSE)
		if (OpenGlHelper.glGetShaderi(shader, OpenGlHelper.GL_COMPILE_STATUS) == GL11.GL_FALSE)
		{
//			int error = OpenGlHelper.glGetError();
//			if (error != OpenGlHelper.GL_NO_ERROR)
//			{
//				Nali.error(GLU.gluErrorString(error));
//			}
//			Nali.error(OpenGlHelper.glGetShaderInfoLog(shader, 1024));
			warn(info_string);
			error(OpenGlHelper.glGetShaderInfoLog(shader, 1024));
		}

//		//reset memory
//		OpenGlHelper.glDeleteShader(shader);
//		shader = OpenGlHelper.glCreateShader(type);
//		OpenGlHelper.glShaderSource(shader, bytebuffer);
//		OpenGlHelper.glCompileShader(shader);

		return shader;
	}
}
