package me.rt.linklobby;

import me.rt.linklobby.io.LocalDB;
import me.rt.linklobby.model.CustomPlayer;
import me.rt.linklobby.model.LinklCard;
import org.bukkit.entity.Player;

public class Utils {
    // put in utils class
    public static String capitalizeWords(String input) {
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
            }
        }

        return result.toString().trim();
    }

    public static CustomPlayer getCustomPlayer(Player requester) {
        CustomPlayer target;
        target = LocalDB.findPlayer(requester.getName());

        if (target == null) {
            target = LocalDB.createCustomPlayer(requester, new LinklCard());
        }

        return target;
    }
}
