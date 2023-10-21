package me.manglam.skyblockcore.core.Commands;

import me.manglam.skyblockcore.core.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CommandsManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 4 || !args[1].equalsIgnoreCase("set")) {
            player.sendMessage(ChatColor.RED + "Usage: /skyblock rarity set <rarity name> <type>");
            return true;
        }

        String rarityName = args[2].toUpperCase();
        String itemType = args[3].toUpperCase();

        try {
            Rarity rarity = Rarity.valueOf(rarityName);
            Rarity.Type itemTypeEnum = Rarity.Type.valueOf(itemType);
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(rarity.getRarityColor() + "" + ChatColor.BOLD + rarity.getName());
                meta.setLore(Collections.singletonList(rarity.getLore()));
                rarity.setItemType(itemTypeEnum);
                item.setItemMeta(meta);

                player.sendMessage(ChatColor.GREEN + "Rarity and item type set successfully!");
            } else {
                player.sendMessage(ChatColor.RED + "Could not modify item.");
            }
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid rarity name or item type.");
        }

        return true;
    }
}
