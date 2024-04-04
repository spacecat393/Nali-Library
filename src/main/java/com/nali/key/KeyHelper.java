package com.nali.key;

import com.nali.Nali;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.regex.Pattern;

@SideOnly(Side.CLIENT)
public class KeyHelper
{
    static
    {
        copyToClipboard(getTextFromClipboard());
    }

    public static void copyToClipboard(String text)
    {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public static String getTextFromClipboard()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(null);

        if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
        {
            try
            {
                return (String)transferable.getTransferData(DataFlavor.stringFlavor);
            }
            catch (Exception e)
            {
                Nali.error(e);
            }
        }

        return "";
    }

    public static boolean isValidUUIDString(String uuid_string)
    {
        String uuidRegex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(uuidRegex);
        return uuid_string.length() == 36 && pattern.matcher(uuid_string).matches();
    }
}
