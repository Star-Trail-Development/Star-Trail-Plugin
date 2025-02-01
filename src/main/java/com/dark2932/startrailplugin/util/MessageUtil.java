package com.dark2932.startrailplugin.util;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static void sendWarnMessage(Player player, String action) {
        World world = player.getWorld();
        switch (world.getName()) {
            case "memorial":
                msg(player, format("c"), "你不能在回忆世界" + action + "!");
            case "main":
            default:
                msg(player, format("c"), "你不能在斯达德莱勒世界" + action + "!");
        }
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
    }

    public static ChatColor format(int i) {
        return switch (i) {
            case 0 -> ChatColor.BLACK;
            case 1 -> ChatColor.DARK_BLUE;
            case 2 -> ChatColor.DARK_GREEN;
            case 3 -> ChatColor.DARK_AQUA;
            case 4 -> ChatColor.DARK_RED;
            case 5 -> ChatColor.DARK_PURPLE;
            case 6 -> ChatColor.GOLD;
            case 7 -> ChatColor.GRAY;
            case 8 -> ChatColor.DARK_GRAY;
            case 9 -> ChatColor.BLUE;
            default -> ChatColor.RESET;
        };
    }

    public static ChatColor format(String s) {
        return switch (s) {
            case "a" -> ChatColor.GREEN;
            case "b" -> ChatColor.AQUA;
            case "c" -> ChatColor.RED;
            case "d" -> ChatColor.LIGHT_PURPLE;
            case "e" -> ChatColor.YELLOW;
            case "f" -> ChatColor.WHITE;
            case "k" -> ChatColor.STRIKETHROUGH;
            case "l" -> ChatColor.BOLD;
            case "m" -> ChatColor.MAGIC;
            case "n" -> ChatColor.UNDERLINE;
            case "o" -> ChatColor.ITALIC;
            default -> ChatColor.RESET;
        };
    }

    private static void msg(Player player, ChatColor color, String text) {
        player.sendMessage(color + text);
    }

}
