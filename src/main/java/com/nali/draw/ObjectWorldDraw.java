//package com.nali.draw;
//
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//
//@SideOnly(Side.CLIENT)
//public class ObjectWorldDraw
//{
////	public static Consumer<OpenGLObjectMemory> DRAW_CONSUMER;
////	public ObjectRender objectrender;
//
////	public ObjectWorldDraw(ObjectRender objectrender)
////	{
////		this.objectrender = objectrender;
////	}
//
//	public void renderWorld(/*double ox, double oy, double oz, float scale*/)
//	{
////		OBJECT_LIST.add(this.objectrender);
////		DOUBLE_LIST.add(ox);
////		DOUBLE_LIST.add(oy);
////		DOUBLE_LIST.add(oz);
////		FLOAT_LIST.add(this.objectrender.entitiesrendermemory.scale)
////		int should_add = 0;
////		//Array here
//////
//////		if ((this.objectrender.glow_byte_array[index / 8] >> index % 8 & 1) == 1)
//////		{
//////			this.objectrender.lig_b = -1.0F;
//////			this.objectrender.lig_s = -1.0F;
//////		}
//////		else
//////		{
//////			this.objectrender.updateLightCoord();
//////		}
////
////		for (int i = 0; i < OBJECT_LIST.size(); ++i)
////		{
////			for (int l = 0; l < this.objectrender.memory_object_array.length; ++l)
////			{
////				if ((this.objectrender.model_byte_array[i / 8] >> i % 8 & 1) == 1)
////				{
////					if (OBJECT_LIST.get(i) == this.objectrender.memory_object_array[l])
////					{
////						should_add = -1;
////						INTEGER_LIST.add(i);
////						POS_DOUBLE_2D_LIST.get(i).add(ox);
////						POS_DOUBLE_2D_LIST.get(i).add(oy);
////						POS_DOUBLE_2D_LIST.get(i).add(oz);
////						break;
////					}
////				}
////			}
////		}
////
////		if (should_add != -1)
////		{
////			OBJECT_LIST.add(this.objectrender.memory_object_array[should_add]);
////			int index = this.objectrender.memory_object_array.length;
////			INTEGER_LIST.add(index);
////			POS_DOUBLE_2D_LIST.add(new List<Double>());
////			POS_DOUBLE_2D_LIST.get(index).add(ox);
////			POS_DOUBLE_2D_LIST.get(index).add(oy);
////			POS_DOUBLE_2D_LIST.get(index).add(oz);
////			LIG_FLOAT_2D_LIST.add(new List<Float>());
////			LIG_FLOAT_2D_LIST.get(index).add(ox);
////			LIG_FLOAT_2D_LIST.get(index).add(oy);
////			LIG_FLOAT_2D_LIST.get(index).add(oz);
////		}
//
//		GL11.glPushMatrix();
////		OpenGlHelper.glTranslated(ox, oy, oz);
//
////		OpenGlHelper.glScalef(scale, scale, scale);
//
//		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
//
////		if (MyConfig.SHADER.gl_draw_elements_instanced)
////		{
////			for (int i = 0; i < this.objectrender.getMaxMemory(); ++i)
////			{
////				if ((this.objectrender.model_byte_array[i / 8] >> i % 8 & 1) == 1)
////				{
////					if ((this.objectrender.glow_byte_array[i / 8] >> i % 8 & 1) == 1)
////					{
////						this.objectrender.lig_b = -1.0F;
////						this.objectrender.lig_s = -1.0F;
////					}
////					else
////					{
////						this.objectrender.updateLightCoord();
////					}
////
////					ObjectWorldDrawData.add(this.objectrender.memory_object_array);
////					OpenGLObjectMemory openglobjectmemory = this.objectrender.getMemory(i);
////					OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
////					this.objectrender.setWorldFloatBuffer(openglobjectshadermemory);
////				}
////			}
////		}
////		else
////		{
////		this.objectrender.draw();
////		for (int i = this.objectrender.clientdata.StartPart(); i < this.objectrender.clientdata.EndPart(); ++i)
////		{
////			if ((this.objectrender.model_byte_array[i / 8] >> i % 8 & 1) == 1)
////			{
////				OpenGLObjectMemory openglobjectmemory = this.objectrender.dataloader.openglobjectmemory_array[i];
////				OpenGLObjectShaderMemory openglobjectshadermemory = this.objectrender.dataloader.openglobjectshadermemory_array[openglobjectmemory.shader_id];
////				this.drawWorld(openglobjectmemory, openglobjectshadermemory, i);
////			}
////		}
////		}
//
////		OpenGlHelper.glRotatef(-rx, 1.0F, 0.0F, 0.0F);
////		OpenGlHelper.glScalef(1.0F / scale, 1.0F / scale, 1.0F / scale);
////
////		OpenGlHelper.glTranslated(-ox, -oy, -oz);
//		GL11.glPopMatrix();
//	}
//
////	public void drawWorld(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
////	{
//////		OpenGlHelper.glPushAttrib(OpenGlHelper.GL_COLOR_BUFFER_BIT);
////		this.objectrender.takeDefault(openglobjectmemory, openglobjectshadermemory);
////
//////		if ((this.objectrender.glow_byte_array[index / 8] >> index % 8 & 1) == 1)
////		if ((openglobjectmemory.state & 8) == 8)
////		{
////			this.objectrender.lig_b = -1.0F;
////			this.objectrender.lig_s = -1.0F;
//////			OpenGlHelper.glColor4f(GL_CURRENT_COLOR[0], GL_CURRENT_COLOR[1], GL_CURRENT_COLOR[2], this.objectrender.a);
////		}
////		else
////		{
//////			OpenGlHelper.glColor4f(this.objectrender.r, this.objectrender.g, this.objectrender.b, this.objectrender.a);
////			this.objectrender.updateLightCoord();
////		}
////
////		this.setUniform(openglobjectmemory, openglobjectshadermemory, index);
////
////		DRAW_CONSUMER.accept(openglobjectmemory);
////
//////		OpenGlHelper.glPopAttrib();
////		this.objectrender.setDefault(openglobjectshadermemory);
////	}
//
////	public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
////	{
////		this.objectrender.setFixedPipe(openglobjectshadermemory);
////		this.objectrender.setTextureUniform(openglobjectmemory, openglobjectshadermemory);
////		this.objectrender.setLightMapUniform(openglobjectshadermemory);
////		this.objectrender.setLightCoord(openglobjectshadermemory);
////		this.objectrender.setUniform(openglobjectmemory, openglobjectshadermemory, index);
////	}
//
////	public static void setTUsingFrameBufferIndex()
////	{
////		DRAW_CONSUMER = (openglobjectmemory) ->
////		{
////			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			int draw_frame_buffer = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			int read_frame_buffer = OPENGL_INTBUFFER.get(0);
////
////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, MyConfig.FRAME.frame_buffer_index);
////
////			GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
////
////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, read_frame_buffer);
////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, draw_frame_buffer);
////		};
////	}
//
////	public static void setFUsingFrameBufferIndex()
////	{
////		DRAW_CONSUMER = (openglobjectmemory) ->
////		{
////			GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
////		};
////	}
////
////	public static void loadWithConfig()
////	{
////		if (MyConfig.FRAME.using_frame_buffer_index)
////		{
////			ObjectWorldDraw.setTUsingFrameBufferIndex();
////		}
////		else
////		{
////			ObjectWorldDraw.setFUsingFrameBufferIndex();
////		}
////	}
//}
