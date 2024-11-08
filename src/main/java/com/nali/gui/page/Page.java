package com.nali.gui.page;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class Page
{
	public static Page PAGE;
	public static int WIDTH, HEIGHT;
	public abstract void render();
}
