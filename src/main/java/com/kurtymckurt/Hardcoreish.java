package com.kurtymckurt;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Hardcoreish extends JavaPlugin {

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        getLogger().info("Loaded Hardcoreish...");
        PluginManager pm = getServer().getPluginManager();

        State state = State.loadState();
        if(state == null) {
          state = new State();
        }

        AddCommand addCommand = new AddCommand(state);
        DiedListener diedListener = new DiedListener(state);
        ListCommand listCommand = new ListCommand(state);
        WorldSaveListener worldSaveListener = new WorldSaveListener(state);
        this.getCommand("hardcoreish").setExecutor(addCommand);
        this.getCommand("hard-list").setExecutor(listCommand);
        pm.registerEvents(diedListener, this);
        pm.registerEvents(worldSaveListener, this);
    }

}
