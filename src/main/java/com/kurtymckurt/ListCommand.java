package com.kurtymckurt;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListCommand implements CommandExecutor {

    private State state;

    public ListCommand(State state) {
        this.state = state;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp()) {
            return false;
        }

        Bukkit.getServer().getConsoleSender().sendMessage(this.state.toString());

        return true;
    }
}
