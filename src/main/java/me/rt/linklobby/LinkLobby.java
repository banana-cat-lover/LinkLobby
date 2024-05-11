package me.rt.linklobby;

import me.rt.linklobby.commands.CommandLinkl;
import me.rt.linklobby.io.LocalDB;
import me.rt.linklobby.model.Category;
import org.bukkit.plugin.java.JavaPlugin;

public final class LinkLobby extends JavaPlugin {
    private static LinkLobby instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        Category.initializeCategory();
        this.getCommand("linkl").setExecutor(new CommandLinkl());
        LocalDB.loadPlayers();
    }

    public static LinkLobby getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
//        LocalDB.saveCustomPlayer();
    }


}
