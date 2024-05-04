package com.nali.data.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FastClientData implements ClientData
{
    public int start;
    public int end;

    public FastClientData(int start, int end)
    {
        this.start = start;
        this.end = end;
    }

    @Override
    public int StartPart()
    {
        return this.start;
    }

    @Override
    public int EndPart()
    {
        return this.end;
    }
}
