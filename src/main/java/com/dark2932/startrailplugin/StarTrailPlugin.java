package com.dark2932.startrailplugin;

import com.dark2932.startrailplugin.command.gethead;
import com.dark2932.startrailplugin.event.PreventBlockActionEvent;
import com.dark2932.startrailplugin.event.PreventPlayerInteractInMainEvent;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class StarTrailPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommand("gethead", new gethead());
        registerEvent(new PreventPlayerInteractInMainEvent());
        registerEvent(new PreventBlockActionEvent());
    }

    @Override
    public void onDisable() {}

    private void registerEvent(Listener listener) {
        this.getServer().getPluginManager().registerEvents(listener, this);
    }

    private void registerCommand(String command, CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }

}
