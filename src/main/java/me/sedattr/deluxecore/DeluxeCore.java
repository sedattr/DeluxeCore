package me.sedattr.deluxecore;

import lombok.Getter;
import me.sedattr.deluxecore.inputs.ChatInput;
import me.sedattr.deluxecore.listeners.InventoryListeners;
import me.sedattr.deluxecore.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class DeluxeCore {
    public static HashMap<Player, ChatInput> chatInputs = new HashMap<>();
    @Getter private static JavaPlugin instance;
    public static int version;

    public void setup(JavaPlugin plugin) {
        instance = plugin;
        version = Integer.parseInt(Bukkit.getBukkitVersion().substring(2, 4).replace(".", ""));

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryListeners(), plugin);
        pm.registerEvents(new ChatListener(), plugin);
    }
}
