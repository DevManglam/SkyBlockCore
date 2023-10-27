package me.manglam.skyblockcore;

import me.manglam.skyblockcore.Core.Stats;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import me.manglam.skyblockcore.Scoreboard.CustomScoreboard;
import me.manglam.skyblockcore.Core.Commands.CommandsManager;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public final class SkyBlockCore extends JavaPlugin {

    private static HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    private static SkyBlockCore main;

    // Existing code...
    public static SkyBlockCore getInstance() {
        return main;
    }

    private BukkitTask task;

    private BukkitTask task2;

    private BukkitTask task3;

    @Override
    public void onEnable() {
        setupEnums();
        registerCommands();
        registerListeners();

        CustomScoreboard customScoreboard = new CustomScoreboard(this);
        Stats.startActionBarUpdater();
    }

    @Override
    public void onDisable() {
        main = null;
        perms.clear();
        if (this.task != null && Bukkit.getScheduler().isCurrentlyRunning(this.task.getTaskId()))
            this.task.cancel();
        if (this.task2 != null && Bukkit.getScheduler().isCurrentlyRunning(this.task2.getTaskId()))
            this.task2.cancel();
        if (this.task3 != null && Bukkit.getScheduler().isCurrentlyRunning(this.task3.getTaskId()))
            this.task3.cancel();
    }

    private void setupEnums() {
    }

    private void registerCommands() {
        getCommand("skyblock").setExecutor(new CommandsManager());
    }

    private void registerListeners() {
    }
}
