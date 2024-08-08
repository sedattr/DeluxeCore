package me.sedattr.deluxecore.inventoryapi.inventory;

import lombok.Getter;
import me.sedattr.deluxecore.inventoryapi.InventoryAPI;
import me.sedattr.deluxecore.inventoryapi.item.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

@Getter
public class CustomInventory implements InventoryHolder {
    private final Inventory inventory;
    private final HashMap<Integer, InventoryItem> items = new HashMap<>();
    private final String id;
    private boolean closeable;

    public CustomInventory(String title, InventoryType inventoryType, int size, String id, boolean closeable) {
        this.id = id;
        this.closeable = closeable;

        if (inventoryType.equals(InventoryType.CHEST))
            this.inventory = Bukkit.createInventory(this, size, title);
        else
            this.inventory = Bukkit.createInventory(this, inventoryType, title);
    }

    public void open(final Player player) {
        if (player == null)
            return;

        player.openInventory(this.inventory);
        InventoryAPI.inventories.put(player, this);
    }

    public void close(Player player) {
        if (player == null)
            return;

        this.closeable = true;
        player.closeInventory();
    }

    public void setItem(int slot, InventoryItem clickableItem) {
        if (slot < 0 || slot >= this.inventory.getSize())
            return;

        if (clickableItem.getItem() != null && !clickableItem.getItem().getType().equals(Material.AIR)) {
            this.items.put(slot, clickableItem);
            this.inventory.setItem(slot, clickableItem.getItem());
        }
    }

    public InventoryItem getItem(int slot) {
        return this.items.getOrDefault(slot, null);
    }
}
