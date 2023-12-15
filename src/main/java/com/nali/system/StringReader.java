package com.nali.system;

public class StringReader
{
    public static String[] get(Class clasz)
    {
        String class_name_string = clasz.getSimpleName();
        StringBuilder new_name_stringbuilder = new StringBuilder();
        StringBuilder mod_id_stringbuilder = new StringBuilder("" + class_name_string.charAt(0));
        int i = 1;
        for (; i < class_name_string.length(); ++i)
        {
            char c = class_name_string.charAt(i);
            if (Character.isUpperCase(c))
            {
                break;
            }
            else
            {
                mod_id_stringbuilder.append(c);
            }
        }
        new_name_stringbuilder.append(class_name_string, i, class_name_string.length());
        return new String[]{new_name_stringbuilder.toString().toLowerCase(), mod_id_stringbuilder.toString().toLowerCase()};
    }
}
