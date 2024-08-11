package me.sedattr.deluxecore.listeners;

import me.sedattr.deluxecore.DeluxeCore;
import me.sedattr.deluxecore.inputs.ChatInput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;

public class ChatListener implements Listener {
    @EventHandler
    public void chatListener(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();

        for (Map.Entry<Player, ChatInput> entry : DeluxeCore.getInstance().getChatInputs().entrySet()) {
            Player owner = entry.getKey();
            ChatInput chatInput = entry.getValue();

            if (!player.equals(owner))
                continue;

            e.setCancelled(true);
            HandlerList.unregisterAll(this);

            Bukkit.getScheduler().runTask(DeluxeCore.getInstance().getPlugin(), () -> {
                chatInput.setInput(e.getMessage());

                chatInput.getHandler().run();
                chatInput.getHandler().cancel();
            });
        }
    }
}
