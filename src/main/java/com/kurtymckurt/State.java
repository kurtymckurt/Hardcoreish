package com.kurtymckurt;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class State implements Serializable {

    private final Set<UUID> users;

    private final static String FILE_PATH = "hardcore-player.obj";

    public State() {
        users = Collections.synchronizedSet(new HashSet<UUID>());
    }

    public void addUser(UUID userUUID) {
        users.add(userUUID);
        State.saveState(this);
    }

    public void removeUser(UUID userUUID) {
        users.remove(userUUID);
        State.saveState(this);
    }

    @Override
    public String toString() {
        return "State{" +
                "users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(users, state.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(users);
    }

    public boolean containsUser(UUID userUUID) {
        return users.contains(userUUID);
    }

    public static void saveState(State state) {
        try ( BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(FILE_PATH)))){
            out.writeObject(state);
        } catch (IOException e) {
            Bukkit.getServer().broadcastMessage("Couldn't save hardcoreish data: " + e.toString());
        }
    }



    public static State loadState() {
        try (BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(FILE_PATH)))) {
            return (State) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            Bukkit.getServer().broadcastMessage("Couldn't load hardcoreish data: " + e.toString());
            return null;
        }
    }
}
