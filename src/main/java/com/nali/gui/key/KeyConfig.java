package com.nali.gui.key;

import com.nali.gui.page.Page;
import com.nali.gui.page.PageConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyConfig extends Key
{
	@Override
	public void run()
	{
		PageConfig pageconfig = (PageConfig)Page.PAGE;
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				int key = Keyboard.getEventKey();
				//						int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
				//						warn("KEY: " + key);
				//						warn("I: " + i);
				//SHIFT 42
				//space 57
				if ((pageconfig.state & 4) == 4)
				{
					char c = Keyboard.getEventCharacter();
					if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_C))
					{
						GuiScreen.setClipboardString(pageconfig.input_stringbuilder.toString());
					}
					else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_V))
					{
						String string = GuiScreen.getClipboardString();
						pageconfig.input_stringbuilder.insert(pageconfig.select_box, string);
						pageconfig.select_box += string.length();

						pageconfig.clear();
						pageconfig.init();
						pageconfig.gen();
						this.setScrollEdit(pageconfig, pageconfig.wh20, Page.WIDTH, Page.HEIGHT);
					}
					else if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) && Keyboard.isKeyDown(Keyboard.KEY_X))
					{
						pageconfig.input_stringbuilder.setLength(0);
						pageconfig.select_box = 0;

						pageconfig.clear();
						pageconfig.init();
						pageconfig.gen();
						this.setScrollEdit(pageconfig, pageconfig.wh20, Page.WIDTH, Page.HEIGHT);
					}
					else if (Character.isLetterOrDigit(c) || c == '.'/* || Character.isSpaceChar(c)*/)
					{
						pageconfig.input_stringbuilder.insert(pageconfig.select_box, c);
						++pageconfig.select_box;

						pageconfig.clear();
						pageconfig.init();
						pageconfig.gen();
						this.setScrollEdit(pageconfig, pageconfig.wh20, Page.WIDTH, Page.HEIGHT);
					}
					else if (key == Keyboard.KEY_BACK)
					{
						if (pageconfig.select_box > 0)
						{
							pageconfig.input_stringbuilder.deleteCharAt(pageconfig.select_box - 1);
							--pageconfig.select_box;
						}

						pageconfig.clear();
						pageconfig.init();
						pageconfig.gen();
						this.setScrollEdit(pageconfig, pageconfig.wh20, Page.WIDTH, Page.HEIGHT);
					}
					else if (key == Keyboard.KEY_LEFT)
					{
						if (pageconfig.select_box > 0)
						{
							--pageconfig.select_box;
						}

//							pageconfig.gen(width, height, wh20, h20);
						this.setScrollEdit(pageconfig, pageconfig.wh20, Page.WIDTH, Page.HEIGHT);
					}
					else if (key == Keyboard.KEY_RIGHT)
					{
						if (pageconfig.select_box < pageconfig.input_stringbuilder.length())
						{
							++pageconfig.select_box;
						}

//							pageconfig.gen(width, height, wh20, h20);
						this.setScrollEdit(pageconfig, pageconfig.wh20, Page.WIDTH, Page.HEIGHT);
					}
					else if (key == Keyboard.KEY_ESCAPE)
					{
						pageconfig.state ^= 4;
						pageconfig.scroll = ((float)pageconfig.select * pageconfig.wh20 * 4 - pageconfig.wh20 * 4) / Page.HEIGHT;
						pageconfig.gen();
					}
				}
				else
				{
					if (key == Keyboard.KEY_ESCAPE)
					{
						//							config_byte_array = new byte[];
						pageconfig.state |= 8;
					}
					else if (key == Keyboard.KEY_LEFT)
					{
						pageconfig.next((byte)-1);
						pageconfig.gen();
						pageconfig.scroll = ((float)pageconfig.select * pageconfig.wh20 * 4 - pageconfig.wh20 * 4) / Page.HEIGHT;
					}
					else if (key == Keyboard.KEY_RIGHT)
					{
						pageconfig.next((byte)1);
						pageconfig.gen();
						pageconfig.scroll = ((float)pageconfig.select * pageconfig.wh20 * 4 - pageconfig.wh20 * 4) / Page.HEIGHT;
					}
				}
				if (key == Keyboard.KEY_RETURN)
				{
					pageconfig.enter();
					if ((pageconfig.state & 4) == 0)
					{
						pageconfig.scroll = ((float)pageconfig.select * pageconfig.wh20 * 4 - pageconfig.wh20 * 4) / Page.HEIGHT;
					}
					pageconfig.clear();
					pageconfig.init();
					pageconfig.gen();
				}
				else if (key == Keyboard.KEY_UP)
				{
					pageconfig.scroll -= pageconfig.wh20 * 4.0F / Page.HEIGHT;
				}
				else if (key == Keyboard.KEY_DOWN)
				{
					pageconfig.scroll += pageconfig.wh20 * 4.0F / Page.HEIGHT;
				}
			}
		}
	}

	public void setScrollEdit(PageConfig pageconfig, int wh20, int width, int height)
	{
		int wh10 = wh20 / 2;
		int nl_ss = wh20 + wh10;
		int nl_x = wh20, nl_y = 0;

		for (int i = 0; i < pageconfig.select_box; ++i)
		{
			if (nl_x > width - nl_ss)
			{
				nl_x = wh20;
				nl_y += nl_ss;
			}
			nl_x += nl_ss;
		}

		pageconfig.scroll = 2.0F * (float)nl_y / height;
	}
}
