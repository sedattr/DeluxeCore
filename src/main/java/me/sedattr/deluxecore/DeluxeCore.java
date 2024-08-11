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
    @Getter private static DeluxeCore instance;

    @Getter private JavaPlugin plugin;
    @Getter private final HashMap<Player, ChatInput> chatInputs = new HashMap<>();
    @Getter private int version;

    public void setup(JavaPlugin javaPlugin) {
        instance = this;
        this.plugin = javaPlugin;
        this.version = Integer.parseInt(Bukkit.getBukkitVersion().substring(2, 4).replace(".", ""));

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new InventoryListeners(), plugin);
        pm.registerEvents(new ChatListener(), plugin);
    }
}
