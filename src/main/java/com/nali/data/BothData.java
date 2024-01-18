package com.nali.data;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface BothData
{
    @SideOnly(Side.CLIENT)
    int MaxPart();
    @SideOnly(Side.CLIENT)
    int StepModels();

    float Width();
    float Height();
    float Scale();
    int MaxFrame();
}
