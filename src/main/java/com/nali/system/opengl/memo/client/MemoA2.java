package com.nali.system.opengl.memo.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MemoA2
{
    public int[] index_int_array;

    public float[] vertices_float_array;
    public float[] joints_float_array;
    public float[] weights_float_array;
    public byte max_joints;
}
