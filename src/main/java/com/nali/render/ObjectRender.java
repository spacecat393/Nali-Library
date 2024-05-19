package com.nali.render;

import com.nali.data.client.ClientData;
import com.nali.draw.DrawWorld;
import com.nali.draw.DrawWorldData;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.system.bytes.ByteArray;
import com.nali.system.bytes.BytesWriter;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memory.OpenGLAttribMemory;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import static com.nali.system.ClientLoader.*;
import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
public class ObjectRender
{
//    public DataLoader dataloader;
//    public Object[] memory_object_array;
//    public BothData bothdata;
    public ClientData clientdata;

    public EntitiesRenderMemory entitiesrendermemory;
//    public ObjectScreenDraw objectscreendraw;
//    public ObjectWorldDraw objectworlddraw;

//    public int[] texture_index_int_array;
//    public byte[] model_byte_array;
//    public byte[] glow_byte_array;
    public float lig_b = -1.0F, lig_s = -1.0F;

    public ObjectRender(EntitiesRenderMemory entitiesrendermemory, ClientData clientdata/*, DataLoader dataloader*//*, int i*/)
    {
        this.entitiesrendermemory = entitiesrendermemory;
//        this.bothdata = bothdata;
        this.clientdata = clientdata;
//        this.dataloader = dataloader;

//        int max_part = bothdata.MaxPart();
//        int max_part = clientdata.EndPart() - clientdata.StartPart();
//        int step_models = bothdata.StepModels();

//        this.texture_index_int_array = new int[max_part];

//        this.memory_object_array = MIX_MEMORY_OBJECT_ARRAY[i];
//        this.memory_object_array = new Object[max_part];
//        this.model_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];
//        this.glow_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];

//        System.arraycopy(this.dataloader.memory_object_array, step_models, this.memory_object_array, 0, max_part);

//        this.setGlow();
//        this.setModel();

//        this.objectscreendraw = this.getObjectScreenDraw();
//        this.objectworlddraw = this.getObjectWorldDraw();
    }

//    public ObjectRender(BothData bothdata, DataLoader dataloader/*, Object[] memory_object_array*/)
//    {
//        this.bothdata = bothdata;
//        this.dataloader = dataloader;
//
//        int max_part = bothdata.MaxPart();
//
////        this.texture_index_int_array = new int[max_part];
//
////        this.memory_object_array = memory_object_array;
//        this.model_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];
////        this.glow_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];
//
//        this.objectscreendraw = this.getObjectScreenDraw();
//        this.objectworlddraw = this.getObjectWorldDraw();
//    }

    public static void enableBuffer(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory)
    {
//        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
        OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, openglobjectmemory.element_array_buffer);
//        OpenGlHelper.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)openglobjectmemory.index, OpenGlHelper.GL_STATIC_DRAW);

        for (int i = 0; i < openglobjectshadermemory.attriblocation_int_array.length; ++i)
        {
            OpenGLAttribMemory openglattribmemory = openglobjectmemory.openglattribmemory_arraylist.get(i);
            OpenGLBuffer.setFloatBuffer(openglobjectshadermemory.attriblocation_int_array[i], openglattribmemory.buffer, openglattribmemory.size);
        }

        int program = openglobjectshadermemory.program;
//        if (MY_CURRENT_PROGRAM != program)
//        {
        OpenGlHelper.glUseProgram(program);
//        }
//        MY_CURRENT_PROGRAM = program;

        for (int i : openglobjectshadermemory.attriblocation_int_array)
        {
            GL20.glEnableVertexAttribArray(i);
        }

        if ((openglobjectmemory.state & 2) == 2)
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
        else
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
    }

    public static void setTransparent(boolean transparent)
    {
        //        if (this.getTransparent(openglobjectmemory))
        GL11.glDepthMask(!transparent);
//        if (transparent)
//        {
//            GL11.glDepthMask(false);
////            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
//
////            GL11.glEnable(GL11.GL_BLEND);
////
////            GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);
////
////            GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
////        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);//glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
//        }
//        else
//        {
////            GL11.glDisable(GL11.GL_BLEND);
//            GL11.glDepthMask(true);
//        }
    }

//    public static void deleteBuffer(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory)
//    {
////        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
//        OpenGlHelper.glDeleteBuffers(openglobjectmemory.element_array_buffer);
//
//        for (int i = 0; i < openglobjectshadermemory.attriblocation_int_array.length; ++i)
//        {
//            OpenGLAttribMemory openglattribmemory = openglobjectmemory.openglattribmemory_arraylist.get(i);
//            OpenGlHelper.glDeleteBuffers(openglattribmemory.buffer);
//        }
//    }

