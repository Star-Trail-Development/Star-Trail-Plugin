package com.dark2932.startrailplugin.command;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GetheadCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("gethead")) {
            if (sender instanceof Player) {
                if (args.length == 1) {
                    Player player = (Player) sender;
                    final ItemStack stack = new ItemStack(Material.PLAYER_HEAD, 1);
                    if (stack.getItemMeta() instanceof SkullMeta) {
                        SkullMeta meta = (SkullMeta) stack.getItemMeta();
                        meta.setOwningPlayer(player.getServer().getPlayer(args[0]));
                        stack.setItemMeta(meta);
                    }
                    player.getWorld().dropItemNaturally(player.getLocation(), stack);
                    return true;
                } else {
                    sender.sendMessage("参数错误，只能输入一位玩家的名字。");
                    return true;
                }
            } else {
                sender.sendMessage("只有玩家才可以使用此命令。");
                return true;
            }
        }
        return false;
    }
}
