package com.nali.gui.key;

import com.nali.gui.page.Page;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Key
{
	public static Key KEY;

	public int key;

	public void run()
	{
		while (Keyboard.next())
		{
			if (Keyboard.getEventKeyState())
			{
				this.key = Keyboard.getEventKey();
				this.enter();
			}
		}
	}

	public void enter()
	{
		if (this.key == Keyboard.KEY_BACK)
		{
			Page.PAGE.back();
		}
		else if (this.key == Keyboard.KEY_ESCAPE)
		{
			Page.PAGE.exit();
			Page.exitAll();
		}
	}
}
