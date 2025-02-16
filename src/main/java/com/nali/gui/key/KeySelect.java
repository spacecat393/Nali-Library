package com.nali.gui.key;

import com.nali.gui.page.Page;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeySelect extends Key
{
	@Override
	public void enter()
	{
		super.enter();

		PageSelect pageselect = (PageSelect)Page.PAGE;
		if (this.key == Keyboard.KEY_UP)
		{
			pageselect.scroll -= pageselect.wh40 * 2 / Page.HEIGHT;
		}
		else if (this.key == Keyboard.KEY_DOWN)
		{
			pageselect.scroll += pageselect.wh40 * 2 / Page.HEIGHT;
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

		this.enterReturn(pageselect);
	}

	public void enterReturn(PageSelect pageselect)
	{
		if (this.key == Keyboard.KEY_RETURN)
		{
			pageselect.enter();
			if ((pageselect.fl & PageSelect.BF_ENTER_MODE) == 0)
			{
				pageselect.scroll = ((float) pageselect.select * pageselect.wh40 * 2 - pageselect.wh40 * 2) / Page.HEIGHT;
			}
			pageselect.clear();
			pageselect.init();
			Page.WIDTH = -1;
		}
	}
}
