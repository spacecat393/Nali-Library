package com.nali.system.opengl.store;

import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class StoreSle<G extends MemoGs, S extends MemoSs> extends StoreS<G, S>
{
    public float body_rot,
            head_rot,
            net_head_yaw,
            head_pitch;

    public boolean should_render;
}
