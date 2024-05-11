package me.rt.linklobby.commands;

import me.rt.linklobby.model.CustomPlayer;
import me.rt.linklobby.model.Category;
import me.rt.linklobby.Utils;
import me.rt.linklobby.io.LocalDB;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class CommandLinkl implements CommandExecutor {
    private static final String prefix = ChatColor.GOLD + "[LinkLobby]: ";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg1, String[] args) {
        if (sender instanceof Player) {
            Player requester = (Player) sender;
            CustomPlayer customRequester = Utils.getCustomPlayer(requester);
            if (args.length == 0) {
                sendCommandUsage(requester);
            } else if (args[0].equals("add")) {
                handleAdd(args, requester, customRequester);
            } else if (args[0].equals("show")) {
                handleShowProfile(args, requester);
            } else if (args[0].equals("public") && args.length == 1) {
                handleVisibility(customRequester, requester, true);
            } else if (args[0].equals("private") && args.length == 1) {
               handleVisibility(customRequester, requester, false);
            } else if (args[0].equals("categories") && args.length == 1) {
                handleCategories(requester);
            } else {
                requester.sendMessage(prefix + ChatColor.BLUE + "Command not recognized!");
            }
        }

        return true;
    }

    private void handleCategories(Player requester) {
        requester.sendMessage(prefix + ChatColor.BLUE + Category.stringify());
    }

    private void sendCommandUsage(Player req) {
        req.sendMessage(prefix + "\n" + ChatColor.RED +
            "/linkl categories - displays all categories" + "\n" +
            "/link add <category> <description> - add a description to specific category \n" +
            "(leave description blank to remove category from profile)" + "\n" +
            "/linkl public - make your profile public" + "\n" +
            "/linkl private - make your profile private" + "\n" +
            "/linkl show - view your profile" + "\n" +
            "/linkl show <username> - view [username's] profile\n"
        );
    }

    private void handleShowProfile(String[] args, Player requester) {
        Player target;
        if (args.length == 1) {
            target = requester;
        } else {
            target = Bukkit.getPlayerExact(args[1]);
        }
        if (target != null) {
            CustomPlayer receiver = Utils.getCustomPlayer(target);
            if (receiver.isPublic() || target == requester) {
                String content = receiver.getLinklData().toString();
                if (content.isEmpty()) {
                    requester.sendMessage(prefix + ChatColor.BLUE + receiver.getPlayerName() + "'s Linkl is empty");
                } else {
                    requester.sendMessage(ChatColor.GOLD + receiver.getPlayerName() + "'s Linkl\n" + ChatColor.BLUE + content);
                }
            } else {
                requester.sendMessage(prefix + ChatColor.BLUE + receiver.getPlayerName() + " has a private profile");
            }
        } else {
            OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);
            CustomPlayer cp  = LocalDB.findPlayer(op.getName());
            if (cp == null) {
                requester.sendMessage(prefix + ChatColor.BLUE + "Player not found");
            } else {
                if (cp.isPublic()) {
                    String content = cp.getLinklData().toString();
                    if (content.isEmpty()) {
                        requester.sendMessage(prefix + ChatColor.BLUE + cp.getPlayerName() + "'s Linkl is empty");
                    } else {
                        requester.sendMessage(ChatColor.GOLD + cp.getPlayerName() + "'s Linkl\n" + ChatColor.BLUE + content);
                    }
                } else {
                    requester.sendMessage(prefix + ChatColor.BLUE + cp.getPlayerName() + " has a private profile");
                }
            }
        }
    }

    private void handleVisibility(CustomPlayer cp, Player requester, boolean toPublic) {
        String status = toPublic ? "public" : "private";
        if (cp.isPublic() != toPublic) {
            cp.toggleVisibility();
            LocalDB.updatePlayer(cp.getPlayerName(), cp.getLinklData());

            requester.sendMessage(prefix + ChatColor.BLUE + "Profile is now " + status);
        } else {
            requester.sendMessage(prefix + ChatColor.BLUE + "Profile is already " + status);
        }
    }

    private void handleAdd(String[] args, Player requester, CustomPlayer customRequester) {
        StringBuilder sb = new StringBuilder();
        if (args.length > 2) {
            for (int i = 2; i < args.length-1; i++) {
                sb.append(args[i]).append(" ");
            }
            sb.append(args[args.length-1]);
        }

        if (sb.length() > 32) {
            requester.sendMessage(prefix + ChatColor.BLUE + "Description too long");
        } else {
            String cat = args[1].substring(0, 1).toUpperCase() + args[1].substring(1).toLowerCase();
            if (customRequester.setCategory(args[1].toUpperCase(), sb.toString())) {
                LocalDB.updatePlayer(requester.getName(), customRequester.getLinklData());
                requester.sendMessage(prefix + ChatColor.BLUE + cat + " was updated successfully!");
            } else {
                requester.sendMessage(prefix + ChatColor.BLUE + cat + " is not a valid category.\n/linkl categories to see categories.");
            }
        }
    }


}
