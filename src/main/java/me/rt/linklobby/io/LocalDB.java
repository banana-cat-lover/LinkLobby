package me.rt.linklobby.io;

import com.google.gson.Gson;
import me.rt.linklobby.model.CustomPlayer;
import me.rt.linklobby.LinkLobby;
import me.rt.linklobby.model.LinklCard;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocalDB {
    private static List<CustomPlayer> players = new ArrayList<>();

    private LocalDB(){ }

    public static CustomPlayer findPlayer(String name) {
        for (CustomPlayer cp : players) {
            if (cp.getPlayerName().equalsIgnoreCase(name)) {
                return cp;
            }
        }
        saveCustomPlayer();
        return null;
    }
    public static CustomPlayer createCustomPlayer(Player p, LinklCard lc) {
        CustomPlayer cp = new CustomPlayer(p.getName(), lc);
        players.add(cp);
        saveCustomPlayer();
        return cp;
    }

    public static void deletePlayer(String id)  {
        for (CustomPlayer cp : players) {
            if (cp.getId().equalsIgnoreCase(id)) {
                players.remove(cp);
                break;
            }
        }
    }

    public static CustomPlayer updatePlayer(String name, LinklCard lc) {
        for (CustomPlayer cp : players) {
            if (cp.getId().equalsIgnoreCase(name)) {
                cp.setLinkl(lc);
                return cp;
            }
        }
        saveCustomPlayer();
        return null;
    }

    public static void saveCustomPlayer() {
        Gson gson = new Gson();
        File file = new File(LinkLobby.getInstance().getDataFolder().getAbsolutePath() + "/linklData.json");
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
            Writer writer = new FileWriter(file, false);
            gson.toJson(players, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Fail");
        }
    }

    public static void loadPlayers() {
        Gson gson = new Gson();
        File file = new File(LinkLobby.getInstance().getDataFolder().getAbsolutePath() + "/linklData.json");
        if (file.exists()) {
            Reader reader;
            try {
                reader = new FileReader(file);
            } catch (Exception e) {
                throw new RuntimeException();
            }
            CustomPlayer[] cp = gson.fromJson(reader, CustomPlayer[].class);
            players = new ArrayList<>(Arrays.asList(cp));
        }
        saveCustomPlayer();
    }

}
