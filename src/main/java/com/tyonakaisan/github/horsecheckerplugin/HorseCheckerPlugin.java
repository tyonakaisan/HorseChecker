package com.tyonakaisan.github.horsecheckerplugin;

import com.tyonakaisan.github.horsecheckerplugin.command.TabComplete;
import com.tyonakaisan.github.horsecheckerplugin.command.maincommand;
import com.tyonakaisan.github.horsecheckerplugin.manager.ConfigManager;
import com.tyonakaisan.github.horsecheckerplugin.horselistener.GetHorseStatsListener;
import com.tyonakaisan.github.horsecheckerplugin.horselistener.HorseCancelBreedListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class HorseCheckerPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    private GetHorseStatsListener statsListener;
    private HorseCancelBreedListener breedListener;
    private ConfigManager configmanager;
    public ConfigManager getConfigManager() {
        return this.configmanager;
    }

    @Override
    public void onEnable() {


        getCommand("hch").setExecutor(new maincommand());
        this.getCommand("hch").setTabCompleter(new TabComplete());

        plugin = this;
        configmanager = new ConfigManager(this);
        this.statsListener = new GetHorseStatsListener();
        this.breedListener = new HorseCancelBreedListener();
        getServer().getPluginManager().registerEvents(this.statsListener, this);
        getServer().getPluginManager().registerEvents(this.breedListener, this);
    }



        @Override
        public void onDisable () {
            // Plugin shutdown logic
        }
    }
