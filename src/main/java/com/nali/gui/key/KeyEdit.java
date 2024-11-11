package com.nali.gui.key;

import com.nali.gui.page.Page;
import com.nali.gui.page.PageEdit;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyEdit extends KeySelect
{
	@Override
	public void enter()
	{
		PageEdit pageedit = (PageEdit)Page.PAGE;
		if ((pageedit.state & 1) == 1)
		{
			char c = Keyboard.getEventCharacter();
			if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_C))
			{
				GuiScreen.setClipboardString(pageedit.input_stringbuilder.toString());
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_V))
			{
				String string = GuiScreen.getClipboardString();
				pageedit.input_stringbuilder.insert(pageedit.select_box, string);
				pageedit.select_box += string.length();

				pageedit.clear();
				pageedit.init();
				this.setScrollEdit(pageedit);
				Page.WIDTH = -1;
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_X))
			{
				pageedit.input_stringbuilder.setLength(0);
				pageedit.select_box = 0;

				pageedit.clear();
				pageedit.init();
				this.setScrollEdit(pageedit);
				Page.WIDTH = -1;
			}
			else if (Character.isLetterOrDigit(c) || c == '.' || c == '-' || c == '_'/* || Character.isSpaceChar(c)*/)
			{
				pageedit.input_stringbuilder.insert(pageedit.select_box, c);
				++pageedit.select_box;

				pageedit.clear();
				pageedit.init();
				this.setScrollEdit(pageedit);
				Page.WIDTH = -1;
			}
			else if (key == Keyboard.KEY_BACK)
			{
				if (pageedit.select_box > 0)
				{
					pageedit.input_stringbuilder.deleteCharAt(pageedit.select_box - 1);
					--pageedit.select_box;
				}

				pageedit.clear();
				pageedit.init();
				this.setScrollEdit(pageedit);
				Page.WIDTH = -1;
			}
			else if (key == Keyboard.KEY_LEFT)
			{
				if (pageedit.select_box > 0)
				{
					--pageedit.select_box;
				}

				this.setScrollEdit(pageedit);
			}
			else if (key == Keyboard.KEY_RIGHT)
			{
				if (pageedit.select_box < pageedit.input_stringbuilder.length())
				{
					++pageedit.select_box;
				}

				this.setScrollEdit(pageedit);
			}
			else if (key == Keyboard.KEY_ESCAPE)
			{
				pageedit.state ^= 1;
				pageedit.scroll = ((float) pageedit.select * pageedit.wh40 * 2 - pageedit.wh40 * 2) / Page.HEIGHT;
				Page.WIDTH = -1;
			}
			else if (this.key == Keyboard.KEY_UP)
			{
				pageedit.select_box -= 15;
				if (pageedit.select_box < 0)
				{
					pageedit.select_box = 0;
				}
				this.setScrollEdit(pageedit);
			}
			else if (this.key == Keyboard.KEY_DOWN)
			{
				pageedit.select_box += 15;
				int length = pageedit.input_stringbuilder.length();
				if (pageedit.select_box > length)
				{
					pageedit.select_box = length;
				}
				this.setScrollEdit(pageedit);
			}
		}
		else
		{
			super.enter();
		}
	}

	public void setScrollEdit(PageEdit pageconfig)
	{
		float nl_ss = pageconfig.wh20 + pageconfig.wh10;
		float nl_x = pageconfig.wh20, nl_y = 0;

		for (int i = 0; i < pageconfig.select_box; ++i)
		{
			if (nl_x > Page.WIDTH - nl_ss)
			{
				nl_x = pageconfig.wh20;
				nl_y += nl_ss;
			}
			nl_x += nl_ss;
		}

		pageconfig.scroll = 2.0F * nl_y / Page.HEIGHT;
	}
}
