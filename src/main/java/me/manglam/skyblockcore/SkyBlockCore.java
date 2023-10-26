package me.manglam.skyblockcore;

import org.bukkit.plugin.java.JavaPlugin;
import me.manglam.skyblockcore.Scoreboard.CustomScoreboard;
import me.manglam.skyblockcore.Core.Commands.CommandsManager;

public final class SkyBlockCore extends JavaPlugin {

    @Override
    public void onEnable() {
        setupEnums();
        registerCommands();
        registerListeners();

        CustomScoreboard customScoreboard = new CustomScoreboard(this);

    }

    @Override
    public void onDisable() {
    }

    private void setupEnums() {
    }

    private void registerCommands() {
        getCommand("skyblock").setExecutor(new CommandsManager());
    }

    private void registerListeners() {
    }
}
