package com.kurtymckurt;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DiedListener implements Listener {

    private State state;

    public DiedListener(State state) {
        this.state = state;
    }

    @EventHandler
    public void playerDied(PlayerDeathEvent event) {
        String playerName = event.getEntity().getName();
        if(state.containsUser(event.getEntity().getUniqueId())) {
            state.removeUser(event.getEntity().getUniqueId());
            ConsoleCommandSender consoleSender = Bukkit.getServer().getConsoleSender();
            Bukkit.dispatchCommand(consoleSender, "whitelist remove " + playerName);
            Bukkit.dispatchCommand(consoleSender, "kick " + playerName + " dying. LOL.");
        }
    }
}
