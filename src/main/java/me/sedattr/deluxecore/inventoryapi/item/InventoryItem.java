package me.sedattr.deluxecore.inventoryapi.item;

import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@Getter
public class InventoryItem {
    private final ItemStack item;
    private final ClickInterface click;

    private InventoryItem(ItemStack item, ClickInterface click) {
        this.item = item;
        this.click = click;
    }

    public static InventoryItem click(ItemStack item, ClickInterface click) {
        return new InventoryItem(item, click);
    }

    public static InventoryItem empty(ItemStack item) {
        return new InventoryItem(item, null);
    }
}