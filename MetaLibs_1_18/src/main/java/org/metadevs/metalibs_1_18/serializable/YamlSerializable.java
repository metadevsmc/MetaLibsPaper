package org.metadevs.metalibs_1_18.serializable;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public abstract class YamlSerializable implements ConfigurationSerializable {

    public @NotNull Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<>();
        Field[] field = getClass().getDeclaredFields();
        for (Field f : field) {
            try {
                f.setAccessible(true);
                if (f.isAnnotationPresent(Serializable.class)) {
                    Serializable serializable = f.getAnnotation(Serializable.class);
                    String key = serializable.key();
                    if (key.equals("")) {
                        key = f.getName();
                    }
                    map.put(key, f.get(this));
                }
                map.put(f.getName(), f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