//    public void setModel()
//    {
//        Arrays.fill(this.model_byte_array, (byte)255);
//    }
//
//    public void setGlow()
//    {
//
//    }

//    public void multiplyAnimation()
//    {
//
//    }

    public void setFixedPipe(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[3], OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_POSITION, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniform4(openglobjectshadermemory.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);
    }

    public void setTextureUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory/*, int index*/)
    {
        OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//        OpenGLBuffer.setTextureBuffer((int)this.dataloader.opengltexturememory.texture_array[this.texture_index_int_array[index]], (byte)(openglobjectmemory.state & 1));
        OpenGLBuffer.setTextureBuffer(OPENGLTEXTUREMEMORY_LIST.get(this.getTextureID(openglobjectmemory)).texture_buffer, (byte)(openglobjectmemory.state & 1));
//        OpenGLBuffer.setTextureBuffer(this.clientdata.Texture(), (byte)(openglobjectmemory.state & 1));
    }

//    public void setFrameBufferUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory/*, int index*/)
//    {
//        OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//        OpenGLBuffer.setTextureBuffer(Minecraft.getMinecraft().getFramebuffer().framebufferTexture, (byte)0);
////        OpenGLBuffer.setTextureBuffer(this.getTextureBuffer(openglobjectmemory), (byte)(openglobjectmemory.state & 1));
//    }

    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
//        OpenGlHelper.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[6], 2);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
        OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
    }

    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(this.lig_b);
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(this.lig_s);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniform2(openglobjectshadermemory.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
//        OpenGlHelper.glUniform2(openglobjectshadermemory.uniformlocation_int_array[7], this.lig_b, this.lig_s);
    }

//    public void updateLightCoord()
//    {
//
//    }

//    public ObjectScreenDraw getObjectScreenDraw()
//    {
//        return new ObjectScreenDraw(this);
//    }
//
//    public ObjectWorldDraw getObjectWorldDraw()
//    {
//        return new ObjectWorldDraw(this);
//    }

    public static void takeDefault(/*OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory*/)
    {
//        takeColor();

//        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;

        GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, OPENGL_INTBUFFER);
        GL_CURRENT_PROGRAM = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ELEMENT_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, OPENGL_INTBUFFER);
        GL_ARRAY_BUFFER_BINDING = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, OPENGL_INTBUFFER);
        GL_ACTIVE_TEXTURE = OPENGL_INTBUFFER.get(0);

        GL_TEXTURE_WRAP_S = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
        GL_TEXTURE_WRAP_T = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
        GL_TEXTURE_MIN_FILTER = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_0 = OPENGL_INTBUFFER.get(0);
        GL_TEXTURE_WRAP_S_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
        GL_TEXTURE_WRAP_T_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
        GL_TEXTURE_MIN_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER_0 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
        GL_TEXTURE_BINDING_2D_1 = OPENGL_INTBUFFER.get(0);
        GL_TEXTURE_WRAP_S_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
        GL_TEXTURE_WRAP_T_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
        GL_TEXTURE_MIN_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
        GL_TEXTURE_MAG_FILTER_1 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//        GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, OPENGL_INTBUFFER);
//        GL_TEXTURE_BINDING_2D_2 = OPENGL_INTBUFFER.get(0);
//        GL_TEXTURE_WRAP_S_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//        GL_TEXTURE_WRAP_T_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//        GL_TEXTURE_MIN_FILTER_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER_2 = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

//        setBuffer(openglobjectmemory, openglobjectshadermemory);

//        OpenGlHelper.glUseProgram(openglobjectshadermemory.program);
//
//        for (int i : openglobjectshadermemory.attriblocation_int_array)
//        {
//            GL20.glEnableVertexAttribArray(i);
//        }

        GL_DEPTH_TEST = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

//        GL11.glGetInteger(GL11.GL_DEPTH_FUNC, OPENGL_INTBUFFER);
//        GL_DEPTH_FUNC = OPENGL_INTBUFFER.get(0);

//        GL11.glGetInteger(GL11.GL_FRONT_FACE, OPENGL_INTBUFFER);
//        GL_FRONT_FACE = OPENGL_INTBUFFER.get(0);
//        if (this.getTransparent(openglobjectmemory))
//        {
//            GL11.glDepthMask(false);
////            GL11.glDepthFunc(GL11.GL_ALWAYS);
////            GL11.glFrontFace(GL11.GL_CCW);
//        }
//        else
//        {
////            GL11.glDepthMask(true);
//        }

        GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
