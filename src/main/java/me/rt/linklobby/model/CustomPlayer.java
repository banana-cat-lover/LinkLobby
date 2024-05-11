package me.rt.linklobby.model;

import java.util.UUID;

public class CustomPlayer {
    private final String playerName;
    private LinklCard linklData;
    private final String id;

    public CustomPlayer(String playerName, LinklCard linklData) {
        this.playerName = playerName;
        this.linklData = linklData;
        this.id = UUID.randomUUID().toString();
    }

    public boolean setCategory(String cat, String data) {
        return linklData.setCategory(cat, data);
    }

    public String getId() {
        return this.id;
    }

    public LinklCard getLinklData() {
        return this.linklData;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void toggleVisibility() {
        linklData.toggleVisibility();
    }

    public boolean isPublic() {
        return this.linklData.getIsPublic();
    }

    public void setLinkl(LinklCard lc) {
        this.linklData = lc;
    }

}
