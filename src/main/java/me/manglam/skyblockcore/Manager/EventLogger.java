package me.manglam.skyblockcore.Manager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EventLogger extends JavaPlugin implements Listener {

    // Somehow create a way to load a C++ library here
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        DiscordEventLogger(": " + event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        DiscordEventLogger(": " + event.getPlayer().getName());
    }
    private native void DiscordEventLogger(String message);
}


// Yeah this is just a test code
// Will use this to log events such as player command execution etc
// Would prefer if this code is moved to a utils package thanks
