# DeluxeCore

Shared library used by the Deluxe series of Spigot/Bukkit plugins. Not a
standalone plugin — it's shaded into each Deluxe* plugin and provides
common utilities so they don't have to reimplement the same boilerplate.

## What's Included

- **Inventory API** — `CustomInventory`, `InventoryItem`, `InventoryManager`,
  `ClickInterface` for building menus and handling clicks.
- **Input prompts** — `AnvilInput`, `ChatInput`, `SignInput` for asking
  the player for text input.
- **Utilities** — `ColorUtils`, `ItemUtils`, `MaterialHelper`,
  `Placeholders`, `PlaceholderUtils`, `Base64Utils`.

## Requirements

- Spigot/Paper API 1.21+
- Java 17+

## Usage

Include DeluxeCore as a Maven dependency (or shade the source into your
plugin), then initialize it from your plugin's `onEnable`:

```java
@Override
public void onEnable() {
    new DeluxeCore().setup(this);
    // ... your plugin setup
}
```

After that you can use the inventory API, input prompts, and utility
classes from your plugin.