//        if ((openglobjectmemory.state & 2) == 2)
//        {
//            GL11.glEnable(GL11.GL_CULL_FACE);
//        }
//        else
//        {
//            GL11.glDisable(GL11.GL_CULL_FACE);
//        }

        GL_BLEND = GL11.glIsEnabled(GL11.GL_BLEND);

        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);


        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
        GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
        GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);


        GL11.glGetInteger(GL11.GL_DEPTH_WRITEMASK, OPENGL_INTBUFFER);
        GL_DEPTH_WRITEMASK = OPENGL_INTBUFFER.get(0);

        GL11.glEnable(GL11.GL_BLEND);

        GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
    }

    public static void setDefault(/*OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory*/)
    {
//        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;

//        for (int i : openglobjectshadermemory.attriblocation_int_array)
//        {
//            GL20.glDisableVertexAttribArray(i);
//        }

        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_0);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_0);

        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_1);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_1);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_1);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_1);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_1);

//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D_2);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S_2);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T_2);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER_2);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER_2);

        OpenGlHelper.glUseProgram(GL_CURRENT_PROGRAM);
        OpenGlHelper.setActiveTexture(GL_ACTIVE_TEXTURE);
        OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, GL_ELEMENT_ARRAY_BUFFER_BINDING);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
        OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER);

        if (GL_DEPTH_TEST)
        {
            GL11.glEnable(GL11.GL_DEPTH_TEST);
        }
        else
        {
            GL11.glDisable(GL11.GL_DEPTH_TEST);
        }

//        GL11.glDepthFunc(GL_DEPTH_FUNC);
        if (GL_DEPTH_WRITEMASK == 1)
        {
            GL11.glDepthMask(true);
        }
        else
        {
            GL11.glDepthMask(false);
        }

//        GL11.glFrontFace(GL_FRONT_FACE);

        if (GL_CULL_FACE)
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
        else
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }

        if (GL_BLEND)
        {
            GL11.glEnable(GL11.GL_BLEND);
        }
        else
        {
            GL11.glDisable(GL11.GL_BLEND);
        }

        GL20.glBlendEquationSeparate(GL_BLEND_EQUATION_RGB, GL_BLEND_EQUATION_ALPHA);
        GL14.glBlendFuncSeparate(GL_BLEND_SRC_RGB, GL_BLEND_DST_RGB, GL_BLEND_SRC_ALPHA, GL_BLEND_DST_ALPHA);

//        OpenGlHelper.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        OpenGlHelper.glColor4f(RED, GREEN, BLUE, ALPHA);

//        MY_CURRENT_PROGRAM = -1;
    }

    public static void disableBuffer(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        for (int i : openglobjectshadermemory.attriblocation_int_array)
        {
            GL20.glDisableVertexAttribArray(i);
        }
    }

//    public OpenGLObjectMemory getMemory(int i)
//    {
//        return (OpenGLObjectMemory)this.memory_object_array[i];
//    }

