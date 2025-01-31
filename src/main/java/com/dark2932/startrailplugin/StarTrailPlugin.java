package com.dark2932.startrailplugin;

import com.dark2932.startrailplugin.command.GetheadCommandExecutor;
import com.dark2932.startrailplugin.event.PreventPlayerInteractInMainEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class StarTrailPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("gethead").setExecutor(new GetheadCommandExecutor());
        this.getServer().getPluginManager().registerEvents(new PreventPlayerInteractInMainEvent(), this);
    }

    @Override
    public void onDisable() {}
}
