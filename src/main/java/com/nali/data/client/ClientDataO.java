package com.nali.data.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ClientDataO
{
    int StartPart();
    int EndPart();//MaxPart = StartPart - EndPart
//    int Texture();
//    int Shader();
}
