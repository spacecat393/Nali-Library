package com.nali.system.opengl.memo.client.store;

import com.nali.system.opengl.memo.client.MemoAnimation;
import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class StoreS<RG extends MemoGo, RS extends MemoSo> extends StoreO<RG, RS>
{
    public List<MemoAnimation> memoanimation_list = new ArrayList();
}
