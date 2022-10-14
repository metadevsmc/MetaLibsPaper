package org.metadevs.metalibs_1_18.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;

@SuppressWarnings("unused")
public class Util {
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static Component componentColor(String string) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(string);
    }
}
