package com.dark2932.startrailplugin.command;

import com.dark2932.startrailplugin.util.MessageUtil;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
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

public class gethead implements CommandExecutor, TabExecutor {

    private static final String PERMISSION = "startrailplugin.command.gethead";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String input, String[] args) {
        Server server = sender.getServer();
        if (input.equalsIgnoreCase("gethead")) {
            if (sender instanceof Player player && player.hasPermission(PERMISSION)) {
                World world = player.getWorld();
                ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
                if (stack.getItemMeta() instanceof SkullMeta meta) {
                    if (args.length == 1) {
                        meta.setOwnerProfile(server.createPlayerProfile(args[0]));
                        sender.sendMessage(MessageUtil.format("a") + "你获取了一个" + args[0] + "的头颅。");
                    } else if (args.length == 0) {
                        meta.setOwnerProfile(server.createPlayerProfile(player.getName()));
                        sender.sendMessage(MessageUtil.format("a") + "你获取了一个你自己的头颅。");
                    } else {
                        sender.sendMessage(MessageUtil.format("c") + "参数错误，你不能输入多个玩家的名字。");
                        return false;
                    }
                    stack.setItemMeta(meta);
                    world.dropItemNaturally(player.getLocation(), stack);
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
