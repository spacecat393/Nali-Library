package com.nali.gui.key;

import com.nali.gui.page.Page;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeySelect extends Key
{
	public int key;

	@Override
	public void run()
	{
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				PageSelect pageselect = (PageSelect)Page.PAGE;
				this.key = Keyboard.getEventKey();
				this.enter();

				if (this.key == Keyboard.KEY_RETURN)
				{
					pageselect.enter();
					if ((pageselect.state & 1) == 0)
					{
						pageselect.scroll = ((float) pageselect.select * pageselect.wh40 * 2 - pageselect.wh40 * 2) / Page.HEIGHT;
					}
					pageselect.clear();
					pageselect.init();
					Page.WIDTH = -1;
				}
			}
		}
	}

	public void enter()
	{
		PageSelect pageselect = (PageSelect)Page.PAGE;
		if (this.key == Keyboard.KEY_UP)
		{
			pageselect.scroll -= pageselect.wh40 * 2 / Page.HEIGHT;
		}
		else if (this.key == Keyboard.KEY_DOWN)
		{
			pageselect.scroll += pageselect.wh40 * 2 / Page.HEIGHT;
		}
		else if (this.key == Keyboard.KEY_BACK)
		{
			pageselect.back();
		}
		else if (this.key == Keyboard.KEY_ESCAPE)
		{
			pageselect.exit();
			Page.exitAll();
		}
		else if (this.key == Keyboard.KEY_LEFT)
		{
			pageselect.next((byte)-1);
//			pageselect.scroll = (pageselect.select.intValue() * pageselect.wh40 * 2 - pageselect.wh40 * 2) / Page.HEIGHT;
			pageselect.scroll = (pageselect.select * pageselect.wh40 * 2 - pageselect.wh40 * 2) / Page.HEIGHT;
			Page.WIDTH = -1;
		}
		else if (this.key == Keyboard.KEY_RIGHT)
		{
			pageselect.next((byte)1);
//			pageselect.scroll = (pageselect.select.intValue() * pageselect.wh40 * 2 - pageselect.wh40 * 2) / Page.HEIGHT;
			pageselect.scroll = (pageselect.select * pageselect.wh40 * 2 - pageselect.wh40 * 2) / Page.HEIGHT;
			Page.WIDTH = -1;
		}
	}
}
