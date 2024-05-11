package me.rt.linklobby.model;

import org.bukkit.ChatColor;

import java.util.*;

import static me.rt.linklobby.model.Category.CATEGORIES;


public class LinklCard {
    private final LinkedHashMap<String, String> mainInfo;
    private boolean isPublic = false;

    public LinklCard() {
        this.mainInfo = new LinkedHashMap<>();
        for (String c : CATEGORIES()) {
            mainInfo.put(c, "");
        }
    }

    public boolean setCategory(String cat, String content) {
        // in case you update the categories.txt file without modifying the json
        for (String c : CATEGORIES()) {
            if (!mainInfo.containsKey(c)) {
                this.mainInfo.put(c, "");
            }
        }

        if (mainInfo.containsKey(cat.toUpperCase())) {
            mainInfo.put(cat, content);
            return true;
        } else {
            return false;
        }

    }

    public void toggleVisibility() {
        this.isPublic = !this.isPublic;
    }

    public boolean getIsPublic() {
        return this.isPublic;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (String cat : this.mainInfo.keySet()) {
            System.out.println(cat);
            if (!Objects.equals(mainInfo.get(cat), "")) {
                String catFormat = cat.substring(0, 1).toUpperCase() + cat.substring(1).toLowerCase();
                res.append(ChatColor.BLUE).append(catFormat).append(": ").append(ChatColor.WHITE).append(mainInfo.get(cat)).append("\n");
            }
        }

        return res.toString();
    }
}
