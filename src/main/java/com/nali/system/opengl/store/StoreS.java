package com.nali.system.opengl.store;

import com.nali.system.opengl.memo.MemoAnimation;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class StoreS<G extends MemoGs, S extends MemoSs> extends StoreO<G, S>
{
    public List<MemoAnimation> memoanimation_list = new ArrayList();
    public float scale;
}
