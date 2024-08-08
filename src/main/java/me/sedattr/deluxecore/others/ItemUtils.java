package me.sedattr.deluxecore.others;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {
    public static void changeName(ItemStack item, String name, Placeholders placeholderUtil) {
        if (item == null)
            return;
        if (name.isEmpty())
            return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return;

        meta.setDisplayName(ColorUtils.colorize(PlaceholderUtils.replacePlaceholders(name, placeholderUtil)));

        item.setItemMeta(meta);
    }

    public static void changeLore(ItemStack item, List<String> lore, Placeholders placeholderUtil) {
        if (item == null)
            return;
        if (lore.isEmpty())
            return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null)
            return;

        List<String> newLore = new ArrayList<>();

        for (String line : lore)
            newLore.add(ColorUtils.colorize(PlaceholderUtils.replacePlaceholders(line, placeholderUtil)));

        meta.setLore(newLore);
        item.setItemMeta(meta);
    }
}
