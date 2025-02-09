package com.nali.render;

import com.nali.NaliConfig;
import com.nali.NaliGL;
import com.nali.da.IBothDaO;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.nali.system.ClientLoader.*;

@SideOnly(Side.CLIENT)
public class RenderO
<
	BD extends IBothDaO
>
{
	public static ByteBuffer BYTEBUFFER = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder());
	public static IntBuffer INTBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
	public static FloatBuffer FLOATBUFFER = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

//	public static float R_GL_ALPHA_TEST_REF;
	public static int
	//	R_GL_RENDERBUFFER_BINDING,
	//	R_GL_DEPTH_ATTACHMENT,
	//	R_GL_DRAW_FRAMEBUFFER_BINDING,
	//	R_GL_READ_FRAMEBUFFER_BINDING,

		R_GL_VERTEX_ARRAY_BINDING,
		R_GL_CURRENT_PROGRAM,
		R_GL_ELEMENT_ARRAY_BUFFER_BINDING,
		R_GL_ARRAY_BUFFER_BINDING,
		R_GL_BLEND_EQUATION_RGB,
		R_GL_BLEND_EQUATION_ALPHA,
		R_GL_BLEND_SRC_RGB,
		R_GL_BLEND_SRC_ALPHA,
		R_GL_BLEND_DST_RGB,
		R_GL_BLEND_DST_ALPHA,

//		R_GL_ACTIVE_TEXTURE,
	//	GL_ACTIVE_TEXTURE_0;
	//	GL_ACTIVE_TEXTURE_1;
	//	GL_ACTIVE_TEXTURE_2;
		R_GL_TEXTURE_BINDING_2D,
//		R_GL_TEXTURE_BINDING_2D_0,
//		R_GL_TEXTURE_BINDING_2D_1,
	//	R_GL_TEXTURE_BINDING_2D_2,
	//	R_GL_TEXTURE_BINDING_2D_3,

	//	GL_FRONT_FACE;

		R_GL_DEPTH_WRITEMASK;//after use extra this should be removed
	//	GL_DEPTH_FUNC;

	//	R_GL_MATRIX_MODE,

	//	GL_TEXTURE_WRAP_S,
//		R_GL_TEXTURE_WRAP_S_0,
//		R_GL_TEXTURE_WRAP_S_1,
	//	R_GL_TEXTURE_WRAP_S_2,
	//	R_GL_TEXTURE_WRAP_S_3,
	//	GL_TEXTURE_WRAP_T,
//		R_GL_TEXTURE_WRAP_T_0,
//		R_GL_TEXTURE_WRAP_T_1,
	//	R_GL_TEXTURE_WRAP_T_2,
	//	R_GL_TEXTURE_WRAP_T_3,
	//	GL_TEXTURE_MIN_FILTER,
//		R_GL_TEXTURE_MIN_FILTER_0,
//		R_GL_TEXTURE_MIN_FILTER_1,
	//	R_GL_TEXTURE_MIN_FILTER_2,
	//	R_GL_TEXTURE_MIN_FILTER_3,
	//	GL_TEXTURE_MAG_FILTER,
//		R_GL_TEXTURE_MAG_FILTER_0;
//		R_GL_TEXTURE_MAG_FILTER_1;
	//	R_GL_TEXTURE_MAG_FILTER_2;
	//	R_GL_TEXTURE_MAG_FILTER_3;

	//1 GL_DEPTH_TEST
	//2 R_GL_CULL_FACE
	//4 R_GL_BLEND
	public static byte GL_STATE;
//	public static boolean
//		R_GL_DEPTH_TEST,
//		R_GL_CULL_FACE,
//		R_GL_BLEND;
//	//	R_GL_TEXTURE_2D;
//	//	GL_LIGHTING;

//	public static float GL_LINE_WIDTH;

//	public static float[] GL_CURRENT_COLOR = new float[4];

