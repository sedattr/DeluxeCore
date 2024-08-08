package me.sedattr.deluxecore.inventoryapi;

import me.sedattr.deluxecore.inventoryapi.inventory.CustomInventory;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class InventoryAPI {
    public static Map<Player, CustomInventory> playerInventories = new HashMap<>();
    public static Map<Player, Long> cooldown = new HashMap<>();

    public static Long getCooldown(Player player) {
        return cooldown.getOrDefault(player, 0L);
    }

    public static void addCooldown(Player player, Long time) {
        cooldown.put(player, time);
    }

    public static void removeCooldown(Player player) {
        cooldown.remove(player);
    }

    public static CustomInventory getPlayerInventory(Player player) {
        return playerInventories.get(player);
    }

    public static void addPlayerInventory(Player player, CustomInventory inventory) {
        playerInventories.put(player, inventory);
    }

    public static void removePlayerInventory(Player player) {
        playerInventories.remove(player);
    }
}
