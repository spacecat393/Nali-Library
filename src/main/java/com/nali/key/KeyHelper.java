package com.nali.key;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static com.nali.Nali.I;

@SideOnly(Side.CLIENT)
public class KeyHelper
{
//    static
//    {
//        copyToClipboard(getTextFromClipboard());
//    }

    public static KeyBinding[] KEYBINDING_ARRAY = new KeyBinding[0];
    public static Method[] DETECT_METHOD_ARRAY = new Method[0];
//    public static List<Integer> CURRENT_KEY_INTEGER_LIST = new ArrayList();
//    public static List<Integer> CURRENT_MOUSE_KEY_INTEGER_LIST = new ArrayList();

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
                I.error(e);
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

    public static Color generateRainbowColor()
    {
        return Color.getHSBColor(Minecraft.getSystemTime()/*System.currentTimeMillis()*/ % 3600 / 3600.0F, 1.0F, 1.0F);
    }

    public static int getRainbowColor4()
    {
        Color color = generateRainbowColor();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = 255;
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
