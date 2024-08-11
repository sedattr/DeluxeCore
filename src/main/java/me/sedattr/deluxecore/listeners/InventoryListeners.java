package me.sedattr.deluxecore.listeners;

import me.sedattr.deluxecore.DeluxeCore;
import me.sedattr.deluxecore.inventoryapi.InventoryAPI;
import me.sedattr.deluxecore.inventoryapi.inventory.CustomInventory;
import me.sedattr.deluxecore.inventoryapi.item.ClickInterface;
import me.sedattr.deluxecore.inventoryapi.item.InventoryItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import java.time.ZonedDateTime;

public class InventoryListeners implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null)
            return;
        if (!(inventory.getHolder() instanceof CustomInventory))
            return;
        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player))
            return;
        if (event.getSlot() < 0)
            return;

        CustomInventory gui = InventoryAPI.getPlayerInventory(player);
        if (gui == null)
            return;

        if (!inventory.equals(gui.getInventory()))
            return;

        InventoryItem clickableItem = gui.getItem(event.getSlot());
        if (clickableItem == null)
            return;

        ClickInterface click = clickableItem.getClick();
        if (click == null)
            return;

        long cooldown = InventoryAPI.getCooldown(player);
        if (cooldown > 0) {
            long time = ZonedDateTime.now().toInstant().toEpochMilli() - cooldown;
            if (time < 400)
                return;
        }

        click.click(event);
        InventoryAPI.addCooldown(player, ZonedDateTime.now().toInstant().toEpochMilli());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player))
            return;

        CustomInventory gui = InventoryAPI.getPlayerInventory(player);
        if (gui == null)
            return;

        if (gui.isCloseable()) {
            InventoryAPI.removePlayerInventory(player);
            return;
        }

        Bukkit.getScheduler().runTaskLater(DeluxeCore.getInstance().getPlugin(), () -> gui.open(player), 1L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();

        InventoryAPI.removeCooldown(player);
        InventoryAPI.removePlayerInventory(player);
    }
}
