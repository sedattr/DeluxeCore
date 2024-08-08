package me.sedattr.deluxecore.inventoryapi.inventory;

import org.bukkit.event.inventory.InventoryType;

public class InventoryManager {
    private String title = "";
    private final InventoryType inventoryType;
    private int size;
    private boolean closeable;
    private String id;

    public InventoryManager() {
        this.inventoryType = InventoryType.CHEST;
        this.size = 54;
        this.closeable = true;
        this.id = "";
    }

    public InventoryManager setTitle(String title) {
        this.title = title;
        return this;
    }

    public InventoryManager setSize(int size) {
        this.size = size;
        return this;
    }

    public InventoryManager setCloseable(boolean type) {
        this.closeable = type;
        return this;
    }

    public InventoryManager setId(String id) {
        this.id = id;
        return this;
    }

    public CustomInventory create() {
        return new CustomInventory(this.title, this.inventoryType, this.size, this.id, this.closeable);
    }
}
