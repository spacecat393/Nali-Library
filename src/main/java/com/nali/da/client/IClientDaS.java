package com.nali.da.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IClientDaS extends IClientDaO
{
    int AnimationID();
}
