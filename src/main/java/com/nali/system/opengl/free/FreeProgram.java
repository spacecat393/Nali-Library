//package com.nali.system.opengl.free;
//
//import net.minecraft.client.renderer.OpenGlHelper;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//import org.lwjgl.opengl.GL11;
//import org.lwjgl.opengl.GL20;
//
//import static com.nali.system.opengl.memo.client.MemoCurrent.GL_CURRENT_PROGRAM;
//import static com.nali.system.opengl.memo.client.MemoCurrent.OPENGL_INTBUFFER;
//
//@SideOnly(Side.CLIENT)
//public class FreeProgram extends Free
//{
//    @Override
//    public void start()
//    {
//        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
//        GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);
//    }
//
//    @Override
//    public void end()
//    {
//        OpenGlHelper.glUseProgram(GL_CURRENT_PROGRAM);
//    }
//}
