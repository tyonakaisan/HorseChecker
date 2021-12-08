package com.tyonakaisan.github.horsecheckerplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {

    private static  final String[] HORSE_CHECKER_COMMANDS = {
            "help",
            "statsinfo",
    };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        final List<String> commands = new ArrayList<>(Arrays.asList(HORSE_CHECKER_COMMANDS));
        List<String> matches = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], commands, matches);
        Collections.sort(matches);
        return matches;
    }
}
