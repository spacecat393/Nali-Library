package com.nali.render;

import com.nali.da.client.IClientDaO;
import com.nali.draw.DrawWorld;
import com.nali.draw.DrawWorldData;
import com.nali.mixin.IMixinEntityRenderer;
import com.nali.system.bytes.ByteWriter;
import com.nali.system.opengl.OpenGLBuffer;
import com.nali.system.opengl.memo.client.MemoAttrib;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import com.nali.system.opengl.memo.client.store.StoreO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import static com.nali.Nali.I;
import static com.nali.draw.DrawWorld.KEY_MAP;
import static com.nali.system.opengl.memo.client.MemoCurrent.*;

@SideOnly(Side.CLIENT)
public class RenderO<RG extends MemoGo, RS extends MemoSo, RST extends StoreO<RG, RS>, RC extends IClientDaO>
{
//    public DataLoader dataloader;
//    public Object[] memo_object_array;
//    public BothData bothdata;
    public RST rst;
    public RC rc;

//    public EntitiesRenderMemo entitiesrendermemo;
//    public ObjectScreenDraw objectscreendraw;
//    public ObjectWorldDraw objectworlddraw;

//    public int[] texture_index_int_array;
//    public byte[] model_byte_array;
//    public byte[] glow_byte_array;
    public float lig_b = -1.0F, lig_s = -1.0F;

    public RenderO(RST rst, RC rc)
    {
//        this.entitiesrendermemo = entitiesrendermemo;
//        this.bothdata = bothdata;
        this.rst = rst;
        this.rc = rc;
//        this.dataloader = dataloader;

//        int max_part = bothdata.MaxPart();
//        int max_part = clientdata.EndPart() - clientdata.StartPart();
//        int step_models = bothdata.StepModels();

//        this.texture_index_int_array = new int[max_part];

//        this.memo_object_array = MIX_MEMORY_OBJECT_ARRAY[i];
//        this.memo_object_array = new Object[max_part];
//        this.model_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];
//        this.glow_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];

//        System.arraycopy(this.dataloader.memo_object_array, step_models, this.memo_object_array, 0, max_part);

//        this.setGlow();
//        this.setModel();

//        this.objectscreendraw = this.getObjectScreenDraw();
//        this.objectworlddraw = this.getObjectWorldDraw();
    }

//    public ObjectRender(BothData bothdata, DataLoader dataloader/*, Object[] memo_object_array*/)
//    {
//        this.bothdata = bothdata;
//        this.dataloader = dataloader;
//
//        int max_part = bothdata.MaxPart();
//
////        this.texture_index_int_array = new int[max_part];
//
////        this.memo_object_array = memo_object_array;
//        this.model_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];
////        this.glow_byte_array = new byte[(int)Math.ceil(max_part / 8.0D)];
//
//        this.objectscreendraw = this.getObjectScreenDraw();
//        this.objectworlddraw = this.getObjectWorldDraw();
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

    public void setFixedPipe(RS rs)
    {
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(16);
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetFloat(GL11.GL_CURRENT_COLOR, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(4);
        OpenGlHelper.glUniform4(rs.uniformlocation_int_array[3], OPENGL_FIXED_PIPE_FLOATBUFFER);
        GL11.glGetLight(GL11.GL_LIGHT0, GL11.GL_POSITION, OPENGL_FIXED_PIPE_FLOATBUFFER);
        OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], OPENGL_FIXED_PIPE_FLOATBUFFER);
    }

    public void setTextureUniform(RG rg, RS rs/*, int index*/)
    {
        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[4], 0);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//        OpenGLBuffer.setTextureBuffer((int)this.dataloader.opengltexturememo.texture_array[this.texture_index_int_array[index]], (byte)(openglobjectmemo.state & 1));
//        OpenGLBuffer.setTextureBuffer(OPENGLTEXTUREMEMORY_LIST.get(this.getTextureID(openglobjectmemo)).texture_buffer, (byte)(openglobjectmemo.state & 1));
        OpenGLBuffer.setTextureBuffer(this.getTextureBuffer(rg), (byte)(rg.state & 1));
