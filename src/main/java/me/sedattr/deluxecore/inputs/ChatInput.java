package me.sedattr.deluxecore.inputs;

import lombok.Getter;
import lombok.Setter;
import me.sedattr.deluxecore.DeluxeCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@Getter
public class ChatInput {
    private BukkitRunnable handler;
    @Setter private String input;

    public ChatInput setHandler(BukkitRunnable runnable) {
        this.handler = runnable;
        return this;
    }

    public void open(Player player, Integer time) {
        DeluxeCore.getInstance().getChatInputs().put(player, this);

        Bukkit.getScheduler().runTaskLaterAsynchronously(DeluxeCore.getInstance().getPlugin(), () -> {
            if (!this.handler.isCancelled())
                this.handler.run();
        }, time * 20L);
    }
}
