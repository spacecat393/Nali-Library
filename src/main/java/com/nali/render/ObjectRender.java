package com.nali.render;

import com.nali.data.BothData;
import com.nali.draw.ObjectScreenDraw;
import com.nali.draw.ObjectWorldDraw;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.system.DataLoader;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memory.OpenGLAttribMemory;
import com.nali.system.opengl.memory.OpenGLObjectMemory;
import com.nali.system.opengl.memory.OpenGLObjectShaderMemory;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.util.Arrays;

import static com.nali.system.opengl.memory.OpenGLCurrentMemory.*;

@SideOnly(Side.CLIENT)
public class ObjectRender
{
    public DataLoader dataloader;
    public Object[] memory_object_array;
    public BothData bothdata;

    public ObjectScreenDraw objectscreendraw;
    public ObjectWorldDraw objectworlddraw;

    public boolean should_render;
    public float r = 1.0F, g = 1.0F, b = 1.0F, a = 1.0F;
//    public float width, height;
    public float lig_b = 208.0F, lig_s = 240.0F;
    public float x, y, z = 50.0F;
    public float rx = -90.0F, ry, rz;
    public float sx = -25.0F, sy = -25.0F, sz = -25.0F;
    public float scale, body_rot, head_rot, net_head_yaw, head_pitch;
    public int[] texture_index_int_array;
    public boolean[] model_boolean_array;
    public boolean[] glow_boolean_array;

    public ObjectRender(BothData bothdata, DataLoader dataloader)
    {
        this.init(bothdata, dataloader);
    }

    public void init(BothData bothdata, DataLoader dataloader)
    {
        this.bothdata = bothdata;
        this.dataloader = dataloader;

        int max_part = bothdata.MaxPart();
        int step_models = bothdata.StepModels();

        this.texture_index_int_array = new int[max_part];

        this.memory_object_array = new Object[max_part];
        this.model_boolean_array = new boolean[max_part];
        this.glow_boolean_array = new boolean[max_part];

        for (int i = 0; i < max_part; ++i)
        {
            this.model_boolean_array[i] = false;
        }

        System.arraycopy(this.dataloader.memory_object_array, step_models, this.memory_object_array, 0, max_part);

        this.setGlow();
        Arrays.fill(this.model_boolean_array, true);

        this.objectscreendraw = this.getObjectScreenDraw();
        this.objectworlddraw = this.getObjectWorldDraw();
    }

    public void setBuffer(OpenGLObjectMemory openglobjectmemory)
    {
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, openglobjectmemory.element_array_buffer);
//        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)openglobjectmemory.index, GL15.GL_STATIC_DRAW);

        for (int i = 0; i < openglobjectshadermemory.attriblocation_int_array.length; ++i)
        {
            OpenGLAttribMemory openglattribmemory = openglobjectmemory.openglattribmemory_arraylist.get(i);
            OpenGLBuffer.setFloatBuffer(openglobjectshadermemory.attriblocation_int_array[i], openglattribmemory.buffer, openglattribmemory.size);
        }
    }

    public static void deleteBuffer(OpenGLObjectMemory openglobjectmemory)
    {
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;
        GL15.glDeleteBuffers(openglobjectmemory.element_array_buffer);

        for (int i = 0; i < openglobjectshadermemory.attriblocation_int_array.length; ++i)
        {
            OpenGLAttribMemory openglattribmemory = openglobjectmemory.openglattribmemory_arraylist.get(i);
            GL15.glDeleteBuffers(openglattribmemory.buffer);
        }
    }

    public void setModel()
    {
        Arrays.fill(this.model_boolean_array, true);
    }

    public void setGlow()
    {

    }

    public void multiplyAnimation()
    {

    }

    public void setUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
    {
        this.setFixedPipe(openglobjectshadermemory);
        this.setTextureUniform(openglobjectmemory, openglobjectshadermemory, index);
        this.setLightMapUniform(openglobjectshadermemory);
        this.setLightCoord(openglobjectshadermemory);
    }

    public void setFixedPipe(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL20.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL20.glUniformMatrix4(openglobjectshadermemory.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        GL20.glUniform4(openglobjectshadermemory.uniformlocation_int_array[3], OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_POSITION, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL20.glUniform4(openglobjectshadermemory.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);
    }

    public void setTextureUniform(OpenGLObjectMemory openglobjectmemory, OpenGLObjectShaderMemory openglobjectshadermemory, int index)
    {
        GL20.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[4], 0);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        OpenGLBuffer.setTextureBuffer((int)this.dataloader.opengltexturememory.texture_array[this.texture_index_int_array[index]], openglobjectmemory.texture_state);
    }

    public void setLightMapUniform(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        GL20.glUniform1i(openglobjectshadermemory.uniformlocation_int_array[5], 1);
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer) Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
    }

    public void setLightCoord(OpenGLObjectShaderMemory openglobjectshadermemory)
    {
        GL20.glUniform2f(openglobjectshadermemory.uniformlocation_int_array[6], this.lig_b, this.lig_s);
    }

    public void updateLightCoord()
    {

    }

    public ObjectScreenDraw getObjectScreenDraw()
    {
        return new ObjectScreenDraw(this);
    }

    public ObjectWorldDraw getObjectWorldDraw()
    {
        return new ObjectWorldDraw(this);
    }

    public void takeDefault(OpenGLObjectMemory openglobjectmemory)
    {
//        takeColor();

        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;

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

        this.setBuffer(openglobjectmemory);

        GL20.glUseProgram(openglobjectshadermemory.program);

        for (int i : openglobjectshadermemory.attriblocation_int_array)
        {
            GL20.glEnableVertexAttribArray(i);
        }

        GL_DEPTH_TEST = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        GL_CULL_FACE = GL11.glIsEnabled(GL11.GL_CULL_FACE);
        if (openglobjectmemory.culling == 0)
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
        else
        {
            GL11.glEnable(GL11.GL_CULL_FACE);
        }

        GL_BLEND = GL11.glIsEnabled(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_RGB, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL20.GL_BLEND_EQUATION_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_EQUATION_ALPHA = OPENGL_INTBUFFER.get(0);

        GL20.glBlendEquationSeparate(GL14.GL_FUNC_ADD, GL14.GL_FUNC_ADD);

        GL11.glGetInteger(GL14.GL_BLEND_SRC_RGB, OPENGL_INTBUFFER);
        GL_BLEND_SRC_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_SRC_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_SRC_ALPHA = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_RGB, OPENGL_INTBUFFER);
        GL_BLEND_DST_RGB = OPENGL_INTBUFFER.get(0);
        GL11.glGetInteger(GL14.GL_BLEND_DST_ALPHA, OPENGL_INTBUFFER);
        GL_BLEND_DST_ALPHA = OPENGL_INTBUFFER.get(0);
        GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
    }

    public void setDefault(OpenGLObjectMemory openglobjectmemory)
    {
        OpenGLObjectShaderMemory openglobjectshadermemory = (OpenGLObjectShaderMemory)openglobjectmemory.shader;

        for (int i : openglobjectshadermemory.attriblocation_int_array)
        {
            GL20.glDisableVertexAttribArray(i);
        }

        GL20.glUseProgram(GL_CURRENT_PROGRAM);
        GL13.glActiveTexture(GL_ACTIVE_TEXTURE);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, GL_ELEMENT_ARRAY_BUFFER_BINDING);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, GL_TEXTURE_BINDING_2D);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, GL_ARRAY_BUFFER_BINDING);

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

//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        GL11.glColor4f(RED, GREEN, BLUE, ALPHA);
    }
}
