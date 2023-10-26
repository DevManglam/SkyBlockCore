package me.manglam.skyblockcore.Manager;
import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ProfileConfig extends JavaPlugin implements Listener {

        private final Plugin plugin;
        private FileConfiguration playerJoinConfig;

        public ProfileConfig(Plugin plugin) {
            this.plugin = plugin;

            playerJoinConfig = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "player_join.yml"));
        }

        public void createProfileFolder(Player player, String profileId) {
            File profilesFolder = new File(plugin.getDataFolder(), "Profiles");

            if (!profilesFolder.exists()) {
                profilesFolder.mkdirs();
            }
            String playerName = player.getUniqueId().toString();

            if (!playerJoinConfig.getBoolean(playerName + " .joined", false)) {
                String folderName = playerName + "-" + profileId;

                File profileFolder = new File(profilesFolder, folderName);
                if (!profileFolder.exists()) {
                    profileFolder.mkdirs();

                    String fileName = playerName + " -profile_data.yml";
                    File configFile = new File(profileFolder, fileName);
                    if (!configFile.exists()) {
                        plugin.saveResource("profile_data.yml", false);
                        configFile.renameTo(new File(profileFolder, fileName));
                    }

                    playerJoinConfig.set(playerName + ".joined", true);
                    savePlayerJoinConfig();
                }
            }
        }

        public void savePlayerJoinConfig() {
            try {
                playerJoinConfig.save(new File(plugin.getDataFolder(), "player_join_data.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
