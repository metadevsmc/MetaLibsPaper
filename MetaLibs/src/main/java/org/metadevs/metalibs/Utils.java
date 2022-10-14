package org.metadevs.metalibs;

import net.md_5.bungee.api.ChatColor;

public class Utils {

    private Utils() {
    }

    public static String color(String toColor) {
        return ChatColor.translateAlternateColorCodes('&', toColor);
    }
}
