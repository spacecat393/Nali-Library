//package com.nali.draw;
//
//import com.nali.Nali;
//import com.nali.list.data.NaliData;
//import com.nali.mixin.IMixinEntityRenderer;
//import com.nali.render.RenderO;
//import com.nali.system.bytes.ByteReader;
//import com.nali.system.opengl.memo.client.MemoG;
//import com.nali.system.opengl.memo.client.MemoS;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL13;
//import org.lwjgl.opengl.GL20;
//import org.lwjgl.opengl.GL30;
//
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static com.nali.draw.DrawWorld.*;
//import static com.nali.render.RenderO.setLightMapBuffer;
//import static com.nali.render.RenderO.setTextureBuffer;
//import static com.nali.system.ClientLoader.G_LIST;
//import static com.nali.system.ClientLoader.S_LIST;
//import static com.nali.system.opengl.memo.client.MemoC.*;
//
//@SideOnly(Side.CLIENT)
//@Mod.EventBusSubscriber(modid = Nali.ID, value = Side.CLIENT)
//public class DrawWorldExFb
//{
//	public static boolean
//	EX_FRAMEBUFFER/*,
//	TRANSLUCENT*/;
//
//	public static int
//
////	PBO,
//	DEPTH0_TEXTURE,
////	DEPTH1_TEXTURE,
////	R_S_FRAMEBUFFER_TEXTURE = -1,
////	R_S_RENDERBUFFER_TEXTURE = -1,
//	R_T_FRAMEBUFFER_TEXTURE,
//	R_T_RENDERBUFFER_TEXTURE,
//	R_G_FRAMEBUFFER_TEXTURE,
//	R_G_RENDERBUFFER_TEXTURE,
////	R_TG_FRAMEBUFFER_TEXTURE = -1,
////	R_TG_RENDERBUFFER_TEXTURE = -1,
//	R_MCTB_FRAMEBUFFER_TEXTURE,
//	R_MCTB_RENDERBUFFER_TEXTURE,
//
////	R_M_FRAMEBUFFER_TEXTURE = -1,
////	R_M_RENDERBUFFER_TEXTURE = -1,
//
////	MC_FRAMEBUFFER = -1,
////	MC_RENDERBUFFER = -1,
//	DEPTH_COLOR0_TEXTURE,
//
//	GL_COLOR_ATTACHMENT0,
//	GL_DEPTH_ATTACHMENT,
//
//	DISPLAY_WIDTH,
//	DISPLAY_HEIGHT;
//
////	public static float R, G, B;
//
//	//depth 4 to rgba
////	public static ByteBuffer PBO_BYTEBUFFER = ByteBuffer.allocateDirect(1920 * 1080 * 4).order(ByteOrder.nativeOrder());
//
//	public static void gen()
//	{
////		MIX_TEXTURE = GL11.glGenTextures();
////		MC_FRAMEBUFFER = OpenGlHelper.glGenFramebuffers();
////		MC_RENDERBUFFER = OpenGlHelper.glGenRenderbuffers();
//
//		DEPTH_COLOR0_TEXTURE = GL11.glGenTextures();
//		DEPTH0_TEXTURE = GL11.glGenTextures();
////		DEPTH1_TEXTURE = GL11.glGenTextures();
//
//		R_MCTB_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
//		R_MCTB_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
////		R_S_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////		R_S_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
//		R_T_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
//		R_T_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
//		R_G_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
//		R_G_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
////		R_TG_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////		R_TG_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//
////		R_M_FRAMEBUFFER_TEXTURE = GL11.glGenTextures();
////		R_M_RENDERBUFFER_TEXTURE = GL11.glGenTextures();
//	}
//
//	public static void init()
//	{
//		Minecraft minecraft = Minecraft.getMinecraft();
//		int display_width = minecraft.displayWidth,
//		display_height = minecraft.displayHeight;
//
//		if (display_width != DISPLAY_WIDTH || display_height != DISPLAY_HEIGHT)
//		{
//			GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//			int gl_texture_binding_2d = OPENGL_INTBUFFER.get(0);
//
//			DISPLAY_WIDTH = display_width;
//			DISPLAY_HEIGHT = display_height;
//
////			init(R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE, 4);
////			init(R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE, 4);
////			init(R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE, 4);
////			init(R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE, 4);
////			init(R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE, 4);
////			init(R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE, 4);
//			init(R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
////			init(R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
////			init(R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
//			init(R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
//			init(R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
////			init(R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE);
//
////			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			int gl_draw_framebuffer_binding = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, OPENGL_INTBUFFER);
////			int gl_read_framebuffer_binding = OPENGL_INTBUFFER.get(0);
////			GL11.glGetInteger(GL30.GL_RENDERBUFFER_BINDING, OPENGL_INTBUFFER);
////			int gl_renderbuffer_binding = OPENGL_INTBUFFER.get(0);
////
////			OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, MC_FRAMEBUFFER);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////			OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, MC_RENDERBUFFER);
////
////			OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, DISPLAY_WIDTH, DISPLAY_HEIGHT);
////			OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, MC_RENDERBUFFER);
////
////			OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, gl_renderbuffer_binding);
////			OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);
////			OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, gl_draw_framebuffer_binding);
//
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE);
//			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA16, DISPLAY_WIDTH, DISPLAY_HEIGHT, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (ByteBuffer)null);
//
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d);
//
////			if (PBO != -1)
////			{
////				OpenGlHelper.glDeleteBuffers(PBO);
////			}
//
////			PBO = OpenGlHelper.glGenBuffers();
////			PBO_BYTEBUFFER = ByteBuffer.allocateDirect(DISPLAY_WIDTH * DISPLAY_HEIGHT * 16 << 2).order(ByteOrder.nativeOrder());
////			OpenGlHelper.glBindBuffer(GL21.GL_PIXEL_PACK_BUFFER, PBO);
////			OpenGlHelper.glBufferData(GL21.GL_PIXEL_PACK_BUFFER, PBO_BYTEBUFFER, GL15.GL_STREAM_READ);
////			PBO_BYTEBUFFER.limit(DISPLAY_WIDTH * DISPLAY_HEIGHT/* * 4 * 4*//* * 16 * 4*/);
////			PBO_BYTEBUFFER.limit(1);
//		}
//	}
//
//	public static void run()
//	{
//		GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
//		GL_COLOR_ATTACHMENT0 = OPENGL_INTBUFFER.get(0);
//		GL30.glGetFramebufferAttachmentParameter(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL30.GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME, OPENGL_INTBUFFER);
//		GL_DEPTH_ATTACHMENT = OPENGL_INTBUFFER.get(0);
//
//		RenderO.takeDefault();
//
////		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//
////		clear(R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
////		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//
////		clear(R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
////		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//
////		!GL11.glViewport(0, 0, DISPLAY_WIDTH / 4, DISPLAY_HEIGHT / 4);
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
//		draw(FIRST_MODEL_MAP, false);
//		clear(R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
////		draw(GLOW_MODEL_MAP, false, true);
//		GL11.glEnable(GL11.GL_CULL_FACE);
//		clear(R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
//		draw(SECOND_MODEL_MAP, false);
//
//		DRAWWORLDDATA_LIST.clear();
//		DATA_SIZE = 0;
//		FIRST_MODEL_MAP.clear();
//		SECOND_MODEL_MAP.clear();
//
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
////		GL11.glDisable(GL11.GL_BLEND);
//
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//		int gl_texture_binding_2d_2 = OPENGL_INTBUFFER.get(0);
//		int gl_texture_wrap_s_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//		int gl_texture_wrap_t_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//		int gl_texture_min_filter_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//		int gl_texture_mag_filter_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////		int gl_texture_binding_2d_3 = OPENGL_INTBUFFER.get(0);
////		int gl_texture_wrap_s_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////		int gl_texture_wrap_t_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
////		int gl_texture_min_filter_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		int gl_texture_mag_filter_3 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
////
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE4);
////		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
////		int gl_texture_binding_2d_4 = OPENGL_INTBUFFER.get(0);
////		int gl_texture_wrap_s_4 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
////		int gl_texture_wrap_t_4 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
////		int gl_texture_min_filter_4 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
////		int gl_texture_mag_filter_4 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);
//
////		clear(GL_COLOR_ATTACHMENT0, GL_DEPTH_ATTACHMENT);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////		!GL11.glViewport(0, 0, DISPLAY_WIDTH, DISPLAY_HEIGHT);
////		draw2dLite(GL_DEPTH_ATTACHMENT, R_M_FRAMEBUFFER_TEXTURE, R_M_RENDERBUFFER_TEXTURE);
//
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE, 0);
////		draw2dDepth(GL_DEPTH_ATTACHMENT, R_M_RENDERBUFFER_TEXTURE);
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//
////		//!
////		draw2dBlur(GL_DEPTH_ATTACHMENT, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE/*, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE*/);
////		draw2dLite(GL_DEPTH_ATTACHMENT, R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
//
////		if (TRANSLUCENT)
////		{
//
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE, 0);
////			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);//clear depth to 0.0 / old color/depth
//////			draw2dDepth(GL_DEPTH_ATTACHMENT, GL_DEPTH_ATTACHMENT);//remap
//////			draw2dDepth(DEPTH_COLOR0_TEXTURE, R_G_RENDERBUFFER_TEXTURE);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//////!
////			draw2dMix(DEPTH_COLOR0_TEXTURE, R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE/*, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE*/);
////
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE, 0);
////			draw2dDepth(DEPTH_COLOR0_TEXTURE, R_T_RENDERBUFFER_TEXTURE);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//
////			boolean gl_blend = GL11.glIsEnabled(GL11.GL_BLEND);
////			GL11.glEnable(GL11.GL_BLEND);
////			draw2dLite(DEPTH_COLOR0_TEXTURE, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
//
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, DEPTH_COLOR0_TEXTURE, 0);
////			draw2dDepth(DEPTH_COLOR0_TEXTURE, DEPTH0_TEXTURE);
////			OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//
////			draw2dLite(DEPTH_COLOR0_TEXTURE, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);//DEPTH0_TEXTURE
////			draw2dLite(GL_DEPTH_ATTACHMENT, R_MCTB_FRAMEBUFFER_TEXTURE, R_MCTB_RENDERBUFFER_TEXTURE);
////			draw2dLite(R_MCTB_RENDERBUFFER_TEXTURE, R_MCTB_FRAMEBUFFER_TEXTURE, GL_DEPTH_ATTACHMENT);
////			if (gl_blend)
////			{
////				GL11.glEnable(GL11.GL_BLEND);
////			}
////			else
////			{
////				GL11.glDisable(GL11.GL_BLEND);
////			}
////		}
//
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, gl_texture_wrap_s_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, gl_texture_wrap_t_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_2);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_2);
//
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_3);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, gl_texture_wrap_s_3);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, gl_texture_wrap_t_3);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_3);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_3);
////
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE4);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_4);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, gl_texture_wrap_s_4);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, gl_texture_wrap_t_4);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, gl_texture_min_filter_4);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, gl_texture_mag_filter_4);
//
//		RenderO.setDefault();
//
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////		GL11.glClearColor(R, G, B, 0);
//	}
//
//	public static void draw(Map<byte[], List<Integer>> model_map/*, boolean transparent*/, boolean glow)
//	{
//		byte[][] keyset_byte_2d_array = model_map.keySet().toArray(new byte[0][]);
//		List<Integer>[] values_integer_list = model_map.values().toArray(new ArrayList[0]);
//
//		for (int g = 0; g < keyset_byte_2d_array.length; ++g)
//		{
//			byte[] byte_array = keyset_byte_2d_array[g];
//			List<Integer> index_integer_list = values_integer_list[g];
//			MemoG rg = G_LIST.get(ByteReader.getInt(byte_array, 0));
//			MemoS rs = S_LIST.get(ByteReader.getInt(byte_array, 4 + 4));
//
//			RenderO.enableBuffer(rg, rs);
//
////			boolean should_set_texture = false;
//			OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5], 1);
//			if ((byte_array[4 + 4 + 4] & 4) == 4)//color
//			{
//				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//				setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
//
//				int color = ByteReader.getInt(byte_array, 4);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.limit(3);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//				OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.put((color & 0xFF) / 255.0F);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[4], OPENGL_FIXED_PIPE_FLOATBUFFER);
//			}
//			else
//			{
//				setTexture(rg, rs, byte_array);
////				should_set_texture = true;
//			}
//
//			for (Integer integer : index_integer_list)
//			{
////				ObjectRender.enableBuffer(openglobjectmemory, openglobjectshadermemory);
////				int index = step_size + id_integer_list.get(i);
////				DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(data_id_integer_list.get(step_size + i));
//				DrawWorldData drawworlddata = DRAWWORLDDATA_LIST.get(integer);
//
////				boolean animation = drawworlddata.skinning_float_array != null;
////				if (animation)
////				{
////					OPENGL_FLOATBUFFER.limit(drawworlddata.skinning_float_array.length);
////				}
//
////				RenderO.setTransparent((byte_array[4 + 4 + 4] & 1) == 1);
////				if ((byte_array[4 + 4 + 4] & 1) == 1)
////				{
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_T_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_T_RENDERBUFFER);
////				}
//
////				//1
////				OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
////				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
////				OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer) Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
//
////				//1
////				OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
////				OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////				OpenGLBuffer.setTextureBuffer(BytesReader.getInt(byte_array, 4), (byte)(openglobjectmemory.state & 1));
//
//				OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
//				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.projection_m4x4_float, 16);
//				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
//				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.modelview_m4x4_float, 16);
//				OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
//				OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
//				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.color_v4_float, 4);
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[3], OPENGL_FIXED_PIPE_FLOATBUFFER);
//				put(OPENGL_FIXED_PIPE_FLOATBUFFER, drawworlddata.light0position_v4_float, 4);
//				OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);
////				boolean glow = (rg.state & 8) == 8;
////				boolean transparent = (byte_array[4 + 4 + 4] & 1) == 1;
////				if (transparent && glow)
////				{
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_TG_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_TG_RENDERBUFFER);
////				}
////				else if (transparent)
////				{
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_T_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_T_RENDERBUFFER);
////				}
////				else if (glow)
////				{
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_G_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_G_RENDERBUFFER);
////				}
////				if (transparent/* || glow*/)
////				{
//////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_S_FRAMEBUFFER);
//////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_S_RENDERBUFFER);
//////					clear(R_S_FRAMEBUFFER, R_S_RENDERBUFFER);
////					clear(R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
////				}
////				else
//				/*if (transparent)
//				{
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_T_FRAMEBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_T_RENDERBUFFER_TEXTURE, 0);
////					GL11.glViewport(0, 0, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 2);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_G_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_G_RENDERBUFFER);
//				}
//				else *//*if (!transparent && glow)
//				{
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_G_FRAMEBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_G_RENDERBUFFER_TEXTURE, 0);
////					GL11.glViewport(0, 0, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 2);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_G_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_G_RENDERBUFFER);
//				}*/
//
//				if (glow/* || (byte_array[4 + 4 + 4] & 1) == 1*/)
//				{
//					OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
//					OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//					OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
//					OPENGL_FIXED_PIPE_FLOATBUFFER.put(-1.0f);
//					OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
////					FloatBuffer floatbuffer = ByteBuffer.allocateDirect(2 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
////					floatbuffer.put(-1.0f);
////					floatbuffer.put(-1.0f);
////					floatbuffer.flip();
//					OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
////					OpenGlHelper.glUniform2(openglobjectshadermemory.uniformlocation_int_array[7], -1.0F, -1.0F);
//				}
//				else
//				{
//					OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
//					OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
//					OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_b);
//					OPENGL_FIXED_PIPE_FLOATBUFFER.put(drawworlddata.lig_s);
//					OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
////					FloatBuffer floatbuffer = ByteBuffer.allocateDirect(2 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
////					floatbuffer.put(drawworlddata.lig_b);
////					floatbuffer.put(drawworlddata.lig_s);
////					floatbuffer.flip();
//					OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
////					OpenGlHelper.glUniform2(openglobjectshadermemory.uniformlocation_int_array[7], drawworlddata.lig_b, drawworlddata.lig_s);
//				}
//
////				if (animation)
//				if (drawworlddata.skinning_float_array != null)
//				{
////					OpenGLAnimationMemory openglanimationmemory = (OpenGLAnimationMemory)dataloader.object_array[animation_id];
////					float[] skinning_float_array = SKINNING_MAP.get(i);
//					setFloatBuffer(drawworlddata.skinning_float_array);
//					OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[7], false, OPENGL_FLOATBUFFER);
////					OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[8], false, OPENGL_FLOATBUFFER);
//				}
//
////				clear(R_M_FRAMEBUFFER, R_M_RENDERBUFFER);
////				OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
////				OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
////				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//				GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
////				ObjectRender.disableBuffer(openglobjectshadermemory);
//
//				/*if (transparent && glow)
//				{
//					GL11.glDisable(GL11.GL_DEPTH_TEST);
//
//					RenderO.disableBuffer(rs);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_TG_FRAMEBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_TG_RENDERBUFFER_TEXTURE, 0);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_TG_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_TG_RENDERBUFFER);
//					draw2dMix(R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
//					RenderO.enableBufferBack(rg, rs);
//
//					if (should_set_texture)
//					{
//						setTexture(rg, rs, byte_array);
//					}
//
////					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_M_FRAMEBUFFER_TEXTURE, 0);
////					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_M_RENDERBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
//
//					GL11.glEnable(GL11.GL_DEPTH_TEST);
//				}
//				else if (transparent)
//				{
//					GL11.glDisable(GL11.GL_DEPTH_TEST);
//
//					RenderO.disableBuffer(rs);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_T_FRAMEBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_T_RENDERBUFFER_TEXTURE, 0);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_T_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_T_RENDERBUFFER);
//					draw2dMix(R_T_FRAMEBUFFER_TEXTURE, R_T_RENDERBUFFER_TEXTURE, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
//					RenderO.enableBufferBack(rg, rs);
//
//					if (should_set_texture)
//					{
//						setTexture(rg, rs, byte_array);
//					}
//
////					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_M_FRAMEBUFFER_TEXTURE, 0);
////					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_M_RENDERBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
//
//					GL11.glEnable(GL11.GL_DEPTH_TEST);
//				}
//				else *//*if (glow && !transparent*//* || transparent*//*)
//				{
////					GL11.glDisable(GL11.GL_DEPTH_TEST);
////
////					RenderO.disableBuffer(rs);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_G_FRAMEBUFFER);
////					draw2dMix(R_G_FRAMEBUFFER_TEXTURE, R_G_RENDERBUFFER_TEXTURE, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
////
////					//draw depth to texture
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_GD_FRAMEBUFFER);
////					draw2dDepth(R_GD_DEPTH_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
////
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_GDB_FRAMEBUFFER);
////					draw2dDepthBlur(R_GDB_DEPTH_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
////					//
////
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_TG_FRAMEBUFFER);
////					draw2dBlur(R_TG_FRAMEBUFFER_TEXTURE, R_TG_RENDERBUFFER_TEXTURE, R_S_FRAMEBUFFER_TEXTURE, R_S_RENDERBUFFER_TEXTURE);
////					RenderO.enableBufferBack(rg, rs);
////
////					if (should_set_texture)
////					{
////						setTexture(rg, rs, byte_array);
////					}
//
////					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, R_M_FRAMEBUFFER_TEXTURE, 0);
////					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, R_M_RENDERBUFFER_TEXTURE, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, GL_COLOR_ATTACHMENT0, 0);
//					OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, GL_DEPTH_ATTACHMENT, 0);
////					GL11.glViewport(0, 0, DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 2);
////					OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, R_M_FRAMEBUFFER);
//////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_M_RENDERBUFFER);
//////					OpenGlHelper.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, R_GL_READ_FRAMEBUFFER_BINDING);
//////					OpenGlHelper.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, R_GL_DRAW_FRAMEBUFFER_BINDING);
//////					OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, R_GL_RENDERBUFFER_BINDING);
////
////					GL11.glEnable(GL11.GL_DEPTH_TEST);
//				}*/
//			}
//
////			RenderO.disableBuffer(rs);
//			//				GL33.glVertexAttribDivisor(i, );
////				GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0, STEP_INTEGER_LIST.get());
//
////			step_size += step_integer_list.get(g);
//		}
//
//		//0
////		ObjectRender.setDefault();
//	}
//
//	public static void setTexture(MemoG rg, MemoS rs, byte[] byte_array)
//	{
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5], 1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		setTextureBuffer(ByteReader.getInt(byte_array, 4), (byte)(rg.state & 1));
//	}
//
//	public static void init(/*int framebuffer*//*, int renderbuffer*//*, */int framebuffer_texture, int renderbuffer_texture/*, int scale*/)
//	{
////		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//////		OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer);
//////		OpenGlHelper.glRenderbufferStorage(OpenGlHelper.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, DISPLAY_WIDTH, DISPLAY_HEIGHT);
//////		OpenGlHelper.glFramebufferRenderbuffer(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, OpenGlHelper.GL_RENDERBUFFER, renderbuffer);
//
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
//		//GL_RGBA8 as minecraft using 32856
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, DISPLAY_WIDTH/* / scale*/, DISPLAY_HEIGHT/* / scale*/, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderbuffer_texture);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_DEPTH_COMPONENT, DISPLAY_WIDTH/* / scale*/, DISPLAY_HEIGHT/* / scale*/, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer)null);
//
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, framebuffer_texture, 0);
////		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, renderbuffer_texture, 0);
//	}
//
//	public static void clear(int color_texture, int depth_texture/*int framebuffer*//*, int renderbuffer*/)
//	{
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, color_texture, 0);
//		OpenGlHelper.glFramebufferTexture2D(OpenGlHelper.GL_FRAMEBUFFER, OpenGlHelper.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depth_texture, 0);
////		OpenGlHelper.glBindFramebuffer(OpenGlHelper.GL_FRAMEBUFFER, framebuffer);
//////		OpenGlHelper.glBindRenderbuffer(OpenGlHelper.GL_RENDERBUFFER, renderbuffer);
//		GL11.glClear(/*GL11.GL_COLOR_BUFFER_BIT | */GL11.GL_DEPTH_BUFFER_BIT);
////		GL11.glClearColor(0, 0, 0, 0);
////		GL11.glClearColor(R, G, B, 0);
//	}
//
//	public static void draw2dMix(int depth_texture, int framebuffer_texture, int renderbuffer_texture/*, int mctb_framebuffer_texture, int mctb_renderbuffer_texture*/)
//	{
//		MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 1);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
//		GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth_texture);//s_renderbuffer_texture R_MC_RENDERBUFFER_TEXTURE
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 2);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderbuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
//
////		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[3], 3);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mctb_framebuffer_texture);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////
////		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 4);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE4);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mctb_renderbuffer_texture);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//
//	public static void draw2dBlur(int depth_texture, int framebuffer_texture, int renderbuffer_texture/*, int mctb_framebuffer_texture, int mctb_renderbuffer_texture*/)
//	{
//		MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 2);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
//		GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);
//
////		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, main_framebuffer_texture);//Minecraft.getMinecraft().getFramebuffer().framebufferTexture
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
//////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth_texture);//s_renderbuffer_texture R_MC_RENDERBUFFER_TEXTURE
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 2);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, renderbuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
//
////		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[3], 3);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE3);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mctb_framebuffer_texture);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
////
////		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 4);
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE4);
////		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mctb_renderbuffer_texture);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
////		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//
//	public static void draw2dLite(int depth_texture, int framebuffer_texture, int own_depth_texture)
//	{
//		MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 3);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
//		GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depth_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, framebuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[2], 2);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, own_depth_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//
//	public static void draw2dDepth(int main_renderbuffer_texture, int own_renderbuffer_texture)
//	{
//		MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 4);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
//		GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, main_renderbuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, own_renderbuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//
//	public static void draw2dDepthBlur(int main_renderbuffer_texture, int own_renderbuffer_texture)
//	{
//		MemoS rs = S_LIST.get(NaliData.SHADER_STEP + 5);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, FULL_ARRAY_BUFFER);
//		GL20.glVertexAttribPointer(v, 4, GL11.GL_FLOAT, false, 0, 0);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, main_renderbuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, own_renderbuffer_texture);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		GL20.glDisableVertexAttribArray(v);
//	}
//}
