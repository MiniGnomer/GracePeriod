package me.minignomer.graceperiod.commands;

import me.minignomer.graceperiod.GracePeriod;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class GraceperiodCommand implements CommandExecutor {

    // /graceperiod enable/disable number seconds/minutes/hours

    final String correctUsage = "§c§lIncorrect Usage!\n§r§a§lProper usage:§r§a /graceperiod <enable/disable> <amount of time> <seconds/minutes/hours>";
    final String pvpOn = "§aGrace period has been §r§2§lDISABLED";
    final String pvpOff = "§cGrace period has been §r§4§lENABLED";
    public static boolean enabled;

    public void toggleGrace(CommandSender sender, String str) {
        if (str.equalsIgnoreCase("enable"))
        {
            Bukkit.broadcastMessage(pvpOff);
            enabled = true;
            return;
        }

        if (str.equalsIgnoreCase("disable"))
        {
            Bukkit.broadcastMessage(pvpOn);
            enabled = false;
            return;
        }

        sender.sendMessage(correctUsage);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1)
        {
            toggleGrace(sender, args[0]);
            return true;
        }

        if (args.length == 2 || args.length > 3)
        {
            sender.sendMessage(correctUsage);
            return true;
        }

        int amount = Integer.parseInt(args[1]);

        if (amount <= 0)
        {
            sender.sendMessage("§c§lThe amount must be higher than 0!");
            return true;
        }

        long totalAmount;

        String timeUnit = args[2];

        if (!args[0].equalsIgnoreCase("enable") && !args[0].equalsIgnoreCase("disable"))
        {
            sender.sendMessage(correctUsage);
            return true;
        }

        switch (timeUnit)
        {
            case "seconds":
                totalAmount = amount * 20L;
                sender.sendMessage("§a§lGrace period will be " + args[0].toLowerCase() + "d in " + amount + " seconds!");
                break;
            case "minutes":
                totalAmount = amount * 20L * 60L;
                sender.sendMessage("§a§lGrace period will be " + args[0].toLowerCase() + "d in " + amount + " minutes!");
                break;
            case "hours":
                totalAmount = amount * 20L * 60L * 60L;
                sender.sendMessage("§a§lGrace period will be " + args[0].toLowerCase() + "d in " + amount + " hours!");
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
