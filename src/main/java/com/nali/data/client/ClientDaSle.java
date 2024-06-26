package com.nali.data.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class ClientDaSle extends ClientDaSn
{
    public float body_rot,
            head_rot,
            net_head_yaw,
            head_pitch;

    public boolean should_render;
}
