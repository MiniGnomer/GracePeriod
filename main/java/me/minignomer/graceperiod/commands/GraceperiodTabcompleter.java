package me.minignomer.graceperiod.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GraceperiodTabcompleter implements TabCompleter {

    // /graceperiod enable/disable 4 seconds/minutes/hours

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<>();
        switch (args.length) {
            case 1:
                options.add("enable");
                options.add("disable");
                return options;
            case 2:
                options.add("1");
                options.add("2");
                options.add("5");
                options.add("10");
                options.add("15");
                options.add("20");
                return options;
            case 3:
                options.add("seconds");
                options.add("minutes");
                options.add("hours");
                return options;
        }
        return null;
    }
}
