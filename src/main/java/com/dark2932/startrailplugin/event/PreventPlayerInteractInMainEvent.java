package com.dark2932.startrailplugin.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.PlayerInventory;

public class PreventPlayerInteractInMainEvent implements Listener {

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        Action action = event.getAction();
        if (!player.isOp() && !player.hasPermission("startrailplugin.interact")) {
            if (world.getName().equals("main") && action.equals(Action.RIGHT_CLICK_BLOCK)) {
                PlayerInventory inv = player.getInventory();

                if (inv.getItemInMainHand().toString().toLowerCase().contains("frame") || inv.getItemInOffHand().toString().toLowerCase().contains("frame")) {
                    player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界放置物品展示框!");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                    event.setCancelled(true);
                }

                Material type = event.getClickedBlock().getType();
                if (type.isInteractable()) {
                    String dataName = type.data.getName();
                    if (dataName.contains("block.data.type")) {

                        if (!(dataName.contains("Door") || dataName.contains("Gate") || dataName.contains("Switch") ||
                              dataName.contains("Campfire") || dataName.contains("Bell") || dataName.contains("Jukebox") ||
                              dataName.contains("NoteBlock") || dataName.contains("Grindstone") || dataName.contains("BrewingStand") ||
                              dataName.contains("Furnace") || dataName.contains("Chest") || dataName.contains("Barrel") ||
                              dataName.contains("Lectern") || dataName.contains("ChiseledBookshelf"))) {

                            player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界对其进行交互!");
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                            event.setCancelled(true);
                        }
                    } else if (dataName.contains("material.MaterialData")) {
                        String typeName = type.toString().toLowerCase();
                        if (!(typeName.contains("table") || typeName.contains("beacon") || typeName.contains("cauldron"))) {
                            player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界对其进行交互!");
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && !player.hasPermission("startrailplugin.block")) {
            if (player.getWorld().getName().equals("main")) {
                player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界破坏方块!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && !player.hasPermission("startrailplugin.block")) {
            String dataName = event.getBlockReplacedState().getType().data.getName();
            if (player.getWorld().getName().equals("main") && !(dataName.contains("Jukebox"))) {
                player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界放置方块!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && !player.hasPermission("startrailplugin.block") && player.getWorld().getName().equals("main")) {
            player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界从讲台上取书!");
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp() && !player.hasPermission("startrailplugin.block")) {
            String entityName = event.getRightClicked().getName();
            if (player.getWorld().getName().equals("main") && (entityName.toLowerCase().contains("frame") || entityName.toLowerCase().contains("armor"))) {
                player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界对其进行交互!");
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player player) {
            player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界对其进行破坏!");
            if (!player.isOp() && !player.hasPermission("startrailplugin.block")) {
                String entityName = event.getEntity().getName();
                if (player.getWorld().getName().equals("main") && (entityName.toLowerCase().contains("frame") || entityName.toLowerCase().contains("armor"))) {
                    player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界对其进行破坏!");
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界使用桶!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界使用桶!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBucketEntity(PlayerBucketEntityEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.RED + "你不能在斯达德莱勒世界使用桶!");
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO, 1.0f, 0.1f);
        event.setCancelled(true);
    }

}