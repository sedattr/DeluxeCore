package me.sedattr.deluxecore.inventoryapi;

import me.sedattr.deluxecore.inventoryapi.inventory.CustomInventory;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class InventoryAPI {
    public static Map<Player, CustomInventory> inventories = new HashMap<>();
    public static Map<Player, Long> cooldown = new HashMap<>();

    public static CustomInventory getInventory(Player player) {
        return inventories.get(player);
    }

    public static boolean hasInventory(Player player) {
        return inventories.containsKey(player);
    }
}
