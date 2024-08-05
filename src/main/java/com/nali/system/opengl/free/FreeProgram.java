//package com.nali.system.opengl.free;
//
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL20;
//
//import static com.nali.system.opengl.memo.client.MemoCurrent.R_GL_CURRENT_PROGRAM;
//import static com.nali.system.opengl.memo.client.MemoCurrent.OPENGL_INTBUFFER;
//
//@SideOnly(Side.CLIENT)
//public class FreeProgram extends Free
//{
//    @Override
//    public void start()
//    {
//        GL11.glGetInteger(GL20.R_GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
//        R_GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);
//    }
//
//    @Override
//    public void end()
//    {
//        OpenGlHelper.glUseProgram(R_GL_CURRENT_PROGRAM);
//    }
//}
