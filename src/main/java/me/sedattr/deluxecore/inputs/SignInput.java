package me.sedattr.deluxecore.inputs;

import de.rapha149.signgui.SignGUI;
import de.rapha149.signgui.SignGUIBuilder;
import me.sedattr.deluxecore.DeluxeCore;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.List;

public class SignInput {
    private final SignGUIBuilder gui;

    public SignInput() {
        this.gui = SignGUI.builder();
    }

    public SignInput setLines(List<String> lines) {
        if (lines.isEmpty())
            return this;

        int i = 0;
        for (String line : lines) {
            this.gui.setLine(i, line);
        }

        return this;
    }

    public SignInput setLine(Integer line, String text) {
        this.gui.setLine(line, text);
        return this;
    }

    public SignInput setHandler(BukkitRunnable runnable) {
        this.gui.setHandler((p, entry) -> {
            runnable.runTaskAsynchronously(DeluxeCore.getInstance().getPlugin());
            return Collections.emptyList();
        });

        return this;
    }

    public void open(Player player) {
        this.gui.build().open(player);
    }
}