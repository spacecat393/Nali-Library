package com.nali.key;

import com.nali.system.Reflect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static com.nali.Nali.error;

@SideOnly(Side.CLIENT)
public abstract class Key
{
	public static Key[] KEY_ARRAY;

	static
	{
		List<Class> key_class_list = Reflect.getClasses("com.nali.list.key");
		int size = key_class_list.size();
		KEY_ARRAY = new Key[size];

		for (int i = 0; i < size; ++i)
		{
			Class key_class = key_class_list.get(i);
			try
			{
//				key_class.getField("ID").set(null, i);
				KEY_ARRAY[i] = (Key)key_class/*.getConstructors()[0]*/.newInstance();
			}
			catch (InstantiationException | IllegalAccessException/* | InvocationTargetException*//* | NoSuchFieldException*/ e)
			{
				error(e);
			}
		}
	}

//	public abstract int getKeyCode();
	public abstract void run();
}
