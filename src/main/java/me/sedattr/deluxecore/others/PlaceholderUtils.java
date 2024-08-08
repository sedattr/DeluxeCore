package me.sedattr.deluxecore.others;

import java.util.Map;

public class PlaceholderUtils {
    public static String replacePlaceholders(String message, PlaceholderUtil placeholderUtil) {
        if (placeholderUtil == null)
            return message;

        Map<String, String> placeholders = placeholderUtil.getPlaceholders();
        if (placeholders != null && !placeholders.isEmpty())
            for (Map.Entry<String, String> placeholder : placeholders.entrySet()) {
                String key = placeholder.getKey();
                String value = placeholder.getValue();

                message = message
                        .replace(key, value);
            }

        return message;
    }
}
