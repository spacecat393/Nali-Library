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
						pageselect.scroll = ((float) pageselect.select * pageselect.wh20 * 4 - pageselect.wh20 * 4) / Page.HEIGHT;
					}
					pageselect.clear();
					pageselect.init();
					pageselect.gen();
				}
				else if (this.key == Keyboard.KEY_UP)
				{
					pageselect.scroll -= pageselect.wh20 * 4.0F / Page.HEIGHT;
				}
				else if (this.key == Keyboard.KEY_DOWN)
				{
					pageselect.scroll += pageselect.wh20 * 4.0F / Page.HEIGHT;
				}
			}
		}
	}

	public void enter()
	{
		PageSelect pageselect = (PageSelect)Page.PAGE;
		if (this.key == Keyboard.KEY_BACK)
		{
			pageselect.back();
		}
		else if (this.key == Keyboard.KEY_ESCAPE)
		{
			//							config_byte_array = new byte[];
			pageselect.state |= 2;
		}
		else if (this.key == Keyboard.KEY_LEFT)
		{
			pageselect.next((byte)-1);
			pageselect.gen();
			pageselect.scroll = ((float) pageselect.select * pageselect.wh20 * 4 - pageselect.wh20 * 4) / Page.HEIGHT;
		}
		else if (this.key == Keyboard.KEY_RIGHT)
		{
			pageselect.next((byte)1);
			pageselect.gen();
			pageselect.scroll = ((float) pageselect.select * pageselect.wh20 * 4 - pageselect.wh20 * 4) / Page.HEIGHT;
		}
	}
}
