package com.nali.system;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.nali.Nali.error;

public class StringReader
{
	public static String[] get(Class c)
	{
		String class_name_string = c.getSimpleName();
		StringBuilder new_name_stringbuilder = new StringBuilder();
		StringBuilder mod_id_stringbuilder = new StringBuilder();
		mod_id_stringbuilder.append(class_name_string.charAt(0));
		int i = 1;
		while (i < class_name_string.length())
		{
			char class_name_char = class_name_string.charAt(i);
			if (class_name_char < 97)
			{
				break;
			}
			mod_id_stringbuilder.append(class_name_char);
			++i;
		}
		new_name_stringbuilder.append(class_name_string, i, class_name_string.length());
		return new String[]{new_name_stringbuilder.toString().toLowerCase(), mod_id_stringbuilder.toString().toLowerCase()};
	}

//	public static String convertNumberToLetter(int number)
//	{
//		StringBuilder stringbuilder = new StringBuilder();
//
//		while (number >= 0)
//		{
//			int remainder = number % 26;
//			stringbuilder.insert(0, (char) ('a' + remainder));
//			number = (number / 26) - 1; // Subtract 1 to convert to 0-based indexing
//
//			if (number < 0)
//			{
//				break;
//			}
//		}
//
//		return stringbuilder.toString();
//	}

	public static void append(StringBuilder stringbuilder, String file_string)
	{
		BufferedReader bufferedreader = null;

		try
		{
			bufferedreader = new BufferedReader(new FileReader(file_string));
		}
		catch (FileNotFoundException e)
		{
			error(e);
		}

		try
		{
			String line;
			while ((line = bufferedreader.readLine()) != null)
			{
				stringbuilder.append(line).append("\n");
			}
		}
		catch (IOException e)
		{
			error(e);
		}
		finally
		{
			if (bufferedreader != null)
			{
				try
				{
					bufferedreader.close();
				}
				catch (IOException e)
				{
					error(e);
				}
			}
		}
	}
}
