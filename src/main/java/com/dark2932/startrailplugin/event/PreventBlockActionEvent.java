package com.dark2932.startrailplugin.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class PreventBlockActionEvent implements Listener {

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent event) {
        final Block block = event.getBlock();
        final Material type = block.getType();
        if (block.getWorld().getName().equals("memorial") && (type.equals(Material.WATER) || type.equals(Material.LAVA))) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockFalling(EntityChangeBlockEvent event) {
        if (event.getEntity().getWorld().getName().equals("memorial") && event.getEntityType().equals(EntityType.FALLING_BLOCK)) {
            event.setCancelled(true);
        }
    }

}