//	public float light_b = -1.0F, light_s;
	public int i;
	public MemoG rg;
	public MemoS rs;
	public BD bd;

	public RenderO(BD bd)
	{
		this.bd = bd;
	}

	public static void take()
	{
		GL_STATE = (byte)((GL11.glIsEnabled(GL11.GL_DEPTH_TEST) ? 1 : 0) | (GL11.glIsEnabled(GL11.GL_CULL_FACE) ? 2 : 0) | (GL11.glIsEnabled(GL11.GL_BLEND) ? 4 : 0));
//		GL11.glEnable(GL11.GL_DEPTH_TEST);
//		GL11.glEnable(GL11.GL_BLEND);

//		RenderO.FLOATBUFFER.limit(16);
//		GL11.glGetFloat(GL11.GL_ALPHA_TEST_REF, RenderO.FLOATBUFFER);
//		R_GL_ALPHA_TEST_REF = RenderO.FLOATBUFFER.get(0);
//		GL11.glAlphaFunc(GL11.GL_GREATER, 0.0F);

		GL11.glGetBoolean(GL11.GL_DEPTH_WRITEMASK, BYTEBUFFER);
		R_GL_DEPTH_WRITEMASK = BYTEBUFFER.get(0);
		GL11.glDepthMask(true);

		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, INTBUFFER);
		R_GL_CURRENT_PROGRAM = INTBUFFER.get(0);

		if (NaliConfig.VAO)
		{
			R_GL_VERTEX_ARRAY_BINDING = NaliGL.glVertexArrayBinding();
		}
		else
		{
			GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, INTBUFFER);
			R_GL_ELEMENT_ARRAY_BUFFER_BINDING = INTBUFFER.get(0);

			GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, INTBUFFER);
			R_GL_ARRAY_BUFFER_BINDING = INTBUFFER.get(0);
		}

		//unsafe current texture buffer
		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
		R_GL_TEXTURE_BINDING_2D = INTBUFFER.get(0);

//		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, INTBUFFER);
//		R_GL_ACTIVE_TEXTURE = INTBUFFER.get(0);
//
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
//		R_GL_TEXTURE_BINDING_2D_0 = INTBUFFER.get(0);
//		R_GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//		R_GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//		R_GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//		R_GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, INTBUFFER);
//		R_GL_TEXTURE_BINDING_2D_1 = INTBUFFER.get(0);
//		R_GL_TEXTURE_WRAP_S_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//		R_GL_TEXTURE_WRAP_T_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//		R_GL_TEXTURE_MIN_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//		R_GL_TEXTURE_MAG_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		R_GL_TEXTURE_BINDING_2D_2 = OPENGL_INTBUFFER.get(0);
//		R_GL_TEXTURE_WRAP_S_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//		R_GL_TEXTURE_WRAP_T_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//		R_GL_TEXTURE_MIN_FILTER_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//		R_GL_TEXTURE_MAG_FILTER_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, INTBUFFER);
		R_GL_BLEND_EQUATION_RGB = INTBUFFER.get(0);
		GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, INTBUFFER);
		R_GL_BLEND_EQUATION_ALPHA = INTBUFFER.get(0);

		GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, INTBUFFER);
		R_GL_BLEND_SRC_RGB = INTBUFFER.get(0);
		GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, INTBUFFER);
		R_GL_BLEND_SRC_ALPHA = INTBUFFER.get(0);
		GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, INTBUFFER);
		R_GL_BLEND_DST_RGB = INTBUFFER.get(0);
		GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, INTBUFFER);
		R_GL_BLEND_DST_ALPHA = INTBUFFER.get(0);

//		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//		GL14.glBlendEquation(GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_DST_ALPHA, GL11.GL_ONE, GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_ALPHA);
//		GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
//		GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
	}

	public static void free()
	{
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_0);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_0);

//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_1);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_1);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_1);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_1);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_1);

//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, R_GL_TEXTURE_WRAP_S_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, R_GL_TEXTURE_WRAP_T_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, R_GL_TEXTURE_MIN_FILTER_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, R_GL_TEXTURE_MAG_FILTER_2);

//		OpenGlHelper.setActiveTexture(R_GL_ACTIVE_TEXTURE);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, R_GL_TEXTURE_BINDING_2D);

		OpenGlHelper.glUseProgram(R_GL_CURRENT_PROGRAM);

		if (NaliConfig.VAO)
		{
			NaliGL.glBindVertexArray(R_GL_VERTEX_ARRAY_BINDING);
		}
		else
		{
			OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, R_GL_ELEMENT_ARRAY_BUFFER_BINDING);
			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, R_GL_ARRAY_BUFFER_BINDING);
		}

