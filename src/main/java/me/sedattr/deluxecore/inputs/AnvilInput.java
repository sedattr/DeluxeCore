package me.sedattr.deluxecore.inputs;

import me.sedattr.deluxecore.DeluxeCore;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

public class AnvilInput {
    private final AnvilGUI.Builder gui;

    public AnvilInput() {
        this.gui = new AnvilGUI.Builder();
    }

    public AnvilInput setText(String text) {
        this.gui.text(text);
        return this;
    }

    public AnvilInput setPlugin(Plugin plugin) {
        this.gui.plugin(plugin);
        return this;
    }

    public AnvilInput setHandler(BukkitRunnable runnable) {
        this.gui.onClick((slot, state) -> {
            runnable.runTaskAsynchronously(DeluxeCore.getInstance());
            return Collections.singletonList(AnvilGUI.ResponseAction.close());
        });

        return this;
    }

    public void open(Player player) {
        this.gui.open(player);
    }
}
