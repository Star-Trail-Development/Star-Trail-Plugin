package com.dark2932.startrailplugin.command;

import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetheadCommandExecutor implements CommandExecutor, TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String input, String[] args) {
        Server server = sender.getServer();
        if (input.equalsIgnoreCase("gethead")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player player = (Player) sender;
                    final ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
                    if (stack.getItemMeta() instanceof SkullMeta meta) {
                        meta.setOwnerProfile(server.createPlayerProfile(args[0]));
                        stack.setItemMeta(meta);
                    }
                    player.getWorld().dropItemNaturally(player.getLocation(), stack);
                    return true;
                } else {
                    sender.sendMessage("参数错误，请输入一位玩家的名字。");
                    return true;
                }
            } else {
                sender.sendMessage("只有玩家才可以使用此命令。");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String input, String[] args) {
        Server server = sender.getServer();
        LinkedList<String> list = new LinkedList<>();
        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (Player onlinePlayer : server.getOnlinePlayers()) { names.add(onlinePlayer.getName()); }
            if (args[0].isEmpty()) {
                list.addAll(names);
            } else {
                for (String name : names) {
                    if (name.toLowerCase().startsWith(args[0].toLowerCase())) {
                        list.add(name);
                    }
                }
            }
        }
        return list;
    }

}
