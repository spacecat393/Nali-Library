package com.nali.list.key;

import com.nali.gui.page.Page;
import com.nali.gui.page.PageLoad;
import com.nali.key.Key;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

@SideOnly(Side.CLIENT)
public class NaliGui extends Key
{
	public byte state;

	@Override
	public void run()
	{
		if (Minecraft.getMinecraft().currentScreen == null)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_P))
			{
				if ((this.state & 1) == 0)
				{
					if ((this.state & 2) == 2)
					{
						PageLoad pageload = (PageLoad)Page.PAGE;
						pageload.clear();
						Page.PAGE = null;

						this.state &= 255-2;
					}
					else
					{
						PageLoad pageload = new PageLoad();
						pageload.init();
						Page.WIDTH = Display.getWidth();
						Page.HEIGHT = Display.getHeight();
						pageload.gen(Page.WIDTH, Page.HEIGHT);
						Page.PAGE = pageload;

						this.state |= 2;
					}
					this.state |= 1;
				}
			}
			else
			{
				this.state &= 255-1;
			}
		}
	}
}
