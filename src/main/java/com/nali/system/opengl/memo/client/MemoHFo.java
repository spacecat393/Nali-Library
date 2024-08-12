package com.nali.system.opengl.memo.client;

import com.nali.system.StringReader;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MemoHFo extends MemoH
{
    public MemoHFo(String file_string)
    {
        StringBuilder stringbuilder = new StringBuilder();
        StringReader.append(stringbuilder, file_string);

        this.shader = genShader(getFrom(stringbuilder), OpenGlHelper.GL_FRAGMENT_SHADER, file_string);
    }
}