//        OpenGLBuffer.setTextureBuffer(this.getTextureID(openglobjectmemo), (byte)(openglobjectmemo.state & 1));
//        OpenGLBuffer.setTextureBuffer(this.clientdata.Texture(), (byte)(openglobjectmemo.state & 1));
    }

//    public void setFrameBufferUniform(OpenGLObjectMemo openglobjectmemo, OpenGLObjectShaderMemo openglobjectshadermemo/*, int index*/)
//    {
//        OpenGlHelper.glUniform1i(openglobjectshadermemo.uniformlocation_int_array[5], 1);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//        OpenGLBuffer.setTextureBuffer(Minecraft.getMinecraft().getFramebuffer().framebufferTexture, (byte)0);
////        OpenGLBuffer.setTextureBuffer(this.getTextureBuffer(openglobjectmemo), (byte)(openglobjectmemo.state & 1));
//    }

    public void setLightMapUniform(RS rs)
    {
        OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[5], 1);
//        OpenGlHelper.glUniform1i(openglobjectshadermemo.uniformlocation_int_array[6], 2);
        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//        OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE2);
        OpenGLBuffer.setLightMapBuffer(((IMixinEntityRenderer)Minecraft.getMinecraft().entityRenderer).lightmapTexture().getGlTextureId());
    }

    public void setLightCoord(RS rs)
    {
        OPENGL_FIXED_PIPE_FLOATBUFFER.limit(2);
        OPENGL_FIXED_PIPE_FLOATBUFFER.clear();
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(this.lig_b);
        OPENGL_FIXED_PIPE_FLOATBUFFER.put(this.lig_s);
        OPENGL_FIXED_PIPE_FLOATBUFFER.flip();
        OpenGlHelper.glUniform2(rs.uniformlocation_int_array[6], OPENGL_FIXED_PIPE_FLOATBUFFER);
//        OpenGlHelper.glUniform2(openglobjectshadermemo.uniformlocation_int_array[7], this.lig_b, this.lig_s);
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

//    public OpenGLObjectMemo getMemo(int i)
//    {
//        return (OpenGLObjectMemo)this.memo_object_array[i];
//    }

//    public int getMaxMemo()
//    {
//        return this.memo_object_array.length;
//    }

    public int getTextureID(RG rg)
    {
//        return (int)this.dataloader.opengltexturememo.texture_array[openglobjectmemo.texture_id];
//        return OPENGLTEXTUREMEMORY_LIST.get(openglobjectmemo.texture_id).texture_buffer;
//        return OPENGLTEXTUREMEMORY_LIST.get(openglobjectmemo.texture_id).texture_buffer;
        return rg.texture_id;
    }

    public int getTextureBuffer(RG rg)
    {
        return I.clientloader.opengltexturememo_list.get(getTextureID(rg)).texture_buffer;
    }

    public int getShaderID(RG rg)
    {
        return rg.shader_id;
    }

    public boolean getGlow(RG rg)
    {
        return (rg.state & 8) == 8;
    }

    public boolean getTransparent(RG rg)
    {
        return (rg.state & 4) == 4;
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
        for (int i = this.rc.StartPart(); i < this.rc.EndPart(); ++i)
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
//        OpenGLObjectMemo openglobjectmemo = (OpenGLObjectMemo)this.dataloader.object_array[index];
//        OpenGLObjectMemo openglobjectmemo = (OpenGLObjectMemo)I.clientloader.object_list.get(index);
        RG rg = this.rst.rg_list.get(index);
////        this.updateLight(openglobjectmemo);
//        byte[] byte_array = new byte[4 + 4 + 4 + 1/* + 4*//* + 4*/];
//        ByteWriter.set(byte_array, index, 0);
////        BytesWriter.set(byte_array, OPENGLTEXTUREMEMORY_LIST.get(this.getTextureID(openglobjectmemo)).texture_buffer, 4);
//        ByteWriter.set(byte_array, this.getTextureBuffer(rg), 4);
////        BytesWriter.set(byte_array, this.getTextureID(openglobjectmemo), 4);
////        BytesWriter.set(byte_array, this.clientdata.Texture(), 4);
//        ByteWriter.set(byte_array, this.getShaderID(rg), 4 + 4);
////        BytesWriter.set(byte_array, this.clientdata.Shader(), 4 + 4);
////        BytesWriter.set(byte_array, this.lig_b, 4 + 4 + 4);
////        BytesWriter.set(byte_array, this.lig_s, 4 + 4 + 4 + 4);
////        BytesWriter.set(byte_array, ((SkinningClientData)this.clientdata).AnimationID(), 4 + 4 + 4 + 4 + 4);
//        byte_array[4 + 4 + 4] = (byte)(this.getTransparent(rg) ? 1 : 0);
//        byte_array[4 + 4 + 4] += this.getExtraBit(rg);
////        BytesWriter.set(byte_array, this.dataloader.index, 4 + 4 + 4 + 1);
////        DrawWorld.add(new ByteArray(byte_array));
        DrawWorld.add(KEY_MAP.computeIfAbsent(index + " " + this.getTextureBuffer(rg) + " " + this.getShaderID(rg) + " " + (byte)((this.getTransparent(rg) ? 1 : 0) + this.getExtraBit(rg)), k -> this.createByteArray(index)));
    }

    public byte[] createByteArray(int index)
    {
        RG rg = this.rst.rg_list.get(index);
        byte[] byte_array = new byte[4 + 4 + 4 + 1];
        ByteWriter.set(byte_array, index, 0);
        ByteWriter.set(byte_array, this.getTextureBuffer(rg), 4);
        ByteWriter.set(byte_array, this.getShaderID(rg), 4 + 4);
        byte_array[4 + 4 + 4] = (byte)(this.getTransparent(rg) ? 1 : 0);
        byte_array[4 + 4 + 4] += this.getExtraBit(rg);
        return byte_array;
    }

    public void draw()
    {
        for (int i = this.rc.StartPart(); i < this.rc.EndPart(); ++i)
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
//        OpenGLObjectMemo openglobjectmemo = this.dataloader.openglobjectmemo_array[index];
        RG rg = this.rst.rg_list.get(index);
//        OpenGLObjectShaderMemo openglobjectshadermemo = this.dataloader.openglobjectshadermemo_array[openglobjectmemo.shader_id];
        RS rs = this.rst.rs_list.get(this.getShaderID(rg));
//        OpenGLObjectShaderMemo openglobjectshadermemo = I.clientloader.openglobjectshadermemo_list.get(this.clientdata.Shader());
        this.updateLight(rg);
        takeDefault();
        enableBuffer(rg, rs);
        setTransparent(this.getTransparent(rg));

//        if ((this.objectrender.glow_byte_array[index / 8] >> index % 8 & 1) == 1)
//        else
//        {
//            this.updateLightCoord();
//        }

        this.setUniform(rg, rs, index);

        GL11.glDrawElements(GL11.GL_TRIANGLES, rg.index_length, GL11.GL_UNSIGNED_INT, 0);
//        DRAW_CONSUMER.accept(openglobjectmemo);

//        OpenGlHelper.glPopAttrib();
        disableBuffer(rs);
        setDefault();
        this.lig_b = lig_b;
        this.lig_s = lig_s;
    }

    public void updateLight(RG rg)
    {
        if (this.getGlow(rg))
        {
            this.lig_b = -1.0F;
            this.lig_s = -1.0F;
        }
    }

    public void setUniform(RG rg, RS rs, int index)
    {
        this.setFixedPipe(rs);
        this.setTextureUniform(rg, rs);
//        this.setFrameBufferUniform(openglobjectmemo, openglobjectshadermemo);
        if (this.lig_b != -1.0F)
        {
            this.setLightMapUniform(rs);
        }
        this.setLightCoord(rs);
    }

    public byte getExtraBit(RG rg)
    {
        return 0;
    }

    public void updateLightCoord(World world, BlockPos blockpos)
    {
        if (world.isBlockLoaded(blockpos))
        {
            int brightness = world.getCombinedLight(blockpos, 0);
            this.lig_b = (brightness % 65536) / 255.0F;
            this.lig_s = (brightness / 65536.0F) / 255.0F;
        }

        if (this.lig_b < 0.1875F)
        {
            this.lig_b = 0.1875F;
        }
    }

    public static void takeDefault(/*OpenGLObjectMemo openglobjectmemo, OpenGLObjectShaderMemo openglobjectshadermemo*/)
    {
//        takeColor();

//        OpenGLObjectShaderMemo openglobjectshadermemo = (OpenGLObjectShaderMemo)openglobjectmemo.shader;

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

//        GL_TEXTURE_WRAP_S = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S);
//        GL_TEXTURE_WRAP_T = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T);
//        GL_TEXTURE_MIN_FILTER = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER);
//        GL_TEXTURE_MAG_FILTER = GL11.glGetTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER);

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

//        setBuffer(openglobjectmemo, openglobjectshadermemo);

//        OpenGlHelper.glUseProgram(openglobjectshadermemo.program);
//
//        for (int i : openglobjectshadermemo.attriblocation_int_array)
//        {
//            GL20.glEnableVertexAttribArray(i);
//        }

        GL_DEPTH_TEST = GL11.glIsEnabled(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

//        GL11.glGetInteger(GL11.GL_DEPTH_FUNC, OPENGL_INTBUFFER);
//        GL_DEPTH_FUNC = OPENGL_INTBUFFER.get(0);

//        GL11.glGetInteger(GL11.GL_FRONT_FACE, OPENGL_INTBUFFER);
//        GL_FRONT_FACE = OPENGL_INTBUFFER.get(0);
//        if (this.getTransparent(openglobjectmemo))
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
//        if ((openglobjectmemo.state & 2) == 2)
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

    public static void setDefault(/*OpenGLObjectMemo openglobjectmemo, OpenGLObjectShaderMemo openglobjectshadermemo*/)
    {
//        OpenGLObjectShaderMemo openglobjectshadermemo = (OpenGLObjectShaderMemo)openglobjectmemo.shader;

//        for (int i : openglobjectshadermemo.attriblocation_int_array)
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

//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL_TEXTURE_WRAP_S);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL_TEXTURE_WRAP_T);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL_TEXTURE_MIN_FILTER);
//        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL_TEXTURE_MAG_FILTER);

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

    public static void disableBuffer(MemoSo memoso)
    {
        for (int i : memoso.attriblocation_int_array)
        {
            GL20.glDisableVertexAttribArray(i);
        }
    }

    public static void enableBuffer(MemoGo memogo, MemoSo memoso)
    {
//        OpenGLObjectShaderMemo openglobjectshadermemo = (OpenGLObjectShaderMemo)openglobjectmemo.shader;
        OpenGlHelper.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, memogo.element_array_buffer);
//        OpenGlHelper.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, (IntBuffer)openglobjectmemo.index, OpenGlHelper.GL_STATIC_DRAW);

        for (int i = 0; i < memoso.attriblocation_int_array.length; ++i)
        {
            MemoAttrib openglattribmemo = memogo.openglattribmemo_arraylist.get(i);
            OpenGLBuffer.setFloatBuffer(memoso.attriblocation_int_array[i], openglattribmemo.buffer, openglattribmemo.size);
        }

        int program = memoso.program;
//        if (MY_CURRENT_PROGRAM != program)
//        {
        OpenGlHelper.glUseProgram(program);
//        }
//        MY_CURRENT_PROGRAM = program;

        for (int i : memoso.attriblocation_int_array)
        {
            GL20.glEnableVertexAttribArray(i);
        }

        if ((memogo.state & 2) == 2)
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
        //        if (this.getTransparent(openglobjectmemo))
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

//    public static void deleteBuffer(OpenGLObjectMemo openglobjectmemo, OpenGLObjectShaderMemo openglobjectshadermemo)
//    {
////        OpenGLObjectShaderMemo openglobjectshadermemo = (OpenGLObjectShaderMemo)openglobjectmemo.shader;
//        OpenGlHelper.glDeleteBuffers(openglobjectmemo.element_array_buffer);
//
//        for (int i = 0; i < openglobjectshadermemo.attriblocation_int_array.length; ++i)
//        {
//            OpenGLAttribMemo openglattribmemo = openglobjectmemo.openglattribmemo_arraylist.get(i);
//            OpenGlHelper.glDeleteBuffers(openglattribmemo.buffer);
//        }
//    }
}
