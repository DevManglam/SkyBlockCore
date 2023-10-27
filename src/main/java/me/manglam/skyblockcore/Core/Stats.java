package me.manglam.skyblockcore.Core;

import lombok.Getter;
import me.manglam.skyblockcore.SkyBlockCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;


public enum Stats {
    Health("health", '❤', ChatColor.RED, "Health", false, 4, 100, -1),
    Defense("def", '❈', ChatColor.GREEN, "Defense", false, 2, 0, -1),
    Intelligence("mana", '✎', ChatColor.AQUA, "Intelligence", false, 0, 0, 100),
    Speed("speed", '✦', ChatColor.WHITE, "Speed", false, 0, 0, 100),
    Strength("strength", '❁', ChatColor.RED, "Strength", true, 2, 0, -1),
    CritDamage("cd", '☠', ChatColor.BLUE, "Crit Damage", true, 0, 0, 50),
    CritChance("cc", '☣', ChatColor.BLUE, "Crit Chance", true, 0, 0, 30),
    AbilityDamage("abilitydamage", '๑', ChatColor.RED, "Ability Damage", true, 0, 0, -1),
    Ferocity("ferocity", '⫽', ChatColor.RED, "Ferocity", false, 0, 0, -1),
    MagicFind("magicfind", '✯', ChatColor.AQUA, "Magic Find", false, 0, 0, -1),
    MiningSpeed("miningspeed", '⸕', ChatColor.GOLD, "Mining Speed", false, 0, 0, -1),
    MiningFortune("miningfortune", '☘', ChatColor.GOLD, "Mining Fortune", false, 0, 0, -1),
    Pristine("pristine", '✧', ChatColor.DARK_PURPLE, "Pristine", false, 0, 0, -1),
    AttackSpeed("as", '⚔', ChatColor.YELLOW, "Attack Speed", true, 0, 0, -1),
    TrueDefense("truedefense", '❂', ChatColor.WHITE, "True Defense", false, 0, 0, -1),
    SeaCreatureChance("seacreaturechance", 'α', ChatColor.DARK_AQUA, "Sea Creature Chance", false, 0, 0, 20),
    FishingSpeed("fishingspeed", '☂', ChatColor.AQUA, "Fishing Speed", false, 0, 0, -1),
    SwingRange("swingrange", ' ', ChatColor.GOLD, "Swing Range", true, 0, 0, -1),
    BreakingPower("breakingpower", '℗', ChatColor.DARK_GREEN, "Breaking Power", false, 0, 0, -1),
    FarmingFortune("farmingfortune", '☘', ChatColor.GOLD, "Farming Fortune", false, 0, 0, -1),
    FarmingWisdom("farmingwisdom", '☯', ChatColor.DARK_AQUA, "Farming Wisdom", false, 0, 0, -1),
    HealthRegen("healthregen", '❣', ChatColor.RED, "Health Regen", false, 0, 0, -1),
    WeaponDamage("dmg", ' ', ChatColor.RED, "Damage", true, 2, 0, -1);

    public static final List<Stats> statItemDisplayOrder = List.of(WeaponDamage, Strength, CritChance, CritDamage, AttackSpeed, AbilityDamage, SwingRange, Health, Defense, Intelligence, MagicFind, Ferocity, MiningSpeed, Pristine, MiningFortune, FarmingFortune, SeaCreatureChance, FishingSpeed, FarmingWisdom);

    private final String dataName;
    private final char symbol;
    private final ChatColor color;
    private final String name;
    @Getter
    private final boolean aggressive;
    @Getter
    private final double baseAmount;
    @Getter
    private final double minAmount;
    @Getter
    private final double maxAmount;
    private final Map<UUID, Double> playerValues = new HashMap<>();
    private static BukkitRunnable task;

    public static class stats extends BukkitRunnable {
        @Override
        public void run() {
            for (Player player : Bukkit.getOnlinePlayers()) {
                updateActionBar(player);
            }
        }
    }

    Stats(String dataName, char symbol, ChatColor color, String name, boolean aggressive,
          double baseAmount, double minAmount, double maxAmount) {
        this.dataName = dataName;
        this.symbol = symbol;
        this.color = color;
        this.name = name;
        this.aggressive = aggressive;
        this.baseAmount = baseAmount;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    private static void updateActionBar(Player player) {
        // Get the player's Health, Defense, and Intelligence stats
        double health = Health.getPlayerValue(player.getUniqueId());
        double defense = Defense.getPlayerValue(player.getUniqueId());
        double intelligence = Intelligence.getPlayerValue(player.getUniqueId());

        // Format the action bar text
        TextComponent actionBarText = new TextComponent(
                ChatColor.RED + "❤ " + (int) health + "  " +
                        ChatColor.GREEN + "❈ " + (int) defense + "  " +
                        ChatColor.AQUA + "✎ " + (int) intelligence
        );

        // Set the action bar text for the player
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, actionBarText);
    }

    public String getDataName() {
        return dataName;
    }

    public char getSymbol() {
        return symbol;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setPlayerValue(UUID playerId, double value) {
        playerValues.put(playerId, value);
    }

    public double getPlayerValue(UUID playerId) {
        return playerValues.getOrDefault(playerId, baseAmount);
    }

    public void removePlayerValue(UUID playerId) {
        playerValues.remove(playerId);
    }

    public static Stats getByDataName(String dataName) {
        for (Stats stat : values()) {
            if (stat.dataName.equalsIgnoreCase(dataName)) {
                return stat;
            }
        }
        return null;
    }

    public static Stats getBySymbol(char symbol) {
        for (Stats stat : values()) {
            if (stat.symbol == symbol) {
                return stat;
            }
        }
        return null;
    }

    public static void startActionBarUpdater() {
        // Schedule the ActionBar update task
        if (task == null) {
            task = new Stats.stats(); // Create an instance of the inner class
            task.runTaskTimer(SkyBlockCore.getInstance(), 0L, 20L); // Update every second (20 ticks)
        }
    }
}
