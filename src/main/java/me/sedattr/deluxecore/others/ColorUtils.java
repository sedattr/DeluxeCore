package me.sedattr.deluxecore.others;

import me.sedattr.deluxecore.DeluxeCore;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
    public static String strip(String text) {
        if (text == null || text.isEmpty())
            return "";

        text = colorize(text);
        return ChatColor.stripColor(text);
    }

    public static String hex(String message) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find()) {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');

            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : ch) {
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
            matcher = pattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String colorize(String s) {
        if (s == null || s.isEmpty())
            return "";

        if (DeluxeCore.version < 16)
            return ChatColor.translateAlternateColorCodes('&', s);

        return hex(s);
    }
}