//    public int getMaxMemory()
//    {
//        return this.memory_object_array.length;
//    }

    public int getTextureID(OpenGLObjectMemory openglobjectmemory)
    {
//        return (int)this.dataloader.opengltexturememory.texture_array[openglobjectmemory.texture_id];
        return openglobjectmemory.texture_id;
    }

    public int getShaderID(OpenGLObjectMemory openglobjectmemory)
    {
        return openglobjectmemory.shader_id;
    }

    public boolean getGlow(OpenGLObjectMemory openglobjectmemory)
    {
        return (openglobjectmemory.state & 8) == 8;
    }

    public boolean getTransparent(OpenGLObjectMemory openglobjectmemory)
    {
        return (openglobjectmemory.state & 4) == 4;
    }

    public void drawLater()
    {
//        float lig_b = this.lig_b;
//        float lig_s = this.lig_s;

        DrawWorldData drawworlddata = new DrawWorldData();
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        DrawWorld.add(drawworlddata.projection_m4x4_float, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        DrawWorld.add(drawworlddata.modelview_m4x4_float, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        DrawWorld.add(drawworlddata.color_v4_float, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_POSITION, OPENGL_FIXED_PIPE_FLOATBUFFER);
        DrawWorld.add(drawworlddata.light0position_v4_float, OPENGL_FIXED_PIPE_FLOATBUFFER);
        drawworlddata.lig_b = lig_b;
        drawworlddata.lig_s = lig_s;
        this.updateDataLater(drawworlddata);
        for (int i = this.clientdata.StartPart(); i < this.clientdata.EndPart(); ++i)
        {
            this.drawLater(i);
        }

        DrawWorld.DRAWWORLDDATA_LIST.add(drawworlddata);
        ++DrawWorld.DATA_SIZE;

//        this.lig_b = lig_b;
//        this.lig_s = lig_s;
    }

    public void updateDataLater(DrawWorldData drawworlddata)
    {
    }

    public void drawLater(int index)
    {
//        int model_id = index;
//        OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)this.dataloader.object_array[index];
        OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)OBJECT_LIST.get(index);
//        this.updateLight(openglobjectmemory);
        byte[] byte_array = new byte[4 + 4 + 4 + 1/* + 4*//* + 4*/];
        BytesWriter.set(byte_array, index, 0);
        BytesWriter.set(byte_array, OPENGLTEXTUREMEMORY_LIST.get(this.getTextureID(openglobjectmemory)).texture_buffer, 4);
//        BytesWriter.set(byte_array, this.clientdata.Texture(), 4);
        BytesWriter.set(byte_array, this.getShaderID(openglobjectmemory), 4 + 4);
//        BytesWriter.set(byte_array, this.clientdata.Shader(), 4 + 4);
//        BytesWriter.set(byte_array, this.lig_b, 4 + 4 + 4);
//        BytesWriter.set(byte_array, this.lig_s, 4 + 4 + 4 + 4);
//        BytesWriter.set(byte_array, ((SkinningClientData)this.clientdata).AnimationID(), 4 + 4 + 4 + 4 + 4);
        byte_array[4 + 4 + 4] = (byte)(this.getTransparent(openglobjectmemory) ? 1 : 0);
        byte_array[4 + 4 + 4] += this.getExtraBit(openglobjectmemory);
//        BytesWriter.set(byte_array, this.dataloader.index, 4 + 4 + 4 + 1);
        DrawWorld.add(new ByteArray(byte_array));
    }

    public void draw()
    {
        for (int i = this.clientdata.StartPart(); i < this.clientdata.EndPart(); ++i)
        {
//            if ((this.model_byte_array[i / 8] >> i % 8 & 1) == 1)
//            {
            this.draw(i);
//            }
        }
    }

    public void draw(int index)
    {
        float lig_b = this.lig_b;
        float lig_s = this.lig_s;
//        OpenGLObjectMemory openglobjectmemory = this.dataloader.openglobjectmemory_array[index];
        OpenGLObjectMemory openglobjectmemory = (OpenGLObjectMemory)OBJECT_LIST.get(index);
//        OpenGLObjectShaderMemory openglobjectshadermemory = this.dataloader.openglobjectshadermemory_array[openglobjectmemory.shader_id];
        OpenGLObjectShaderMemory openglobjectshadermemory = OPENGLOBJECTSHADERMEMORY_LIST.get(this.getShaderID(openglobjectmemory));
//        OpenGLObjectShaderMemory openglobjectshadermemory = OPENGLOBJECTSHADERMEMORY_LIST.get(this.clientdata.Shader());
        this.updateLight(openglobjectmemory);
        takeDefault();
        enableBuffer(openglobjectmemory, openglobjectshadermemory);
        setTransparent(this.getTransparent(openglobjectmemory));

//        if ((this.objectrender.glow_byte_array[index / 8] >> index % 8 & 1) == 1)
//        else
//        {
//            this.updateLightCoord();
//        }

        this.setUniform(openglobjectmemory, openglobjectshadermemory, index);

        GL11.glDrawElements(GL11.GL_TRIANGLES, openglobjectmemory.index_length, GL11.GL_UNSIGNED_INT, 0);
//        DRAW_CONSUMER.accept(openglobjectmemory);

//        OpenGlHelper.glPopAttrib();
        disableBuffer(openglobjectshadermemory);
        setDefault();
        this.lig_b = lig_b;
        this.lig_s = lig_s;
    }

    public void updateLight(OpenGLObjectMemory openglobjectmemory)
    {
        if (this.getGlow(openglobjectmemory))
        {
            this.lig_b = -1.0F;
            this.lig_s = -1.0F;
        }
    }

    public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
    {
        this.setFixedPipe(openglobjectshadermemory);
        this.setTextureUniform(openglobjectmemory, openglobjectshadermemory);
//        this.setFrameBufferUniform(openglobjectmemory, openglobjectshadermemory);
        if (this.lig_b != -1.0F)
        {
            this.setLightMapUniform(openglobjectshadermemory);
        }
        this.setLightCoord(openglobjectshadermemory);
    }

    public byte getExtraBit(OpenGLObjectMemory openglobjectmemory)
    {
        return 0;
    }
}
