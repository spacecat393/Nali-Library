package com.nali.render;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntitiesRenderMemory
{
    public float scale,
        body_rot,
        head_rot,
        net_head_yaw,
        head_pitch;

    public boolean should_render;
}
