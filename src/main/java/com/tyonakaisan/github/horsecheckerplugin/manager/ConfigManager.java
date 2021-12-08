package com.tyonakaisan.github.horsecheckerplugin.manager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
    private final Plugin plugin;
    private FileConfiguration config = null;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        this.load();
    }

    public String STATS_ITEM;
    public boolean MESSAGE_CHECK_SPEED;
    public boolean MESSAGE_CHECK_JUMP;
    public boolean MESSAGE_CHECK_HEALTH;
    public boolean MESSAGE_CHECK_TAME;
    public boolean MESSAGE_CHECK_AGE;
    public boolean MESSAGE_CHECK_OWNER;
    public boolean BREED_CANCEL_CHECK;


        public void load() {

            plugin.saveDefaultConfig();

            if (config != null) {
                plugin.reloadConfig();
            }

            config = plugin.getConfig();

            STATS_ITEM = this.plugin.getConfig().getString("Item");
            MESSAGE_CHECK_SPEED = this.plugin.getConfig().getBoolean("Messages.Speed", true);
            MESSAGE_CHECK_JUMP = this.plugin.getConfig().getBoolean("Messages.Jump", true);
            MESSAGE_CHECK_HEALTH = this.plugin.getConfig().getBoolean("Messages.Health", true);
            MESSAGE_CHECK_TAME = this.plugin.getConfig().getBoolean("Messages.Tame", true);
            MESSAGE_CHECK_AGE = this.plugin.getConfig().getBoolean("Messages.Age", true);
            MESSAGE_CHECK_OWNER = this.plugin.getConfig().getBoolean("Messages.Owner", true);
            BREED_CANCEL_CHECK = this.plugin.getConfig().getBoolean("CancelBreeding", true);
        }
}
