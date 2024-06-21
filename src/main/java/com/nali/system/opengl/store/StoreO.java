package com.nali.system.opengl.store;

import com.nali.system.opengl.memo.MemoGo;
import com.nali.system.opengl.memo.MemoSo;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class StoreO<G extends MemoGo, S extends MemoSo>
{
    public List<G> g_list = new ArrayList();
    public List<S> s_list = new ArrayList();
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
