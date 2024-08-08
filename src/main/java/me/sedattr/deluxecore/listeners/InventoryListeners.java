package me.sedattr.deluxecore.listeners;

import me.sedattr.deluxecore.DeluxeCore;
import me.sedattr.deluxecore.inventoryapi.InventoryAPI;
import me.sedattr.deluxecore.inventoryapi.inventory.CustomInventory;
import me.sedattr.deluxecore.inventoryapi.item.ClickInterface;
import me.sedattr.deluxecore.inventoryapi.item.InventoryItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.ZonedDateTime;

public class InventoryListeners implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player))
            return;
        CustomInventory gui = InventoryAPI.getInventory(player);
        if (gui == null)
            return;
        event.setCancelled(true);

        Inventory inventory = event.getClickedInventory();
        if (inventory == null || event.getSlot() < 0)
            return;

        if (!inventory.equals(gui.getInventory()))
            return;

        long cooldown = InventoryAPI.cooldown.getOrDefault(player, 0L);
        if (cooldown > 0) {
            long time = ZonedDateTime.now().toInstant().toEpochMilli() - cooldown;
            if (time < 400)
                return;
        }

        InventoryItem clickableItem = gui.getItem(event.getSlot());
        if (clickableItem == null)
            return;

        ClickInterface click = clickableItem.getClick();
        if (click == null)
            return;

        click.click(event);
        InventoryAPI.cooldown.put(player, ZonedDateTime.now().toInstant().toEpochMilli());
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player player))
            return;

        CustomInventory gui = InventoryAPI.getInventory(player);
        if (gui == null)
            return;

        InventoryAPI.inventories.remove(player);
        InventoryAPI.cooldown.remove(player);
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player))
            return;
        CustomInventory gui = InventoryAPI.getInventory(player);
        if (gui == null)
            return;

        if (gui.isCloseable()) {
            InventoryAPI.inventories.remove(player);
            InventoryAPI.cooldown.remove(player);
            if (DeluxeCore.getInstance() == null)
                return;

            new BukkitRunnable() {
                public void run() {
                    player.updateInventory();
                }
            }.runTaskLater(DeluxeCore.getInstance(), 1L);
            return;
        }
        if (DeluxeCore.getInstance() == null)
            return;

        new BukkitRunnable() {
            public void run() {
                gui.open(player);
            }
        }.runTaskLater(DeluxeCore.getInstance(), 1L);
    }
}
