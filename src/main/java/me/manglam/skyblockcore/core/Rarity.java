package me.manglam.skyblockcore.core;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.BOLD + "COMMON", ChatColor.WHITE),
    UNCOMMON(ChatColor.BOLD + "UNCOMMON", ChatColor.GREEN),
    RARE(ChatColor.BOLD + "RARE", ChatColor.BLUE),
    EPIC(ChatColor.BOLD + "EPIC", ChatColor.DARK_PURPLE),
    MYTHICAL(ChatColor.BOLD + "MYTHICAL", ChatColor.LIGHT_PURPLE),
    LEGENDARY(ChatColor.BOLD + "LEGENDARY", ChatColor.GOLD);

    public enum Type {
        SWORD,
        PICKAXE,
        ITEM,
        AXE
    }

    private final String name;
    private final ChatColor color;
    private Type itemType;

    Rarity(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ChatColor getRarityColor() {
        return color;
    }

    public void setItemType(Type itemType) {
        this.itemType = itemType;
    }

    public Type getItemType() {
        return itemType;
    }

    public String getLore() {
        if (itemType != null) {
            return color + "" + ChatColor.BOLD + name + " " + itemType.name() + "\n";
        } else {
            return color + "" + ChatColor.BOLD + name + "\n";
        }
    }
}