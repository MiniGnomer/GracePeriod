package me.minignomer.graceperiod.listeners;

import me.minignomer.graceperiod.commands.GraceperiodCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageEvent extends GraceperiodCommand implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity player = event.getEntity();
        Entity damager = event.getDamager();

        if (player.getType() != EntityType.PLAYER || damager.getType() != EntityType.PLAYER) return;
        if (!GraceperiodCommand.enabled) return;

        event.setCancelled(true);
    }
}
