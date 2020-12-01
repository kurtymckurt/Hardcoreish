package com.kurtymckurt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;

public class WorldSaveListener implements Listener {

    private final State state;

    public WorldSaveListener(State state) {
        this.state = state;
    }

    @EventHandler
    public void saveTheWorld(WorldSaveEvent event) {
        State.saveState(state);
    }
}
