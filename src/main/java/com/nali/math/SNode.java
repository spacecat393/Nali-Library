package com.nali.math;

import net.minecraft.util.math.BlockPos;

public class SNode
{
    public BlockPos blockpos;
//    public float g, h, f;
    public int g;
//    public boolean walk_able = true/*, close = false*/;
    public SNode parent_snode;
    public SNode[] children_snode_array = new SNode[26];
    // public BlockPos[] sub_blockpos_array;

    public SNode(BlockPos blockpos)
    {
        this.blockpos = blockpos;
    }

    public void calculateG(SNode parent_snode)
    {
        this.parent_snode = parent_snode;
        this.g = parent_snode.g + 1;
    }
//    public void calculateH(int x, int y, int z)
//    {
//        // int dx = Math.abs(this.blockpos.getX() - x);
//        // int dy = Math.abs(this.blockpos.getY() - y);
//        // int dz = Math.abs(this.blockpos.getZ() - z);
//
//        // this.h = (int)Math.sqrt(dx * dx + dy * dy + dz * dz);
//        this.h = (float)Math.sqrt(Math.pow(x - this.blockpos.getX(), 2) + Math.pow(y - this.blockpos.getY(), 2) + Math.pow(z - this.blockpos.getZ(), 2));
////        this.h = (float)(Math.pow(x - this.blockpos.getX(), 2) + Math.pow(y - this.blockpos.getY(), 2) + Math.pow(z - this.blockpos.getZ(), 2));
//    }
//
//    public void calculateG(SNode parent_snode)
//    {
//        this.parent_snode = parent_snode;
//        // int i0 = blockpos.getX() - parent_snode.blockpos.getX();
//        // int i1 = blockpos.getY() - parent_snode.blockpos.getY();
//        // int i2 = blockpos.getZ() - parent_snode.blockpos.getZ();
//        // this.g = parent_snode.g + (int)Math.sqrt(i0 * i0 + i1 * i1 + i2 * i2);
//        this.g = (float)(parent_snode.g + Math.sqrt(Math.pow(parent_snode.blockpos.getX() - this.blockpos.getX(), 2) + Math.pow(parent_snode.blockpos.getY() - this.blockpos.getY(), 2) + Math.pow(parent_snode.blockpos.getZ() - this.blockpos.getZ(), 2)));
////        this.g = (float)(parent_snode.g + Math.pow(parent_snode.blockpos.getX() - this.blockpos.getX(), 2) + Math.pow(parent_snode.blockpos.getY() - this.blockpos.getY(), 2) + Math.pow(parent_snode.blockpos.getZ() - this.blockpos.getZ(), 2));
////        this.g = parent_snode.g + this.h;
//    }
//
//    public void calculateF()
//    {
//        this.f = this.g + this.h;
//    }
}
