package com.nali.system.opengl.store;

import com.nali.system.opengl.memo.MemoAnimation;
import com.nali.system.opengl.memo.MemoGs;
import com.nali.system.opengl.memo.MemoSs;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class StoreS<RG extends MemoGs, RS extends MemoSs> extends StoreO<RG, RS>
{
    public List<MemoAnimation> memoanimation_list = new ArrayList();
}
