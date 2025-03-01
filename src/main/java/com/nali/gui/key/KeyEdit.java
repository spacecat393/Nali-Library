package com.nali.gui.key;

import com.nali.gui.box.Box;
import com.nali.gui.page.PageEdit;
import com.nali.gui.page.PageSelect;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyEdit
<
	P extends PageEdit
> extends KeySelect<P>
{
	public KeyEdit(P p)
	{
		super(p);
	}

	@Override
	public void enter()
	{
		if ((this.p.fl & PageSelect.BF_ENTER_MODE) == PageSelect.BF_ENTER_MODE)
		{
			char c = Keyboard.getEventCharacter();
			if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_C))
			{
				GuiScreen.setClipboardString(this.p.input_stringbuilder.toString());
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_V))
			{
				String string = GuiScreen.getClipboardString();
				this.p.input_stringbuilder.insert(this.p.select_box, string);
				this.p.select_box += string.length();

				this.p.clear();
				this.p.init();
				this.setScrollEdit(this.p);
				Box.WIDTH = -1;
			}
			else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_X))
			{
				this.p.input_stringbuilder.setLength(0);
				this.p.select_box = 0;

				this.p.clear();
				this.p.init();
				this.setScrollEdit(this.p);
				Box.WIDTH = -1;
			}
			else if (Character.isLetterOrDigit(c) || c == '.' || c == '-' || c == '_'/* || Character.isSpaceChar(c)*/)
			{
				this.p.input_stringbuilder.insert(this.p.select_box, c);
				++this.p.select_box;

				this.p.clear();
				this.p.init();
				this.setScrollEdit(this.p);
				Box.WIDTH = -1;
			}
			else if (this.key == Keyboard.KEY_BACK)
			{
				if (this.p.select_box > 0)
				{
					this.p.input_stringbuilder.deleteCharAt(this.p.select_box - 1);
					--this.p.select_box;
				}

				this.p.clear();
				this.p.init();
				this.setScrollEdit(this.p);
				Box.WIDTH = -1;
			}
			else if (this.key == Keyboard.KEY_LEFT)
			{
				if (this.p.select_box > 0)
				{
					--this.p.select_box;
				}

				this.setScrollEdit(this.p);
			}
			else if (this.key == Keyboard.KEY_RIGHT)
			{
				if (this.p.select_box < this.p.input_stringbuilder.length())
				{
					++this.p.select_box;
				}

				this.setScrollEdit(this.p);
			}
			else if (this.key == Keyboard.KEY_ESCAPE)
			{
				this.p.fl ^= PageSelect.BF_ENTER_MODE;
				this.p.scroll = ((float) this.p.select * this.p.wh40 * 2 - this.p.wh40 * 2) / Box.HEIGHT;
				Box.WIDTH = -1;
			}
			else if (this.key == Keyboard.KEY_UP)
			{
				this.p.select_box -= 15;
				if (this.p.select_box < 0)
				{
					this.p.select_box = 0;
				}
				this.setScrollEdit(this.p);
			}
			else if (this.key == Keyboard.KEY_DOWN)
			{
				this.p.select_box += 15;
				int length = this.p.input_stringbuilder.length();
				if (this.p.select_box > length)
				{
					this.p.select_box = length;
				}
				this.setScrollEdit(this.p);
			}

			this.enterReturn(this.p);
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
			if (nl_x > Box.WIDTH - nl_ss)
			{
				nl_x = pageconfig.wh20;
				nl_y += nl_ss;
			}
			nl_x += nl_ss;
		}

		pageconfig.scroll = 2.0F * nl_y / Box.HEIGHT;
	}
}
