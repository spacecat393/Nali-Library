package com.nali.system.opengl.memo.client.store;

import com.nali.system.opengl.memo.client.MemoGo;
import com.nali.system.opengl.memo.client.MemoSo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class StoreO<RG extends MemoGo, RS extends MemoSo>
{
    public List<RG> rg_list = new ArrayList();
    public List<RS> rs_list = new ArrayList();
//    @Override
//    public List<G> getListG()
//    {
//        return g_list;
//    }
//
//    @Override
//    public List<S> getListS()
//    {
//        return s_list;
//    }
}
