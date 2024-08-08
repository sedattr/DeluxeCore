package me.sedattr.deluxecore.others;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Placeholders {
    private final Map<String, String> placeholders = new HashMap<>();

    public Placeholders addPlaceholder(String key, String value) {
        if (key == null)
            return this;
        if (value == null)
            return this;

        this.placeholders.put(key, value);
        return this;
    }

    public Placeholders removePlaceholder(String key) {
        if (key == null)
            return this;

        this.placeholders.remove(key);
        return this;
    }
}
