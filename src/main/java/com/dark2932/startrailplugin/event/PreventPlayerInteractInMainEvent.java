package com.dark2932.startrailplugin.event;

import com.dark2932.startrailplugin.util.MessageUtil;
import org.bukkit.Material;
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

import java.util.List;

public class PreventPlayerInteractInMainEvent implements Listener {

    private static final List<String> WORLDS = List.of(new String[]{"main", "memorial"});
    private static final String PERMISSION = "startrailplugin.block";

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            if (action.equals(Action.RIGHT_CLICK_BLOCK)) {

                //Item Frame and Armor Stand
                //—————————————————————————————————————————————————————————————————
                PlayerInventory inv = player.getInventory();
                if ((inv.getItemInMainHand().toString().toLowerCase().contains("frame") || inv.getItemInOffHand().toString().toLowerCase().contains("frame")) ||
                    (inv.getItemInMainHand().toString().toLowerCase().contains("armor") || inv.getItemInOffHand().toString().toLowerCase().contains("armor"))) {
                    MessageUtil.sendWarnMessage(player, "放置它");
                    event.setCancelled(true);
                }
                //—————————————————————————————————————————————————————————————————

                Material type = event.getClickedBlock().getType();
                if (type.isInteractable()) {

                    //other vanilla blocks
                    //—————————————————————————————————————————————————————————————————
                    String dataName = type.data.getName();
                    if (dataName.contains("block.data.type")) {
                        if (!(dataName.contains("Door") || dataName.contains("Gate") || dataName.contains("Switch") ||
                              dataName.contains("Campfire") || dataName.contains("Bell") || dataName.contains("Jukebox") ||
                              dataName.contains("NoteBlock") || dataName.contains("Grindstone") || dataName.contains("BrewingStand") ||
                              dataName.contains("Furnace") || dataName.contains("Slab") || dataName.contains("Stairs") ||
                              dataName.contains("Lectern") || dataName.contains("ChiseledBookshelf"))) {
                            MessageUtil.sendWarnMessage(player, "对其进行交互");
                            event.setCancelled(true);
                        }
                    } else if (dataName.contains("material.MaterialData")) {
                        String typeName = type.toString().toLowerCase();
                        if (!(typeName.contains("table") || typeName.contains("beacon") || typeName.contains("cauldron"))) {
                            MessageUtil.sendWarnMessage(player, "对其进行交互");
                            event.setCancelled(true);
                        }
                    }
                    //—————————————————————————————————————————————————————————————————

                    //mod blocks
                    //—————————————————————————————————————————————————————————————————

                    //—————————————————————————————————————————————————————————————————

                }

            }
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            MessageUtil.sendWarnMessage(player, "破坏方块");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            String dataName = event.getBlockReplacedState().getType().data.getName();
            if (!(dataName.contains("Jukebox"))) {
                MessageUtil.sendWarnMessage(player, "放置方块");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerTakeLecternBook(PlayerTakeLecternBookEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            MessageUtil.sendWarnMessage(player, "从讲台上取书");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            String entityName = event.getRightClicked().getName();
            if (entityName.toLowerCase().contains("frame") || entityName.toLowerCase().contains("armor")) {
                MessageUtil.sendWarnMessage(player, "对其进行交互");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof Player player) {
            if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
                String entityName = event.getEntity().getName();
                if (entityName.toLowerCase().contains("frame") || entityName.toLowerCase().contains("armor")) {
                    MessageUtil.sendWarnMessage(player, "对其进行破坏");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            MessageUtil.sendWarnMessage(player, "使用桶");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            MessageUtil.sendWarnMessage(player, "使用桶");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerBucketEntity(PlayerBucketEntityEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(PERMISSION) && WORLDS.contains(player.getWorld().getName())) {
            MessageUtil.sendWarnMessage(player, "使用桶");
            event.setCancelled(true);
        }
    }

}