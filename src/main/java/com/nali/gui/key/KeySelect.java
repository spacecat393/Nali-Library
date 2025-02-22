package com.nali.gui.key;

import com.nali.gui.page.Page;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeySelect
<
	P extends PageSelect
> extends Key
{
	public P p;

	public KeySelect(P pageselect)
	{
		this.p = pageselect;
	}

	@Override
	public void enter()
	{
		super.enter();

		if (this.key == Keyboard.KEY_UP)
		{
			this.p.scroll -= this.p.wh40 * 2 / Page.HEIGHT;
		}
		else if (this.key == Keyboard.KEY_DOWN)
		{
			this.p.scroll += this.p.wh40 * 2 / Page.HEIGHT;
		}
		else if (this.key == Keyboard.KEY_LEFT)
		{
			this.p.next((byte)-1);
//			p.scroll = (p.select.intValue() * p.wh40 * 2 - p.wh40 * 2) / Page.HEIGHT;
			this.p.scroll = (this.p.select * this.p.wh40 * 2 - this.p.wh40 * 2) / Page.HEIGHT;
			Page.WIDTH = -1;
		}
		else if (this.key == Keyboard.KEY_RIGHT)
		{
			this.p.next((byte)1);
//			p.scroll = (p.select.intValue() * p.wh40 * 2 - p.wh40 * 2) / Page.HEIGHT;
			this.p.scroll = (this.p.select * this.p.wh40 * 2 - this.p.wh40 * 2) / Page.HEIGHT;
			Page.WIDTH = -1;
		}

		this.enterReturn(this.p);
	}

	public void enterReturn(PageSelect pageselect)
	{
		if (this.key == Keyboard.KEY_RETURN)
		{
			this.p.enter();
			if ((this.p.fl & PageSelect.BF_ENTER_MODE) == 0)
			{
				this.p.scroll = ((float)this.p.select * this.p.wh40 * 2 - this.p.wh40 * 2) / Page.HEIGHT;
			}
			this.p.clear();
			this.p.init();
			Page.WIDTH = -1;
		}
	}
}
