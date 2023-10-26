package me.manglam.skyblockcore.Scoreboard;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomScoreboard {

    private final JavaPlugin plugin;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");

    public CustomScoreboard(JavaPlugin plugin) {
        this.plugin = plugin;

        new BukkitRunnable() {
            @Override
            public void run() {
                updateScoreboard();
            }
        }.runTaskTimer(plugin, 0, 1200); // Update every 1 minute (20 ticks * 60 seconds)
    }

    public static String parseTime(long time) {
        long gameTime = time;
        long hours = gameTime / 1000 + 6;
        long minutes = (gameTime % 1000) * 60 / 1000;
        String ampm = "AM";
        if (hours >= 12) {
            hours -= 12;
            ampm = "PM";
        }
        if (hours >= 12) {
            hours -= 12;
            ampm = "AM";
        }
        if (hours == 0) hours = 12;
        String mm = "0" + minutes;
        mm = mm.substring(mm.length() - 2, mm.length());
        return hours + ":" + mm + " " + ampm;
    }

    private String formatColorCodes(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private void updateScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String date = dateFormat.format(new Date());
            String currentTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
            dateFormat.format(new Date(System.currentTimeMillis()));

            Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
            Objective obj = board.registerNewObjective("test", "dummy");
            obj.setDisplayName(formatColorCodes("&e&lSKYBLOCK"));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score idSkyblock = obj.getScore(formatColorCodes("&7" + date + " mini01A"));
            idSkyblock.setScore(13);
            obj.getScore(formatColorCodes("&a")).setScore(12);
            Score monthSkyblock = obj.getScore(formatColorCodes("&fEarly Summer 5th"));
            monthSkyblock.setScore(11);
            Score timeSkyblock = obj.getScore(formatColorCodes("&7" + currentTime + "am&6 ☀"));
            timeSkyblock.setScore(10);
            Score locSkyblock = obj.getScore(formatColorCodes("&7⏣&a Hub"));
            locSkyblock.setScore(9);
            obj.getScore(formatColorCodes("&2")).setScore(8);
            Score coinSkyblock = obj.getScore(formatColorCodes("&fPurse:&6 " + PlaceholderAPI.setPlaceholders(player, "0")));
            coinSkyblock.setScore(7);
            Score bitSkyblock = obj.getScore(formatColorCodes("&fBits:&b 0"));
            bitSkyblock.setScore(6);
            obj.getScore(formatColorCodes("&0")).setScore(5);
            Score taskSkyblock = obj.getScore(formatColorCodes("&fObjective"));
            taskSkyblock.setScore(4);
            Score taskCoreSkyblock = obj.getScore(formatColorCodes("&eOverthrow Dante!"));
            taskCoreSkyblock.setScore(3);
            obj.getScore(formatColorCodes("&1")).setScore(2);
            Score addressSkyblock = obj.getScore(formatColorCodes("&emc.gamepixel.fun"));
            addressSkyblock.setScore(1);

            player.setScoreboard(board);
        }
    }
}
