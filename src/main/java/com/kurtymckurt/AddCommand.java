package com.kurtymckurt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AddCommand implements CommandExecutor {

    private State state;

    public AddCommand(State state) {
        this.state = state;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            return false;
        }

        String addOrRemove = args[0];
        if(addOrRemove == null) {
            return false;
        }

        if(!"add".equals(addOrRemove) && !"remove".equals(addOrRemove)) {
            return false;
        }

        if(args.length < 2) {
            return false;
        }

        ConsoleCommandSender consoleSender = Bukkit.getServer().getConsoleSender();
        for(int i = 1; i < args.length; i++) {
            Player player = Bukkit.getPlayer(args[i]);
            if(player == null) {
                Bukkit.getOfflinePlayer(args[i]);
            }
            if("add".equals(addOrRemove)) {
                //addPlayer();
                if (player != null) {
                    Bukkit.dispatchCommand(consoleSender, "whitelist add " + player.getName());
                    state.addUser(player.getUniqueId());
                }
            } else {
                //removePlayer();

                if (player != null) {
                    Bukkit.dispatchCommand(consoleSender, "whitelist remove " +  player.getName());
                    state.removeUser(player.getUniqueId());
                }
            }
        }

        return true;
    }
}
