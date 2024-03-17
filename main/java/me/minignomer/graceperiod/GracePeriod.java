package me.minignomer.graceperiod;

import me.minignomer.graceperiod.commands.GraceperiodCommand;
import me.minignomer.graceperiod.commands.GraceperiodTabcompleter;
import me.minignomer.graceperiod.listeners.DamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class GracePeriod extends JavaPlugin {

    public static GracePeriod instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("graceperiod").setExecutor(new GraceperiodCommand());
        getCommand("graceperiod").setTabCompleter(new GraceperiodTabcompleter());
        getServer().getPluginManager().registerEvents(new DamageEvent(), this);
    }
}