//		GL11.glAlphaFunc(GL11.GL_GREATER, R_GL_ALPHA_TEST_REF);

		GL20.glBlendEquationSeparate(R_GL_BLEND_EQUATION_RGB, R_GL_BLEND_EQUATION_ALPHA);
		GL14.glBlendFuncSeparate(R_GL_BLEND_SRC_RGB, R_GL_BLEND_DST_RGB, R_GL_BLEND_SRC_ALPHA, R_GL_BLEND_DST_ALPHA);

		if ((GL_STATE & 1) == 1)
		{
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		else
		{
			GL11.glDisable(GL11.GL_DEPTH_TEST);
		}

		if ((GL_STATE & 2) == 2)
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
		else
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
		}

		if ((GL_STATE & 4) == 4)
		{
			GL11.glEnable(GL11.GL_BLEND);
		}
		else
		{
			GL11.glDisable(GL11.GL_BLEND);
		}

		GL11.glDepthMask((R_GL_DEPTH_WRITEMASK & 1) == 1);
	}

	public static void disableBuffer(MemoS rs)
	{
		for (int i : rs.attriblocation_int_array)
		{
			GL20.glDisableVertexAttribArray(i);
		}
	}

	public static void enableBuffer(MemoG rg, MemoS rs)
	{
		int program = rs.program;

		OpenGlHelper.glUseProgram(program);

		if (NaliConfig.VAO)
		{
			NaliGL.glBindVertexArray(rg.vao);
		}
		else
		{
			OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, rg.buffer);
			int[] attriblocation_int_array = rs.attriblocation_int_array;
			for (int i = 0; i < attriblocation_int_array.length; ++i)
			{
				int attriblocation = attriblocation_int_array[i];
				MemoA memoa = rg.memoa_array[i];
				GL20.glEnableVertexAttribArray(attriblocation);
				GL20.glVertexAttribPointer(attriblocation, memoa.size, memoa.type, false, rg.stride, memoa.offset);
			}
			OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, rg.ebo);
		}

		if ((rg.state & MemoG.B_CULL) == 0)
		{
			GL11.glDisable(GL11.GL_CULL_FACE);
		}
		else
		{
			GL11.glEnable(GL11.GL_CULL_FACE);
		}
	}

//	public static void setTextureBuffer(int buffer, byte texture_state)
//	{
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, buffer);
//
//		switch (texture_state)
//		{
//			case 0:
//			{
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
////				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//				break;
//			}
//			case 1:
//			{
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//				break;
//			}
//			case 2:
//			{
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_CLAMP);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_CLAMP);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
//				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//				break;
//			}
//			default:
//			{
//				error("TEXTURE_LEAK " + texture_state);
//			}
//		}
//	}

	public void setTextureUniform()
	{
	//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
	//		setTextureBuffer(this.getTextureBuffer(rg), (byte)(rg.flag & 1+2));
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.getTextureBuffer());
	}

	public int getTextureID()
	{
		return this.rg.texture_id;
	}

	public int getTextureBuffer()
	{
		return TEXTURE_INTEGER_LIST.get(this.getTextureID())/*.texture_buffer*/;
	}

	public int getShaderID()
	{
		return this.rg.shader_id;
	}

//	public boolean getGlow(MemoG rg)
//	{
//		return (rg.flag & 16) == 16;
//	}
//
//	public boolean getTransparent(MemoG rg)
//	{
//		return (rg.flag & 8) == 8;
//	}

//	public void draw(BD bd)
	public void doDraw()
	{
//		for (int i = bd.O_StartPart(); i < bd.O_EndPart(); ++i)
		for (this.i = this.bd.O_StartPart(); this.i < this.bd.O_EndPart(); ++this.i)
		{
			this.rg = G_LIST.get(this.i);
			this.rs = S_LIST.get(this.getShaderID());
//			this.draw(/*i, */rg, rs);
			this.draw();
		}
	}

//	public void draw(/*int index, */MemoG rg, MemoS rs)
	public void draw()
	{
//		float light_b = this.light_b;
//		float light_s = this.light_s;
//
//		//this.updateLight(rg);
//		if (this.getGlow(rg))
//		{
//			this.light_b = -1.0F;
//			this.light_s = -1.0F;
//		}
		enableBuffer(this.rg, this.rs);

//		this.setUniform(rg, rs/*, index*/);
		this.setUniform();

		if (NaliConfig.VAO)
		{
			NaliGL.glDrawElementsTUi0(this.rg.index_length);
		}
		else
		{
			GL11.glDrawElements(GL11.GL_TRIANGLES, this.rg.index_length, GL11.GL_UNSIGNED_INT, 0);
		}

		if (!NaliConfig.VAO)
		{
			disableBuffer(this.rs);
		}
//		this.light_b = light_b;
//		this.light_s = light_s;
	}

//	public void setUniform(MemoG rg, MemoS rs/*, int index*/)
	public void setUniform()
	{
		FLOATBUFFER.limit(16);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, FLOATBUFFER);
		OpenGlHelper.glUniformMatrix4(this.rs.uniformlocation_int_array[0], false, FLOATBUFFER);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, FLOATBUFFER);
		OpenGlHelper.glUniformMatrix4(this.rs.uniformlocation_int_array[1], false, FLOATBUFFER);
		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, FLOATBUFFER);
		FLOATBUFFER.limit(4);
		OpenGlHelper.glUniform4(this.rs.uniformlocation_int_array[4], FLOATBUFFER);

//		this.setTextureUniform(rg, rs);
		this.setTextureUniform();
//		this.setMapUniform(rs);
//		this.setLightUniform(rs);
	}
}
