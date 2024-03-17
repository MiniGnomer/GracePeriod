package me.minignomer.graceperiod.commands;

import me.minignomer.graceperiod.GracePeriod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class GraceperiodCommand implements CommandExecutor {

    // /graceperiod enable/disable 4 seconds/minutes/hours

    final String correctUsage = "§c§lIncorrect Usage!\n§r§a§lProper usage:§r§a /graceperiod <enable/disable> <amount of time> <seconds/minutes/hours>";
    final String pvpOn = "§aPVP has been §r§2§lENABLED";
    final String pvpOff = "§cPVP has been §r§4§lDISABLED";
    public static boolean enabled;

    public void toggleGrace(CommandSender sender, String str) {
        if (str.equalsIgnoreCase("enable")) {
            Bukkit.broadcastMessage(pvpOff);
            enabled = true;
        } else if (str.equalsIgnoreCase("disable")) {
            Bukkit.broadcastMessage(pvpOn);
            enabled = false;
        } else {
            sender.sendMessage(correctUsage);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {
            toggleGrace(sender, args[0]);
            return true;
        }

        int amount = Integer.parseInt(args[1]);

        if (amount <= 0) {
            sender.sendMessage("§c§lThe amount must be higher than 0!");
            return true;
        }

        long totalAmount;

        String timeUnit = args[2];

        switch (timeUnit) {
            case "seconds":
                totalAmount = amount * 20L;
                break;
            case "minutes":
                totalAmount = amount * 20L * 60L;
                break;
            case "hours":
                totalAmount = amount * 20L * 60L * 60L;
                break;
            default:
                sender.sendMessage(correctUsage);
                return true;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                toggleGrace(sender, args[0]);
            }
        }.runTaskLater(GracePeriod.instance, totalAmount);

        return true;
    }
}
